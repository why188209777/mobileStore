package com.phonestore.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.phonestore.entity.User;
import com.phonestore.service.UserService;
import com.phonestore.service.impl.UserServiceImpl;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
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
		response.setHeader("Access-Control-Allow-Origin", "*");//跨越
		response.setHeader("Access-Control-Allow-Credentials", "true");//允许操作Cookie
		PrintWriter out = response.getWriter();
		UserService userService = new UserServiceImpl();
		
		String op = request.getParameter("op");
		
		if ("login".equals(op)) {
			String name = request.getParameter("adminname");
			String password = request.getParameter("password");
			User user = userService.login(name, password);
			if (user != null) {
				if (user.getAdmin() == 1) {
					out.println(true);
				}else {
					out.println(false);
				}
			}else {
				out.println(false);
			}
		}
		
		//用户列表显示、分页以及模糊搜索
		if ("userlist".equals(op)) {
			int pageSize = 7;
			int pageIndex = 
				request.getParameter("pageIndex") == ""
				? 1
				: Integer.parseInt(request.getParameter("pageIndex"));
			System.out.println("pageIndex:" + pageIndex);
			String key = request.getParameter("key");
			List<User> list = null;
			System.out.println("key:" + key);
			int totalSize = 0;
			if (!"".equals(key) && key != null) {
				list = userService.getAllUserByPage(pageIndex, pageSize, key);
				totalSize = userService.getTotalCount(key) % pageSize == 0
						? userService.getTotalCount(key) / pageSize
						: userService.getTotalCount(key) / pageSize + 1;
			}else {
				list = userService.getAllUserByPage(pageIndex, pageSize);
				
				totalSize = userService.getTotalCount() % pageSize == 0
						? userService.getTotalCount() / pageSize
						: userService.getTotalCount() / pageSize + 1;
			}
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userList", list);
			map.put("pageIndex", pageIndex);
			map.put("totalSize", totalSize);
			String json = JSON.toJSONString(map);
			out.println(json);
		}
		
		if ("adduser".equals(op)) {
			String userName = request.getParameter("name");
			String password = request.getParameter("password");
			int vip = Integer.parseInt(request.getParameter("vip"));
			int admin = Integer.parseInt(request.getParameter("admin"));
			String phoneNum = request.getParameter("phonenum");
			User user = new User(userName, password, vip, admin, phoneNum);
			int addUser = userService.addUser(user);
			if (addUser > 0) {
				out.println(true);
			}else {
				out.println(false);
			}
		}
		
		if ("updateuser".equals(op)) {
			int id = Integer.parseInt(request.getParameter("id"));
			String userName = request.getParameter("name");
			String password = request.getParameter("password");
			int vip = Integer.parseInt(request.getParameter("vip"));
			int admin = Integer.parseInt(request.getParameter("admin"));
			String phoneNum = request.getParameter("phonenum");
			User user = new User(id, userName, password, vip, admin, phoneNum);
			int updateUser = userService.updateUser(user);
			if (updateUser > 0) {
				out.println(true);
			}else {
				out.println(false);
			}
		}
		
		if ("deleteuser".equals(op)) {
			int id = Integer.parseInt(request.getParameter("id"));
			int delUser = userService.delUser(id);
			if (delUser > 0) {
				out.println(true);
			}else {
				out.println(false);
			}
		}
		
		if ("userinfo".equals(op)) {
			int id = Integer.parseInt(request.getParameter("id"));
			User user = userService.searchUser(id);
			String json = JSON.toJSONString(user);
			out.println(json);
		}
		
		if ("updatepwd".equals(op)) {
			String username = request.getParameter("adminname");
			String password = request.getParameter("oldpwd");
			System.out.println(username);
			System.out.println(password);
			User user = userService.login(username, password);
			System.out.println(user);
			if (user != null) {
				String newpwd = request.getParameter("newpwd");
				System.out.println(newpwd);
				user.setPassword(newpwd);
				int updateUser = userService.updateUser(user);
				if (updateUser > 0) {
					out.println(true);
				}else {
					out.println(false);
				}
			}else {
				out.println(false);
			}
		}
	
	}

}
