<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
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
<body>
	<h1> 스텝탈퇴 </h1>
	<form action ="<%=request.getContextPath()%>/haus/haus/removeEmployee.jsp" method="post">
	<table border="1">
		<tr>
			<td> 비밀번호 입력 </td>
			<td> <input type="password" name="Pass" id="Pass"></td>
			
		</tr>
	
	</table>
	<button type="submit"> 탈퇴 </button>
</form>
</body>
</html>