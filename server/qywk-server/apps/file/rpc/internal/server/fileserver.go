// Code generated by goctl. DO NOT EDIT.
// Source: file.proto

package server

import (
	"context"

	"qywk-server/apps/file/rpc/file"
	"qywk-server/apps/file/rpc/internal/logic"
	"qywk-server/apps/file/rpc/internal/svc"
)

type FileServer struct {
	svcCtx *svc.ServiceContext
	file.UnimplementedFileServer
}

func NewFileServer(svcCtx *svc.ServiceContext) *FileServer {
	return &FileServer{
		svcCtx: svcCtx,
	}
}

// 上传文件
func (s *FileServer) Upload(ctx context.Context, in *file.UploadReq) (*file.UploadResp, error) {
	l := logic.NewUploadLogic(ctx, s.svcCtx)
	return l.Upload(in)
}

// 下载文件
func (s *FileServer) Download(ctx context.Context, in *file.DownLoadReq) (*file.DownLoadResp, error) {
	l := logic.NewDownloadLogic(ctx, s.svcCtx)
	return l.Download(in)
}