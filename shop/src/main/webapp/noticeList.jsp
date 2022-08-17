<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page import="java.util.*"%>    
<%@ page import="vo.*" %>
<%@ page import="service.*" %>
<%@ page import="repository.*" %>  


<%
	// 인코딩
	request.setCharacterEncoding("utf-8");

	
	int rowPerPage = 5;
	int currentPage = 1;
	int lastPage = 0;

	if ( (request.getParameter("currentPage")) != null) {
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}

	NoticeService noticeService = new NoticeService();
	List<Notice> list = new ArrayList<Notice>();
	
	list = noticeService.getNoticeList(rowPerPage, currentPage);
	lastPage= noticeService.getNoticeListLastPage(rowPerPage);
	System.out.println(lastPage);
	
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

<h1> 공지사항 </h1>
		
<a href="<%=request.getContextPath()%>/noticeForm.jsp"> 게시글 작성 </a>		

<div>
	<table border="1">
	<thead>
		<tr>
			<td> Notice No </td>
			<td> Writer </td>
			<td> Title </td>
			<td> Create Date </td>
		</tr>
	</thead>
	<tbody>
		<%
			for (Notice n : list) {
		%>
		<tr>
			<td><%=n.getNoticeNo() %></td>
			<td><%=n.getWriter() %></td>
			<td>
				<a href="<%=request.getContextPath()%>/noticeOne.jsp?noticeNo=<%=n.getNoticeNo() %>">
					<%=n.getNoticeTitle() %>
				</a>
			
			</td>
			<td><%=n.getCreateDate() %></td>
	
		</tr>	
		<%
			}
		%>
	</tbody>
	</table>

	<!--  페이징  -->
	<%
	if(currentPage >1) {
	%>

		<a href="<%=request.getContextPath()%>/noticeList.jsp?currentPage=<%=currentPage-1%>"> 이전 </a>

	<%
	}
	
	if(currentPage < lastPage) {
	%>
		<a href ="<%=request.getContextPath()%>/noticeList.jsp?currentPage=<%=currentPage+1%>"> 다음 </a>

	<%
	}
	%>

</div>		
		
</body>
</html>