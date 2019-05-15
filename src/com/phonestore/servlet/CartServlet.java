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
//		ItemDao itemDao = new ItemDaoImpl();
		String op = request.getParameter("op");
		PhoneService phonedao = new PhoneServiceImpl();
		CartService cartDao = new CartServiceImpl();
		OrderService oDao = new OrderServiceImpl();
		if ("list".equals(op)) {
			int clearChecked = cartDao.clearChecked();
			int userid = Integer.parseInt(request.getParameter("userid"));
			//cartDao.clearChecked();
			List<Phone> phoneList = null;
			List<Cart> cartList = cartDao.getAllCart(userid);
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
		if ("update".equals(op)) {
			int numid=Integer.parseInt(request.getParameter("numid"));
			int num=Integer.parseInt(request.getParameter("num"));
			int updateNum = cartDao.updateNum(numid, num);
			String json = JSON.toJSONString(updateNum);
			out.println(json);
		}
		if ("updatechecked".equals(op)) {
			int checkednum=0;
			String checked = request.getParameter("checked");
			int id = Integer.parseInt(request.getParameter("id"));
			if (checked.equals("true")) {
				checkednum=1;
			}
			int updateNum = cartDao.updateChecked(id, checkednum);
			String json = JSON.toJSONString(updateNum);
			System.out.println(json);
			out.println(json);
		}
		if ("updateallchecked".equals(op)) {
			String checked = request.getParameter("checked");
			if (checked.equals("true")) {
				int updateAllChecked = cartDao.updateAllChecked();
				String json = JSON.toJSONString(updateAllChecked);
				out.println(json);
			}
		}
		if ("delall".equals(op)) {
			int delAllCart = cartDao.delAllCart();
			String json = JSON.toJSONString(delAllCart);
			System.out.println(json);
			out.println(json);
		}
		
		if ("checkedList".equals(op)) {
			int userid = Integer.parseInt(request.getParameter("userid"));
			List<Cart> cartList = cartDao.getAllCartChecked(userid);
			List<Phone> phoneList = null;
			if (cartList != null) {
				phoneList = new ArrayList<Phone>();
				for (Cart cart : cartList) {
					phoneList.add(phonedao.searchPhoneByPhoneId(cart.getPhoneId()));
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
