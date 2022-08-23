<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.*"%>
<%@page import="vo.*"%>
<%@page import="repository.*"%>
<%@page import="service.CustomerService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	// 인코딩
	request.getParameter("utf-8");


	if(session.getAttribute("user") == null) { // 로긴한 애가 아니면
		response.sendRedirect(request.getContextPath()+"/loginForm.jsp");
			
		return;
	} else { // 아니면 회정상세보기 ㄱㄱ
	
		
	// 이전페이지서 넘어온 변수 받기	
	String customerId = request.getParameter("customerId");
	
	// 디버깅
	System.out.println(customerId+"<-info 25번째줄");
	

	// 변수담을 vo Customer, 메서듯 ㅣㄹ행시킬 의 객체 생성
	Customer customer = new Customer();	
	CustomerService customerService = new CustomerService();
	customer = customerService.getCustomermore(customerId);
	//customer.setCustomerId(customerId);
	

	
	// 쿼리 돌릴 메서드를 실행시키기 위해 service 객체 만들기

	//List<Customer> list = new ArrayList<Customer>();
	
	
	// 디버깅
	System.out.println(customer+"마지막customercustomercustomercustomer");


	}
	
%>    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%=session.getAttribute("user") %> <!--  customer / employee -->
	<br>
	<%=session.getAttribute("id") %> <!-- 로그인 아이디 -->
	<br>
	<%=session.getAttribute("name") %> <!-- 로그인 이름 -->
	<br>
	<a href="<%=request.getContextPath()%>/logout.jsp"> 로그아웃 </a>
	<a href="<%=request.getContextPath()%>/customerinfo.jsp?customerId=<%=session.getAttribute("id") %>">  회원정보상세보기 </a>
	<a href="<%=request.getContextPath()%>/remove<%=session.getAttribute("user")%>Form.jsp"> 회원탈퇴 </a>
	
	
<h1> 회원정보 상세보기 </h1>
<table border="1">
<tr>
	<th>
		Customer Id : <%= request.getParameter("customerId") %>
	</th>
</tr>


<tr>	
	<th>
		
	</th>
</tr>

</table>




</body>
</html>