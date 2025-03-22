package cn.hiauth.server.service.impl;

import cn.hiauth.server.entity.Oauth2Authorization;
import cn.hiauth.server.mapper.Oauth2AuthorizationMapper;
import cn.hiauth.server.service.Oauth2AuthorizationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * oauth2认证
 */
@Service
public class Oauth2AuthorizationServiceImpl extends ServiceImpl<Oauth2AuthorizationMapper, Oauth2Authorization> implements Oauth2AuthorizationService {

}