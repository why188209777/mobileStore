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
import com.phonestore.entity.Address;
import com.phonestore.entity.Cart;
import com.phonestore.entity.Item;
import com.phonestore.entity.Order;
import com.phonestore.entity.Phone;
import com.phonestore.service.AddressService;
import com.phonestore.service.CartService;
import com.phonestore.service.ItemService;
import com.phonestore.service.OrderService;
import com.phonestore.service.PhoneService;
import com.phonestore.service.impl.AddressServiceImpl;
import com.phonestore.service.impl.CartServiceImpl;
import com.phonestore.service.impl.ItemServiceImpl;
import com.phonestore.service.impl.OrderServiceImpl;
import com.phonestore.service.impl.PhoneServiceImpl;

/**
 * Servlet implementation class PayServlet
 */
@WebServlet("/pay")
public class PayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PayServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String op = request.getParameter("op");
		OrderService orderService = new OrderServiceImpl();
		AddressService addressService = new AddressServiceImpl();
		ItemService itemService = new ItemServiceImpl();
		CartService cartService = new CartServiceImpl();
		PhoneService phoneService = new PhoneServiceImpl();
		
		boolean result = true;
		
		//生成订单基本信息
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
		if (addOrder == 0) {
			result = false;
		}
		
		
		List<Cart> cartList = cartService.getAllCartChecked(userId);
		
		//生成订单详细信息，确保生成订单信息后再生成详细信息
		if (result) {
			if (cartList != null) {
				for (Cart cart : cartList) {
					Phone phone = phoneService.getPhoneByPhoneId(cart.getPhoneId());
					Item item = new Item(
						phone.getPhoneId(), phone.getPhonename(),
						phone.getImage(), phone.getPrice(),
						cart.getNum(), order.getOrderId());
					int addUItem = itemService.addUItem(item);
					if (addUItem == 0) {
						result = false;
					}
				}
			}
		}
		
		//更新手机库存，确保前面操作都正确才更新库存
		if (result) {
			if (cartList != null) {
				for (Cart cart : cartList) {
					Phone phone = phoneService.getPhoneByPhoneId(cart.getPhoneId());
					//更新手机库存
					phone.setNum(phone.getNum() - cart.getNum());
					int updatePhone = phoneService.updatePhone(phone);
					if (updatePhone == 0) {
						result = false;
					}
				}
			}
		}
		
		//删除购物车的商品
		if (result) {
			if (cartList != null) {
				for (Cart cart : cartList) {
					int delCart = cartService.delCart(cart.getId());
					if (delCart == 0) {
						result = false;
					}
				}
			}
		}
		
		String json = JSON.toJSONString(result);
		out.println(json);
	}
}
