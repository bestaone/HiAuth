package com.bestaone.hiauth.core.service;

import com.bestaone.hiauth.core.entity.BaseEntity;
import com.bestaone.hiauth.core.mapper.BaseMapper;
import com.bestaone.hiauth.utils.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.List;

public abstract class BaseServiceImpl<T extends BaseEntity, PK extends Serializable> implements BaseService<T, PK> {

    @Autowired
    protected IdGenerator idGenerator;

    public abstract BaseMapper<T, PK> getMapper();

    @Override
    public T findById(PK id) {
        Assert.notNull(id, "通过主键查找时，给出的id不能为空");
        return getMapper().findById(id);
    }

    @Override
    public T save(T entity){
        Assert.notNull(entity, "待保存的对象不能为空");
        if(entity.getId()==null){
            entity.setId(idGenerator.generate());
            getMapper().insert(entity);
        }else {
            getMapper().update(entity);
        }
        return entity;
    }

    @Override
    public void delete(PK id){
        Assert.notNull(id, "删除对象时，没有给出id");
        getMapper().delete(id);
    }

    @Override
    public List<T> findAll(){
        return getMapper().findAll();
    }

}
