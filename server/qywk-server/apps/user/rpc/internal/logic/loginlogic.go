package logic

import (
	"context"
	"database/sql"
	"github.com/zeromicro/go-zero/core/logx"
	"log"
	"qywk-server/apps/user/models"
	"qywk-server/apps/user/rpc/internal/svc"
	"qywk-server/apps/user/rpc/user"
	"qywk-server/pkg/constants"
	"qywk-server/pkg/encrypt"
	"time"
)

type LoginLogic struct {
	ctx    context.Context
	svcCtx *svc.ServiceContext
	logx.Logger
}

func NewLoginLogic(ctx context.Context, svcCtx *svc.ServiceContext) *LoginLogic {
	return &LoginLogic{
		ctx:    ctx,
		svcCtx: svcCtx,
		Logger: logx.WithContext(ctx),
	}
}

// 普通登陆
func (l *LoginLogic) Login(in *user.LoginReq) (*user.LoginResp, error) {
	MDB := l.svcCtx.MDB

	md5pwd := encrypt.GetMD5String(in.Password)
	uid := encrypt.GenRandUserId()

	info := models.UserInfo{
		UserId:     uid,
		Password:   md5pwd,
		Tel:        sql.NullString{in.UserId, true},
		Nickname:   in.UserId,
		Avatar:     sql.NullString{"https://qywk/avatar.jpg", true}, // 这里先是默认的头像
		CreateTime: time.Now(),                                      // 使用当前时间替代零值
		UpdateTime: time.Now(),                                      // 使用当前时间替代零值
		Enable:     sql.NullString{constants.Activation, true},
	}
	_, err := MDB.Insert(&info)
	if err != nil {
		log.Panic("Insert failed: %v", err)
	}

	return &user.LoginResp{}, nil
}
