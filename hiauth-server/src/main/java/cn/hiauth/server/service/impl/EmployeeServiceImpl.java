package cn.hiauth.server.service.impl;

import cn.hiauth.server.api.dto.emp.EmpCreateDto;
import cn.hiauth.server.api.dto.emp.EmpUpdateDto;
import cn.hiauth.server.api.vo.EmpVo;
import cn.hiauth.server.entity.Employee;
import cn.hiauth.server.mapper.DepartmentMapper;
import cn.hiauth.server.mapper.EmployeeMapper;
import cn.hiauth.server.service.EmployeeService;
import cn.hutool.core.lang.Snowflake;
import cn.webestar.scms.commons.Assert;
import cn.webestar.scms.commons.SysCode;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 员工
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private Snowflake idGenerator;

    @Override
    public IPage<Employee> pageByDepId(Page<Employee> p, LambdaQueryWrapper<Employee> queryWapper, Set<Long> depIds) {
        return baseMapper.pageByDepId(p, queryWapper, depIds);
    }

    @Override
    public Employee lastLoginCorpAdminByUserId(Long userId) {
        Assert.notNull(userId, SysCode.biz(1), "userId不能为空");
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUserId, userId);
        queryWrapper.eq(Employee::getIsCorpAdmin, true);
        queryWrapper.last("OFFSET 0 LIMIT 1");
        List<Employee> emps = this.list(queryWrapper);
        return emps != null && !emps.isEmpty() ? emps.get(0) : null;
    }

    @Override
    @Transactional
    public EmpVo create(EmpCreateDto dto) {
        // 保存员工信息
        Employee o = dto.toDO();
        this.save(o);
        // 保存部门信息
        if (dto.getDepIds() != null && !dto.getDepIds().isEmpty()) {
            List<Map<String, Object>> depEmps = new ArrayList<>();
            dto.getDepIds().forEach(item -> {
                Map<String, Object> map = new HashMap<>();
                map.put("id", idGenerator.nextId());
                map.put("cid", dto.getCid());
                map.put("empId", o.getId());
                map.put("depId", item);
                depEmps.add(map);
            });
            departmentMapper.addDepEmp(depEmps);
        }
        return EmpVo.convert(o, dto.getDepIds(), null);
    }

    @Override
    public EmpVo modify(Long cid, EmpUpdateDto dto) {
        Employee o = dto.toDO();
        this.updateById(o);
        // 更新部门
        if (dto.getDepIds() != null && !dto.getDepIds().isEmpty()) {
            List<Map<String, Object>> depEmps = new ArrayList<>();
            dto.getDepIds().forEach(item -> {
                Map<String, Object> map = new HashMap<>();
                map.put("id", idGenerator.nextId());
                map.put("cid", cid);
                map.put("empId", o.getId());
                map.put("depId", item);
                depEmps.add(map);
            });
            departmentMapper.resetEmpDep(cid, o.getId(), dto.getDepIds());
            departmentMapper.modifyDepEmp(depEmps);
        }
        return EmpVo.convert(o, dto.getDepIds(), null);
    }

    @Override
    public boolean del(Long cid, Set<Long> ids) {
        boolean b = this.removeByIds(ids);
        departmentMapper.del(cid, ids);
        return b;
    }

}