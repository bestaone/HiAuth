package cn.hiauth.mgr.api.common;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

public abstract class LimitRequest extends Request {

    private Integer offset = 0;
    private Integer limit = 500;

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public <T> QueryWrapper<T> toQueryWapper() {
        QueryWrapper<T> queryWrapper = toParamsQueryWapper();
        queryWrapper.last("limit " + getOffset() + "," + getLimit());
        return queryWrapper;
    }

    public abstract <T> QueryWrapper<T> toParamsQueryWapper();

}
