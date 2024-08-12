package svc

import "qywk-server/apps/file/rpc/internal/config"

type ServiceContext struct {
	Config   config.Config
	FileRoot string
}

func NewServiceContext(c config.Config) *ServiceContext {
	return &ServiceContext{
		Config:   c,
		FileRoot: c.FileRoot,
	}
}
