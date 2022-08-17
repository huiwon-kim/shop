package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import repository.GoodsDao;
import repository.OrdersDao;
import vo.Orders;

public class OrdersService {

	
	// 관리자의 고객주문확인의 상세보기 수정용 (배송상태수정)
	public int modifyOrdersOne(Orders orders) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = new DBUtil().getConnection();
			conn.setAutoCommit(false);
			
			OrdersDao ordersDao = new OrdersDao();
			result = ordersDao.updateOrdersOne(conn, orders);
			
			if(result==0) { // 수정이 실패하면
				System.out.println("result==0");
				throw new Exception();
			}
			conn.commit();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return result;
		
	}
	
	
	
	
	
	// 관리자의 고객주문확인의 상세보기용
	public Map<String, Object> getOrdersOne(int orderNo) {
		Map<String, Object> m = null;
		Connection conn = null;
			
		try {
			conn = new DBUtil().getConnection(); // 디비연동
			conn.setAutoCommit(false); //자동커밋방지	
			
			OrdersDao ordersDao = new OrdersDao();
			
			m = ordersDao.selectOrdersOne(conn, orderNo);
			System.out.println(m +"<-getOrdersListByEmployee의 m");
			
			if(m ==null ) {
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
		
		return m;
	}
	
	
	
	//관리자의 고객주문확인의 페이징용
	public int getOrderListByPageLastPage(int rowPerPage) {
		Connection conn =null;
		
		
		try {
			conn = new DBUtil().getConnection(); // 디비연동
			conn.setAutoCommit(false); //자동커밋방지	
			
			OrdersDao ordersDao = new OrdersDao();
			rowPerPage = ordersDao.customerGoodsListLastPage(conn);
			
			System.out.print(rowPerPage +"<-ordersService의 rowPerPage");
			
			if(rowPerPage ==0 ) {
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
		
		return rowPerPage;
		
		
	}
	
	
	// 관리자의 고객주문확인
	public List<Map<String, Object>> getOrdersListByEmployee (final int rowPerPage, int currentPage) {
		List<Map<String, Object>> m = new ArrayList<>();;
		
		Connection conn = null;
		int beginRow = 0;	
		beginRow = (currentPage -1 ) * rowPerPage;
	
		
		try {
			conn = new DBUtil().getConnection(); // 디비연동
			conn.setAutoCommit(false); //자동커밋방지
			
			OrdersDao ordersDao = new OrdersDao();
			System.out.println(ordersDao +"<-getOrdersListByEmployee의 ordersDao");
			
			m = ordersDao.selectOrdersList(conn, rowPerPage, beginRow);
			
			System.out.println(m +"<-getOrdersListByEmployee의 m");
			
			if(m ==null ) {
				throw new Exception();
			}
		
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
		return m;
		
	}
	
	
	// 고객의 셀프주문확인의 페이징용
	public int getcustomerGoodsListByPageLastPage(int rowPerPage) {
		Connection conn=null;
		
		try {
			conn = new DBUtil().getConnection(); // 디비연동
			conn.setAutoCommit(false); //자동커밋방지	
			
			// GoodsDao goodsDao = new GoodsDao();
			OrdersDao ordersDao = new OrdersDao();
			rowPerPage = ordersDao.customerGoodsListLastPage(conn);
			
			System.out.print(rowPerPage +"<-ordersService의 rowPerPage");
			
			
			if(rowPerPage ==0 ) {
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
		
		return rowPerPage;
	}
	
	
	// 고객의 셀프주문확인
	public List<Map<String, Object>> getOrdersListByCustomer (String customerId, final int rowPerPage, int currentPage) {
		
		List<Map<String, Object>> m = new ArrayList<>();;
		
		Connection conn = null;
		int beginRow = 0;	
		beginRow = (currentPage -1 ) * rowPerPage;
		
		try {
			conn = new DBUtil().getConnection();
			OrdersDao ordersDao = new OrdersDao();
			
			m = ordersDao.selectOrdersListByCustomer(conn, customerId, rowPerPage, beginRow);
			System.out.println(m +"<-OrdersService의 m");
			
			if(m ==null ) {
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
		return m;
		
	}
	
	
}
