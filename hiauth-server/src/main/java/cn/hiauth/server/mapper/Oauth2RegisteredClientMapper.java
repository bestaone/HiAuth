package cn.hiauth.server.mapper;

import cn.hiauth.server.entity.Oauth2RegisteredClient;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * oauth2客户端
 */
@Mapper
public interface Oauth2RegisteredClientMapper extends BaseMapper<Oauth2RegisteredClient> {

    @Select("SELECT DISTINCT O.* FROM oauth2_registered_client O WHERE O.client_id = #{clientId}")
    Oauth2RegisteredClient findByClientId(@Param("clientId") String clientId);

}
