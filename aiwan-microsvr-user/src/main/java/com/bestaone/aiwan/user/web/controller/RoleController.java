package com.bestaone.aiwan.user.web.controller;

import com.bestaone.aiwan.api.user.RoleApi;
import com.bestaone.aiwan.api.user.vo.RoleVo;
import com.bestaone.aiwan.common.api.ApiResponse;
import com.bestaone.aiwan.common.exception.CommonException;
import com.bestaone.aiwan.user.domain.Role;
import com.bestaone.aiwan.user.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@PreAuthorize("isAuthenticated()")
@RestController
@RequestMapping("/api/role")
public class RoleController implements RoleApi {

	@Autowired
	RoleService roleService;

	@Override
	@GetMapping("/get_role_by_user_id/{userId:\\d+}")
	public ApiResponse<List<RoleVo>> getRoleByUserId(@PathVariable Long userId) throws CommonException {
		List<Role> roles = roleService.findByUserId(userId);
		List<RoleVo> roleVos = new ArrayList();
		for(Role r : roles){
			RoleVo vo = new RoleVo();
			vo.setId(r.getId());
			vo.setCode(r.getCode());
			vo.setName(r.getName());
			roleVos.add(vo);
		}
		return ApiResponse.sucess(roleVos);
	}

}
