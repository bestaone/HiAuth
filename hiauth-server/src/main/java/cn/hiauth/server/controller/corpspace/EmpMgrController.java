package cn.hiauth.server.controller.corpspace;

import cn.hiauth.server.api.dto.emp.EmpCreateDto;
import cn.hiauth.server.api.dto.emp.EmpPageDto;
import cn.hiauth.server.api.dto.emp.EmpUpdateDto;
import cn.hiauth.server.api.dto.user.UserLimitDto;
import cn.hiauth.server.api.vo.EmpVo;
import cn.hiauth.server.config.rest.ResourceApi;
import cn.hiauth.server.entity.Employee;
import cn.hiauth.server.entity.Role;
import cn.hiauth.server.entity.User;
import cn.hiauth.server.service.DepartmentService;
import cn.hiauth.server.service.EmployeeService;
import cn.hiauth.server.service.RoleService;
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
import java.util.stream.Collectors;

@Slf4j
@Validated
@ResourceApi
@RestController
@RequestMapping("/api/corpSpace/empMgr")
public class EmpMgrController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @PostMapping("/page")
    public R<PageVO<EmpVo>> page(@RequestBody @Valid EmpPageDto dto) {
        Long cid = SessionContextHolder.getPrincipal().getCid();
        Assert.notNull(cid, SysCode.biz(1), "未登录租户空间");
        dto.setCid(cid);
        Page<Employee> p = new Page<>(dto.getPageNum(), dto.getPageSize(), true);
        LambdaQueryWrapper<Employee> qw = dto.toQueryWapper();
        qw.orderByDesc(Employee::getCreateTime);
        IPage<Employee> page = employeeService.pageByDepId(p, qw, dto.getDepIds());
        return R.success(EmpVo.toPageVo(page));
    }

    @PostMapping("/findById")
    public R<EmpVo> findById(@RequestParam("id") Long id) {
        Long cid = SessionContextHolder.getPrincipal().getCid();
        Assert.notNull(cid, SysCode.biz(1), "未登录租户空间");
        Employee o = employeeService.getById(id);
        Set<Long> depIds = departmentService.findDepIdsByEmpId(cid, o.getId());
        List<Role> roles = roleService.findByEmpId(o.getId());
        Set<Long> roleIds = roles.stream().map(Role::getId).collect(Collectors.toSet());
        return R.success(EmpVo.convert(o, depIds, roleIds));
    }

    @PostMapping("/create")
    public R<EmpVo> create(@RequestBody @Valid EmpCreateDto dto) {
        Long cid = SessionContextHolder.getPrincipal().getCid();
        Assert.notNull(cid, SysCode.biz(1), "未登录租户空间");
        dto.setCid(cid);
        EmpVo vo = employeeService.create(dto);
        roleService.setEmpRole(cid, vo.getId(), dto.getRoleIds());
        return R.success(vo);
    }

    @PostMapping("/update")
    public R<EmpVo> update(@RequestBody @Valid EmpUpdateDto dto) {
        Long cid = SessionContextHolder.getPrincipal().getCid();
        Assert.notNull(cid, SysCode.biz(1), "未登录租户空间");
        EmpVo vo = employeeService.modify(cid, dto);
        roleService.setEmpRole(cid, vo.getId(), dto.getRoleIds());
        return R.success(vo);
    }

    @PostMapping("/delete")
    public R<Boolean> delete(@RequestBody Map<String, Set<Long>> map) {
        Long cid = SessionContextHolder.getPrincipal().getCid();
        Assert.notNull(cid, SysCode.biz(1), "未登录租户空间");
        Set<Long> ids = map.get("ids");
        boolean b = employeeService.del(cid, ids);
        return R.success(b);
    }

    @PostMapping("/findUser")
    public R<List<User>> findUser(@RequestBody @Valid UserLimitDto dto) {
        List<User> list = userService.list(dto.toQueryWapper());
        return R.success(list);
    }

}
