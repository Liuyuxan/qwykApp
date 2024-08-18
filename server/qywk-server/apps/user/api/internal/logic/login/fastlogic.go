package login

import (
	"context"
	"qywk-server/apps/user/rpc/user"
	"qywk-server/resultful"

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

func (l *FastLogic) Fast(req *types.WechatLoginReq) (resp *result.Result, err error) {
	res, err := l.svcCtx.Login.WechatLogin(l.ctx, &user.WechatLoginReq{
		Code: req.Code,
	})

	if err != nil {
		return result.Err().SetData("err", err.Error()), nil
	}

	return result.Ok().SetData("token", res.Token), nil
}
