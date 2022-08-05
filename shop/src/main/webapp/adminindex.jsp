<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import = "vo.*" %>        
    
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
	
	<div>
		<a href="<%=request.getContextPath()%>/employeeList.jsp" > 사원관리 </a>
		<a href="<%=request.getContextPath()%>/" > 상품관리 </a>
		<a href="<%=request.getContextPath()%>/" > 고객관리 </a>
		<a href="<%=request.getContextPath()%>/" > 주문관리 </a>
		<a href="<%=request.getContextPath()%>/" > 공지관리 </a>
	</div>
</div>

</body>
</html>