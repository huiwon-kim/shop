<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="model.MemberDao"%>
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
	Customer customer = new Customer();
	customer.setCustomerId(customerId);
	customer.setCustomerPass(customerPass);
	
	// 디버깅
	System.out.println(customerId+ "<-customerId");
	System.out.println(customerPass+ "<-customerPass");
	
	
	// MemberDao 객체 만들기
	MemberDao memberDao = new MemberDao();
	System.out.println(memberDao +"<-memberDao");
	
	
	// login 메서드 사용
	Customer login = memberDao.Clogin(customer);
	
	
	if(login !=null) {
		System.out.println("성공");		
		session.setAttribute("user", "customer"); //세션에 넣는거 
		session.setAttribute("id", login.getCustomerId());
		session.setAttribute("name", login.getCustomerName());
		response.sendRedirect(request.getContextPath()+"/ourindl,,,ex.jsp");	// 다이렉트
		
	}else { // 로그인 실패
		
		response.sendRedirect(request.getContextPath()+"/loginForm.jsp");
		
		System.out.println("실패");		
	}
	
	
	
%>
