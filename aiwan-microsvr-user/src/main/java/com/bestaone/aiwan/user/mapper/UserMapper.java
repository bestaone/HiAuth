package com.bestaone.aiwan.user.mapper;

import com.bestaone.aiwan.user.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO sys_user(id,name,username,password,tel,gender,createTime) VALUES(#{id},#{name},#{username},#{password},#{tel},#{gender},#{createTime})")
    void insert(User user);

    @Delete("DELETE FROM sys_user WHERE id = #{id}")
    void delete(Long id);

    @Update("UPDATE sys_user SET name=#{name},username=#{username},password=#{password},tel=#{tel},gender=#{gender},createTime=#{createTime} WHERE id =#{id}")
    int update(User user);

    @ResultMap("BaseResultMap")
    @Select("SELECT * FROM sys_user WHERE id=#{id}")
    User findById(Long id);

    @ResultMap("BaseResultMap")
    @Select("SELECT * FROM sys_user WHERE username=#{username}")
    User findByUsername(String username);

    @ResultMap("BaseResultMap")
    @Select("SELECT * FROM sys_user WHERE tel=#{tel}")
    User findByTel(String tel);

    @ResultMap("BaseResultMap")
    @Select("SELECT * FROM sys_user")
    List<User> findAll();

    @ResultMap("BaseResultMap")
    @Select("SELECT * FROM sys_user WHERE name like #{name}")
    List<User> findByName(String name);

}
