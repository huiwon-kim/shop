package service;


import java.sql.Connection;
import java.sql.SQLException;

import repository.CustomerDao;
import repository.OutIdDao;
import vo.Customer;
import vo.OutId;

//여기가 이제 트랜젝션과 conn rs stmt 이런거 처리하는듯?
public class CustomerService { // 서비스는 이름 유연하게 써도 된대
	// loginAction.jsp 호출
		
	// 트랜젝션 처리를 서비스에서 한다 그래서 트라이캐치파이널리 막 씌우면서 복잡한 이유
	// 딜리트는 리턴할게 없어서 불리언 쓴거오
	public boolean removeCustomer (Customer paramCustomer) {
		
		Connection conn = null;
		
		try {
			conn = new DBUtil().getConnection();
			conn.setAutoCommit(false); // executeUpdate()실행시 자동커밋 안되게 잠그기
			//								└ 메모리엔 O 실제 디비에 반영 안되는 상태
			
			CustomerDao customerDao = new CustomerDao(); // 커스토머 디에이오 호출
			if(customerDao.deleteCustomer(conn, paramCustomer) !=1) { 
				throw new Exception();
			}
			OutIdDao OutIdDao = new OutIdDao();
			if(OutIdDao.insertOutId(conn, paramCustomer.getCustomerId()) !=1) {
					// 예외 >   try, catch, finally, throw, throws 중 throw가 예외발생
					throw new Exception();
				}
				// 위에가 삭제가 되야만 아웃IdDao에 데이트 삽입하게
				// 인설트가 예외는 안났는데 아웃이 안되면 또 
				
				
			// 28~35를 이렇게 써도 된대						
//				if(customerDao.deleteCustomer(conn, paramCustomer)==1) { // 삭제성공하면 1
//					OutIdDao OutIdDao = new OutIdDao();
//					if(OutIdDao.insertOutId(conn, paramCustomer.getCustomerId()) !=1) {
//						// 예외 >   try, catch, finally, throw, throws 중 throw가 예외발생
//						throw new Exception();
//					}			
						
			conn.commit(); // 위에 둘다 같은 conn 실행시켜서 둘다 실패 없을시 드디어 커밋 ★★★★★
		} catch (Exception e) {
			e.printStackTrace();// 콘솔창에 예외메시지 출력 / 기입 必
			try {
				conn.rollback(); // CustomerDao OutIdDao 예외나면 롤백
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return false;
		} finally {
				try {
					conn.close(); // 예외가 나든 안나든 클로스는 이루어지고
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return true;
		}
	
	
		// 얘는 세션에 넣어야하니까 리턴값이 customer이여야지
		public Customer loginCustomer (Customer paramCustomer) {
			
			Connection conn = null;
				Customer customer =new Customer();
			
				try {
				conn = new DBUtil().getConnection();
				conn.setAutoCommit(false);
				
				CustomerDao customerDao = new CustomerDao();			
				customer=customerDao.selectCustomer(conn, paramCustomer);
				
				
				conn.commit();
				
				} catch (Exception e) {
					
					e.printStackTrace();
				} finally {
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				}
			return customer;
			
		}
	
	}
// 예외보고 >>> throws
// 알아서 처리 >> try catch
// 예외처리방안 >>> paramCustomer 이거로 오류메시지띄우고 & 롤백할거야
// 모든 트랜젝션 처리는 DAO래
// 커넥션을 만드는 것[서비스]과 거기에 쿼리를 던지는 일(=실행; DAO)은 분리래. DB에 향한다고 같은 일은 아니래
// 서비스는 1개 DAO는 10개일 수도 잇대

// 액션 > 서비스 >DAO > 서비스 > 예외조건(롤백반환)
// 셀렉트는 변환되지 않음, 결과물은 VIEW 일뿐 >>> 이건 트랜젝션 처리 안하고
// 인서릍 업데이트 딜리트는 실제 테이블에 접근함 >>> 얘는 트렌젝션 처리를 하고
// 리턴은값은 보이드나 TRUE FALSE.. 1은 좀 아닌것같다는 ㅅ쌤의 말씀
