package com.test.cmd;

import java.util.List;

import com.test.biz.PstmtBiz;
import com.test.biz.impl.PstmtBizImpl;
import com.test.vo.TestVO;

public class PstmtCmd {
	
	public void createTable(){
		PstmtBiz biz = new PstmtBizImpl();
		biz.createTable();
	}
	
	public void dropTable(){
		PstmtBiz biz = new PstmtBizImpl();
		biz.dropTable();
	}
	
	public void insertData(){
		PstmtBiz biz = new PstmtBizImpl();
		for(int i=0;i<10;i++){
			TestVO vo = new TestVO();
			vo.setTestNm("abc");
			biz.insert(vo);
		}
	}
	
	public void updateData(){
		PstmtBiz biz = new PstmtBizImpl();
		TestVO vo = new TestVO();
		vo.setTestId(1);
		vo.setTestNm("def");
		biz.update(vo);
	}
	
	public void deleteData(){
		PstmtBiz biz = new PstmtBizImpl();
		biz.delete(2);
	}
	
	public List<TestVO> selectData(){
		PstmtBiz biz = new PstmtBizImpl();
		List<TestVO> list = null;
		TestVO vo = new TestVO();
		//vo.setTestId(1);
		vo.setTestNm("abc");
		list = biz.select(vo);
		
		System.out.println(list.size());
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i).getTestId()+","+list.get(i).getTestNm()+","+list.get(i).getTestDt());
		}
		
		return null;
	}
	
	public static void main(String[] args) {
		PstmtCmd cmd = new PstmtCmd();
//		cmd.insertData();
//		cmd.updateData();
//		cmd.deleteData();
		cmd.selectData();
	}
}
