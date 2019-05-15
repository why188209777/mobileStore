package com.phonestore.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.phonestore.entity.Phone;
import com.phonestore.entity.Cart;
import com.phonestore.service.CartService;
import com.phonestore.service.PhoneService;
import com.phonestore.service.impl.CartServiceImpl;
import com.phonestore.service.impl.PhoneServiceImpl;

/**
 * Servlet implementation class PhoneServlet
 */
@WebServlet("/PhoneServlet")
public class PhoneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PhoneServlet() {
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
		PhoneService ps=new PhoneServiceImpl();
		CartService cs=new CartServiceImpl();
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Access-Control-Allow-Origin", "*");//跨越
		response.setHeader("Access-Control-Allow-Credentials", "true");//允许操作Cookie
		PrintWriter out=response.getWriter();//输出对象
		System.out.println("执行PhoneServlet中的doPost方法");
		String op = request.getParameter("op");
		HashMap<String,Object> map = new HashMap<>();//通用存储体
		List<Phone> listBrand=ps.searchDistinctBrand();//去重品牌
		//全局
		int pageSize=9;
		int totalPage=0;//总页数
		String pageIndex=request.getParameter("pageIndex");//接受前端参数第一次访问为null
		int currentPage=pageIndex==null?1:Integer.parseInt(pageIndex);//当前页
		
		map.put("currentPage", currentPage);
		map.put("pageSize", pageSize);
		map.put("listBrand", listBrand);//临时方法
		String json = null;//统一出口
		//判断请求类型
		if ("getAllPhone".equals(op)){
			//请求Phone数据
			List<Phone> listPhone=ps.getAllPhoneByPage(currentPage,pageSize);
			//总数据总页数
			totalPage=(int) (ps.getTotalCount()%pageSize==0?ps.getTotalCount()/pageSize:ps.getTotalCount()/pageSize+1);
			map.put("totalPage", totalPage);
			map.put("list", listPhone);
			json=JSON.toJSONString(map);
		}else if ("getPhone".equals(op)) {
			//获取某个Phone的详情
			String phoneid=request.getParameter("phoneid");
			Phone phone=ps.searchPhoneByPhoneId(phoneid);
			System.out.println("phoneid:"+phoneid);
			if(phone!=null){
				json=JSON.toJSONString(phone);
			}else{
				System.out.println("未找到该商品的信息");
			}
		}else if ("getBrandPhone".equals(op)) {
			String brand=request.getParameter("brand");
			System.out.println(brand);
			System.out.println(ps.getTotalCountByBrand(brand));
			System.out.println(ps.getTotalCountByBrand(brand)%pageSize);
			totalPage=(int) (ps.getTotalCountByBrand(brand)%pageSize==0?ps.getTotalCountByBrand(brand)/pageSize:ps.getTotalCountByBrand(brand)/pageSize+1);
			map.put("totalPage", totalPage);
			List<Phone> listPhone=ps.searchBrandPhone(brand,currentPage,pageSize);
			System.out.println("brand:"+brand);
			if(listPhone!=null){
				map.put("brand", brand);
				map.put("list", listPhone);
				json=JSON.toJSONString(map);
			}else{
				System.out.println("未找到该品牌的信息");
			}
		}else if ("getPricePhone".equals(op)) {
			String minPrice=request.getParameter("minPrice");
			String maxPrice=request.getParameter("maxPrice");
			totalPage=(int) (ps.getTotalCountByPrice(minPrice, maxPrice)%pageSize==0?ps.getTotalCountByPrice(minPrice, maxPrice)/pageSize:ps.getTotalCountByPrice(minPrice, maxPrice)/pageSize+1);
			map.put("totalPage", totalPage);
			List<Phone> listPhone=ps.searchPricePhone(minPrice, maxPrice,currentPage,pageSize);
			System.out.println("minPrice:"+minPrice+"maxPrice:"+maxPrice);
			System.out.println("价格Phone的数量："+listPhone.size());
			if(listPhone!=null){
				map.put("minPrice", minPrice);
				map.put("maxPrice", maxPrice);
				map.put("list", listPhone);
				json=JSON.toJSONString(map);
			}else{
				System.out.println("未找到该价格区间的信息");
			}
		}else if ("getVaguePhone".equals(op)) {
			String keyword=request.getParameter("keyword");
			System.out.println("模糊搜索"+keyword);
			totalPage=(int) (ps.getTotalCountByVague(keyword)%pageSize==0?ps.getTotalCountByVague(keyword)/pageSize:ps.getTotalCountByVague(keyword)/pageSize+1);
			map.put("totalPage", totalPage);
			List<Phone> listPhone=ps.vagueSearchPhone(keyword,currentPage,pageSize);
			System.out.println("keyword:"+keyword);
			if(listPhone!=null){
				map.put("keyword", keyword);
				map.put("list", listPhone);
				json=JSON.toJSONString(map);
			}else{
				System.out.println("未找到该模糊条件的信息");
			}
		}else if ("addPhone".equals(op)) {
			System.out.println("开始准备增加手机！");
			String phoneid=request.getParameter("phoneid1");
			String phonenum=request.getParameter("phonenum1");
			Phone phone=ps.searchPhoneByPhoneId(phoneid);
			int result=0;
			//增加前先查询若存在则库存+1
			if(phone!=null){
				System.out.println("本手机已存在");
				result=ps.updatePhoneByNum(phone.getNum()+Integer.valueOf(phonenum), phoneid);
				if(result>0){
					System.out.println("添加成功(更改库存)");
				}else{
					System.out.println("添加失败(更改库存出错)");
				}
				
			}else{
				//总共15参数
				String phonebrand=request.getParameter("phonebrand1");
				String phonename=request.getParameter("phonename1");
				String phoneprice=request.getParameter("phoneprice1");
				String phoneimage=request.getParameter("phoneimage1");
				String phonesize=request.getParameter("phonesize1");
				String phonecolcor=request.getParameter("phonecolcor1");
				String phoneram=request.getParameter("phoneram1");
				String phonerom=request.getParameter("phonerom1");
				String phonenettype=request.getParameter("phonenettype1");
				String phonecamera=request.getParameter("phonecamera1");
				String phonecpu=request.getParameter("phonecpu1");
				String phoneoperatingsystem=request.getParameter("phoneoperatingsystem1");
				String phonecontent=request.getParameter("phonecontent1");
				Phone addPhone=new Phone(phoneid, phonename, phonebrand, Double.valueOf(phoneprice), Integer.valueOf(phonenum), phoneimage, phonesize, phonecolcor, phoneram, phonerom, phonenettype, phonecamera, phonecpu, phoneoperatingsystem, phonecontent);
				result=ps.addPhone(addPhone);
				if(result>0){
					System.out.println("添加新手机成功");
				}else{
					System.out.println("添加新手机错误");
				}
			}
			json=JSON.toJSONString(result);
		}else if ("modifyMangePhone".equals(op)) {
			String phoneid=request.getParameter("phoneId");
			String phonenum=request.getParameter("phoneNum");
			String phoneprice=request.getParameter("phonePrice");
			String phonedescription=request.getParameter("phoneDescription");
			Phone phone=new Phone(phoneid, Integer.valueOf(phonenum), Integer.valueOf(phoneprice), phonedescription);
			int result=ps.modifyPhone(phone);
			if(result>0){
				System.out.println("修改配置成功");
				
			}else{
				System.out.println("修改配置失败");
			}
			json=JSON.toJSONString(result);
		}else if ("deleteMangePhone".equals(op)) {
			String phoneid=request.getParameter("phoneId");
			int result=ps.delPhoneByPhoneId(phoneid);
			if(result>0){
				System.out.println("删除手机成功");
				
			}else{
				System.out.println("删除手机失败");
			}
			json=JSON.toJSONString(result);
		}else if ("addCart".equals(op)) {
			System.out.println("进入购物车流程：");
			String userid=request.getParameter("userid");
			String phoneid=request.getParameter("phoneid");
			String num=request.getParameter("num");
			int result=0;
			Cart isExitCart=cs.getCart(phoneid);
			if(isExitCart!=null){
				System.out.println("本phoneid已存在cart表");
				System.out.println("已存在的phoneid的物品数量为："+isExitCart.getNum());
				Cart cart=new Cart(phoneid, isExitCart.getNum()+Integer.valueOf(num), Integer.valueOf(userid),0);
				result=cs.updateCart(cart);
				if(result>0){
					System.out.println("添加购物车成功");	
				}else{
					System.out.println("添加购物车失败(已存在)");
				}
			}else{
				Cart cart=new Cart(phoneid, Integer.valueOf(num), Integer.valueOf(userid), 0);
				result=cs.addCart(cart);
				if(result>0){
					System.out.println("添加购物车成功");
					
				}else{
					System.out.println("添加购物车失败(不存在)");
				}
			}
			json=JSON.toJSONString(result);
		}
		System.out.println(json);
		out.println(json);
	}

}
