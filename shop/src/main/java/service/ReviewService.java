package service;

import java.sql.Connection;
import java.sql.SQLException;

import repository.NoticeDao;
import repository.ReviewDao;
import vo.Review;

public class ReviewService {
	
	// 리뷰 보기
	public Review getReview (int goodsNo) {
		Connection conn = null;
		Review review = new Review();
		
				
		
		try {
			conn = new DBUtil().getConnection();
			
			ReviewDao reviewDao = new ReviewDao();
			review = reviewDao.selectReview(conn, goodsNo);
			System.out.println(review +"<-getReview의 review");
			
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
				
		return review;
	}
	
	
	
	// 리뷰입력
	public int addReview (Review paramreview) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = new DBUtil().getConnection();
			conn.setAutoCommit(false);
			
			ReviewDao reviewDao = new ReviewDao();			
			result = reviewDao.insertReview(conn, paramreview);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
