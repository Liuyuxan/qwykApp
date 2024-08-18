// Code generated by goctl. DO NOT EDIT.
package types

type ChangeReq struct {
	UserId      string `json:"userId"`
	Password    string `json:"password"`
	Email       string `json:"email"`
	Code        string `json:"code"`
	NewPassword string `json:"newPassword"`
}

type CodeReq struct {
	Email string `json:"email"`
}

type ForgetRep struct {
	UserId   string `json:"userId"`
	Password string `json:"password"`
	Email    string `json:"email"`
	Code     string `json:"code"`
}

type LoginReq struct {
	UserId   string `json:"userId"`
	Password string `json:"password"`
}

type RegisterReq struct {
	Password string `json:"password"`
	Code     string `json:"code"`
	Email    string `json:"email"`
	Nickname string `json:"nickname"`
}

type Result struct {
}

type WechatLoginReq struct {
	Code string `json:"code"`
}

type GetUserInfoBasicReq struct {
	UserId string `form:"userId"`
}
