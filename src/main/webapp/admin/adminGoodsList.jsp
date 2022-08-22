<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>    
<%@ page import="vo.*" %>
<%@ page import="service.*" %>
<%@ page import="repository.*" %>   

<%	

	request.setCharacterEncoding("utf-8");

	// 관리자만 수정가능하게
	if ( session.getAttribute("active").equals("Y")) {	
	System.out.println("관리자입니다");

	} else { 		
		System.out.println("관리자가 아닙니다");
		
		response.sendRedirect(request.getContextPath() + "/index.jsp"); // 아니면 인덱스 ㄱㄱ
		return;}



	int rowPerPage = 5; // 한페이지당 몇개?
	int currentPage = 1;// 어디서부터 시작?
	int lastPage = 0;
	
	if ( (request.getParameter("currentPage")) != null) {
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}

	
	GoodsService goodsService = new GoodsService();
	List <Goods> list= new ArrayList<Goods>();
	
	list = goodsService.getGoodsListByPage(rowPerPage, currentPage);
	lastPage = goodsService.getGoodsListLastPage(rowPerPage);
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
	<ul><!--  상단메뉴  -->
		<li><a href="<%=request.getContextPath()%>/admin/employeeList.jsp" > 사원관리 </a></li>
		<li><a href="<%=request.getContextPath()%>/admin/adminGoodsList.jsp" > 상품관리 </a></li>
		<!--  └상품목록/등록/수정(품절)/삭제 -->
		<!--  					 └삭제는 근데 장바구니, 주문이 단 한번이라도 없는 경우에만 가능 -->
		<li><a href="<%=request.getContextPath()%>/admin/adminCustomerList.jsp" > 고객관리 </a></li><!-- 주문목록/수정 -->
		<li><a href="<%=request.getContextPath()%>/admin/adminOrderList.jsp" > 주문관리 </a></li><!-- 고객목록/탈퇴/비밀번호수정(전달구현X) -->
		<li><a href="<%=request.getContextPath()%>/noticeList.jsp" > 공지관리 </a></li><!-- CRUD -->
	</ul>
</div>

<h1> 상품관리</h1>

<a href="<%=request.getContextPath()%>/admin/addGoodsForm.jsp"> 상품추가</a>
<div>
	<table border="1">
	<thead>
		<tr>
			<td> goodsNo </td>
			<td> goodsName </td>
			<td> goodsPrice </td>
			<td> updateDate </td>
			<td> createDate </td>
			<td> soldOut </td>
		</tr>
		</thead>
		<tbody>
			<%
			for (Goods g : list) {
			%>
			<tr>
				<td><%=g.getGoodsNo() %></td>
				<td><a href="<%=request.getContextPath()%>/admin/adminGoodsImgOne.jsp?goodsNo=<%=g.getGoodsNo() %>">
				<%=g.getGoodsName() %></a>
					
				</td>
				<td><%=g.getGoodsPrice() %></td>
				<td><%=g.getUpdateDate() %></td>
				<td><%=g.getCreateDate() %></td>
				<td><%=g.getSoldOut() %></td>
			</tr>
		
	<% 
	}
	%>
	</tbody>
	</table>	
	
	
	
	<!--  페이징  -->
	<% 
	if( currentPage >1 ) {
	%>
	
	<a href="<%=request.getContextPath()%>/admin/adminGoodsList.jsp?currentPage=<%=currentPage-1%>"> 이전 </a>
	
	<% 
	}
	
	if ( currentPage <lastPage) {	
	%>
	
	<a href="<%=request.getContextPath()%>/admin/adminGoodsList.jsp?currentPage=<%=currentPage+1%>"> 다음 </a>

	<%
	}
	%>
	
	
</div>
</body>
</html>