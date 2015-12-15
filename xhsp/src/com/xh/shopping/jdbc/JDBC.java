/*************************************************************************************************
 * @版权所有 (C)2015,  星火工作室
 * 
 * @文件名称：JDBC.java
 * @内容摘要：数据库连接
 * @当前版本： TODO
 * @作        者： 李加蒙
 * @完成日期：2015年9月14日 下午3:25:05
 * @修改记录：
 * @修改日期：2015年9月14日 下午3:25:05
 * @版   本  号：
 * @修   改  人：
 * @修改内容：
 ************************************************************************************************/

package com.xh.shopping.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @文件名称：JDBC.java 数据库连接、操纵数据库 插入、删除、更改、
 */
public class JDBC {

	/**
	 * @return connection 获取一个数据库的连接
	 */
	public static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/xhsp", "root", "root");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	/**
	 * 
	 * @param connection
	 *            传入的数据库连接
	 * @return statement 获取一个数据库声明 执行SQL语句
	 * @statement 执行SQL语句不带参的语句
	 */
	public static Statement getStatement(Connection connection) {
		Statement statement = null;

		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return statement;
	}

	/**
	 * @param connection
	 *            传入的数据库连接
	 * @param sql
	 *            SQL语句
	 * @return PStatement 执行SQL语句带参的语句
	 */
	public static Statement getPStatement(Connection connection, String sql) {
		PreparedStatement PStatement = null;
		try {
			PStatement = connection.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return PStatement;
	}

	/**
	 * 
	 * @param statement
	 *            执行SQL语句不带参的语句
	 * @param sql
	 *            SQL语句
	 * @return resultSet 返回的结果集 resultSet.next(); 一整条数据
	 */
	public static ResultSet executeQuery(Statement statement, String sql) {
		ResultSet resultSet = null;
		try {
			resultSet = statement.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSet;
	}

	/**
	 * 
	 * @param PStatement
	 *            PStatement 执行SQL语句带参的Statement
	 * @param sql
	 *            SQL语句
	 * @param autoGeneratedKeys
	 *            产生的Key
	 * @return resultSetKey 返回Key
	 */
	public static int executeUpdate(PreparedStatement PStatement, String sql,
			int autoGeneratedKeys) {
		int resultSetKey = 0;
		try {
			resultSetKey = PStatement.executeUpdate(sql, autoGeneratedKeys);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSetKey;
	}

	/**
	 * @param resultSet
	 *            关闭结果集
	 */
	public static void close(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			resultSet = null;
		}
	}

	/**
	 * @param statement
	 *            关闭执行SQL语句的statement
	 */
	public static void close(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			statement = null;
		}
	}

	/**
	 * @param connection
	 *            关闭数据库连接connection
	 */
	public static void close(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			connection = null;
		}
	}

}