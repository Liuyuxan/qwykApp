package logic

import (
	"context"
	"errors"
	"fmt"
	"google.golang.org/appengine/log"
	"math/rand"
	"net/http"
	"net/url"
	"qywk-server/pkg/constants"
	"regexp"
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
	// 1. 验证手机号格式
	if !isValidPhone(in.Tel) {
		return nil, errors.New("invalid phone number format")
	}

	// 2. 生成验证码
	code := generateVerificationCode()

	codeK := constants.VERIFY_CODE
	// 3. 存储验证码到缓存或数据库 (假设使用 Redis)
	err := RMD.Set(ctx, codeK, code, time.Minute*5).Err() // 验证码有效期为5分钟
	if err != nil {
		return nil, errors.New("failed to store verification code")
	}

	// 4. 发送验证码 (假设使用短信服务提供商)
	go smsSend(in.Tel, code)

	// 5. 记录发送日志
	log.Infof(l.ctx, "Verification code sent to phone: %s, code: %s", in.Tel, code)

	// 6. 返回结果
	return &user.CodeResp{
		Status: true,
	}, nil
}

// 营业厅发送短信业务
func smsSend(tel, code string) error {
	// 1. 校验手机号格式
	if !isValidPhone(tel) {
		return errors.New("invalid phone number format")
	}

	// 2. 构建短信内容
	message := fmt.Sprintf("Your verification code is: %s", code)

	// 3. 调用短信服务商接口发送短信
	err := sendSmsViaProvider(tel, message)
	if err != nil {
		log.Infof(ctx, "Failed to send SMS to %s: %v", tel, err)
		return errors.New("failed to send SMS")
	}

	// 4. 记录发送日志
	log.Infof(ctx, "SMS sent to %s with code: %s at %s", tel, code, time.Now().Format(time.RFC3339))

	return nil
}

func sendSmsViaProvider(tel, message string) error {
	resp, err := http.PostForm("营业厅提供的短信api", url.Values{
		"tel":     {tel},
		"message": {message},
	})
	if err != nil {
		return err
	}
	defer resp.Body.Close()

	if resp.StatusCode != http.StatusOK {
		return errors.New("SMS provider returned non-200 status")
	}

	return nil
}

// 验证手机号格式
func isValidPhone(phone string) bool {
	// 正则表达式验证手机号格式 (假设国内手机号格式)
	re := regexp.MustCompile(`^1[3-9]\d{9}$`)
	return re.MatchString(phone)
}

// 生成验证码
func generateVerificationCode() string {
	rand.Seed(time.Now().UnixNano())
	return fmt.Sprintf("%06d", rand.Intn(1000000)) // 生成6位数字验证码
}
