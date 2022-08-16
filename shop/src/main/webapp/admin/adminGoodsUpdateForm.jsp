<%@page import="java.util.Map"%>
<%@page import="service.GoodsService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import = "vo.*" %>    
<%@ page import = "controller.*" %>       
<%@ page import = "controller.*" %>
<%@ page import = "repository.*" %>  

<%

	// 변수 받아오기 (adminOrderList에서)
	int goodsNo = Integer.parseInt(request.getParameter("goodsNo")); 
	
	GoodsService goodsService = new GoodsService();
	Map<String, Object> map = goodsService.getGoodsAndImgOne(goodsNo);

	
%>


        
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form id="imgForm" action="<%=request.getContextPath() %>/admin/updateGoodsAction.jsp"
		method="post" enctype="multipart/form-data"><!--  바이너리는 무조건 포스트여야한대 겟은 ㄴㄴㄴ -->
	
	<!--  받는거 편하게 하려고 cos라이브러리 추가함 -->


<h1> 상품 수정하기 </h1>

<table border="1">
	<thead>
		<tr> 
			<td>goodsNo</td>
			<td>goodsImg</td>
			<td>goodsName</td>
			<td>goodsPrice</td>
			<td>updateDate</td>
			<td>soldOut</td>
		</tr>
	</thead>
	
	
	<tbody>
	<tr>	
	
		<td><%=map.get("goodsNo")%></td>
		<td><img src="<%=request.getContextPath()%>/upload/<%=map.get("filename")%>" width="280px" height="200px">
			<br>
			<input type="file" name="imgfile" id="imgfile">
		</td>
		<td><input type="text" name="goodsName" id="goodsName"> </td>
		<td><input type="text" name="goodsPrice" id="goodsPrice"> </td>
		<td>
			<%=map.get("updateDate")%>
			
			<input type="hidden" name="updateDate" id="updateDate" value="<%=map.get("createDate")%>">
			<input type="hidden" name="preimgName" id="preimgName" value="<%=map.get("filename")%>">
			<input type="hidden" name="goodsNo" id="goodsNo" value="<%=map.get("goodsNo")%>">
		</td>
		<td><%=map.get("soldOut")%></td>
		
		<td>

			<select name="soldOut">
			 <%
				      if(map.get("soldOut").equals("N")) {
				   %>
				         <option value="Y">Y</option>
				         <option value="N" selected="selected">N</option>
				   <%
				      } else {
				   %>
				         <option value="Y" selected="selected">Y</option>
				         <option value="N">N</option>
				   <%
				      }
				   %>	
			
			</select>	
		

		</td>
		</tr>	
	</tbody>
</table>
		<button type="submit" > 상품수정 </button>
	
	</form>
</body>
</html>