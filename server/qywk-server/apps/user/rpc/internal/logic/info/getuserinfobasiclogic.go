package infologic

import (
	"context"
	"github.com/pkg/errors"
	"github.com/redis/go-redis/v9"
	"qywk-server/apps/user/models"
	"qywk-server/pkg/redisutils/keys"
	"qywk-server/pkg/redisutils/pre"
	"time"

	"qywk-server/apps/user/rpc/internal/svc"
	"qywk-server/apps/user/rpc/user"

	"github.com/zeromicro/go-zero/core/logx"
)

type GetUserInfoBasicLogic struct {
	ctx    context.Context
	svcCtx *svc.ServiceContext
	logx.Logger
}

func NewGetUserInfoBasicLogic(ctx context.Context, svcCtx *svc.ServiceContext) *GetUserInfoBasicLogic {
	return &GetUserInfoBasicLogic{
		ctx:    ctx,
		svcCtx: svcCtx,
		Logger: logx.WithContext(ctx),
	}
}

// 查询用户的基本信息
func (l *GetUserInfoBasicLogic) GetUserInfoBasic(in *user.UserInfoBasicReq) (*user.UserInfoBasicResp, error) {
	MDB := l.svcCtx.MDB
	RDB := l.svcCtx.RDB
	ctx := l.ctx

	uid := in.UserId

	var userinfo models.UserInfo
	uidK := keys.Create(pre.UserInfoId, uid)

	// 从 Redis 中获取用户信息
	res, err := RDB.Get(ctx, uidK).Result()
	if err != nil && err != redis.Nil {
		logx.Infof("Error getting data from Redis: %v", err)
		return nil, errors.New("缓存查询异常")
	}

	if res != "" {
		// 从缓存中反序列化 JSON 数据
		err := userinfo.FromString(res)
		if err != nil {
			logx.Infof("Error deserializing JSON from Redis: %v", err)
			return nil, errors.New("缓存数据解析异常")
		}
	} else {
		// 从数据库中获取用户信息
		get, err := MDB.Where("user_id=? and enable=1", uid).Get(&userinfo)
		if err != nil {
			logx.Infof("Error querying database: %v", err)
			return nil, errors.New("数据库查询异常")
		}

		if !get {
			errMsg := "账户不存在，或者账户状态异常"
			logx.Infof(errMsg)
			return nil, errors.New(errMsg)
		}

		// 将用户信息序列化为 JSON
		str, err := userinfo.ToString()
		if err != nil {
			logx.Infof("Error serializing userinfo to JSON: %v", err)
			return nil, errors.New("用户信息序列化异常")
		}

		// 将用户信息存入 Redis 缓存
		err = RDB.Set(ctx, uidK, str, time.Hour*3).Err()
		if err != nil {
			logx.Infof("Error setting data to Redis: %v", err)
			return nil, errors.New("缓存存储异常")
		}
	}

	return &user.UserInfoBasicResp{
		UserId:   userinfo.UserId,
		Nickname: userinfo.Nickname,
		Avatar:   userinfo.Avatar.String,
	}, nil
}
