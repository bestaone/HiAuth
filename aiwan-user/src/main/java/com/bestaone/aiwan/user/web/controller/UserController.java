package com.bestaone.aiwan.user.web.controller;

import com.bestaone.aiwan.user.api.UserApi;
import com.bestaone.aiwan.user.api.dto.UserDto;
import com.bestaone.aiwan.user.api.vo.ApiResponse;
import com.bestaone.aiwan.user.api.vo.Page;
import com.bestaone.aiwan.user.api.vo.UserVo;
import com.bestaone.aiwan.user.domain.User;
import com.bestaone.aiwan.user.domain.enums.Gender;
import com.bestaone.aiwan.user.exception.Assert;
import com.bestaone.aiwan.user.exception.CommonException;
import com.bestaone.aiwan.user.exception.DataNotExistException;
import com.bestaone.aiwan.user.service.AccountService;
import com.bestaone.aiwan.user.service.UserService;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
	public ApiResponse<String> create(@Valid @RequestBody UserDto userDto) {
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
	public ApiResponse<Page<UserVo>> query(Pageable pageable, UserDto userDto) {
		com.github.pagehelper.Page pageinfo = PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize());
		List<User> users = userService.findByName(userDto.getName());
		List<UserVo> userVos = new ArrayList<>();
		for(User user : users){
			UserVo vo = new UserVo();
			vo.setUsername(user.getUsername());
			vo.setId(user.getId());
			vo.setGender(user.getGender()!=null?user.getGender().name():Gender.UNKNOWN.name());
			vo.setName(user.getName());
			userVos.add(vo);
		}
		return ApiResponse.sucess(new Page<>(pageinfo.getPageNum(), pageinfo.getPageSize(),pageinfo.getTotal(),pageinfo.getPages(),userVos));
	}

	@Override
	@GetMapping("/{id:\\d+}")
	public ApiResponse<UserVo> getInfo(@PathVariable Long id) {
		try {
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
		}catch (CommonException ex){
			logger.debug(ex.getMessage(),ex);
			return ApiResponse.fail(ex);
		}catch (Exception ex){
			logger.debug(ex.getMessage(),ex);
			return ApiResponse.fail(ex.getMessage());
		}
	}

}
