package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import repository.EmployeeDao;
import repository.OutIdDao;
import vo.*;


public class EmployeeService {

	
	public int getEmployeeListLastPage(int rowPerPage) {		
		Connection conn = null;
		
		try {
			conn = new DBUtil().getConnection();
			conn.setAutoCommit(false); //자동커밋방지	
			
			EmployeeDao employeeDao = new EmployeeDao();
			rowPerPage = employeeDao.EmployeelastPage(conn);
			
			System.out.print(rowPerPage +"<-EmployeeService의 rowPerPage");
			
			
			if(rowPerPage ==0) {
				throw new Exception();
			}
			conn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return rowPerPage;
		
	}
	
	
	public ArrayList<Employee> getEmployeeList(int rowPerPage, int currentPage) {

		// int rowPerPage, int currentPage 받아서
		// 여기서 beginRow를 가져오래
		
	
		ArrayList<Employee> list = new ArrayList<Employee>();
		Connection conn = null;
		int beginRow = (currentPage -1 ) * rowPerPage;
		
		
		
		try {
			conn = new DBUtil().getConnection();
			EmployeeDao employeeDao = new EmployeeDao();
			
			list = employeeDao.SelectEmployeeList(conn, rowPerPage, beginRow);
			
			System.out.println(list +"<-EmployeeService의 list");
			
			if(list==null) {
				throw new Exception();
			}
				conn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		return list;
		
	}
	
	
	public void signInEmployee(Employee paramEmployee )  {
		Connection conn = null;
		
		try {
			conn = new DBUtil().getConnection();
			conn.setAutoCommit(false); // 자동커밋 방지
			
			EmployeeDao employeeDao = new EmployeeDao();
			
			try {
				employeeDao.insertEmployee(conn, paramEmployee);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			conn.commit();
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
	public boolean removeEmployee (Employee paramEmployee) {
		
		Connection conn = null;
		
		try {
			conn = new DBUtil().getConnection();
			conn.setAutoCommit(false);
	
			EmployeeDao employeeDao = new EmployeeDao();
			if(employeeDao.deleteEmployee(conn, paramEmployee) !=1) { 
				throw new Exception();
			}
			OutIdDao OutIdDao = new OutIdDao();
			if(OutIdDao.insertOutId(conn, paramEmployee.getEmployeeId()) !=1) {
					// 예외 >   try, catch, finally, throw, throws 중 throw가 예외발생
					throw new Exception();
				}
			conn.commit(); // 위에 둘다 같은 conn 실행시켜서 둘다 실패 없을시 드디어 커밋 ★★★★★
		} catch (Exception e) {
			e.printStackTrace();// 콘솔창에 예외메시지 출력 / 기입 必
			try {
				conn.rollback(); // CustomerDao OutIdDao 예외나면 롤백
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return false;
		} finally {
				try {
					conn.close(); // 예외가 나든 안나든 클로스는 이루어지고
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return true;
		}
	
	
	public Employee loginEmployee (Employee paramEmployee) {
		Connection conn = null;
		
		Employee employee = new Employee(); 
		// 로그인액션에서 리턴값이 Employee니까 이거 넘겨줄 때도 Employee의 형태
		
		
		try {
			conn = new DBUtil().getConnection();
			conn.setAutoCommit(false);
			
			EmployeeDao employeeDao = new EmployeeDao();
			employee = employeeDao.selectEmployee(conn, paramEmployee);
			
			conn.commit();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return employee;
		
	}
	
}
