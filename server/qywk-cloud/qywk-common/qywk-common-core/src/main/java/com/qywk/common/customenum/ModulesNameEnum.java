package com.qywk.common.customenum;

/**
 * @author cxq
 * @date 2023-8-8
 * @description 模块名称定义枚举
 */
public enum ModulesNameEnum {

    PUBLIC(0, "public"), // 公共模块

    ;
    public final Integer code;
    public final String name;
    ModulesNameEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
