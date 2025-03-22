package cn.hiauth.server.controller.common;

import cn.hiauth.server.api.dto.appResource.AppResourceCreateDto;
import cn.hiauth.server.api.dto.appResource.AppResourcePageDto;
import cn.hiauth.server.api.dto.appResource.AppResourceUpdateDto;
import cn.hiauth.server.api.dto.appResource.FindAppResourceIdsByRoleAndAppDto;
import cn.hiauth.server.api.vo.CommonTreeNodeVo;
import cn.hiauth.server.config.rest.ResourceApi;
import cn.hiauth.server.entity.AppResource;
import cn.hiauth.server.service.AppResourceService;
import cn.webestar.scms.commons.R;
import cn.webestar.scms.commons.api.PageVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
@Validated
@ResourceApi
@RestController
@RequestMapping("/api/common/appResourceMgr")
public class AppResourceMgrController {

    @Autowired
    private AppResourceService appResourceService;

    @PostMapping("/page")
    public R<PageVO<AppResource>> page(@RequestBody @Valid AppResourcePageDto dto) {
        Page<AppResource> p = new Page<>(dto.getPageNum(), dto.getPageSize(), true);
        IPage<AppResource> page = appResourceService.page(p, dto.toQueryWapper());
        page.getRecords().sort(Comparator.comparing(AppResource::getSort));
        return R.success(new PageVO<>(page.getCurrent(), page.getSize(), page.getTotal(), page.getRecords()));
    }

    @PostMapping("/findById")
    public R<AppResource> findById(@RequestParam("id") Long id) {
        AppResource o = appResourceService.getById(id);
        return R.success(o);
    }

    @PostMapping("/create")
    public R<AppResource> create(@RequestBody @Valid AppResourceCreateDto body) {
        AppResource o = body.toDO();
        appResourceService.save(o);
        return R.success(o);
    }

    @PostMapping("/update")
    public R<AppResource> update(@RequestBody @Valid AppResourceUpdateDto body) {
        AppResource o = body.toDO();
        appResourceService.updateById(o);
        return R.success(o);
    }

    @PostMapping("/delete")
    public R<Boolean> delete(@RequestBody Map<String, Set<Long>> map) {
        Set<Long> ids = map.get("ids");
        boolean b = appResourceService.removeByIds(ids);
        return R.success(b);
    }

    @PostMapping("/tree")
    public R<List<CommonTreeNodeVo>> tree(@RequestParam("appId") Long appId) {
        List<CommonTreeNodeVo> tree = appResourceService.findAppResourceTree(appId);
        return R.success(tree);
    }

    @PostMapping("/findAppResourceIdsByRoleAndApp")
    public R<Set<Long>> findAppResourceIdsByRoleAndApp(@RequestBody @Valid FindAppResourceIdsByRoleAndAppDto dto) {
        Set<Long> ids = appResourceService.findAppResourceIdsByRoleAndApp(dto);
        return R.success(ids);
    }

}
