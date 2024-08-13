package login

import (
	"net/http"

	"github.com/zeromicro/go-zero/rest/httpx"
	"qywk-server/apps/user/api/internal/logic/login"
	"qywk-server/apps/user/api/internal/svc"
	"qywk-server/apps/user/api/internal/types"
)

// 手机号注册
func RegisterHandler(svcCtx *svc.ServiceContext) http.HandlerFunc {
	return func(w http.ResponseWriter, r *http.Request) {
		var req types.RegisterReq
		if err := httpx.Parse(r, &req); err != nil {
			httpx.ErrorCtx(r.Context(), w, err)
			return
		}

		l := login.NewRegisterLogic(r.Context(), svcCtx)
		resp, err := l.Register(&req)
		if err != nil {
			httpx.ErrorCtx(r.Context(), w, err)
		} else {
			httpx.OkJsonCtx(r.Context(), w, resp)
		}
	}
}
