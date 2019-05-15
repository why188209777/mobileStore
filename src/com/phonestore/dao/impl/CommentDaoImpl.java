package com.phonestore.dao.impl;

import java.util.List;

import com.phonestore.dao.CommentDao;
import com.phonestore.entity.Comment;
import com.phonestore.entity.Phone;
import com.phonestore.util.DBUtil;

public class CommentDaoImpl implements CommentDao {

	//phone详情页添加评论
		@Override
		public int addComment(Comment comment) {
			String  sql="insert into comment(content,userid,phoneid,ip,equipment,contentTime)values(?,?,?,?,?,?)";
			return DBUtil.executeUpdate(sql, comment.getContent(), comment.getUserid(), comment.getPhoneid(), comment.getIp(), comment.getEquipment(), comment.getContentTime());
		}

		//查找某phoneid的所有评论
		@Override
		public List getAllComment(int phoneId) {
			String sql="select * from comment where phoneid=?";
			return DBUtil.getObjects(Comment.class, sql, phoneId);
		}

		@Override
		public List<Comment> getAllComments() {
			String sql="SELECT * from comment";
			return DBUtil.getObjects(Comment.class, sql);
		}

		@Override
		public int removeComment(int commentid) {
			String sql="DELETE FROM comment WHERE id =?";
			return DBUtil.executeUpdate(sql, commentid);
		}

		@Override
		public int updateComment(int commentid, String content) {
			String sql="UPDATE comment SET content=? WHERE id=?";
			return DBUtil.executeUpdate(sql,content,commentid);
		}

		@Override
		public List<Comment> vagueSearchComment(String keyword,int pageIndex, int pageSize) {
			String sql="SELECT * FROM comment where concat(userid, phoneid,contentTime) like \"%\"?\"%\" limit ?,?";
			return DBUtil.getObjects(Comment.class, sql, keyword,pageIndex,pageSize);
		}

		@Override
		public long getTotalCount() {
			String sql = "select count(*) from comment";
			return DBUtil.getTotalCount(sql);
		}

		@Override
		public long getTotalCountByVague(String keyword) {
			String sql="SELECT count(*) FROM comment where concat(userid, phoneid,contentTime) like \"%\"?\"%\"";
			/*if(DBUtil.Integer(keyword)){
				int keywordInt=Integer.valueOf(keyword);
				return DBUtil.getTotalCount(sql, keywordInt);
			}else{
				return DBUtil.getTotalCount(sql, keyword);
			}*/
			return DBUtil.getTotalCount(sql, keyword);
			
		}

}
