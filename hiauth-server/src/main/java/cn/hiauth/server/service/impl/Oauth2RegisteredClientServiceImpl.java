package cn.hiauth.server.service.impl;

import cn.hiauth.server.entity.Oauth2RegisteredClient;
import cn.hiauth.server.mapper.Oauth2RegisteredClientMapper;
import cn.hiauth.server.service.Oauth2RegisteredClientService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    @Override
    public IPage<Oauth2RegisteredClient> pageByCorpId(Page<Oauth2RegisteredClient> page, LambdaQueryWrapper<Oauth2RegisteredClient> queryWapper, Long corpId) {
        return this.baseMapper.pageByCorpId(page, queryWapper, corpId);
    }

}