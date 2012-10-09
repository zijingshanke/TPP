package com.fordays.fdpay.transaction.biz;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import com.fordays.fdpay.agent.Agent;
import com.fordays.fdpay.agent.dao.AgentDAO;
import com.fordays.fdpay.transaction.OrderDetails;
import com.fordays.fdpay.transaction.TempTransaction;
import com.fordays.fdpay.transaction.TempTransactionBalance;
import com.fordays.fdpay.transaction.Transaction;
import com.fordays.fdpay.transaction.TransactionBalance;
import com.fordays.fdpay.transaction.TransactionListForm;
import com.fordays.fdpay.transaction.dao.TransactionDAO;
import com.neza.exception.AppException;
import com.neza.tool.DateUtil;


public class TransactionBizImp implements TransactionBiz {
	
	private TransactionDAO transactionDAO;
	private AgentDAO agentDAO;
	private TransactionTemplate transactionTemplate;

	public void setTransactionManager(
			HibernateTransactionManager transactionManager) {
		this.transactionTemplate = new TransactionTemplate(transactionManager);
	}



	public void setTransactionDAO(TransactionDAO transactionDAO)
		{
			this.transactionDAO = transactionDAO;
		}


	public Transaction getTransactionById(long id) throws AppException{
		return transactionDAO.getTransactionById(id);
	}
	

	

	public List getTransactions(TransactionListForm ulf) throws AppException{		
	
		return transactionDAO.list(ulf);
	}
	public ArrayList<ArrayList<Object>> notPagingList(TransactionListForm tlf) throws AppException{		
		
		List data =transactionDAO.notPagingList(tlf);
		ArrayList<ArrayList<Object>> list_context = new ArrayList<ArrayList<Object>>();
		ArrayList<Object> list_title = new ArrayList<Object>();
		list_title.add("#钱门交易记录查询");
		list_context.add(list_title);
		list_title = new ArrayList<Object>();
		list_title.add("#下载时间：");
		list_context.add(list_title);
		list_title = new ArrayList<Object>();
		list_title.add(DateUtil.getDateString("yyyy年MM月dd日HH:mm:ss"));
		list_context.add(list_title);
		list_title =  new ArrayList<Object>();
		list_title.add("交易创建时间");
		list_title.add("交易号");
		list_title.add("订单号");
		list_title.add("外部订单号");
		list_title.add("交易名称");
		list_title.add("出帐人");
		list_title.add("入账人");
		list_title.add("交易类型");
		list_title.add("交易状态");
		list_title.add("交易金额（元）");
		list_context.add(list_title);
		for (int i = 0; i < data.size(); i++)
		{
			Transaction transaction = (Transaction) data.get(i);
			ArrayList<Object> list_context_item = new ArrayList<Object>();
			list_context_item.add(DateUtil.getDateString(transaction.getAccountDate(), "yyyy年MM月dd日HH:mm:ss"));
			list_context_item.add(transaction.getNo());
			list_context_item.add(transaction.getOrderDetails().getOrderDetailsNo());
			list_context_item.add(transaction.getOrderDetails().getOrderNo());
			list_context_item.add(transaction.getOrderDetails().getShopName());
			list_context_item.add(transaction.getFromAgent().getName()+"      "+transaction.getFromAgent().getLoginName());
			list_context_item.add(transaction.getToAgent().getName()+"      "+transaction.getToAgent().getLoginName());
			list_context_item.add(transaction.getTypeCaption());
			if(transaction.getStatus()==10){
				list_context_item.add("买家已付款，等待卖家发货");
			}else
			if(transaction.getStatus()==7){
				list_context_item.add("卖家已发货，等待买家确认");
			}else{
				list_context_item.add(transaction.getTransactionStatus());
			}
			list_context_item.add(transaction.getAmount());
			list_context.add(list_context_item);
		}
		return list_context;
	}
	

	

	public void saveTransaction(Transaction transaction) throws AppException{
		transactionDAO.save(transaction);

	}


	public long updateInfo(Transaction transaction) throws AppException{
		transactionDAO.update(transaction);		

		return 0;
	}
	
	public void deleteTransaction(long id) throws AppException{
		transactionDAO.deleteById(id);
	}

	public Transaction queryTransactionById(long id)throws AppException
	{
		return transactionDAO.queryTransactionById(id);
	}



	public void addOrderDetails(OrderDetails orderDetails) throws AppException {
		transactionDAO.addOrderDetails(orderDetails);
	}



	public List getAgentTransactions(TransactionListForm talf, boolean isPass)
			throws AppException {
		
		return transactionDAO.getAgentTransactions(talf,isPass);
	}

	//获得当前最新的账户余额
	public TransactionBalance getLatestTransationBalance(Agent agent)throws AppException{
		return transactionDAO.getLatestTransationBalance(agent);
	}
	
	//新交易添加到TransactionBalance中
	public void statistAgentTransactionBalance(Agent agent) throws AppException{
		
		transactionDAO.addTransactionBalance(agent);
		agentDAO.synallowBalance(agent);
	}

	public ArrayList<ArrayList<Object>> getAllTransationList(TransactionListForm tlf ,Agent agent)
			throws AppException {
		
		List data =transactionDAO.getAllTransationList(tlf);
		ArrayList<ArrayList<Object>> list_context = new ArrayList<ArrayList<Object>>();
		ArrayList<Object> list_title = new ArrayList<Object>();
		list_title.add("#钱门商户账户明细查询");
		list_context.add(list_title);
		list_title = new ArrayList<Object>();
		list_title.add("#下载时间：");
		list_context.add(list_title);
		list_title = new ArrayList<Object>();
		list_title.add(DateUtil.getDateString("yyyy年MM月dd日HH:mm:ss"));
		list_context.add(list_title);
		list_title = new ArrayList<Object>();
		list_title.add("商户名   （账号）");
		list_context.add(list_title);
		list_title = new ArrayList<Object>();
		list_title.add(agent.getName() +"("+agent.getLoginName()+")");
		list_context.add(list_title);
		list_title =  new ArrayList<Object>();
		list_title.add("时间");
		list_title.add("商户订单号");
		list_title.add("业务流水号");
		list_title.add("类型");
		list_title.add("收入（元）");
		list_title.add("支出（元）");
		list_title.add("账户余额（元）");
		list_title.add("备注");
		list_context.add(list_title);
		for (int i = 0; i < data.size(); i++)
		{
			TempTransaction transaction = (TempTransaction) data.get(i);
			ArrayList<Object> list_context_item = new ArrayList<Object>();
			list_context_item.add(DateUtil.getDateString(transaction.getAccountDate(), "yyyy年MM月dd日HH:mm:ss"));
			list_context_item.add(transaction.getOrderDetails().getOrderDetailsNo());
			list_context_item.add(transaction.getOrderDetails().getOrderNo());
			list_context_item.add(transaction.getTypeCatption());
			if(transaction.getToAgent().getId()==tlf.getAgent().getId()){
				list_context_item.add(transaction.getAmount());
			}else{
				list_context_item.add("");
			}
			if(transaction.getFromAgent().getId()==tlf.getAgent().getId()){
				if(Float.parseFloat(transaction.getAmount().toString())>=0)
				list_context_item.add("-"+transaction.getAmount());
			}else{
				list_context_item.add("");
			}
			list_context_item.add(transaction.getCurrentBalance());
			list_context_item.add(transaction.getMark());
			list_context.add(list_context_item);
		}
		return list_context;
	}



	public AgentDAO getAgentDAO() {
		return agentDAO;
	}



	public void setAgentDAO(AgentDAO agentDAO) {
		this.agentDAO = agentDAO;
	}



	public List getTransactionBalanceByAgent(TransactionListForm tlf)
			throws AppException {
		return transactionDAO.getTransactionBalanceByAgent(tlf);
	}



	public ArrayList<ArrayList<Object>> getAllTransationBalanceByAgent(
			TransactionListForm tlf, Agent agent) throws AppException {
	
	List data =transactionDAO.getAllTransactionBalanceByAgent(tlf);
	ArrayList<ArrayList<Object>> list_context = new ArrayList<ArrayList<Object>>();
	ArrayList<Object> list_title = new ArrayList<Object>();
	if(tlf.getBalanceType()==0){
	list_title.add("#钱门商户账户明细查询2");
	}
	else if(tlf.getBalanceType()==1){
		list_title.add("#钱门商户账户可用余额查询");	
	}
	else if(tlf.getBalanceType()==2){
		list_title.add("#钱门商户账户冻结余额查询");
	}
	else if(tlf.getBalanceType()==3){
		list_title.add("#钱门商户账户信用余额查询");
	}
	list_context.add(list_title);
	list_title = new ArrayList<Object>();
	list_title.add("#下载时间：");
	list_context.add(list_title);
	list_title = new ArrayList<Object>();
	list_title.add(DateUtil.getDateString("yyyy年MM月dd日HH:mm:ss"));
	list_context.add(list_title);
	list_title = new ArrayList<Object>();
	list_title.add("商户名   （账号）");
	list_context.add(list_title);
	list_title = new ArrayList<Object>();
	list_title.add(agent.getName() +"("+agent.getLoginName()+")");
	list_context.add(list_title);
	list_title =  new ArrayList<Object>();
	list_title.add("商户订单号");
	list_title.add("业务流水号");
	list_title.add("交易时间");
	list_title.add("钱门交易号");
	list_title.add("交易对方");
	list_title.add("收入(元)");
	list_title.add("支出(元)");
	if(tlf.getBalanceType()==0||tlf.getBalanceType()==1){
	list_title.add("账户可用余额(元)");
	}
	if(tlf.getBalanceType()==0||tlf.getBalanceType()==2){
	list_title.add("账户冻结余额(元)");
	}
	if(tlf.getBalanceType()==0||tlf.getBalanceType()==3){
	list_title.add("账户信用余额(元)");
	}
	if(tlf.getBalanceType()==0){
	list_title.add("账户总余额(元)");
	}
	list_title.add("交易状态");
	list_title.add("商品名称");
	list_context.add(list_title);
	for (int i = 0; i < data.size(); i++)
	{
		TempTransactionBalance tb = (TempTransactionBalance) data.get(i);
		
		ArrayList<Object> list_context_item = new ArrayList<Object>();
		list_context_item.add(tb.getOrderDetailsNo());// 商户订单号
		list_context_item.add(tb.getOrderNo());// 业务流水号
		list_context_item.add(DateUtil.getDateString(tb.getTransactionDate(),
		    "yyyy-MM-dd   HH:mm:ss"));// 交易时间
		list_context_item.add(tb.getOrderDetailsNo());// 钱门交易号
		if (tb.getFromAgentId() == tlf.getAgent().getId())
		{
			list_context_item.add(tb.getToAgentEmail() + "    "
			    + tb.getToAgentName());// 交易对方
		}
		else
		{
			list_context_item.add(tb.getFromAgentEmail() + "    "
			    + tb.getFromAgentName());// 交易对方
		}

		if(tb.getToAgentId()==tb.getFromAgentId()){
			list_context_item.add(tb.getAmount());
			list_context_item.add("-" + tb.getAmount());
		}else if (tb.getToAgentId() == tlf.getAgent().getId())
		{
			list_context_item.add(tb.getAmount());
			list_context_item.add("");
		}else if (tb.getFromAgentId() == tlf.getAgent().getId())
		{
			list_context_item.add("");
			if (tb.getAmount().compareTo(new BigDecimal("0")) > 0)
				list_context_item.add("-" + tb.getAmount());
			else
				list_context_item.add("");
		}
		
		BigDecimal balance=new BigDecimal(0);
		BigDecimal notallowBalance=new BigDecimal(0);
		BigDecimal creditBalance=new BigDecimal(0);
		if(tb.getBalance()!=null){
			balance=tb.getBalance();
		}
		if(tb.getNotallowBalance()!=null){
			notallowBalance=tb.getNotallowBalance();
		}
		if(tb.getCreditBalance()!=null){
			creditBalance=tb.getCreditBalance();
		}
		if(tlf.getBalanceType()==0||tlf.getBalanceType()==1){
		list_context_item.add(balance);// 账户可用余额
		}
		if(tlf.getBalanceType()==0||tlf.getBalanceType()==2){
		list_context_item.add(notallowBalance);// 账户不可用余额
		}
		if(tlf.getBalanceType()==0||tlf.getBalanceType()==3){
		list_context_item.add(creditBalance);// 账户信用余额
		}
		if(tlf.getBalanceType()==0){
		list_context_item.add(tb.getConteBalance());// 账户总余额
		}
		list_context_item.add(tb.getTypeCaption());// 交易状态
		list_context_item.add(tb.getShopName());// 商品名称
		list_context.add(list_context_item);
	}

	Object[] toAgent = transactionDAO.statToAgentTransaction(tlf,0);
	Object[] fromAgent = transactionDAO.statToAgentTransaction(tlf,1);
	if (fromAgent[0] != null)
		fromAgent[0] = fromAgent[0];
	else
		fromAgent[0] = 0;
	if (fromAgent[1] != null)
		fromAgent[1] = fromAgent[1];
	else
		fromAgent[1] = 0;
	if (toAgent[0] != null)
		toAgent[0] = toAgent[0];
	else
		toAgent[0] = 0;
	if (toAgent[1] != null)
		toAgent[1] = toAgent[1];
	else
		toAgent[1] = 0;
	list_title = new ArrayList<Object>();
	list_title.add("#支出合计:共支出" + fromAgent[1] + "笔  支出总额:" + fromAgent[0]);
	list_context.add(list_title);
	list_title = new ArrayList<Object>();
	list_title.add("#收入合计:共收入" + toAgent[1] + "笔  收入总额:" + toAgent[0]);
	list_context.add(list_title);
	return list_context;

}



	public int getTransactionBalanceRow(TransactionListForm tlf)
			throws AppException {
		return transactionDAO.getTransactionBalanceRow(tlf);
	}
}
