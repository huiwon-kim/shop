<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="java.util.Map"%>
<%@ page import = "vo.*" %>    
<%@ page import = "service.*" %>
<%@ page import = "repository.*" %>
    
<%
	request.getParameter("UTF-8");
	int goodsNo = Integer.parseInt(request.getParameter("goodsNo")); 


	
	GoodsService goodsService = new GoodsService();
	Map<String, Object> map = goodsService.getGoodsAndImgOne(goodsNo);
	
	String customerId = request.getParameter("customerId");
	//String customerId = (String)session.getAttribute("id");
	System.out.println(customerId+"<-list의customerId");

%>    
    



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1> 상품 상세보기</h1>

<form action="<%=request.getContextPath()%>/customer/customerGoodsCartAction.jsp?goodsNo=<%=map.get("goodsNo") %>" method="post">
<table border="1">

	<thead>
	<tr> 
		<td>goodsNo</td>
		<td>goodsImg</td>
		<td>goodsName</td>
		<td>goodsPrice</td>
		<td>updateDate</td>	
		<td>orderQuantity</td>
		<td>soldOut</td>
	</tr>
	</thead>
	<tbody>
	<tr>
		<td><%=map.get("goodsNo")%>
			<input type="hidden" name="goodsNo" id="goodsNo" value="<%=map.get("goodsNo")%>">
			<input type="hidden" name="customerId" id="customerId" value="<%=session.getAttribute("id")%>">
		</td>
		<td><img src="<%=request.getContextPath()%>/upload/<%=map.get("filename")%>"></td>
		<td>
			<%=map.get("goodsName")%>
			<input type="hidden" name="goodsName" id="goodsName" value="<%=map.get("goodsName")%>">
		</td>
		<td>
			<%=map.get("goodsPrice")%>
			<input type="hidden" name="goodsPrice" id="goodsPrice" value="<%=map.get("goodsPrice")%>">
		</td>
		<td>
			<%=map.get("updateDate")%>
			<input type="hidden" name="updateDate" id="updateDate" value="<%=map.get("updateDate")%>">
		</td>
		<td><input type="text" name="orderQuantity" id="orderQuantity">
		<button type="submit"> 장바구니에 추가 </button></td>
		
		<td>
			<%=map.get("soldOut")%>
			<input type="hidden" name="soldOut" id="soldOut" value="<%=map.get("soldOut")%>">
		</td>

	</tr>

	<tr>

	</tr>
	</tbody>	

</table>
</form>	
여기에 리뷰달기 근데 이제 방명록같이 다는...

</body>
</html>