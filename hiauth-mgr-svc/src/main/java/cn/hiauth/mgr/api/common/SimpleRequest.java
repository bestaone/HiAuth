package cn.hiauth.mgr.api.common;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

public abstract class SimpleRequest extends Request {

    public <T> QueryWrapper<T> toQueryWapper() {
        return toParamsQueryWapper();
    }

    public abstract <T> QueryWrapper<T> toParamsQueryWapper();

}
