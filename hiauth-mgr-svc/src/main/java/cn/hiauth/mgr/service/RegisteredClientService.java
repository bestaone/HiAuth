package cn.hiauth.mgr.service;

import cn.hiauth.mgr.domain.RegisteredClient;
import com.baomidou.mybatisplus.extension.service.IService;

public interface RegisteredClientService extends IService<RegisteredClient> {

    String create(RegisteredClient client);

}
