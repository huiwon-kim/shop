package service;

import java.sql.Connection;
import java.sql.SQLException;

import repository.CustomerCartDao;
import vo.Cart;

public class CustomerCartService {

		// 카트에 담긴 고객상품 수정하기
	
	
	   // 고객 상품 카트에 담기
	   public int addcustomerCart (Cart paramcart) {
		   int result = 0;
		   Connection conn = null;
		   
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