package logic

import (
	"context"

	"qywk-server/apps/user/rpc/internal/svc"
	"qywk-server/apps/user/rpc/user"

	"github.com/zeromicro/go-zero/core/logx"
)

type WechatLoginLogic struct {
	ctx    context.Context
	svcCtx *svc.ServiceContext
	logx.Logger
}

func NewWechatLoginLogic(ctx context.Context, svcCtx *svc.ServiceContext) *WechatLoginLogic {
	return &WechatLoginLogic{
		ctx:    ctx,
		svcCtx: svcCtx,
		Logger: logx.WithContext(ctx),
	}
}

// 微信快速登陆
func (l *WechatLoginLogic) WechatLogin(in *user.WechatLoginReq) (*user.LoginResp, error) {
	// todo: add your logic here and delete this line

	return &user.LoginResp{}, nil
}
