package logic

import (
	"context"
	"fmt"
	"io/ioutil"
	"path/filepath"

	"qywk-server/apps/file/rpc/file"
	"qywk-server/apps/file/rpc/internal/svc"

	"github.com/zeromicro/go-zero/core/logx"
)

type DownloadLogic struct {
	ctx    context.Context
	svcCtx *svc.ServiceContext
	logx.Logger
}

func NewDownloadLogic(ctx context.Context, svcCtx *svc.ServiceContext) *DownloadLogic {
	return &DownloadLogic{
		ctx:    ctx,
		svcCtx: svcCtx,
		Logger: logx.WithContext(ctx),
	}
}

// 下载文件
func (l *DownloadLogic) Download(in *file.DownLoadReq) (*file.DownLoadResp, error) {
	// 构建文件路径
	filePath := filepath.Join(l.svcCtx.FileRoot, in.GetFileId())

	// 读取文件内容
	fileContent, err := ioutil.ReadFile(filePath)
	if err != nil {
		return nil, fmt.Errorf("failed to read file: %w", err)
	}

	// 假设文件的 MIME 类型和文件名可以从路径或其他来源获得
	fileName := filepath.Base(filePath)
	mimeType := "application/octet-stream" // 这里可以根据实际情况确定 MIME 类型

	// 返回文件内容
	return &file.DownLoadResp{
		FileName:    fileName,
		FileContent: fileContent,
		MimeType:    mimeType,
		FileSize:    int64(len(fileContent)),
	}, nil
}
