package com.bestaone.aiwan.order.web.controller;

import com.bestaone.aiwan.core.api.ApiResponse;
import com.bestaone.aiwan.core.api.PageVo;
import com.bestaone.aiwan.core.exception.Assert;
import com.bestaone.aiwan.core.exception.CommonException;
import com.bestaone.aiwan.order.api.OrderApi;
import com.bestaone.aiwan.order.api.dto.OrderDto;
import com.bestaone.aiwan.order.api.vo.OrderVo;
import com.bestaone.aiwan.order.domain.Order;
import com.bestaone.aiwan.order.domain.enums.OrderStatus;
import com.bestaone.aiwan.order.service.OrderService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
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
	public ApiResponse<PageVo<OrderVo>> query(Integer pageNum, Integer pageSize, String status) {
		Page pageinfo = PageHelper.startPage(pageNum, pageSize);
		List<Order> orders = userService.findByStatus(OrderStatus.valueOf(status));
		List<OrderVo> orderVos = new ArrayList<>();
		for(Order order : orders){
			OrderVo vo = new OrderVo();
			vo.setTitle(order.getTitle());
			vo.setId(order.getId());
			vo.setStatus(order.getStatus()!=null?order.getStatus().name():null);
			vo.setTotalAmount(order.getTotalAmount());
			orderVos.add(vo);
		}
		return ApiResponse.sucess(new PageVo<>(pageinfo.getPageNum(), pageinfo.getPageSize(),pageinfo.getTotal(),pageinfo.getPages(),orderVos));
	}

	@Override
	@GetMapping("/{id:\\d+}")
	public ApiResponse<OrderVo> getInfo(@PathVariable Long id) throws CommonException{
		Order order = userService.findById(id);
		Assert.notNull(order,20001,"数据不存");
		OrderVo vo = new OrderVo();
		vo.setTitle(order.getTitle());
		vo.setId(order.getId());
		vo.setCreateTime(order.getCreateTime());
		vo.setStatus(order.getStatus()!=null?order.getStatus().name():null);
		vo.setTotalAmount(order.getTotalAmount());
		return ApiResponse.sucess(vo);
	}

}
