package com.phonestore.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.phonestore.entity.Comment;
import com.phonestore.entity.Phone;
import com.phonestore.service.CommentService;
import com.phonestore.service.PhoneService;
import com.phonestore.service.impl.CommentServiceImpl;
import com.phonestore.service.impl.PhoneServiceImpl;

/**
 * Servlet implementation class CommentServlet
 */
@WebServlet("/CommentServlet")
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CommentService cs = new CommentServiceImpl();
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Access-Control-Allow-Origin", "*");//跨越
		response.setHeader("Access-Control-Allow-Credentials", "true");//允许操作Cookie
		PrintWriter out=response.getWriter();//输出对象
		System.out.println("执行CommentServlet中的doPost方法");
		int pageSize=9;
		int totalPage=0;//总页数
		HashMap<String,Object> map = new HashMap<>();//通用存储体
		String pageIndex=request.getParameter("pageIndex");//接受前端参数第一次访问为null
		int currentPage=pageIndex==null?1:Integer.parseInt(pageIndex);//当前页
		map.put("currentPage", currentPage);
		map.put("pageSize", pageSize);
		String op = request.getParameter("op");
		if ("addComment".equals(op)){
			String content=request.getParameter("content");
			int userId=Integer.parseInt(request.getParameter("uerId"));
			String phoneId=request.getParameter("phoneId");
			String ip=request.getParameter("ip");
			String equipment=request.getParameter("equipment");
			Date date=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String conentTime=sdf.format(date);
			
			//执行数据库增加
			Comment comment = new Comment(content, userId, phoneId, ip, equipment,conentTime);
			int result=cs.addComment(comment);
			if(result>0){
				System.out.println("添加评论成功");
				map.put("flag", 1);
				map.put("content", content);
				map.put("conentTime", conentTime);
				String json=JSON.toJSONString(map);
				out.println(json);
			}else{
				System.out.println("添加评论失败");
			}
		}else if ("commentList".equals(op)){
			String phoneId=request.getParameter("phoneId");
			System.out.println(phoneId);
			
			List<Comment> listComment=cs.getAllComment(Integer.parseInt(phoneId));//获得评论列表
			String json=JSON.toJSONString(listComment);
			out.println(json);
		}else if ("getAllComments".equals(op)){
			System.out.println("接受请求全部评论的请求！");
			List<Comment> listComment=cs.getAllComments();//获得所有评论列表
			totalPage=(int) (cs.getTotalCount()%pageSize==0?cs.getTotalCount()/pageSize:cs.getTotalCount()/pageSize+1);
			map.put("totalPage", totalPage);
			map.put("list", listComment);
			String json=JSON.toJSONString(map);
			out.println(json);
		}else if ("removeMangeComments".equals(op)){
			System.out.println("接受请求删除某条评论的请求！");
			String commentid=request.getParameter("commentid");
			int result=cs.removeComment(Integer.valueOf(commentid));
			if(result>0){
				System.out.println("删除评论成功！");
				String json=JSON.toJSONString(result);
				out.println(json);
			}else{
				System.out.println("删除评论失败！");
			}
		}else if ("modifyMangeComments".equals(op)){
			System.out.println("接受请求修改某条评论的请求！");
			String commentid=request.getParameter("commentid");
			String content=request.getParameter("commentcontent");
			System.out.println(content);
			int result=cs.updateComment(Integer.valueOf(commentid), content);
			if(result>0){
				System.out.println("修改评论成功！");
				String json=JSON.toJSONString(result);
				out.println(json);
			}else{
				System.out.println("修改评论失败！");
			}
			
		}else if ("getVagueComment".equals(op)){
			System.out.println("接受请求模糊查询评论的请求！");
			String keyword=request.getParameter("keyword");
			totalPage=(int) (cs.getTotalCountByVague(keyword)%pageSize==0?cs.getTotalCountByVague(keyword)/pageSize:cs.getTotalCountByVague(keyword)/pageSize+1);
			map.put("totalPage", totalPage);
			List<Comment> listComment=cs.vagueSearchComment(keyword,currentPage,pageSize);
			System.out.println("keyword:"+keyword);
			if(listComment!=null){
				map.put("keyword", keyword);
				map.put("list", listComment);
				String json=JSON.toJSONString(map);
				out.println(json);
			}else{
				System.out.println("未找到该模糊条件区间的信息");
			}
			
		}
	}

}
