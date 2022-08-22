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
	
	// 그 여기서 EmployeeDao에게 currentPage와 rowPerPage 넘겨주기
	
	int currentPage = 1; // 어디서부터 시작?
	final int rowPerPage = 10; // 한페이지당 몇개?
	
	if(request.getParameter("currentPage")!=null) {
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}
			
	
	
	EmployeeDao employeeDao = new EmployeeDao();
	// EmployeeDao에는 employee를 셀렉하는 메서드가 존재함
	EmployeeService employeeService = new EmployeeService();
	
	ArrayList<Employee> list = employeeService.getEmployeeList(rowPerPage, currentPage);
	//			└ employee로 리턴할거양			└ 이제 이 메서드 실행시켜가지고 얻은 값들을 가지고
	

			
	
		
	
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!--  뭔가 목록을 보여주고 그걸 누르면 수정가능하게? -->
<!--  그러면 셀렉트문을 실행하는 DAO / 이를CONN하는 SERVICE -->
<!--  저거SERVICE 실행하는 액션? -->
	<h1> 사원리스트 </h1>

	<table border="1">
	<tr>
		<td>employee_id</td>
		<td><%= %></td>
	</tr>
	<tr>
		<td>employee_pass</td>
		<td><%= %></td>
	</tr>
	<tr>
		<td>employee_name</td>
		<td><%= %></td>
	</tr>
	
	</table>
</body>
</html>