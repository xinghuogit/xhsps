/*************************************************************************************************
 * @版权所有 (C)2015,  星火工作室
 * 
 * @文件名称：User.java
 * @内容摘要：用户bean
 * @当前版本： TODO
 * @作        者： 李加蒙
 * @完成日期：2015年9月20日 下午6:25:05
 * @修改记录：
 * @修改日期：2015年9月20日 下午6:25:05
 * @版   本  号：
 * @修   改  人：
 * @修改内容：
 ************************************************************************************************/

package com.xh.shopping.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import com.xh.shopping.util.DB;

/**
 * @文件名称：User.java 用户注册bean
 */
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private String username;
	private String password;
	private String phone;
	private String auth;
	private String name;
	private String pid;
	private String addr;
	private Date rdate;
	private Date cpdate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public Date getRdate() {
		return rdate;
	}

	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}

	public Date getCpdate() {
		return cpdate;
	}

	public void setCpdate(Date cpdate) {
		this.cpdate = cpdate;
	}

	public void save() {
		Connection connection = DB.getConnection();
		String sql = "insert into user values (null, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement pStatement = DB.getPStatement(connection, sql);
		try {
			pStatement.setString(1, getUsername());
			pStatement.setString(2, getPassword());
			pStatement.setString(3, getPhone());
			pStatement.setString(4, getAuth());
			pStatement.setString(5, getName());
			pStatement.setString(6, getPid());
			pStatement.setString(7, getAddr());
			pStatement.setTimestamp(8, new Timestamp(getRdate().getTime()));
			pStatement.setTimestamp(9, new Timestamp(getCpdate().getTime()));
			pStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(pStatement);
			DB.close(connection);
		}
	}

}