package com.bestaone.aiwan.common.api;

import java.util.List;

public class PageVo<T> {

    private Integer pageNum;
    private Integer pageSize;
    private Long totle;
    private Integer pages;
    private List<T> list;

    public PageVo(Integer pageNum, Integer pageSize, Long totle, Integer pages, List<T> list) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totle = totle;
        this.pages = pages;
        this.list = list;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotle() {
        return totle;
    }

    public void setTotle(Long totle) {
        this.totle = totle;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

}