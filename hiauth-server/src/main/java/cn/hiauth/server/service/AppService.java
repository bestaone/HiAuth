package cn.hiauth.server.service;

import cn.hiauth.server.api.dto.appClient.AppClientLimitDto;
import cn.hiauth.server.entity.App;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 应用
 */
public interface AppService extends IService<App> {

    List<App> limitNotHaveApp(AppClientLimitDto dto);

    List<App> limitHaveApp(AppClientLimitDto dto);

    Map<Long, App> findByIds(Set<Long> appIds);

}
