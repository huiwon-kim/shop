<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import = "controller.*"%>
<%@ page import = "repository.*"%>
<%@ page import = "service.*"%>
<%@ page import = "vo.*"%>
<%@page import="java.io.File"%>


<%

	// 인코딩
	request.setCharacterEncoding("UTF-8");
	
	// 파일 경로지정
	String dir = request.getServletContext().getRealPath("/upload");
	System.out.println(dir);
	
	
	// cos 이용한 파일업로드 셋팅
	int max= 10 * 1024 * 1024; 
	MultipartRequest mRequest = new MultipartRequest(request, dir, max, "UTF-8", new DefaultFileRenamePolicy()); 

	
	// 변수받아오기
	int goodsNo =Integer.parseInt( mRequest.getParameter("goodsNo"));
	String goodsName = mRequest.getParameter("goodsName");	
	String preimgName = mRequest.getParameter("preimgName"); // 기존에 있던 이미지를 지워야하니까 이를 받아주고
	
	int goodsPrice = Integer.parseInt(mRequest.getParameter("goodsPrice"));
	String contentType = mRequest.getContentType("imgfile");
	
	String originFileName = mRequest.getOriginalFileName("imgfile");
	String filename = mRequest.getFilesystemName("imgfile");
	
	String soldOut = mRequest.getParameter("soldOut");

	
	
	// 디버깅
	System.out.println(goodsNo +"<-goodsNo");
	System.out.println(goodsName +"<-goodsName");
	System.out.println(preimgName +"<-imgfile(인데 preimgName로 받아온)");
	System.out.println(goodsPrice +"<-goodsPrice");
	System.out.println(filename +"<-filename(imgfile을 받아와서 셋팅함)");
	System.out.println(contentType +"<-contentType");
	System.out.println(soldOut +"<-soldOut");
	
	
	// 기존에 만약 같은 이름의 파일이 있다면 지워주기
	File f= new File(dir+"\\"+preimgName);
	if(f.exists()){
		f.delete();	// 얘도 트루펄스 리턴
	}
	
	
	
	// 객체 생서후 불러온 변수 넣어주기

	Goods goods = new Goods();
	GoodsImg goodsImg = new GoodsImg();

	
	goods.setGoodsNo(goodsNo);
	goods.setGoodsName(goodsName);
	goods.setGoodsPrice(goodsPrice);
	goods.setSoldOut(soldOut);
	goodsImg.setGoodsNo(goodsNo); // 그냥 담긴걸 셋팅한거래
	goodsImg.setContentType(contentType);
	goodsImg.setOriginFileName(originFileName);
	goodsImg.setFileName(filename);
	
	
	GoodsService goodsService= new GoodsService();
	int k = goodsService.updateGoods(goods, goodsImg);
	
	// 적용 및 콘솔출력

	
	if(k ==0) {
		System.out.println("상품 수정 실패");
		response.sendRedirect(request.getContextPath()+"/admin/adminGoodsList.jsp");
	} else {
		System.out.println("상품 수정 성공");
		response.sendRedirect(request.getContextPath()+"/admin/adminGoodsList.jsp");
	}




%>