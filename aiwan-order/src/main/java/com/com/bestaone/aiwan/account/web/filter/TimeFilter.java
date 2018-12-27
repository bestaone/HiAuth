package com.com.bestaone.aiwan.account.web.filter;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

//@Component
public class TimeFilter implements Filter {

	@Override
	public void destroy() {
		System.out.println("time filter destroy");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("time filter start");
		long start = System.currentTimeMillis();
		chain.doFilter(request, response);
		System.out.println("time filter 耗时:"+ (System.currentTimeMillis() - start));
		System.out.println("time filter finish");
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("time filter init");
	}

}
