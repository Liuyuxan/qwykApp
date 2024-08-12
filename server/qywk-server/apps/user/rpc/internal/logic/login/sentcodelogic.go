package loginlogic

import (
	"context"
	"errors"
	"fmt"
	"math/rand"
	"qywk-server/pkg/email"
	"qywk-server/pkg/redisutils/keys"
	"qywk-server/pkg/redisutils/pre"
	"time"

	"qywk-server/apps/user/rpc/internal/svc"
	"qywk-server/apps/user/rpc/user"

	"github.com/zeromicro/go-zero/core/logx"
)

type SentCodeLogic struct {
	ctx    context.Context
	svcCtx *svc.ServiceContext
	logx.Logger
}

func NewSentCodeLogic(ctx context.Context, svcCtx *svc.ServiceContext) *SentCodeLogic {
	return &SentCodeLogic{
		ctx:    ctx,
		svcCtx: svcCtx,
		Logger: logx.WithContext(ctx),
	}
}

func (l *SentCodeLogic) SentCode(in *user.CodeReq) (*user.CodeResp, error) {
	RMD := l.svcCtx.RDB
	SMS := l.svcCtx.Config.Sms
	ctx := l.ctx

	// 1. 验证手机号格式
	if !email.IsValidEmail(in.Email) {
		return nil, errors.New("invalid email format")
	}

	// 2. 检查是否在一分钟内已发送过验证码
	limitK := keys.Create(pre.LimitEmail, in.Email)
	exists, err := RMD.Exists(ctx, limitK).Result()
	if err != nil {
		return nil, errors.New("failed to check email code limit")
	}
	if exists > 0 {
		return nil, errors.New("please wait before requesting a new code")
	}

	// 3. 生成验证码
	code := generateVerificationCode()

	codeK := keys.Create(pre.VerifyEmail, in.Email)
	// 4. 存储验证码到缓存或数据库 (假设使用 Redis)
	err = RMD.Set(ctx, codeK, code, time.Minute*5).Err() // 验证码有效期为5分钟
	if err != nil {
		return nil, errors.New("failed to store verification code")
	}

	// 5. 设置一分钟的限制键
	err = RMD.Set(ctx, limitK, "sent", time.Minute).Err()
	if err != nil {
		return nil, errors.New("failed to set email code limit")
	}

	// 6. 发送验证码
	go email.SendCode(SMS, in.Email, code)

	// 7. 返回结果
	return &user.CodeResp{}, nil
}

// 生成验证码
func generateVerificationCode() string {
	rand.Seed(time.Now().UnixNano())
	return fmt.Sprintf("%06d", rand.Intn(1000000)) // 生成6位数字验证码
}
