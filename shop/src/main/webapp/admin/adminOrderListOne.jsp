<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="java.util.*"%>       
<%@ page import = "vo.*" %>    
<%@ page import = "service.*" %>
<%@ page import = "repository.*" %>    
    
   
 <%
 	// 인코딩 
	request.setCharacterEncoding("utf-8");

	// 관리자만 수정가능하게
	if ( session.getAttribute("active").equals("Y")) {	
	System.out.println("관리자입니다");

	} else { 		
		System.out.println("관리자가 아닙니다");
		
		response.sendRedirect(request.getContextPath() + "/index.jsp"); // 아니면 인덱스 ㄱㄱ
		return;}
 
 
	
	// 변수 받아오기
	
	
 %>  
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div>
	<h1> 관리자 페이지</h1>
	<%=session.getAttribute("user") %> <!--  customer / employee -->
	<br>
	<%=session.getAttribute("id") %> <!-- 로그인 아이디 -->
	<br>
	<%=session.getAttribute("name") %> <!-- 로그인 이름 -->
	<br>
		
</div>

<div>
	<ul><!--  상단메뉴  -->
		<li><a href="<%=request.getContextPath()%>/admin/employeeList.jsp" > 사원관리 </a></li>
		<li><a href="<%=request.getContextPath()%>/admin/adminGoodsList.jsp" > 상품관리 </a></li>
		<!--  └상품목록/등록/수정(품절)/삭제 -->
		<!--  					 └삭제는 근데 장바구니, 주문이 단 한번이라도 없는 경우에만 가능 -->
		<li><a href="<%=request.getContextPath()%>/admin/adminCustomerList.jsp" > 고객관리 </a></li><!-- 주문목록/수정 -->
		<li><a href="<%=request.getContextPath()%>/admin/adminOrderList.jsp" > 주문관리 </a></li><!-- 고객목록/탈퇴/비밀번호수정(전달구현X) -->
		<li><a href="<%=request.getContextPath()%>/admin/adminNoticeList.jsp" > 공지관리 </a></li><!-- CRUD -->
	</ul>
</div>

<h1> 주문 상세 보기</h1> <!--  <li><a href="<!%=request.getContextPath()!%>/admin/adminOrderList.jsp" > 주문관리  -->

	<table border="1">
	
	
	
	</table>

</body>
</html>