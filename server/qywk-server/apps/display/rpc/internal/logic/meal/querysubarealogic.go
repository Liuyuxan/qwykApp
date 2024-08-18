package meallogic

import (
	"context"
	"errors"
	"qywk-server/apps/display/models"
	"qywk-server/pkg/constants"
	"qywk-server/pkg/jsonx"
	"qywk-server/pkg/redisutils/keys"
	"qywk-server/pkg/redisutils/pre"
	"time"

	"qywk-server/apps/display/rpc/display"
	"qywk-server/apps/display/rpc/internal/svc"

	"github.com/zeromicro/go-zero/core/logx"
)

type QuerySubareaLogic struct {
	ctx    context.Context
	svcCtx *svc.ServiceContext
	logx.Logger
}

func NewQuerySubareaLogic(ctx context.Context, svcCtx *svc.ServiceContext) *QuerySubareaLogic {
	return &QuerySubareaLogic{
		ctx:    ctx,
		svcCtx: svcCtx,
		Logger: logx.WithContext(ctx),
	}
}

func (l *QuerySubareaLogic) QuerySubarea(in *display.Request) (*display.QuerySubareaResp, error) {
	MDB := l.svcCtx.MDB
	RDB := l.svcCtx.RDB
	ctx := l.ctx

	key := keys.Create(pre.DisplayMealSubarea)
	val := RDB.Get(ctx, key).String()

	var ret *display.QuerySubareaResp

	err := jsonx.Unmarshal(val, ret)
	if err == nil {
		return ret, nil
	}
	logx.Infof("err:%v", err)

	// 查询所有
	var res []models.Meal

	err = MDB.Table(models.Meal{}.TableName()).
		Where("enable = ?", constants.Activation).
		GroupBy("subarea").
		Find(&res)

	if err != nil {
		logx.Infof("err:%v", err)
		return nil, errors.New("数据库异常")
	}

	// 数据转换
	var subareas []*display.Subarea

	for _, v := range res {
		subareas = append(subareas, &display.Subarea{
			Name: v.Subarea,
			Url:  v.SubareaURL,
		})
	}

	ret = &display.QuerySubareaResp{
		Subareas: subareas,
	}

	json, err := jsonx.Marshal(ret)

	if err == nil {
		RDB.Set(ctx, key, json, time.Hour*3)
	} else {
		logx.Infof("err:%v", err)
	}

	return ret, nil

	return &display.QuerySubareaResp{}, nil
}
