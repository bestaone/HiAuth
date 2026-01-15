package cn.hiauth.server.mapper;

import cn.hiauth.server.entity.CorpLog;
import cn.webestar.scms.mybatisplus.cache.CacheHelper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;

/**
 * 企业日志
 */
@Mapper
@CacheNamespace(implementation = CacheHelper.class, eviction = CacheHelper.class)
public interface CorpLogMapper extends BaseMapper<CorpLog> {

}
