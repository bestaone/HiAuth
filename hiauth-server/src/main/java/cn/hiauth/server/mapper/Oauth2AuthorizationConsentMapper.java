package cn.hiauth.server.mapper;

import cn.hiauth.server.entity.Oauth2AuthorizationConsent;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * oauth2认证授权
 */
@Mapper
public interface Oauth2AuthorizationConsentMapper extends BaseMapper<Oauth2AuthorizationConsent> {

}
