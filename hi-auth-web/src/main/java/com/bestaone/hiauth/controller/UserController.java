package com.bestaone.hiauth.controller;

import com.bestaone.hiauth.api.UserApi;
import com.bestaone.hiauth.api.commom.ApiResponse;
import com.bestaone.hiauth.api.commom.PageVo;
import com.bestaone.hiauth.api.dto.UserDto;
import com.bestaone.hiauth.api.vo.AuthUserInfoVo;
import com.bestaone.hiauth.api.vo.UserVo;
import com.bestaone.hiauth.core.exception.Assert;
import com.bestaone.hiauth.core.exception.CommonException;
import com.bestaone.hiauth.domain.User;
import com.bestaone.hiauth.domain.enums.Gender;
import com.bestaone.hiauth.service.AccountService;
import com.bestaone.hiauth.service.UserService;
import com.bestaone.hiauth.utils.RedisUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.*;

@RestController
@RequestMapping("/api/user")
public class UserController implements UserApi {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	AccountService accountService;

	@Autowired
	UserService userService;

	@Autowired
	RedisUtil redisUtil;

    @Override
    @GetMapping("/profile")
    public ApiResponse<AuthUserInfoVo> profile(Principal principal, Authentication auth) {

		AuthUserInfoVo authUserInfoVo = new AuthUserInfoVo();

		User user = userService.findByUsername(principal.getName());
		if(user!=null){
			authUserInfoVo.setUserId(user.getId());
		}

		authUserInfoVo.setName(user.getName());
		authUserInfoVo.setUsername(principal.getName());
		authUserInfoVo.setTel(user.getTel());
		authUserInfoVo.setLastLoginTime(user.getLastLoginTime());
		authUserInfoVo.setAvatar("https://file.iviewui.com/dist/a0e88e83800f138b94d2414621bd9704.png");
		Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) auth.getAuthorities();
		Set<String> roles = new HashSet<>();
		for(GrantedAuthority grantedAuthority : authorities){
            roles.add(grantedAuthority.getAuthority());
		}
		authUserInfoVo.setRoles(roles);
		authUserInfoVo.setIntroduction("我是" + user.getName());

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
		user.setGender(Gender.valueOf(userDto.getGender()));
		user.setTel(userDto.getTel());
		user.setCreateTime(new Date());
		userService.save(user);
		if(userDto.getRoleIds()!=null){
			userService.addUserRole(user.getId(), userDto.getRoleIds());
		}
		return ApiResponse.sucess(user.getId().toString());
	}

	@Override
	@PutMapping("/{id:\\d+}")
	public ApiResponse update(@PathVariable Long id, @Valid @RequestBody UserDto userDto, BindingResult errors) {
		User user = userService.findById(id);
		user.setPassword(userDto.getPassword());
		user.setUsername(userDto.getUsername());
		user.setName(userDto.getName());
		user.setGender(Gender.valueOf(userDto.getGender()));
		user.setTel(userDto.getTel());
		userService.save(user);
		if(userDto.getRoleIds()!=null){
			userService.updateUserRole(user.getId(), userDto.getRoleIds());
		}
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
			vo.setPassword(user.getPassword());
			vo.setId(user.getId());
			vo.setGender(user.getGender()!=null?user.getGender().name():Gender.UNKNOWN.name());
			vo.setName(user.getName());
			vo.setTel(user.getTel());
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
		vo.setTel(user.getTel());
		vo.setCreateTime(user.getCreateTime());
		vo.setGender(user.getGender()!=null?user.getGender().name():Gender.UNKNOWN.name());
		vo.setName(user.getName());
		return ApiResponse.sucess(vo);
	}

}
