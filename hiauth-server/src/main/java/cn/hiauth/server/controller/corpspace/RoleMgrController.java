package cn.hiauth.server.controller.corpspace;

import cn.hiauth.server.api.dto.role.*;
import cn.hiauth.server.config.rest.ResourceApi;
import cn.hiauth.server.entity.Role;
import cn.hiauth.server.service.RoleService;
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
@RequestMapping("/api/corpSpace/roleMgr")
public class RoleMgrController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/page")
    public R<PageVO<Role>> page(@RequestBody @Valid RolePageDto dto) {
        Long cid = SessionContextHolder.getPrincipal().getCid();
        Assert.notNull(cid, SysCode.biz(1), "未登录租户空间");
        dto.setCid(cid);
        Page<Role> p = new Page<>(dto.getPageNum(), dto.getPageSize(), true);
        IPage<Role> page = roleService.page(p, dto.toQueryWapper());
        return R.success(new PageVO<>(page.getCurrent(), page.getSize(), page.getTotal(), page.getRecords()));
    }

    @PostMapping("/limit")
    public R<List<Role>> page(@RequestBody @Valid RoleLimitDto dto) {
        Long cid = SessionContextHolder.getPrincipal().getCid();
        Assert.notNull(cid, SysCode.biz(1), "未登录租户空间");
        dto.setCid(cid);
        List<Role> list = roleService.list(dto.toQueryWapper());
        return R.success(list);
    }

    @PostMapping("/findById")
    public R<Role> findById(@RequestParam("id") Long id) {
        Long cid = SessionContextHolder.getPrincipal().getCid();
        Assert.notNull(cid, SysCode.biz(1), "未登录租户空间");
        Role o = roleService.getById(id);
        return R.success(o);
    }

    @PostMapping("/create")
    public R<Role> create(@RequestBody @Valid RoleCreateDto body) {
        Long cid = SessionContextHolder.getPrincipal().getCid();
        Assert.notNull(cid, SysCode.biz(1), "未登录租户空间");
        Role o = body.toDO();
        o.setCid(cid);
        roleService.save(o);
        return R.success(o);
    }

    @PostMapping("/update")
    public R<Role> update(@RequestBody @Valid RoleUpdateDto body) {
        Long cid = SessionContextHolder.getPrincipal().getCid();
        Assert.notNull(cid, SysCode.biz(1), "未登录租户空间");
        Role o = body.toDO();
        roleService.updateById(o);
        return R.success(o);
    }

    @PostMapping("/delete")
    public R<Boolean> delete(@RequestBody Map<String, Set<Long>> map) {
        Long cid = SessionContextHolder.getPrincipal().getCid();
        Assert.notNull(cid, SysCode.biz(1), "未登录租户空间");
        Set<Long> ids = map.get("ids");
        boolean b = roleService.removeByIds(ids);
        return R.success(b);
    }

    @PostMapping("/auth")
    public R<Boolean> auth(@RequestBody @Valid RoleAuthDto dto) {
        Long cid = SessionContextHolder.getPrincipal().getCid();
        Assert.notNull(cid, SysCode.biz(1), "未登录租户空间");
        roleService.auth(dto);
        return R.success(true);
    }

}
