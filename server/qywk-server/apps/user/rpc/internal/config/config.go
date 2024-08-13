package config

import (
	"github.com/zeromicro/go-zero/zrpc"
	"qywk-server/pkg/email"
)

type Config struct {
	zrpc.RpcServerConf

	Sms email.Pop3

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
