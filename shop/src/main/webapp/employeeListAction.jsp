<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import = "vo.*" %>    
<%@ page import = "service.*" %>  
<%@ page import = "repository.*" %>  
    
<%

	// 여기서 셀렉 구문...dao 가져와서
	// 회원들 목록 띄우는데 필요한 정보들 가져와서
	// employee리스트에서는 <%= %!> 로 컬럼마다 모든 디비들 다 얻게ㅐ
	
	request.setCharacterEncoding("utf-8");
	
	
	
	EmployeeService employeeService = new EmployeeService();
	employeeService.getEmployeeList(int rowPerPage, int currentPage);
			
	response.sendRedirect(request.getContextPath()+"/employeeList.jsp");
	
	
	
%>