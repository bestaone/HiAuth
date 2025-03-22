package cn.hiauth.server.mapper;

import cn.hiauth.server.entity.File;
import cn.webestar.scms.mybatisplus.cache.CacheHelper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文件
 */
@Mapper
@CacheNamespace(implementation = CacheHelper.class, eviction = CacheHelper.class)
public interface FileMapper extends BaseMapper<File> {

}
