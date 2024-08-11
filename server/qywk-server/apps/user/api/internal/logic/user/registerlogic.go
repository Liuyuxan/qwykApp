package user

import (
	"context"
	"qywk-server/apps/user/rpc/user"
	"qywk-server/pkg/resultful"

	"qywk-server/apps/user/api/internal/svc"
	"qywk-server/apps/user/api/internal/types"

	"github.com/zeromicro/go-zero/core/logx"
)

type RegisterLogic struct {
	logx.Logger
	ctx    context.Context
	svcCtx *svc.ServiceContext
}

// 手机号注册
func NewRegisterLogic(ctx context.Context, svcCtx *svc.ServiceContext) *RegisterLogic {
	return &RegisterLogic{
		Logger: logx.WithContext(ctx),
		ctx:    ctx,
		svcCtx: svcCtx,
	}
}

func (l *RegisterLogic) Register(req *types.RegisterReq) (resp *result.Result, err error) {
	res, err := l.svcCtx.User.Register(l.ctx, &user.RegisterReq{
		Password: req.Password,
		Code:     req.Code,
		Email:    req.Email,
		Nickname: req.Nickname,
	})

	if err != nil {
		return result.Err().SetData("err", err.Error()), nil
	}

	return result.Ok().SetData("info", res), nil
}
