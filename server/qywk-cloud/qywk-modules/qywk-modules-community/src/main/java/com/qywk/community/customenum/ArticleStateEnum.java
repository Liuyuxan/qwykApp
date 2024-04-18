package com.qywk.community.customenum;

/**
 * @author ricetofu
 * @date 2024/4/2
 * @description 文章的状态信息枚举
 */
public enum ArticleStateEnum {

    NORMAL(0,"正常状态的文章,可被其他用户查看到"),
    HIDDEN(1,"被用户自己隐藏的文章,只能用户自己查看到"),
    USER_DELETED(2,"被用户自己删除的文章"),
    ADMIN_DELETED(3, "被管理员删除的文章")
    ;

    public final Integer code; // 状态码
    public final String details; // 状态信息描述


    ArticleStateEnum(Integer code ,String details) {
        this.code = code;
        this.details = details;
    }

}
