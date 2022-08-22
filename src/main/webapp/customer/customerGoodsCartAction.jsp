<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import = "vo.*" %>    
<%@ page import = "service.*" %>  
<%@ page import = "repository.*" %>      

<%
	// 인코딩
	request.getParameter("UTF-8");
	if(session.getAttribute("user") == null) { // 로긴한 애가 아니면
		response.sendRedirect(request.getContextPath()+"/loginForm.jsp");
			
		return;
	} 
	

	// 변수받아오기
	int goodsNo = Integer.parseInt(request.getParameter("goodsNo"));	
	String goodsName = request.getParameter("goodsName");	
	int orderQuantity = Integer.parseInt(request.getParameter("orderQuantity"));
	String customerId = request.getParameter("custoemrId");
	//String customerId = (String)session.getAttribute("custoemrId");

	int goodsPrice = Integer.parseInt(request.getParameter("goodsPrice"));
		
		


	// 디버깅	
	System.out.println(goodsNo +"<-goodsNo");
	System.out.println(goodsName +"<-goodsName");		
	System.out.println(goodsPrice +"<-goodsPrice");
	System.out.println(orderQuantity +"<-goodorderQuantitysPrice");
	System.out.println(customerId +"<-customerId");
	
	// 받은 변수들을 담을 객체 생성
	Cart parmamcart = new Cart();
	
	
	// 객체 cart에 위에서 불러온 변수들 셋팅	
	parmamcart.setGoodsNo(goodsNo);
	parmamcart.setGoodsName(goodsName);
	parmamcart.setOrderQuantity(orderQuantity);
	parmamcart.setGoodsPrice(goodsPrice);
	parmamcart.setCustomerId(customerId);
	System.out.println(parmamcart +"<-cart");
	
	
	// 카트에 담는 메서드 관련
	int row = 0;

	CustomerCartService customerCartService = new CustomerCartService();
	row = customerCartService.addcustomerCart(parmamcart);
	
	System.out.println(row + "<-row");
	
	
	if(row ==0) {
		System.out.println("카트에 담기 실패!");
		response.sendRedirect(request.getContextPath() + "/customer/customerGoodsList.jsp?");
		} else {
			System.out.println("카트에 담기 성공!");
	
			
			
			response.sendRedirect(request.getContextPath() + "/customerGoodscart.jsp");	
		}
	
%>        
   