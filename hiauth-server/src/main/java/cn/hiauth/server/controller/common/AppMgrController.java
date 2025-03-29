package cn.hiauth.server.controller.common;

import cn.hiauth.server.api.dto.app.AppCreateDto;
import cn.hiauth.server.api.dto.app.AppLimitDto;
import cn.hiauth.server.api.dto.app.AppPageDto;
import cn.hiauth.server.api.dto.app.AppUpdateDto;
import cn.hiauth.server.config.rest.ResourceApi;
import cn.hiauth.server.config.rest.security.MySecurityUser;
import cn.hiauth.server.entity.App;
import cn.hiauth.server.service.AppService;
import cn.webestar.scms.commons.Assert;
import cn.webestar.scms.commons.R;
import cn.webestar.scms.commons.SysCode;
import cn.webestar.scms.commons.api.PageVO;
import cn.webestar.scms.security.SessionContextHolder;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
@Validated
@ResourceApi
@RestController
@RequestMapping("/api/common/appMgr")
public class AppMgrController {

    @Autowired
    private AppService appService;

    @PostMapping("/page")
    public R<PageVO<App>> page(@RequestBody @Valid AppPageDto dto) {
        MySecurityUser user = (MySecurityUser) SessionContextHolder.getPrincipal();
        Assert.notNull(user, SysCode.biz(1), "请先登录");
        if (!user.getIsSysAdmin()) {
            dto.setCid(user.getCid());
        }
        Page<App> p = new Page<>(dto.getPageNum(), dto.getPageSize(), true);
        IPage<App> page = appService.page(p, dto.toQueryWapper());
        return R.success(new PageVO<>(page.getCurrent(), page.getSize(), page.getTotal(), page.getRecords()));
    }

    @PostMapping("/findById")
    public R<App> findById(@RequestParam("id") Long id) {
        App o = appService.getById(id);
        return R.success(o);
    }

    @PostMapping("/create")
    public R<App> create(@RequestBody @Valid AppCreateDto dto) {
        MySecurityUser user = (MySecurityUser) SessionContextHolder.getPrincipal();
        Assert.notNull(user, SysCode.biz(1), "请先登录");
        dto.setCid(user.getCid());
        App o = dto.toDO();
        appService.save(o);
        return R.success(o);
    }

    @PostMapping("/update")
    public R<App> update(@RequestBody @Valid AppUpdateDto body) {
        App o = body.toDO();
        appService.updateById(o);
        return R.success(o);
    }

    @PostMapping("/delete")
    public R<Boolean> delete(@RequestBody Map<String, Set<Long>> map) {
        Set<Long> ids = map.get("ids");
        boolean b = appService.removeByIds(ids);
        return R.success(b);
    }

    @PostMapping("/limitList")
    public R<List<App>> limitList(@RequestBody @Valid AppLimitDto dto) {
        List<App> list = appService.list(dto.toQueryWapper());
        return R.success(list);
    }

}
