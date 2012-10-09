package com.fordays.fdpay.transaction.biz;

import java.util.List;
import com.neza.exception.AppException;
import com.fordays.fdpay.agent.Agent;
import com.fordays.fdpay.transaction.Draw;
import com.fordays.fdpay.transaction.DrawLog;
import com.fordays.fdpay.transaction.DrawLogListForm;


public interface DrawLogBiz
{
	public void save(DrawLog drawLog)throws AppException;
	
	public DrawLog getDrawLogById(int id)throws AppException;
	
	public DrawLog queryDrawLogById(int id)throws AppException;

	public List getDrawLogs(DrawLogListForm clf)throws AppException;

	public int updateInfo(DrawLog drawLog)throws AppException;

	public long approval(Draw draw ,Agent agent,String note)throws AppException;
	public long achieve(Draw draw ,Agent agent,String note)throws AppException;
	
	public long audit(Draw draw ,Agent agent,String note)throws AppException;
	
	public List getDrawLogByContent(DrawLogListForm tlf) throws AppException;
}
