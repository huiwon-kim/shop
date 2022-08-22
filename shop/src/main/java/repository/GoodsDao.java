package repository;

import vo.Goods;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class GoodsDao {
	
	
	
		// 고객 상품 상세보기
	   public Map<String, Object> selectcustomerGoodsOne (Connection conn, int goodsNo) throws Exception {
		   
		   /*
		    테이블 3개 조인해서 일단 Map의 객체에 넣자
		    SELECT g.*, o.*, gi.filename
		    FROM goods g INNER JOIN orders o
		    ON g.goods_no = o.goods_no
		    INNER JOIN goods_img gi
		    ON g.goods_no = gi.goods_no
		    WHERE o.order_no=?
		     
		    */
		   
		   
		   String sql="		    SELECT g.*, o.*, gi.filename\r\n"
		   		+ "		    FROM goods g INNER JOIN orders o\r\n"
		   		+ "		    ON g.goods_no = o.goods_no\r\n"
		   		+ "		    INNER JOIN goods_img gi\r\n"
		   		+ "		    ON g.goods_no = gi.goods_no\r\n"
		   		+ "		    WHERE g.goods_no=?";
		   
		   
		   PreparedStatement stmt = null;
		   ResultSet rs = null;
		   Map<String, Object> map = null;
		   
		   
		  
		   try {
			   // Map의 객체 map 생성
			   map = new HashMap<String, Object>();
			   stmt = conn.prepareStatement(sql);
			   stmt.setInt(1, goodsNo);
			   rs = stmt.executeQuery();
			   
			  
			   while(rs.next()) {
				    // 선택이 된다면 map에 집어넣기
				   map.put("goodsNo", rs.getInt("g.goods_no"));
				   map.put("goodsName", rs.getString("g.goods_name")); 
				   map.put("goodsPrice", rs.getInt("g.goods_price")); 
				   map.put("goodsSoldout", rs.getInt("g.sold_out")); 
				   map.put("updateDate", rs.getInt("g.update_date")); 
				   map.put("createDate", rs.getInt("g.create_date")); 
				   map.put("orderNo", rs.getInt("o.order_no")); 
				   map.put("customerId", rs.getInt("o.customer_id")); 
				   map.put("orderQuantity", rs.getInt("o.order_quantity")); 
				   map.put("orderPrice", rs.getInt("o.order_price")); 
				   map.put("orderAddr", rs.getInt("o.order_addr")); 
				   map.put("orderCreatedate", rs.getInt("o.create_date")); 
				   map.put("orderUpdatedate", rs.getInt("o.update_date")); 
				   map.put("filename", rs.getInt("gi.filename")); 
				   
				   // 디버깅
				   System.out.println(map+"<-selectcustomerGoodsList의 map");
			   }
			   
		   } finally {
			   // 자원해제
			   if(rs!=null) {rs.close();}
			   if(stmt!=null) {stmt.close();}
		   }
		   
		   return map;
	   }
		
	
	
	   // 고객 상품리스트 페이지에서 사용 >>> 쿠팡같은 상품보기
	   public List<Map<String, Object>> selectcustomerGoodsListByPage(Connection conn, int rowPerPage, int beginRow) throws SQLException {
		   List<Map<String, Object>> list= null;

		   
		   /*
		    고객의 판매량수 많은 것 부터 >> 판매량수니까 원래는 COUNT(*) 인데 
		    손님이 1주문량에 물건 n개일수잇으니까 SUM!
		    		
		    SELECT g.goods_no goodsNo,
		    g.goods_name goodsName,
		    g.goods_price goodsPrice,
		    gi.filename fileName		  
		    FROM
		    goods g LEFT JOIN (SELECT goods_no, SUM(order_quantity) sumNum
							    FROM orders
							    GROUP BY goods_no) t 
							    ON g.goods_no = t.goods_no
							    	INNER JOIN goods_img gi
							    	ON g.goods_no = gi.goods_no
			ORDER BY IFNULL(t.sumNum, 0) DESC
		 
		  
		    한번도 안팔린 상품 나왔으면 족헷으니까>>> 왼쪽 >>> LEFT JOIN
		    그걸 ORDER BY해봤자 0개 / 오른쪽은 존재 안한다면 null로 나온대 >>> 주문량 0..
		    
		    실무에선 테이블 조인이 엄청 많아서 / 그 조인과의 집계결과를 또 조인하기도 하고 UNION도 있고 등등 300~400줄 온대
		    */
		   
		   /*
		   품절이어도 품절로 뜸
		   SELECT
		   g.goods_no goodsNo,
		   g.goods_name goodsName,
		   g.goods_price goodsPrice,
		   g.sold_out soldOut
		   FROM goods g 
		   INNER JOIN goods_img gi ON g.goods_no = gi.goods_no
		   ORDER BY crate_date LIMIT ?,?;		  		   
		   */
		   String sort = null;
		    
		   String sql = "SELECT g.goods_no,\r\n"
		   		+ "g.goods_name,\r\n"
		   		+ "  g.goods_price,\r\n"
		   		+ " gi.filename\r\n"
		   		+ " FROM\r\n"
		   		+ "goods g LEFT JOIN \r\n"
		   		+ "(SELECT goods_no, SUM(order_quantity) sumNum\r\n"
		   		+ "FROM orders\r\n"
		   		+ "GROUP BY goods_no) t \r\n"
		   		+ "ON g.goods_no = t.goods_no\r\n"
		   		+ "INNER JOIN goods_img gi\r\n"
		   		+ " 	ON g.goods_no = gi.goods_no\r\n"
		   		//+ "ORDER BY "+ sort + "LIMIT ?, ?";
		   		+ "ORDER BY g.goods_no DESC LIMIT ?, ?";
		   
		   // 조건부 정렬을 위해 정렬의 기준이 되는 부분을 sort로 변경
		  
		   
		   PreparedStatement stmt = null;
		   ResultSet rs =null;		   
		   
		   System.out.println(beginRow);
		   System.out.println(rowPerPage);
		   try {
			   list = new ArrayList<Map<String,Object>>();
			   stmt = conn.prepareStatement(sql);
			   stmt.setInt(1, beginRow);
			   stmt.setInt(2, rowPerPage);
			   rs = stmt.executeQuery();
		   
		   while(rs.next()) {
			   Map<String, Object> map = new HashMap<String, Object>();
			   
			  map.put("goodsNo", rs.getInt("g.goods_no"));
			  map.put("goodsName", rs.getString("g.goods_name"));
			  map.put("goodsPrice", rs.getString("g.goods_price"));
			  map.put("filename", rs.getString("gi.filename"));
			  
			  list.add(map);
			  System.out.println(list +"<-list에map이 추가된");
		   		}
		   } finally {
			   if(rs != null) {	rs.close();	}
			   if(stmt != null) { stmt.close(); }
		   }
		   return list;
	   }
	
   
	   
	   // 상품수정
	   
	   public int updateGoods(Connection conn, Goods goods) throws SQLException {
			  int row=0; 
		   
		   /*
		    UPDATE goods SET
		     goods_name goodsName = ?,
		     goods_price goodsPrice = ?,
		     update_date updateDate = NOW(),
		     sold_out soldOut = ?
		     WHERE goodsNo =?
		    */
		   
			/*
			 * String sql = "		    UPDATE goods SET\r\n" +
			 * "		     goods_name goodsName = ?,\r\n" +
			 * "		     goods_price goodsPrice = ?,\r\n" +
			 * "		     update_date updateDate = NOW(),\r\n" +
			 * "		     sold_out soldOut = ?\r\n" + "		     WHERE goodsNo =?";
			 */
		   
		   String sql = "		    UPDATE goods SET "
			   		+ "		     goods_name = ?, "
			   		+ "		     goods_price = ?, "
			   		+ "		     update_date = NOW(), "
			   		+ "		     sold_out = ? "
			   		+ "		     WHERE goods_no =?";
		   
		   PreparedStatement stmt = null;
			 ResultSet rs = null;
		   
			  try {
				  stmt = conn.prepareStatement(sql);
				  stmt.setString(1, goods.getGoodsName());
				  stmt.setInt(2, goods.getGoodsPrice());
				  stmt.setString(3, goods.getSoldOut());
				  stmt.setInt(4, goods.getGoodsNo());
				 
				  row = stmt.executeUpdate(); // 원래는 insert 성공시 row의 수가 리턴 >>> 우리는 옵션붙일거야(Statement.RETURN_GENERATED_KEYS)라는)
				   // stmt.getGeneratedKeys(); // 이러면 stmt가 두번 실행되는거 방금 자동으로 만들어진 key값을 SELECT하여 imgkey값으로 인서트래
				   
				 
			  } finally { 
				   if(rs != null) { rs.close();  }
				   if(stmt != null) { stmt.close(); }
			 
		  		}
			  	//System.out.println(keyId+"<-GoodsDao의 keyId");
			  	return row; // 굿즈넘버로 리턴
	   }
	   
	   
	
		// 반환값 ; key값  >>> 동시입력이란게 너무 어렵고 복잡함 >>> 그 api쓸거래
	   public int insertGoods(Connection conn, Goods goods) throws SQLException {
		  int keyId=0;
		  int row=0; 
		   /*
		    INSERT INTO goods
		    (goods_no, 
		    goods_name, 
		    goods_price, 
		    update_date, 
		    create_date, 
		    sold_out)
		    VALUES (?, ?, ?, ?, NOW(),?)
		   */
		  
		  
		  System.out.println(keyId+"<-26행 GoodsDao의 keyId");
		  PreparedStatement stmt = null;
		  ResultSet rs = null;
		  //											┌ 1을 리턴함 
		  stmt = conn.prepareStatement(" INSERT INTO goods   (goods_name , goods_price , update_date ,create_date )  VALUES ( ?, ? , NOW(), NOW() );" , Statement.RETURN_GENERATED_KEYS);   
		   // 1) insert		┌rs.get(1)로 호출가능
		   // 2) select last_ai_key from... // 마지막으로 만들어진 친구의 라스트키값
		   
		  try {
			  stmt.setString(1, goods.getGoodsName());
			  stmt.setInt(2, goods.getGoodsPrice());
			 
			  row = stmt.executeUpdate(); // 원래는 insert 성공시 row의 수가 리턴 >>> 우리는 옵션붙일거야(Statement.RETURN_GENERATED_KEYS)라는)
			   // stmt.getGeneratedKeys(); // 이러면 stmt가 두번 실행되는거 방금 자동으로 만들어진 key값을 SELECT하여 imgkey값으로 인서트래
			   
			  if(row != 0 ) {
			   rs = stmt.getGeneratedKeys(); // 
			   System.out.println(rs+"<-GoodsDao의 rs");
			   
				   if(rs.next()) {
					   keyId =  rs.getInt(1); // INSERT goods_no값이 들어가겠징 
					 
			   		}		   
		  		}
		  } finally { 
			   if(rs != null) { rs.close();  }
			   if(stmt != null) { stmt.close(); }
		 
	  		}
		  	System.out.println(keyId+"<-GoodsDao의 keyId");
		  	return keyId; // 굿즈넘버로 리턴
	   }
	
	
	// 조인을 한다면 어디서 봐? 부모(메인이 되는, 왼쪽에 오는) 쪽에
	//	
	//																	┌굿즈 넘버 받아야하니깡
	   public Map<String, Object> selectGoodsAndImgOne(Connection conn, int goodsNo) throws SQLException {
		// 자바에는 익명객체가 없지만 가장 비슷한 타이빙 이 map(엄밀히 말하자면 해시맵) 타입이다
		// 키.값 / 키.값 / 키.값 형태
//		
		/*
//		SELECT g.*, gi.*
//		FROM goods g inner JOIN goods_img gi
//		ON g.goods_no = gi.goods_no
//		WHERE g.goods_no = 1 		
//		
//		쿼리에서 WHERE 조건이 없다면 반환타입 List<Map<String, Object>> list		
//		
//		조인의 결과물(VIEW), GROUP BY ...
//		기본테이블과 동일한 VO(domain type)외에 우리는 VO 안 만들고 Map 쓸거래 
//		dao도 원래는 비지니스서비스코드라고 쓰는데 우리는 그냥 dao쓸거랴
//		
//		*/
//		
		String sql ="SELECT g.goods_no,"
				+ "g.goods_name,"
				+ "g.goods_price,"
				+ "g.update_date,"
				+ "g.create_date,"
				+ "g.sold_out, "
				+ "gi.goods_no,"
				+ "gi.filename, "
				+ "gi.origin_filename,"
				+ "gi.content_type,"
				+ "gi.create_date "
				+ "FROM goods g INNER JOIN goods_img gi "
				+ "ON g.goods_no = gi.goods_no "
				+ "WHERE g.goods_no = ? ";  
		   
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Map<String, Object> map = new HashMap<String, Object>();
		
		
	
			stmt = conn.prepareStatement(sql);			
			stmt.setInt(1, goodsNo);
			rs = stmt.executeQuery();	
			
			while(rs.next()) {
				map.put("goodsNo", rs.getInt("g.goods_no")); // 얘는 겟셋이 안되서 내가 직접 쳐주어야
				map.put("goodsName", rs.getString("g.goods_name"));
				map.put("goodsPrice", rs.getInt("g.goods_price"));
				map.put("updateDate", rs.getString("g.update_date"));
				map.put("createDate", rs.getString("g.create_date"));
				map.put("soldOut", rs.getString("g.sold_out"));
				map.put("filename", rs.getString("gi.filename"));
				map.put("originFilename", rs.getString("gi.origin_filename"));
				map.put("contentType", rs.getString("gi.content_type"));
				map.put("createDate", rs.getString("gi.create_date"));
				System.out.print(map+"<-map1");
			}
			System.out.print(map+"<-map2");
		return map;
		
	}
	
	
	
	public int lastPage(Connection conn) throws SQLException {
		
		String sql= "SELECT COUNT(*) FROM goods"; // count(*)함수 >> 고를때마다 +1(약간 java에서 > sum++ 하는 느낌으로)
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int totalRow = 0; // 전체페이지
		
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			
			if(rs.next()) { // 셀 수 있다면
				totalRow = rs.getInt("COUNT(*)");
			}
			
			// 디버깅
			System.out.print(totalRow +"dao");
		}
		
		finally {
			if(rs!=null) {rs.close();}
			if(stmt!=null) {stmt.close();}
		}		
		return totalRow;
	}
	
	
	
	// 사실은 인터페이스로 만들어야한다는데????
	// fm대로하면 어제간한 vo들은 인터페이스가 있어야 한대
	// 계층간의 연결은 인터페이스로 연결해야한대 클래스가 아니라
	// 메서드와 클래스의연결이 필요할 때 인터페이스를 쓰는게 디커플링(느슨하게)할 수 있는 방법인가봐
	
											//				┌ 한번 입력하면 바뀌어선 안되는! 원래는final이 붙어야 하는데 실무에서 안붙인대
	public List<Goods> selectGoodsListByPage(Connection conn, final int rowPerPage , int beginRow) throws Exception{
	      
		   
	      String sql = "SELECT goods_no,goods_name,goods_price,update_date , create_date ,sold_out FROM goods ORDER BY goods_no DESC LIMIT ?, ?";
	      List<Goods> list = new ArrayList<Goods>(); // 주의! List<> *** = new ArrayList<>();
	            
	      PreparedStatement stmt = null;
	      Goods goods =new Goods();
	      ResultSet rs =null;
	      
	      try {
	         stmt = conn.prepareStatement(sql);
	         stmt.setInt(1, beginRow);
	         stmt.setInt(2, rowPerPage);
	         rs = stmt.executeQuery();
	         
	         
	         
	         while(rs.next()) {
	            goods = new Goods();   
	            goods.setGoodsNo(rs.getInt("goods_no"));
	            goods.setGoodsName(rs.getString("goods_name"));
	            goods.setGoodsPrice(rs.getInt("goods_price"));
	            goods.setUpdateDate(rs.getString("update_date"));
	            goods.setCreateDate(rs.getString("create_date"));
	            goods.setSoldOut(rs.getString("sold_out"));
	            
	            list.add(goods); // list로 리턴하니까 리스트에 값을 넣어주자
	         }
	           
	         //디버깅
	         System.out.println(goods.getGoodsName());	      
	      
	      }finally {
	         if(rs!=null) {rs.close();}
	         if(stmt!=null) {stmt.close();}
	      
	      }
	      
	      
	      return list;
	      
	      
	   }
}
