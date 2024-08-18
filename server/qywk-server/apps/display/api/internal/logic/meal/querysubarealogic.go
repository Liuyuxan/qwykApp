package meal

import (
	"context"
	"qywk-server/apps/display/rpc/client/meal"
	result "qywk-server/resultful"

	"github.com/zeromicro/go-zero/core/logx"
	"qywk-server/apps/display/api/internal/svc"
)

type QuerySubareaLogic struct {
	logx.Logger
	ctx    context.Context
	svcCtx *svc.ServiceContext
}

// 获取膳食分区信息
func NewQuerySubareaLogic(ctx context.Context, svcCtx *svc.ServiceContext) *QuerySubareaLogic {
	return &QuerySubareaLogic{
		Logger: logx.WithContext(ctx),
		ctx:    ctx,
		svcCtx: svcCtx,
	}
}

func (l *QuerySubareaLogic) QuerySubarea() (resp *result.Result, err error) {
	res, err := l.svcCtx.MealZrpcClient.QuerySubarea(l.ctx, &meal.Request{})

	if err != nil {
		return result.Err().SetData("err", err.Error()), nil
	}

	return result.Ok().SetData("data", res), nil
}
