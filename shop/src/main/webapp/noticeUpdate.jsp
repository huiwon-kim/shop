<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="java.util.*"%>    
<%@ page import="vo.*" %>
<%@ page import="service.*" %>
<%@ page import="repository.*" %>  


<%   
	request.setCharacterEncoding("utf-8");
	
	int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
	System.out.println(noticeNo + "<-noticeNonoticeNo");
	
	NoticeService noticeService = new NoticeService();
	Notice paramnotice = new Notice();
	paramnotice = noticeService.getnoticeOne(noticeNo);
	
	System.out.println(paramnotice + "<-paramnotice");


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

<h1> 공지사항 수정보기 </h1>


<div>
<form action ="<%=request.getContextPath()%>/noticeUpdateAction.jsp" method="post">

	<table border="1">
	<thead>
	

		<tr>
		<td colspan="4"> Title; 
			<input type="text" name="noticeTitle" id="noticeTitle" value="<%=paramnotice.getNoticeTitle() %>">
		</td>
		</tr>
		<tr>
			
			<td> Notice No; 
				<input type="text" name="noticeNo" id="noticeNo" value="<%=paramnotice.getNoticeNo() %>" readonly='readonly'>
			</td>
			<td> Writer; 
				<input type="text" name="noticeWriter" id="noticeWriter" value="<%=paramnotice.getWriter() %>">
			</td>
			<td> Create Date;
				<input type="text" name="createDate" id="createDate" value="<%=paramnotice.getCreateDate() %>">
			</td>
		</tr>
	
		<tr>
		<td colspan="3">
				<input type="text" name="noticeContent" id="noticeContent" value="<%=paramnotice.getNoticeContent() %>">
		</td>
		
		</tr>
				<tr>
		<td colspan="3">
				<input type="password" name="noticePass" id="noticePass" >
		</td>
		
		</tr>
	</thead>
	<tbody>

</tbody>
</table>
	<button type="submit"> 수정완료 </button>
</form>

</div>
</body>
</html>