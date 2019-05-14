package com.phonestore.service;

import java.util.List;

import com.phonestore.entity.Comment;

public interface CommentService {
	public List<Comment> getAllComments();
	public int addComment(Comment comment);//增加评论
	public List<Comment> getAllComment(int phoneId);//通过id查找所有评论
	public int removeComment(int commentid);//根据id删除某条评论
	public int updateComment(int commentid,String content);
	public List<Comment> vagueSearchComment(String keyword,int pageIndex, int pageSize);//模糊查询评论
	public long getTotalCount();//总评论量
	public long getTotalCountByVague(String keyword);//模糊查询数据量
}
