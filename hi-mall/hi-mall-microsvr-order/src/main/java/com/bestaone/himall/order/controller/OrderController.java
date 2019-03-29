package com.bestaone.himall.order.controller;

import com.bestaone.himall.api.order.OrderApi;
import com.bestaone.himall.api.order.dto.OrderDto;
import com.bestaone.himall.common.api.ApiResponse;
import com.bestaone.himall.common.api.PageVo;
import com.bestaone.himall.common.exception.CommonException;
import com.bestaone.himall.core.exception.Assert;
import com.bestaone.himall.order.domain.Order;
import com.bestaone.himall.order.enums.OrderStatus;
import com.bestaone.himall.order.service.OrderService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController implements OrderApi {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	OrderService userService;

	@Override
	@PostMapping
	public ApiResponse<String> create(@Valid @RequestBody OrderDto orderDto) throws CommonException {
		Assert.notNull(orderDto.getTitle(),50000, "title不存在");
		Assert.notNull(orderDto.getTotalAmount(),50000, "totalAmount不存在");
		Order order = new Order();
		order.setTitle(orderDto.getTitle());
		order.setTotalAmount(orderDto.getTotalAmount());
		order.setCreateTime(new Date());
		order.setStatus(OrderStatus.UNPAY);
		userService.save(order);
		return ApiResponse.sucess(order.getId().toString());
	}

	@Override
	@PutMapping("/{id:\\d+}")
	public ApiResponse update(@PathVariable Long id, @Valid @RequestBody OrderDto orderDto, BindingResult errors) {
		Order order = userService.findById(id);
		order.setTitle(orderDto.getTitle());
		order.setTotalAmount(orderDto.getTotalAmount());
		order.setCreateTime(new Date());
		order.setStatus(OrderStatus.UNPAY);
		userService.save(order);
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
	public ApiResponse<PageVo<Order>> query(Integer pageNum, Integer pageSize, String status) {
		Page pageinfo = PageHelper.startPage(pageNum, pageSize);
		List<Order> orders = userService.findByStatus(OrderStatus.valueOf(status));
		return ApiResponse.sucess(new PageVo<>(pageinfo.getPageNum(), pageinfo.getPageSize(),pageinfo.getTotal(),pageinfo.getPages(),orders));
	}

	@Override
	@GetMapping("/{id:\\d+}")
	public ApiResponse<Order> getInfo(@PathVariable Long id) throws CommonException{
		Order order = userService.findById(id);
		Assert.notNull(order,20001,"数据不存");
		return ApiResponse.sucess(order);
	}

}
