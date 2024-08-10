package models

import (
	"context"
	_ "github.com/go-sql-driver/mysql"
	"github.com/redis/go-redis/v9"
	"log"
	"xorm.io/xorm"
)

// 初始化数据库引擎
func InitDB(datasource string) *xorm.Engine {
	engine, err := xorm.NewEngine("mysql", datasource)
	if err != nil {
		log.Printf("Xorm New Engine Error: %v\n", err)
		return nil
	}
	return engine
}

// 初始化 Redis 客户端
func InitRedis(options *redis.Options) *redis.Client {
	client := redis.NewClient(options)

	// 测试 Redis 连接
	_, err := client.Ping(context.Background()).Result()
	if err != nil {
		log.Printf("Redis Ping Error: %v\n", err)
		return nil
	}

	return client
}
