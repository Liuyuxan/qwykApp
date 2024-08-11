package user

import (
	"context"
	"qywk-server/apps/user/rpc/user"
	result "qywk-server/pkg/resultful"

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

func (l *SentCodeLogic) SentCode(req *types.CodeReq) (resp *result.Result, err error) {
	_, err = l.svcCtx.User.SentCode(l.ctx, &user.CodeReq{
		Email: req.Email,
	})

	if err != nil {
		return result.Err().SetData("err", err.Error()), nil
	}

	return result.Ok().SetMsg("短信发送成功"), nil
}
