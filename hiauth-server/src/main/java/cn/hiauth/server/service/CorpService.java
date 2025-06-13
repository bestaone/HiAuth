package cn.hiauth.server.service;

import cn.hiauth.server.api.dto.RegisterDto;
import cn.hiauth.server.api.vo.CorpResourceTreeNodeVo;
import cn.hiauth.server.api.vo.IndexCorpAppVo;
import cn.hiauth.server.entity.Corp;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Set;

/**
 * 租户
 */
public interface CorpService extends IService<Corp> {

    List<CorpResourceTreeNodeVo> findCorpAppResourceTree(Long cid);

    void register(RegisterDto dto);

    List<IndexCorpAppVo> findIndexCorpAppByUserId(Long userId);

    void saveCorpAppBatch(Long cid, Set<Long> appIds);

}
