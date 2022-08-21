package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.Notice;

public class NoticeDao {
	
	// 공지사항 글입력용
	public int insertNotice(Connection conn, Notice notice) throws SQLException {
		/*
		INSERT INTO notice
		(employee_id, notice_pass, notice_title, notice_content,
		update_date, create_date)
		VALUES
		(?, PASSWORD(?), ?, ?, NOW(), NOW()) 
		  
		 */
		int row = 0;
		
		String sql ="		INSERT INTO notice\r\n"
				+ "		(employee_id, notice_pass, notice_title, notice_content,\r\n"
				+ "		update_date, create_date)\r\n"
				+ "		VALUES\r\n"
				+ "		(?, PASSWORD(?), ?, ?, NOW(), NOW()) ";
	
		PreparedStatement stmt = null;
		ResultSet rs = null;
	
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, notice.getEmployeeId());
			stmt.setString(2, notice.getNoticePass());
			stmt.setString(3, notice.getNoticeTitle());
			stmt.setString(4, notice.getNoticeContent());
			
			row = stmt.executeUpdate();
			System.out.println(row + "<-insertNotice의 row");
		} finally {
			if(rs!=null) {rs.close();}
			if(stmt!=null) {stmt.close();}
		}
	
		return row;
	}
	
	
	
	
	
	
	// 공지사항 목록보기(라스트페이지)용
	public int lastPage(Connection conn) throws Exception {
		
		String sql = "SELECT COUNT(*) FROM notice";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int totalRow = 0;
		
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				totalRow = rs.getInt("COUNT(*)");
			}
			
			System.out.print(totalRow+"<-totalRow");
		} finally {
			if(rs!=null) {rs.close();}
			if(stmt!=null) {stmt.close();}
			
		}
		return totalRow;
		
	}
	
	
	
	// 공지사항 목록보기용
	public List<Notice> selectNoticeList (Connection conn, final int rowPerPage, int beginRow ) throws SQLException {
		int k =0;
		
		/*
		SELECT
		notice_no noticeNo
		notice_title noticeTitle
		notice_content noticeContent
		update_date updateDate
		create_date createDate
		FROM notice
		ORDER BY notice_no DESC LIMIT ?,?
		
		*/
		
		
		String sql ="		SELECT\r\n"
				+ "		notice_no, \r\n"
				+ "		employee_id, \r\n"
				+ "		notice_writer, \r\n"
				+ "		notice_title, \r\n"
				+ "		notice_content, \r\n"
				+ "		update_date, \r\n"
				+ "		create_date\r\n"
				+ "		FROM notice\r\n"
				+ "		ORDER BY notice_no DESC LIMIT ?,?";
		
		List<Notice> list = new ArrayList<Notice>();
		Notice notice = new Notice();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			
			rs = stmt.executeQuery();
			
			
			while(rs.next()) {
				notice = new Notice();
				notice.setNoticeNo(rs.getInt("notice_no"));
				notice.setEmployeeId(rs.getString("employee_id"));
				notice.setWriter(rs.getString("notice_writer"));
				notice.setNoticeTitle(rs.getString("notice_title"));
				notice.setNoticeContent(rs.getString("notice_content"));
				notice.setUpdateDate(rs.getString("update_date"));
				notice.setCreateDate(rs.getString("create_date"));
			
				list.add(notice);
				
			}
			
			System.out.println(list+"<-selectNoticeList 의 list");
			
		} finally {
			if(rs!=null) {rs.close();}
			if(stmt!=null) {stmt.close();}
		} 
		return list;
		
		
}
}
