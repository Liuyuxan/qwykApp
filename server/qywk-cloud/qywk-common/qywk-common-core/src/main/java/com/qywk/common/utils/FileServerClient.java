package com.qywk.common.utils;

import lombok.Data;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: cxq
 * @introduction: 远程调用的接口
 * @date: 2023-8-6
 */
@FeignClient(url = "${file-server-address:10.0.6.22:2049}", name = "fileServer") // 为文件服务器配置一个默认的url，你可以在模块配置中修改这个地址
public interface FileServerClient {

    // 返回结果体
    @Data
    class ResultInfo{
        // 是否成功
        private Boolean success;
        // 返回消息
        private String message;
        // 携带信息
        private Map<String, Object> data = new HashMap<>();
    }

    /**
     * 上传文件远程调用
     * */
    @PostMapping(value = "upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResultInfo upload(
            @RequestPart("file")MultipartFile file,
            @RequestParam String file_path,
            @RequestParam String file_name
    );

    /**
     * 下载文件远程调用
     * */
    @GetMapping("/download")
    ResponseEntity<Resource> download(
            @RequestParam String file_path,
            @RequestParam String file_name
    );

    /**
     * 删除文件远程调用
     * */
    @DeleteMapping("/delete")
    ResultInfo delete(
            @RequestParam String file_path,
            @RequestParam String file_name);

    /**
     * 判断一个文件是否存在远程调用
     * */
    @GetMapping("/exist")
    ResultInfo exist(@RequestParam String file_path,
                     @RequestParam String file_name);


}
