package email

import (
	"crypto/tls"
	"fmt"
	"net/smtp"
	"regexp"
)

type Pop3 struct {
	Id   string
	Auth string
	Host string
	Port string
}

// 发送验证码
func SendCode(smt Pop3, to string, vcode string) error {
	// 发件人邮箱地址和授权码
	from := smt.Id
	password := smt.Auth
	smtpHost := smt.Host
	smtpPort := smt.Port

	// 收件人
	toEmail := to

	// 构建邮件内容
	subject := "Subject: 来自轻养问康APP的验证码邮件\r\n"
	fromHeader := fmt.Sprintf("From: %s\r\n", from)
	toHeader := fmt.Sprintf("To: %s\r\n", toEmail)
	body := fmt.Sprintf(`
    <div style="font-family: Arial, sans-serif; max-width: 600px; margin: auto; padding: 20px; border: 1px solid #dddddd; border-radius: 10px; background-color: #f9f9f9;">
        <h1 style="color: #333333; text-align: center; border-bottom: 2px solid #4CAF50; padding-bottom: 10px;">来自轻养问康APP的验证码邮件</h1>
        <p style="font-size: 18px; color: #555555;">您好，</p>
        <p style="font-size: 18px; color: #555555;">请接收您的验证码：</p>
        <div style="text-align: center; margin: 20px 0;">
            <span style="font-size: 24px; color: #4CAF50; font-weight: bold; padding: 10px 20px; border: 2px dashed #4CAF50; border-radius: 5px; display: inline-block;">%s</span>
        </div>
        <p style="font-size: 18px; color: #555555;">5分钟后过期，请妥善保管您的验证码，避免泄露。</p>
        <p style="font-size: 16px; color: #777777; text-align: center;">感谢您使用我们的服务！</p>
    </div>`, vcode)
	message := fromHeader + toHeader + subject + "Content-Type: text/html; charset=UTF-8\r\n\r\n" + body

	// 设置 SMTP 服务器配置
	auth := smtp.PlainAuth("", from, password, smtpHost)

	// TLS 配置
	tlsconfig := &tls.Config{
		InsecureSkipVerify: true,
		ServerName:         smtpHost,
	}

	// 连接到 SMTP 服务器
	conn, err := tls.Dial("tcp", smtpHost+":"+smtpPort, tlsconfig)
	if err != nil {
		return err
	}
	defer conn.Close()

	client, err := smtp.NewClient(conn, smtpHost)
	if err != nil {
		return err
	}
	defer client.Quit()

	// 认证
	if err = client.Auth(auth); err != nil {
		return err
	}

	// 设置发件人和收件人
	if err = client.Mail(from); err != nil {
		return err
	}

	if err = client.Rcpt(toEmail); err != nil {
		return err
	}

	// 发送邮件内容
	writer, err := client.Data()
	if err != nil {
		return err
	}

	_, err = writer.Write([]byte(message))
	if err != nil {
		return err
	}

	err = writer.Close()
	if err != nil {
		return err
	}

	return nil
}

// 验证电子邮件地址的合法性
func IsValidEmail(email string) bool {
	// 电子邮件地址的正则表达式
	emailRegex := `^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$`
	re := regexp.MustCompile(emailRegex)
	return re.MatchString(email)
}
