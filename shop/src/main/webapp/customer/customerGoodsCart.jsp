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
	int goodsNo = Integer.parseInt(request.getParameter("goodsNo"));
	
	// goodsNo를 받아 이를 넣어 실행시킬 메서드, 이에 필요한 goodsService 객체 생성
	GoodsService goodsService = new GoodsService();	
	Map<String, Object> map = goodsService.getGoodsAndImgOne(goodsNo);
	
	
	// 카트에 담는 메서드 관련
	int row = 0;
	Cart cart = new Cart();
	CustomerCartService customerCartService = new CustomerCartService();
	row = customerCartService.addcustomerCart(cart);
	
	if(row ==0) {
		System.out.println("카트에 담기 실패!");
		response.sendRedirect(request.getContextPath() + "/customerGoodsList.jsp");
		} else {
			System.out.println("카트에 담기 성공!");
			response.sendRedirect(request.getContextPath() + "/customerGoodscart.jsp");	
			String customerId = request.getParameter("custoemrId");
		}
	
	
	
	// 카트에 담아 보여주는 메서드 관련
	
	
	// 나머지 변수들 가져오기
	String customerId = (String)map.get("customerId");
	String goodsName = (String)map.get("goodsName");
	
	int goodsPrice = (int)map.get("goodsPrice");
	int orderQuantity = Integer.parseInt(request.getParameter("orderQuantity"));
	String soldOut = (String)map.get("soldOut");
	


	// 디버깅	
	System.out.println(goodsNo +"<-goodsNo");
	System.out.println(goodsName +"<-goodsName");		
	System.out.println(goodsPrice +"<-goodsPrice");
	System.out.println(orderQuantity +"<-goodorderQuantitysPrice");
	
	
	// 상품구매가격을 위한 변수설정
	int orderPrice = goodsPrice * orderQuantity;
	
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
	
	<tr>
	<td><img src="<%=request.getContextPath()%>/upload/<%=map.get("filename")%>"></td></td>
	<td><%=map.get("goodsName")%></td>
	<td><%=map.get("goodsPrice")%></td>
	<td><input type="text" id="orderQuantity" name="orderQuantity" value="orderQuantity" readonly='readonly'></td>
	<td><input type="text" id="orderPrice" name="orderPrice" value="orderPrice" readonly='readonly'></td>

	
	</tr>
	
	</tbody>
</table>
	<button type="submit"> 수정하기 </button>

</form>	
</body>
</html>