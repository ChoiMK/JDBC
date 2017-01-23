package com.test.biz.impl;

import java.util.List;


import com.test.biz.PstmtBiz;
import com.test.dao.PstmtDao;
import com.test.dao.impl.PstmtDaoImpl;
import com.test.vo.TestVO;

/**
 * @author 최민규
 * @version 1.0
 * 
 * <p>
 * 파일명 : PstmtBizImpl.java <br/>
 * 설명 : JDBC PSTMT를 테스트 하기 위한 BIZ 구현 클래스 <br/>
 * 
 * 수정이력<br/>
 * --------------------------------------------<br/>
 * 수정일자     |수정인|수정내용<br/>
 * --------------------------------------------<br/>
 * 2017.01.19 최민규 최초생성<br/>
 * --------------------------------------------<br/>
 * </p>
 */

public class PstmtBizImpl implements PstmtBiz{

	private PstmtDao dao = PstmtDaoImpl.getInstance();
	
	@Override
	public boolean createTable() {
		boolean isSuccess = dao.createTable();
		return isSuccess;
	}

	@Override
	public boolean dropTable() {
		boolean isSuccess = dao.dropTable();
		return isSuccess;
	}

	@Override
	public boolean insert(TestVO vo) {
		boolean isSuccess = dao.insert(vo);
		return isSuccess;
	}

	@Override
	public boolean update(TestVO vo) {
		boolean isSuccess =  dao.update(vo);
		return isSuccess;
	}

	@Override
	public boolean delete(int testId) {
		boolean isSuccess = dao.delete(testId);
		return isSuccess;
	}

	@Override
	public List<TestVO> select(TestVO vo) {
		List<TestVO> resultList = dao.select(vo);
		return resultList;
	}

}
