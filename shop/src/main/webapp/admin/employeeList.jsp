<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<%@ page import = "java.util.*" %>    
<%@ page import = "vo.*" %>    
<%@ page import = "service.*" %>  
<%@ page import = "repository.*" %>  
    
<%

	// 여기서 셀렉 구문...dao 가져와서
	// 회원들 목록 띄우는데 필요한 정보들 가져와서
	// employee리스트에서는 <%= %!> 로 컬럼마다 모든 디비들 다 얻게ㅐ
	
	request.setCharacterEncoding("utf-8");
	
	// 관리자만 수정가능하게
	if ( session.getAttribute("active").equals("Y")) {	
	System.out.println("관리자입니다");

	} else { 		
		System.out.println("관리자가 아닙니다");
		
		response.sendRedirect(request.getContextPath() + "/index.jsp"); // 아니면 인덱스 ㄱㄱ
		return;}
	
	
	// 그 여기서 EmployeeDao에게 currentPage와 rowPerPage 넘겨주기
	
	int currentPage = 1; // 어디서부터 시작?
	final int rowPerPage = 5; // 한페이지당 몇개?
	int lastPage=0;
	
	if(request.getParameter("currentPage")!=null) {
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}
			
	
	
	EmployeeService employeeService = new EmployeeService();
	
	ArrayList<Employee> list = employeeService.getEmployeeList(rowPerPage, currentPage);
	//			└ employee로 리턴할거양			└ 이제 이 메서드 실행시켜가지고 얻은 값들을 가지고
	lastPage = employeeService.getEmployeeListLastPage(rowPerPage);

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
<!--  뭔가 목록을 보여주고 그걸 누르면 수정가능하게? -->
<!--  그러면 셀렉트문을 실행하는 DAO / 이를CONN하는 SERVICE -->
<!--  저거SERVICE 실행하는 액션? -->


	<!--  사원목록, active값 수정 -->
	<h1> 사원리스트 </h1>

	<table border="1">
	<thead>
		<tr>
			<td>employeeId</td>
			<td>employeeName</td>	
			<td>updateDate</td>	
			<td>createDate</td>
			<td>active</td>			
		</tr>
	</thead>
	
	
	<tbody>
	
	<%
		for(Employee e : list) {
	%>
	
	<tr>
	
		<td><%=e.getEmployeeId() %></td>
		<td><%=e.getEmployeeName() %></td>
		<td><%=e.getUpdateDate() %></td>
		<td><%=e.getCreateDate() %></td>
		<td><%=e.getActive() %></td>
		
		<td>
		<form action ="<%=request.getContextPath()%>/admin/adminListEditAction.jsp" method="post">
		<input type="hidden" name="employeeId" value="<%=e.getEmployeeId() %>">
			<select name="active">
				   <%
				      if(e.getActive().equals("N")) {
				   %>
				         <option>Y</option>
				         <option selected="selected">N</option>
				   <%
				      } else {
				   %>
				         <option selected="selected">Y</option>
				         <option>N</option>
				   <%
				      }
				   %>
			</select>
			<button type="submit"> 수정하기</button>
			</form>
		</td>
		<%
		}
	%>
	</tr>
	</tbody>
	</table>
	
	
	<!--  페이징 -->
	<% 
	if( currentPage >1 ) {
	%>
	
		<a href="<%=request.getContextPath()%>/admin/employeeList.jsp?currentPage=<%=currentPage-1%>"> 이전 </a>
	
	<% 
	}
	
	if ( currentPage <lastPage) {	
	%>
	
	<a href="<%=request.getContextPath()%>/admin/employeeList.jsp?currentPage=<%=currentPage+1%>"> 다음 </a>

	<%
	}
	%>
	
	
</body>
</html>