package userinfo

import (
	"context"
	"qywk-server/apps/user/rpc/user"
	result "qywk-server/resultful"

	"qywk-server/apps/user/api/internal/svc"
	"qywk-server/apps/user/api/internal/types"

	"github.com/zeromicro/go-zero/core/logx"
)

type GetUserInfoBasicLogic struct {
	logx.Logger
	ctx    context.Context
	svcCtx *svc.ServiceContext
}

// 获取用户的基础信息
func NewGetUserInfoBasicLogic(ctx context.Context, svcCtx *svc.ServiceContext) *GetUserInfoBasicLogic {
	return &GetUserInfoBasicLogic{
		Logger: logx.WithContext(ctx),
		ctx:    ctx,
		svcCtx: svcCtx,
	}
}

func (l *GetUserInfoBasicLogic) GetUserInfoBasic(req *types.GetUserInfoBasicReq) (resp *result.Result, err error) {
	res, err := l.svcCtx.Info.GetUserInfoBasic(l.ctx, &user.UserInfoBasicReq{
		UserId: req.UserId,
	})

	if err != nil {
		return result.Err().SetData("err", err.Error()), nil
	}

	return result.Ok().SetData("info", res), nil
}
