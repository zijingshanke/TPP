package com.fordays.fdpay.transaction.biz;

import java.util.ArrayList;
import java.util.List;
import com.neza.exception.AppException;
import com.fordays.fdpay.agent.Agent;
import com.fordays.fdpay.transaction.Charge;
import com.fordays.fdpay.transaction.ChargeListForm;
import com.fordays.fdpay.transaction.ChargeLog;
import com.fordays.fdpay.transaction.ChargeLogListForm;
import com.fordays.fdpay.transaction.Debit;
import com.fordays.fdpay.transaction.DebitLogListForm;
import com.fordays.fdpay.user.SysUser;

public interface DebitLogBiz {

	public void auditLog(Debit tempDebit,Agent agent,SysUser user,String note) throws AppException;
	public void approvalLog(Debit tempDebit,Agent agent,SysUser user,String note) throws AppException;
	public void advanceLog(Debit tempDebit,Agent agent,SysUser user,String note) throws AppException;
	public List list(DebitLogListForm dlf)throws AppException;
	public List getDebitLogByContent(DebitLogListForm clf)throws AppException;
	public ArrayList<ArrayList<Object>> getDebitLogDetailed(DebitLogListForm dlf)throws AppException;
}
