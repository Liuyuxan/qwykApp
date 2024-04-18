package com.qywk.common.core.entity;

import cn.hutool.core.bean.BeanUtil;
import com.qywk.common.core.customenum.CodeStateEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * @author qlh
 * @date 2024/02/29 22:25
 * @description 公共统一返回类型
 */

@Data
@NoArgsConstructor
public class ResultBody {
    // 返回码
    private Integer code;

    // 是否成功
    private Boolean success;

    // 返回信息
    private String message;

    // 返回数据
    private Map<String, Object> data = new HashMap<>();

    // 附带说明
    private Map<String, String> expound = new HashMap<>();

    /**
     * 请求成功
     *
     * @return ResultBody
     */
    public static ResultBody ok() {
        ResultBody ret = new ResultBody();
        ret.setSuccess(true);
        ret.setCode(CodeStateEnum.SUCCESS.code);
        ret.setMessage(CodeStateEnum.SUCCESS.message);
        return ret;
    }

    /**
     * 请求失败
     *
     * @return ResultBody
     */
    public static ResultBody error() {
        ResultBody r = new ResultBody();
        r.setSuccess(false);
        r.setCode(CodeStateEnum.ERROR.code);
        r.setMessage(CodeStateEnum.ERROR.message);
        return r;
    }

    /**
     * 请求失败
     *
     * @return ResultBody
     */
    public static ResultBody error(CodeStateEnum code) {
        ResultBody r = new ResultBody();
        r.setSuccess(false);
        r.setCode(code.code);
        r.setMessage(code.message);
        return r;
    }

    /**
     * 设置返回信息
     *
     * @param message
     * @return
     */
    public ResultBody message(String message) {
        this.setMessage(message);
        return this;
    }

    /**
     * 设置返回code
     *
     * @param code
     * @return
     */
    public ResultBody code(Integer code) {
        this.setCode(code);
        return this;
    }

    /**
     * 设置返回数据
     *
     * @param key   key
     * @param value 内容
     * @return
     */
    public ResultBody data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    /**
     * 设置返回数据
     *
     * @param map
     * @return
     */
    public ResultBody data(Map<String, Object> map) {
        this.data.putAll(map);
        return this;
    }

    /**
     * 设置返回数据
     * <br/> 基本引用类型、列表类型都不能使用该方法 <br/> 对map集合、对象兼容 <br/> <strong>使用时考虑清楚</strong>
     *
     * @param data
     * @return
     */
//    @Deprecated
    public ResultBody data(Object data) {
        BeanUtil.copyProperties(data,this.data);
        return this;
    }

    /**
     * 追加说明信息
     *
     * @param field 追加信息字段名
     * @param info  追加信息内容
     * @return
     */
    public ResultBody info(String field, String info) {
        this.expound.put(field, info);
        return this;
    }

    /**
     * 追加说明信息
     *
     * @param expound 追加信息
     * @return
     */
    public ResultBody info(Map<String, String> expound) {
        this.setExpound(expound);
        return this;
    }
}
