package loginlogic

import (
	"context"
	"database/sql"
	"errors"
	"fmt"
	"qywk-server/apps/user/models"
	"qywk-server/apps/user/rpc/internal/svc"
	"qywk-server/apps/user/rpc/user"
	"qywk-server/pkg/constants"
	"qywk-server/pkg/encrypt"
	"qywk-server/pkg/jwts"
	"qywk-server/pkg/redisutils/keys"
	"qywk-server/pkg/redisutils/pre"
	"time"

	"github.com/zeromicro/go-zero/core/logx"
)

type RegisterLogic struct {
	ctx    context.Context
	svcCtx *svc.ServiceContext
	logx.Logger
}

func NewRegisterLogic(ctx context.Context, svcCtx *svc.ServiceContext) *RegisterLogic {
	return &RegisterLogic{
		ctx:    ctx,
		svcCtx: svcCtx,
		Logger: logx.WithContext(ctx),
	}
}

var ctx = context.Background()

// 手机号注册
func (l *RegisterLogic) Register(in *user.RegisterReq) (*user.RegisterResp, error) {
	MDB := l.svcCtx.MDB
	RDB := l.svcCtx.RDB
	ctx := l.ctx

	// 验证码校验
	codeK := keys.Create(pre.VerifyEmail, in.Email)
	code, _ := RDB.Get(ctx, codeK).Result()

	if code == "" || code != in.Code {
		return nil, errors.New("验证码无效")
	} else {
		RDB.Del(ctx, codeK)
	}

	// 邮箱查询, 判断是否已经注册过了
	var userinfo models.UserInfo
	emailK := keys.Create(pre.UserInfoEmail, in.Email)

	res, _ := RDB.Get(ctx, emailK).Result()
	if res != "" {
		return nil, errors.New("邮箱已注册")
	}

	// 如果 Redis 中没有用户信息，查询数据库
	get, err := MDB.Where("e_mail=?", in.Email).Get(&userinfo)
	if err != nil {
		return nil, errors.New("数据库查询异常")
	}

	if get {
		userInfoJson, _ := userinfo.ToString()
		RDB.Set(ctx, emailK, userInfoJson, time.Hour*3).Err()
		return nil, errors.New("邮箱已注册")
	}

	// 判断密码格式是否正确
	if !encrypt.VerifyPwd(in.Password) {
		return nil, errors.New("密码长度至少为 8 个字符，且包含至少一个数字和一个字母")
	}

	md5pwd := encrypt.GetMD5String(in.Password)
	uid := encrypt.GenRandUserId()

	userinfo = models.UserInfo{
		UserId:     uid,
		Password:   md5pwd,
		Tel:        sql.NullString{"", false},
		Email:      sql.NullString{in.Email, true},
		Nickname:   in.Nickname,
		Avatar:     sql.NullString{"https://qywk/avatar.jpg", true}, // 这里先是默认的头像
		CreateTime: time.Now(),                                      // 使用当前时间替代零值
		UpdateTime: time.Now(),                                      // 使用当前时间替代零值
		Enable:     sql.NullString{constants.Activation, true},
	}

	_, err = MDB.Insert(&userinfo)
	if err != nil {
		return nil, errors.New("数据库异常")
	}

	uidK := keys.Create(pre.UserInfoId, uid)

	token, err := jwts.GenJwtToken(
		l.svcCtx.Config.Jwt.AccessSecret,
		l.svcCtx.Config.Jwt.AccessExpire,
		map[string]any{
			constants.UserId:  uid,
			constants.UserKey: uidK,
		},
	)

	userInfoJson, err := userinfo.ToString()

	err1, err2 :=
		RDB.Set(ctx, emailK, userInfoJson, time.Hour*3).Err(),
		RDB.Set(ctx, uidK, userInfoJson, time.Hour*3).Err()

	if err1 != nil || err2 != nil {
		fmt.Println("Redis Set Key Err", err1, err2)
	}

	return &user.RegisterResp{
		UserId: uid,
		Token:  token,
	}, nil
}
