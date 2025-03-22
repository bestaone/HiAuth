package cn.hiauth.server.mapper;

import cn.hiauth.server.entity.AppResource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Set;

/**
 * 系统资源
 */
@Mapper
public interface AppResourceMapper extends BaseMapper<AppResource> {

    @Select("""
                <script>
                SELECT O.* FROM t_app_resource O
                LEFT JOIN t_role_app_resource AS O1 ON O1.app_resource_id = O.id
                WHERE O.app_id=#{appId} AND O1.role_id IN <foreach collection='roleIds' item='id' open='(' separator=',' close=')'>#{id}</foreach>
                </script>
            """)
    List<AppResource> findByAppIdAndRoleIds(@Param("appId") Long appId, @Param("roleIds") Set<Long> roleIds);

}
