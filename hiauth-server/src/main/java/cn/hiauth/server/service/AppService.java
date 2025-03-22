package cn.hiauth.server.service;

import cn.hiauth.server.api.dto.corpApp.CorpAppLimitDto;
import cn.hiauth.server.entity.App;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 应用
 */
public interface AppService extends IService<App> {

    List<App> limitNotHaveApp(CorpAppLimitDto dto);

    List<App> limitHaveApp(CorpAppLimitDto dto);

    Map<Long, App> findByIds(Set<Long> appIds);

}
