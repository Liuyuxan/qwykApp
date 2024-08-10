package user

import (
	"context"

	"qywk-server/apps/user/api/internal/svc"
	"qywk-server/apps/user/api/internal/types"

	"github.com/zeromicro/go-zero/core/logx"
)

type SentCodeLogic struct {
	logx.Logger
	ctx    context.Context
	svcCtx *svc.ServiceContext
}

// 发送验证码
func NewSentCodeLogic(ctx context.Context, svcCtx *svc.ServiceContext) *SentCodeLogic {
	return &SentCodeLogic{
		Logger: logx.WithContext(ctx),
		ctx:    ctx,
		svcCtx: svcCtx,
	}
}

func (l *SentCodeLogic) SentCode(req *types.ChangeReq) (resp *types.Result, err error) {
	// todo: add your logic here and delete this line

	return
}
