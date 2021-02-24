package com.example.imdb.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter(urlPatterns = "/*")
public class ProfilerWebFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		var start = System.currentTimeMillis();
		chain.doFilter(request, response);
		var stop = System.currentTimeMillis();
		var pathInfo = ((HttpServletRequest) request).getServletPath();
		System.err.println(pathInfo  + "\t" + (stop-start)+" ms.");
	}

}
