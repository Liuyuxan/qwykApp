package result

import "net/http"

type Result struct {
	Code    int32                  `json:"code"`
	Success bool                   `json:"success"`
	Message string                 `json:"message"`
	Data    map[string]interface{} `json:"data"`
}

func NewResult() *Result {
	return &Result{
		Data: make(map[string]interface{}),
	}
}

func Ok() *Result {
	return &Result{
		Code:    http.StatusOK,
		Success: true,
		Message: "请求成功",
		Data:    make(map[string]interface{}),
	}
}

func Err() *Result {
	return &Result{
		Code:    http.StatusBadRequest,
		Success: false,
		Message: "请求失败",
		Data:    make(map[string]interface{}),
	}
}

func (r *Result) SetData(key string, value interface{}) *Result {
	if r.Data == nil {
		r.Data = make(map[string]interface{})
	}
	r.Data[key] = value
	return r
}

func (r *Result) SetMsg(msg string) *Result {
	r.Message = msg
	return r
}

func (r *Result) SetCode(code int32) *Result {
	r.Code = code
	return r
}
