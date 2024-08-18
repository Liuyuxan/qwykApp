package models

type PlantUser struct {
	UserID  string `xorm:"user_id"`
	PlantID string `xorm:"plant_id"`
}

func (PlantUser) TableName() string {
	return "plant_user"
}
