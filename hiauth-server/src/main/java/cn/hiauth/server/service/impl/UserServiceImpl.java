package cn.hiauth.server.service.impl;

import cn.hiauth.server.entity.User;
import cn.hiauth.server.mapper.UserMapper;
import cn.hiauth.server.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 用户
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}