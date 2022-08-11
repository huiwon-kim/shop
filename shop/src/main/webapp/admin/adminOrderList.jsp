<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import = "java.util.*" %>        
<%@ page import = "vo.*" %>      
<%@ page import = "service.*" %>  
<%@ page import = "repository.*" %>  

<%
	
	// 인코딩
	request.setCharacterEncoding("utf-8");
	
	// 관리자만 수정가능하게
	if ( session.getAttribute("active").equals("Y")) {	
	System.out.println("관리자입니다");
	
	} else { 		
		System.out.println("관리자가 아닙니다");
		
		response.sendRedirect(request.getContextPath() + "/index.jsp"); // 아니면 인덱스 ㄱㄱ
		return;}
	
	
	
	// 페이징
	int currentPage = 1; // 어디서부터 시작?
	final int rowPerPage = 5; // 한페이지당 몇개?
	int lastPage = 0;

	
	if(request.getParameter("currentPage")!=null) {
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	} 
	
	
	
	// 주문목록 불러오기 (전체)
	OrdersService ordersService = new OrdersService();
	OrdersDao ordersDao = new OrdersDao();	
	
	List<Map<String, Object>> list =ordersService.getOrdersListByEmployee(rowPerPage, currentPage); 

	lastPage = ordersService.getOrderListByPageLastPage(rowPerPage);
	System.out.print(lastPage);	
%>

    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div>
	<h1> 관리자 페이지</h1>
	<%=session.getAttribute("user") %> <!--  customer / employee -->
	<br>
	<%=session.getAttribute("id") %> <!-- 로그인 아이디 -->
	<br>
	<%=session.getAttribute("name") %> <!-- 로그인 이름 -->
	<br>
		
</div>

<div>
	<ul><!--  상단메뉴  -->
		<li><a href="<%=request.getContextPath()%>/admin/employeeList.jsp" > 사원관리 </a></li>
		<li><a href="<%=request.getContextPath()%>/admin/adminGoodsList.jsp" > 상품관리 </a></li>
		<!--  └상품목록/등록/수정(품절)/삭제 -->
		<!--  					 └삭제는 근데 장바구니, 주문이 단 한번이라도 없는 경우에만 가능 -->
		<li><a href="<%=request.getContextPath()%>/admin/adminCustomerList.jsp" > 고객관리 </a></li><!-- 주문목록/수정 -->
		<li><a href="<%=request.getContextPath()%>/admin/adminOrderList.jsp" > 주문관리 </a></li><!-- 고객목록/탈퇴/비밀번호수정(전달구현X) -->
		<li><a href="<%=request.getContextPath()%>/admin/adminNoticeList.jsp" > 공지관리 </a></li><!-- CRUD -->
	</ul>
</div>


	<h1> 전체 주문 관리 </h1>

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
			<td><a href="<%=request.getContextPath()%>/admin/adminOrderListOne.jsp"> <%=m.get("goodsName") %></a></td>
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
	
		<a href="<%=request.getContextPath()%>/admin/adminOrderList.jsp?currentPage=<%=currentPage-1%>"> 이전 </a>
	
	<% 
	}
	
	if ( currentPage <lastPage) {	
	%>
	
	<a href="<%=request.getContextPath()%>/admin/adminOrderList.jsp?currentPage=<%=currentPage+1%>"> 다음 </a>

	<%
	}
	%>
	


</body>
</html>