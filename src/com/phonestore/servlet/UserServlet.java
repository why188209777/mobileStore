package com.phonestore.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.phonestore.entity.User;
import com.phonestore.service.UserService;
import com.phonestore.service.impl.UserServiceImpl;

/**
 * Servlet implementation class UserServlet
 */
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
			if (user != null) {
				Cookie nameCookie = new Cookie("username", name);
				Cookie pwdCookie = new Cookie("password", password);
				nameCookie.setMaxAge(60);
				pwdCookie.setMaxAge(60);
				response.addCookie(nameCookie);
				response.addCookie(pwdCookie);
			}
			String json = JSON.toJSONString(user);
			System.out.println(json);
			out.println(json);
		}
	}

}
