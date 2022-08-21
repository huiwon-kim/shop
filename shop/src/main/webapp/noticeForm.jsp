<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	
	// 인코딩
	request.setCharacterEncoding("utf-8");





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

<h1> 공지사항 작성하기 </h1>
<form action="<%=request.getContextPath()%>/noticeinsertAction.jsp" method="post">
<table border="1">
	
	<tr>
	<th>
	Title : <input type="text" id="noticeTitle" name="noticeTitle">
	</th>
	</tr>

	<tr>
	<th> Content :	<textarea id="noticeContent" name="noticeContent" rows="3" cols="30"> </textarea></th>	
	</tr>
	<tr>
	<th>
	Pass :	<input type="password" id="noticePass" name="noticePass"></th>	
	</tr>
	

</table>
<button type="submit"> 공지사항 입력</button> 

</form>
</body>
</html>