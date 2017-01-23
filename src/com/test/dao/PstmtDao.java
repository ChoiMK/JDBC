package com.test.dao;

import java.util.List;

import com.test.vo.TestVO;

/**
 * @author 최민규
 * @version 1.0
 * 
 * <p>
 * 파일명 : PstmtDao.java <br/>
 * 설명 : JDBC PSTMT를 테스트 하기 위한 DAO 인터페이스 <br/>
 * 
 * 수정이력<br/>
 * --------------------------------------------<br/>
 * 수정일자     |수정인|수정내용<br/>
 * --------------------------------------------<br/>
 * 2017.01.19 최민규 최초생성<br/>
 * --------------------------------------------<br/>
 * </p>
 */
public interface PstmtDao {

	/**
	 * createTable - 테이블 신규생성
	 * @param null
	 * @return boolean
	 */
	public boolean createTable();
	
	/**
	 * dropTable - 테이블 삭제
	 * @param null
	 * @return boolean
	 */
	public boolean dropTable();
	
	/**
	 * insert - 테이블 데이터입력
	 * @param TestVO
	 * @return boolean
	 */
	public boolean insert(TestVO vo);
	
	/**
	 * update - 테이블 데이터수정
	 * @param TestVO
	 * @return boolean
	 */
	public boolean update(TestVO vo);
	
	/**
	 * delete - 테이블 데이터삭제
	 * @param int
	 * @return boolean
	 */
	public boolean delete(int testId);
	
	/**
	 * select - 테이블 데이터조회
	 * @param TestVO
	 * @return java.util.List
	 */
	public List<TestVO> select(TestVO vo);
}
