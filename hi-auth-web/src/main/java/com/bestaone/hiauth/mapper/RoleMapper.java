package com.bestaone.hiauth.mapper;

import com.bestaone.hiauth.core.mapper.BaseMapper;
import com.bestaone.hiauth.domain.Role;
import com.bestaone.hiauth.domain.RoleResource;
import com.bestaone.hiauth.domain.UserRole;
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

    void insertUserRoles(@Param("userRoles") List<UserRole> userRoles);

    @Delete("DELETE FROM sys_user_role WHERE userId = #{userId}")
    void deleteUserRole(Long userId);

    void insertRoleResources(@Param("roleResources") List<RoleResource> roleResources);

    void deleteRoleResources(@Param("roleId") Long roleId, @Param("resourcesIds") List<Long> resourcesIds);

}
