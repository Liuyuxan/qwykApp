package com.qywk.file.api;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.qywk.file.config.properties.AliOSSProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.UUID;

/**
 * 阿里云 OSS 工具类
 */

@RestController
public class AliOSSApiController<T> {

    @Autowired
    AliOSSProperties aliOSSProperties;

    /**
     * 实现上传图片到 OSS
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    public String upload(T file) throws IOException{

        // 阿里云配置
        String endpoint = aliOSSProperties.getEndpoint();
        String accessKeyId = aliOSSProperties.getAccessKeyId();
        String accessKeySecret = aliOSSProperties.getAccessKeySecret();
        String bucketName = aliOSSProperties.getBucketName();

        /* 获取上传文件输入流
         避免文件覆盖
         获取原始文件名
         */
        InputStream inputStream = null;
        String originalFilename = null;
        if(file instanceof MultipartFile){
            inputStream = ((MultipartFile) file).getInputStream();
            originalFilename = ((MultipartFile) file).getOriginalFilename();
        }
        else if(file instanceof File){
            inputStream = new FileInputStream((File) file);
            originalFilename = ((File) file).getName();
        }

        String fileName = UUID.randomUUID().toString().concat(Objects.requireNonNull(originalFilename).substring(originalFilename.lastIndexOf(".")));

        // 上传文件到 OSS
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        ossClient.putObject(bucketName, fileName, inputStream);

        // 文件访问路径
        String url = endpoint.split("//")[0] + "//" + bucketName + "." + endpoint.split("//")[1] + "/" + fileName;

        // 关闭 ossClient
        ossClient.shutdown();

        // 上传 oss 的路径返回
        return url;
    }
}

