package user

import (
	"context"
	"qywk-server/apps/user/rpc/user"
	result "qywk-server/pkg/resultful"

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

func (l *ForgetLogic) Forget(req *types.ForgetRep) (resp *result.Result, err error) {
	res, err := l.svcCtx.User.Forget(l.ctx, &user.ForgetReq{
		UserId:   req.UserId,
		Password: req.Password,
		Email:    req.Email,
		Code:     req.Code,
	})

	if err != nil {
		return result.Err().SetData("err", err.Error()), nil
	}

	return result.Ok().SetData("status", res.Status), nil
}
