<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import ="vo.*" %>
<%@ page import ="service.*" %>


<%
	// 인코딩
	request.setCharacterEncoding("utf-8");

	// 변수 받아오기
	String customerId = request.getParameter("customerId");
	String customerPass = request.getParameter("customerPass");
	String customerName = request.getParameter("customerName");
	String customerAddress = request.getParameter("customerAddress");
	String customerTelephone = request.getParameter("customerTelephone");
	
	
	// Customer 객체 생성 후 값들 넣어주기	
	Customer paramCustomer = new Customer();
	paramCustomer.setCustomerId(customerId);
	paramCustomer.setCustomerPass(customerPass);
	paramCustomer.setCustomerName(customerName);
	paramCustomer.setCustomerAddress(customerAddress);
	paramCustomer.setCustomerTelephone(customerTelephone);
	
	
	System.out.println(paramCustomer +"<-paramCustomer");
	
	// CustomerService 객체 생성 후 메서드실행수 Customer객체 넣어주기
	CustomerService customerService = new CustomerService();
	customerService.signInCustomer(paramCustomer);
	
	
	
	response.sendRedirect(request.getContextPath()+"/index.jsp");

%>