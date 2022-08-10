package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import service.*;

@WebServlet("/idckController")
public class IdCkController extends HttpServlet {
	private SignService signService;
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		
		// SignrService 객체만들기
		this.signService = new SignService();
		
		String ckId = request.getParameter("ckId");
		// └ 아이디 체크 눌렀을 때 이 값을 받아야해
		
		System.out.println(ckId + " <-- ckId");
		
		String id = this.signService.getIdCheck(ckId);
		// id -> null -> idck사용가능 -> yes
		// id -> select값 -> idck사용불가 -> no
		
		Gson gson = new Gson();  // 지금은 단순문자열이라 지손가지고 할 일은 없대
		String jsonStr = ""; // jsonStr을 보낼건데
		if(id == null) {
			jsonStr = gson.toJson("y"); // 사용가능하면 y보낼거양
		} else {
			jsonStr = gson.toJson("n"); // 아니면 n보낼거양
		}
		
		PrintWriter out = response.getWriter();
		out.write(jsonStr);
		out.flush();
		out.close();
	}
}
