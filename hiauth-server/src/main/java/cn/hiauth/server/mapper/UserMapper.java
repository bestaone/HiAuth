package cn.hiauth.server.mapper;

import cn.hiauth.server.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
