package keys

import "strings"

// CreateRedisKey 生成一个 Redis 键
func Create(prefix string, values ...string) string {
	if len(values) == 0 {
		return prefix
	}
	return prefix + strings.Join(values, ":")
}
