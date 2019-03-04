package com.bestaone.aiwan.user.service;

import com.bestaone.aiwan.core.service.BaseService;
import com.bestaone.aiwan.user.domain.App;

import java.util.List;

public interface AppService extends BaseService<App, Long> {

    List<App> findByNameOrClientId(String name, String clientId);

}
