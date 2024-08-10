package svc

import (
	"github.com/zeromicro/go-zero/zrpc"
	"qywk-server/apps/user/api/internal/config"
	"qywk-server/apps/user/rpc/userclient"
)

type ServiceContext struct {
	Config config.Config

	userclient.User
}

func NewServiceContext(c config.Config) *ServiceContext {
	return &ServiceContext{
		Config: c,
		User:   userclient.NewUser(zrpc.MustNewClient(c.UserRpc)),
	}
}
