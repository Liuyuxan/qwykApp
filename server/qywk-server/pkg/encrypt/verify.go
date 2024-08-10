package encrypt

import "unicode"

// 密码长度至少为 8 个字符，且包含至少一个数字和一个字母
func VerifyPwd(pwd string) bool {
	if len(pwd) < 8 {
		return false
	}

	hasLetter := false
	hasDigit := false

	for _, ch := range pwd {
		if unicode.IsLetter(ch) {
			hasLetter = true
		}
		if unicode.IsDigit(ch) {
			hasDigit = true
		}
	}

	return hasLetter && hasDigit
}
