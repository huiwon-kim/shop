package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vo.*;

public class SignDao {
	
	/*
	// 아이디체크 (ajax & 제이쿼리)
	public String selectIdCheck(Connection conn, String ckId) throws Exception {
		String id = null;
		
		/*
		SELECT t.id
		FROM
		(SELECT customer_id id FROM customer
		UNION
		SELECT employee_id id FROM employee
		UNION
		SELECT out_id id FROM  outid) t
		WHERE t.id = ?
		
		String sql ="SELECT t.id "
				+ "FROM "
				+ "(SELECT customer_id id "
				+ "FROM customer UNION "
				+ "SELECT employee_id id "
				+ "FROM employee UNION "
				+ "SELECT out_id id "
				+ "FROM outid) t "
				+ "WHERE t.id = ?";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;		
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, ckId);
			rs = stmt.executeQuery();
			
				if(rs.next()) {
					id = rs.getString("t.id");
					//id = rs.getString("1");
					
				}
		} finally {
			if(rs != null) {rs.close();}		
			if(stmt != null) {stmt.close();}
		}
		return id;//동일한 아이디가 있으면 id가 리턴 아니면 null이 리턴됨
	}
*/
	
	

	
	// 아이디체크				// 커넥션을 주입하는 계층은 컨트롤러계층 다음 서비스계층에서 dao를 호출. 서비스계층에서 conn 주입
	public String idCheck(Connection conn, String id) throws SQLException {
		String ckId = null;
		
		/*
		 * t는 임시 알리언스; ()안의 결과물을 t라고 임시 이름을 붙인거 SELECT t.id FROM (SELECT customer_id id
		 * FROM customer UNION SELECT employee_id id FROM employee UNION SELECT out_id
		 * id FROM outid) t WHERE t.id=? ->> 결과물이 null일 때 사용가능한 아이디 (true리턴) !null 이면
		 * false 리턴
		 */
		
		
		
		// if(rs!=null) {}
		// close....
		
		String sql = "SELECT t.id\r\n"
				+ "		FROM 	(SELECT customer_id id FROM customer\r\n"
				+ "				UNION\r\n"
				+ "				SELECT	employee_id id FROM employee\r\n"
				+ "				UNION\r\n"
				+ "				SELECT out_id id FROM outid) t\r\n"
				+ "		WHERE t.id=?";
		// 내가 입력한 ckId가 저 3개 테이블 돌려서 있어서 값이 나오면 (select 이요해서) 중복
		PreparedStatement stmt= null;
		ResultSet rs = null;
		
		
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				ckId = rs.getString("t.id");
			}
		
			if(rs==null) {stmt.close();}
			else {rs.close();}
			
		return ckId;
	} 
}
