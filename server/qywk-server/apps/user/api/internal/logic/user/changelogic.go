package user

import (
	"context"
	"qywk-server/apps/user/rpc/user"
	result "qywk-server/pkg/resultful"

	"qywk-server/apps/user/api/internal/svc"
	"qywk-server/apps/user/api/internal/types"

	"github.com/zeromicro/go-zero/core/logx"
)

type ChangeLogic struct {
	logx.Logger
	ctx    context.Context
	svcCtx *svc.ServiceContext
}

// 修改密码
func NewChangeLogic(ctx context.Context, svcCtx *svc.ServiceContext) *ChangeLogic {
	return &ChangeLogic{
		Logger: logx.WithContext(ctx),
		ctx:    ctx,
		svcCtx: svcCtx,
	}
}

func (l *ChangeLogic) Change(req *types.ChangeReq) (resp *result.Result, err error) {
	res, err := l.svcCtx.User.Change(l.ctx, &user.ChangeReq{
		UserId:      req.UserId,
		Password:    req.Password,
		NewPassword: req.NewPassword,
		Email:       req.Email,
		Code:        req.Code,
	})

	if err != nil {
		return result.Err().SetData("err", err.Error()), nil
	}

	return result.Ok().SetData("status", res.Status), nil
}
