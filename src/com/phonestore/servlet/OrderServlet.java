package com.phonestore.servlet;

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
import com.phonestore.dao.impl.ItemDaoImpl;
import com.phonestore.dao.impl.OrderDaoImpl;
import com.phonestore.entity.Address;
import com.phonestore.entity.Item;
import com.phonestore.entity.Order;
import com.phonestore.service.AddressService;
import com.phonestore.service.ItemService;
import com.phonestore.service.OrderService;
import com.phonestore.service.impl.AddressServiceImpl;
import com.phonestore.service.impl.ItemServiceImpl;
import com.phonestore.service.impl.OrderServiceImpl;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/order")
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
		OrderService orderService = new OrderServiceImpl();
		AddressService addressService = new AddressServiceImpl();
		String op = request.getParameter("op");
		if ("list".equals(op)) {
			int userid =Integer.parseInt(request.getParameter("userid"));
			List<Order> list = orderService.searcOrderByUserId(userid);
			String json = JSON.toJSONString(list);
			System.out.println("orderList" + list);
			System.out.println("json:" + json);
			out.println(json);
		}
		
		if ("cancel".equals(op)) {
			String orderId = request.getParameter("orderid");
			int delOrder = orderService.delOrderByOrderId(orderId);
			String json = JSON.toJSONString(delOrder);
			out.println(json);
		}
		
		if ("add".equals(op)) {
			int userId = Integer.parseInt(request.getParameter("userid"));
			double total = Double.parseDouble(request.getParameter("total"));
			int addressId = Integer.parseInt(request.getParameter("addressid"));
			Address address = addressService.searchAddress(addressId);
			int status = Integer.parseInt(request.getParameter("status"));
			Order order = new Order(
				userId, total, address.getCity() + " " + address.getDetail(),
				address.getPhoneNum(), status);
			System.out.println("order:" + order);
			int addOrder = orderService.addOrder(order);
			String json = JSON.toJSONString(addOrder);
			out.println(json);
		}
	}

}
