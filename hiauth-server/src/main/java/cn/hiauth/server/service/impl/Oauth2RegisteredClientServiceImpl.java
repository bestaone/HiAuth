package cn.hiauth.server.service.impl;

import cn.hiauth.server.entity.Oauth2RegisteredClient;
import cn.hiauth.server.mapper.Oauth2RegisteredClientMapper;
import cn.hiauth.server.service.Oauth2RegisteredClientService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * oauth2客户端
 */
@Service
public class Oauth2RegisteredClientServiceImpl extends ServiceImpl<Oauth2RegisteredClientMapper, Oauth2RegisteredClient> implements Oauth2RegisteredClientService {

    @Override
    public Oauth2RegisteredClient findByClientId(String clientId) {
        return this.baseMapper.findByClientId(clientId);
    }

}