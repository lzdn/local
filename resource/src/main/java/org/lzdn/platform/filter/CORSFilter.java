package org.lzdn.platform.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class CORSFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletResponse httpResponse = (HttpServletResponse) response;
		httpResponse.setHeader("Access-Control-Allow-Origin", "*");
		httpResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
		httpResponse.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		// httpResponse.setHeader("Access-Control-Allow-Headers",
		// "Cache-Control, Pragma, Origin, Authorization, Content-Type,
		// X-Requested-With");
		// httpResponse.addHeader("Access-Control-Allow-Headers", "origin,
		// content-type, accept, x-requested-with, sid, mycustom, smuser");
		httpResponse.setHeader("Access-Control-Max-Age", "1800");
		httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
	
		chain.doFilter(request, httpResponse);

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
