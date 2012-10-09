package com.fordays.fdpay.transaction.biz;

import java.util.ArrayList;
import java.util.List;
import com.neza.exception.AppException;
import com.fordays.fdpay.agent.Agent;
import com.fordays.fdpay.transaction.Debit;
import com.fordays.fdpay.transaction.DebitListForm;
import com.fordays.fdpay.transaction.Expense;
import com.fordays.fdpay.transaction.ExpenseListForm;


public interface ExpenseBiz
{
	public List list(ExpenseListForm elf)throws AppException;
	public Expense getExpenseById(long id)throws AppException;
	public long updateInfo(Expense expense)throws AppException;
	public long advanceAchieve(Expense expense)throws AppException;
	public Expense getDebitByNo(String orderNo)throws AppException;
	public ArrayList<ArrayList<Object>> listAllExpense(ExpenseListForm elf)throws AppException;
}
