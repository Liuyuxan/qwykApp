package plants

import (
	"context"
	"qywk-server/apps/display/rpc/client/plants"
	"qywk-server/resultful"

	"qywk-server/apps/display/api/internal/svc"
	"qywk-server/apps/display/api/internal/types"

	"github.com/zeromicro/go-zero/core/logx"
)

type QueryHasLogic struct {
	logx.Logger
	ctx    context.Context
	svcCtx *svc.ServiceContext
}

// 查询已拥有的中药植物信息
func NewQueryHasLogic(ctx context.Context, svcCtx *svc.ServiceContext) *QueryHasLogic {
	return &QueryHasLogic{
		Logger: logx.WithContext(ctx),
		ctx:    ctx,
		svcCtx: svcCtx,
	}
}

func (l *QueryHasLogic) QueryHas(req *types.QueryPlantsReq) (resp *result.Result, err error) {
	res, err := l.svcCtx.PlantsZrpcClient.QueryHas(l.ctx, &plants.QueryPlantsReq{
		UserId: req.UserId,
		Page:   req.Page,
		Size:   req.Size,
	})

	if err != nil {
		return result.Err().SetData("err", err.Error()), nil
	}

	return result.Ok().SetData("data", res), nil
}
