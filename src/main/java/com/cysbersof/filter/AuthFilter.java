package com.cysbersof.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = "/login")
public class AuthFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse reponse, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req =(HttpServletRequest) request;
		HttpServletResponse resq =(HttpServletResponse) reponse;
		
		if(!req.getServletPath().startsWith("/login")) {
			if(req.getSession().getAttribute("LOGIN_USER") != null) {
				chain.doFilter(request, reponse);
			}else {
				resq.sendRedirect(req.getContextPath() +"/login");
			}
		}else {
			chain.doFilter(request, reponse);
		}
		
		
		
	}
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}
}
