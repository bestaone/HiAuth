package cn.hiauth.server.mapper;

import cn.hiauth.server.entity.Oauth2Authorization;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * oauth2认证
 */
@Mapper
public interface Oauth2AuthorizationMapper extends BaseMapper<Oauth2Authorization> {

}
