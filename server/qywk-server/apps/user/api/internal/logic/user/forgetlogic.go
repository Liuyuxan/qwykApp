package user

import (
	"context"

	"qywk-server/apps/user/api/internal/svc"
	"qywk-server/apps/user/api/internal/types"

	"github.com/zeromicro/go-zero/core/logx"
)

type ForgetLogic struct {
	logx.Logger
	ctx    context.Context
	svcCtx *svc.ServiceContext
}

// 忘记密码
func NewForgetLogic(ctx context.Context, svcCtx *svc.ServiceContext) *ForgetLogic {
	return &ForgetLogic{
		Logger: logx.WithContext(ctx),
		ctx:    ctx,
		svcCtx: svcCtx,
	}
}

func (l *ForgetLogic) Forget(req *types.ForgetRep) (resp *types.Result, err error) {
	// todo: add your logic here and delete this line

	return
}
