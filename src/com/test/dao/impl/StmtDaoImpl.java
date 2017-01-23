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
 * @author �ֹα�
 * @version 1.0
 * 
 * <p>
 * ���ϸ� : StmtDaoImpl.java <br/>
 * ���� : JDBC STMT�� �׽�Ʈ �ϱ� ���� DAO ���� Ŭ���� <br/>
 * 
 * �����̷�<br/>
 * --------------------------------------------<br/>
 * ��������     |������|��������<br/>
 * --------------------------------------------<br/>
 * 2017.01.19 �ֹα� ���ʻ���<br/>
 * --------------------------------------------<br/>
 * </p>
 */
public class StmtDaoImpl implements StmtDao{

	//�̱��� ������ ����ϱ� ���� �ڽ� Ŭ������ �ν��Ͻ�
	private static StmtDaoImpl stmtDaoImpl = new StmtDaoImpl();
	
	//������ DB�� ������ �ּ�
	private static final String DB_URL = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
	
	//DB���� ID
	private static final String DB_USER = "sem";
	
	//DB���� �н�����
	private static final String DB_PASSWORD = "java";
	
	private StmtDaoImpl() {
		//new�� ���ο� �ν��Ͻ��� �������� ���ϵ��� ���� ������
	}
	
	//Dao�� �����Ҷ� �ű� DAO�� �������� ���ϰ� getInstatnce�� ����
	//������ ����� ���� �ν��Ͻ� �ϳ����� ������ �� ����.
	public static StmtDaoImpl getInstance(){
		return stmtDaoImpl;
	}
	
	/**
	 * JDBC�� Java Database Connection�� ���ڷ�
	 * Java�� ���� DB�� �����ؼ� ������ �����ϰ� �� ����� �޴� ����̴�.
	 * JDBC�� �����ϴ� ������ ������ ����.
	 * 1. �����ͺ��̽� ����̹��� �ε�(DB�� ����Ŭ �Ӹ� �ƴ϶� �ٸ� ���� DBMS�� �ֱ� ������ �� DB �� �ٸ� DB����̹��� ����ؾ� �Ѵ�.)
	 * 2. Connection : �����ͺ��̽� ����
	 * 3. Statement : Connection���κ��� ������ ������ �� �ִ� ������Ʈ��Ʈ�� �����Ѵ�.
	 * 4. ������ ���� : Statement�� ���� executeQuery, executeUpdate �޼��带 ���Ͽ� ������ �����Ѵ�.
	 * 5. ������ ������ ����� �޴´�.
	 *    - executeUpdate : create, drop, insert, update, delete ������ ��� ������ ����Ǿ� ����� �������� ������ ��ȯ�޴´�.
	 *    - executeQuery : select ������ ��� ������ ��ȸ�� �����͸� ResultSet���� ��ȯ�޴´�.
	 * 6. ���� ������ ������ �� ������� ��� �ݾ��־�� �Ѵ�. (****�߿�)
	 *    - (select ������ ���) ResultSet�� �ݴ´�.
	 *    - statement�� �ݴ´�.
	 *    - connection�� �ݴ´�.
	 */
	@Override
	public boolean createTable() {
		
		//���̺� ������ �����ߴ��� ���θ� ��ȯ�ϱ� ���� ����
		boolean isSuccess = false;
		
		//DB������ ���� Connection ��ü
		Connection conn = null;
		
		//���� ������ ���� Statement ��ü
		Statement stmt = null;
		
		//������ ����
		String query = "CREATE TABLE JDBC_TEST ("
				+ " TEST_ID NUMBER NOT NULL, " 
				+ " TEST_NM VARCHAR(50), "
				+ " TEST_DT DATE, "
				+ " CONSTRAINT PK_JDBC_TEST PRIMARY KEY (TEST_ID) "
				+ " ) ";
		
		try {
			//������ DB�� ����̹��� �ε��Ѵ�.
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//�����ͺ��̽��� ������ �����Ѵ�.
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			
			//Statement�� �����´�.
			stmt = conn.createStatement();
			
			//SQL���� �����Ѵ�.
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
				//Statement�� �ݴ´�.
				if(stmt!=null){
					stmt.close();
				}
				//Connection�� �ݴ´�.
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
		//���̺� ������ �����ߴ��� ���θ� ��ȯ�ϱ� ���� ����
		boolean isSuccess = false;
		
		//DB������ ���� Connection ��ü
		Connection conn = null;
		
		//���� ������ ���� Statement ��ü
		Statement stmt = null;
		
		//������ ����
		String query = "DROP TABLE JDBC_TEST";
		
		try {
			//������ DB�� ����̹��� �ε��Ѵ�.
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//�����ͺ��̽��� ������ �����Ѵ�.
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			
			//Statement�� �����´�.
			stmt = conn.createStatement();
			
			//SQL���� �����Ѵ�.
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
				//Statement�� �ݴ´�.
				if(stmt != null) {
					stmt.close();
				}
				//Connection�� �ݴ´�.
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

		//������ �Է��� �����ߴ��� ���θ� ��ȯ�ϱ� ���� ����
		boolean isSuccess = false;
		
		//DB������ ���� Connection ��ü
		Connection conn = null;
		
		//���� ������ ���� Statement ��ü
		Statement stmt = null;
		
		//������ ����
		String query = "INSERT INTO JDBC_TEST (TEST_ID,TEST_NM,TEST_DT) "
				+ " VALUES "
				+ " ( "
				//TEST_ID�� ���� ��ȣ�� ��� ������ �ֱ� ���� TEST_ID�� �ִ밪�� +1 �Ͽ� �������ش�.
				//NVL�� ����� ������ ���̺� �ƹ��� �����Ͱ� �������� MAX(TEST_ID)�� null�� ������ ����
				+ " (SELECT NVL(MAX(TEST_ID),0)+1 FROM JDBC_TEST), "
				+ " '"+vo.getTestNm()+"', "
				+ " SYSDATE "
				+ " ) ";
		
		//������ DB�� ����̹��� �ε��Ѵ�.
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//�����ͺ��̽��� ������ �����Ѵ�.
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			
			//Statement�� �����´�.
			stmt = conn.createStatement();
			
			//SQL���� �����Ѵ�.
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
					//Connection�� �ݴ´�
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
		//������ ������ �����ߴ��� ���θ� ��ȯ�ϱ� ���� ����
		boolean isSuccess = false;
		
		//DB������ ���� Connection ��ü
		Connection conn = null;
		
		//���� ������ ���� Statement ��ü
		Statement stmt = null;
		
		//������ ����
		String query = "UPDATE JDBC_TEST "
				+ " SET "
				+ " TEST_NM = '"+vo.getTestNm()+"', "
				+ " TEST_DT = SYSDATE "
				+ " WHERE TEST_ID = '"+vo.getTestId()+"' ";
		
		//������ DB�� ����̹��� �ε��Ѵ�.
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//�����ͺ��̽��� ������ �����Ѵ�.
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			
			//Statement�� �����´�.
			stmt = conn.createStatement();
			
			//SQL���� �����Ѵ�.
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
					//Connection�� �ݴ´�
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
		//������ ������ �����ߴ��� ���θ� ��ȯ�ϱ� ���� ����
		boolean isSuccess = false;
		
		//DB������ ���� Connection ��ü
		Connection conn = null;
		
		//���� ������ ���� Statement ��ü
		Statement stmt = null;
		
		//������ ����
		String query = "DELETE FROM JDBC_TEST WHERE TEST_ID = '"+testId+"'";
		
		try {
			//������ DB�� ����̹��� �ε��Ѵ�.
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//�����ͺ��̽��� ������ �����Ѵ�.
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			
			//Statement�� �����´�.
			stmt = conn.createStatement();
			
			//SQL���� �����Ѵ�.
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
				//Statement�� �ݴ´�.
				if(stmt != null) {
					stmt.close();
				}
				//Connection�� �ݴ´�.
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
		
		// ��� ����Ʈ
		List<TestVO> resultList = new ArrayList<TestVO>(); 
		
		// DB������ ���� Connection ��ü
		Connection conn = null;

		// ���� ������ Statement ��ü
		Statement stmt = null;
		
		// ������� ������ ResultSet
		ResultSet rs = null;

		// ������ ����
		String query = "SELECT * FROM JDBC_TEST WHERE 1=1 ";
		if(vo.getTestId()!=0){
			query += " AND TEST_ID = "+vo.getTestId();
		}
		if(vo.getTestNm()!=null&&!vo.getTestNm().equals("")){
			query += " AND TEST_NM LIKE '%"+vo.getTestNm()+"%'";
		}

		try {
			// ������ DB�� ����̹��� �ε��Ѵ�.
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// �����ͺ��̽��� ������ �����Ѵ�.
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

			// Statement�� �����´�.
			stmt = conn.createStatement();

			// SQL���� �����Ѵ�.
			// �����͸� ��ȸ�� ����� ResultSet���� ��ȯ�ȴ�.
			rs = stmt.executeQuery(query);
			
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

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			// statement �ݱ�
			try {
				if(rs!=null){
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				// Connection�� �ݴ´�
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
