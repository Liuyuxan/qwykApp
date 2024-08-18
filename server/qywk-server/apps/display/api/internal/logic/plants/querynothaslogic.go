package plants

import (
	"context"
	"qywk-server/apps/display/rpc/client/plants"
	"qywk-server/resultful"

	"qywk-server/apps/display/api/internal/svc"
	"qywk-server/apps/display/api/internal/types"

	"github.com/zeromicro/go-zero/core/logx"
)

type QueryNotHasLogic struct {
	logx.Logger
	ctx    context.Context
	svcCtx *svc.ServiceContext
}

// 查询未拥有的中药植物信息
func NewQueryNotHasLogic(ctx context.Context, svcCtx *svc.ServiceContext) *QueryNotHasLogic {
	return &QueryNotHasLogic{
		Logger: logx.WithContext(ctx),
		ctx:    ctx,
		svcCtx: svcCtx,
	}
}

func (l *QueryNotHasLogic) QueryNotHas(req *types.QueryPlantsReq) (resp *result.Result, err error) {
	res, err := l.svcCtx.PlantsZrpcClient.QueryNotHas(l.ctx, &plants.QueryPlantsReq{
		UserId: req.UserId,
		Page:   req.Page,
		Size:   req.Size,
	})

	if err != nil {
		return result.Err().SetData("err", err.Error()), nil
	}

	return result.Ok().SetData("data", res), nil
}
