package com.bestaone.aiwan.misc.mapper;

import com.bestaone.aiwan.misc.domain.SysLog;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SysLogMapper {

    @Insert("INSERT INTO sys_log(id,content,createTime) VALUES(#{id},#{content},#{createTime})")
    void insert(SysLog user);

    @Delete("DELETE FROM sys_log WHERE id = #{id}")
    void delete(Long id);

    @Update("UPDATE sys_log SET content=#{content},createTime=#{createTime} WHERE id =#{id}")
    int update(SysLog user);

    @ResultMap("BaseResultMap")
    @Select("SELECT * FROM sys_log WHERE id=#{id}")
    SysLog findById(Long id);

    @ResultMap("BaseResultMap")
    @Select("SELECT * FROM sys_log")
    List<SysLog> findAll();

}
