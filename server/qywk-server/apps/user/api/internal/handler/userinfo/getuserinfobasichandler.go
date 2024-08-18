package userinfo

import (
	"net/http"

	"github.com/zeromicro/go-zero/rest/httpx"
	"qywk-server/apps/user/api/internal/logic/userinfo"
	"qywk-server/apps/user/api/internal/svc"
	"qywk-server/apps/user/api/internal/types"
)

// 获取用户的基础信息
func GetUserInfoBasicHandler(svcCtx *svc.ServiceContext) http.HandlerFunc {
	return func(w http.ResponseWriter, r *http.Request) {
		var req types.GetUserInfoBasicReq
		if err := httpx.Parse(r, &req); err != nil {
			httpx.ErrorCtx(r.Context(), w, err)
			return
		}

		l := userinfo.NewGetUserInfoBasicLogic(r.Context(), svcCtx)
		resp, err := l.GetUserInfoBasic(&req)
		if err != nil {
			httpx.ErrorCtx(r.Context(), w, err)
		} else {
			httpx.OkJsonCtx(r.Context(), w, resp)
		}
	}
}
