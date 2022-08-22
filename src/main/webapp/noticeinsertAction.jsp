
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="vo.Notice"%>
<%@page import="service.*"%>
<%
	// 인코딩
	request.setCharacterEncoding("utf-8");
	

	// 이전페이지에서 넘어올 변수받기	
	String employeeId = (String)session.getAttribute("id"); // 아이디는 로그인시 세션에 저장된
	String noticeTitle= request.getParameter("noticeTitle");
	String noticePass = request.getParameter("noticePass");
	String noticeContent = request.getParameter("noticeContent");
	
	// 디버깅
	System.out.println(employeeId);
	System.out.println(noticeTitle);
	System.out.println(noticePass);
	System.out.println(noticeContent);	
	
	// 변수를 담을 객체 생성 및 변수담아주기
	Notice notice = new Notice();
	notice.setEmployeeId(employeeId);
	notice.setNoticeTitle(noticeTitle);
	notice.setNoticePass(noticePass);
	notice.setNoticeContent(noticeContent);
	

	
	// 그 객체를 다시 또 담기위한 메서드를 부르기 위해 서비스객체 생성
	NoticeService noticeService = new NoticeService();
	
	
	// 서비스 객체에 변수담은 객체 넣어주기 이후 메서드 실행시키기
	noticeService.getNotice(notice);
	
	
	
	// 편한 디버깅을 위한 리턴값 생성
	int result = 0;	
	
	
	// 리턴값에 메서드 실행시킨 리턴값 넣어주어 if로 실행여부에 따른 콘솔창 출력 및 넘어갈 화면 셍성
	result = noticeService.getNotice(notice);
	
	if(result == 0) { // 공지입력실패
		System.out.println("입력실패");
		response.sendRedirect(request.getContextPath()+"/noticeForm.jsp");	// 다이렉트
		
	} else {
		System.out.println("입력성공");
		response.sendRedirect(request.getContextPath()+"/noticeList.jsp");	// 다
	}
	


%>
