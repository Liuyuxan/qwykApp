package config

import (
	"github.com/zeromicro/go-zero/zrpc"
)

type Config struct {
	zrpc.RpcServerConf

	MDB struct {
		Datasource string
	}

	RDB struct {
		Addr     string
		Password string
		DB       int
	}

	Jwt struct {
		AccessSecret string
		AccessExpire int64
	}
}
