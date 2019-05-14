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
import com.phonestore.service.AddressService;
import com.phonestore.service.impl.AddressServiceImpl;

/**
 * Servlet implementation class AddressServlet
 */
@WebServlet("/address")
public class AddressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddressServlet() {
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
		PrintWriter out = response.getWriter();
		AddressService addressService = new AddressServiceImpl();
		String op = request.getParameter("op");
		if ("list".equals(op)) {
			
			int userId = Integer.parseInt(request.getParameter("userid"));
			List<Address> list = addressService.searchAllAddressByUserId(userId);
			String json = JSON.toJSONString(list);
			System.out.println("list"+list);
			System.out.println("json"+json);
			out.println(json);
		}else if ("add".equals(op)) {
			
			int userId = Integer.parseInt(request.getParameter("userid"));
			String city = request.getParameter("city");
			String detail = request.getParameter("detail");
			String name = request.getParameter("name");
			String postalcode = request.getParameter("postalcode");
			String phoneNum = request.getParameter("phonenum");
			Address address = new Address(name, city, detail, postalcode, phoneNum, userId);
			System.out.println(address);
			int addAddress = addressService.addAddress(address);
			out.println(addAddress);
		}else if ("getAddressById".equals(op)) {
			
			int id = Integer.parseInt(request.getParameter("id"));
			Address address = addressService.searchAddress(id);
			System.out.println(address);
			String json = JSON.toJSONString(address);
			out.println(json);
		}else if ("update".equals(op)) {
			
			int userId = Integer.parseInt(request.getParameter("userid"));
			String city = request.getParameter("city");
			String detail = request.getParameter("detail");
			String name = request.getParameter("name");
			String postalcode = request.getParameter("postalcode");
			String phoneNum = request.getParameter("phonenum");
			int id = Integer.parseInt(request.getParameter("id"));
			Address address = new Address(id, name, city, detail, postalcode, phoneNum, userId);
			System.out.println(address);
			int updateAddress = addressService.updateAddress(address);
			String json = JSON.toJSONString(updateAddress);
			out.println(json);
		}else if ("del".equals(op)) {
			
			int id = Integer.parseInt(request.getParameter("id"));
			int delAddress = addressService.delAddress(id);
			System.out.println(delAddress);
			out.println(delAddress);
		}
		
	}

}
