package userinfo

import (
	"context"
	"qywk-server/apps/user/rpc/user"
	"qywk-server/pkg/constants"
	result "qywk-server/resultful"

	"github.com/zeromicro/go-zero/core/logx"
	"qywk-server/apps/user/api/internal/svc"
)

type GetUserInfoDetailLogic struct {
	logx.Logger
	ctx    context.Context
	svcCtx *svc.ServiceContext
}

// 获取用户的详细信息
func NewGetUserInfoDetailLogic(ctx context.Context, svcCtx *svc.ServiceContext) *GetUserInfoDetailLogic {
	return &GetUserInfoDetailLogic{
		Logger: logx.WithContext(ctx),
		ctx:    ctx,
		svcCtx: svcCtx,
	}
}

func (l *GetUserInfoDetailLogic) GetUserInfoDetail() (resp *result.Result, err error) {
	userId := l.ctx.Value(constants.UserId).(string)
	res, err := l.svcCtx.Info.GetUserInfoDetail(l.ctx, &user.UserInfoDetailReq{
		UserId: userId,
	})

	if err != nil {
		return result.Err().SetData("err", err.Error()), nil
	}

	return result.Ok().SetData("info", res), nil
}
