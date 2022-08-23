package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import repository.NoticeDao;
import vo.Notice;

public class NoticeService {
	private NoticeDao noticeDao;

	
	
	// 공지사항 수정
	public int motifyNotice(Notice parmanotice) {
		Connection conn = null;
		int row = 0;
		
		try {
			conn = new DBUtil().getConnection(); // DB연동
			conn.setAutoCommit(false); // 자동커밋방지
			
			
			// NoticeDao 객체생성
			NoticeDao noticeDao = new NoticeDao();
			
			row = noticeDao.updateNotice(conn, parmanotice);
			
			System.out.println(row +"<-SERVICE motifyNotice의 ROW");
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
		
		return row;
	}
	
	
	// 공지사항 상세보기
	public Notice getnoticeOne(int noticeNo) {
		Connection conn = null;
		int row = 0;
		Notice paramnotice = new Notice();
		
		try {
			conn = new DBUtil().getConnection(); // DB연동
			conn.setAutoCommit(false); // 자동커밋방지
			
			
			// NoticeDao 객체생성
			NoticeDao noticeDao = new NoticeDao();
			// 리턴할 객체에 DAO안 메서드 실행시켜 담기
			paramnotice=noticeDao.noticeOne(conn, noticeNo);
			
			System.out.println(paramnotice);
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
			
		return paramnotice;
	}
	
	
	// 공지 입력하기
	public int getNotice (Notice notice) {
		Connection conn = null;
		int row = 0;
		
		
		try {
			conn = new DBUtil().getConnection(); // DB연동
			conn.setAutoCommit(false); // 자동커밋방지
			
			
			// NoticeDao 객체생성
			NoticeDao noticeDao = new NoticeDao();
			
			
			// 리턴값에 값 담기위한 메서드 호출 및 담기
			row = noticeDao.insertNotice(conn, notice);
			
			// 디버깅
			System.out.println(row +"<-getNotice의 row");
			
			// 그리구 커밋할거양
			conn.commit();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				// 이상하면 롤백 ㄱ
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
		
		return row;
		
	}
	
	
	// 공지 목록보기 페이징
	public int getNoticeListLastPage(int rowPerPage) {
		
		Connection conn = null;
		
		try {
			conn = new DBUtil().getConnection();
			conn.setAutoCommit(false);
			
			// NoticeDao 객체생성
			NoticeDao noticeDao = new NoticeDao();
			
			// 리턴값에 값 담기위한 메서드 호출
			rowPerPage = noticeDao.lastPage(conn);
			
			System.out.print(rowPerPage +"<-rowPerPage");
			
			if(rowPerPage ==0) {
				System.out.print("rowPerPage");
				throw new Exception();
			}
			
			
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
		
		return rowPerPage;
		
	}
	
	
	
	// 공지 목록보기
	public List<Notice> getNoticeList(final int rowPerPage, int currentPage) {
		List<Notice> list = new ArrayList<Notice>();
		
		this.noticeDao = new NoticeDao();
		Connection conn = null;
		
		int beginRow = 0;
		
		beginRow = (currentPage -1) * rowPerPage;
		
		try {
			conn = new DBUtil().getConnection();
			
			// NoticeDao 객체 생성
			NoticeDao noticeDao = new NoticeDao();
			
			// 리턴값에 값 넣어주기 (메서드 호출을 통한)
			list = noticeDao.selectNoticeList(conn, rowPerPage, beginRow);
			
			// 디버깅
			System.out.println(list +"<-getNoticeList의 list");
			
			if(list == null) {
				System.out.println("32번째 줄 참고");
				throw new Exception();				
			}
	

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return list;
		
	}
}
