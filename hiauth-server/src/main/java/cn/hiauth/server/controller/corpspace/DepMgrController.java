package cn.hiauth.server.controller.corpspace;

import cn.hiauth.server.api.dto.dep.DepCreateDto;
import cn.hiauth.server.api.dto.dep.DepLimitDto;
import cn.hiauth.server.api.dto.dep.DepPageDto;
import cn.hiauth.server.api.dto.dep.DepUpdateDto;
import cn.hiauth.server.api.vo.CommonTreeNodeVo;
import cn.hiauth.server.config.rest.ResourceApi;
import cn.hiauth.server.entity.Department;
import cn.hiauth.server.service.DepartmentService;
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

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
@Validated
@ResourceApi
@RestController
@RequestMapping("/api/corpSpace/depMgr")
public class DepMgrController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/page")
    public R<PageVO<Department>> page(@RequestBody @Valid DepPageDto dto) {
        Long cid = SessionContextHolder.getPrincipal().getCid();
        Assert.notNull(cid, SysCode.biz(1), "未登录租户空间");
        dto.setCid(cid);
        Page<Department> p = new Page<>(dto.getPageNum(), dto.getPageSize(), true);
        IPage<Department> page = departmentService.page(p, dto.toQueryWapper());
        page.getRecords().sort(Comparator.comparing(Department::getSort));
        return R.success(new PageVO<>(page.getCurrent(), page.getSize(), page.getTotal(), page.getRecords()));
    }

    @PostMapping("/limit")
    public R<List<Department>> limit(@RequestBody @Valid DepLimitDto dto) {
        Long cid = SessionContextHolder.getPrincipal().getCid();
        Assert.notNull(cid, SysCode.biz(1), "未登录租户空间");
        dto.setCid(cid);
        List<Department> list = departmentService.list(dto.toQueryWapper());
        return R.success(list);
    }

    @PostMapping("/findById")
    public R<Department> findById(@RequestParam("id") Long id) {
        Long cid = SessionContextHolder.getPrincipal().getCid();
        Assert.notNull(cid, SysCode.biz(1), "未登录租户空间");
        Department o = departmentService.getById(id);
        return R.success(o);
    }

    @PostMapping("/create")
    public R<Department> create(@RequestBody @Valid DepCreateDto dto) {
        Long cid = SessionContextHolder.getPrincipal().getCid();
        Assert.notNull(cid, SysCode.biz(1), "未登录租户空间");
        dto.setCid(cid);
        Department o = dto.toDO();
        departmentService.save(o);
        return R.success(o);
    }

    @PostMapping("/update")
    public R<Department> update(@RequestBody @Valid DepUpdateDto body) {
        Long cid = SessionContextHolder.getPrincipal().getCid();
        Assert.notNull(cid, SysCode.biz(1), "未登录租户空间");
        Department o = body.toDO();
        departmentService.updateById(o);
        return R.success(o);
    }

    @PostMapping("/delete")
    public R<Boolean> delete(@RequestBody Map<String, Set<Long>> map) {
        Long cid = SessionContextHolder.getPrincipal().getCid();
        Assert.notNull(cid, SysCode.biz(1), "未登录租户空间");
        Set<Long> ids = map.get("ids");
        boolean b = departmentService.removeByIds(ids);
        return R.success(b);
    }

    @PostMapping("/depTree")
    public R<List<CommonTreeNodeVo>> depTree() {
        Long cid = SessionContextHolder.getPrincipal().getCid();
        Assert.notNull(cid, SysCode.biz(1), "未登录租户空间");
        List<CommonTreeNodeVo> tree = departmentService.findDepTree(cid);
        return R.success(tree);
    }

}
