<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import = "vo.*" %>    
<%@ page import = "controller.*" %>       
<%@ page import = "controller.*" %>
<%@ page import = "repository.*" %>  

<%
	int goodsNo = Integer.parseInt(request.getParameter("goodsNo"));
	GoodsImgDao goodsimgDao = new GoodsImgDao().
	
%>


        
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form id="imgForm" action="<%=request.getContextPath() %>/updateGoodsAction.jsp"
		method="post" enctype="multipart/form-data"><!--  바이너리는 무조건 포스트여야한대 겟은 ㄴㄴㄴ -->
	
	<!--  받는거 편하게 하려고 cos라이브러리 추가함 -->


	<div>
		<a>
			<img src="<%=request.getContextPath()%>/upload/<%=goodsimgDao. %>">
		</a>
	</div>


	
	<h1> 상품 수정하기 </h1>
		<table border="1">
		<tr>			
			<td> 상품명 </td>
			<td><input type="text" name="goodsName" id="goodsName"> </td>
		</tr>
		<tr>
			<td> 상품이미지 </td>
			<td><input type="file" name="imgfile" id="imgfile"> </td>
		</tr>	
		<tr>
			<td> 상품가격 </td>
			<td><input type="text" name="goodsPrice" id="goodsPrice"> </td>
		</tr>
	
		</table>
		<button type="submit" > 상품추가 </button>
	
	</form>
</body>
</html>