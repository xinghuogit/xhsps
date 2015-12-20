package com.xh.shopping.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.xh.shopping.model.User;
import com.xh.shopping.util.DB;
import com.xh.shopping.util.StringUtil;

/**
 * Servlet implementation class UserRegister
 */
@WebServlet("/UserRegister")
public class UserRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public UserRegister() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet请求");
		PrintWriter out = response.getWriter();
		out.print("doGet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");

		// String phone = request.getParameter("phone");
		// String nickname = request.getParameter("nickname");
		// String addr = request.getParameter("addr");

		if (StringUtil.isStringDataNull(username)) {
			out.print("账号不可为空");
			return;
		}
		if (StringUtil.isStringDataNull(password)) {
			out.print("密码不可为空");
			return;
		}
		if (StringUtil.isStringDataNull(password2)) {
			out.print("确认密码不可为空");
			return;
		}
		System.out.println("password2：" + password2);
		System.out.println("password1：" + password);
		System.out.println("username1：" + username);
		if (!password.equals(password2)) {
			out.print("密码与确认密码不一致");
			return;
		}

		// if (StringUtil.isStringDataNull(phone)) {
		String phone = username;
		// }
		// if (StringUtil.isStringDataNull(nickname)) {
		String nickname = username;
		// }

		User user = new User(0, username, password, phone, nickname, "",
				new Date(), new Date());

		try {
			if (!user.getUserName(username)) {
				out.print("账号已注册，请更换账号");
				return;
			} else {
				System.out.println("账号进行注册中");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			out.print("数据库异常，请稍后再试");
			System.out.println("数据库异常，请稍后再试");
		}

		try {
			user.save();
			out.print("注册成功");
			System.out.println("注册成功");
		} catch (SQLException e) {
			e.printStackTrace();
			out.print("数据库异常，请稍后再试");
			System.out.println("数据库异常，请稍后再试");
		}
	}
}
