package cn.hiauth.server.service.impl;

import cn.hiauth.server.api.dto.appClient.AppClientLimitDto;
import cn.hiauth.server.entity.App;
import cn.hiauth.server.mapper.AppMapper;
import cn.hiauth.server.service.AppService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 应用
 */
@Service
public class AppServiceImpl extends ServiceImpl<AppMapper, App> implements AppService {

    @Override
    public List<App> limitNotHaveApp(AppClientLimitDto dto) {
        return baseMapper.limitNotHaveApp(dto);
    }

    @Override
    public List<App> limitHaveApp(AppClientLimitDto dto) {
        return baseMapper.limitHaveApp(dto);
    }

    @Override
    public Map<Long, App> findByIds(Set<Long> appIds) {
        List<App> list = baseMapper.selectBatchIds(appIds);
        return list.stream().collect(Collectors.toMap(App::getId, Function.identity()));
    }

//    @Override
//    public List<App> limitAppByUserId(Long userId) {
//        return baseMapper.limitAppByUserId(userId);
//    }

}