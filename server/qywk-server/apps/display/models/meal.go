package models

import "time"

type Meal struct {
	ID         string    `xorm:"id"`
	Subarea    string    `xorm:"subarea"`
	SubareaURL string    `xorm:"subarea_url"`
	Subtitle   string    `xorm:"subtitle"`
	Name       string    `xorm:"name"`
	Photos     string    `xorm:"photos"`
	Desc       string    `xorm:"desc"`
	CreateTime time.Time `xorm:"create_time"`
	UpdateTime time.Time `xorm:"update_time"`
	Enable     string    `xorm:"enable"`
}

func (Meal) TableName() string {
	return "meal"
}
