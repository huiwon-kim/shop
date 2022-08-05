<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!--  id ck Form -->
	<h1> 고객 회원가입 </h1>
	<form action="<%=request.getContextPath()%>/idCheckAction.jsp" method="post">
		<div>
			ID 체크
			<input type="text" name="ckId">
			<button type="submit"> 아이디중복검사 </button>	<!-- 버튼누르면 액션을 idcheck 액션호출하고 -->
		</div>
	</form>
	
	<!-- 자스를 이용해 위에서 중복검사로 허락 받은 아디가 밑에 자동 기록되게 할거야! -->
	<!-- idCheckAction에서 true가 들어오면 -->
	
	<%
		String ckId="";
		if(request.getParameter("ckId") !=null) {
				ckId = request.getParameter("ckId"); // 액션 지나서 낫눌이라 넘어온 아이디 ★
		}
	%>
	
	
	<!--  고객가입 Form -->	
	<form action="<%=request.getContextPath()%>/idAddAction.jsp" method="post">
		<table border="1">
			<tr>
				<td> customerId </td>
				<td><input type="text" name="customerId" id="customerId" 
				readonly="readonly" value="<%=ckId%>"> <!-- ★이 여기로 기입  -->
				</td>
			</tr>
			<tr>					
				<td> customerPass </td>
				<td> <input type="password" name="customerPass" id="customerPass"> </td>
			</tr>	
			<tr>
				<td> customerName </td>
				<td> <input type="text" name="customerName" id="customerName"> </td>
			</tr>
			<tr>	
				<td> customerAddress</td>
				<td> <input type="text" name="customerAddress" id="customerAddress"> </td>
			</tr>
			<tr>	
				<td> customerTelephone </td>
				<td> <input type="text" name="customerTelephone" id="customerTelephone"
				placeholder="only number input"> </td>

			</tr>
		</table>
		<button type="submit"> 회원가입 </button>
	</form>
</body>
</html>