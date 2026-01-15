package cn.hiauth.server.service.impl;

import cn.hiauth.server.entity.CorpLog;
import cn.hiauth.server.mapper.CorpLogMapper;
import cn.hiauth.server.service.CorpLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 企业日志
 */
@Service
public class CorpLogServiceImpl extends ServiceImpl<CorpLogMapper, CorpLog> implements CorpLogService {

}