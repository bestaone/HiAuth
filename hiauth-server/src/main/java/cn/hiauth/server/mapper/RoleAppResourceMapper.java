package cn.hiauth.server.mapper;

import cn.hiauth.server.entity.RoleAppResource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 角色应用资源关联关系
 */
@Mapper
public interface RoleAppResourceMapper extends BaseMapper<RoleAppResource> {

    @Select("""
            <script>
                INSERT INTO t_role_app_resource ( id, app_id, cid, role_id, app_resource_id ) VALUES
                <foreach collection="list" item="o" separator=",">
                    (#{o.id},#{o.appId},#{o.cid},#{o.roleId},#{o.appResourceId})
                </foreach>
                ON CONFLICT (role_id, app_resource_id) DO NOTHING
            </script>
            """)
    void auth(@Param("list") List<RoleAppResource> list);

}
