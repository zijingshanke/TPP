package com.fordays.fdpay.transaction.dao;

import java.util.List;
import com.neza.exception.AppException;
import com.fordays.fdpay.transaction.Debit;
import com.fordays.fdpay.transaction.DebitListForm;
import com.fordays.fdpay.transaction.ExpenseLog;
import com.fordays.fdpay.transaction.ExpenseLogListForm;
import com.neza.base.BaseDAO;



public interface ExpenseLogDAO extends BaseDAO
{
	public List list(ExpenseLogListForm tlf)throws AppException;
	public long  save(ExpenseLog el)throws AppException;
	public List getExpenseLogByContent(ExpenseLogListForm clf)throws AppException;
	public List getExpenseLogDetailed(ExpenseLogListForm clf)throws AppException;
}
