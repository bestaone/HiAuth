package com.bestaone.aiwan.user.web.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class TimeFilter implements Filter {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void destroy() { }

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		long start = System.currentTimeMillis();
		chain.doFilter(request, response);
		logger.debug("接口[{}]耗时{}毫秒", ((HttpServletRequest) request).getRequestURI(), (System.currentTimeMillis() - start));
	}

	@Override
	public void init(FilterConfig arg0) {}

}
