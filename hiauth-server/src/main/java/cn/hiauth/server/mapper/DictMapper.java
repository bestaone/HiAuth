package cn.hiauth.server.mapper;

import cn.hiauth.server.entity.Dict;
import cn.webestar.scms.mybatisplus.cache.CacheHelper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.*;

import java.util.Set;

/**
 * 字典
 */
@Mapper
@CacheNamespace(implementation = CacheHelper.class, eviction = CacheHelper.class)
public interface DictMapper extends BaseMapper<Dict> {

    @ResultMap("BaseResultMap")
    @Select("""
            <script>
            SELECT DISTINCT * FROM (
                SELECT (O1.id IS NOT NULL) AS has_child, O.*  FROM t_dict O
                LEFT JOIN t_dict O1 ON O1.p_code = O.code
            ) AS Q ${ew.customSqlSegment}
            </script>
            """)
    IPage<Dict> pageByPcode(@Param("page") IPage page, @Param("ew") Wrapper<Dict> queryWrapper, @Param("pCode") String pCode, @Param("isRoot") boolean isRoot);

    @Select("""
            <script>
            SELECT count(O1.id) FROM t_dict O
            LEFT JOIN t_dict O1 ON O1.p_code = O.code
            WHERE O.id IN <foreach collection='ids' item='id' open='(' separator=',' close=')'>#{id}</foreach>
            </script>
            """)
    int childCount(@Param("ids") Set<Long> ids);

}
