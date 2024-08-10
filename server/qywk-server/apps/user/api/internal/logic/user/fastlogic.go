package user

import (
	"context"

	"qywk-server/apps/user/api/internal/svc"
	"qywk-server/apps/user/api/internal/types"

	"github.com/zeromicro/go-zero/core/logx"
)

type FastLogic struct {
	logx.Logger
	ctx    context.Context
	svcCtx *svc.ServiceContext
}

// 微信快速登陆
func NewFastLogic(ctx context.Context, svcCtx *svc.ServiceContext) *FastLogic {
	return &FastLogic{
		Logger: logx.WithContext(ctx),
		ctx:    ctx,
		svcCtx: svcCtx,
	}
}

func (l *FastLogic) Fast(req *types.WechatLoginReq) (resp *types.Result, err error) {
	// todo: add your logic here and delete this line

	return
}
