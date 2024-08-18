package main

import (
	"flag"
	"fmt"

	"qywk-server/apps/display/rpc/display"
	"qywk-server/apps/display/rpc/internal/config"
	plantsServer "qywk-server/apps/display/rpc/internal/server/plants"
	"qywk-server/apps/display/rpc/internal/svc"

	"github.com/zeromicro/go-zero/core/conf"
	"github.com/zeromicro/go-zero/core/service"
	"github.com/zeromicro/go-zero/zrpc"
	"google.golang.org/grpc"
	"google.golang.org/grpc/reflection"
)

var configFile = flag.String("f", "etc/display.yaml", "the config file")

func main() {
	flag.Parse()

	var c config.Config
	conf.MustLoad(*configFile, &c)
	ctx := svc.NewServiceContext(c)

	s := zrpc.MustNewServer(c.RpcServerConf, func(grpcServer *grpc.Server) {
		display.RegisterPlantsServer(grpcServer, plantsServer.NewPlantsServer(ctx))

		if c.Mode == service.DevMode || c.Mode == service.TestMode {
			reflection.Register(grpcServer)
		}
	})
	defer s.Stop()

	fmt.Printf("Starting rpc server at %s...\n", c.ListenOn)
	s.Start()
}

// CGO_ENABLED=0 GOOS=linux GOARCH=amd64 go build -o qywk-display-rpc .
