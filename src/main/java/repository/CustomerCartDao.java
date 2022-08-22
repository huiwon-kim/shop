package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import vo.Cart;

public class CustomerCartDao {
	
		// 카트담긴 상품을 주문하기 >> 이때api사용
	
	
		// 카트에 담긴 상품 수정하기(수량?)
	
		
	
	/*
		// 카트에 담긴 상품 보여주기 >>> api써서 customer addr, detail addr 추가
		public Cart selectcustomerCart(Connection conn, String customerId,int goodsNo) {
			/*
			SELECT goods_no,
			customer_id,
			order_quantity,
			order_price,
			update_date,
			create_date
			FROM cart 
			WHERE customer_id=? AND goods_no=?
		
			 
		
			Cart cart = new Cart();
			customerId =request.getParameter("custoemrId");
			
			
			
		}	*/
	
	   // 고객 상품 카트에 담기
	   public  int insertcustomerCart (Connection conn, Cart paramcart) throws SQLException {
		/*
		INSERT INTO cart
		(
		goods_no, customer_id, order_quantity, order_price, update_date, create_date
		)
		VALUES
		(?, ?, ?, ?, NOW(), NOW())  
		   
		   
		*/
		   
		String sql ="INSERT INTO cart (goods_no, customer_id, order_quantity, goods_name, goods_price, update_date, create_date) VALUES (?, ?, ?, ?, ?, NOW(), NOW() )";
		  
		  int row = 0;
		 
		 PreparedStatement stmt = null;
	
		 System.out.println(paramcart +"<-paramcart");
		 
		 try {
			 stmt =conn.prepareStatement(sql);
			 stmt.setInt(1, paramcart.getGoodsNo());
			 stmt.setString(2, paramcart.getCustomerId());
			 stmt.setInt(3, paramcart.getOrderQuantity());
			 stmt.setString(4, paramcart.getGoodsName());
			 stmt.setInt(5, paramcart.getGoodsPrice());
			 stmt.setString(6, paramcart.getUpdateDate());
			 stmt.setString(7, paramcart.getCreateDate());
			 
			 row = stmt.executeUpdate();
			 System.out.println(row +"<-row");
		 
		 } finally {
			 if(stmt!=null) {stmt.close();}
			 
		 }
		   
		 return row;
	}
}	
