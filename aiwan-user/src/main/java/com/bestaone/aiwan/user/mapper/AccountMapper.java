package com.bestaone.aiwan.user.mapper;

import com.bestaone.aiwan.user.domain.Account;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AccountMapper {

    @Insert("INSERT INTO account(id,userId) VALUES(#{id},#{user.id})")
    void insert(Account account);

    @Delete("DELETE FROM account WHERE id = #{id}")
    void delete(Long id);

    @Update("UPDATE account SET userId=#{user.id} WHERE id =#{id}")
    int update(Account account);

    @ResultMap("BaseResultMap")
    @Select("SELECT id,userId FROM account WHERE id=#{id}")
    Account findById(Long id);

    @ResultMap("BaseResultMap")
    @Select("SELECT id,userId FROM account")
    List<Account> findAll();

}
