package com.fordays.fdpay.transaction.dao;

import java.util.List;
import com.neza.exception.AppException;
import com.fordays.fdpay.transaction.Debit;
import com.fordays.fdpay.transaction.DebitListForm;
import com.fordays.fdpay.transaction.DebitLogListForm;
import com.fordays.fdpay.transaction.Expense;
import com.neza.base.BaseDAO;



public interface DebitDAO extends BaseDAO
{
	public List list(DebitListForm tlf)throws AppException;
	
	public Debit getDebitById(long id)throws AppException;
	public Debit getDebitByNo(String no)throws AppException;
	public long update(Debit debit)throws AppException;

	public List getAllDebitList(DebitListForm dlf)throws AppException;
	public Expense getExpenseByDebitId(long id);
}
