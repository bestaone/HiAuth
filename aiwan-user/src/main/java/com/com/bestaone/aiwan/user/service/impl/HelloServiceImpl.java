package com.com.bestaone.aiwan.user.service.impl;

import com.com.bestaone.aiwan.user.service.HelloService;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {

	@Override
	public String greeting(String name) {
		System.out.println("greeting");
		return "hello "+name;
	}

}
