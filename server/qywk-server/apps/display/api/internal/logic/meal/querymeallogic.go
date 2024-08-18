package meal

import (
	"context"
	"qywk-server/apps/display/rpc/client/meal"
	"qywk-server/pkg/constants"
	result "qywk-server/resultful"

	"qywk-server/apps/display/api/internal/svc"
	"qywk-server/apps/display/api/internal/types"

	"github.com/zeromicro/go-zero/core/logx"
)

type QueryMealLogic struct {
	logx.Logger
	ctx    context.Context
	svcCtx *svc.ServiceContext
}

// 获取膳食详细信息
func NewQueryMealLogic(ctx context.Context, svcCtx *svc.ServiceContext) *QueryMealLogic {
	return &QueryMealLogic{
		Logger: logx.WithContext(ctx),
		ctx:    ctx,
		svcCtx: svcCtx,
	}
}

func (l *QueryMealLogic) QueryMeal(req *types.QueryMealReq) (resp *result.Result, err error) {
	userId := l.ctx.Value(constants.UserId).(string)
	res, err := l.svcCtx.MealZrpcClient.QueryMeal(l.ctx, &meal.QueryMealReq{
		Page:    req.Page,
		Size:    req.Size,
		Subarea: req.Subarea,
		UserId:  userId,
	})

	if err != nil {
		return result.Err().SetData("err", err.Error()), nil
	}

	return result.Ok().SetData("data", res), nil
}
