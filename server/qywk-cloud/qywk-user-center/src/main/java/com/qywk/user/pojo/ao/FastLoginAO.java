package com.qywk.user.pojo.ao;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author ricetofu
 * @date 2023/10/21
 * @description 快速登陆接口数据实体
 */
@Data
public class FastLoginAO {

    /**
     * 微信code
     * */
    @NotBlank(message = "code不能为空")
    private String code;

}
