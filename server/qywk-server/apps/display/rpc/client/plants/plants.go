// Code generated by goctl. DO NOT EDIT.
// Source: display.proto

package plants

import (
	"context"

	"qywk-server/apps/display/rpc/display"

	"github.com/zeromicro/go-zero/zrpc"
	"google.golang.org/grpc"
)

type (
	Plants         = display.Plants
	PlantsResp     = display.PlantsResp
	QueryPlantsReq = display.QueryPlantsReq

	PlantsZrpcClient interface {
		QueryAll(ctx context.Context, in *QueryPlantsReq, opts ...grpc.CallOption) (*PlantsResp, error)
		QueryHas(ctx context.Context, in *QueryPlantsReq, opts ...grpc.CallOption) (*PlantsResp, error)
		QueryNotHas(ctx context.Context, in *QueryPlantsReq, opts ...grpc.CallOption) (*PlantsResp, error)
	}

	defaultPlantsZrpcClient struct {
		cli zrpc.Client
	}
)

func NewPlantsZrpcClient(cli zrpc.Client) PlantsZrpcClient {
	return &defaultPlantsZrpcClient{
		cli: cli,
	}
}

func (m *defaultPlantsZrpcClient) QueryAll(ctx context.Context, in *QueryPlantsReq, opts ...grpc.CallOption) (*PlantsResp, error) {
	client := display.NewPlantsClient(m.cli.Conn())
	return client.QueryAll(ctx, in, opts...)
}

func (m *defaultPlantsZrpcClient) QueryHas(ctx context.Context, in *QueryPlantsReq, opts ...grpc.CallOption) (*PlantsResp, error) {
	client := display.NewPlantsClient(m.cli.Conn())
	return client.QueryHas(ctx, in, opts...)
}

func (m *defaultPlantsZrpcClient) QueryNotHas(ctx context.Context, in *QueryPlantsReq, opts ...grpc.CallOption) (*PlantsResp, error) {
	client := display.NewPlantsClient(m.cli.Conn())
	return client.QueryNotHas(ctx, in, opts...)
}