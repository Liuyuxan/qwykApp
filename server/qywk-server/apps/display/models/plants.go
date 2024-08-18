package models

import (
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
