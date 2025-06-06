package cn.hiauth.server.service;

import cn.hiauth.server.api.dto.emp.EmpCreateDto;
import cn.hiauth.server.api.dto.emp.EmpUpdateDto;
import cn.hiauth.server.api.vo.EmpVo;
import cn.hiauth.server.entity.Employee;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Set;

/**
 * 员工
 */
public interface EmployeeService extends IService<Employee> {

    IPage<Employee> pageByDepId(Page<Employee> p, LambdaQueryWrapper<Employee> queryWapper, Set<Long> depIds);

    Employee lastLoginCorpAdminByUserId(Long userId);

    EmpVo create(EmpCreateDto dto);

    EmpVo modify(Long cid, EmpUpdateDto dto);

    boolean del(Long cid, Set<Long> ids);

    void swichCorp(Long userId, Long cid);

}
