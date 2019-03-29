package com.bestaone.hiauth.controller;

import com.bestaone.hiauth.api.RoleApi;
import com.bestaone.hiauth.api.commom.ApiResponse;
import com.bestaone.hiauth.api.commom.PageVo;
import com.bestaone.hiauth.api.vo.RoleVo;
import com.bestaone.hiauth.domain.Role;
import com.bestaone.hiauth.service.RoleService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
