package com.bestaone.aiwan.user.web.controller;

import com.bestaone.aiwan.api.user.ResourceApi;
import com.bestaone.aiwan.api.user.vo.ResourceVo;
import com.bestaone.aiwan.api.user.vo.RoleVo;
import com.bestaone.aiwan.common.api.ApiResponse;
import com.bestaone.aiwan.common.api.PageVo;
import com.bestaone.aiwan.user.domain.Resource;
import com.bestaone.aiwan.user.domain.Role;
import com.bestaone.aiwan.user.service.ResourceService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
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
@RequestMapping("/api/resource")
public class ResourceController implements ResourceApi {

	@Autowired
	ResourceService resourceService;

	@Override
	@GetMapping
	public ApiResponse<PageVo<ResourceVo>> query(Integer pageNum, Integer pageSize) {
		Page pageinfo = PageHelper.startPage(pageNum, pageSize);
		List<Resource> resources = resourceService.findAll();
		List<ResourceVo> resourceVos = new ArrayList<>();
		for(Resource r : resources){
			ResourceVo vo = new ResourceVo();
			vo.setId(r.getId());
			vo.setCode(r.getCode());
			vo.setDomain(r.getDomain().name());
			vo.setName(r.getName());
			vo.setOperate(r.getOperate().name());
			resourceVos.add(vo);
		}
		return ApiResponse.sucess(new PageVo<>(pageinfo.getPageNum(), pageinfo.getPageSize(),pageinfo.getTotal(),pageinfo.getPages(),resourceVos));
	}

	@Override
	@GetMapping("/get_resource_by_role_id/{roleId:\\d+}")
	public ApiResponse<List<ResourceVo>> getResourceByRoleId(@PathVariable Long roleId) {
		List<Resource> resources = resourceService.findByRoleId(roleId);
		List<ResourceVo> resourceVos = new ArrayList<>();
		for(Resource r : resources){
			ResourceVo vo = new ResourceVo();
			vo.setId(r.getId());
			vo.setCode(r.getCode());
			vo.setDomain(r.getDomain().name());
			vo.setName(r.getName());
			vo.setOperate(r.getOperate().name());
			resourceVos.add(vo);
		}
		return ApiResponse.sucess(resourceVos);
	}


}
