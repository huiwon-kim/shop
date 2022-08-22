<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import ="vo.*" %>
<%@ page import ="service.*" %>


<%
	// 인코딩
	request.setCharacterEncoding("utf-8");
	
	// 변수 받아오기
	String employeeId = request.getParameter("employeeId");
	String employeePass = request.getParameter("employeePass");
	String employeeName = request.getParameter("employeeName");

	
	
	// Employee 객체 생성 후 값들 넣어주기	
	Employee paramEmployee = new Employee();
	paramEmployee.setEmployeeId(employeeId);
	paramEmployee.setEmployeePass(employeePass);
	paramEmployee.setEmployeeName(employeeName);
	
	
	// 디버깅
	System.out.println(paramEmployee +"<-paramEmployee");
	
	// CustomerService 객체 생성 후 메서드실행수 Customer객체 넣어주기
	EmployeeService employeeService = new EmployeeService();
	employeeService.signInEmployee(paramEmployee);
	
	
	response.sendRedirect(request.getContextPath()+"/index.jsp");

%>