<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@ page import="vo.*" %>  
<%@ page import = "service.*" %>


 <%
 	// 변수 받아오기
 	String ckId = request.getParameter("ckId");  
 
 	// 디버깅
 	System.out.println(ckId + "<-ckId");
 
 	
 	// check 용 id 객체 만들기 	
 	Id ckid = new Id();
 	ckid.setId(ckId);
 	
 	
 	// SignService 객체 사용
 	SignService signService = new SignService(); 	 
 	
 	//String result1 =signService.idCheck(ckId);
 	//request.getParameter("result1");
 	
 	boolean result =signService.idCheck(ckId);
 	System.out.println(result +"<-result");		
 			
 	
 	
 	
 	if(result == false ){
 	// 둘중 하나를 sendRedirect 할거야 addCustomer 	
 	// service -> false
 	response.sendRedirect(request.getContextPath()+"/addCustomer.jsp?errorMsg=already exists Id");
 	// 														└ 사용할 수 없다면 이렇게 넘길거양
 	}  else {
 	// service -> true
 	response.sendRedirect(request.getContextPath()+"/addCustomer.jsp?ckId="+ckId);
	//														└ 사용할 수 있다면 이렇게 넘길거양, 사용할 수 있으니까 그대로 Id넘겨주라고
 
 	}
 
 
 
 
 
 %>