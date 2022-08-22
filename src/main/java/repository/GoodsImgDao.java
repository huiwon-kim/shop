package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;

import vo.Goods;
import vo.GoodsImg;



public class GoodsImgDao {
	
	
	public int updateGoodsImg(Connection conn, GoodsImg goodsImg ) throws SQLException {
		int row = 0;
		
		/*
		UPDATE goods_img SET
		filename =?,
		orIFIN_filename orginFilename=?,
		content_type contentType=?
		WHERE goods_no=?		
		*/
		
		PreparedStatement stmt = null;
		
		String sql = "		UPDATE goods_img SET\r\n"
				+ "		filename = ?,\r\n"
				+ "		origin_filename = ?,\r\n"
				+ "		content_type = ?\r\n"
				+ "		WHERE goods_no=?	";
		
		try {
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, goodsImg.getFileName());
			stmt.setString(2, goodsImg.getOriginFileName());
			stmt.setString(3, goodsImg.getContentType());
			stmt.setInt(4, goodsImg.getGoodsNo());
			
			row = stmt.executeUpdate();
			
			System.out.println(row +"<-updateGoodsImg의 row");
		} finally {
			
			if(stmt!=null) {stmt.close();}
		}
		
		return row;
	}
	
	
	public GoodsImg selectGoodsImg(Connection conn, int goodsNo) throws SQLException {
		GoodsImg goodsImg = null;
		String sql ="SELECT\r\n"
				+ "		 goods_no goodsNo,\r\n"
				+ "		 filename fileName,\r\n"
				+ "		 origin_filename originFilename,\r\n"
				+ "		 content_type contentType,\r\n"
				+ "		 create_date createDate";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				goodsImg = new GoodsImg();
				goodsImg.setGoodsNo(rs.getInt("goodsNo"));
				goodsImg.setFileName(rs.getString("fileName"));
				goodsImg.setOriginFileName(rs.getString("originFilename"));
				goodsImg.setCreateDate(rs.getString("createDate"));
			}
			
		} finally {
			if(rs !=null) { rs.close();}
			if(stmt !=null ) {stmt.close();}
		}
		
		return goodsImg;
	}
	
	
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
