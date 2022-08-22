<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	
	int goodsNo = Integer.parseInt(request.getParameter("goodsNo"));
	String customerId= request.getParameter("customerId");
	String reviewContent = request.getParameter("reviewContent");
	
	
	ReviewService reviewService = new ReviewService();
	reviewService.



%>