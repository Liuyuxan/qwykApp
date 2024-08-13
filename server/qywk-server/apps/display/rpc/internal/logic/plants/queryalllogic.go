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

type QueryAllLogic struct {
	ctx    context.Context
	svcCtx *svc.ServiceContext
	logx.Logger
}

func NewQueryAllLogic(ctx context.Context, svcCtx *svc.ServiceContext) *QueryAllLogic {
	return &QueryAllLogic{
		ctx:    ctx,
		svcCtx: svcCtx,
		Logger: logx.WithContext(ctx),
	}
}

func (l *QueryAllLogic) QueryAll(in *display.QueryPlantsReq) (*display.PlantsResp, error) {
	MDB := l.svcCtx.MDB
	RDB := l.svcCtx.RDB
	ctx := l.ctx

	limit := int(in.Size)
	offset := int((in.Page - 1) * in.Size)
	var res []models.Plants

	key := keys.Create(pre.DisplayPlantsAll, in.UserId, strconv.Itoa(limit), strconv.Itoa(offset))
	val := RDB.Get(ctx, key).String()

	var ret *display.PlantsResp

	err := jsonx.Unmarshal(val, ret)
	if err == nil {
		return ret, nil
	}
	logx.Infof("err:", err)

	// 查询所有
	err = MDB.Table(models.Plants{}.TableName()).
		Where("enable = ?", constants.Activation).
		Limit(limit, offset).
		Find(&res)
	if err != nil {
		logx.Infof("err:", err)
		return nil, errors.New("数据库异常")
	}

	// 查询总记录数
	count, err := MDB.Where("enable = ?", constants.Activation).Count(&models.Plants{})
	if err != nil {
		logx.Infof("err:", err)
		return nil, errors.New("数据库异常")
	}

	// 转换数据
	var records []*display.Plants
	for _, v := range res {
		get, err := MDB.Table(models.PlantUser{}.TableName()).Where("user_id=? and plant_id=?", in.UserId, v.Id).Get(&models.PlantUser{})
		if err != nil {
			logx.Infof("err:", err)
			return nil, errors.New("数据库异常")
		}
		var has string
		if get {
			has = constants.Activation
		} else {
			has = constants.Invalid
		}
		records = append(records, &display.Plants{
			Id:   v.Id,
			Name: v.Name,
			Url:  v.Url,
			Tag:  strings.Split(v.Tag, ";"),
			Desc: v.Desc,
			Has:  has,
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
		logx.Infof("err:", err)
	}

	return ret, nil
}
