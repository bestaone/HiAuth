package com.bestaone.aiwan.misc.mapper;

import com.bestaone.aiwan.misc.domain.SysLog;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SysLogMapper {

    @Insert("INSERT INTO sysLog(id,content,createTime) VALUES(#{id},#{content},#{createTime})")
    void insert(SysLog user);

    @Delete("DELETE FROM sysLog WHERE id = #{id}")
    void delete(Long id);

    @Update("UPDATE sysLog SET content=#{content},createTime=#{createTime} WHERE id =#{id}")
    int update(SysLog user);

    @ResultMap("BaseResultMap")
    @Select("SELECT * FROM sysLog WHERE id=#{id}")
    SysLog findById(Long id);

    @ResultMap("BaseResultMap")
    @Select("SELECT * FROM sysLog")
    List<SysLog> findAll();

}
