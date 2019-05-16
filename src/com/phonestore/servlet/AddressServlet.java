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
import com.phonestore.entity.Address;
import com.phonestore.service.AddressService;
import com.phonestore.service.impl.AddressServiceImpl;

/**
 * Servlet implementation class AddressServlet
 */
@WebServlet("/AddressServlet")
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
		// TODO Auto-generated method stub
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
		AddressService as = new AddressServiceImpl();
		
		//前台管理
		if ("list".equals(op)) {
			
			int userId = Integer.parseInt(request.getParameter("userid"));
			List<Address> list = as.searchAllAddressByUserId(userId);
			String json = JSON.toJSONString(list);
			System.out.println("list"+list);
			System.out.println("json"+json);
			out.println(json);
		}
		if ("add".equals(op)) {
			
			int userId = Integer.parseInt(request.getParameter("userid"));
			String city = request.getParameter("city");
			String detail = request.getParameter("detail");
			String name = request.getParameter("name");
			String postalcode = request.getParameter("postalcode");
			String phoneNum = request.getParameter("phonenum");
			Address address = new Address(name, city, detail, postalcode, phoneNum, userId);
			System.out.println(address);
			int addAddress = as.addAddress(address);
			out.println(addAddress);
		}
		if ("getAddressById".equals(op)) {
			
			int id = Integer.parseInt(request.getParameter("id"));
			Address address = as.searchAddress(id);
			System.out.println(address);
			String json = JSON.toJSONString(address);
			out.println(json);
		}
		if ("update".equals(op)) {
			
			int userId = Integer.parseInt(request.getParameter("userid"));
			String city = request.getParameter("city");
			String detail = request.getParameter("detail");
			String name = request.getParameter("name");
			String postalcode = request.getParameter("postalcode");
			String phoneNum = request.getParameter("phonenum");
			int id = Integer.parseInt(request.getParameter("id"));
			Address address = new Address(id, name, city, detail, postalcode, phoneNum, userId);
			System.out.println(address);
			int updateAddress = as.updateAddress(address);
			String json = JSON.toJSONString(updateAddress);
			out.println(json);
		}
		if ("del".equals(op)) {
			
			int id = Integer.parseInt(request.getParameter("id"));
			int delAddress = as.delAddress(id);
			System.out.println(delAddress);
			out.println(delAddress);
		}
		
		//h后台管理
		if ("getlist".equals(op)) {
			List<Address> list = as.getAll();
			int pageSize = 7;
			int pageIndex = 
				request.getParameter("pageIndex") == ""
				? 1
				: Integer.parseInt(request.getParameter("pageIndex"));
			String key = request.getParameter("key");
			int totalSize = 0;
			if (!"".equals(key) && key != null) {
				list = as.getAllAddressByPage(pageIndex, pageSize, key);
				System.out.println(list);
				totalSize = as.getTotalCount(key) % pageSize == 0
						? as.getTotalCount(key) / pageSize
						: as.getTotalCount(key) / pageSize + 1;
			}else {
				list = as.getAllAddressByPage(pageIndex, pageSize);
				System.out.println(list);
				totalSize = as.getTotalCount() % pageSize == 0
						? as.getTotalCount() / pageSize
						: as.getTotalCount() / pageSize + 1;
			}
			Map map = new HashMap();
			map.put("addresslist", list);
			map.put("pageIndex", pageIndex);
			map.put("totalSize", totalSize);
			System.out.println(map);
			String json = JSON.toJSONString(map);
			out.print(json);
		}
		if ("deleteaddress".equals(op)) {
			int id = Integer.parseInt(request.getParameter("id"));
			int delAddress = as.delAddress(id);
			String json = JSON.toJSONString(delAddress);
			out.print(json);
		}
		if("addaddress".equals(op)) {
			String city = request.getParameter("city");
			String detail = request.getParameter("detail");
			String receiver = request.getParameter("receiver");
			String postalcode = request.getParameter("postalcode");
			String phonenum = request.getParameter("phonenum");
			int userid = Integer.parseInt(request.getParameter("userid"));
			System.out.println(userid);
			Address address = new Address(receiver, city, detail, postalcode, phonenum, userid);
			System.out.println(address);
			int addAddress = as.addAddress(address);
			String json = JSON.toJSONString(addAddress);
			out.print(json);
		}
		if ("addressinfo".equals(op)) {
			int id = Integer.parseInt(request.getParameter("id"));
			Address searchAddress = as.searchAddress(id);
			System.out.println(searchAddress);
			String json = JSON.toJSONString(searchAddress);
			out.print(json);
		}
		if("updateaddress".equals(op)) {
			int id = Integer.parseInt(request.getParameter("id"));
			String city = request.getParameter("city");
			String detail = request.getParameter("detail");
			String receiver = request.getParameter("receiver");
			String postalcode = request.getParameter("postalcode");
			String phonenum = request.getParameter("phonenum");
			int userid = Integer.parseInt(request.getParameter("userid"));
			Address address = new Address(id,receiver, city, detail, postalcode, phonenum,userid);
			System.out.println(address);
			int updateAddress = as.updateAddress(address);
			String json = JSON.toJSONString(updateAddress);
			out.print(json);
		}

	}

}
