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
		
		Connection conn=null;
        DruidDataSourceFactory factory = new DruidDataSourceFactory();
        Properties p = new Properties();
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
	
	public static int executeUpdate(String sql, Object...params) {
		Connection conn = getConn();
		QueryRunner qr = new QueryRunner();
		try {
			return qr.update(conn, sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public static Object getObject(Class clazz, String sql, Object...params) {
		Connection conn = getConn();
		QueryRunner qr = new QueryRunner();
		try {
			return qr.query(conn, sql, new BeanHandler<Class>(clazz), params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static List getObjects(Class clazz, String sql, Object...params) {
		Connection conn = getConn();
		QueryRunner qr = new QueryRunner();
		try {
			return qr.query(conn, sql, new BeanListHandler<Class>(clazz), params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
