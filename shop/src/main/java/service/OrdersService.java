package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import repository.OrdersDao;

public class OrdersService {

	
	//관리자의 고객주문확인의 페이징용
	
	// 관리자의 고객주문확인(리스트뽑기용)
	
	
	
	// 고객의 셀프주문확인의 페이징용
	
	
	// 고객의 셀프주문확인 (리스트 뽑기용)
	public List<Map<String, Object>> getOrdersListByCustomer (String customerId, final int rowPerPage, int currentPage) {
		List<Map<String, Object>> m = null;
		
		Connection conn = null;
		int beginRow = 0;	
		beginRow = (currentPage -1 ) * rowPerPage;
		
		try {
			conn = new DBUtil().getConnection();
			OrdersDao ordersDao = new OrdersDao();
			
			m = ordersDao.selectOrdersListByCustomer(conn, customerId, rowPerPage, beginRow);
			
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
