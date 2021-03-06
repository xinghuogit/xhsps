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

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

/**
 * @文件名称：JDBC.java 数据库连接、操纵数据库 插入、删除、更改、
 */
public class DB {

	/**
	 * 静态块 进入本类就执行语句
	 */
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return connection 获取一个数据库的连接
	 * @throws SQLException
	 */
	public static Connection getConnection() throws Exception {
		Connection connection = null;
		// try {
		// connection = DriverManager
		// .getConnection(
		// "jdbc:mysql://rds861y2gckfb4dvlus4.mysql.rds.aliyuncs.com:3306/xhspsql",
		// "xhspsql", "liLJM371916");// 阿里云数据库
		// connection = DriverManager.getConnection(
		// "jdbc:mysql://xhmysql-li160.tenxcloud.net:59985/xhspsql",
		// "admin", "eC2J5mQHJ3mD");// 时速云数据库服务器

		/**
		 * 本地
		 */
		String url = "jdbc:mysql://localhost:3306/xhspsql";
		String user = "root";
		String password = "123456";
		connection = DriverManager.getConnection(url, user, password);

		// 时速云数据库服务器 1
		// connection = DriverManager.getConnection(
		// "jdbc:mysql://xhmysql-li160.tenxcloud.net:23105/xhspsql",
		// "admin", "5Z0JR0uNzq0G");// 时速云数据库服务器

		// int lport = 3307;// 本地端口
		// String rhost = "xhserver-li160.tenxcloud.net";// 远程MySQL服务器
		// int rport = 45169;// 远程MySQL服务端口
		//
		// String user = "root";// SSH连接用户名
		// String password = "li191128";// SSH连接密码
		// String host = "xhserver-li160.tenxcloud.net";// SSH服务器
		// int port = 57329;// SSH访问端口
		//
		// JSch jsch = new JSch();
		// Session session = jsch.getSession(user, host, port);
		// session.setPassword(password);
		// session.setConfig("StrictHostKeyChecking", "no");
		// session.connect();
		// System.out.println(session.getServerVersion());// 这里打印SSH服务器版本信息
		// System.out.println("rhost:" + rhost);
		// int assinged_port = session.setPortForwardingL(lport, rhost, rport);
		// System.out.println("localhost:" + assinged_port + " -> " + rhost +
		// ":"
		// + rport);
		// connection = DriverManager.getConnection(
		// "jdbc:mysql://localhost:45169/xhspsql", "root", "li191128");//
		// // 时速云服务器上面的数据库服务器
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
	 * @return PreparedStatement PStatement 执行SQL语句带参的语句
	 */
	public static PreparedStatement getPStatement(Connection connection,
			String sql) {
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
	 * @param connection
	 *            传入的数据库连接
	 * @param sql
	 *            SQL语句
	 * @param generatedKey
	 *            返回当前插入的数据
	 * @return PreparedStatement PStatement 执行SQL语句带参的语句
	 */
	public static PreparedStatement getPStatement(Connection connection,
			String sql, boolean generatedKey) {
		PreparedStatement PStatement = null;
		try {
			PStatement = connection.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);
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
			// resultSet = statement.execu
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSet;
	}

	public static boolean execute(Statement statement, String sql) {
		System.out.println("sql:" + sql);
		boolean execute = false;
		try {
			execute = statement.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return execute;
	}

	public static int executeUpdate(Statement statement, String sql) {
		System.out.println("sql:" + sql);
		int execute = 0;
		try {
			execute = statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return execute;
	}

	/**
	 * 
	 * @param statement
	 *            执行SQL语句不带参的语句
	 * @param sql
	 *            SQL语句
	 * @return resultSet 返回的结果集 resultSet.next(); 一整条数据
	 */
	public static ResultSet executeQuery(Connection connection, String sql) {
		System.out.println("sql:" + sql);
		ResultSet resultSet = null;
		try {
			resultSet = connection.createStatement().executeQuery(sql);
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
	 * 执行一条更新sql语句
	 * 
	 * @param connection
	 * @param sql
	 * @return
	 */
	public static int executeUpdata(Connection conn, String sql) {
		int ret = 0;
		Statement statement = null;
		try {
			statement = getStatement(conn);
			ret = statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(statement);
		}
		return ret;
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
