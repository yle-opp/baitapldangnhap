package com.cysbersof.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionIdListener;

import com.cysbersof.pojo.User;
import com.cysbersoft.model.LoginDAO;

@WebServlet(name= "baitapldangnhap", urlPatterns = "/login")
public class LoginController extends HttpServlet{
	
	
	private LoginDAO loginDAO = null;
	public LoginController() {
		loginDAO = new LoginDAO();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.getRequestDispatcher("/login").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User users = new User();
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		users = loginDAO.getUsers(email, password);
		
		if(users != null) {
			HttpSession session = req.getSession();
			session.setAttribute("LOGIN_USER",users);
			session.setMaxInactiveInterval(1800);
			resp.sendRedirect(req.getContextPath() + "/home.jsp" );
		}else {
			req.setAttribute("message", "Sai tên đăng nhập hoặc mật khẩu!");
			req.setAttribute("login", users);
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
			
		}
		
	}
}
