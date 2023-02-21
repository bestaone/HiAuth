package cn.hiauth.mgr.mapper;

import cn.hiauth.mgr.domain.Manager;
import cn.hiauth.mgr.utils.CacheHelper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@CacheNamespace(implementation = CacheHelper.class, eviction = CacheHelper.class)
public interface ManagerMapper extends BaseMapper<Manager> {

}
