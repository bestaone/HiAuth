package com.bestaone.aiwan.user.web.controller;

import com.bestaone.aiwan.api.user.AppApi;
import com.bestaone.aiwan.api.user.dto.AppDto;
import com.bestaone.aiwan.api.user.vo.AppVo;
import com.bestaone.aiwan.common.api.ApiResponse;
import com.bestaone.aiwan.common.api.PageVo;
import com.bestaone.aiwan.common.exception.CommonException;
import com.bestaone.aiwan.core.exception.Assert;
import com.bestaone.aiwan.user.domain.App;
import com.bestaone.aiwan.user.service.AppService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@PreAuthorize("isAuthenticated()")
@RestController
@RequestMapping("/api/app")
public class AppController implements AppApi {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	PasswordEncoder passwordEncoder;

	@Autowired
	AppService appService;

	@Override
	@PostMapping
	public ApiResponse<String> create(@Valid @RequestBody AppDto appDto) throws CommonException {
		Assert.notNull(appDto.getName(),50000, "APP名不存在");
		App app = new App();
		app.setClientId(RandomStringUtils.randomAlphanumeric(10));
		app.setName(appDto.getName());
		app.setImg1X(appDto.getImg1X());
		app.setCreateTime(new Date());
		app.setClientSecret(RandomStringUtils.randomAlphanumeric(10));
		if(appDto.getScopes()!=null){
			app.setScope(StringUtils.join(appDto.getScopes().toString(), ","));
		}
		if(appDto.getAuthorizedGrantTypes()!=null){
			app.setAuthorizedGrantTypes(StringUtils.join(appDto.getAuthorizedGrantTypes(), ","));
		}
		if(appDto.getWebServerRedirectUris()!=null){
			app.setWebServerRedirectUri(StringUtils.join(appDto.getWebServerRedirectUris(), ","));
		}
		app.setAuthorities(null);
		app.setAccessTokenValidity(1800);
		app.setRefreshTokenValidity(86400);
		app.setAdditionalInformation(null);
		app.setAutoapprove("false");
		appService.save(app);
		return ApiResponse.sucess(app.getId().toString());
	}

	@Override
	@PutMapping("/{id:\\d+}")
	public ApiResponse update(@PathVariable Long id, @Valid @RequestBody AppDto appDto, BindingResult errors) {
		App app = appService.findById(id);
		if(appDto.getClientId()!=null){
			app.setClientId(appDto.getClientId());
		}
		if(appDto.getName()!=null){
			app.setName(appDto.getName());
		}
		if(appDto.getClientSecret()!=null){
			app.setClientSecret(appDto.getClientSecret());
		}
		if(appDto.getImg1X()!=null){
			app.setImg1X(appDto.getImg1X());
		}
		if(appDto.getScopes()!=null){
			app.setScope(StringUtils.join(appDto.getScopes().toString(), ","));
		}
		if(appDto.getAuthorizedGrantTypes()!=null){
			app.setAuthorizedGrantTypes(StringUtils.join(appDto.getAuthorizedGrantTypes(), ","));
		}
		if(appDto.getWebServerRedirectUris()!=null){
			app.setWebServerRedirectUri(StringUtils.join(appDto.getWebServerRedirectUris(), ","));
		}
		appService.save(app);
		return ApiResponse.sucess();
	}

	@Override
	@DeleteMapping("/{id:\\d+}")
	public ApiResponse delete(@PathVariable Long id) {
		appService.delete(id);
		return ApiResponse.sucess();
	}

	@Override
	@GetMapping
	public ApiResponse<PageVo<AppVo>> query(Integer pageNum, Integer pageSize, String name, String clientId) {
		Page pageinfo = PageHelper.startPage(pageNum, pageSize);
		List<App> apps = appService.findByNameOrClientId(name,clientId);
		List<AppVo> appVos = new ArrayList<>();
		for(App app : apps){
			AppVo vo = new AppVo();
			vo.setId(app.getId());
			vo.setName(app.getName());
			vo.setImg1X(app.getImg1X());
			vo.setCreateTime(app.getCreateTime());
			vo.setClientId(app.getClientId());
			vo.setClientSecret(app.getClientSecret());
			if(app.getScope()!=null){
				vo.setScope(Arrays.asList(app.getScope().split(",")));
			}
			if(app.getAuthorizedGrantTypes()!=null){
				vo.setAuthorizedGrantTypes(Arrays.asList(app.getAuthorizedGrantTypes().split(",")));
			}
			if(app.getWebServerRedirectUri()!=null){
				vo.setWebServerRedirectUri(Arrays.asList(app.getWebServerRedirectUri().split(",")));
			}
			appVos.add(vo);
		}
		return ApiResponse.sucess(new PageVo<>(pageinfo.getPageNum(), pageinfo.getPageSize(),pageinfo.getTotal(),pageinfo.getPages(),appVos));
	}

	@Override
	@GetMapping("/{id:\\d+}")
	public ApiResponse<AppVo> getInfo(@PathVariable Long id) throws CommonException {
		App app = appService.findById(id);
		Assert.notNull(app,20001,"数据不存");
		AppVo vo = new AppVo();
		vo.setId(app.getId());
		vo.setName(app.getName());
		vo.setImg1X(app.getImg1X());
		vo.setCreateTime(app.getCreateTime());
		vo.setClientId(app.getClientId());
		vo.setClientSecret(app.getClientSecret());
		if(app.getScope()!=null){
			vo.setScope(Arrays.asList(app.getScope().split(",")));
		}
		if(app.getAuthorizedGrantTypes()!=null){
			vo.setAuthorizedGrantTypes(Arrays.asList(app.getAuthorizedGrantTypes().split(",")));
		}
		if(app.getWebServerRedirectUri()!=null){
			vo.setWebServerRedirectUri(Arrays.asList(app.getWebServerRedirectUri().split(",")));
		}
		return ApiResponse.sucess(vo);
	}

}
