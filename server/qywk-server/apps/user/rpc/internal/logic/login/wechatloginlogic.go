package loginlogic

import (
	"context"
	"errors"
	"fmt"
	"qywk-server/apps/user/models"
	"qywk-server/pkg/constants"
	"qywk-server/pkg/jwts"
	"qywk-server/pkg/redisutils/keys"
	"qywk-server/pkg/redisutils/pre"
	"time"

	"qywk-server/apps/user/rpc/internal/svc"
	"qywk-server/apps/user/rpc/user"

	"github.com/zeromicro/go-zero/core/logx"
)

type WechatLoginLogic struct {
	ctx    context.Context
	svcCtx *svc.ServiceContext
	logx.Logger
}

func NewWechatLoginLogic(ctx context.Context, svcCtx *svc.ServiceContext) *WechatLoginLogic {
	return &WechatLoginLogic{
		ctx:    ctx,
		svcCtx: svcCtx,
		Logger: logx.WithContext(ctx),
	}
}

// 微信快速登陆
func (l *WechatLoginLogic) WechatLogin(in *user.WechatLoginReq) (*user.LoginResp, error) {
	MDB := l.svcCtx.MDB
	RDB := l.svcCtx.RDB
	ctx := l.ctx
	// todo wechat openid 得查看微信开发文档
	// 第一步：使用授权码换取访问令牌和OpenID
	//accessToken, openID, err := l.exchangeCodeForAccessToken(in.Code)
	//if err != nil {
	//	return nil, fmt.Errorf("使用授权码获取访问令牌失败: %v", err)
	//}

	// 第二步：使用访问令牌和OpenID获取微信用户信息
	//wechatUserInfo, err := l.getWechatUserInfo(accessToken, openID)
	//if err != nil {
	//	return nil, fmt.Errorf("获取微信用户信息失败: %v", err)
	//}

	// 第三步：检查用户是否已经存在于数据库中
	var userinfo models.UserInfo
	get, err := MDB.Where("open_id=?", "myOpenId").Get(&userinfo)
	if err != nil {
		return nil, err
	}
	if !get {
		return nil, errors.New("未绑定账号")
	}

	// 第四步：为用户生成JWT令牌或会话
	uidK := keys.Create(pre.UserInfoId, userinfo.UserId)

	token, err := jwts.GenJwtToken(
		l.svcCtx.Config.Jwt.AccessSecret,
		l.svcCtx.Config.Jwt.AccessExpire,
		map[string]any{
			constants.UserId:  userinfo.UserId,
			constants.UserKey: uidK,
		},
	)

	userInfoJson, err := userinfo.ToString()
	emailK := keys.Create(pre.UserInfoEmail, userinfo.Email.String)

	err1, err2 :=
		RDB.Set(ctx, emailK, userInfoJson, time.Hour*3).Err(),
		RDB.Set(ctx, uidK, userInfoJson, time.Hour*3).Err()

	if err1 != nil || err2 != nil {
		fmt.Println("Redis Set Key Err", err1, err2)
	}
	// 第五步：返回登录响应
	return &user.LoginResp{
		Token: token,
	}, nil
}
