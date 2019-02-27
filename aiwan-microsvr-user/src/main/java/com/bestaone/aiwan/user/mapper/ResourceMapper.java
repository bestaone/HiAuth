package com.bestaone.aiwan.user.mapper;

import com.bestaone.aiwan.core.mapper.BaseMapper;
import com.bestaone.aiwan.user.domain.Resource;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ResourceMapper extends BaseMapper<Resource,Long> {

    @Override
    @Insert("INSERT INTO sys_resource(id,name,domain,operate,code) VALUES(#{id},#{name},#{domain},#{operate},#{code})")
    void insert(Resource resource);

    @Override
    @Delete("DELETE FROM sys_resource WHERE id = #{id}")
    void delete(Long id);

    @Override
    @Update("UPDATE sys_resource SET name=#{name},domain=#{domain},operate=#{operate},code=#{code} WHERE id =#{id}")
    void update(Resource resource);

    @Override
    @ResultMap("BaseResultMap")
    @Select("SELECT * FROM sys_resource WHERE id=#{id}")
    Resource findById(Long id);

    @Override
    @ResultMap("BaseResultMap")
    @Select("SELECT * FROM sys_resource")
    List<Resource> findAll();

    List<Resource> findByUserId(Long userId);

}
