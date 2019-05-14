package com.phonestore.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.phonestore.entity.Item;
import com.phonestore.service.ItemService;
import com.phonestore.service.impl.ItemServiceImpl;

/**
 * Servlet implementation class ItemServlet
 */
@WebServlet("/item")
public class ItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemServlet() {
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
		ItemService itemService = new ItemServiceImpl();
		String op = request.getParameter("op");
		System.out.println("op:" + op);
		//显示订单详细信息
		if ("orderDetail".equals(op)) {
			
			String orderId = request.getParameter("orderid");
			List<Item> list = itemService.searchItemsByOrderId(orderId);
			String json = JSON.toJSONString(list);
			System.out.println("json:" + json);
			out.println(json);
		}
		
		if ("cancel".equals(op)) {
			String orderId = request.getParameter("orderid");
			int delItem = itemService.delItemByOrderId(orderId);
			String json = JSON.toJSONString(delItem);
			out.println(json);
		}
	}

}
