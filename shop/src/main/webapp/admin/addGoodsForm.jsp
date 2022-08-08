<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="<%=request.getContextPath()%>/admin/addGoodsAction.jsp"
		method="post"
		enctype="multipart/form-data"><!--  바이너리는 무조건 포스트여야한대 겟은 ㄴㄴㄴ -->
	
	<!--  받는거 편하게 하려고 cos라이브러리 추가함 -->
	<input type="text">
	<input type="file">
	
	
	
	
	</form>
</body>
</html>