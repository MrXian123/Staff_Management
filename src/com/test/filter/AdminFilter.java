package com.test.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter(filterName="admin", urlPatterns="/admin/*")
public class AdminFilter implements Filter {

	    @Override
	    public void init(FilterConfig filterConfig) throws ServletException {
	    	 System.out.println("用户登录过滤器启动 --->");
	    }
	    
	    @Override
	    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
	        HttpSession session = ((HttpServletRequest)servletRequest).getSession();
	        if(session.getAttribute("admin") == null) {
	        	String message = "对不起你没有相应的权限，请先以管理员的身份登录系统";
	            servletResponse.setCharacterEncoding("UTF-8");
	            servletResponse.setContentType("application/json;charset=UTF-8");
	            servletResponse.getOutputStream().write(com.alibaba.fastjson.JSON.toJSONBytes(message));
	            return;
	        } else {
	        	filterChain.doFilter(servletRequest, servletResponse);
	        }
	    }

	    @Override
	    public void destroy() {
	        System.out.println("用户登录过滤器销毁 --->");
	    }

	
	
}
