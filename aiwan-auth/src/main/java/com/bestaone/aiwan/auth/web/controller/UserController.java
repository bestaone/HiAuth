package com.bestaone.aiwan.auth.web.controller;

import com.bestaone.aiwan.auth.api.UserApi;
import com.bestaone.aiwan.auth.api.dto.UserDto;
import com.bestaone.aiwan.auth.api.vo.UserVo;
import com.bestaone.aiwan.auth.domain.User;
import com.bestaone.aiwan.auth.domain.enums.Gender;
import com.bestaone.aiwan.auth.service.AccountService;
import com.bestaone.aiwan.auth.service.UserService;
import com.bestaone.aiwan.core.api.ApiResponse;
import com.bestaone.aiwan.core.api.PageVo;
import com.bestaone.aiwan.core.exception.Assert;
import com.bestaone.aiwan.core.exception.CommonException;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController implements UserApi {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	AccountService accountService;

	@Autowired
	UserService userService;

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
