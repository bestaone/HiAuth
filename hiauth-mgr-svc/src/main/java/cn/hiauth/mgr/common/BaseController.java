package cn.hiauth.mgr.common;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class BaseController<T> {

    public abstract IService<T> getService();

    @PostMapping("/create")
    public T create(@RequestBody T body) {
        getService().save(body);
        return body;
    }

    @PostMapping("/delete")
    public boolean delete(@RequestParam("id") Serializable id) {
        Assert.notNull(id, 30001, "id不能为空");
        return getService().removeById(id);
    }

    @PostMapping("/update")
    public T update(@RequestBody T body) {
//        Assert.notNull(body.getId(), 30001, "id不能为空");
//        Model o = body.toDO();
        getService().updateById(body);
        return body;
    }

    @PostMapping("/findById")
    public T findById(@RequestParam("id") Serializable id) {
        Assert.notNull(id, 30001, "id不能为空");
        return getService().getById(id);
    }

    @PostMapping("/findByIds")
    public List<T> findByIds(@RequestBody Set<Serializable> ids) {
        Assert.notNull(ids, 30001, "ids不能为空");
        Assert.isTrue(ids.size()>0, 30002, "ids不能为空");
        return getService().listByIds(ids);
    }

    @PostMapping("/page")
    public IPage<T> page(@RequestBody Map<String, Object> body) {

        Assert.isTrue(body.containsKey("pageNum"), 30001, "pageNum不能为空");
        Assert.notNull(body.containsKey("pageSize"), 30002, "pageSize不能为空");
        Integer pageNum = (Integer) body.get("pageNum");
        Integer pageSize = (Integer) body.get("pageSize");
        Assert.isTrue(pageSize<=200, 30003, "pageSize长度不能超过200");

        QueryWrapper queryWrapper = new QueryWrapper();
        for (Map.Entry<String, Object> entry : body.entrySet()) {
            if (!ObjectUtils.isEmpty(entry.getValue()) && !entry.getKey().equals("pageNum") && !entry.getKey().equals("pageSize")) {
                queryWrapper.eq(StringUtils.camelToUnderline(entry.getKey()), entry.getValue());
            }
        }
        IPage<T> page = getService().page(new Page(pageNum, pageSize, true), queryWrapper);
        return page;
    }

    @PostMapping("/findOne")
    public Object findOne(@RequestBody Map<String, Object> body) {

        QueryWrapper queryWrapper = new QueryWrapper();
        for (Map.Entry<String, Object> entry : body.entrySet()) {
            if (!ObjectUtils.isEmpty(entry.getValue())) {
                queryWrapper.eq(StringUtils.camelToUnderline(entry.getKey()), entry.getValue());
            }
        }

        Object o = null;
        try {
            o =  getService().getOne(queryWrapper);
        } catch (MyBatisSystemException e) {
            if (e.getCause() instanceof TooManyResultsException) {
                throw new CommonException(31001, "查询结果不唯一");
            } else {
                e.printStackTrace();
            }
        }

        // 解决无法转换null返回的问题
        return o!=null?o:new HashMap();
    }

    @PostMapping("/limitList")
    public List<T> limitList(@RequestBody Map<String, Object> request) {
        Assert.isTrue(request.containsKey("offset"), 30001, "offset不能为空");
        Assert.isTrue(request.containsKey("limit"), 30002, "limit不能为空");
        QueryWrapper queryWrapper = new QueryWrapper();
        for (Map.Entry<String, Object> entry : request.entrySet()) {
            if (!ObjectUtils.isEmpty(entry.getValue()) && !entry.getKey().equals("offset") && !entry.getKey().equals("limit")) {
                queryWrapper.eq(StringUtils.camelToUnderline(entry.getKey()), entry.getValue());
            }
        }
        queryWrapper.last("limit " + request.get("offset") + "," + request.get("limit"));
        List<T> list = getService().list(queryWrapper);
        return list;
    }

}
