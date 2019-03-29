package com.bestaone.hiauth.mapper;

import com.bestaone.hiauth.core.mapper.BaseMapper;
import com.bestaone.hiauth.domain.App;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AppMapper extends BaseMapper<App,Long> {

    @Override
    void insert(App app);
    void insertClientDetails(App app);

    @Override
    void delete(Long id);

    @Override
    void update(App app);

    @Override
    App findById(Long id);

    @Override
    List<App> findAll();

    List<App> findByNameOrClientId(@Param("name") String name, @Param("clientId") String clientId);

    @ResultMap("BaseResultMap")
    @Select("SELECT * FROM sys_app WHERE clientId=#{clientId}")
    App findByClientId(@Param("clientId") String clientId);

}
