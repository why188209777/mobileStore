package com.phonestore.service.impl;

import java.util.List;

import com.phonestore.dao.CommentDao;
import com.phonestore.dao.impl.CommentDaoImpl;
import com.phonestore.entity.Comment;
import com.phonestore.service.CommentService;

public class CommentServiceImpl implements CommentService {

	CommentDao dao = new CommentDaoImpl();
	@Override
	public int addComment(Comment comment) {
		return dao.addComment(comment);
	}

	@Override
	public List<Comment> getAllComment(int phoneId) {
		return dao.getAllComment(phoneId);
	}

	@Override
	public List<Comment> getAllComments() {
		return dao.getAllComments();
	}

	@Override
	public int removeComment(int commentid) {
		return dao.removeComment(commentid);
	}

	@Override
	public int updateComment(int commentid, String content) {
		return dao.updateComment(commentid, content);
	}

	@Override
	public List<Comment> vagueSearchComment(String keyword, int pageIndex, int pageSize) {
		// TODO Auto-generated method stub
		return dao.vagueSearchComment(keyword, pageIndex, pageSize);
	}

	@Override
	public long getTotalCount() {
		// TODO Auto-generated method stub
		return dao.getTotalCount();
	}

	@Override
	public long getTotalCountByVague(String keyword) {
		// TODO Auto-generated method stub
		return dao.getTotalCountByVague(keyword);
	}

}
