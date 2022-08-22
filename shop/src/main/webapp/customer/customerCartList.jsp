<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import = "vo.*" %>    
<%@ page import = "service.*" %>  
<%@ page import = "repository.*" %>      
<%


	// 인코딩
	request.getParameter("UTF-8");
	if(session.getAttribute("user") == null) { // 로긴한 애가 아니면
		response.sendRedirect(request.getContextPath()+"/loginForm.jsp");
			
		return;
	} 
	
	// 이전 페이지에서 넘어온 변수 받기, map 에 저장된 변수를 얻기 위해  Map의 객체 map 생성
	//int goodsNo = Integer.parseInt(request.getParameter("goodsNo"));
	
	// goodsNo를 받아 이를 넣어 실행시킬 메서드, 이에 필요한 goodsService 객체 생성
	// GoodsService goodsService = new GoodsService();	
	// Map<String, Object> map = goodsService.getGoodsAndImgOne(goodsNo);
	
	// 각종 실행 메서드의 가장 큰 기준은 customerId
	String customerId = (String)session.getAttribute("id");
	System.out.println(customerId+ "<-customerId");
	
	
	//Map<String, Object> map = new ArrayList<String, Object>();;
	List<Map<String, Object>> list = new ArrayList<>();
	
	CustomerCartService customerCartService = new CustomerCartService();
	list = customerCartService.getcustomerCart(customerId);
	System.out.println(list+ "<-listlistlistlist");
	//System.out.println(map+ "<-mapmapmapmap");
	
	
	
	// 카트에 담아 보여주는 메서드 관련
	

	
	
%>        
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1> <%=session.getAttribute("id") %> 님의 장바구니 </h1>
	
<form action ="" method="">
<table border="1">

	<thead>
	<tr> 
		<td>goodsImg</td>
		<td>goodsName</td>
		<td>goodsPrice</td>
		<td>orderQuantity</td>
		<td>orderPrice</td>
		<td>soldOut</td>
	</tr>
	</thead>
	<tbody>
	<%
		for( Map<String, Object> m : list ){
	%>
	
	
	<tr>
	<td><img src="<%=request.getContextPath()%>/upload/<%=m.get("filename")%>" width=200 height=200></td>
	<td><%=m.get("goodsName")%></td>
	<td><%=m.get("goodsPrice")%></td>
	<td><input type="text" id="orderQuantity" name="orderQuantity" value="orderQuantity" readonly='readonly'></td>
	<td><input type="text" id="orderPrice" name="orderPrice" value="orderPrice" readonly='readonly'></td>

	
	</tr>
		<% 
	}
	%>
	
	</tbody>
</table>
	<button type="submit"> 수정하기 </button>

</form>	
</body>
</html>