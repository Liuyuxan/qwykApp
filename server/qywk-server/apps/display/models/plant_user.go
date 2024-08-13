package models

import "encoding/json"

type PlantUser struct {
	UserID  string `xorm:"user_id"`
	PlantID string `xorm:"plant_id"`
}

func (PlantUser) TableName() string {
	return "plant_user"
}

// 序列化为 JSON 字符串
func (p *PlantUser) ToString() (string, error) {
	data, err := json.Marshal(p)
	if err != nil {
		return "", err
	}
	return string(data), nil
}

// 反序列化 JSON 字符串
func (p *PlantUser) FromString(jsonStr string) error {
	return json.Unmarshal([]byte(jsonStr), p)
}
