package cn.hiauth.server.service.impl;

import cn.hiauth.server.entity.SysLog;
import cn.hiauth.server.mapper.SysLogMapper;
import cn.hiauth.server.service.SysLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 系统日志
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {

}