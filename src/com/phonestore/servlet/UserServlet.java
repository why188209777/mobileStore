package com.phonestore.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.phonestore.entity.User;
import com.phonestore.service.UserService;
import com.phonestore.service.impl.UserServiceImpl;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//根据op判断需要执行的操作
		String op = request.getParameter("op");
		UserService userService = new UserServiceImpl();
		PrintWriter out = response.getWriter();
		if ("login".equals(op)) {
			String name = request.getParameter("username");
			String password = request.getParameter("password");
			User user = userService.login(name, password);
			String json = JSON.toJSONString(user);
			System.out.println(json);
			out.println(json);
		}
		
		if ("register".equals(op)) {
			
			String name = request.getParameter("username");
			String password = request.getParameter("password");
			User user = new User(name, password);
			int addUser = userService.addUser(user);
			if (addUser > 0) {
				out.println(true);
			}else {
				out.println(false);
			}
			
		}
		
		if ("userinfo".equals(op)) {
			
			int userId = Integer.parseInt(request.getParameter("userid"));
			User user = userService.searchUser(userId);
			String json = JSON.toJSONString(user);
			out.println(json);
		}
		
		if ("updatePassword".equals(op)) {
			
			int userId = Integer.parseInt(request.getParameter("userid"));
			String password = request.getParameter("password");
			User user = userService.searchUser(userId);
			System.out.println(user);
			user.setPassword(password);
			int updateUser = userService.updateUser(user);
			if (updateUser > 0) {
				out.println(true);
			}else {
				out.println(false);
			}
		}
		
		if ("updatePhoneNum".equals(op)) {
			
			int userId = Integer.parseInt(request.getParameter("userid"));
			String phoneNum = request.getParameter("phonenum");
			User user = userService.searchUser(userId);
			System.out.println(user);
			user.setPhoneNum(phoneNum);
			int updateUser = userService.updateUser(user);
			if (updateUser > 0) {
				out.println(true);
			}else {
				out.println(false);
			}
		}
	}

}
