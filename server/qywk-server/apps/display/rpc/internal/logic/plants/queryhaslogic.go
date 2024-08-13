package plantslogic

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

type QueryHasLogic struct {
	ctx    context.Context
	svcCtx *svc.ServiceContext
	logx.Logger
}

func NewQueryHasLogic(ctx context.Context, svcCtx *svc.ServiceContext) *QueryHasLogic {
	return &QueryHasLogic{
		ctx:    ctx,
		svcCtx: svcCtx,
		Logger: logx.WithContext(ctx),
	}
}

func (l *QueryHasLogic) QueryHas(in *display.QueryPlantsReq) (*display.PlantsResp, error) {
	MDB := l.svcCtx.MDB
	RDB := l.svcCtx.RDB
	ctx := l.ctx
	limit := int(in.Size)
	offset := int((in.Page - 1) * in.Size)
	var res []models.Plants

	key := keys.Create(pre.DisplayPlantsHas, in.UserId, strconv.Itoa(limit), strconv.Itoa(offset))
	val := RDB.Get(ctx, key).String()

	var ret *display.PlantsResp

	err := jsonx.Unmarshal(val, ret)
	if err == nil {
		return ret, nil
	}
	logx.Infof("err:%v", err)

	// 执行查询
	err = MDB.Table("plants").
		Join("INNER", "plant_user", "plant_user.plant_id = plants.id and plant_user.user_id = ?", in.UserId).
		Where("plants.enable = ?", constants.Activation).
		Limit(limit, offset).
		Find(&res)

	if err != nil {
		logx.Infof("err:", err)
		return nil, errors.New("数据库异常")
	}

	// 查询总记录数
	count, err := MDB.Table("plants").
		Join("INNER", "plant_user", "plant_user.plant_id = plants.id and plant_user.user_id = ?", in.UserId).
		Where("plants.enable = ?", constants.Activation).
		Count(&models.Plants{})

	if err != nil {
		logx.Infof("err:%v", err)
		return nil, errors.New("数据库异常")
	}

	// 转换数据
	var records []*display.Plants
	for _, v := range res {
		records = append(records, &display.Plants{
			Id:   v.Id,
			Name: v.Name,
			Url:  v.Url,
			Tag:  strings.Split(v.Tag, ";"),
			Desc: v.Desc,
			Has:  constants.Activation,
		})
	}

	ret = &display.PlantsResp{
		Total:   int32(count),
		Records: records,
	}

	json, err := jsonx.Marshal(ret)

	if err == nil {
		RDB.Set(ctx, key, json, time.Hour*3)
	} else {
		logx.Infof("err:%v", err)
	}

	return ret, nil
}
