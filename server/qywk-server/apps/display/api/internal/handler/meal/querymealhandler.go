package meal

import (
	"net/http"

	"github.com/zeromicro/go-zero/rest/httpx"
	"qywk-server/apps/display/api/internal/logic/meal"
	"qywk-server/apps/display/api/internal/svc"
	"qywk-server/apps/display/api/internal/types"
)

// 获取膳食详细信息
func QueryMealHandler(svcCtx *svc.ServiceContext) http.HandlerFunc {
	return func(w http.ResponseWriter, r *http.Request) {
		var req types.QueryMealReq
		if err := httpx.Parse(r, &req); err != nil {
			httpx.ErrorCtx(r.Context(), w, err)
			return
		}

		l := meal.NewQueryMealLogic(r.Context(), svcCtx)
		resp, err := l.QueryMeal(&req)
		if err != nil {
			httpx.ErrorCtx(r.Context(), w, err)
		} else {
			httpx.OkJsonCtx(r.Context(), w, resp)
		}
	}
}
