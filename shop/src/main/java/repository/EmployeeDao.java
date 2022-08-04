package repository;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vo.Employee;

public class EmployeeDao {
	
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
		 
		 String sql = "SELECT \r\n"
		 		+ "	   employee_id employeeId,\r\n"
		 		+ "	   employee_pass employeePass,\r\n"
		 		+ "	   employee_name employeeName\r\n"
		 		+ "	   FROM employee \r\n"
		 		+ "	   WHERE\r\n"
		 		+ "	 	employee_id=? AND employee_pass=PASSWORD(?) AND active='Y'";
		
		
		 PreparedStatement stmt = null;
	
		 ResultSet rs = null;
		 
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

				}
		 
		 } finally {
			 stmt.close();
			 rs.close();
		 }
		
		return loginEmployee;
	}
	
	
}
