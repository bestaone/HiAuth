package com.bestaone.aiwan.auth.mapper;

import com.bestaone.aiwan.auth.domain.Resource;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ResourceMapper {

    @Insert("INSERT INTO sys_resource(id,operate,code) VALUES(#{id},#{operate},#{code})")
    void insert(Resource resource);

    @Delete("DELETE FROM sys_resource WHERE id = #{id}")
    void delete(Long id);

    @Update("UPDATE sys_resource SET operate=#{operate},code=#{code} WHERE id =#{id}")
    int update(Resource resource);

    @ResultMap("BaseResultMap")
    @Select("SELECT * FROM sys_resource WHERE id=#{id}")
    Resource findById(Long id);

    @ResultMap("BaseResultMap")
    @Select("SELECT * FROM sys_resource")
    List<Resource> findAll();

    List<Resource> findByUserId(Long userId);

}
