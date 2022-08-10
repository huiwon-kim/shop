package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import vo.GoodsImg;


public class GoodsImgDao {
	public int insertGoodsImg(Connection conn, GoodsImg goodsImg) throws SQLException {
		int row=0;
		
		
		/*
		 INSERT INTO goods_img
		 (goods_no, filename, origin_filename, content_type, create_date)
		  VALUES (?, ?, ?, ?, NOW())
		  
		 */
		
		System.out.println(row+"<-22행 GoodsImgDao의 row");
		
		String sql ="	 INSERT INTO goods_img\r\n"
				+ "		 (goods_no, "
				+ "		  filename, "
				+ "		  origin_filename, "
				+ "		  content_type, "
				+ "		  create_date)\r\n"
				+ "		  VALUES (?, ?, ?, ?, NOW())";
		
		PreparedStatement imgStat = null;
			
				
		try {			
			imgStat = conn.prepareStatement(sql);		
			imgStat.setInt(1, goodsImg.getGoodsNo());
			imgStat.setString(2, goodsImg.getFileName());
			imgStat.setString(3, goodsImg.getOriginFileName());
			imgStat.setString(4, goodsImg.getContentType());
		
		row = imgStat.executeUpdate();		
		System.out.println(row+"<-GoodsImgDao의 row"); // 디버깅
		
		} finally {
			if(imgStat!=null) {imgStat.close();}
			
		}		
		
		return row;
	}
	
}
