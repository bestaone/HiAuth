package cn.hiauth.server.service;

import cn.hiauth.server.api.vo.CommonTreeNodeVo;
import cn.hiauth.server.entity.Department;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Set;

/**
 * 部门
 */
public interface DepartmentService extends IService<Department> {

    List<CommonTreeNodeVo> findDepTree(Long cid);

    Set<Long> findDepIdsByEmpId(Long cid, Long empId);

}
