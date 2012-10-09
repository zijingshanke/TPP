package com.fordays.fdpay.transaction.biz;

import java.util.ArrayList;
import java.util.List;
import com.neza.exception.AppException;
import com.fordays.fdpay.agent.Agent;
import com.fordays.fdpay.transaction.Debit;
import com.fordays.fdpay.transaction.DebitListForm;
import com.fordays.fdpay.transaction.Expense;


public interface DebitBiz
{
	public List list(DebitListForm dlf)throws AppException;
	public Debit getDebitById(long id)throws AppException;
	public Debit getDebitByNo(String no)throws AppException;
	public long updateInfo(Debit debit)throws AppException;
	public long advanceAchieve(Debit debit)throws AppException;
	public ArrayList<ArrayList<Object>> listAllDebit(DebitListForm dlf)throws AppException;
	public Expense getExpenseByDebitId(long id);
}
