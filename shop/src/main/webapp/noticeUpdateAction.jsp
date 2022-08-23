<%@page import="service.NoticeService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.*"%>
<%@page import="vo.*"%>
<%@page import="repository.*"%>  
  
<%
	request.setCharacterEncoding("utf-8");

	int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
	System.out.println(noticeNo + "<-noticeNonoticeNo");
	
	String noticeTitle = request.getParameter("noticeTitle");
	
	String noticeWriter = request.getParameter("noticeWriter");
	String createDate = request.getParameter("createDate");
	String noticeContent = request.getParameter("noticeContent");
	String noticePass= request.getParameter("noticePass");
	
	Notice parmanotice = new Notice();
	
	parmanotice.setNoticeNo(noticeNo);
	parmanotice.setNoticeTitle(noticeTitle);
	parmanotice.setNoticeContent(noticeContent);
	parmanotice.setWriter(noticeWriter);
	parmanotice.setNoticePass(noticePass);
	
	NoticeService noticeService = new NoticeService();
	
	int k= 0;
	k = noticeService.motifyNotice(parmanotice);
	
	
	
	if(k ==0) {
		System.out.println("공지사항수정실패!");
		response.sendRedirect(request.getContextPath()+"/noticeList.jsp");
		
	} else{
		System.out.println("공지사항수정성공!");
	
		response.sendRedirect(request.getContextPath()+"/noticeList.jsp");
	}

%>