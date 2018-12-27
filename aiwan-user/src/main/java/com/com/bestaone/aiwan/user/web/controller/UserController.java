package com.com.bestaone.aiwan.user.web.controller;

import com.com.bestaone.aiwan.user.api.dto.UserDto;
import com.com.bestaone.aiwan.user.api.vo.UserVo;
import com.com.bestaone.aiwan.user.exception.UserNotExistException;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.social.connect.web.ProviderSignInUtils;

@RestController
@RequestMapping("/user")
public class UserController {

	@PostMapping
	@ApiOperation(value = "创建用户")
	public UserVo create(@Valid @RequestBody UserVo user) {

		System.out.println(user.getId());
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getBirthday());

		user.setId("1");
		return user;
	}

	@PutMapping("/{id:\\d+}")
	public UserVo update(@Valid @RequestBody UserVo user, BindingResult errors) {

		System.out.println(user.getId());
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getBirthday());

		user.setId("1");
		return user;
	}

	@DeleteMapping("/{id:\\d+}")
	public void delete(@PathVariable String id) {
		System.out.println(id);
	}

	@GetMapping
	@JsonView(UserVo.UserSimpleView.class)
	@ApiOperation(value = "用户查询服务")
	public List<UserVo> query(UserDto condition,
							  @PageableDefault(page = 2, size = 17, sort = "username,asc") Pageable pageable) {

		System.out.println(ReflectionToStringBuilder.toString(condition, ToStringStyle.MULTI_LINE_STYLE));

		System.out.println(pageable.getPageSize());
		System.out.println(pageable.getPageNumber());
		System.out.println(pageable.getSort());

		List<UserVo> users = new ArrayList<>();
		users.add(new UserVo());
		users.add(new UserVo());
		users.add(new UserVo());
		return users;
	}

	@GetMapping("/{id:\\d+}")
	@JsonView(UserVo.UserDetailView.class)
	public UserVo getInfo(@ApiParam("用户id") @PathVariable String id) {
		if(id.endsWith("0")){
			throw new UserNotExistException("user not exist");
		}
		System.out.println("进入getInfo服务");
		UserVo user = new UserVo();
		user.setUsername("tom");
		return user;
	}

}
