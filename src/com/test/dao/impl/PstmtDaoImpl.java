package com.test.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.test.dao.PstmtDao;
import com.test.vo.TestVO;

/**
 * @author 최민규
 * @version 1.0
 * 
 * <p>
 * 파일명 : PstmtDaoImpl.java <br/>
 * 설  명 : JDBC_PSTMT를 테스트 하기 위한 DAO 구현 클래스 <br/>
 * 
 * 수정이력<br/>
 * ----------------------------------------<br/>
 * 수정일자	|수정인|수정내용<br/>
 * ----------------------------------------<br/>
 * 2017.01.19 최민규 최초생성<br/>
 * ----------------------------------------<br/>
 * </p>
*/
public class PstmtDaoImpl implements PstmtDao{

	// 싱글톤 패턴을 사용하기 위한 자신 클래스의 인스턴스
	private static PstmtDaoImpl pstmtDaoImpl = new PstmtDaoImpl();

	// 접속할 DB의 종류와 주소가 필요
	private static final String DB_URL = "jdbc:oracle:thin:@127.0.0.1:1521:XE";

	// DB접속 ID
	private static final String DB_USER = "sem";
	
	// DB접속 패스워드
	private static final String DB_PASSWORD = "java";
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private PstmtDaoImpl() {
		//new로 새로운 인스턴스를 생성하지 못하도록 막는 생성자
		//접속할 DB의 드라이버를 로딩한다
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static PstmtDaoImpl getInstance(){
		return pstmtDaoImpl;
	}
	
	//Connection 하는 부분을 메서드로 만들어 중복 코딩을 하지 말도록 하자.
	public void connect(){
		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//커넥션을 끊는 부분도 따로 메서드로 만들어 관리
	public void disconnect(){
		try {
			// ResultSet 닫기
			if(rs!=null){
				rs.close();
			}
			// statement 닫기
			if (pstmt != null) {
				pstmt.close();
			}
			// Connection을 닫는다
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean createTable() {
		boolean isSuccess = true;
		
		//실행할 쿼리
		String query = "CREATE TABLE JDBC_TEST ("
				+ " TEST_ID NUMBER NOT NULL, " 
				+ " TEST_NM VARCHAR(50), "
				+ " TEST_DT DATE, "
				+ " CONSTRAINT PK_JDBC_TEST PRIMARY KEY (TEST_ID) "
				+ " ) ";
		
		this.connect();
		
		try {
			//Statement를 가져온다.
			pstmt = conn.prepareStatement(query);
			
			//SQL문을 실행한다.
			isSuccess = pstmt.execute();
			
		} catch (SQLException e) {
			isSuccess = false;
			e.printStackTrace();
		} finally {
			this.disconnect();
		}
		
		return isSuccess;
	}

	@Override
	public boolean dropTable() {
		boolean isSuccess = true;
		
		//실행할 쿼리
		String query = "DROP TABLE JDBC_TEST";
		
		this.connect();
		
		try {
			// Statement를 가져온다.
			pstmt = conn.prepareStatement(query);
			
			// SQL문을 실행한다.
			isSuccess = pstmt.execute();
		} catch (SQLException e) {
			isSuccess = false;
			e.printStackTrace();
		} finally {
			this.disconnect();
		}
		
		return isSuccess;
	}

	@Override
	public boolean insert(TestVO vo) {
		boolean isSuccess = true;
		
		//실행할 쿼리
		String query = "INSERT INTO JDBC_TEST(TEST_ID, TEST_NM, TEST_DT)"
				+ " VALUES "
				+ " ( "
				//TEST_ID는 다음 번호로 계속 생성해 주기 위해 TEST_ID의 최대값에 +1하여 생성해준다
				//NVL을 사용한 이유는 테이블에 아무런 테이터가 없을때는  MAX(TEST_ID)가 null이 나왹 때문이다
				+ " (SELECT NVL(MAX(TEST_ID),0)+1 FROM JDBC_TEST),"
				+ " ?, "
				+ " SYSDATE " //sysdate는 현재시간을 의미한다
				+ " ) ";
		
		this.connect();
		
		try {
			// Statement를 가져온다.
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, vo.getTestNm());
			
			// SQL문을 실행한다.
			isSuccess = pstmt.execute();
		} catch (SQLException e) {
			isSuccess = false;
			e.printStackTrace();
		} finally {
			this.disconnect();
		}
		
		return isSuccess;
	}

	@Override
	public boolean update(TestVO vo) {
		boolean isSuccess = true;
		
		String query = "UPDATE JDBC_TEST "
				+ " SET "
				+ " TEST_NM = ?, "
				+ " TEST_DT = SYSDATE "
				+ " WHERE TEST_ID = ? ";
		
		this.connect();
		
		try {
			// Statement를 가져온다.
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, vo.getTestNm());
			pstmt.setInt(2, vo.getTestId());
			
			// SQL문을 실행한다.
			isSuccess = pstmt.execute();
		} catch (SQLException e) {
			isSuccess = false;
			e.printStackTrace();
		} finally {
			this.disconnect();
		}
		
		return isSuccess;
	}

	@Override
	public boolean delete(int testId) {
		boolean isSuccess = true;
		
		String query = "DELETE FROM JDBC_TEST WHERE TEST_ID = ? ";
		
		this.connect();
		
		try {
			// Statement를 가져온다.
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, testId);
			
			// SQL문을 실행한다.
			isSuccess = pstmt.execute();
		} catch (SQLException e) {
			isSuccess = false;
			e.printStackTrace();
		} finally {
			this.disconnect();
		}
		
		return isSuccess;
	}

	@Override
	public List<TestVO> select(TestVO vo) {
		
		List<TestVO> resultList = new ArrayList<TestVO>();
		
		// 실행할 쿼리
		String query = "SELECT * FROM JDBC_TEST WHERE 1=1 ";
		if(vo.getTestId()!=0){
			query += " AND TEST_ID = ?";
		}
		if(vo.getTestNm()!=null&&!vo.getTestNm().equals("")){
			query += " AND TEST_NM LIKE '%'||?||'%'";
		}

		
		this.connect();
		
		//Statement를 가져온다.
		try {
			pstmt = conn.prepareStatement(query);
			int index = 1;
			if(vo.getTestId()!=0){
				pstmt.setInt(index, vo.getTestId());
				index++;
			}
			if(vo.getTestNm()!=null && !vo.getTestNm().equals("")){
				pstmt.setString(index, vo.getTestNm());
			}
			
			//SQL문을 실행한다
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				//반환할 결과 리스트에 추가할 VO객체를 신규생성
				TestVO vo2 = new TestVO();
				
				//resultSet에서 컬럼명으로 데이터를 가져온다.
				int testId = rs.getInt("TEST_ID");
				String testNm = rs.getString("TEST_NM");
				String testDt = rs.getString("TEST_DT");
				
				//vo에 각각의 데이터를 넣는다.
				vo2.setTestId(testId);
				vo2.setTestNm(testNm);
				vo2.setTestDt(testDt);
				
				//데이터를 넣은 vo를 반환할 리스트에 추가한다.
				resultList.add(vo2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.disconnect();
		}
		
		return resultList;
	}

}
