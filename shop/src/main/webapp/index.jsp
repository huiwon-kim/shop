<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<%@ page import = "vo.Customer" %>    
<%@ page import = "vo.Employee" %>  
      
<%

	if(session.getAttribute("user") == null) { // 로긴한 애가 아니면
		response.sendRedirect(request.getContextPath()+"/loginForm.jsp");
			
		return;
	} 
	
		
%>    
    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body><!-- 고객인지 스텝인지 구분을 해야하는데 -->
	<%=session.getAttribute("user") %> <!--  customer / employee -->
	<br>
	<%=session.getAttribute("id") %> <!-- 로그인 아이디 -->
	<br>
	<%=session.getAttribute("name") %> <!-- 로그인 이름 -->
	<br>
	<a href="<%=request.getContextPath()%>/logout.jsp"> 로그아웃 </a>
	<a href="<%=request.getContextPath()%>/info.jsp">상세보기 </a>
	<a href="<%=request.getContextPath()%>/remove<%=session.getAttribute("user")%>Form.jsp"> 회원탈퇴 </a>
	
</body>
</html>