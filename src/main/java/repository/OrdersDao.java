package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vo.Orders;

public class OrdersDao {
	
	
	// 관리자의 고객주문확인의 상세보기 수정용 (배송상태)
	public int updateOrdersOne(Connection conn, Orders orders) throws SQLException {
		
		/*
		UPDATE orders SET order_state=? WHERE order_no=?
		*/
		String sql = "UPDATE orders SET order_state=? WHERE order_no=?";
		int row = 0;
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, orders.getOrderState());
			stmt.setInt(2, orders.getOrderNo());
			
			row = stmt.executeUpdate();
			System.out.print(row +"<-row");
					
		} finally {
			if(stmt!=null) { stmt.close();}
		}
		
		return row;
				
	}
	
	
	
	
	// 5-2) 주문 상세 보기 (관리자의 고객주문확인의 상세보기용)
	public Map<String, Object> selectOrdersOne(Connection conn, int ordersNo) throws SQLException {
		Map<String, Object> m = null; // >>이렇게 만드는게 낫대
		
		
		// 커스터머가 없는 쪽을 아웃터조인하라고?
		/*
		SELECT
		o.order_no,
		o.goods_no,
		o.customer_id,
		o.order_quantity,
		o.order_price,
		o.order_addr,
		o.order_state,
		g.goods_no,
		g.goods_name,
		g.goods_price,
		g.update_date,
		g.create_date,
		g.sold_out,
		c.customer_id,
		c.customer_name,
		c.customer_address,
		c.customer_telephone
		FROM orders o INNER JOIN goods g
		ON o.goods_no = g.goods_no 
									INNER JOIN customer c
									ON o.customer_id = c.customer_id 
		WHERE o.order_no =1 ?				
		
		ON o.goods_no = g.goods_no // 외래키
		ON o.customer_id = c.customer_id // 외래키
		*/
		
		String sql ="		SELECT\r\n"
				+ "		o.order_no,\r\n"
				+ "		o.goods_no,\r\n"
				+ "		o.customer_id,\r\n"
				+ "		o.order_quantity,\r\n"
				+ "		o.order_price,\r\n"
				+ "		o.order_addr,\r\n"
				+ "		o.order_state,\r\n"
				+ "		g.goods_no,\r\n"
				+ "		g.goods_name,\r\n"
				+ "		g.goods_price,\r\n"
				+ "		g.update_date,\r\n"
				+ "		g.create_date,\r\n"
				+ "		g.sold_out,\r\n"
				+ "		c.customer_id,\r\n"
				+ "		c.customer_name,\r\n"
				+ "		c.customer_address,\r\n"
				+ "		c.customer_telephone\r\n"
				+ "		FROM orders o INNER JOIN goods g\r\n"
				+ "		ON o.goods_no = g.goods_no \r\n"
				+ "									INNER JOIN customer c\r\n"
				+ "									ON o.customer_id = c.customer_id \r\n"
				+ "		WHERE o.order_no = ?";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, ordersNo);
			
			rs =stmt.executeQuery();
			
			System.out.println(rs+"<-rs");
			
				while(rs.next()) {
					m = new HashMap<String, Object>();
					
					m.put("orderNo", rs.getInt("o.order_no"));
					m.put("goodsNo", rs.getInt("o.goods_no"));
					m.put("customerId", rs.getString("o.customer_id"));
					m.put("orderQuantity", rs.getString("o.order_quantity"));
					m.put("orderPrice", rs.getInt("o.order_price"));
					m.put("orderAddr", rs.getString("o.order_addr"));
					m.put("orderstate", rs.getString("o.order_state"));
					m.put("goodsNo", rs.getInt("g.goods_no"));
					m.put("goodsName", rs.getString("g.goods_name"));
					m.put("goodsPrice", rs.getInt("g.goods_price"));
					m.put("updateDateGd", rs.getString("g.update_date"));
					m.put("createDateGd", rs.getString("g.create_date"));
					m.put("soldOut", rs.getString("g.sold_out"));
					m.put("customerIdc", rs.getString("c.customer_id"));
					m.put("customerName", rs.getString("c.customer_name"));
					m.put("customerAddress", rs.getString("c.customer_address"));
					m.put("customerTelephone", rs.getString("c.customer_telephone"));
	
					System.out.print(m+"<-selectOrdersOne의 m");					
				}
				
		} finally {
			if(rs!=null) {rs.close();}
			if(stmt!=null) {stmt.close();}
		}
		
		return  m;
	}
	
	
	// 5-1) 전체 주문 목록(관리자페이지)
	public List<Map<String, Object>> selectOrdersList(Connection conn, int rowPerPage, int beginRow ) throws SQLException {
		 List<Map<String, Object>> list = new ArrayList<>(); // 하나도 데이터가 없을시 null이 아니라 빈 ArrayList를 리턴할거야 >>> 다형성
		
		/*
		SELECT 
		 o. ,
		 g. ,
		FROM orders o INNER JOIN goods g
		ON o.goods_no = g.goods_no
		ORDER BY create_date DESC
		LIMIT ?,?
		
		무슨 상품을 삿는지 알 수가 없어서 결국은 조인을 해야한대 >>> List<Orders>를 사용 불가
		>>> Map을 넣어 >>> List<Map<String, Object>> 이 형태로 받아야한다
		(0808일에 했던 그 Map썼던거-상품상세보기 참고)
		오늘은 도메인리스트를 대신하기 위해 List<Map<String, Object>> 사용
		*/
		
		String sql ="	SELECT \r\n"
				+ "		 o.order_no,\r\n"
				+ "		 o.goods_no,\r\n"
				+ "		 o.customer_id,\r\n"
				+ "		 o.order_quantity,\r\n"
				+ "		 o.order_price,\r\n"
				+ "		 o.order_addr,\r\n"
				+ "		 o.order_state,\r\n"
				+ "		 o.update_date,\r\n"
				+ "		 o.create_date,\r\n"
				+ "		 g.goods_no,\r\n"
				+ "		 g.goods_name,\r\n"
				+ "		 g.goods_price,\r\n"
				+ "		 g.update_date,\r\n"
				+ "		 g.create_date,\r\n"
				+ "		 g.sold_out \r\n"
				+ "		FROM orders o INNER JOIN goods g\r\n"
				+ "		ON o.goods_no = g.goods_no\r\n"
				+ "		ORDER BY o.create_date DESC\r\n"
				+ "		LIMIT ?,?";
		 
		 
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Map<String, Object> m = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			
			rs = stmt.executeQuery();
			
			System.out.println(beginRow +"<-beginRow");
			System.out.println(rowPerPage +"<-rowPerPage");
			
			while(rs.next()) {
				m = new HashMap<String, Object>();
				
				m.put("orderNo", rs.getInt("o.order_no"));
				m.put("goodsNo", rs.getInt("o.goods_no"));
				m.put("customerId", rs.getString("o.customer_id"));
				m.put("orderQuantity", rs.getString("o.order_quantity"));
				m.put("orderPrice", rs.getInt("o.order_price"));
				m.put("orderAddr", rs.getString("o.order_addr"));
				m.put("orderstate", rs.getString("o.order_state"));
				m.put("updateDateOr", rs.getString("o.update_date"));
				m.put("createDateOr", rs.getString("o.create_date"));
				m.put("goodsNo", rs.getInt("g.goods_no"));				
				m.put("goodsName", rs.getString("g.goods_name"));
				m.put("goodsPrice", rs.getInt("g.goods_price"));
				m.put("updateDateGd", rs.getString("g.update_date"));
				m.put("createDateGd", rs.getString("g.create_date"));
				m.put("soldOut", rs.getString("g.sold_out"));
				list.add(m); // hashmap 형태의 변수들을 list에 넣어주기
				
				System.out.println(list +"<-관리자 상품보기list");
			}
			
			
		} finally {
			if(rs!=null) {rs.close();}
			if(stmt!=null) {stmt.close();}
			
		}

		 
		return list;
	}
	
	
	// 2-1-주문 목록(관리자, 고객페이지)의 라스트페이지
	   // lastPage
	   public int customerGoodsListLastPage(Connection conn) throws SQLException {
		   
		   String sql ="SELECT COUNT(*) FROM orders";
		   PreparedStatement stmt = null;
		   ResultSet rs = null;
		   int totalCount = 0;
		   
		   try {
			   stmt = conn.prepareStatement(sql);
			   rs = stmt.executeQuery();
			   
			   if(rs.next()) {
				   totalCount = rs.getInt("COUNT(*)");
			   }
				System.out.print(totalCount +"customerGoodsList의 dao");
			   
			   
		   } finally {
				
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
		   }		   
		   return totalCount;		   
	   }
	   
	
	// ※ DAO나 Service를 꼭 주체에 따라 분리할 필요는 없다
	// 2-1) 고객 한명의 주문 목록(관리자, 고객페이지)
	public List<Map<String, Object>> selectOrdersListByCustomer (Connection conn, String customerId, int rowPerPage, int beginRow) throws SQLException {
		List<Map<String, Object>> list = new ArrayList<>(); // 다형성
		
		
		/*
		SELECT 
		 o.order_no,
		 o.goods_no,
		 o.customer_id,
		 o.order_quantity,
		 o.order_price,
		 o.order_addr,
		 o.order_state,
		 o.update_date,
		 o.create_date,
		 g.goods_no,
		 g.goods_name,
		 g.goods_price,
		 g.update_date,
		 g.create_date,
		 g.sold_out,
		FROM orders o INNER JOIN goods g
		ON o.goods_no = g.goods_no
		WHERE o.customer_id=?
		ORDER BY o.create_date DESC
		LIMIT ?,?
		*/
		
		String sql ="	SELECT \r\n"
				+ "		 o.order_no,\r\n"
				+ "		 o.goods_no,\r\n"
				+ "		 o.customer_id,\r\n"
				+ "		 o.order_quantity,\r\n"
				+ "		 o.order_price,\r\n"
				+ "		 o.order_addr,\r\n"
				+ "		 o.order_state,\r\n"
				+ "		 o.update_date,\r\n"
				+ "		 o.create_date,\r\n"
				+ "		 g.goods_no,\r\n"
				+ "		 g.goods_name,\r\n"
				+ "		 g.goods_price,\r\n"
				+ "		 g.update_date,\r\n"
				+ "		 g.create_date,\r\n"
				+ "		 g.sold_out \r\n"
				+ "		FROM orders o INNER JOIN goods g\r\n"
				+ "		ON o.goods_no = g.goods_no\r\n"
				+ "		WHERE o.customer_id=?\r\n"
				+ "		ORDER BY o.create_date DESC\r\n"
				+ "		LIMIT ?,?";
		// 아우터조인으로 바꿀 것
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Map<String, Object> m = null;
		
		System.out.println(m +"<-154번재줄");
		try {
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, customerId);
			stmt.setInt(2, beginRow);
			stmt.setInt(3, rowPerPage);
			
			rs = stmt.executeQuery();
			
			
			System.out.println(rs +"<-Rs");
			System.out.println(customerId+"<-customerId");
			System.out.println(rowPerPage +"<-rowPerPage");
			
			while(rs.next()) {
				m = new HashMap<String, Object>();
				
				m.put("orderNo", rs.getInt("o.order_no"));
				m.put("goodsNo", rs.getInt("o.goods_no"));
				m.put("customerId", rs.getString("o.customer_id"));
				m.put("orderQuantity", rs.getString("o.order_quantity"));
				m.put("orderPrice", rs.getInt("o.order_price"));
				m.put("orderAddr", rs.getString("o.order_addr"));
				m.put("orderstate", rs.getString("o.order_state"));
				m.put("updateDateOr", rs.getString("o.update_date"));
				m.put("createDateOr", rs.getString("o.create_date"));
				m.put("goodsNo", rs.getInt("g.goods_no"));				
				m.put("goodsName", rs.getString("g.goods_name"));
				m.put("goodsPrice", rs.getInt("g.goods_price"));
				m.put("updateDateGd", rs.getString("g.update_date"));
				m.put("createDateGd", rs.getString("g.create_date"));
				m.put("soldOut", rs.getString("g.sold_out"));
				list.add(m);
				
				System.out.println(list +"<-list");
			}
			
		} finally {
			if(rs!=null) {rs.close();}
			if(stmt!=null) {stmt.close();}
			
		}
				
		return list;
	}
}
