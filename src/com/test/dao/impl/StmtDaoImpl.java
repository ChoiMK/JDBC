package com.test.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.test.dao.StmtDao;
import com.test.vo.TestVO;

/**
 * @author 최민규
 * @version 1.0
 * 
 * <p>
 * 파일명 : StmtDaoImpl.java <br/>
 * 설명 : JDBC STMT를 테스트 하기 위한 DAO 구현 클래스 <br/>
 * 
 * 수정이력<br/>
 * --------------------------------------------<br/>
 * 수정일자     |수정인|수정내용<br/>
 * --------------------------------------------<br/>
 * 2017.01.19 최민규 최초생성<br/>
 * --------------------------------------------<br/>
 * </p>
 */
public class StmtDaoImpl implements StmtDao{

	//싱글턴 패턴을 사용하기 위한 자신 클래스의 인스턴스
	private static StmtDaoImpl stmtDaoImpl = new StmtDaoImpl();
	
	//접속할 DB의 종류와 주소
	private static final String DB_URL = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
	
	//DB접속 ID
	private static final String DB_USER = "sem";
	
	//DB접속 패스워드
	private static final String DB_PASSWORD = "java";
	
	private StmtDaoImpl() {
		//new로 새로운 인스턴스를 생성하지 못하도록 막는 생성자
	}
	
	//Dao를 참조할때 신규 DAO를 생성하지 못하고 getInstatnce를 통해
	//기존에 만들어 놓은 인스턴스 하나만을 참조할 수 있음.
	public static StmtDaoImpl getInstance(){
		return stmtDaoImpl;
	}
	
	/**
	 * JDBC는 Java Database Connection의 약자로
	 * Java를 통해 DB에 접속해서 쿼리를 실행하고 그 결과를 받는 방법이다.
	 * JDBC를 실행하는 순서는 다음과 같다.
	 * 1. 데이터베이스 드라이버의 로드(DB는 오라클 뿐만 아니라 다른 여러 DBMS가 있기 때문에 각 DB 별 다른 DB드라이버를 사용해야 한다.)
	 * 2. Connection : 데이터베이스 연결
	 * 3. Statement : Connection으로부터 쿼리를 실행할 수 있는 스테이트먼트를 생성한다.
	 * 4. 쿼리의 실행 : Statement를 통해 executeQuery, executeUpdate 메서드를 통하여 쿼리를 실행한다.
	 * 5. 쿼리를 실행한 결과를 받는다.
	 *    - executeUpdate : create, drop, insert, update, delete 쿼리의 경우 쿼리가 실행되어 변경된 데이터의 갯수를 반환받는다.
	 *    - executeQuery : select 쿼리의 경우 실제로 조회된 데이터를 ResultSet으로 반환받는다.
	 * 6. 쿼리 실행이 끝나면 각 연결들을 모두 닫아주어야 한다. (****중요)
	 *    - (select 쿼리의 경우) ResultSet을 닫는다.
	 *    - statement를 닫는다.
	 *    - connection을 닫는다.
	 */
	@Override
	public boolean createTable() {
		
		//테이블 생성이 성공했는지 여부를 반환하기 위한 변수
		boolean isSuccess = false;
		
		//DB연결을 위한 Connection 객체
		Connection conn = null;
		
		//쿼리 실행을 위한 Statement 객체
		Statement stmt = null;
		
		//실행할 쿼리
		String query = "CREATE TABLE JDBC_TEST ("
				+ " TEST_ID NUMBER NOT NULL, " 
				+ " TEST_NM VARCHAR(50), "
				+ " TEST_DT DATE, "
				+ " CONSTRAINT PK_JDBC_TEST PRIMARY KEY (TEST_ID) "
				+ " ) ";
		
		try {
			//접속할 DB의 드라이버를 로딩한다.
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//데이터베이스의 연결을 설정한다.
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			
			//Statement를 가져온다.
			stmt = conn.createStatement();
			
			//SQL문을 실행한다.
			int result = stmt.executeUpdate(query);
			
			if(result<0){
				isSuccess = false;
			}else{
				isSuccess = true;
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				//Statement를 닫는다.
				if(stmt!=null){
					stmt.close();
				}
				//Connection을 닫는다.
				if(conn!=null){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return isSuccess;
	}

	@Override
	public boolean dropTable() {
		//테이블 삭제가 성공했는지 여부를 반환하기 위한 변수
		boolean isSuccess = false;
		
		//DB연결을 위한 Connection 객체
		Connection conn = null;
		
		//쿼리 실행을 위한 Statement 객체
		Statement stmt = null;
		
		//실행할 쿼리
		String query = "DROP TABLE JDBC_TEST";
		
		try {
			//접속할 DB의 드라이버를 로딩한다.
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//데이터베이스의 연결을 설정한다.
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			
			//Statement를 가져온다.
			stmt = conn.createStatement();
			
			//SQL문을 실행한다.
			int result = stmt.executeUpdate(query);
			
			if( result < 0 ) {
				isSuccess = false;
			} else {
				isSuccess = true;
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				//Statement를 닫는다.
				if(stmt != null) {
					stmt.close();
				}
				//Connection을 닫는다.
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return isSuccess;
	}

	@Override
	public boolean insert(TestVO vo) {

		//데이터 입력이 성공했는지 여부를 반환하기 위한 변수
		boolean isSuccess = false;
		
		//DB연결을 위한 Connection 객체
		Connection conn = null;
		
		//쿼리 실행을 위한 Statement 객체
		Statement stmt = null;
		
		//실행할 쿼리
		String query = "INSERT INTO JDBC_TEST (TEST_ID,TEST_NM,TEST_DT) "
				+ " VALUES "
				+ " ( "
				//TEST_ID는 다음 번호로 계속 생성해 주기 위해 TEST_ID의 최대값에 +1 하여 생성해준다.
				//NVL을 사용한 이유는 테이블에 아무런 데이터가 없을때는 MAX(TEST_ID)가 null이 나오기 때문
				+ " (SELECT NVL(MAX(TEST_ID),0)+1 FROM JDBC_TEST), "
				+ " '"+vo.getTestNm()+"', "
				+ " SYSDATE "
				+ " ) ";
		
		//접속할 DB의 드라이버를 로딩한다.
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//데이터베이스의 연결을 설정한다.
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			
			//Statement를 가져온다.
			stmt = conn.createStatement();
			
			//SQL문을 실행한다.
			int result = stmt.executeUpdate(query);
			
			if(result<0){
				isSuccess = false;
			}else{
				isSuccess = true;
			}
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
				try {
					if(stmt!= null){
					stmt.close();
					}
					//Connection을 닫는다
					if(conn!= null){
						conn.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				
			}
		}
		
		return isSuccess;
	}

	@Override
	public boolean update(TestVO vo) {
		//데이터 수정이 성공했는지 여부를 반환하기 위한 변수
		boolean isSuccess = false;
		
		//DB연결을 위한 Connection 객체
		Connection conn = null;
		
		//쿼리 실행을 위한 Statement 객체
		Statement stmt = null;
		
		//실행할 쿼리
		String query = "UPDATE JDBC_TEST "
				+ " SET "
				+ " TEST_NM = '"+vo.getTestNm()+"', "
				+ " TEST_DT = SYSDATE "
				+ " WHERE TEST_ID = '"+vo.getTestId()+"' ";
		
		//접속할 DB의 드라이버를 로딩한다.
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//데이터베이스의 연결을 설정한다.
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			
			//Statement를 가져온다.
			stmt = conn.createStatement();
			
			//SQL문을 실행한다.
			int result = stmt.executeUpdate(query);
			
			if(result<0){
				isSuccess = false;
			}else{
				isSuccess = true;
			}
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
				try {
					if(stmt!= null){
					stmt.close();
					}
					//Connection을 닫는다
					if(conn!= null){
						conn.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				
			}
		}
		
		return isSuccess;
	}

	@Override
	public boolean delete(int testId) {
		//데이터 삭제가 성공했는지 여부를 반환하기 위한 변수
		boolean isSuccess = false;
		
		//DB연결을 위한 Connection 객체
		Connection conn = null;
		
		//쿼리 실행을 위한 Statement 객체
		Statement stmt = null;
		
		//실행할 쿼리
		String query = "DELETE FROM JDBC_TEST WHERE TEST_ID = '"+testId+"'";
		
		try {
			//접속할 DB의 드라이버를 로딩한다.
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//데이터베이스의 연결을 설정한다.
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			
			//Statement를 가져온다.
			stmt = conn.createStatement();
			
			//SQL문을 실행한다.
			int result = stmt.executeUpdate(query);
			
			if( result < 0 ) {
				isSuccess = false;
			} else {
				isSuccess = true;
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				//Statement를 닫는다.
				if(stmt != null) {
					stmt.close();
				}
				//Connection을 닫는다.
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return isSuccess;
	}

	@Override
	public List<TestVO> select(TestVO vo) {
		
		// 결과 리스트
		List<TestVO> resultList = new ArrayList<TestVO>(); 
		
		// DB연결을 위한 Connection 객체
		Connection conn = null;

		// 쿼리 실행을 Statement 객체
		Statement stmt = null;
		
		// 결과값을 가져올 ResultSet
		ResultSet rs = null;

		// 실행할 쿼리
		String query = "SELECT * FROM JDBC_TEST WHERE 1=1 ";
		if(vo.getTestId()!=0){
			query += " AND TEST_ID = "+vo.getTestId();
		}
		if(vo.getTestNm()!=null&&!vo.getTestNm().equals("")){
			query += " AND TEST_NM LIKE '%"+vo.getTestNm()+"%'";
		}

		try {
			// 접속할 DB의 드라이버를 로딩한다.
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 데이터베이스의 연결을 설정한다.
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

			// Statement를 가져온다.
			stmt = conn.createStatement();

			// SQL문을 실행한다.
			// 데이터를 조회한 결과가 ResultSet으로 반환된다.
			rs = stmt.executeQuery(query);
			
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

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			// statement 닫기
			try {
				if(rs!=null){
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				// Connection을 닫는다
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultList;
	}

}
