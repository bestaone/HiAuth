package com.bestaone.aiwan.misc.service.impl;

import com.bestaone.aiwan.misc.domain.SysLog;
import com.bestaone.aiwan.misc.mapper.SysLogMapper;
import com.bestaone.aiwan.misc.service.SysLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysLogServiceImpl implements SysLogService {

    @Resource
    SysLogMapper mapper;

    @Override
    public SysLog save(SysLog user) {
        if(user.getId()!=null){
            mapper.update(user);
        } else {
            user.setId(System.currentTimeMillis());
            mapper.insert(user);
        }
        return user;
    }

    @Override
    public SysLog findById(Long id) {
        return mapper.findById(id);
    }

    @Override
    public List<SysLog> findAll() {
        return mapper.findAll();
    }

    @Override
    public void delete(Long id) {
        mapper.delete(id);
    }

}
