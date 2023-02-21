package cn.hiauth.mgr.mapper;

import cn.hiauth.mgr.domain.RegisteredClient;
import cn.hiauth.mgr.utils.CacheHelper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@CacheNamespace(implementation = CacheHelper.class, eviction = CacheHelper.class)
public interface RegisteredClientMapper extends BaseMapper<RegisteredClient> {

}
