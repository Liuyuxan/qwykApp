package svc

import (
	"github.com/zeromicro/go-zero/zrpc"
	"qywk-server/apps/display/api/internal/config"
	"qywk-server/apps/display/rpc/client/meal"
	"qywk-server/apps/display/rpc/client/plants"
)

type ServiceContext struct {
	Config config.Config
	plants.PlantsZrpcClient
	meal.MealZrpcClient
}

func NewServiceContext(c config.Config) *ServiceContext {
	return &ServiceContext{
		Config:           c,
		PlantsZrpcClient: plants.NewPlantsZrpcClient(zrpc.MustNewClient(c.DisplayRpc)),
		MealZrpcClient:   meal.NewMealZrpcClient(zrpc.MustNewClient(c.DisplayRpc)),
	}
}
