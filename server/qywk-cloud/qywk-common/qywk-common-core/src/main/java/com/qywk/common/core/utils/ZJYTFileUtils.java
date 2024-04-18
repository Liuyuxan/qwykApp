//package com.qywk.common.utils;
//
//
//import com.qywk.common.customenum.ModulesNameEnum;
//import feign.FeignException;
//import lombok.Data;
//import org.apache.commons.fileupload.FileItem;
//import org.apache.commons.fileupload.disk.DiskFileItemFactory;
//import org.apache.commons.io.IOUtils;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.Resource;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Component;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.multipart.commons.CommonsMultipartFile;
//
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.nio.file.Files;
//
///**
// * @author ricetofu
// * @date 2023/10/3
// * @description 指尖移通文件工具类
// */
//@Component // 注册为指尖移通组件，方便直接注入使用
//public class ZJYTFileUtils {
//
//    @Autowired
//    private FileServerClient client;
//
//    /**
//     * 上传一个文件，在文件服务器内部，以 module_name + classification + file_name 来唯一定位一个文件。<br>
//     * 如果在上面描述的规则下，出现了相同的组合，则一次上传操作会默认它是一次文件更新操作，而不会产生文件已存在之类的报错提醒。<br>
//     * 除了上传的模块name为 ModulesNameEnum.PUBLIC.name 里的文件，其他模块名的文件均不可以通过暴露给前端的接口访问，需要自行封装下载接口。<br>
//     * @param file 多部分文件流对象
//     * @param file_name 文件名(需要包含后缀名)
//     * @param module_name 文件模块归属
//     * @param classification 文件的分类(自定义属性，方便文件服务器归类)
//     * */
//    public Result upload(
//            MultipartFile file,
//            String file_name,
//            ModulesNameEnum module_name,
//            String classification
//    ) {
//        // 返回实体类
//        Result result = new Result();
//
//        // 字段校验
//        {
//            if (file == null) {
//                result.setCode(ResultCodeEnum.PARAM_ERROR);
//                result.setSuccess(false);
//                result.setMessage("文件参数错误!上传的文件(流)不能为空!");
//                return result;
//            }
//            if (file_name == null || file_name.isEmpty()) {
//                result.setCode(ResultCodeEnum.PARAM_ERROR);
//                result.setSuccess(false);
//                result.setMessage("file_name参数错误!上传的文件名不能为空!");
//                return result;
//            }
//            if (!file_name.contains(".")) {
//                result.setCode(ResultCodeEnum.PARAM_ERROR);
//                result.setSuccess(false);
//                result.setMessage("file_name参数错误!上传的文件不具有后缀!");
//                return result;
//            }
//            if (module_name == null) {
//                result.setCode(ResultCodeEnum.PARAM_ERROR);
//                result.setSuccess(false);
//                result.setMessage("module_name参数错误!未指定上传的模块!");
//                return result;
//            }
//            if (classification == null) {
//                result.setCode(ResultCodeEnum.PARAM_ERROR);
//                result.setSuccess(false);
//                result.setMessage("classification参数错误!未指定上传的文件的分类!");
//                return result;
//            }
//        }
//
//        // 向远程服务器发起上传请求
//        FileServerClient.ResultInfo uploadInfo = client.upload(file, module_name.name + File.separator + classification, file_name);
//        if (uploadInfo.getSuccess()) {
//            result.setCode(ResultCodeEnum.SUCCESS);
//            result.setSuccess(true);
//            result.setMessage("文件上传成功!");
//            return result;
//        }
//
//        result.setSuccess(false);
//        result.setCode(ResultCodeEnum.FAILED);
//        result.setMessage("上错错误!");
//        return result;
//    }
//
//    /**
//     * 上传一个文件 (解释详见第一个方法的doc注释)
//     * @param in 输入流(会被转化为多部分流文件)
//     * */
//    public Result upload(
//            InputStream in,
//            String file_name,
//            ModulesNameEnum module_name,
//            String classification
//    ) {
//        return upload(inputStreamToMultipartFile(in,file_name), file_name, module_name, classification);
//    }
//
//    /**
//     * 上传一个文件 (解释详见第一个方法的doc注释)
//     * @param file 文件对象(会被转化为多部分流文件)
//     * */
//    public Result upload(
//            File file,
//            String file_name,
//            ModulesNameEnum module_name,
//            String classification
//    ) {
//        InputStream in = null;
//        try {
//            in = Files.newInputStream(file.toPath());
//        } catch (IOException e) {
//            Result result = new Result();
//            result.setSuccess(false);
//            result.setCode(ResultCodeEnum.FAILED);
//            result.setMessage("处理文件时出现了一个IO错误!");
//            return result;
//        }
//        return upload(inputStreamToMultipartFile(in,file_name), file_name, module_name, classification);
//    }
//
//    /**
//     * 判断在文件服务器中一个文件是否存在
//     * */
//    public boolean exist(
//            ModulesNameEnum module_name,
//            String file_path,
//            String file_name
//    ) {
//        FileServerClient.ResultInfo exist = client.exist(module_name.name + File.separator + file_path, file_name);
//        return exist.getSuccess() && (Boolean) exist.getData().get("exist");
//    }
//
//    /**
//     * 下载一个文件，如果有这个文件，那么会返回一个文件流，用这个流来接收这个文件
//     * @param module_name 模块名称
//     * @param file_name 文件名
//     * @param classification 文件的分类目录
//     * */
//    public Result download(
//            ModulesNameEnum module_name,
//            String classification,
//            String file_name
//    ) {
//
//        // 返回实体
//        Result result = new Result();
//
//        // 参数校验
//        {
//            if (file_name == null || file_name.isEmpty()) {
//                result.setCode(ResultCodeEnum.PARAM_ERROR);
//                result.setSuccess(false);
//                result.setMessage("file_name参数错误!上传的文件名不能为空!");
//                return result;
//            }
//            if (!file_name.contains(".")) {
//                result.setCode(ResultCodeEnum.PARAM_ERROR);
//                result.setSuccess(false);
//                result.setMessage("file_name参数错误!上传的文件不具有后缀!");
//                return result;
//            }
//            if (module_name == null) {
//                result.setCode(ResultCodeEnum.PARAM_ERROR);
//                result.setSuccess(false);
//                result.setMessage("module_name参数错误!未指定上传的模块!");
//                return result;
//            }
//            if (classification == null) {
//                result.setCode(ResultCodeEnum.PARAM_ERROR);
//                result.setSuccess(false);
//                result.setMessage("classification参数错误!未指定上传的文件的分类!");
//                return result;
//            }
//        }
//
//        // 远程请求下载
//        ResponseEntity<Resource> downloadInfo = null;
//
//        try {
//            downloadInfo = client.download(module_name.name + File.separator + classification, file_name);
//        } catch (FeignException.FeignClientException e) {
//            LoggerFactory.getLogger(getClass()).warn("feign远程调用出现了一个错误,疑似为访问了不存在的文件: " + e.getMessage());
//        }
//
//        try {
//            if (downloadInfo == null || downloadInfo.getBody() == null) {
//                // 请求体为空？文件不存在？
//                result.setCode(ResultCodeEnum.FAILED);
//                result.setSuccess(false);
//                result.setMessage("下载失败!文件是否存在?");
//            } else {
//                downloadInfo.getBody().getInputStream();
//                downloadInfo.getBody().getInputStream();
//                // 请求成功
//                result.setCode(ResultCodeEnum.SUCCESS);
//                result.setSuccess(true);
//                result.setMessage("下载成功!");
//                result.setInputStream(downloadInfo.getBody().getInputStream());
//            }
//        }catch (IOException e) {
//            // 出现异常
//            result.setCode(ResultCodeEnum.FAILED);
//            result.setSuccess(false);
//            result.setMessage("下载失败!出现了一个IO异常!");
//        }
//
//        return result;
//    }
//
//    /**
//     * 删除一个文件
//     * @param module_name 文件所在的模块目录
//     * @param file_name 文件名
//     * @param classification 文件所在的分类目录
//     * */
//    public Result delete(
//            ModulesNameEnum module_name,
//            String classification,
//            String file_name
//    ) {
//        // 返回实体
//        Result result = new Result();
//
//        // 参数校验
//        {
//            if (file_name == null || file_name.isEmpty()) {
//                result.setCode(ResultCodeEnum.PARAM_ERROR);
//                result.setSuccess(false);
//                result.setMessage("file_name参数错误!上传的文件名不能为空!");
//                return result;
//            }
//            if (!file_name.contains(".")) {
//                result.setCode(ResultCodeEnum.PARAM_ERROR);
//                result.setSuccess(false);
//                result.setMessage("file_name参数错误!上传的文件不具有后缀!");
//                return result;
//            }
//            if (module_name == null) {
//                result.setCode(ResultCodeEnum.PARAM_ERROR);
//                result.setSuccess(false);
//                result.setMessage("module_name参数错误!未指定上传的模块!");
//                return result;
//            }
//            if (classification == null) {
//                result.setCode(ResultCodeEnum.PARAM_ERROR);
//                result.setSuccess(false);
//                result.setMessage("classification参数错误!未指定上传的文件的分类!");
//                return result;
//            }
//        }
//
//        // 远程进行一次删除请求
//        FileServerClient.ResultInfo deleteInfo = client.delete(module_name + File.separator + classification, file_name);
//
//        if (deleteInfo.getSuccess()) {
//            // 如果请求成功
//            result.setCode(ResultCodeEnum.SUCCESS);
//            result.setSuccess(true);
//            result.setMessage("删除成功!");
//            return result;
//        }
//        // 请求失败
//        result.setCode(ResultCodeEnum.FAILED);
//        result.setSuccess(false);
//        result.setMessage("删除失败!文件不存在?");
//        return result;
//    }
//
//    /**
//     * 输入流转多部分文件的默认方法
//     * @param in 输入流
//     * @param file_name 文件名
//     * @return 多部分文件，如果出错了，会返回null
//     * */
//    public MultipartFile inputStreamToMultipartFile (InputStream in, String file_name){
//        // 构建fileItem对象
//        FileItem fileItem = new DiskFileItemFactory().createItem("file", MediaType.MULTIPART_FORM_DATA_VALUE, true, file_name);
//        try (OutputStream out = fileItem.getOutputStream()) {
//            // 流copy
//            IOUtils.copy(in, out);
//        }catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//        return new CommonsMultipartFile(fileItem);
//    }
//
//    @Data
//    public static class Result {
//
//        /**
//         * 返回代码
//         * */
//        private ResultCodeEnum code;
//
//        /**
//         * 请求是否成功
//         * */
//        private Boolean success;
//
//        /**
//         * 响应的信息
//         * */
//        private String message;
//
//        /**
//         * 响应携带的流数据(仅下载)
//         * */
//        private InputStream inputStream;
//
//    }
//
//    /**
//     * 返回枚举
//     * */
//    public enum ResultCodeEnum {
//
//        SUCCESS(0, "操作成功!"),
//        FAILED(1, "操作失败!"),
//        SERVER_CONNECTION_ERROR(2, "无法连接到文件服务器!"),
//        PARAM_ERROR(3, "参数错误!"),
//        ;
//
//        public final Integer code; // 代码
//        public final String message; // 描述信息
//
//        ResultCodeEnum(Integer code, String message) {
//            this.code = code;
//            this.message = message;
//        }
//    }
//
//}
