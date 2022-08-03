package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vo.Customer;
import vo.Employee;

public class MemberDao {

		
	 public Employee Elogin(Employee employee) throws Exception {
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
		 

		DBUtil dbUtil = new DBUtil();
		PreparedStatement stmt = null;
		Connection conn = null;
		ResultSet rs = null;
	 
		try {
			conn = dbUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, employee.getEmployeeId());
			stmt.setString(2, employee.getEmployeePass());
			rs = stmt.executeQuery();
			
			
			if (rs.next()) {
				loginEmployee = new Employee();
				loginEmployee.setEmployeeId(rs.getString("employeeId"));
				loginEmployee.setEmployeePass(rs.getString("employeePass"));
				loginEmployee.setEmployeeName(rs.getString("employeeName"));

			}
			
			
		} finally {
			if(rs!=null) {rs.close();} // 널이면 실행이 안함 >>> finally말고 try 내부의 오류를 보기 위해서 이 코드를 추가한대
			if(rs!=null) {stmt.close();}
			if(rs!=null) {conn.close();}
			
		}
		return loginEmployee;
		
	 }
	 
	 

	public Customer Clogin(Customer customer) throws Exception {
		Customer loginCustomer = null;

		/*
		  SELECT 
		  customer_id customerId, 
		  customer_pass customerPass, 
		  customer_name customerName
		  FROM customer WHERE
		  customer_id=? AND customer_pass=PASSWORD(?)
		
		 */

		String sql = "SELECT \r\n"
				+ "		  customer_id customerId, \r\n"
				+ "		  customer_pass customerPass, \r\n"
				+ "		  customer_name customerName\r\n"
				+ "		  FROM customer WHERE\r\n"
				+ "		  customer_id=? AND customer_pass=PASSWORD(?)";

		DBUtil dbUtil = new DBUtil();
		PreparedStatement stmt = null;
		Connection conn = null;
		ResultSet rs = null;

		try {
			conn = dbUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, customer.getCustomerId());
			stmt.setString(2, customer.getCustomerPass());
			rs = stmt.executeQuery();

			if (rs.next()) {
				loginCustomer = new Customer();
				loginCustomer.setCustomerId(rs.getString("customerId"));
				loginCustomer.setCustomerPass(rs.getString("customerPass"));
				loginCustomer.setCustomerName(rs.getString("customerName"));

			}

		} finally {
			if(rs!=null) {rs.close();}
			if(rs!=null) {stmt.close();}
			if(rs!=null) {conn.close();}

		}

		return loginCustomer;

	}

}
