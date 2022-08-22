<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import ="vo.*" %>    
<%@ page import ="repository.*" %> 
<%@ page import ="service.*" %> 


<%

	// 인코딩 
	request.setCharacterEncoding("utf-8");


	//관리자만 수정가능하게
	if ( session.getAttribute("active").equals("Y")) {	
	System.out.println("관리자입니다");
	
	} else { 		
		System.out.println("관리자가 아닙니다");
		
		response.sendRedirect(request.getContextPath() + "/index.jsp"); // 아니면 인덱스 ㄱㄱ
		return;}

	
	// 변수설정 및 가져오기	
	int row = 0;
	String orderState = request.getParameter("orderstate");
	int orderNo =Integer.parseInt(request.getParameter("orderNo"));

	
	// 디버깅
	System.out.println(orderState+ "<-orderState");


	// 객체 생성 및 객체에 값 집어넣기
	Orders orders = new Orders();
	orders.setOrderState(orderState);
	orders.setOrderNo(orderNo);

	
	// 객체생성 및 메서드실행
	OrdersService ordersService = new OrdersService();
	row = ordersService.modifyOrdersOne(orders);
	
	if( row ==0) {
		System.out.println("수정 실패!");
		response.sendRedirect(request.getContextPath()+"/admin/adminOrderList.jsp");
	} else {
		System.out.println("수정 성공!");
		response.sendRedirect(request.getContextPath()+"/admin/adminOrderList.jsp");
	}
	
	
%>