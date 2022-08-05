<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page import="repository.EmployeeDao"%>
<%@page import="service.EmployeeService"%>
<%@page import="vo.Employee"%> 
  
 <%
   
         
         
         	request.setCharacterEncoding("utf-8");
         
        	String employeeId = request.getParameter("employeeId");
        	String employeePass = request.getParameter("employeePass");
        	String employeeName = request.getParameter("employeeName");
        	
        	// 디버깅
        	System.out.println(employeeId+"<-employeeId");
        	System.out.println(employeePass+"<-employeePass");
        	
        	
        	// Employee 객체 만들기
        	Employee paramEmployee = new Employee();
        	paramEmployee.setEmployeeId(employeeId);
        	paramEmployee.setEmployeePass(employeePass);
        	
        
        	
        	// EmployeeService 객체 만들기
        	EmployeeService employeeService = new EmployeeService();
        	
        	//  메서드 사용
        	Employee login = employeeService.loginEmployee(paramEmployee);
        	
         
        	if(login !=null) {
        		System.out.println("성공");		
        		session.setAttribute("user", "Employee"); //세션에 넣는거 
        		session.setAttribute("id", login.getEmployeeId());
        		session.setAttribute("name", login.getEmployeeName());
        		response.sendRedirect(request.getContextPath()+"/adminindex.jsp");	// 다이렉트
        		
        	}else { // 로그인 실패
        		
        		response.sendRedirect(request.getContextPath()+"/loginForm.jsp");
        		
        		System.out.println("실패");		
        	}
    %>