<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
     
<%@page import="vo.Customer"%>    
<%@ page import = "service.*" %>

    
<%
	// ...
	
	// 변수받아오기
	String Pass = request.getParameter("Pass");
	String customerId = (String)session.getAttribute("id");

	

	// 디버깅
	System.out.println(Pass +"<-Pass");
	System.out.println(customerId +"<-CustomerId");
	
	// 1. Customer용
	// Customer 객체 만들기	
	Customer paramCustomer = new Customer();
    paramCustomer.setCustomerId(customerId);
    paramCustomer.setCustomerPass(Pass);

	// Customerdao는 service에서 햿으니까 불러올 필요 X 나는 service만
    
    // CustomerService 객체 만들기
	CustomerService customerService = new CustomerService();
	customerService.removeCustomer(paramCustomer);
	
	
		
	
	response.sendRedirect(request.getContextPath()+"/logout.jsp");
	
	
%>
