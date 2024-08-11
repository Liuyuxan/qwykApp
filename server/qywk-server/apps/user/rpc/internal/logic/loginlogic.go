package logic

import (
	"context"
	"errors"
	"fmt"
	"github.com/zeromicro/go-zero/core/logx"
	"qywk-server/apps/user/models"
	"qywk-server/apps/user/rpc/internal/svc"
	"qywk-server/apps/user/rpc/user"
	"qywk-server/pkg/constants"
	"qywk-server/pkg/encrypt"
	"qywk-server/pkg/jwts"
	"time"
)

type LoginLogic struct {
	ctx    context.Context
	svcCtx *svc.ServiceContext
	logx.Logger
}

func NewLoginLogic(ctx context.Context, svcCtx *svc.ServiceContext) *LoginLogic {
	return &LoginLogic{
		ctx:    ctx,
		svcCtx: svcCtx,
		Logger: logx.WithContext(ctx),
	}
}

// 普通登陆
func (l *LoginLogic) Login(in *user.LoginReq) (*user.LoginResp, error) {
	MDB := l.svcCtx.MDB
	RDB := l.svcCtx.RDB

	// 1. 验证用户输入
	if in.UserId == "" || in.Password == "" {
		return nil, errors.New("账户或密码为空")
	}

	// 2. 检查用户是否存在
	var userinfo models.UserInfo

	get, err := MDB.Where("user_id=?", in.UserId).Get(&userinfo)
	if err != nil {
		return nil, errors.New("数据库查询异常")
	}

	if !get {
		return nil, errors.New("用户不存在")
	}

	// 3. 验证密码
	if encrypt.GetMD5String(in.Password) != userinfo.Password {
		return nil, errors.New("用户或密码有误")
	}

	// 4. 生成身份验证令牌 (假设使用 JWT)
	uidK := constants.USERINFO_ID + in.UserId
	emailK := constants.USERINFO_EMAIL + in.UserId

	token, err := jwts.GenJwtToken(
		l.svcCtx.Config.Jwt.AccessSecret,
		l.svcCtx.Config.Jwt.AccessExpire,
		map[string]any{
			"user_id":  in.UserId,
			"user_key": uidK,
		},
	)

	userInfoJson, err := userinfo.ToString()

	err1, err2 :=
		RDB.Set(ctx, emailK, userInfoJson, time.Hour*3).Err(),
		RDB.Set(ctx, uidK, userInfoJson, time.Hour*3).Err()

	if err1 != nil || err2 != nil {
		fmt.Println("Redis Set Key Err", err1, err2)
	}

	// 5. 返回结果
	return &user.LoginResp{
		Token: token,
	}, nil
}
