package com.test.vo;

/**
 *	@author 최민규
 *	@version 1.0
 *	
 *	<p>
 *	파일명 : TestVO.java <br/>
 *	설명 : JDBC_TEST 테이블 데이터를 관리하기 위한 VO <br/>
 *
 * 	수정이력<br/>
 * 	-------------------------------------<br/>
 * 	수정일자		|수정인|수정내용<br/>
 * 	-------------------------------------<br/>
 * 	2017.01.19 최민규 최초생성<br/>
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
