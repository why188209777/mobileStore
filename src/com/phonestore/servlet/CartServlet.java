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
import com.phonestore.dao.CartDao;
import com.phonestore.dao.OrderDao;
import com.phonestore.dao.PhoneDao;
import com.phonestore.dao.impl.CartDaoImpl;
import com.phonestore.dao.impl.OrderDaoImpl;
import com.phonestore.dao.impl.PhoneDaoImpl;
import com.phonestore.entity.Cart;
import com.phonestore.entity.Order;
import com.phonestore.entity.Phone;
import com.phonestore.service.CartService;
import com.phonestore.service.OrderService;
import com.phonestore.service.PhoneService;
import com.phonestore.service.impl.CartServiceImpl;
import com.phonestore.service.impl.OrderServiceImpl;
import com.phonestore.service.impl.PhoneServiceImpl;


@WebServlet("/Cart")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
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
		System.out.println("我进来了");
//		ItemDao itemDao = new ItemDaoImpl();
		String op = request.getParameter("op");
		PhoneService phonedao = new PhoneServiceImpl();
		CartService cartDao = new CartServiceImpl();
		OrderService oDao = new OrderServiceImpl();
		int userId = Integer.parseInt(request.getParameter("userid"));
		if ("list".equals(op)) {
			List<Phone> phoneList = null;
			List<Cart> cartList = cartDao.getAllCart(userId);
			if (cartList != null) {
				phoneList = new ArrayList<Phone>();
				for (Cart cart : cartList) {
					Phone phone = phonedao.getPhoneByPhoneId(cart.getPhoneId());
					phoneList.add(phone);
				}
			}
			Map map = new HashMap();
			map.put("cartlist", cartList);
			map.put("phonelist", phoneList);
			String json = JSON.toJSONString(map);
			out.println(json);
		}		
		if ("del".equals(op)) {
			int id=Integer.parseInt(request.getParameter("id"));
			System.out.println(id);
			int delCart = cartDao.delCart(id);
			String json = JSON.toJSONString(delCart);
			System.out.println(json);
			out.println(json);
		}
		
		if ("checkedList".equals(op)) {
			List<Cart> cartList = cartDao.getAllCartChecked(userId);
			List<Phone> phoneList = null;
			if (cartList != null) {
				phoneList = new ArrayList<Phone>();
				for (Cart cart : cartList) {
					phoneList.add(phonedao.searchPhone(cart.getPhoneId()));
				}
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("cartList", cartList);
			map.put("phoneList", phoneList);
			String json = JSON.toJSONString(map);
			System.out.println(json);
			out.println(json);
		}
		
		
	}

}
