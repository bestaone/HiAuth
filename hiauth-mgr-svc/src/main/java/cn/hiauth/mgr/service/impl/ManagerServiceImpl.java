package cn.hiauth.mgr.service.impl;

import cn.hiauth.mgr.domain.Manager;
import cn.hiauth.mgr.mapper.ManagerMapper;
import cn.hiauth.mgr.service.ManagerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ManagerServiceImpl extends ServiceImpl<ManagerMapper, Manager> implements ManagerService {

}
