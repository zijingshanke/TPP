package com.fordays.fdpay.transaction.biz;

import java.util.ArrayList;
import java.util.List;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import com.fordays.fdpay.agent.dao.AgentDAO;
import com.fordays.fdpay.transaction.Debit;
import com.fordays.fdpay.transaction.DebitListForm;
import com.fordays.fdpay.transaction.Expense;
import com.fordays.fdpay.transaction.OrderDetails;
import com.fordays.fdpay.transaction.Transaction;
import com.fordays.fdpay.transaction.dao.DebitDAO;
import com.fordays.fdpay.transaction.dao.OrderDetailsDAO;
import com.fordays.fdpay.transaction.dao.TransactionDAO;
import com.neza.base.NoUtil;
import com.neza.exception.AppException;
import com.neza.tool.DateUtil;

public class DebitBizImp implements DebitBiz {
	private TransactionTemplate transactionTemplate;	
	private DebitDAO debitDAO;
	private NoUtil noUtil; 
	private AgentDAO agentDAO;
	private OrderDetailsDAO orderDetailsDAO;
	private TransactionDAO transactionDAO;
	public void setTransactionManager(
			HibernateTransactionManager transactionManager) {
		this.transactionTemplate = new TransactionTemplate(transactionManager);
	}
	public List list(DebitListForm dlf) throws AppException {
		
		return debitDAO.list(dlf);
	}
	public DebitDAO getDebitDAO() {
		return debitDAO;
	}
	public void setDebitDAO(DebitDAO debitDAO) {
		this.debitDAO = debitDAO;
	}
	public Debit getDebitById(long id) throws AppException {
		return debitDAO.getDebitById(id);
	}
	public long updateInfo(Debit debit) throws AppException {
		return  debitDAO.update(debit);
	}
	public long advanceAchieve(Debit debit) throws AppException {
		
		debitDAO.update(debit);
		//生成订单
		OrderDetails orderDetails = new OrderDetails();
		orderDetails.setOrderNo(debit.getNo());
		orderDetails.setShopName("预支");
		orderDetails.setShopTotal(new Long(1));
		orderDetails.setOrderDetailsType(OrderDetails.ORDER_DETAILS_TYPE_1);
		orderDetails.setShopPrice(debit.getAmount());
		orderDetails.setPaymentPrice(debit.getAmount());
		orderDetails.setDetailsContent("预支款项");
		orderDetails.setStatus(new Long(0));
		orderDetails.setOrderDetailsNo(noUtil.getOrderDetailsNo());
		
		orderDetailsDAO.save(orderDetails);
		
		Transaction tempTransaction = new Transaction();
		tempTransaction.setNo(noUtil.getTransactionNo());
		tempTransaction.setAmount(debit.getAmount());
		tempTransaction.setType(Transaction.TYPE_109);// 预支
		tempTransaction.setStatus(Transaction.STATUS_3);// 预支成功
		tempTransaction.setAccountDate(debit.getCheck2Date());// 创建时间
		tempTransaction.setPayDate(debit.getCheck2Date());// 付款时间
		tempTransaction.setToAgent(debit.getAgent()); 
		tempTransaction.setFromAgent(debit.getFromAgent());
		tempTransaction.setOrderDetails(orderDetails);
		tempTransaction.setMark("预支");
		transactionDAO.save(tempTransaction);
		
		//改变agnet金额
		 agentDAO.addAmount(debit.getAgent(),debit.getAmount());
		 agentDAO.deleteAmount(debit.getFromAgent(), debit.getAmount());
		return 0;
	}
	
	public ArrayList<ArrayList<Object>> listAllDebit(DebitListForm dlf)throws AppException {
		List data =debitDAO.getAllDebitList(dlf);
		ArrayList<ArrayList<Object>> list_context = new ArrayList<ArrayList<Object>>();
		ArrayList<Object> list_title = new ArrayList<Object>();
		list_title.add("#预支报表");
		list_context.add(list_title);
		list_title = new ArrayList<Object>();
		list_title.add("#下载时间：");
		list_context.add(list_title);
		list_title = new ArrayList<Object>();
		list_title.add(DateUtil.getDateString("yyyy年MM月dd日HH:mm:ss"));
		list_context.add(list_title);
		
		list_title =  new ArrayList<Object>();
		list_title.add("申请方（账号）");
		list_title.add("预支方（账号）");
		list_title.add("交易号");
		list_title.add("预支时间");
		list_title.add("预支状态");
		list_title.add("预支金额（元）");
		list_context.add(list_title);
		for (int i = 0; i < data.size(); i++)
		{
			Debit debit = (Debit) data.get(i);
			ArrayList<Object> list_context_item = new ArrayList<Object>();
			list_context_item.add(debit.getAgent().getName()+"("+debit.getAgent().getLoginName()+")");
			list_context_item.add(debit.getFromAgent().getName()+"("+debit.getFromAgent().getLoginName()+")");
			list_context_item.add(debit.getNo());
			if(debit.getCheck2Date()!=null)
				list_context_item.add(DateUtil.getDateString(debit.getCheck2Date(), "yyyy年MM月dd日HH:mm:ss"));
			else
				list_context_item.add("");
			
			list_context_item.add(debit.getStatusTo());
			list_context_item.add(debit.getAmount());
			list_context.add(list_context_item);
		}
		return list_context;
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
	public Debit getDebitByNo(String no) throws AppException {
		return debitDAO.getDebitByNo(no);
	}
	public Expense getExpenseByDebitId(long id){
		return debitDAO.getExpenseByDebitId(id);
	}
}

