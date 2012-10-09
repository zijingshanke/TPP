package com.fordays.fdpay.transaction.dao;

import java.math.BigDecimal;
import java.util.List;
import com.neza.exception.AppException;
import com.fordays.fdpay.agent.Agent;
import com.fordays.fdpay.transaction.OrderDetails;

import com.fordays.fdpay.transaction.Transaction;
import com.fordays.fdpay.transaction.TransactionBalance;
import com.fordays.fdpay.transaction.TransactionListForm;
import com.neza.base.BaseDAO;



public interface TransactionDAO extends BaseDAO 
{
	public List list(TransactionListForm tlf)throws AppException ;
	public List notPagingList(TransactionListForm tlf)throws AppException ;
	public List getAllTransationListByTransationBalance()throws AppException ;
	
	public List getAllTransationBalance()throws AppException ;
	public TransactionBalance getLatestTransationBalance(Agent agent)throws AppException ;
	public List getAllTransationList(TransactionListForm tlf)throws AppException ;
	
	public long save(Transaction transaction)throws AppException ;
	
	public long merge(Transaction transaction)throws AppException ;
	
	public long update(Transaction transaction)throws AppException ;
	
	public Transaction getTransactionById(long id)throws AppException ;
	
	public void deleteById(long id)throws AppException ; 
	
	public Transaction queryTransactionById(long id)throws AppException ;
	
	public OrderDetails saveTransaction(List<Transaction> listTransaction,OrderDetails orderDetails,List<Agent> listAgent) throws AppException ;
	
	public void saveTransaction(Transaction transaction,Agent listAgent) throws AppException ;
	
	public double getTotalRefundBalance(long orderId,long avouchAccount) throws AppException;
	
	public void addOrderDetails(OrderDetails orderDetails)throws AppException;
	public long addTransactionBalance(Agent agent)throws AppException;
	
	public List getAgentTransactions(TransactionListForm talf,boolean isPass)throws AppException;
	
//	public int getTransactionBalanceRowByTransactionId(long transactionId)throws AppException;
//	public int getTransactionBalanceRowByAgentId(long agentId)throws AppException;
	
	public List getTransactionBalanceByAgent(TransactionListForm tlf)throws AppException;
	public List getAllTransactionBalanceByAgent(TransactionListForm tlf)throws AppException;
	public int getTransactionBalanceRow(TransactionListForm tlf)throws AppException ;

	public Object[] statToAgentTransaction(TransactionListForm tlf,int type) throws AppException;
	
}
