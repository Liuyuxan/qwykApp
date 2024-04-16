package com.qywk.common.utils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;

import com.qywk.common.entity.WeChatMessageConfig;
import com.qywk.common.entity.WeChatMessageUrlConfig;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zc
 * @date 2023/11/27
 * @description 微信消息推送工具类,里面涉及到到token如果一定要存缓存，为了避免循环依赖引用,该类就不能再这个工具模块下
 */
public class WeChatMessageUtils {

    //微信推送所需要的appid、url等配置
    private WeChatMessageUrlConfig urlConfig;
    //原生的http请求
    private RestTemplate restTemplate=new RestTemplate();
    //构造方法
    public WeChatMessageUtils(WeChatMessageUrlConfig urlConfig) {
        this.urlConfig = urlConfig;
    }
    //redis注入
//    @Autowired
//    private RedisService redisService;

    /**
     * 获取微信消息推送accessToken参数,需要用到的时候请在各自业务里面调用
     * @return
     */
    public synchronized String getAccessToken(){
        //首先从缓存获取accessToken
        String accessToken;
//        accessToken=redisService.getCacheObject("access_token");
//        //非空就直接返回
//        if(!StringUtils.isEmpty(accessToken)){
//            return accessToken;
//        }
        //参数封装
        Map<String,String> paramsMap=new HashMap<>();
        paramsMap.put("appid",urlConfig.getAppId());
        paramsMap.put("secret", urlConfig.getAppSecret());
        //请求api
        ResponseEntity<String> entity=restTemplate.getForEntity(urlConfig.getAccessToken_url(),String.class,paramsMap);
        //获取返回结果中的access_token
        JSONObject object=JSON.parseObject(entity.getBody());
        accessToken=object.getString("access_token");
        //获取返回结果access_token的有效时长,单位是秒
        long expires_in=object.getInteger("expires_in");
//        try{
//            //将accessToken存redis缓存
//            redisService.setCacheObject("access_token",accessToken,expires_in, TimeUnit.SECONDS);
//        }catch (Exception e){
//            //出异常直接返回空
//            return null;
//        }
        //没有异常返回accessToken
        return accessToken;
    }

    /**
     * 消息推送方法
     * @param accessToken 请求token
     * @param messageConfig 参数配置
     * @return
     */
    public synchronized JSONObject sendMessage(String accessToken, WeChatMessageConfig messageConfig){
        //创建map来存参数信息
        Map<String,Object> map=new HashMap<>();
        //参数和数据封装
        map.put("touser",messageConfig.getTouser());
        map.put("template_id",messageConfig.getTemplate_id());
        map.put("page",messageConfig.getPage());
        map.put("miniprogram_state",messageConfig.getMiniprogram_state());
        map.put("data",messageConfig.getData());
        //url拼接
        String url=urlConfig.getSendMessage_url()+accessToken;
        //定义请求头请求参数类型，这里用json所以是MediaType.APPLICATION_JSON
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        //定义请求的消息实体,HttpEntity有消息体和消息头组成,这里的消息实体好比requestBody或者responseBody
        HttpEntity<WeChatMessageConfig> httpEntity = new HttpEntity<>(messageConfig, headers);
        JSONObject object=restTemplate.postForObject(url,httpEntity,JSONObject.class);
        return object;
    }
}
