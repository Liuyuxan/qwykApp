// Code generated by goctl. DO NOT EDIT.
package handler

import (
	"net/http"

	user "qywk-server/apps/user/api/internal/handler/user"
	"qywk-server/apps/user/api/internal/svc"

	"github.com/zeromicro/go-zero/rest"
)

func RegisterHandlers(server *rest.Server, serverCtx *svc.ServiceContext) {
	server.AddRoutes(
		[]rest.Route{
			{
				// 修改密码
				Method:  http.MethodPost,
				Path:    "/change",
				Handler: user.ChangeHandler(serverCtx),
			},
			{
				// 微信快速登陆
				Method:  http.MethodPost,
				Path:    "/fast",
				Handler: user.FastHandler(serverCtx),
			},
			{
				// 忘记密码
				Method:  http.MethodPost,
				Path:    "/forget",
				Handler: user.ForgetHandler(serverCtx),
			},
			{
				// 普通登陆
				Method:  http.MethodPost,
				Path:    "/normal",
				Handler: user.LoginHandler(serverCtx),
			},
			{
				// 手机号注册
				Method:  http.MethodPost,
				Path:    "/register",
				Handler: user.RegisterHandler(serverCtx),
			},
			{
				// 发送验证码
				Method:  http.MethodPost,
				Path:    "/sent/code",
				Handler: user.SentCodeHandler(serverCtx),
			},
		},
		rest.WithPrefix("/user/login"),
	)
}
