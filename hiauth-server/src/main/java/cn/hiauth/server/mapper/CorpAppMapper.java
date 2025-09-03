package cn.hiauth.server.mapper;

import cn.hiauth.server.entity.CorpApp;
import cn.hiauth.server.entity.CorpAppInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 租户
 */
@Mapper
public interface CorpAppMapper extends BaseMapper<CorpApp> {

    @Select("""
            <script>
            SELECT
               O1.id		appId,
               O1.name		appName,
               O1.home		appHome,
               O1.remark	appRemark,
               O2.id		corpId,
               O2.name		corpName
            FROM t_corp_app O
            LEFT JOIN t_app AS O1 ON O1.id = O.app_id
            LEFT JOIN t_corp AS O2 ON O2.id = O.corp_id
            LEFT JOIN t_employee AS O3 ON O3.cid = O.corp_id
            WHERE O3.user_id = #{userId} <if test="appId!=null">AND O.app_id=#{appId}</if>
            ORDER BY O3.last_login_time DESC NULLS LAST
            </script>
            """)
    List<CorpAppInfo> limitCorpAppInfoByUserId(@Param("userId") Long userId, @Param("appId") Long appId);

}
