package userinfo

import (
	"fmt"
	"net/http"

	"github.com/zeromicro/go-zero/rest/httpx"
	"qywk-server/apps/user/api/internal/logic/userinfo"
	"qywk-server/apps/user/api/internal/svc"
)

// 获取用户的详细信息
func GetUserInfoDetailHandler(svcCtx *svc.ServiceContext) http.HandlerFunc {
	return func(w http.ResponseWriter, r *http.Request) {
		l := userinfo.NewGetUserInfoDetailLogic(r.Context(), svcCtx)
		resp, err := l.GetUserInfoDetail()
		if err != nil {
			fmt.Print(err)
			httpx.ErrorCtx(r.Context(), w, err)
		} else {
			httpx.OkJsonCtx(r.Context(), w, resp)
		}
	}
}
