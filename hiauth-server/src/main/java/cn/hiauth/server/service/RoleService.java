package cn.hiauth.server.service;

import cn.hiauth.server.api.dto.role.RoleAuthDto;
import cn.hiauth.server.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Set;

/**
 * 角色
 */
public interface RoleService extends IService<Role> {

    List<Role> findByEmpId(Long empId);

    void setEmpRole(Long cid, Long empId, Set<Long> roleIds);

    Boolean auth(RoleAuthDto dto);

}
