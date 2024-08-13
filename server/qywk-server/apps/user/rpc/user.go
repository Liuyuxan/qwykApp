package main

import (
	"flag"
	"fmt"

	"qywk-server/apps/user/rpc/internal/config"
	infoServer "qywk-server/apps/user/rpc/internal/server/info"
	loginServer "qywk-server/apps/user/rpc/internal/server/login"
	"qywk-server/apps/user/rpc/internal/svc"
	"qywk-server/apps/user/rpc/user"

	"github.com/zeromicro/go-zero/core/conf"
	"github.com/zeromicro/go-zero/core/service"
	"github.com/zeromicro/go-zero/zrpc"
	"google.golang.org/grpc"
	"google.golang.org/grpc/reflection"
)

var configFile = flag.String("f", "etc/user.yaml", "the config file")

func main() {
	flag.Parse()

	var c config.Config
	conf.MustLoad(*configFile, &c)
	ctx := svc.NewServiceContext(c)

	s := zrpc.MustNewServer(c.RpcServerConf, func(grpcServer *grpc.Server) {
		user.RegisterLoginServer(grpcServer, loginServer.NewLoginServer(ctx))
		user.RegisterInfoServer(grpcServer, infoServer.NewInfoServer(ctx))

		if c.Mode == service.DevMode || c.Mode == service.TestMode {
			reflection.Register(grpcServer)
		}
	})
	defer s.Stop()

	fmt.Printf("Starting rpc server at %s...\n", c.ListenOn)
	s.Start()
}
