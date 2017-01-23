package com.test.biz;

import java.util.List;

import com.test.vo.TestVO;

/**
 * @author �ֹα�
 * @version 1.0
 * 
 * <p>
 * ���ϸ� : StmtBiz.java <br/>
 * ���� : JDBC STMT�� �׽�Ʈ �ϱ� ���� BIZ �������̽� <br/>
 * 
 * �����̷�<br/>
 * --------------------------------------------<br/>
 * ��������     |������|��������<br/>
 * --------------------------------------------<br/>
 * 2017.01.19 �ֹα� ���ʻ���<br/>
 * --------------------------------------------<br/>
 * </p>
 */
public interface StmtBiz {
	/**
	 * createTable - ���̺� �űԻ���
	 * @param null
	 * @return boolean
	 */
	public boolean createTable();
	
	/**
	 * dropTable - ���̺� ����
	 * @param null
	 * @return boolean
	 */
	public boolean dropTable();
	
	/**
	 * insert - ���̺� �������Է�
	 * @param TestVO
	 * @return boolean
	 */
	public boolean insert(TestVO vo);
	
	/**
	 * update - ���̺� �����ͼ���
	 * @param TestVO
	 * @return boolean
	 */
	public boolean update(TestVO vo);
	
	/**
	 * delete - ���̺� �����ͻ���
	 * @param int
	 * @return boolean
	 */
	public boolean delete(int testId);
	
	/**
	 * select - ���̺� ��������ȸ
	 * @param TestVO
	 * @return java.util.List
	 */
	public List<TestVO> select(TestVO vo);
}
