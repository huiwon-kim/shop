<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="vo.*" %>    
<%@ page import="service.*" %>      
    
<%

	// 인코딩
	request.setCharacterEncoding("utf-8");
	

	// 넘어온 변수 받기
	int goodsNo = Integer.parseInt(request.getParameter("goodsNo"));
	String customerId= request.getParameter("customerId");
	String reviewPass = request.getParameter("reviewPass");
	String reviewContent = request.getParameter("reviewContent");
	
	
	// 디버깅
	System.out.println(goodsNo+"<-goodsNo");
	System.out.println(customerId+"<-customerId");
	System.out.println(reviewPass+"<-reviewPass");
	System.out.println(reviewContent+"<-reviewContent");
	
	
	// 넘어온 변수 담을 vo Review의 객체 생성, 이후 변수 담아주기
	Review paramreview = new Review();
	paramreview.setGoodsNo(goodsNo);
	paramreview.setCustomerId(customerId);
	paramreview.setCustomerId(reviewPass);
	paramreview.setReviewContent(reviewContent);
	System.out.println(paramreview+"<-paramreviewparamreview");
	
	// 조금 더 편한 출력값 확인을 위한 새로운 변수 설정
	int result =0;
	
	// sql 메서드를 실행시키기 위한 servie객체 생성 및 메서드 실행. 메서드 실행의 매개변수는 위에서 만든 변수
	ReviewService reviewService = new ReviewService();
	reviewService.addReview(paramreview);


	result = reviewService.addReview(paramreview);
	
	if (result==0) {
		System.out.println("후기 입력 실패!");
		response.sendRedirect(request.getContextPath()+"/customer/customerGoodsList.jsp");
	}	else {
		System.out.println("후기 입력 성공!");
		response.sendRedirect(request.getContextPath()+"/customer/customerGoodsOne.jsp");
	}


%>