package meal

import (
	"net/http"

	"github.com/zeromicro/go-zero/rest/httpx"
	"qywk-server/apps/display/api/internal/logic/meal"
	"qywk-server/apps/display/api/internal/svc"
)

// 获取膳食分区信息
func QuerySubareaHandler(svcCtx *svc.ServiceContext) http.HandlerFunc {
	return func(w http.ResponseWriter, r *http.Request) {
		l := meal.NewQuerySubareaLogic(r.Context(), svcCtx)
		resp, err := l.QuerySubarea()
		if err != nil {
			httpx.ErrorCtx(r.Context(), w, err)
		} else {
			httpx.OkJsonCtx(r.Context(), w, resp)
		}
	}
}
