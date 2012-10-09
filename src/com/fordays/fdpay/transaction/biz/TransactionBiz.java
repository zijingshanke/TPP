package com.fordays.fdpay.transaction.biz;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import com.neza.exception.AppException;
import com.fordays.fdpay.agent.Agent;
import com.fordays.fdpay.transaction.OrderDetails;
import com.fordays.fdpay.transaction.Transaction;
import com.fordays.fdpay.transaction.TransactionBalance;
import com.fordays.fdpay.transaction.TransactionListForm;


public interface TransactionBiz
{

	public Transaction getTransactionById(long id) throws AppException;
	
	public Transaction queryTransactionById(long id)throws AppException;

	public List getTransactions(TransactionListForm ulf)throws AppException;
	
	public ArrayList<ArrayList<Object>> notPagingList(TransactionListForm ulf) throws AppException;

	public long updateInfo(Transaction transaction)throws AppException;
	
	public void saveTransaction(Transaction transaction) throws AppException;
	
	public void addOrderDetails(OrderDetails orderDetails)throws AppException;
	
	public List getAgentTransactions(TransactionListForm talf,boolean isPass)throws AppException;
	
	public ArrayList<ArrayList<Object>> getAllTransationList(TransactionListForm tlf,Agent agent)throws AppException;
	
	public ArrayList<ArrayList<Object>> getAllTransationBalanceByAgent(TransactionListForm tlf,Agent agent)throws AppException;
	
	public void statistAgentTransactionBalance(Agent agent) throws AppException;
	
	public TransactionBalance getLatestTransationBalance(Agent agent)throws AppException;
	
	public List getTransactionBalanceByAgent(TransactionListForm tlf)throws AppException;
	
	public int getTransactionBalanceRow(TransactionListForm tlf)throws AppException ;
}
