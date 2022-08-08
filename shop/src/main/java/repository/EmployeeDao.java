package repository;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.*;

public class EmployeeDao {
	
	
	
	public int EmployeelastPage(Connection conn) throws SQLException {
		
		String sql ="SELECT COUNT(*) FROM employee";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int totalCount = 0;
		
		
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				totalCount = rs.getInt("COUNT(*)");
			}
			
			System.out.print(totalCount +"Employee의 dao");
			
		}finally {
			
			if(rs!=null) {rs.close();}
			if(rs!=null) {stmt.close();}
		}
		
		return totalCount;
		
	}
	
	
	
	// 사원 상세보기
	// 그 테이블의 값들을 전부 가져와야함
	public ArrayList<Employee> SelectEmployeeList(Connection conn, int rowPerPage, int beginRow) throws SQLException  {
		ArrayList<Employee> list = new ArrayList<Employee>();
		
		/*
		 SELECT 
		 employee_id,
		 employee_pass,
		 employee_name,
		 update_date,
		 create_date,
		 active
		 FROM employee
		 ORDER BY crate_date DESC LIMIT ?, ?;
		 */
		
		
	

		// 그냥 employee 리스트의 값 다 받아오는거니까
		String sql =" SELECT \r\n"
				+ "		 employee_id,\r\n"
				+ "		 employee_pass,\r\n"
				+ "		 employee_name,\r\n"
				+ "		 update_date,\r\n"
				+ "		 create_date,\r\n"
				+ "		 active\r\n"
				+ "		 FROM employee\r\n"
				+ "		 ORDER BY create_date DESC LIMIT ?, ?";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Employee employee = new Employee();
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				employee = new Employee();
				employee.setEmployeeId(rs.getString("employee_id"));
				employee.setEmployeePass(rs.getString("employee_pass"));
				employee.setEmployeeName(rs.getString("employee_name"));
				employee.setCreateDate(rs.getString("create_date"));
				employee.setUpdateDate(rs.getString("update_date"));
				employee.setActive(rs.getString("active"));
				list.add(employee);
				
				System.out.println(list+ "<- Employee의 list<-");
			}
			
		} finally {
			if(rs!=null) {rs.close();}  			
			if(stmt!=null) {stmt.close();}
		}
		return list;
		
	}
	
	
	
	
	// 회원가입
	public int insertEmployee(Connection conn, Employee paramEmployee) throws SQLException {
		int row = 0;
		
		/*
		 INSERT INTO Employee (
		 employee_id,
		 employee_pass,
		 employee_name,
		 update_date updateDate,
		 create_date createDate,
		 active) VALUES (
		 ?, ?, ?, ?, ?, NOW(), NOW(), 'N');
		 */
		
		String sql =" INSERT INTO employee (\r\n"
				+ "		 employee_id,\r\n"
				+ "		 employee_pass,\r\n"
				+ "		 employee_name,\r\n"
				+ "		 update_date,"
				+ "		 create_date"
				+ "		 ) VALUES (\r\n"
				+ "		 ?, PASSWORD(?), ?, NOW(), NOW())";
		
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, paramEmployee.getEmployeeId());
			stmt.setString(2, paramEmployee.getEmployeePass());
			stmt.setString(3, paramEmployee.getEmployeeName());

			row = stmt.executeUpdate();
		} finally {
			stmt.close();
		}
		return row;
	}
	
	
	
	
	// 탈퇴	
	public int deleteEmployee(Connection conn, Employee paramEmployee) throws SQLException {
		
		int row=0;
		
		/*
		 DELETE FROM employee WHERE employee_id AND employee_pass=PASSWORD(?)
		 * */
		String sql ="DELETE FROM employee WHERE employee_id=? AND employee_pass=PASSWORD(?)";
		
		 PreparedStatement stmt = null;
		 
		 
		 try {
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, paramEmployee.getEmployeeId());			
			stmt.setString(2, paramEmployee.getEmployeePass());
		 
			row = stmt.executeUpdate();
			
		 } finally {
			stmt.close();
		 }
		 
		 		 
		 return row;
		 
	 }
	 
		
	
	
	// 로그인
	public Employee selectEmployee(Connection conn, Employee paramemployee) throws SQLException {
		Employee loginEmployee = null;
		
		 /* SELECT 
		   employee_id employeeId,
		   employee_pass employeePass,
		   employee_name employeeName
		   FROM employee 
		   WHERE
		 	employee_id=? AND employee_pass=PASSWORD(?) AND active='Y'; 
		 */ 
		
		 
		 String sql = "SELECT"
		 		+ "	   employee_id employeeId,"
		 		+ "	   employee_pass employeePass,"
		 		+ "	   employee_name employeeName,"
		 		+ "		update_date updateDate,"
		 		+ "		create_date createDate,"		 		
		 		+ "		active active"
		 		+ "	   FROM employee"
		 		+ "	   WHERE\r\n"
		 		+ "	 	employee_id=? AND employee_pass=PASSWORD(?) AND active='Y'";
		
		
		 PreparedStatement stmt = null;
	
		 ResultSet rs= null;
		 
		 try {
			 
			 stmt = conn.prepareStatement(sql);
			 stmt.setString(1, paramemployee.getEmployeeId());
			 stmt.setString(2, paramemployee.getEmployeePass());
			 rs = stmt.executeQuery();
		

				if (rs.next()) {
					loginEmployee = new Employee();
					loginEmployee.setEmployeeId(rs.getString("employeeId"));
					loginEmployee.setEmployeePass(rs.getString("employeePass"));
					loginEmployee.setEmployeeName(rs.getString("employeeName"));
					loginEmployee.setUpdateDate(rs.getString("updateDate"));
					loginEmployee.setCreateDate(rs.getString("createDate"));
					loginEmployee.setActive(rs.getString("active"));
					
					System.out.println(rs.getString("employeeName"));
					System.out.println(rs.getString("createDate"));
					System.out.println(rs.getString("active"));
				}
		 
		 } finally {
			 stmt.close();
			 rs.close();
		 }
		
		return loginEmployee;
	}
	
	
}
