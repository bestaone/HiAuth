package com.bestaone.aiwan.user.service.impl;

import com.bestaone.aiwan.core.mapper.BaseMapper;
import com.bestaone.aiwan.core.service.BaseServiceImpl;
import com.bestaone.aiwan.user.domain.App;
import com.bestaone.aiwan.user.mapper.AppMapper;
import com.bestaone.aiwan.user.service.AppService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AppServiceImpl extends BaseServiceImpl<App, Long> implements AppService {

    @Resource
    AppMapper mapper;

    @Override
    public BaseMapper<App, Long> getMapper() {
        return mapper;
    }

    @Override
    public App save(App app) {
        if(app.getId()==null){
            app.setId((Long) idGenerator.generate());
            mapper.insert(app);
            if(app.getClientId()!=null){
                mapper.insertClientDetails(app);
            }
        }else {
            mapper.update(app);
        }
        return app;
    }

    @Override
    public List<App> findByNameOrClientId(String name, String clientId) {
        return mapper.findByNameOrClientId(name, clientId);
    }

}
