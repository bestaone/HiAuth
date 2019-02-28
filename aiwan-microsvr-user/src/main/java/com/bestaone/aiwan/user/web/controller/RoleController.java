package com.bestaone.aiwan.user.web.controller;

import com.bestaone.aiwan.api.user.RoleApi;
import com.bestaone.aiwan.api.user.vo.RoleVo;
import com.bestaone.aiwan.api.user.vo.UserVo;
import com.bestaone.aiwan.common.api.ApiResponse;
import com.bestaone.aiwan.common.api.PageVo;
import com.bestaone.aiwan.common.exception.CommonException;
import com.bestaone.aiwan.user.domain.Role;
import com.bestaone.aiwan.user.domain.User;
import com.bestaone.aiwan.user.domain.enums.Gender;
import com.bestaone.aiwan.user.service.RoleService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@PreAuthorize("isAuthenticated()")
@RestController
@RequestMapping("/api/role")
public class RoleController implements RoleApi {

	@Autowired
	RoleService roleService;

	@Override
	@GetMapping("/get_role_by_user_id/{userId:\\d+}")
	public ApiResponse<List<RoleVo>> getRoleByUserId(@PathVariable Long userId) {
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

	@Override
	@GetMapping
	public ApiResponse<PageVo<RoleVo>> query(Integer pageNum, Integer pageSize) {
		Page pageinfo = PageHelper.startPage(pageNum, pageSize);
		List<Role> roles = roleService.findAll();
		List<RoleVo> roleVos = new ArrayList<>();
		for(Role r : roles){
			RoleVo vo = new RoleVo();
			vo.setId(r.getId());
			vo.setCode(r.getCode());
			vo.setName(r.getName());
			roleVos.add(vo);
		}
		return ApiResponse.sucess(new PageVo<>(pageinfo.getPageNum(), pageinfo.getPageSize(),pageinfo.getTotal(),pageinfo.getPages(),roleVos));
	}

	@Override
	@PostMapping("/add_resources_by_role_id/{roleId:\\d+}")
	public ApiResponse addRoleResources(@PathVariable Long roleId, @RequestBody Map params) {
		List<Object> ids = (List<Object>) params.get("resourcesIds");
		List<Long> resourcesIds = new ArrayList<>();
		for(Object id : ids){
			resourcesIds.add(Long.parseLong(id.toString()));
		}
		roleService.addRoleResources(roleId, resourcesIds);
		return ApiResponse.sucess();
	}

	@Override
	@DeleteMapping("/remove_resources_by_role_id/{roleId:\\d+}")
	public ApiResponse removeRoleResources(@PathVariable Long roleId, @RequestBody Map params) {
		List<Object> ids = (List<Object>) params.get("resourcesIds");
		List<Long> resourcesIds = new ArrayList<>();
		for(Object id : ids){
			resourcesIds.add(Long.parseLong(id.toString()));
		}
		roleService.removeRoleResources(roleId, resourcesIds);
		return ApiResponse.sucess();
	}

}
