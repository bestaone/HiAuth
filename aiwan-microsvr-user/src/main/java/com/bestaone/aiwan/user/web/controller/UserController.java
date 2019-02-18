package com.bestaone.aiwan.user.web.controller;

import com.bestaone.aiwan.api.user.UserApi;
import com.bestaone.aiwan.api.user.dto.UserDto;
import com.bestaone.aiwan.api.user.vo.AuthUserInfoVo;
import com.bestaone.aiwan.api.user.vo.UserVo;
import com.bestaone.aiwan.common.api.ApiResponse;
import com.bestaone.aiwan.common.api.PageVo;
import com.bestaone.aiwan.common.exception.CommonException;
import com.bestaone.aiwan.core.exception.Assert;
import com.bestaone.aiwan.user.domain.User;
import com.bestaone.aiwan.user.domain.enums.Gender;
import com.bestaone.aiwan.user.service.AccountService;
import com.bestaone.aiwan.user.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.*;

@PreAuthorize("isAuthenticated()")
@RestController
@RequestMapping("/api/user")
public class UserController implements UserApi {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	AccountService accountService;

	@Autowired
	UserService userService;

	@Override
	@GetMapping("/profile")
	public ApiResponse<Map<String, String>> profile() {
		Map<String, String> map = new HashMap<>();
		map.put("id","10001");
		map.put("name","bestaone");
		map.put("email","117919482@qq.com");
		return ApiResponse.sucess(map);
	}

    @Override
    @GetMapping("/get_user_info")
    public ApiResponse<AuthUserInfoVo> getUserInfo(Principal principal, Authentication auth) {

		AuthUserInfoVo authUserInfoVo = new AuthUserInfoVo();

		User user = userService.findByUsername(principal.getName());
		if(user!=null){
			authUserInfoVo.setUserId(user.getId());
		}

		authUserInfoVo.setUsername(principal.getName());
		authUserInfoVo.setAvator("https://file.iviewui.com/dist/a0e88e83800f138b94d2414621bd9704.png");
		Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) auth.getAuthorities();
		Set<String> access = new HashSet<>();
		for(GrantedAuthority grantedAuthority : authorities){
			access.add(grantedAuthority.getAuthority());
		}
		access.add("super_admin");
		access.add("admin");
		authUserInfoVo.setAccess(access);

        return ApiResponse.sucess(authUserInfoVo);
    }

	@Override
	@PostMapping
	public ApiResponse<String> create(@Valid @RequestBody UserDto userDto) throws CommonException {
		Assert.notNull(userDto.getUsername(),50000, "用户名不存在");
		Assert.notNull(userDto.getPassword(),50000, "密码不存在");
		User user = new User();
		user.setPassword(userDto.getPassword());
		user.setUsername(userDto.getUsername());
		user.setName(userDto.getName());
		user.setGender(Gender.UNKNOWN);
		userService.save(user);
		return ApiResponse.sucess(user.getId().toString());
	}

	@Override
	@PutMapping("/{id:\\d+}")
	public ApiResponse update(@PathVariable Long id, @Valid @RequestBody UserDto userDto, BindingResult errors) {
		User user = userService.findById(id);
		user.setPassword(userDto.getPassword());
		user.setUsername(userDto.getUsername());
		user.setName(userDto.getName());
		user.setGender(Gender.UNKNOWN);
		userService.save(user);
		return ApiResponse.sucess();
	}

	@Override
	@DeleteMapping("/{id:\\d+}")
	public ApiResponse delete(@PathVariable Long id) {
		userService.delete(id);
		return ApiResponse.sucess();
	}

	@Override
	@GetMapping
	public ApiResponse<PageVo<UserVo>> query(Integer pageNum, Integer pageSize, String name) {
		Page pageinfo = PageHelper.startPage(pageNum, pageSize);
		List<User> users = userService.findByName(name);
		List<UserVo> userVos = new ArrayList<>();
		for(User user : users){
			UserVo vo = new UserVo();
			vo.setUsername(user.getUsername());
			vo.setId(user.getId());
			vo.setGender(user.getGender()!=null?user.getGender().name():Gender.UNKNOWN.name());
			vo.setName(user.getName());
			vo.setCreateTime(user.getCreateTime());
			userVos.add(vo);
		}
		return ApiResponse.sucess(new PageVo<>(pageinfo.getPageNum(), pageinfo.getPageSize(),pageinfo.getTotal(),pageinfo.getPages(),userVos));
	}

	@Override
	@GetMapping("/{id:\\d+}")
	public ApiResponse<UserVo> getInfo(@PathVariable Long id) throws CommonException{
		User user = userService.findById(id);
		Assert.notNull(user,20001,"数据不存");
		UserVo vo = new UserVo();
		vo.setUsername(user.getUsername());
		vo.setId(user.getId());
		vo.setPassword(user.getPassword());
		vo.setCreateTime(user.getCreateTime());
		vo.setGender(user.getGender()!=null?user.getGender().name():Gender.UNKNOWN.name());
		vo.setName(user.getName());
		return ApiResponse.sucess(vo);
	}

}
