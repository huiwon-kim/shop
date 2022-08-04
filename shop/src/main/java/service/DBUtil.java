package service;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {

	public Connection getConnection() throws Exception {
		
		Class.forName("org.mariadb.jdbc.Driver");		
		
		String url = "jdbc:mariadb://localhost:3306/shop";
		String dbuser = "root";
		String dbpw = "1234";
		
		Connection conn= DriverManager.getConnection(url, dbuser, dbpw);
		// 예외처리는 어디서 해야하는지 기준이 없다. 하지만 요즘은 안하는게(계속 마지막까지 던지다 마지막 애가 처리) 트랜드임!
		// 트라이캐치 + 파이널리 >>> 파이널리는 트라이 캐치에 종속이 되어있음 파이널리를 적기 위해 최소한으로 트라이+파이널리 의 형태로 쓴다 (파이널리 쓰는 이유는 자원종료를 위해서라는데)
				
		// 모든 예외(눌 등등)의 부모 >> Exception [다형성]		
		
		
		
		return conn;
		
	}
	
	
}
