<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="repository.CustomerDao"%>
<%@page import="service.CustomerService"%>
<%@page import="vo.Customer"%>
    
    
<%
  
                	request.setCharacterEncoding("utf-8");

                	// 변수 받아오기
                	String customerId = request.getParameter("customerId");
                	String customerPass = request.getParameter("customerPass");
                	String customerName = request.getParameter("customerName");
                	
                	// 디버깅
                	System.out.println(customerId+"<-customerId");
                	System.out.println(customerPass+"<-customerPass");
                	
                	
                	// Customer 객체 만들기
                	Customer paramCustomer = new Customer();
                	paramCustomer.setCustomerId(customerId);
                	paramCustomer.setCustomerPass(customerPass);
                	
                	// 디버깅
                	System.out.println(customerId+ "<-customerId");
                	System.out.println(customerPass+ "<-customerPass");
                	
                
                	
                	//CustomerService 객체 만들기?
                	CustomerService customerService = new CustomerService();
                	
                	// select Customer 메서드 사용
                	Customer login = customerService.loginCustomer(paramCustomer);
                	
                	
                	if(login !=null) {
                		System.out.println("성공");		
                		session.setAttribute("user", "Customer"); //세션에 넣는거 
                		session.setAttribute("id", login.getCustomerId());
                		session.setAttribute("name", login.getCustomerName());
                		response.sendRedirect(request.getContextPath()+"/index.jsp");	// 다이렉트
                		
                	}else { // 로그인 실패
                		
                		response.sendRedirect(request.getContextPath()+"/loginForm.jsp");
                		
                		System.out.println("실패");		
                	}
        %>
