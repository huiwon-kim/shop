package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import repository.CustomerCartDao;
import vo.Cart;

public class CustomerCartService {

		// 카트에 담긴 고객상품 보여주기	
		public List<Map<String, Object>> getcustomerCart (String customerId)	{
			
			Map<String, Object> map = null;
			Connection conn = null;
			
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			
			try {
				conn = new DBUtil().getConnection(); // 디비연동
				conn.setAutoCommit(false); // 자동커밋방지
				
				CustomerCartDao customerCartDao = new CustomerCartDao();
				
				list = customerCartDao.selectcustomerCart(conn, customerId);
				
				// 디버깅
				System.out.print(map +"<-getcustomerCart의 map");
				
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
	
	
	   // 고객 상품 카트에 담기
	   public int addcustomerCart (Cart paramcart) {
		   int result = 0;
		   Connection conn = null;
		   System.out.println(paramcart.getCustomerId());
		   
		   try {
				conn = new DBUtil().getConnection(); // 디비연동
				conn.setAutoCommit(false); // 자동커밋방지
			   
				CustomerCartDao customerCartDao = new CustomerCartDao();
				
				result = customerCartDao.insertcustomerCart(conn, paramcart);
				
				System.out.println(result +"<-addcustomerCart의");
				
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
	   
	   return result;
	   
	   
	   }

}