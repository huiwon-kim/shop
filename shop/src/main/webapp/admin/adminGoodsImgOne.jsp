<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import = "vo.*" %>    
<%@ page import = "service.*" %>
<%@ page import = "repository.*" %>
    
    
<%

	int goodsNo = Integer.parseInt(request.getParameter("goodsNo")); 

	
	GoodsService goodsService = new GoodsService();
	Map<String, Object> map = goodsService.getGoodsAndImgOne(goodsNo);

%>    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1> 제품 상세보기 </h1>

<form action="<%=request.getContextPath()%>/admin/adminGoodsUpdateForm.jsp?goodsNo=<%=map.get("goodsNo")%>" method="post"> 
<table border="1">

	<thead>
	<tr> 
		<td>goodsNo</td>
		<td>goodsImg</td>
		<td>goodsName</td>
		<td>goodsPrice</td>
		<td>updateDate</td>
		<td>createDate</td>
		<td>soldOut</td>
	</tr>
	</thead>
	<tbody>
	<tr>
		<td><%=map.get("goodsNo")%></td>
		<td><img src="<%=request.getContextPath()%>/upload/<%=map.get("filename")%>"></td>
		<td><%=map.get("goodsName")%></td>
		<td><%=map.get("goodPrice")%></td>
		<td><%=map.get("updateDate")%></td>
		<td><%=map.get("createDate")%></td>
		<td><%=map.get("soldOut")%></td>
	</tr>
	</tbody>	
</table>


	<button type="submit"> 수정하기</button>

</form>

	
</body>
</html>