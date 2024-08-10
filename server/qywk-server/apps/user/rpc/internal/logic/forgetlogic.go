package logic

import (
	"context"

	"qywk-server/apps/user/rpc/internal/svc"
	"qywk-server/apps/user/rpc/user"

	"github.com/zeromicro/go-zero/core/logx"
)

type ForgetLogic struct {
	ctx    context.Context
	svcCtx *svc.ServiceContext
	logx.Logger
}

func NewForgetLogic(ctx context.Context, svcCtx *svc.ServiceContext) *ForgetLogic {
	return &ForgetLogic{
		ctx:    ctx,
		svcCtx: svcCtx,
		Logger: logx.WithContext(ctx),
	}
}

// 忘记密码
func (l *ForgetLogic) Forget(in *user.ForgetRep) (*user.ForgetResp, error) {
	// todo: add your logic here and delete this line

	return &user.ForgetResp{}, nil
}
