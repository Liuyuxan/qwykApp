package logic

import (
	"context"
	"errors"
	"qywk-server/apps/user/models"
	"qywk-server/pkg/constants"
	"qywk-server/pkg/encrypt"

	"qywk-server/apps/user/rpc/internal/svc"
	"qywk-server/apps/user/rpc/user"

	"github.com/zeromicro/go-zero/core/logx"
)

type ChangeLogic struct {
	ctx    context.Context
	svcCtx *svc.ServiceContext
	logx.Logger
}

func NewChangeLogic(ctx context.Context, svcCtx *svc.ServiceContext) *ChangeLogic {
	return &ChangeLogic{
		ctx:    ctx,
		svcCtx: svcCtx,
		Logger: logx.WithContext(ctx),
	}
}

// 修改密码
func (l *ChangeLogic) Change(in *user.ChangeReq) (*user.ChangeResp, error) {
	MDB := l.svcCtx.MDB
	RDB := l.svcCtx.RDB

	// 1. 验证用户输入
	if in.UserId == "" && in.Email == "" {
		return &user.ChangeResp{
			Status: false,
		}, errors.New("用户ID或邮箱必须提供")
	}

	// 2. 检查用户存在性
	var userinfo models.UserInfo
	get, err := MDB.Where("user_id = ? && email = ?", in.UserId, in.Email).Get(&userinfo)
	if err != nil || !get {
		return &user.ChangeResp{
			Status: false,
		}, errors.New("用户或邮箱错误")
	}

	// 3. 判断密码格式是否正确
	if !encrypt.VerifyPwd(in.NewPassword) {
		return &user.ChangeResp{
			Status: false,
		}, errors.New("密码长度至少为 8 个字符，且包含至少一个数字和一个字母")
	}
	md5pwd := encrypt.GetMD5String(in.NewPassword)

	if encrypt.GetMD5String(in.Password) != userinfo.Password {
		return &user.ChangeResp{
			Status: false,
		}, errors.New("旧密码有误")
	}

	// 4. 验证码校验
	codeK := constants.VERIFY_EMAIL_CODE + in.Email
	code, _ := RDB.Get(ctx, codeK).Result()

	if code == "" || code != in.Code {
		return &user.ChangeResp{
			Status: false,
		}, errors.New("验证码无效")
	} else {
		RDB.Del(ctx, codeK)
	}

	_, err = MDB.Where("user_id=?", in.UserId).Update(map[string]interface{}{"password": md5pwd})
	if err != nil {
		return &user.ChangeResp{
			Status: false,
		}, errors.New("数据库异常")
	}

	return &user.ChangeResp{
		Status: true,
	}, nil
	return &user.ChangeResp{}, nil
}
