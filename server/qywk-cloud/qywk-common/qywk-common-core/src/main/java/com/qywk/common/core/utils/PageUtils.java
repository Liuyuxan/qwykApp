package com.qywk.common.core.utils;

import java.util.List;

/**
 * @author qlh
 * @date 2024/04/02 17:11
 * @description 分页工具类
 */
public class PageUtils<T> {

    private Integer pageNum;
    private Integer pageSize;
    private final List<T> src;

    public PageUtils(List<T> src, Integer pageNum, Integer pageSize){
        this.src = src;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public List<T> getRecords(){
        int total = src.size();
        int startIndex = (pageNum - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, total);
        if(startIndex > endIndex) return null;
        return src.subList(startIndex, endIndex);
    }

    public Integer getTotal(){
        return this.src.size();
    }

    public void setPageNum(Integer pageNum){
        this.pageNum = pageNum;
    }

    public void setPageSize(Integer pageSize){
        this.pageSize = pageSize;
    }

}
