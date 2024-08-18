package models

type MealUser struct {
	UserID string `xorm:"user_id"`
	MealID string `xorm:"meal_id"`
}

func (MealUser) TableName() string {
	return "meal_user"
}
