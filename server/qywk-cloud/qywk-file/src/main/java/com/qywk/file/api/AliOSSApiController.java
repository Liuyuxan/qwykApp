package com.qywk.file.api;

import com.qywk.common.core.entity.ResultBody;
import com.qywk.file.utils.AliOSSUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;


/**
 * 阿里云 OSS 工具类
 */

@RestController
@RequestMapping("/api")
public class AliOSSApiController{

    @Autowired
    AliOSSUtils aliOSSUtils;

    /**
     * 实现上传图片到 OSS, todo 后续优化分包，文件是否合法
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    public ResultBody upload(@RequestBody MultipartFile file){
        String url = null;
        try {
            url = aliOSSUtils.upload(file);
        }catch (Exception e){
            return ResultBody.error().message("文件不合法或者文件损毁");
        }
        return ResultBody.ok().message("上传成功").data("url", url);
    }
}

