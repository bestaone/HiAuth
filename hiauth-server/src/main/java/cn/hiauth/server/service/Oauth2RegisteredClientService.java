package cn.hiauth.server.service;

import cn.hiauth.server.entity.Oauth2RegisteredClient;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * oauth2客户端
 */
public interface Oauth2RegisteredClientService extends IService<Oauth2RegisteredClient> {

    Oauth2RegisteredClient findByClientId(String clientId);

}
