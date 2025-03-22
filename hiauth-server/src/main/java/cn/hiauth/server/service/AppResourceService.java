package cn.hiauth.server.service;

import cn.hiauth.server.api.dto.appResource.FindAppResourceIdsByRoleAndAppDto;
import cn.hiauth.server.api.vo.CommonTreeNodeVo;
import cn.hiauth.server.entity.AppResource;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Set;

/**
 * 系统资源
 */
public interface AppResourceService extends IService<AppResource> {

    List<AppResource> findByAppIdAndRoleIds(Long appId, Set<Long> roleIds);

    List<CommonTreeNodeVo> findAppResourceTree(Long appId);

    Set<Long> findAppResourceIdsByRoleAndApp(FindAppResourceIdsByRoleAndAppDto dto);

}
