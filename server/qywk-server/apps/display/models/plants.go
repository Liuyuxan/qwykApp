package models

import (
	"encoding/json"
	"time"
)

type Plants struct {
	Id         string    `xorm:"id"`
	Name       string    `xorm:"name"`
	Url        string    `xorm:"url"`
	Tag        string    `xorm:"tag"`
	Desc       string    `xorm:"desc"`
	CreateTime time.Time `xorm:"create_time"`
	UpdateTime time.Time `xorm:"update_time"`
	Enable     string    `xorm:"enable"`
}

func (Plants) TableName() string {
	return "plants"
}

// 序列化为 JSON 字符串
func (p *Plants) ToString() (string, error) {
	data, err := json.Marshal(p)
	if err != nil {
		return "", err
	}
	return string(data), nil
}

// 反序列化 JSON 字符串
func (p *Plants) FromString(jsonStr string) error {
	return json.Unmarshal([]byte(jsonStr), p)
}
