<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%

	// 뭔가 GoodsService goodsService= new GoodsService()
	// goodsService의 메서드 하나 불러와서 서비스가 2개 하니까 ... 뭐 그러면 밑에도 적용되는..
	// 그런걸까... 

%>    
    
    
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

	
	<h1> 상품 업로드 </h1>
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