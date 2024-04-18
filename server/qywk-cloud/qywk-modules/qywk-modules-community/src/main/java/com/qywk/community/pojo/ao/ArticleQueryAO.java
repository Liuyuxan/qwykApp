package com.qywk.community.pojo.ao;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author ricetofu
 * @date 2024/3/30
 * @description 进行文章查询的条件封装实体
 */
@Data
public class ArticleQueryAO {

    /**
     * 获取的数据的页码
     * */
    @NotNull(message = "page参数不能为null")
    private Integer page;

    /**
     * 每页的数据条数
     * */
    @NotNull(message = "limit参数不能为null")
    private Integer limit;

    /**
     * 排序字段，这里定义根据返回的哪个字段进行排序，为空时则代表不启用排序规则<br>
     * 支持的字段规则列表:<br>
     * publish_time: 发布时间<br>
     * hot: 文章热度<br>
     * */
    @NotNull(message = "sortBby参数不能为null")
    private String sortBy;

    /**
     * 排序规则，这里允许的两个合法参数为: desc, ascend 分别代表降序和升序
     * */
    @NotNull(message = "sort参数不能为null")
    private String sort;
}
