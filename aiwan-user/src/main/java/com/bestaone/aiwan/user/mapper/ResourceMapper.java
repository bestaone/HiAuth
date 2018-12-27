package com.bestaone.aiwan.user.mapper;

import com.bestaone.aiwan.user.domain.Resource;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ResourceMapper {

    @Insert("INSERT INTO resource(id,operate,code) VALUES(#{id},#{operate},#{code})")
    void insert(Resource resource);

    @Delete("DELETE FROM resource WHERE id = #{id}")
    void delete(Long id);

    @Update("UPDATE resource SET operate=#{operate},code=#{code} WHERE id =#{id}")
    int update(Resource resource);

    @ResultMap("BaseResultMap")
    @Select("SELECT * FROM resource WHERE id=#{id}")
    Resource findById(Long id);

    @ResultMap("BaseResultMap")
    @Select("SELECT * FROM resource")
    List<Resource> findAll();

}
