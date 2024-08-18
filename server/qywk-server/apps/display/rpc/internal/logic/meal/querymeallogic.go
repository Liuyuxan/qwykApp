package meallogic

import (
	"context"
	"errors"
	"qywk-server/apps/display/models"
	"qywk-server/pkg/constants"
	"qywk-server/pkg/jsonx"
	"qywk-server/pkg/redisutils/keys"
	"qywk-server/pkg/redisutils/pre"
	"strconv"
	"strings"
	"time"

	"qywk-server/apps/display/rpc/display"
	"qywk-server/apps/display/rpc/internal/svc"

	"github.com/zeromicro/go-zero/core/logx"
)

type QueryMealLogic struct {
	ctx    context.Context
	svcCtx *svc.ServiceContext
	logx.Logger
}

func NewQueryMealLogic(ctx context.Context, svcCtx *svc.ServiceContext) *QueryMealLogic {
	return &QueryMealLogic{
		ctx:    ctx,
		svcCtx: svcCtx,
		Logger: logx.WithContext(ctx),
	}
}

func (l *QueryMealLogic) QueryMeal(in *display.QueryMealReq) (*display.QueryMealResp, error) {
	MDB := l.svcCtx.MDB
	RDB := l.svcCtx.RDB
	ctx := l.ctx
	limit := int(in.Size)
	offset := int((in.Page - 1) * in.Size)

	key := keys.Create(pre.DisplayMealInfo, in.UserId, strconv.Itoa(limit), strconv.Itoa(offset))
	val := RDB.Get(ctx, key).String()
	var ret *display.QueryMealResp

	err := jsonx.Unmarshal(val, ret)
	if err == nil {
		return ret, nil
	}
	logx.Infof("err:%v", err)

	var res []models.Meal

	err = MDB.Table(models.Meal{}.TableName()).
		Where("subarea = ? and enable = ?", in.Subarea, constants.Activation).
		Limit(limit, offset).
		Find(&res)

	if err != nil {
		logx.Infof("err:%v", err)
		return nil, errors.New("数据库异常")
	}

	var meals []*display.Meal

	for _, v := range res {
		get, err := MDB.Table(models.MealUser{}.TableName()).Where("user_id=? and meal_id=?", in.UserId, v.ID).Get(&models.MealUser{})

		if err != nil {
			logx.Infof("err:", err)
			return nil, errors.New("数据库异常")
		}

		meals = append(meals, &display.Meal{
			Subarea:    v.Subarea,
			SubareaUrl: v.SubareaURL,
			Name:       v.Name,
			Photos:     strings.Split(v.Photos, ";"),
			Desc:       v.Desc,
			Collect:    get,
			Subtitle:   strings.Split(v.Subtitle, ";"),
		})
	}

	ret = &display.QueryMealResp{
		Meals: meals,
	}

	json, err := jsonx.Marshal(ret)

	if err == nil {
		RDB.Set(ctx, key, json, time.Hour*3)
	} else {
		logx.Infof("err:%v", err)
	}

	return ret, nil
}
