package com.bestaone.aiwan.user.mapper;

import com.bestaone.aiwan.core.mapper.BaseMapper;
import com.bestaone.aiwan.user.domain.Account;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AccountMapper extends BaseMapper<Account,Long> {

    @Override
    @Insert("INSERT INTO sys_account(id,userId,type,openid,accessToken,refreshToken,expireTime,createTime,lastLoginTime) VALUES(#{id},#{user.id},#{type},#{openid},#{accessToken},#{refreshToken},#{expireTime},#{createTime},#{lastLoginTime})")
    void insert(Account account);

    @Override
    @Delete("DELETE FROM sys_account WHERE id = #{id}")
    void delete(Long id);

    @Override
    @Update("UPDATE sys_account SET userId=#{user.id},type=#{type},openid=#{openid},accessToken=#{accessToken},refreshToken=#{refreshToken},expireTime=#{expireTime},createTime=#{createTime},lastLoginTime=#{lastLoginTime} WHERE id =#{id}")
    void update(Account account);

    @Override
    @ResultMap("BaseResultMap")
    @Select("SELECT id,userId,type,openid,accessToken,refreshToken,expireTime,createTime,lastLoginTime FROM sys_account WHERE id=#{id}")
    Account findById(Long id);

    @Override
    @ResultMap("BaseResultMap")
    @Select("SELECT id,userId,type,openid,accessToken,refreshToken,expireTime,createTime,lastLoginTime FROM sys_account")
    List<Account> findAll();

}
