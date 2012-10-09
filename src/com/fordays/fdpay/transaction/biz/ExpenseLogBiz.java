package com.fordays.fdpay.transaction.biz;

import java.util.ArrayList;
import java.util.List;
import com.neza.exception.AppException;
import com.fordays.fdpay.agent.Agent;
import com.fordays.fdpay.transaction.Debit;
import com.fordays.fdpay.transaction.DebitListForm;
import com.fordays.fdpay.transaction.DebitLogListForm;
import com.fordays.fdpay.transaction.Expense;
import com.fordays.fdpay.transaction.ExpenseListForm;
import com.fordays.fdpay.transaction.ExpenseLogListForm;
import com.fordays.fdpay.user.SysUser;


public interface ExpenseLogBiz
{
	public void auditLog(Expense tempExpense,Agent agent,SysUser user,String note) throws AppException;
	public void approvalLog(Expense tempExpense,Agent agent,SysUser user,String note) throws AppException;
	public void advanceLog(Expense tempExpense,Agent agent,SysUser user,String note) throws AppException;
	public List list(ExpenseLogListForm ellf)throws AppException;
	public List getExpenseLogByContent(ExpenseLogListForm clf)throws AppException;
	public ArrayList<ArrayList<Object>> getExpenseLogDetailed(ExpenseLogListForm elf)throws AppException;
}
