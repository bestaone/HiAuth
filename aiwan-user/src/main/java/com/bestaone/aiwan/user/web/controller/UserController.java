package com.bestaone.aiwan.user.web.controller;

import com.bestaone.aiwan.user.api.UserApi;
import com.bestaone.aiwan.user.api.dto.UserDto;
import com.bestaone.aiwan.user.api.vo.PageVo;
import com.bestaone.aiwan.user.api.vo.UserVo;
import com.bestaone.aiwan.user.domain.User;
import com.bestaone.aiwan.user.domain.enums.Gender;
import com.bestaone.aiwan.user.service.AccountService;
import com.bestaone.aiwan.user.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
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

	@Autowired
	AccountService accountService;

	@Autowired
	UserService userService;

	@Override
	@PostMapping
	public String create(@Valid @RequestBody UserDto userDto) {
		User user = new User();
		user.setPassword(userDto.getPassword());
		user.setUsername(userDto.getUsername());
		user.setName(userDto.getName());
		user.setGender(Gender.UNKNOWN);
		userService.save(user);
		return user.getId().toString();
	}

	@Override
	@PutMapping("/{id:\\d+}")
	public void update(@PathVariable Long id, @Valid @RequestBody UserDto userDto, BindingResult errors) {
		User user = userService.findById(id);
		user.setPassword(userDto.getPassword());
		user.setUsername(userDto.getUsername());
		user.setName(userDto.getName());
		user.setGender(Gender.UNKNOWN);
		userService.save(user);
	}

	@Override
	@DeleteMapping("/{id:\\d+}")
	public void delete(@PathVariable Long id) {
		userService.delete(id);
	}

	@Override
	@GetMapping
	public PageVo<UserVo> query(Pageable pageable, UserDto userDto) {
		Page<User> pageinfo = PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize());
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
		return new PageVo<>(pageinfo.getPageNum(), pageinfo.getPageSize(),pageinfo.getTotal(),pageinfo.getPages(),userVos);
	}

	@Override
	@GetMapping("/{id:\\d+}")
	public UserVo getInfo(@PathVariable Long id) {
		User user = userService.findById(id);
		UserVo vo = new UserVo();
		vo.setUsername(user.getUsername());
		vo.setId(user.getId());
		vo.setPassword(user.getPassword());
		vo.setCreateTime(user.getCreateTime());
		vo.setGender(user.getGender()!=null?user.getGender().name():Gender.UNKNOWN.name());
		vo.setName(user.getName());
		return vo;
	}

}
