package com.bestaone.aiwan.user.mapper;

import com.bestaone.aiwan.core.mapper.BaseMapper;
import com.bestaone.aiwan.user.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RoleMapper extends BaseMapper<Role,Long> {

    @Override
    @Insert("INSERT INTO sys_role(id,code,name,description) VALUES(#{id},#{code},#{name},#{description})")
    void insert(Role role);

    @Override
    @Delete("DELETE FROM sys_role WHERE id = #{id}")
    void delete(Long id);

    @Override
    @Update("UPDATE sys_role SET code=#{code},name=#{name},description=#{description} WHERE id =#{id}")
    void update(Role role);

    @Override
    @ResultMap("BaseResultMap")
    @Select("SELECT * FROM sys_role WHERE id=#{id}")
    Role findById(Long id);

    @Override
    @ResultMap("BaseResultMap")
    @Select("SELECT * FROM sys_role")
    List<Role> findAll();

    List<Role> findByUserId(Long userId);

}
