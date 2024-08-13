package svc

import (
	"github.com/zeromicro/go-zero/zrpc"
	"qywk-server/apps/user/api/internal/config"
	"qywk-server/apps/user/rpc/client/info"
	"qywk-server/apps/user/rpc/client/login"
)

type ServiceContext struct {
	Config config.Config
	login.Login
	info.Info
}

func NewServiceContext(c config.Config) *ServiceContext {
	return &ServiceContext{
		Config: c,
		Login:  login.NewLogin(zrpc.MustNewClient(c.UserRpc)),
		Info:   info.NewInfo(zrpc.MustNewClient(c.UserRpc)),
	}
}
