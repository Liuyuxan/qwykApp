package plants

import (
	"net/http"

	"github.com/zeromicro/go-zero/rest/httpx"
	"qywk-server/apps/display/api/internal/logic/plants"
	"qywk-server/apps/display/api/internal/svc"
	"qywk-server/apps/display/api/internal/types"
)

// 查询已拥有的中药植物信息
func QueryHasHandler(svcCtx *svc.ServiceContext) http.HandlerFunc {
	return func(w http.ResponseWriter, r *http.Request) {
		var req types.QueryPlantsReq
		if err := httpx.Parse(r, &req); err != nil {
			httpx.ErrorCtx(r.Context(), w, err)
			return
		}

		l := plants.NewQueryHasLogic(r.Context(), svcCtx)
		resp, err := l.QueryHas(&req)
		if err != nil {
			httpx.ErrorCtx(r.Context(), w, err)
		} else {
			httpx.OkJsonCtx(r.Context(), w, resp)
		}
	}
}
