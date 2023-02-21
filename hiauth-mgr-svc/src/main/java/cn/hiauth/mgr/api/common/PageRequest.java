package cn.hiauth.mgr.api.common;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

public abstract class PageRequest extends Request {

    private Integer pageNum = 1;
    private Integer pageSize = 50;

    public PageRequest() {}

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

    public Page getPage() {
        return new Page(pageNum, pageSize, true);
    }

    public <T> QueryWrapper<T> toQueryWapper() {
        return toParamsQueryWapper();
    }

    public abstract <T> QueryWrapper<T> toParamsQueryWapper();

}
