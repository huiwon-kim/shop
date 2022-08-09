<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import = "com.oreilly.servlet.*" %>    
<%@ page import = "com.oreilly.servlet.multipart.*" %>   
<%@ page import = "service.GoodsService" %> 
<!--  └액션에서 하나의 서비스 호출 >> 하나의 서비스로 이곳저곳 다 집어넣나본데 -->

<%@ page import = "vo.Goods" %> 
<%@ page import = "vo.*" %> 
<%@ page import ="java.io.File.*" %>
<%@page import="java.io.File"%>
<%@page import="java.net.URLEncoder"%>

<%
	// ※ Dynamic Web Project - fileupload 참조
	
	
	request.setCharacterEncoding("UTF-8");
	
	// 이미지 저장될 경로 설정
	String dir = request.getServletContext().getRealPath("/upload");
	// 						└톰캣 폴더 안의 				└ upload 폴더
	// 디버깅
	System.out.println(dir); 
	
	// 이미지 용량 기준 설정
	int max= 10*1024*1024; 	
	MultipartRequest mRequest = new MultipartRequest(request, dir, max, "UTF-8", new DefaultFileRenamePolicy()); 

	
	// 변수받아오기
	String goodsName = mRequest.getParameter("goodsName");
	System.out.println(goodsName + "<-goodsName"); 
	
	int goodsPrice = Integer.parseInt(mRequest.getParameter("goodsPrice"));	
	String contentType = mRequest.getContentType("imgfile");
	
	String originFileName = mRequest.getOriginalFileName("imgfile");
	String systemFilename = mRequest.getFilesystemName("imgfile");
	String imgName = mRequest.getFilesystemName("imgfile");
	String imgType = mRequest.getContentType("imgfile");
	
	
	//디버깅
	System.out.println(dir +"<-dir"); 
	System.out.println(goodsName + "<-goodsName"); 
	System.out.println(goodsPrice + "<-goodsPrice"); 
	System.out.println(contentType + "<-contentType"); 
	System.out.println(originFileName + "<-originFileName");
	System.out.println(systemFilename + "<-systemFilename");
	System.out.println(imgName + "<-imgName");
	System.out.println(imgType + "<-imgType");
	
	
	//변수받아오기	
	// 확장자가 img 친구들 일때만 저장할래
	if ( !( imgType.equals("image/gif") || imgType.equals("image/png") || imgType.equals("image/jpeg") ||
			imgType.equals("image/jpg")) ) {
				
		// 이미 업로드된 파일을 삭제 (이미지 파일 아닌 애들만)
		File f= new File(dir+"\\"+imgName);
		if(f.exists()){
			f.delete();	// 얘도 트루펄스 리턴
		}	
		return;
	}
	
	

	
	Goods goods = new Goods();
	GoodsImg goodsImg = new GoodsImg();
	
	goods.setGoodsName(goodsName);
	goods.setGoodsPrice(goodsPrice);
	goodsImg.setContentType(contentType);
	goodsImg.setOriginFileName(originFileName);
	goodsImg.setFileName(systemFilename);

	
	GoodsService goodsService= new GoodsService();
	int k= goodsService.addGoods(goods, goodsImg);
		
	if(k ==0) {
		System.out.println("상품 업로드 실패");
		response.sendRedirect(request.getContextPath()+"/admin/adminGoodsList.jsp");
	} else  {
		System.out.println("상품 업로드 성공");
		response.sendRedirect(request.getContextPath()+"/admin/adminGoodsList.jsp");
		}
	
	
		
%>