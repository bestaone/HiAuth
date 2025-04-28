package cn.hiauth.server.mapper;

import cn.hiauth.server.entity.Corp;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 租户
 */
@Mapper
public interface CorpMapper extends BaseMapper<Corp> {

    @ResultMap("BaseResultMap")
    @Select("""
        <script>
        SELECT O.* FROM t_corp O
        LEFT JOIN t_employee O1 ON O1.cid = O.id
        WHERE O1.user_id=#{userId}
        ORDER BY O1.last_login_time DESC
        </script>
    """)
    List<Corp> findByUserId(@Param("userId") Long userId);

}
