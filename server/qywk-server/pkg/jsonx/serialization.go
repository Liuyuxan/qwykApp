package jsonx

import (
	"encoding/json"
)

// Marshal 将数据结构序列化为 JSON 字符串
func Marshal(data interface{}) (string, error) {
	bytes, err := json.Marshal(data)
	if err != nil {
		return "", err
	}
	return string(bytes), nil
}

// Deserialize 将 JSON 字符串反序列化为指定类型的对象
func Unmarshal(jsonStr string, result interface{}) error {
	return json.Unmarshal([]byte(jsonStr), result)
}
