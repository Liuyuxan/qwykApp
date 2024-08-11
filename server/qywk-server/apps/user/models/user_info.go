package models

import (
	"database/sql"
	"encoding/json"
	"time"
)

type UserInfo struct {
	UserId     string         `xorm:"'user_id'"`     // 用户id
	Password   string         `xorm:"'password'"`    // md5加密密码
	Tel        sql.NullString `xorm:"'tel'"`         // 手机号
	Email      sql.NullString `xorm:"'e_mail'"`      // 邮箱
	OpenId     sql.NullString `xorm:"'open_id'"`     // 微信校验码
	SessionId  sql.NullString `xorm:"'session_id'"`  // 微信会话码
	Nickname   string         `xorm:"'nickname'"`    // 用户名
	Avatar     sql.NullString `xorm:"'avatar'"`      // 头像url地址
	CreateTime time.Time      `xorm:"'create_time'"` // 创建时间
	UpdateTime time.Time      `xorm:"'update_time'"` // 更新时间
	Enable     sql.NullString `xorm:"'enable'"`      // 用户状态码
}

func (UserInfo) TableName() string {
	return "user_info"
}

// MarshalBinary 实现 encoding.BinaryMarshaler
func (u *UserInfo) MarshalBinary() ([]byte, error) {
	return json.Marshal(u)
}

// UnmarshalBinary 实现 encoding.BinaryUnmarshaler
func (u *UserInfo) UnmarshalBinary(data []byte) error {
	return json.Unmarshal(data, u)
}

// 序列化为 JSON 字符串
func (u *UserInfo) ToString() (string, error) {
	data, err := json.Marshal(u)
	if err != nil {
		return "", err
	}
	return string(data), nil
}
