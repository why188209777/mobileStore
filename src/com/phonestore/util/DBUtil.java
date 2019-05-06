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
	dsadsad
	//获得连接
	public static Connection getConn() {
		System.out.print("222");
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
	
	//执行删、改操作
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
	
	//执行单个查询
	public static Object getObject(Class clazz, String sql, Object...params) {
		Connection conn = getConn();
		QueryRunner runner = new QueryRunner();
		try {
			runner.query(conn, sql, new BeanHandler<Class>(clazz), params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	//执行全部查询
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
	
	
}
