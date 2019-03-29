package com.bestaone.hiauth.mapper;

import com.bestaone.hiauth.core.mapper.BaseMapper;
import com.bestaone.hiauth.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User,Long> {

    @Override
    @Insert("INSERT INTO sys_user(id,name,avatarUrl,username,password,tel,gender,createTime,lastLoginTime) VALUES(#{id},#{name},#{avatarUrl},#{username},#{password},#{tel},#{gender},#{createTime},#{lastLoginTime})")
    void insert(User user);

    @Override
    @Delete("DELETE FROM sys_user WHERE id = #{id}")
    void delete(Long id);

    @Override
    @Update("UPDATE sys_user SET name=#{name},avatarUrl=#{avatarUrl},username=#{username},password=#{password},tel=#{tel},gender=#{gender},createTime=#{createTime},lastLoginTime=#{lastLoginTime} WHERE id =#{id}")
    void update(User user);

    @Override
    @ResultMap("BaseResultMap")
    @Select("SELECT * FROM sys_user WHERE id=#{id}")
    User findById(Long id);

    @Override
    @ResultMap("BaseResultMap")
    @Select("SELECT * FROM sys_user")
    List<User> findAll();

    @ResultMap("BaseResultMap")
    @Select("SELECT * FROM sys_user WHERE name like #{name}")
    List<User> findByName(String name);

    @ResultMap("BaseResultMap")
    @Select("SELECT * FROM sys_user WHERE username=#{username}")
    User findByUsername(String username);

    @ResultMap("BaseResultMap")
    @Select("SELECT * FROM sys_user WHERE tel=#{tel}")
    User findByTel(String tel);

}
