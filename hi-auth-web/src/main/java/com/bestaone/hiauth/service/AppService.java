package com.bestaone.hiauth.service;

import com.bestaone.hiauth.core.service.BaseService;
import com.bestaone.hiauth.domain.App;

import java.util.List;

public interface AppService extends BaseService<App, Long> {

    List<App> findByNameOrClientId(String name, String clientId);

    App findByClientId(String clientId);

}
