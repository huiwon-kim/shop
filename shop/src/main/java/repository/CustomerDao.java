package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import service.DBUtil;
import vo.Customer;
import vo.Notice;

//여기는 어제와 같은 dao같아 그 sql문만 처리하는
public class CustomerDao { 

	
	// 회정상세보기(회원용)
	public Customer selectCustomermore(Connection conn, String customerId) throws Exception {
		
		
		/*
		
		SELECT
		customer_id customerId,
		customer_pass customerPass,
		customer_name customerName,
		customer_address customerAddress,
		customer_telephone customerTelephone,
		update_date updateDate,
		create_date createDate
		FROM customer
		
		*/
	
		String sql="		SELECT"
				+ "		customer_id,"
				+ "		customer_pass,"
				+ "		customer_name,"
				+ "		customer_address,"
				+ "		customer_telephone,"
				+ "		customer_dedatiladdr,"
				+ "		update_date,\r\n"
				+ "		create_date\r\n"
				+ "		FROM customer"
				+ "		WHERE customer_id=?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Customer paramcustomer = new Customer();
		
		
		
		try {
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, customerId);	
		rs = stmt.executeQuery();
		
			while(rs.next()) {
				// 맞다면 (쿼리에서 찾는다면 paramcustomer에 저장할거양)
				paramcustomer = new Customer();
				paramcustomer.setCustomerId(rs.getString("customer_id"));
				paramcustomer.setCustomerName(rs.getString("customer_name"));
				paramcustomer.setCustomerAddress(rs.getString("customer_address"));
				paramcustomer.setCustomerDetailAddress(rs.getString("customer_dedatiladdr"));
				paramcustomer.setCustomerTelephone(rs.getString("customer_telephone"));
				paramcustomer.setUpdateDate(rs.getString("update_date"));
				paramcustomer.setCreateDate(rs.getString("create_date"));
			
	
				// 디버깅
				System.out.println(paramcustomer+"<-리턴할 selectCustomermore paramcustomer에 담긴 것들");
			}
			
		} finally {
			if(rs!=null) {rs.close();}
			if(stmt!=null) {stmt.close();}
		}
		
		
		return paramcustomer;
	}
	
	
	
	 // 회원가입	
	public int insertCustomer(Connection conn, Customer paramCustomer) throws SQLException {
		int row = 0;
		
		/*
		 INSERT INTO customer (
		 customer_id customerId,
		 customer_pass customerPass,
		 customer_name customerName,
		 customer_address customerAddress,
		 customer_telephone customerTelephone,
		 update_date updateDate,
		 create_date createDate) VALUES (
		 ?, ?, ?, ?, ?, NOW(), NOW());
		 */
		
		String sql = 
				"		 INSERT INTO customer (\r\n"
				+ "customer_id,\r\n"
				+ "customer_pass,\r\n"
				+ "customer_name,\r\n"
				+ "customer_address,\r\n"
				+ "customer_dedatiladdr,\r\n"
				+ "customer_telephone,\r\n"
				+ "update_date,\r\n"
				+ "create_date) VALUES (\r\n"
				+ "?, PASSWORD(?), ?, ?, ?, ?,  NOW(), NOW())";
		
		PreparedStatement stmt = null;
	
		
		
		try {
			 stmt = conn.prepareStatement(sql);
			 stmt.setString(1, paramCustomer.getCustomerId());
			 stmt.setString(2, paramCustomer.getCustomerPass());
			 stmt.setString(3, paramCustomer.getCustomerName());
			 stmt.setString(4, paramCustomer.getCustomerAddress());
			 stmt.setString(5, paramCustomer.getCustomerDetailAddress());
			 stmt.setString(6, paramCustomer.getCustomerTelephone());
			
			 row = stmt.executeUpdate(); // 성공하면 1 / 0이
		} finally {		
			stmt.close();
			
		}
		return row;
	}
	
			 
	 // 탈퇴
	 // CustomerService.removeCustomer(Customer paramCustomer)가 호출
	 public int deleteCustomer(Connection conn, Customer paramCustomer) throws SQLException {
		 //						└★★★
		 // 동일한 Cconn( OutIdDao 클래스속 insertOutId가 가진)
		 // >> 근데 그게 불가능함 >>> 내가 만들어서 넣어줘야한대 (근데 클로즈는 하면 안된대)
		 // 나눠쓴 콘중 한명이 실패시 그냥 롤백 ㄱ / 둘다 성공시 커밋
		 // 둘다 커넥션 콘 동일하게 쓰기 위해 내가 매개변수를 넘겨줘버린다 ★★★가 생긴 이유
		 // 얘는 처리한대 클로즈한다
		 
		 
		 // conn.close() [X]
		 
		 
		 /*
		  DELETE FROM customer WHERE customer_id=? AND customer_pass=PASSWORD(?) 
		  * */
		 int row = 0;
		 
		 String sql =" DELETE FROM customer WHERE customer_id=? AND customer_pass=PASSWORD(?)";

		 PreparedStatement stmt = null;
		 ResultSet rs = null;
		 
		 try {
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, paramCustomer.getCustomerId());			
			stmt.setString(2, paramCustomer.getCustomerPass());
		 
			row = stmt.executeUpdate();
			
		 } finally {
			stmt.close();
		 }
		 
		 		 
		 return row;
		 
		 //>>>  insert outid; delete customer 두가지 일을 다 한다는건 호출하는 입자임
		 //>>> 레파짓토리 입장에선 순수하게 딜리트 커스토머마 ㄴ가지고 접근한데
	 }
	 
	 
	 
	 
	 // 로그인
	 public Customer selectCustomer(Connection conn, Customer paramcustomer) throws Exception {
			Customer loginCustomer = null;
			
			/*
			  SELECT 
			  customer_id customerId, 
			  customer_pass customerPass, 
			  customer_name customerName
			  FROM customer WHERE
			  customer_id=? AND customer_pass=PASSWORD(?)
			
			 */
			
			String sql ="SELECT \r\n"
					+ "			  customer_id customerId,"
					+ "			  customer_pass customerPass,"
					+ "			  customer_name customerName"
					+ "			  FROM customer WHERE\r\n"
					+ "			  customer_id=? AND customer_pass=PASSWORD(?)";
	 
		
			PreparedStatement stmt = null;
		
			ResultSet rs = null;
			
			try {
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, paramcustomer.getCustomerId());
				stmt.setString(2, paramcustomer.getCustomerPass());
				rs = stmt.executeQuery();

				if (rs.next()) {
					loginCustomer = new Customer();
					loginCustomer.setCustomerId(rs.getString("customerId"));
					loginCustomer.setCustomerPass(rs.getString("customerPass"));
					loginCustomer.setCustomerName(rs.getString("customerName"));
								
				}
			} finally {
				stmt.close();
				rs.close();
			}
			return loginCustomer;
	 }
}