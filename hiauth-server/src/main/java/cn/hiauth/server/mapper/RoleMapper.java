package cn.hiauth.server.mapper;

import cn.hiauth.server.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 角色
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    @ResultMap("BaseResultMap")
    @Select("""
                SELECT O.*
                FROM t_role AS O
                LEFT JOIN t_emp_role AS O1 ON O1.role_id = O.id
                WHERE O1.emp_id = #{empId}
            """)
    List<Role> findByEmpId(@Param("empId") Long empId);

    @Delete("""
                <script>
                DELETE FROM t_emp_role WHERE emp_id=#{empId}
                    <if test="roleIds!=null and roleIds.size() > 0">
                        AND role_id NOT IN 
                        <foreach collection="roleIds" item="roleId" index="index" open="(" separator="," close=")">
                            #{roleId}
                        </foreach>
                    </if>
                </script>
            """)
    void removeEmpRole(@Param("empId") Long empId, @Param("roleIds") Set<Long> roleIds);

    @Insert("""
                <script>
                INSERT INTO t_emp_role (id, cid, role_id, emp_id) VALUES
                <foreach collection="empRoles" item="o" index="index" separator=",">
                    (#{o.id}, #{o.cid},#{o.roleId},#{o.empId})
                </foreach>
                ON CONFLICT (role_id, emp_id) DO NOTHING
                </script>
            """)
    void createEmpRole(@Param("empRoles") List<Map<String, Object>> empRoles);

}
