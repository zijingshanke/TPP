package com.fordays.fdpay.transaction.biz;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import com.fordays.fdpay.agent.dao.AgentDAO;
import com.fordays.fdpay.transaction.Expense;
import com.fordays.fdpay.transaction.ExpenseListForm;
import com.fordays.fdpay.transaction.OrderDetails;
import com.fordays.fdpay.transaction.Transaction;
import com.fordays.fdpay.transaction.dao.ExpenseDAO;
import com.fordays.fdpay.transaction.dao.OrderDetailsDAO;
import com.fordays.fdpay.transaction.dao.TransactionDAO;
import com.neza.base.NoUtil;
import com.neza.exception.AppException;
import com.neza.tool.DateUtil;

public class ExpenseBizImp implements ExpenseBiz {
	private ExpenseDAO expenseDAO;
	private TransactionTemplate transactionTemplate;	
	private NoUtil noUtil; 
	private AgentDAO agentDAO;
	private OrderDetailsDAO orderDetailsDAO;
	private TransactionDAO transactionDAO;
	public void setTransactionManager(
			HibernateTransactionManager transactionManager) {
		this.transactionTemplate = new TransactionTemplate(transactionManager);
	}
	
	//完成报销
	public long advanceAchieve(Expense expense) throws AppException {
		expenseDAO.update(expense);
		//生成订单
		OrderDetails orderDetails = new OrderDetails();
		orderDetails.setOrderNo(expense.getNo());
		orderDetails.setShopName("报销");
		orderDetails.setShopTotal(new Long(1));
		orderDetails.setOrderDetailsType(OrderDetails.ORDER_DETAILS_TYPE_1);
		orderDetails.setShopPrice(expense.getAmount());
		
		orderDetails.setDetailsContent("报销款项");
		orderDetails.setStatus(new Long(0));
		orderDetails.setOrderDetailsNo(noUtil.getOrderDetailsNo());
		
		
		
		//原来预支的金额   expense.getDebit().getAmount();
		//申请报销的金额   expense.getAmount();
		Transaction tempTransaction = new Transaction();
		if(expense.getDebit().getAmount().compareTo( expense.getAmount())==1){//原来预支金额大于报销金额
			tempTransaction.setToAgent(expense.getAgent()); 
			tempTransaction.setFromAgent(expense.getFromAgent());
			//改变agnet金额
			 agentDAO.addAmount(expense.getAgent(),expense.getDebit().getAmount().subtract(expense.getAmount()));
			 agentDAO.deleteAmount(expense.getFromAgent(),expense.getDebit().getAmount().subtract(expense.getAmount()));
			 tempTransaction.setAmount(expense.getDebit().getAmount().subtract(expense.getAmount()));
			 orderDetails.setPaymentPrice(expense.getDebit().getAmount().subtract(expense.getAmount()));
		}
		if(expense.getDebit().getAmount().compareTo( expense.getAmount())==-1){//原来预支金额小于报销金额
			tempTransaction.setToAgent(expense.getFromAgent()); 
			tempTransaction.setFromAgent(expense.getAgent());
			//改变agnet金额
			 agentDAO.addAmount(expense.getFromAgent(), expense.getAmount().subtract(expense.getDebit().getAmount()));
			 agentDAO.deleteAmount(expense.getAgent(), expense.getAmount().subtract(expense.getDebit().getAmount()));
			 tempTransaction.setAmount(expense.getAmount().subtract(expense.getDebit().getAmount()));
			 orderDetails.setPaymentPrice(expense.getAmount().subtract(expense.getDebit().getAmount()));
		}
		if(expense.getDebit().getAmount().compareTo( expense.getAmount())==0){//原来预支金额等于报销金额
			tempTransaction.setToAgent(expense.getAgent()); 
			tempTransaction.setFromAgent(expense.getFromAgent());
			tempTransaction.setAmount(new BigDecimal(0));
			orderDetails.setPaymentPrice(new BigDecimal(0));
		}
		
		orderDetailsDAO.save(orderDetails);
		tempTransaction.setNo(noUtil.getTransactionNo());
		tempTransaction.setType(Transaction.TYPE_191);// 报销
		tempTransaction.setStatus(Transaction.STATUS_3);// 报销成功
		tempTransaction.setAccountDate(expense.getCheck2Date());// 创建时间
		tempTransaction.setPayDate(expense.getCheck2Date());// 付款时间
		
		tempTransaction.setOrderDetails(orderDetails);
		tempTransaction.setMark("报销");
		transactionDAO.save(tempTransaction);
		return 0;
	}
	
	public ArrayList<ArrayList<Object>> listAllExpense(ExpenseListForm elf)throws AppException {
		List data =expenseDAO.getAllExpenseList(elf);
		ArrayList<ArrayList<Object>> list_context = new ArrayList<ArrayList<Object>>();
		ArrayList<Object> list_title = new ArrayList<Object>();
		list_title.add("#报销报表");
		list_context.add(list_title);
		list_title = new ArrayList<Object>();
		list_title.add("#下载时间：");
		list_context.add(list_title);
		list_title = new ArrayList<Object>();
		list_title.add(DateUtil.getDateString("yyyy年MM月dd日HH:mm:ss"));
		list_context.add(list_title);
		
		list_title =  new ArrayList<Object>();
		list_title.add("申请方（账号）");
		list_title.add("报销方（账号）");
		list_title.add("交易号");
		list_title.add("报销时间");
		list_title.add("报销状态");
		list_title.add("预支金额（元）");
		list_title.add("报销金额（元）");
		list_context.add(list_title);
		for (int i = 0; i < data.size(); i++)
		{
			Expense expense = (Expense) data.get(i);
			ArrayList<Object> list_context_item = new ArrayList<Object>();
			list_context_item.add(expense.getFromAgent().getName()+"("+expense.getFromAgent().getLoginName()+")");
			list_context_item.add(expense.getAgent().getName()+"("+expense.getAgent().getLoginName()+")");
			list_context_item.add(expense.getNo());
			if(expense.getCheck2Date()!=null)
				list_context_item.add(DateUtil.getDateString(expense.getCheck2Date(), "yyyy年MM月dd日HH:mm:ss"));
			else
				list_context_item.add("");
			
			list_context_item.add(expense.getStatusTo());
			list_context_item.add(expense.getDebit().getAmount());
			list_context_item.add(expense.getAmount());
			list_context.add(list_context_item);
		}
		return list_context;
	}

	public Expense getDebitByNo(String orderNo) throws AppException {
		return expenseDAO.getExpenseByNo(orderNo);
	}
	
	
	
	public Expense getExpenseById(long id) throws AppException {
		
		return expenseDAO.getExpenseById(id);
	}

	public List list(ExpenseListForm elf) throws AppException {
		return expenseDAO.list(elf);
	}

	public long updateInfo(Expense expense) throws AppException {
		return expenseDAO.update(expense);
	}

	public ExpenseDAO getExpenseDAO() {
		return expenseDAO;
	}

	public void setExpenseDAO(ExpenseDAO expenseDAO) {
		this.expenseDAO = expenseDAO;
	}

	public NoUtil getNoUtil() {
		return noUtil;
	}

	public void setNoUtil(NoUtil noUtil) {
		this.noUtil = noUtil;
	}

	public AgentDAO getAgentDAO() {
		return agentDAO;
	}

	public void setAgentDAO(AgentDAO agentDAO) {
		this.agentDAO = agentDAO;
	}

	public OrderDetailsDAO getOrderDetailsDAO() {
		return orderDetailsDAO;
	}

	public void setOrderDetailsDAO(OrderDetailsDAO orderDetailsDAO) {
		this.orderDetailsDAO = orderDetailsDAO;
	}

	public TransactionDAO getTransactionDAO() {
		return transactionDAO;
	}

	public void setTransactionDAO(TransactionDAO transactionDAO) {
		this.transactionDAO = transactionDAO;
	}

	
	
}

