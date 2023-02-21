package cn.hiauth.mgr.service.impl;

import cn.hiauth.mgr.domain.User;
import cn.hiauth.mgr.mapper.UserMapper;
import cn.hiauth.mgr.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
