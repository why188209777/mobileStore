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
import com.phonestore.dao.ItemDao;
import com.phonestore.dao.OrderDao;
import com.phonestore.dao.impl.ItemDaoImpl;
import com.phonestore.dao.impl.OrderDaoImpl;
import com.phonestore.entity.Item;
import com.phonestore.entity.Order;

/**
 * Servlet implementation class OrderServlet
 */
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
		PrintWriter out = response.getWriter();
		ItemDao id = new ItemDaoImpl();
		OrderDao od = new OrderDaoImpl();
		List<Order> list = od.getAll();
		List<Item> list2=id.getAll();
		Map map = new HashMap();
		map.put("itemlist", list);
		map.put("orderlist", list2);
		String json = JSON.toJSONString(map);
		out.println(json);
	}

}
