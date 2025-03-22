package cn.hiauth.server.service.impl;

import cn.hiauth.server.api.dto.appResource.FindAppResourceIdsByRoleAndAppDto;
import cn.hiauth.server.api.vo.CommonTreeNodeVo;
import cn.hiauth.server.entity.AppResource;
import cn.hiauth.server.entity.RoleAppResource;
import cn.hiauth.server.mapper.AppMapper;
import cn.hiauth.server.mapper.AppResourceMapper;
import cn.hiauth.server.mapper.CorpAppMapper;
import cn.hiauth.server.mapper.RoleAppResourceMapper;
import cn.hiauth.server.service.AppResourceService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 系统资源
 */
@Service
public class AppResourceServiceImpl extends ServiceImpl<AppResourceMapper, AppResource> implements AppResourceService {

    @Autowired
    private CorpAppMapper corpAppMapper;

    @Autowired
    private AppMapper appMapper;

    @Autowired
    private RoleAppResourceMapper roleAppResourceMapper;

    private static List<CommonTreeNodeVo> buildDepTree(List<AppResource> list) {
        list.sort(Comparator.comparing(AppResource::getSort));
        List<CommonTreeNodeVo> vos = new ArrayList<>();
        for (AppResource o : list) {
            if (o.getPid() == null) {
                CommonTreeNodeVo vo = CommonTreeNodeVo.convertToVo(o);
                buildChild(vo, list, 1);
                vos.add(vo);
            }
        }
        return vos;
    }

    private static void buildChild(CommonTreeNodeVo parent, List<AppResource> list, int depth) {
        if (depth > 10) return;
        if (parent != null && list != null && !list.isEmpty()) {
            list.forEach(o -> {
                if (o.getPid() != null && o.getPid().equals(parent.getId())) {
                    CommonTreeNodeVo child = CommonTreeNodeVo.convertToVo(o);
                    buildChild(child, list, depth + 1);
                    parent.getChildren().add(child);
                }
            });
        }
    }

    @Override
    public List<AppResource> findByAppIdAndRoleIds(Long appId, Set<Long> roleIds) {
        return this.getBaseMapper().findByAppIdAndRoleIds(appId, roleIds);
    }

    @Override
    public List<CommonTreeNodeVo> findAppResourceTree(Long appId) {
        LambdaQueryWrapper<AppResource> queryWrapper = new LambdaQueryWrapper<>();
        if (appId != null) {
            queryWrapper.eq(AppResource::getAppId, appId);
        }
        List<AppResource> deps = baseMapper.selectList(queryWrapper);
        return buildDepTree(deps);
    }

    @Override
    public Set<Long> findAppResourceIdsByRoleAndApp(FindAppResourceIdsByRoleAndAppDto dto) {
        LambdaQueryWrapper<RoleAppResource> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RoleAppResource::getAppId, dto.getAppId());
        queryWrapper.eq(RoleAppResource::getRoleId, dto.getRoleId());
        List<RoleAppResource> list = roleAppResourceMapper.selectList(queryWrapper);
        if (list == null || list.isEmpty()) {
            return Set.of();
        }
        return list.stream().map(RoleAppResource::getAppResourceId).collect(HashSet::new, Set::add, Set::addAll);
    }

}