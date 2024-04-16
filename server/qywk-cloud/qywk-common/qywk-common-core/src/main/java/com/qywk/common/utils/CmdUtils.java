package com.qywk.common.utils;

import lombok.SneakyThrows;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * @author zc
 * @date 2023/10/22
 * @description 执行系统cmd命令的工具类，本来想就放业务里，担万一以后其他地方能用到呢，就写个工具类封装一下吧
 */
public class CmdUtils {
    /**
     * 执行cmd的方法,有的命令响应很慢(如ping命令之类的网络请求命令)，建议慎用
     * @param command 要执行的命令
     * @param line 要读取流的行数,小于0代表不限制读取全部
     * @return 返回结果信息的字符串
     */
    @SneakyThrows
    public  String runCmd(String command,int line){
        //创建执行command命令的process对象
        Process process=Runtime.getRuntime().exec(command);
        //返回结果是流,接收process返回的结果流
        BufferedInputStream bufferedInputStream=new BufferedInputStream(process.getInputStream());
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(bufferedInputStream,Charset.forName("GBK")));
        //读取字符流中的信息
        String stringline;
        int i = 0;
        StringBuffer info=new StringBuffer();
        while((stringline=bufferedReader.readLine())!=null){
            //这里需要对流处理，只取所需的流
            if(i==line){
                info.append(stringline);
                break;
            }else if(line<0){
                info.append(stringline);
            }
            i++;
        }
        //关闭流
        bufferedReader.close();
        bufferedInputStream.close();
        //进行等待直到子进程结束，process进程会自动结束
        process.waitFor();
        //进程退出值，为0则正常，其他均为异常（但并不影响）
        process.exitValue();
        //返回执行命令的结果
        return info.toString();
    }
}
