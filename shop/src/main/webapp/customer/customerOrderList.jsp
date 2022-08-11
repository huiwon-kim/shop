<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<%@ page import = "java.util.*" %>    
<%@ page import = "vo.*" %>    
<%@ page import = "service.*" %>  
<%@ page import = "repository.*" %>  
        
    
<%

	// 인코딩
	request.setCharacterEncoding("utf-8");	

	// 손님인 경우에만 확인 가능하게
	if( session.getAttribute("user").equals("Customer") ) {
		System.out.println("손님입니다");
	} else{	
		System.out.println("손님이 아닙니다");	
		response.sendRedirect(request.getContextPath() + "/index.jsp"); // 아니면 인덱스 ㄱㄱ
		return;
	}
		
	// 변수 가져오기
	String customerId = (String)session.getAttribute("id");	// 히든 넘기지 말고 세션에 저장된 아이디 바로 가져오기
	System.out.println(customerId+"<-customerId");
	
	
	// 페이징
	int currentPage = 1; // 어디서부터 시작?
	final int rowPerPage = 5; // 한페이지당 몇개?
	int lastPage = 0;

	
	if(request.getParameter("currentPage")!=null) {
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	} 


	
	
	// 주문목록 불러오기(손님의)
	OrdersService ordersService = new OrdersService();
	OrdersDao ordersDao = new OrdersDao();	

	List<Map<String, Object>> list =ordersService.getOrdersListByCustomer(customerId, rowPerPage, currentPage);

	
	
	
%>
    
    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1> 주문 상세확인 </h1>
	
	<table border="1">
	<thead>
		<tr>
			<td> 주문자아이디 </td>
			<td> 상품명 </td>
			<td> 상품수량 </td>
			<td> 상품가격 </td>
			<td> 배송주소 </td>
			<td> 배송상태 </td>
			<td> 주문날짜 </td>
		</tr>
	</thead>
		
	<tbody>
	
	<%
		for( Map<String, Object> m : list) {
	
	%>
		<tr>
			<td><%=m.get("customerId") %></td>
			<td><%=m.get("goodsName") %></td>
			<td><%=m.get("orderQuantity") %></td>
			<td><%=m.get("orderPrice") %></td>
			<td><%=m.get("orderAddr") %></td>
			<td><%=m.get("orderstate") %></td>
			<td><%=m.get("createDateOr") %></td>
		</tr>
		
	<%
		}
	%>	
		
	</tbody>
	</table>
	
	
		<!--  페이징 -->
	<% 
	if( currentPage >1 ) {
	%>
	
		<a href="<%=request.getContextPath()%>/admin/customerOrderList.jsp?currentPage=<%=currentPage-1%>"> 이전 </a>
	
	<% 
	}
	
	if ( currentPage <lastPage) {	
	%>
	
	<a href="<%=request.getContextPath()%>/admin/customerOrderList.jsp?currentPage=<%=currentPage+1%>"> 다음 </a>

	<%
	}
	%>
	
	
	
</body>
</html>