package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import service.CustomerService;
import vo.OutId;

public class OutIdDao {
	
	// 탈퇴회원의 아이디를 입력 
	//CustomerService.removeCustomer(Customer paramCustomer)가 호출
	public int insertOutId(Connection conn, String customerId) throws SQLException {
		int row = 0;
		
		//						└★★★
		// 동일한 콘을 써야(CustomerDao 클래스속 deleteCustomer 얘가 가진
		 // >> 근데 그게 불가능함 >>> 내가 만들어서 넣어줘야한대 (근데 클로즈는 하면 안된대)
		 // 나눠쓴 콘중 한명이 실패시 그냥 롤백 ㄱ / 둘다 성공시 커밋
		 // 이제부턴 connection제외하고 클로스하지말래
		
		// conn.close() [X]
		// CustomerService customerService = new CustomerService (); >>> 이거 썼다가 지움
		
		/*
		 INSERT INTO 
		 outid (out_id, out_date) 
		 VALUES (?, NOW())
		 */
		
		String sql = "	 INSERT INTO \r\n"
				+ "		 outid (out_id, out_date) \r\n"
				+ "		 VALUES (?, NOW())";
		
		PreparedStatement stmt=null;
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, customerId);
			
			row = stmt.executeUpdate();
			
		} finally {
			stmt.close();
			
		}
		
		
		
		return row;
		
	}
}
