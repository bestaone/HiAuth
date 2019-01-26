package com.bestaone.aiwan.auth.mapper;

import com.bestaone.aiwan.auth.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RoleMapper {

    @Insert("INSERT INTO role(id,code,name,description) VALUES(#{id},#{code},#{name},#{description})")
    void insert(Role role);

    @Delete("DELETE FROM role WHERE id = #{id}")
    void delete(Long id);

    @Update("UPDATE role SET code=#{code},name=#{name},description=#{description} WHERE id =#{id}")
    int update(Role role);

    @ResultMap("BaseResultMap")
    @Select("SELECT * FROM role WHERE id=#{id}")
    Role findById(Long id);

    @ResultMap("BaseResultMap")
    @Select("SELECT * FROM role")
    List<Role> findAll();

    List<Role> findByUserId(Long userId);

}
