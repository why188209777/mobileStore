package com.phonestore.servlet;

import java.io.Console;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.phonestore.dao.ItemDao;
import com.phonestore.dao.OrderDao;
import com.phonestore.dao.UserDao;
import com.phonestore.dao.impl.ItemDaoImpl;
import com.phonestore.dao.impl.OrderDaoImpl;
import com.phonestore.dao.impl.UserDaoImpl;
import com.phonestore.entity.Item;
import com.phonestore.entity.Order;
import com.phonestore.entity.User;

@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Access-Control-Allow-Origin", "*");//跨越
		response.setHeader("Access-Control-Allow-Credentials", "true");//允许操作Cookie
		String op = request.getParameter("op");
		PrintWriter out = response.getWriter();
		ItemDao id = new ItemDaoImpl();
		OrderDao od = new OrderDaoImpl();
		UserDao ud = new UserDaoImpl();
		List<Order> list = od.getAll();
		List<Item> list2=id.getAll();
		if ("list".equals(op)) {
			Map map = new HashMap();
			map.put("orderlist", list);
			String json = JSON.toJSONString(map);
			out.println(json);
		}
		if ("show".equals(op)) {
			String orderid = request.getParameter("id");
			List<Item> itemlist = id.searchItemsByOrderId(orderid);
			String json = JSON.toJSONString(itemlist);
			out.print(json);
		}
		if ("getlist".equals(op)) {
			List<User> userList = new ArrayList<>();
			List<Item> itemlist = new ArrayList<>();
			for (Order order : list) {
				User user = ud.searchUser(order.getUserId());
				userList.add(user);
				
			}
			int pageSize = 2;
			int pageIndex = 
				request.getParameter("pageIndex") == ""
				? 1
				: Integer.parseInt(request.getParameter("pageIndex"));
			String key = request.getParameter("key");
			int totalSize = 0;
			if (!"".equals(key) && key != null) {
				list = od.getAllOrdersByPage(pageIndex, pageSize, key);
				System.out.println(list);
				totalSize = od.getTotalCount(key) % pageSize == 0
						? od.getTotalCount(key) / pageSize
						: od.getTotalCount(key) / pageSize + 1;
			}else {
				list = od.getAllOrdersByPage(pageIndex, pageSize);
				totalSize = od.getTotalCount() % pageSize == 0
						? od.getTotalCount() / pageSize
						: od.getTotalCount() / pageSize + 1;
			}
			
			Map map = new HashMap();
			map.put("orderlist", list);
			map.put("userlist", userList);
			map.put("pageIndex", pageIndex);
			map.put("totalSize", totalSize);
			String json = JSON.toJSONString(map);
			out.print(json);
		}
		if ("dellist".equals(op)) {
			int itemid = Integer.parseInt(request.getParameter("id"));
			int delOrder = od.delOrder(itemid);
			String json = JSON.toJSONString(delOrder);
			out.print(json);
		}
	}

}
