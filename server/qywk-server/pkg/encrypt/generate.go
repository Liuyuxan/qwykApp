package encrypt

import (
	"crypto/rand"
	"math/big"
)

func GenRandUserId() string {
	// 设置用户 ID 的长度
	idLength := 10

	// 可用于生成随机数的字符集
	charset := "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"

	var result []byte

	// 生成随机数并构建用户 ID
	for i := 0; i < idLength; i++ {
		randomIndex, err := rand.Int(rand.Reader, big.NewInt(int64(len(charset))))
		if err != nil {
			panic(err) // 如果生成随机数失败，则抛出异常
		}
		result = append(result, charset[randomIndex.Int64()])
	}

	return string(result)
}
