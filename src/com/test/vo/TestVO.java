package com.test.vo;

/**
 *	@author �ֹα�
 *	@version 1.0
 *	
 *	<p>
 *	���ϸ� : TestVO.java <br/>
 *	���� : JDBC_TEST ���̺� �����͸� �����ϱ� ���� VO <br/>
 *
 * 	�����̷�<br/>
 * 	-------------------------------------<br/>
 * 	��������		|������|��������<br/>
 * 	-------------------------------------<br/>
 * 	2017.01.19 �ֹα� ���ʻ���<br/>
 * -------------------------------------<br/>
 *	</p> 
 */
public class TestVO {
	//TEST_ID
	private int testId;
	//TEST_NM
	private String testNm;
	//TEST_DT
	private String testDt;
	public int getTestId() {
		return testId;
	}
	public void setTestId(int testId) {
		this.testId = testId;
	}
	public String getTestNm() {
		return testNm;
	}
	public void setTestNm(String testNm) {
		this.testNm = testNm;
	}
	public String getTestDt() {
		return testDt;
	}
	public void setTestDt(String testDt) {
		this.testDt = testDt;
	}
	
}
