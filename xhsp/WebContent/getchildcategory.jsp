<%@page import="com.xh.shopping.util.StringUtil"%>
<%@page import="java.util.List"%>
<%@page import="com.xh.shopping.model.Category"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String strId = request.getParameter("id");
	if (StringUtil.isEmpty(strId)) {
		return;
	}
	System.out.println("strId:" + strId);
	int id = Integer.parseInt(strId);
	Category parent = Category.loadById(id);
	List<Category> childs = parent.getChilds(id);
	StringBuffer buffer = new StringBuffer();

	/* 	
		//普通的字符串
	 	for (int i = 0; i < childs.size(); i++) {
	 Category category = childs.get(i);
	 buffer.append(category.getId() + "," + category.getName() + "-");
	 }
	 if (!StringUtil.isEmpty(buffer.toString())) {
	 buffer.deleteCharAt(buffer.length() - 1);
	 } */

	// 生成xml开始
	for (int i = 0; i < childs.size(); i++) {
		Category category = childs.get(i);
		buffer.append("<category><id>" + category.getId()
				+ "</id><name>" + category.getName()
				+ "</name></category>");
	}
	buffer.insert(0,
			"<?xml version='1.0' encoding='UTF-8'?>\n<categories>");
	buffer.append("</categories>");
	// 生成xml结束

	response.setContentType("text/xml");
	response.setHeader("Cache-Control", "no-store");//HTTP 1.1
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	response.setDateHeader("Expires", 0); //prevents caching at the proxy server
	response.getWriter().write(buffer.toString().trim());

	System.out.println("|" + buffer.toString() + "|");
%>