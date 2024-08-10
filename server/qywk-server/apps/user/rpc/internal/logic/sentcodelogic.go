package logic

import (
	"context"

	"qywk-server/apps/user/rpc/internal/svc"
	"qywk-server/apps/user/rpc/user"

	"github.com/zeromicro/go-zero/core/logx"
)

type SentCodeLogic struct {
	ctx    context.Context
	svcCtx *svc.ServiceContext
	logx.Logger
}

func NewSentCodeLogic(ctx context.Context, svcCtx *svc.ServiceContext) *SentCodeLogic {
	return &SentCodeLogic{
		ctx:    ctx,
		svcCtx: svcCtx,
		Logger: logx.WithContext(ctx),
	}
}

// 发送验证码
func (l *SentCodeLogic) SentCode(in *user.CodeReq) (*user.CodeResp, error) {
	// todo: add your logic here and delete this line

	return &user.CodeResp{}, nil
}
