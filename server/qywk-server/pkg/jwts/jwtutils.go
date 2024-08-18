package jwts

import (
	"fmt"
	"github.com/dgrijalva/jwt-go"
	"github.com/zeromicro/go-zero/rest/httpx"
	"net/http"
	"qywk-server/pkg/constants"
	result "qywk-server/resultful"
	"time"
)

// GenJwtToken 生成 JWT 令牌
func GenJwtToken(secret string, expired int64, data map[string]any) (string, error) {
	// 创建声明
	claims := jwt.MapClaims{
		constants.Expire: time.Now().Add(time.Duration(expired) * time.Second).Unix(),
	}
	for key, value := range data {
		claims[key] = value
	}

	// 创建令牌
	token := jwt.NewWithClaims(jwt.SigningMethodHS256, claims)

	// 签名生成token
	return token.SignedString([]byte(secret))
}

// ParseJwtToken 解析和验证 JWT 令牌
func ParseJwtToken(tokenStr, secret string) (jwt.MapClaims, error) {
	// 解析令牌
	token, err := jwt.Parse(tokenStr, func(token *jwt.Token) (interface{}, error) {
		// 验证签名方法
		if _, ok := token.Method.(*jwt.SigningMethodHMAC); !ok {
			return nil, fmt.Errorf("unexpected signing method: %v", token.Header["alg"])
		}
		return []byte(secret), nil
	})
	if err != nil {
		return nil, err
	}

	// 验证令牌是否有效
	if claims, ok := token.Claims.(jwt.MapClaims); ok && token.Valid {
		return claims, nil
	} else {
		return nil, fmt.Errorf("invalid token")
	}
}

func JwtUnauthorizedResult(w http.ResponseWriter, r *http.Request, err error) {
	httpx.WriteJson(w, http.StatusUnauthorized, result.Err().SetCode(404).SetMsg("鉴权失败"))
}
