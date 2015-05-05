package com.scorm.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scorm.utils.UserCommon;
import com.scorm.vo.Userinfo;

public class AdminFilter implements Filter{
	
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest Request = (HttpServletRequest) request;
		HttpServletResponse Response = (HttpServletResponse) response;
		Userinfo userinfo = (Userinfo) Request.getSession().getAttribute(UserCommon.USERINFO);
		if( userinfo == null ) {
			Response.sendRedirect(Request.getContextPath()+"/login/admin_login.jsp");
		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
