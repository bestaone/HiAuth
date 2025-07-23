package cn.hiauth.server.mapper;

import cn.hiauth.server.entity.Employee;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

/**
 * 员工
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {

    @Select("""
            <script>
            SELECT DISTINCT * FROM (
                SELECT o.* FROM t_employee AS o
                LEFT JOIN t_dep_emp AS o1 ON o1.emp_id = o.id
                <if test="depIds!=null and depIds.size()>0">
                WHERE o1.dep_id IN <foreach collection='depIds' item='id' open='(' separator=',' close=')'>#{id}</foreach>
                </if>
            ) AS q ${ew.customSqlSegment}
            </script>
            """)
    IPage<Employee> pageByDepId(@Param("page") IPage page, @Param("ew") Wrapper queryWrapper, @Param("depIds") Set<Long> depIds);

    @ResultMap("BaseResultMap")
    @Select("""
                <script>
                SELECT O.* FROM t_employee O
                LEFT JOIN t_corp_app O1 ON O1.corp_id = O.cid
                WHERE O.user_id=#{userId} AND O1.app_id=#{appId}
                    <if test="corpAdminOnly!=null">
                        AND O.is_corp_admin=#{corpAdminOnly}
                    </if>
                ORDER BY O.last_login_time DESC
                LIMIT 1 OFFSET 0
                </script>
            """)
    Employee findOneByAppIdAndUserId(@Param("appId") Long appId, @Param("userId") Long userId, @Param("corpAdminOnly") Boolean corpAdminOnly);

}
