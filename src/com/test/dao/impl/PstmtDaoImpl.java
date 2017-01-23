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
 * @author �ֹα�
 * @version 1.0
 * 
 * <p>
 * ���ϸ� : PstmtDaoImpl.java <br/>
 * ��  �� : JDBC_PSTMT�� �׽�Ʈ �ϱ� ���� DAO ���� Ŭ���� <br/>
 * 
 * �����̷�<br/>
 * ----------------------------------------<br/>
 * ��������	|������|��������<br/>
 * ----------------------------------------<br/>
 * 2017.01.19 �ֹα� ���ʻ���<br/>
 * ----------------------------------------<br/>
 * </p>
*/
public class PstmtDaoImpl implements PstmtDao{

	// �̱��� ������ ����ϱ� ���� �ڽ� Ŭ������ �ν��Ͻ�
	private static PstmtDaoImpl pstmtDaoImpl = new PstmtDaoImpl();

	// ������ DB�� ������ �ּҰ� �ʿ�
	private static final String DB_URL = "jdbc:oracle:thin:@127.0.0.1:1521:XE";

	// DB���� ID
	private static final String DB_USER = "sem";
	
	// DB���� �н�����
	private static final String DB_PASSWORD = "java";
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private PstmtDaoImpl() {
		//new�� ���ο� �ν��Ͻ��� �������� ���ϵ��� ���� ������
		//������ DB�� ����̹��� �ε��Ѵ�
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static PstmtDaoImpl getInstance(){
		return pstmtDaoImpl;
	}
	
	//Connection �ϴ� �κ��� �޼���� ����� �ߺ� �ڵ��� ���� ������ ����.
	public void connect(){
		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//Ŀ�ؼ��� ���� �κе� ���� �޼���� ����� ����
	public void disconnect(){
		try {
			// ResultSet �ݱ�
			if(rs!=null){
				rs.close();
			}
			// statement �ݱ�
			if (pstmt != null) {
				pstmt.close();
			}
			// Connection�� �ݴ´�
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
		
		//������ ����
		String query = "CREATE TABLE JDBC_TEST ("
				+ " TEST_ID NUMBER NOT NULL, " 
				+ " TEST_NM VARCHAR(50), "
				+ " TEST_DT DATE, "
				+ " CONSTRAINT PK_JDBC_TEST PRIMARY KEY (TEST_ID) "
				+ " ) ";
		
		this.connect();
		
		try {
			//Statement�� �����´�.
			pstmt = conn.prepareStatement(query);
			
			//SQL���� �����Ѵ�.
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
		
		//������ ����
		String query = "DROP TABLE JDBC_TEST";
		
		this.connect();
		
		try {
			// Statement�� �����´�.
			pstmt = conn.prepareStatement(query);
			
			// SQL���� �����Ѵ�.
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
		
		//������ ����
		String query = "INSERT INTO JDBC_TEST(TEST_ID, TEST_NM, TEST_DT)"
				+ " VALUES "
				+ " ( "
				//TEST_ID�� ���� ��ȣ�� ��� ������ �ֱ� ���� TEST_ID�� �ִ밪�� +1�Ͽ� �������ش�
				//NVL�� ����� ������ ���̺� �ƹ��� �����Ͱ� ��������  MAX(TEST_ID)�� null�� ���� �����̴�
				+ " (SELECT NVL(MAX(TEST_ID),0)+1 FROM JDBC_TEST),"
				+ " ?, "
				+ " SYSDATE " //sysdate�� ����ð��� �ǹ��Ѵ�
				+ " ) ";
		
		this.connect();
		
		try {
			// Statement�� �����´�.
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, vo.getTestNm());
			
			// SQL���� �����Ѵ�.
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
			// Statement�� �����´�.
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, vo.getTestNm());
			pstmt.setInt(2, vo.getTestId());
			
			// SQL���� �����Ѵ�.
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
			// Statement�� �����´�.
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, testId);
			
			// SQL���� �����Ѵ�.
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
		
		// ������ ����
		String query = "SELECT * FROM JDBC_TEST WHERE 1=1 ";
		if(vo.getTestId()!=0){
			query += " AND TEST_ID = ?";
		}
		if(vo.getTestNm()!=null&&!vo.getTestNm().equals("")){
			query += " AND TEST_NM LIKE '%'||?||'%'";
		}

		
		this.connect();
		
		//Statement�� �����´�.
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
			
			//SQL���� �����Ѵ�
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				//��ȯ�� ��� ����Ʈ�� �߰��� VO��ü�� �űԻ���
				TestVO vo2 = new TestVO();
				
				//resultSet���� �÷������� �����͸� �����´�.
				int testId = rs.getInt("TEST_ID");
				String testNm = rs.getString("TEST_NM");
				String testDt = rs.getString("TEST_DT");
				
				//vo�� ������ �����͸� �ִ´�.
				vo2.setTestId(testId);
				vo2.setTestNm(testNm);
				vo2.setTestDt(testDt);
				
				//�����͸� ���� vo�� ��ȯ�� ����Ʈ�� �߰��Ѵ�.
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
