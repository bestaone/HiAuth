package com.bestaone.aiwan.misc.service;

import com.bestaone.aiwan.misc.domain.SysLog;

import java.util.List;

public interface SysLogService {

    SysLog save(SysLog user);

    SysLog findById(Long id);

    List<SysLog> findAll();

    void delete(Long id);

}
