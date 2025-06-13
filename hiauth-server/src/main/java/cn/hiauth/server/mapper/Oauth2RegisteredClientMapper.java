package cn.hiauth.server.mapper;

import cn.hiauth.server.entity.Oauth2RegisteredClient;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

/**
 * oauth2客户端
 */
@Mapper
public interface Oauth2RegisteredClientMapper extends BaseMapper<Oauth2RegisteredClient> {

    @Select("SELECT DISTINCT O.* FROM oauth2_registered_client O WHERE O.client_id = #{clientId}")
    Oauth2RegisteredClient findByClientId(@Param("clientId") String clientId);

    @ResultMap("BaseResultMap")
    @Select("""
            <script>
            SELECT DISTINCT * FROM (
                SELECT * FROM oauth2_registered_client O
                LEFT JOIN t_corp_app AS O1 ON O1.app_id = O.app_id
                WHERE O1.corp_id = #{corpId}   
            ) AS q ${ew.customSqlSegment}
            </script>
            """)
    IPage<Oauth2RegisteredClient> pageByCorpId(@Param("page") IPage<Oauth2RegisteredClient> page, @Param("ew") Wrapper<Oauth2RegisteredClient> queryWrapper, @Param("corpId") Long corpId);

}
