package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.spi.DirStateFactory.Result;

import vo.Review;

public class ReviewDao {

	// 리뷰 보여주기 - 코멘트형태
	
	public Review selectReview (Connection conn, int goodsNo ) throws Exception {
		
		
		/*
		 SELECT 
		 goods_no, customer_id, review_content, update_date,
		 create_date
		 FROM review
		 WHERE goods_no=?
		 
		*/
		
		String sql = "		SELECT "
				+ "		 goods_no, customer_id, review_content, update_date, create_date"
				+ "		 FROM review"
				+ "		 WHERE goods_no=?";
		
		Review review = new Review();		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, goodsNo);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				review.getGoodsNo();
				review.getCustomerId();
				review.getReviewContent();
				
				// 디버깅
				System.out.println(review);
			}
			
			
		} finally {
			if(rs!=null) {rs.close();}
			if(stmt!=null) {stmt.close();}
		}
		
		
		return review;
	}
	
	
	// 리뷰삽입 - 코멘트형태
	public int insertReview (Connection conn, Review paramreview) throws Exception {
		
		/*
		INERT INRO review
		(goods_no, customer_id, review_content, update_date, create_date) 
		VALUES (?, ?, ?, NOW(), NOW()
		
		*/
		String sql = "		INSERT INTO review"
				+ "		(goods_no, customer_id, review_pass, review_content, update_date, create_date)"
				+ "		VALUES (?, ?, PASSWORD(?), ?, NOW(), NOW())";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Review review = new Review();
		int result = 0;
		
		try {
			stmt= conn.prepareStatement(sql);
			stmt.setInt(1, paramreview.getGoodsNo());
			stmt.setString(2, paramreview.getCustomerId());
			stmt.setString(3, paramreview.getReviewPass());
			stmt.setString(4, paramreview.getReviewContent());
			result=stmt.executeUpdate();
			
		
			System.out.println(result+"<-resultresultresult");
		} finally {
			if(rs!=null) {rs.close();}
			if(stmt!=null) {stmt.close();}
		}
		
		
		
		return result;
	}
	
	
}
