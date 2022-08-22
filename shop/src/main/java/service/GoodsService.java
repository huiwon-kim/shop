package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import repository.GoodsDao;
import repository.GoodsImgDao;
import vo.*;


// 서비스가 하는 일: 트랜잭션 처리 ★★★★★★★ + action, Dao가 해서는 안되는 일 처리
public class GoodsService {
	// 굿즈디에이오를 호출 *원래 서비스는 그런거양
	private GoodsImgDao goodsImgDao;
	private GoodsDao goodsDao;	// 특정멤버를 참조할 때도 이렇게 인티페이스를 참조하면 디커플링(Decoupling) >>> 유연해지므로 유지보수 편해
	// 인터페이스는 어디에 쓰이고 왜 쓰이는지가 중요하댕 또한 편한데 만드는게 힘들대	
	// 디자인패턴을(=설계패턴) 따라하는데 이게 어려워가지고 프레임워크가 나타났대. 스프링프레임워크 쓰면 쟤가 자동화로 알아서 만들어주는듯
	// 스프링프레임워크는 MVC프레임워크고 얘들이 필수요소들은 자동으로 만들어쥐만 service는 안만들어준대 이건 ㄹㅇ 인간개발자의 영역. 자동화로 해결 ㄴㄴ래
	
	// 내 손은 내가 갖고있음. 밥먹겠다고 갑자기 세포분열? 이딴거 없ㅇ음 >>> field변수
	// 글쓰겠다고 손에 늘 필기도구 장착하고다님? 그럴리가? 그냥 해당장소가서 필요할때 잡으면 그만 >>> local변수
	
	// DAO안에 5개의 메서드가 있음. 이 5개 메서드들은 다같은 DAO를 가져오겠지 필요할때마다 객체를 마만들겠징

	// 고객상품리스트 페이지상세보기
	public Map<String, Object> getcustomerGoodsOne(int goodsNo) {
		
		Map<String,Object> map = null;
		
		Connection conn = null;
		
		try {
			conn = new DBUtil().getConnection();
			conn.setAutoCommit(false);
			GoodsDao goodsDao = new GoodsDao(); 	
			map = goodsDao.selectcustomerGoodsOne(conn, goodsNo);
			
			System.out.print(map +"<-getcustomerGoodsOne의 map");
			
			conn.commit();
			
			
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
				
		return map;
	}
		
		
		
	// 고객 상품리스트페이지를 호출하는 서비스의 페이징
	public int getcustomerGoodsListLastPage(int rowPerPage) {
		Connection conn = null;
		
		try {
			conn = new DBUtil().getConnection(); // 디비연동
			conn.setAutoCommit(false); //자동커밋방지
			
			GoodsDao goodsDao = new GoodsDao(); 
			
			rowPerPage = goodsDao.lastPage(conn);
			//디버깅 
			System.out.print(rowPerPage +"<-rowPerPage");
		
			
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
		
		return rowPerPage;	
		
		
	}
	
	
	// 고객 상품리스트페이지를 호출하는 서비스
	public List<Map<String, Object>> getcustomerGoodsListByPage(int rowPerPage, int currentPage) {
		
		List<Map<String, Object>> list = null;
		Connection conn = null;
		// GoodsDao 호출
			
		int beginRow = ( currentPage -1 ) * rowPerPage;
		
		
		try {
			conn = new DBUtil().getConnection();
			GoodsDao goodsDao = new GoodsDao();
			
			list = goodsDao.selectcustomerGoodsListByPage(conn, rowPerPage, beginRow);
			
			System.out.println(list +"<-GoodsService의 list");
			
			
			if(list ==null) {
				throw new Exception();
			}
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
	
	
	
	// 상품 수정
	public int updateGoods(Goods goods, GoodsImg goodsImg) {
		int result =0;
		
		Connection conn = null;
		
		try {
			conn = new DBUtil().getConnection();
			conn.setAutoCommit(false);
			
			goodsDao = new GoodsDao();
			goodsImgDao = new GoodsImgDao();
			

			
			
			if(goodsDao.updateGoods(conn, goods) !=0) { // 0이아니면 키값 받아왓다는 소리
				//goodsImg.setGoodsNo(goodsNo); // >>> 원래는 굿즈NO를 따로 셋팅을 해야하는데 updateAction에서 셋팅해서 이게 필요가 없대
				result = goodsImgDao.updateGoodsImg(conn, goodsImg);
				if(result == 0) { // 입력실패
					throw new Exception(); // 이미지 입력실패시 강제로 롤백(catch절 이동)
				}
			}
			
			conn.commit();
		} catch(Exception e) {
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
			if(result !=0) {
				System.out.println("수정 성공");
			} else {System.out.println("수정 실패");}
			return result;
	}
	
	
	// 두개의 인서트를 하나의 트랜젝션으로해야한대	
	public int addGoods(Goods goods, GoodsImg goodsImg) {
		int result=0;
		
		Connection conn = null;
		
	
		//쿼리가 두개래
		
		
		try {
			conn = new DBUtil().getConnection();
			conn.setAutoCommit(false);
			goodsDao = new GoodsDao();
			goodsImgDao = new GoodsImgDao();
			
			//	
			int goodsNo = goodsDao.insertGoods(conn, goods); // goodsNo가 없어도 오토인크리먼트로 자동생성되어 DB에 입력
			
			if(goodsNo !=0) { // 0이아니면 키값 받아왓다는 소리
				result = goodsImg.setGoodsNo(goodsNo); // vo만들어야 오류 안난다는데
				if(goodsImgDao.insertGoodsImg(conn, goodsImg) ==0) { // 입력실패
					throw new Exception(); // 이미지 입력실패시 강제로 롤백(catch절 이동)
				}
			}
			
			conn.commit(); // 예외 업승ㄹ 때만 컵밋
		} catch(Exception e) {
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
			if(result !=0) {
				System.out.println("입력 성공");
			} else {System.out.println("입력 실패");}
			return result;
	}
	
	
	public Map<String, Object> getGoodsAndImgOne(int goodsNo) {
		Map<String,Object> map = null;
		
		Connection conn = null;
		
		try {
			conn = new DBUtil().getConnection();
			conn.setAutoCommit(false);
			GoodsDao goodsDao = new GoodsDao(); 	
			map = goodsDao.selectGoodsAndImgOne(conn, goodsNo);
			
			System.out.print(map +"<-getGoodsAndImgOne map");
			conn.commit();
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
		
		return map;
	}
	
	
	
	public int getGoodsListLastPage(int rowPerPage) {
		
		Connection conn = null;
		
		try {
			conn = new DBUtil().getConnection();
			conn.setAutoCommit(false); //자동커밋방지
			 
			// GoodsDao 객체생성
			GoodsDao goodsDao = new GoodsDao(); 
			
			// lastPage 메서드호출
			rowPerPage = goodsDao.lastPage(conn);
			
			//디버깅 
			System.out.print(rowPerPage +"<-rowPerPage");
		
			
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
		
		return rowPerPage;	
		
		
	}
	
	
	public List<Goods> getGoodsListByPage(final int rowPerPage, int currentPage) {
		List<Goods> list = new ArrayList<Goods>();		
		
		this.goodsDao = new GoodsDao();
		Connection conn = null;
		int beginRow = 0;	
		beginRow = (currentPage -1 ) * rowPerPage;
		
			
		try {
			conn = new DBUtil().getConnection();
			
			// GoodsDao 객체생성
			GoodsDao goodsDao = new GoodsDao();
			
			// 메서드 호출
			list = goodsDao.selectGoodsListByPage(conn, rowPerPage, beginRow);
		
			// 디버깅
			System.out.println(list +"<-goodsservice의 list"); 
		
			if(list==null ) {
				throw new Exception();
			}
			conn.commit(); // list에 무사히 들어오면 커밋 ㄱ
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				conn.rollback(); // 뭔가 잘못되며 보고후에 돌아갈거야
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
		return list;
	}
}
