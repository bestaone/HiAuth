package cn.hiauth.server.mapper;

import cn.hiauth.server.api.dto.appClient.AppClientLimitDto;
import cn.hiauth.server.entity.App;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 应用
 */
@Mapper
public interface AppMapper extends BaseMapper<App> {

    @Select("""
                SELECT DISTINCT O.* FROM t_app O
                LEFT JOIN t_corp_app AS O1 ON O1.app_id = O.id
                WHERE o1.corp_id = #{cid}
            """)
    List<App> findByCid(Long cid);

    @Select("""
                SELECT * FROM t_app
                WHERE id NOT IN(
                    SELECT O.id FROM t_app O
                    LEFT JOIN oauth2_registered_client AS O1 ON O1.app_id = O.id
                    WHERE o1.cid = #{dto.cid}
                )
                OFFSET #{dto.offset} LIMIT #{dto.limit}
            """)
    List<App> limitNotHaveApp(@Param("dto") AppClientLimitDto dto);

    @Select("""
                SELECT O.* FROM t_app O
                LEFT JOIN oauth2_registered_client AS O1 ON O1.app_id = O.id
                WHERE o1.cid = #{dto.cid}
                OFFSET #{dto.offset} LIMIT #{dto.limit}
            """)
    List<App> limitHaveApp(@Param("dto") AppClientLimitDto dto);

}
