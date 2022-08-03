<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page import="model.MemberDao"%>
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
	Employee employee = new Employee();
	employee.setEmployeeId(employeeId);
	employee.setEmployeePass(employeePass);
	
	// 디버깅
	System.out.println(employeeId+ "<-customerId");
	System.out.println(employeePass+ "<-customerPass");
	
	
	// MemberDao 객체 만들기
	MemberDao memberDao = new MemberDao();
	System.out.println(memberDao +"<-memberDao");
 
	
	// login 메서드 사용
	Employee login = memberDao.Elogin(employee);
	
 
	if(login !=null) {
		System.out.println("성공");		
		session.setAttribute("user", "employee"); //세션에 넣는거 
		session.setAttribute("id", login.getEmployeeId());
		session.setAttribute("name", login.getEmployeeName());
		response.sendRedirect(request.getContextPath()+"/index.jsp");	// 다이렉트
		
	}else { // 로그인 실패
		
		response.sendRedirect(request.getContextPath()+"/loginForm.jsp");
		
		System.out.println("실패");		
	}
	
	
	
 %>