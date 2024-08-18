package main

import (
	"flag"
	"fmt"
	"qywk-server/apps/display/api/internal/config"
	"qywk-server/apps/display/api/internal/handler"
	"qywk-server/apps/display/api/internal/svc"
	"qywk-server/pkg/jwts"

	"github.com/zeromicro/go-zero/core/conf"
	"github.com/zeromicro/go-zero/rest"
)

var configFile = flag.String("f", "etc/display.yaml", "the config file")

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

// CGO_ENABLED=0 GOOS=linux GOARCH=amd64 go build -o qywk-display-api .
