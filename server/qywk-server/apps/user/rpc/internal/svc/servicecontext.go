package svc

import (
	"github.com/redis/go-redis/v9"
	"qywk-server/apps/user/models"
	"qywk-server/apps/user/rpc/internal/config"
	"xorm.io/xorm"
)

type ServiceContext struct {
	Config config.Config
	MDB    *xorm.Engine
	RDB    *redis.Client
}

func NewServiceContext(c config.Config) *ServiceContext {

	return &ServiceContext{
		Config: c,
		MDB:    models.InitDB(c.MDB.Datasource),
		RDB: models.InitRedis(&redis.Options{
			Addr:     c.RDB.Addr,
			Password: c.RDB.Password,
			DB:       c.RDB.DB,
		}),
	}
}
