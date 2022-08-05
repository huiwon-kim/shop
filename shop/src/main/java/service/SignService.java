package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import repository.SignDao;

public class SignService {
	private SignDao signDao; // 캡슐화
		
	// return
	// true : 사용 가능한 아이디
	// false : 사용 불가능한 아이디
	// SignDao 는 트루펄스로 가공하는거 좋지 않대ㅑ
	// 가공은 Dao에서 값을 넘겨받는 서비스만!
	
	public boolean idCheck(String id) {
		// SignDao signDao = new SignDao(); 1)
		// 사인서비스 안에서 사용하는 모든 SignDao는 동일한 SignDao
		
		
		boolean result = false; 
		this.signDao = new SignDao(); // 2), 1)로 쓰면 NULLPointException뜬대
		
		// 어떤 경우 의존객체가 메서드안에서 지역변수로 만들여지는게 좋고 ★
		// 다른 경우에는 메서드와 동일한 위치로 있는게 좋대
		// ∴ SignDao는 동등한 위치의 지역변수로 갖는게 좋대
		
		// Ex; 손으로 밥먹엉 >>> 손도 밥먹는것과 동일한 위치의 객체래, 원래있던거 쓰는거니까
		
		// 약간 없던거 만들어내면 낮은 느낌? >>> SignDao도 없던거 만든거라 지역변수인거래 ★
		
		Connection conn = null;
	
		try {
			conn = new DBUtil().getConnection();
			if(signDao.idCheck(conn, id)== null) {
				result = true;
			} //signDao호출 및 메서드이용, null이면 사용이 가능하ㄴ단 소리니까
			
			conn.commit(); // 트라이절 마지막에 커밋 기계적으로 달래
		
		} catch(Exception e) {
			e.printStackTrace(); // 콘솔에 문제있슴다 하고 출력
			try {
				conn.rollback(); // 만약 예외있음 커밋대신 롤백시키구
			} catch (SQLException e1) {				
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
			return result;
		}
		
		// conn... 클로즈
		
	}

