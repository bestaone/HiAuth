package cn.hiauth.server.service;

import cn.hiauth.server.entity.Oauth2RegisteredClient;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * oauth2客户端
 */
public interface Oauth2RegisteredClientService extends IService<Oauth2RegisteredClient> {

    Oauth2RegisteredClient findByClientId(String clientId);

    IPage<Oauth2RegisteredClient> pageByCorpId(Page<Oauth2RegisteredClient> page, LambdaQueryWrapper<Oauth2RegisteredClient> queryWapper, Long corpId);

}
