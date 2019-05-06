package com.phonestore.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.alibaba.druid.pool.DruidDataSourceFactory;

public class DBUtil {
	public static Connection getConn() {
		System.out.println();
		System.out.println();
		Connection conn=null;
        DruidDataSourceFactory factory = new DruidDataSourceFactory();
        Properties p = new Properties();
        System.out.println();
        InputStream in = DBUtil.class.getClassLoader().getResourceAsStream("druid.properties");
        try {
			p.load(in);
			DataSource dataSource = factory.createDataSource(p);
			conn = dataSource.getConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return conn;
	}
	
	//鎵ц鍒犮�佹敼鎿嶄綔
	public static int executeUpdate(String sql, Object...params) {
		Connection conn = getConn();
		QueryRunner runner = new QueryRunner();
		try {
			return runner.update(conn, sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	//鎵ц鍗曚釜鏌ヨ
	public static Object getObject(Class clazz, String sql, Object...params) {
		Connection conn = getConn();
		QueryRunner runner = new QueryRunner();
		try {
			return runner.query(conn, sql, new BeanHandler<Class>(clazz), params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//鎵ц鍏ㄩ儴鏌ヨ
	public static List getObjects(Class clazz, String sql, Object...params) {
		Connection conn = getConn();
		QueryRunner runner = new QueryRunner();
		try {
			return (List) runner.query(conn, sql, new BeanListHandler<Class>(clazz), params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		Connection conn = getConn();
		if (conn!=null) {
			System.out.println("成功");
		}
	}
	
	
}
