package com.bestaone.aiwan.user.mapper;

import com.bestaone.aiwan.core.mapper.BaseMapper;
import com.bestaone.aiwan.user.domain.App;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

}
