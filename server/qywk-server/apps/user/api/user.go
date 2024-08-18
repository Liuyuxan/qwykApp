package main

import (
	"flag"
	"fmt"
	"qywk-server/pkg/jwts"

	"qywk-server/apps/user/api/internal/config"
	"qywk-server/apps/user/api/internal/handler"
	"qywk-server/apps/user/api/internal/svc"

	"github.com/zeromicro/go-zero/core/conf"
	"github.com/zeromicro/go-zero/rest"
)

var configFile = flag.String("f", "etc/user.yaml", "the config file")

func main() {
	flag.Parse()

	var c config.Config
	conf.MustLoad(*configFile, &c)

	server := rest.MustNewServer(c.RestConf, rest.WithUnauthorizedCallback(jwts.JwtUnauthorizedResult))
	defer server.Stop()

	ctx := svc.NewServiceContext(c)
	handler.RegisterHandlers(server, ctx)

	fmt.Printf("Starting server at %s:%d...\n", c.Host, c.Port)
	server.Start()
}

// CGO_ENABLED=0 GOOS=linux GOARCH=amd64 go build -o qywk-user-api .
