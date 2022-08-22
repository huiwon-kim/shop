package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vo.Cart;

public class CustomerCartDao {
	
		// 카트담긴 상품을 주문하기 >> 이때api사용
	
	
		// 카트에 담긴 상품 수정하기(수량?)
	
		
	
	
		// 카트에 담긴 상품 보여주기 >>> api써서 customer addr, detail addr 추가
		public List<Map<String, Object>> selectcustomerCart(Connection conn, String customerId) throws Exception {
			
			/*
			이미지 보여줘야 하니까 테이블 3개 조인?
			SELECT c.*, g.*, gi.filename
			FROM cart c INNER JOIN goods g
			ON c.goods_no = g.goods_no
			INNER JOIN goods_img gi
			ON g.goods_no = gi.goods_no
			WHERE c.customer_id=?;
			
		*/
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			String sql="		SELECT c.*, g.*, gi.filename\r\n"
					+ "			FROM cart c INNER JOIN goods g\r\n"
					+ "			ON c.goods_no = g.goods_no\r\n"
					+ "			INNER JOIN goods_img gi\r\n"
					+ "			ON g.goods_no = gi.goods_no\r\n"
					+ "			WHERE c.customer_id=?";
			 
			 
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			// 다양한거 순서없이 넣으니까 Map 이용
			Map<String, Object> map = null;
			
			try {  
				// Map의 객체 map 생성
				map = new HashMap<String, Object>();
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, customerId);
				rs = stmt.executeQuery();
				
				// 찾아올 수 있다면
				while(rs.next()) {
					// map 에 넣어주기
					map.put("goodsNo", rs.getInt("c.goods_no"));
					map.put("customerId", rs.getString("c.customer_id"));
					map.put("orderQuantity", rs.getInt("c.order_quantity"));
					map.put("goodsName", rs.getString("c.goods_name"));
					map.put("goodsPrice", rs.getString("c.goods_price"));
					map.put("createDate", rs.getString("c.create_date"));
					map.put("soldOut", rs.getString("g.sold_out"));
					map.put("filename", rs.getString("gi.filename"));
					list.add(map);
					// 디버깅
					System.out.println(map+"<-selectcustomerCart의 map");
				}
				
				
			} finally {
				// 자원해제
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
			}
			
		
			return list;			
			
			
		}	
	
		
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
	
		 // System.out.println(paramcart +"<-paramcart");
		 
		 try {
			 stmt =conn.prepareStatement(sql);
			 System.out.println(stmt); // 여ㅛ기까지 충력
			 
			 stmt.setInt(1, paramcart.getGoodsNo());
			 
			 System.out.println(paramcart.getGoodsNo()+"<-나옴? ㅇㅇ나옴");
			 
			 stmt.setString(2, paramcart.getCustomerId());
			 stmt.setInt(3, paramcart.getOrderQuantity());
			 stmt.setString(4, paramcart.getGoodsName());
			 stmt.setInt(5, paramcart.getGoodsPrice());

			 System.out.println(stmt);
			
			 
			 row = stmt.executeUpdate();
			 System.out.println(row +"<-row");
		 
		 } finally {
			 if(stmt!=null) {stmt.close();}
			 
		 }
		   
		 return row;
	}
}	
