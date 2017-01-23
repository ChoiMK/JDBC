package com.test.biz.impl;

import java.util.List;

/**
 * @author �ֹα�
 * @version 1.0
 * 
 *  <p>
 *  ���ϸ� : PstmtBizImpl.java <br/>
 *  ���� : JDBC PSTMT�� �׽�Ʈ �ϱ� ���� BIZ ���� Ŭ����<br/>
 *  
 *  �����̷�<br/>
 *  ------------------------------------------------<br/>
 *  ��������  |������|�������� <br/>
 *  ------------------------------------------------<br/>
 *  2017.01.19 �ֹα� ���ʻ���<br/>
 *  ------------------------------------------------<br/>
 *  </p>
 */
import com.test.biz.StmtBiz;
import com.test.dao.StmtDao;
import com.test.dao.impl.StmtDaoImpl;
import com.test.vo.TestVO;

/**
 * @author �ֹα�
 * @version 1.0
 * 
 * <p>
 * ���ϸ� : StmtBiz.java <br/>
 * ���� : JDBC STMT�� �׽�Ʈ �ϱ� ���� BIZ ���� Ŭ���� <br/>
 * 
 * �����̷�<br/>
 * --------------------------------------------<br/>
 * ��������     |������|��������<br/>
 * --------------------------------------------<br/>
 * 2017.01.19 �ֹα� ���ʻ���<br/>
 * --------------------------------------------<br/>
 * </p>
 */

public class StmtBizImpl implements StmtBiz{

	private StmtDao dao = StmtDaoImpl.getInstance();
	
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
