package cn.hiauth.server.mapper;

import cn.hiauth.server.entity.Department;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 部门
 */
@Mapper
public interface DepartmentMapper extends BaseMapper<Department> {

    @Select("""
                SELECT O.dep_id FROM t_dep_emp O
                WHERE O.cid=#{cid} AND O.emp_id=#{empId}
            """)
    Set<Long> findDepIdsByEmpId(@Param("cid") Long cid, @Param("empId") Long empId);

    @Insert("""
                <script>
                INSERT INTO t_dep_emp (id, cid, emp_id, dep_id) VALUES
                <foreach collection="depEmps" item="o" index="index" separator=",">
                    (#{o.id}, #{o.cid},#{o.empId},#{o.depId})
                </foreach>
                </script>
            """)
    void addDepEmp(@Param("depEmps") List<Map<String, Object>> depEmps);

    @Insert("""
                <script>
                INSERT INTO t_dep_emp (id, cid, emp_id, dep_id) VALUES
                <foreach collection="depEmps" item="o" index="index" separator=",">
                    (#{o.id}, #{o.cid},#{o.empId},#{o.depId})
                </foreach>
                ON CONFLICT DO NOTHING
                </script>
            """)
    void modifyDepEmp(@Param("depEmps") List<Map<String, Object>> depEmps);

    @Delete("""
                <script>
                DELETE FROM t_dep_emp WHERE cid=#{cid} AND emp_id=#{empId} 
                    AND dep_id NOT IN <foreach collection="depIds" item="depId" index="index" open="(" separator="," close=")">#{depId}</foreach>
                </script>
            """)
    void resetEmpDep(@Param("cid") Long cid, @Param("empId") Long empId, @Param("depIds") Set<Long> depIds);

    @Delete("""
                <script>
                DELETE FROM t_dep_emp WHERE cid=#{cid} AND emp_id IN <foreach collection="empIds" item="empId" index="index" open="(" separator="," close=")">#{empId}</foreach>
                </script>
            """)
    void del(@Param("cid") Long cid, @Param("empIds") Set<Long> empIds);

}
