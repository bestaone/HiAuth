package cn.hiauth.server.service.impl;

import cn.hiauth.server.entity.CorpApp;
import cn.hiauth.server.mapper.CorpAppMapper;
import cn.hiauth.server.service.CorpAppService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class CorpAppServiceImpl extends ServiceImpl<CorpAppMapper, CorpApp> implements CorpAppService {

//    @Autowired
//    private CorpAppMapper corpAppMapper;

//    @Override
//    public List<CorpAppInfo> limitCorpAppInfoByUserId(Long userId) {
//        return corpAppMapper.limitCorpAppInfoByUserId(userId);
//    }

}