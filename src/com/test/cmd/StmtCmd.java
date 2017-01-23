package com.test.cmd;

import java.util.List;

import com.test.biz.StmtBiz;
import com.test.biz.impl.StmtBizImpl;
import com.test.vo.TestVO;

public class StmtCmd {
	
	public void createTable(){
		StmtBiz biz = new StmtBizImpl();
		biz.createTable();
	}
	
	public void dropTable(){
		StmtBiz biz = new StmtBizImpl();
		biz.dropTable();
	}
	
	public void insertData(){
		StmtBiz biz = new StmtBizImpl();
		for(int i=0;i<10;i++){
			TestVO vo = new TestVO();
			vo.setTestNm("abc");
			biz.insert(vo);
		}
	}
	
	public void updateData(){
		StmtBiz biz = new StmtBizImpl();
		TestVO vo = new TestVO();
		vo.setTestId(1);
		vo.setTestNm("def");
		biz.update(vo);
	}
	
	public void deleteData(){
		StmtBiz biz = new StmtBizImpl();
		biz.delete(2);
	}
	
	public List<TestVO> selectData(){
		StmtBiz biz = new StmtBizImpl();
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
		StmtCmd cmd = new StmtCmd();
//		cmd.insertData();
//		cmd.updateData();
//		cmd.deleteData();
		cmd.selectData();
	}
}
