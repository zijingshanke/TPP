package com.fordays.fdpay.transaction.dao;

import java.util.List;
import com.neza.exception.AppException;
import com.fordays.fdpay.transaction.Debit;
import com.fordays.fdpay.transaction.DebitListForm;
import com.fordays.fdpay.transaction.Expense;
import com.fordays.fdpay.transaction.ExpenseListForm;
import com.neza.base.BaseDAO;



public interface ExpenseDAO extends BaseDAO
{
	public List list(ExpenseListForm tlf)throws AppException;
	public Expense getExpenseById(long id)throws AppException;
	public Expense getExpenseByNo(String no)throws AppException;
	public long update(Expense expense)throws AppException;
	public List getAllExpenseList(ExpenseListForm elf)throws AppException;
}
