package cn.hiauth.server.service.impl;

import cn.hiauth.server.entity.Oauth2AuthorizationConsent;
import cn.hiauth.server.mapper.Oauth2AuthorizationConsentMapper;
import cn.hiauth.server.service.Oauth2AuthorizationConsentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * oauth2认证授权
 */
@Service
public class Oauth2AuthorizationConsentServiceImpl extends ServiceImpl<Oauth2AuthorizationConsentMapper, Oauth2AuthorizationConsent> implements Oauth2AuthorizationConsentService {

}