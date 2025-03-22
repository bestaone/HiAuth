package cn.hiauth.server.controller.adminspace;

import cn.hiauth.server.api.dto.corp.CorpCreateDto;
import cn.hiauth.server.api.dto.corp.CorpPageDto;
import cn.hiauth.server.api.dto.corp.CorpUpdateDto;
import cn.hiauth.server.api.vo.CorpVo;
import cn.hiauth.server.config.rest.ResourceApi;
import cn.hiauth.server.config.rest.security.MySecurityUser;
import cn.hiauth.server.entity.Corp;
import cn.hiauth.server.entity.Department;
import cn.hiauth.server.entity.User;
import cn.hiauth.server.service.CorpService;
import cn.hiauth.server.service.DepartmentService;
import cn.hiauth.server.service.UserService;
import cn.webestar.scms.commons.Assert;
import cn.webestar.scms.commons.R;
import cn.webestar.scms.commons.SysCode;
import cn.webestar.scms.commons.api.PageVO;
import cn.webestar.scms.security.SessionContextHolder;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
@RequestMapping("/api/adminSpace/corpMgr")
public class CorpMgrController {

    @Autowired
    private CorpService corpService;

    @Autowired
    private UserService userService;

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/page")
    public R<PageVO<CorpVo>> page(@RequestBody @Valid CorpPageDto dto) {
        Page<Corp> p = new Page<>(dto.getPageNum(), dto.getPageSize(), true);
        IPage<Corp> page = corpService.page(p, dto.toQueryWapper());
        PageVO<CorpVo> pageVo = CorpVo.toPageVo(page);
        return R.success(pageVo);
    }

    @PostMapping("/findById")
    public R<CorpVo> findById(@RequestParam("id") Long id) {
        Corp o = corpService.getById(id);
        return R.success(CorpVo.convert(o));
    }

    @PostMapping("/create")
    public R<CorpVo> create(@RequestBody @Valid CorpCreateDto body) {
        Corp o = body.toDO();
        corpService.save(o);
        Department dep = new Department();
        dep.setCid(o.getId());
        dep.setName(o.getName());
        departmentService.save(dep);
        return R.success(CorpVo.convert(o));
    }

    @PostMapping("/update")
    public R<CorpVo> update(@RequestBody @Valid CorpUpdateDto body) {
        Corp o = body.toDO();
        corpService.updateById(o);
        return R.success(CorpVo.convert(o));
    }

    @PostMapping("/delete")
    public R<Boolean> delete(@RequestBody Map<String, Set<Long>> map) {
        Set<Long> ids = map.get("ids");
        boolean b = corpService.removeByIds(ids);
        return R.success(b);
    }

    @PostMapping("/listCorp")
    public R<List<CorpVo>> listCorp() {
        LambdaQueryWrapper<Corp> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Corp::getIsDeleted, false);
        List<Corp> corps = corpService.list(wrapper);
        List<CorpVo> corpVos = CorpVo.convert(corps);
        return R.success(corpVos);
    }

    @PostMapping("/intoAdminSpace")
    public R<Boolean> intoAdminSpace() {
        MySecurityUser securityUser = (MySecurityUser) SessionContextHolder.getPrincipal();
        User u = userService.getById(securityUser.getUserId());
        Assert.isTrue(u.getIsSysAdmin() != null && u.getIsSysAdmin(), SysCode.biz(1), "您不是管理员，无权切换到管理员工作台");
        securityUser.setCid(null);
        securityUser.setIsCorpAdmin(false);
        securityUser.setIsSysAdmin(true);
        SessionContextHolder.refresh();
        return R.success(Boolean.TRUE);
    }

    @PostMapping("/intoCorpSpace")
    public R<Boolean> intoCorpSpace(@RequestParam("cid") Long cid) {
        MySecurityUser securityUser = (MySecurityUser) SessionContextHolder.getPrincipal();
        securityUser.setCid(cid);
        securityUser.setIsCorpAdmin(true);
        securityUser.setIsSysAdmin(false);
        SessionContextHolder.refresh();
        return R.success(Boolean.TRUE);
    }

}
