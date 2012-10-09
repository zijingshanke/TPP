package com.fordays.fdpay.transaction.dao;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import com.fordays.fdpay.agent.Agent;
import com.fordays.fdpay.transaction.OrderDetails;
import com.fordays.fdpay.transaction.Transaction;
import com.fordays.fdpay.transaction.TransactionBalance;
import com.fordays.fdpay.transaction.TransactionListForm;
import com.neza.base.BaseDAOSupport;
import com.neza.base.Constant;
import com.neza.base.Hql;
import com.neza.exception.AppException;

public class TransactionDAOImp extends BaseDAOSupport implements TransactionDAO {
	private TransactionTemplate transactionTemplate;

	public void setTransactionManager(
			PlatformTransactionManager transactionManager) {
		this.transactionTemplate = new TransactionTemplate(transactionManager);
	}

	public long save(Transaction transaction) throws AppException {
		this.getHibernateTemplate().saveOrUpdate(transaction);
		return transaction.getId();
	}

	public OrderDetails saveTransaction(List<Transaction> listTransaction,OrderDetails orderDetails, List<Agent> listAgent)throws AppException {
		this.getHibernateTemplate().saveOrUpdate(orderDetails);
		System.out.println("finish save order detail>>>");
		for (Transaction transaction : listTransaction) {
			transaction.setOrderDetails(orderDetails);
			save(transaction);
			System.out.println("transaction id>>>>>"+transaction.getId());
		}
		for (Agent agent : listAgent) {
			this.getHibernateTemplate().merge(agent);
			System.out.println("agent id>>>>>"+agent.getId());
		}
		return orderDetails;
	}

	public long merge(Transaction transaction) throws AppException {
		this.getHibernateTemplate().merge(transaction);
		return transaction.getId();
	}

	public long update(Transaction transaction) throws AppException {
		if (transaction.getId() > 0)
			return save(transaction);
		else
			throw new IllegalArgumentException("id isn't a valid argument.");
	}

	public void deleteById(long id) throws AppException {
		if (id > 0) {
			Transaction transaction = (Transaction) this.getHibernateTemplate()
					.get(Transaction.class, new Long(id));
			this.getHibernateTemplate().delete(transaction);

		}
	}

	public Transaction getTransactionById(long id) throws AppException {

		Transaction transaction;
		if (id > 0) {
			transaction = (Transaction) this.getHibernateTemplate().get(
					Transaction.class, new Long(id));
			return transaction;

		} else
			return new Transaction();
	}

	public Transaction queryTransactionById(long id) throws AppException {

		if (id > 0) {
			Transaction transaction = (Transaction) this.getQuery(
					"from Transaction where id=" + id).uniqueResult();
			if (transaction != null) {
				return transaction;
			} else {
				return new Transaction();
			}

		} else
			return new Transaction();
	}

	
	public Hql listHQL (TransactionListForm tlf){
		Hql hql=new Hql();
		hql.add("from Transaction tat where 1=1");
			hql.add("  and tat.no like ?");
			hql.addParamter("%"+tlf.getNo().toString().trim()+"%");
			
			if(tlf.getOrderNo()!=null&&!"".equals(tlf.getOrderNo())){
			hql.add(" and tat.orderDetails.orderNo like ?");
			hql.addParamter("%"+tlf.getOrderNo().trim()+"%");
			}
			hql.add(" and tat.orderDetails.orderDetailsNo like ?");
			hql.addParamter("%"+tlf.getOrderDetailsNo().trim()+"%");
			
			hql.add(" and ( tat.fromAgent.name like ?  or tat.toAgent.name like ? or LOWER(tat.fromAgent.loginName) like LOWER(?) or LOWER(tat.toAgent.loginName) like LOWER(?))");
			hql.addParamter("%"+tlf.getAgentName().trim()+"%");
			hql.addParamter("%"+tlf.getAgentName().trim()+"%");
			hql.addParamter("%"+tlf.getAgentName().trim()+"%");
			hql.addParamter("%"+tlf.getAgentName().trim()+"%");
			
		if(tlf.getOrderDetailsType()!=0&&tlf.getOrderDetailsType()!=null){
				hql.add(" and tat.orderDetails.orderDetailsType= ?");
				hql.addParamter(tlf.getOrderDetailsType());
			}
			
			
			if("".equals(tlf.getShopName())==false){
				hql.add(" and tat.orderDetails.shopName like ?");
				hql.addParamter("%"+tlf.getShopName().trim()+"%");
			}
			
			String beginDate = tlf.getBeginDate();
			String endDate = tlf.getEndDate();
			
			if ("".equals(beginDate)==false && "".equals(endDate)==true) {
				hql.add(" and to_char(tat.accountDate,'yyyy-mm-dd hh24:mi:ss') > ?");
				hql.addParamter(beginDate);
			}
			if ("".equals(beginDate)==true && "".equals(endDate)==false) {
				hql.add(" and to_char(tat.accountDate,'yyyy-mm-dd hh24:mi:ss') < ?");
				hql.addParamter(endDate);
			}
			if ("".equals(beginDate)==false && "".equals(endDate)==false) {
				hql.add(" and  to_char(tat.accountDate,'yyyy-mm-dd hh24:mi:ss')  between ? and ? ");
				hql.addParamter(beginDate);
				hql.addParamter(endDate);
			}
	
			if(tlf.getMode()==3){
				hql.add(" and tat.status in (?,?)");
				hql.addParamter(Transaction.STATUS_3);
				hql.addParamter(Transaction.STATUS_11);
			}
			if(tlf.getMode()==1){
				hql.add(" and tat.status in (?,?,?,?)");
				hql.addParamter(Transaction.STATUS_1);
				hql.addParamter(Transaction.STATUS_2);
				hql.addParamter(Transaction.STATUS_6);
				hql.addParamter(Transaction.STATUS_10);
			}
			if(tlf.getMode()==4){
				hql.add(" and (tat.status=? or tat.status=?) and refundsStatus is null");
				hql.addParamter(Transaction.STATUS_4);
				hql.addParamter(Transaction.STATUS_5);
			}
			if(tlf.getMode()==11){
				hql.add(" and (tat.status=? or tat.status=?) and refundsStatus is not null");
				hql.addParamter(Transaction.STATUS_10);
				hql.addParamter(Transaction.STATUS_11);
			}
			hql.add("  order by tat.accountDate desc,tat.id desc");
			System.out.println("hql===========" + hql);
			
			return hql;
			
	}
	
	
	public List list(TransactionListForm tlf) {
		Hql hql=listHQL(tlf);
		return this.list(hql, tlf);
	}

	public List notPagingList(TransactionListForm tlf) throws AppException {
		Hql hql=listHQL(tlf);
		Query query=this.getQuery(hql);
		return query.list();
	}
	public void saveTransaction(Transaction transaction,
				Agent agent) throws AppException {
		/*if(orderDetails!=null){
			this.getHibernateTemplate().saveOrUpdate(orderDetails);
		}*/
		if(transaction!=null){
			save(transaction);
		}
		if(agent!=null){
			this.getHibernateTemplate().merge(agent);
		}
	}
	public double getTotalRefundBalance(long orderId,long avouchAccount) throws AppException{
		double refundBalance=0l;
		Hql hql = new Hql("select SUM(t.amount) from Transaction t where t.orderDetails.id=? and t.toAgent.id=? and status=0");
		hql.addParamter(orderId);
		hql.addParamter(avouchAccount);
		Query query = this.getQuery(hql);
		if (query!=null&& query.list()!=null&&query.list().size() > 0)
		{
			if(query.list().get(0)!=null){
				refundBalance=((BigDecimal) query.list().get(0)).doubleValue();
			}
		}
		return refundBalance;
	}

	public void addOrderDetails(OrderDetails orderDetails) throws AppException {
		this.getHibernateTemplate().saveOrUpdate(orderDetails);
	}

	public List getAgentTransactions(TransactionListForm transactionListForm,boolean isPass)
    throws AppException
{
	Hql hql0 = new Hql();
	Hql hql = new Hql();
	Hql[] hqls=parseHql(transactionListForm,isPass);
	hql0=hqls[0];
	hql=hqls[1];

	int totalRowCount = this.getTotalCount(hql);
	transactionListForm.setTotalRowCount(totalRowCount);
	Query query = this.getQuery(hql0);
	query.setFirstResult(transactionListForm.getStart());
	query.setMaxResults(transactionListForm.getPerPageNum());
	transactionListForm.setHql(hql.toString());
	List list;
	if (query != null)
	{
		list = query.list();
	}
	else
	{
		list = new ArrayList();
	}
	return list;
}
	
	private Hql[] parseHql(TransactionListForm transactionListForm,boolean isPass)
    throws AppException
{
	Hql hql0 = new Hql();

	hql0
	    .add(" select new com.fordays.fdpay.transaction.TempTransaction(orderDetails,t0.id,t0.accountDate,t0.no,t0.fromAgent,t0.toAgent,t0.amount,");
	hql0
	    .add(" (select nvl(sum(amount),0) from Transaction t1 where toAgent.id = ?  and t1.status in(?,?) and t1.id<=t0.id) , ");
	hql0
	    .add(" (select nvl(sum(amount),0) from Transaction t2 where fromAgent.id =?  and t2.status in(?,?) and t2.id<=t0.id),");
	hql0.add(" t0.type,");
	hql0.add(" t0.mark,");
	hql0.add(" t0.status");
	hql0.add(" ) ");
	Hql hql = new Hql();
	hql.add(" from Transaction as t0 ");
	hql
	    .add(" where (t0.fromAgent.id = ? or t0.toAgent.id = ?)  and t0.status in(?,?)   ");
	String minDate ="".equals( transactionListForm.getBeginDate())? null
	    : transactionListForm.getBeginDate();
	String maxDate = "".equals(transactionListForm.getEndDate())? null
	    : transactionListForm.getEndDate();

	if (minDate != null && maxDate == null)
	{
		hql.add(" and to_char(t0.accountDate,'yyyy-mm-dd hh24:mi:ss') >= ?");

	}
	if (maxDate != null && minDate == null)
	{
		hql.add(" and to_char(t0.accountDate,'yyyy-mm-dd hh24:mi:ss')<= ?");

	}
	if (minDate != null && !minDate.equals("") && maxDate != null && !maxDate.equals(""))
	{
		// hql.add(" and (to_char(t0.accountDate,'yyyy-MM-dd') between ? and ?)
		// ");
		hql.add(" and (to_char(t0.accountDate,'yyyy-mm-dd hh24:mi:ss') between ? and ?) ");
		//			 
	}
	
	if(!isPass)
	hql.add(" and t0.type<>"+Transaction.TYPE_70);
	hql.add("  order by t0.accountDate desc,t0.id desc ");
	hql0.add(hql.toString());

	hql0.addParamter(transactionListForm.getAgent().getId());
	hql0.addParamter(Transaction.STATUS_3);
	hql0.addParamter(Transaction.STATUS_11);
	hql0.addParamter(transactionListForm.getAgent().getId());
	hql0.addParamter(Transaction.STATUS_3);
	hql0.addParamter(Transaction.STATUS_11);
	hql0.addParamter(transactionListForm.getAgent().getId());
	hql0.addParamter(transactionListForm.getAgent().getId());
	hql0.addParamter(Transaction.STATUS_3);
	hql0.addParamter(Transaction.STATUS_11);

	hql.addParamter(transactionListForm.getAgent().getId());
	hql.addParamter(transactionListForm.getAgent().getId());
	hql.addParamter(Transaction.STATUS_3);
	hql.addParamter(Transaction.STATUS_11);


	if (minDate != null && maxDate == null)
	{
		hql0.addParamter(minDate);
		hql.addParamter(minDate);
	}
	if (maxDate != null && minDate == null)
	{
		hql0.addParamter(maxDate);
		hql.addParamter(maxDate);
	}
	if (minDate != null && !minDate.equals("") && maxDate != null && !maxDate.equals(""))
	{
		
		 hql0.addParamter(minDate); hql0.addParamter(maxDate);
		 hql.addParamter(minDate); hql.addParamter(maxDate);
		 
//		hql0.addParamter(DateUtil.getDate(minDate + " 00:00:00",
//		    "yyyy-MM-dd HH:mm:ss"));
//		hql0.addParamter(DateUtil.getDate(maxDate + " 23:59:59",
//		    "yyyy-MM-dd HH:mm:ss"));
//		hql.addParamter(DateUtil.getDate(minDate + " 00:00:00",
//		    "yyyy-MM-dd HH:mm:ss"));
//		hql.addParamter(DateUtil.getDate(maxDate + " 23:59:59",
//		    "yyyy-MM-dd HH:mm:ss"));
	}
	Hql[] hqls=new Hql[2];
    hqls[0]=hql0;
    hqls[1]=hql;
	return hqls;
	
}
	//下载列表
	public List getAllTransationList(TransactionListForm tlf) throws AppException {
		Hql hql0 = new Hql();
		Hql hql = new Hql();
		boolean isPass=true;
		Hql[] hqls=parseHql(tlf,isPass);
		hql0=hqls[0];
		hql=hqls[1];

		int totalRowCount = this.getTotalCount(hql);
		tlf.setTotalRowCount(totalRowCount);
		Query query = this.getQuery(hql0);
		List list;
		if (query != null)
		{
			list = query.list();
		}
		else
		{
			list = new ArrayList();
		}
		return list;
	}

	public List getAllTransationBalance() throws AppException {
		Hql hql=new Hql();
		hql.add("from TransactionBalance tb  order by tb.agentId desc");
		Query query=this.getQuery(hql);
		return query.list();
	}

	public long _addTransactionBalance(Agent agent)throws AppException {
		try
		{
			CallableStatement call = this.getSessionFactory().getCurrentSession()
			    .connection().prepareCall("{Call A_A_T_B(?)}");		 
			call.setLong("agentId", agent.getId());
 			call.executeUpdate();
 			
 			return 1;
		}
		catch (Exception ex)
		{
			System.out.println("execute procedure fails:" + ex.getMessage());
			return 0;
		}
 
	}

	public long addTransactionBalance(Agent agent) throws AppException
	{
		try
		{
			String sql = "insert into transaction_balance(id,transaction_id,balance,notallow_balance,"
			    + " credit_balance,agent_id,status,transaction_date)select "
			    + "seq_transactionbalance.nextval, t.id,cal_curr_bal_by_trans_id("
			    + agent.getId() + ", t.id),cal_notallow_bal_by_trans_id("
			    + agent.getId() + ", t.id),cal_credit_bal_by_trans_id("
			    + agent.getId() + ", t.id)," + agent.getId() + ","
			    + TransactionBalance.STATUS_0
			    + ",t.account_date from Transaction t where (t.to_agent_id = "
			    + agent.getId() + " or t.from_agent_id = " + agent.getId()
			    + ")  and t.status in(3,11)";
			if (this.getTransactionBalanceRowByAgentId(agent.getId()) <= 0)
			{
				// 不存在此agent的交易
				sql += " and not  exists(select * from transaction_balance where agent_id = "
				    + agent.getId() + ") ";
			}
			else
			{
				// 存在此agent 添加新交易
				sql += " and not exists(select * from transaction_balance tb where agent_id = "
				    + agent.getId() + " and tb.transaction_id >= t.id)";
			}
			
			
			int i = 0;
			synchronized (new Object())
			{
				System.out.println("---------" + sql);
				i = this.getSession().createSQLQuery(sql).executeUpdate();
			}
			return i;
		}
		catch (Exception e)
		{
			System.out.println("my message     +++++++++" + e.getMessage());
		}
		return 0;
	}
	public TransactionBalance getLatestTransationBalance(Agent agent)
			throws AppException {
		
		Hql hql=new Hql();
		hql.add("from TransactionBalance tb where tb.agentId=? order by transactionId desc");
		hql.addParamter(agent.getId());
		Query query=this.getQuery(hql);
		if(query.list().size()>0){
			TransactionBalance statistBalance=(TransactionBalance)query.list().get(0);
			return statistBalance;
		}
		return null;
	}

	public List getAllTransationListByTransationBalance() throws AppException {
		Hql hql=new Hql();
		hql.add("from Transaction tat where tat.status in (?,?)   order by tat.id desc");
		hql.addParamter(Transaction.STATUS_3);
		hql.addParamter(Transaction.STATUS_11);
		Query query =this.getQuery(hql);
		return query.list();
	}

	public int getTransactionBalanceRowByAgentId(long agentId)
	throws AppException {
		Hql hql=new Hql();
		hql.add("from TransactionBalance tat where  tat.agentId=? ");
		hql.addParamter(agentId);
		Query query =this.getQuery(hql);
		return query.list().size();
	}

	
	
	public List getTransactionBalanceByAgent(TransactionListForm tlf)
	    throws AppException
	{
		Hql hql = this.parseHql(tlf,0)[0];
		return this.list(hql, tlf);
	}
	
	public List getAllTransactionBalanceByAgent(TransactionListForm tlf)
	    throws AppException
	{
		Hql hql = this.parseHql(tlf,0)[0];
		Query query = this.getQuery(hql);
		return query.list();
	}

	public Hql[] parseHql(TransactionListForm tlf,int type) //balanceType--0:
	{
		/*
		 * this.id = id; this.transactionId = transactionId; this.transactionType =
		 * transactionType; this.transactionStatus = transactionStatus; this.balance
		 * = balance; this.creditBalance = creditBalance; this.agentId = agentId;
		 * this.status = status; this.transactionDate = transactionDate;
		 * OrderDetailsId = orderDetailsId; OrderDetailsNo = orderDetailsNo;
		 * this.getOrderNo = getOrderNo; this.amount = amount; this.fromAgentId =
		 * fromAgentId; this.toAgentId = toAgentId; this.fromAgentName =
		 * fromAgentName; this.fromAgentEmail = fromAgentEmail; this.toAgentName =
		 * toAgentName; this.toAgentEmail = toAgentEmail; this.shopName = shopName;
		 */

		Hql hql0 = new Hql();
		Hql hql1 = new Hql();
		Hql hql2 = new Hql();
		
		hql0.add("select new com.fordays.fdpay.transaction.TempTransactionBalance (tb.id,tb.transactionId,transaction.type,transaction.status,tb.balance,");
		hql0.add("tb.notallowBalance,tb.creditBalance,tb.agentId,tb.status,tb.transactionDate,transaction.orderDetails.id,transaction.orderDetails.orderDetailsNo,transaction.orderDetails.orderNo,transaction.amount,");
		hql0.add("transaction.fromAgent.id,transaction.toAgent.id,transaction.fromAgent.name,transaction.fromAgent.email,transaction.toAgent.name,transaction.toAgent.email,");
		hql0.add("transaction.orderDetails.shopName,transaction.mark)");
		hql0.add(" from TransactionBalance tb,Transaction transaction ");
		hql0.add(" where tb.agentId=? and transaction.id=tb.transactionId and transaction.status in (3,11)");
		// sc  下载时报表 添加判断 不显示自己对自己交易  and transaction.fromAgent.id <> transaction.toAgent.id
		//if(tlf.getCheck().equals("download")){
	    //hql0.add("and transaction.fromAgent.id <> transaction.toAgent.id");
		//}
	hql0.addParamter(tlf.getAgent().getId());
		
		hql2.add("select count(*) from TransactionBalance tb ,Transaction transaction  where  tb.agentId=?  and transaction.id=tb.transactionId and transaction.status in (3,11)");
		hql2.addParamter(tlf.getAgent().getId());
		
		
		hql1.add("select sum(transaction.amount), count(transaction.amount) ") ;
		hql1.add(" from TransactionBalance tb,Transaction transaction ");
		hql1.add(" where tb.agentId=? and transaction.id=tb.transactionId and transaction.status in (3,11)");
		hql1.addParamter(tlf.getAgent().getId());
		String beginDate = tlf.getBeginDate();
		String endDate = tlf.getEndDate();
		if ("".equals(beginDate) == false && "".equals(endDate) == true)
		{
			hql0.add(" and to_char(tb.transactionDate,'yyyy-mm-dd hh24:mi:ss') > ?");
			hql0.addParamter(beginDate);
			
			hql1.add(" and to_char(tb.transactionDate,'yyyy-mm-dd hh24:mi:ss') > ?");
			hql1.addParamter(beginDate);
			
			hql2.add(" and to_char(tb.transactionDate,'yyyy-mm-dd hh24:mi:ss') > ?");
			hql2.addParamter(beginDate);
			

		}
		if ("".equals(beginDate) == true && "".equals(endDate) == false)
		{
			hql0.add(" and to_char(tb.transactionDate,'yyyy-mm-dd hh24:mi:ss') < ?");
			hql0.addParamter(endDate);
			
			hql1.add(" and to_char(tb.transactionDate,'yyyy-mm-dd hh24:mi:ss') < ?");
			hql1.addParamter(endDate);
			
			hql2.add(" and to_char(tb.transactionDate,'yyyy-mm-dd hh24:mi:ss') < ?");
			hql2.addParamter(endDate);
		}
		if ("".equals(beginDate) == false && "".equals(endDate) == false)
		{
			hql0.add(" and  to_char(tb.transactionDate,'yyyy-mm-dd hh24:mi:ss')  between ? and ? ");
			hql0.addParamter(beginDate);
			hql0.addParamter(endDate);
			
			hql1.add(" and  to_char(tb.transactionDate,'yyyy-mm-dd hh24:mi:ss')  between ? and ? ");
			hql1.addParamter(beginDate);
			hql1.addParamter(endDate);
			
			hql2.add(" and  to_char(tb.transactionDate,'yyyy-mm-dd hh24:mi:ss')  between ? and ? ");
			hql2.addParamter(beginDate);
			hql2.addParamter(endDate);
		}
 
		int balanceType=tlf.getBalanceType();
		
		if(balanceType==1)//可以余额交易
		{
			hql0.add(" and  (transaction.orderDetails.orderDetailsType=1" +
			" or (transaction.orderDetails.orderDetailsType=3 and transaction.fromAgent.id=? and transaction.toAgent.id<>?)");
			hql0.addParamter(tlf.getAgent().getId());
			hql0.addParamter(tlf.getAgent().getId());
			hql0.add(" or  (transaction.orderDetails.orderDetailsType=4 and transaction.fromAgent.id<>? and transaction.toAgent.id=?)");
			hql0.addParamter(tlf.getAgent().getId());
			hql0.addParamter(tlf.getAgent().getId());
			hql0.add(" or  (transaction.orderDetails.orderDetailsType=5 and transaction.fromAgent.id=? and transaction.toAgent.id<>?)");
			hql0.addParamter(tlf.getAgent().getId());
			hql0.addParamter(tlf.getAgent().getId());
			hql0.add(" or  (transaction.orderDetails.orderDetailsType=5 and transaction.fromAgent.id=? and transaction.toAgent.id=?)");
			hql0.addParamter(tlf.getAgent().getId());
			hql0.addParamter(tlf.getAgent().getId());
			hql0.add(" or  (transaction.orderDetails.orderDetailsType=6 and transaction.fromAgent.id<>? and transaction.toAgent.id=?))");
			hql0.addParamter(tlf.getAgent().getId());
			hql0.addParamter(tlf.getAgent().getId());
			
			
			hql1.add(" and  (transaction.orderDetails.orderDetailsType=1" +
			" or (transaction.orderDetails.orderDetailsType=3 and transaction.fromAgent.id=? and transaction.toAgent.id<>?)");
			hql1.addParamter(tlf.getAgent().getId());
			hql1.addParamter(tlf.getAgent().getId());
			hql1.add(" or  (transaction.orderDetails.orderDetailsType=4 and transaction.fromAgent.id<>? and transaction.toAgent.id=?)");
			hql1.addParamter(tlf.getAgent().getId());
			hql1.addParamter(tlf.getAgent().getId());
			hql1.add(" or  (transaction.orderDetails.orderDetailsType=5 and transaction.fromAgent.id=? and transaction.toAgent.id<>?)");
			hql1.addParamter(tlf.getAgent().getId());
			hql1.addParamter(tlf.getAgent().getId());
			hql1.add(" or  (transaction.orderDetails.orderDetailsType=5 and transaction.fromAgent.id=? and transaction.toAgent.id=?)");
			hql1.addParamter(tlf.getAgent().getId());
			hql1.addParamter(tlf.getAgent().getId());
			hql1.add(" or  (transaction.orderDetails.orderDetailsType=6 and transaction.fromAgent.id<>? and transaction.toAgent.id=?))");
			hql1.addParamter(tlf.getAgent().getId());
			hql1.addParamter(tlf.getAgent().getId());
			
			hql2.add(" and  (transaction.orderDetails.orderDetailsType=1" +
			" or (transaction.orderDetails.orderDetailsType=3 and transaction.fromAgent.id=? and transaction.toAgent.id<>?)");
			hql2.addParamter(tlf.getAgent().getId());
			hql2.addParamter(tlf.getAgent().getId());
			hql2.add(" or  (transaction.orderDetails.orderDetailsType=4 and transaction.fromAgent.id<>? and transaction.toAgent.id=?)");
			hql2.addParamter(tlf.getAgent().getId());
			hql2.addParamter(tlf.getAgent().getId());
			hql2.add(" or  (transaction.orderDetails.orderDetailsType=5 and transaction.fromAgent.id=? and transaction.toAgent.id<>?)");
			hql2.addParamter(tlf.getAgent().getId());
			hql2.addParamter(tlf.getAgent().getId());
			hql2.add(" or  (transaction.orderDetails.orderDetailsType=5 and transaction.fromAgent.id=? and transaction.toAgent.id=?)");
			hql2.addParamter(tlf.getAgent().getId());
			hql2.addParamter(tlf.getAgent().getId());
			hql2.add(" or  (transaction.orderDetails.orderDetailsType=6 and transaction.fromAgent.id<>? and transaction.toAgent.id=?))");
			hql2.addParamter(tlf.getAgent().getId());
			hql2.addParamter(tlf.getAgent().getId());
		
			
		}
		else if(balanceType==2)//冻结余额交易
		{
			
			hql0.add(" and  ((transaction.orderDetails.orderDetailsType=5 and transaction.fromAgent.id<>? and transaction.toAgent.id=?)");
			hql0.addParamter(tlf.getAgent().getId());
			hql0.addParamter(tlf.getAgent().getId());
			hql0.add(" or  (transaction.orderDetails.orderDetailsType=6 and transaction.fromAgent.id=? and transaction.toAgent.id<>?)");
			hql0.addParamter(tlf.getAgent().getId());
			hql0.addParamter(tlf.getAgent().getId());
			hql0.add(" or  (transaction.orderDetails.orderDetailsType=6 and transaction.fromAgent.id=? and transaction.toAgent.id=?))");
			hql0.addParamter(tlf.getAgent().getId());
			hql0.addParamter(tlf.getAgent().getId());
			
			hql1.add(" and  ((transaction.orderDetails.orderDetailsType=5 and transaction.fromAgent.id<>? and transaction.toAgent.id=?)");
			hql1.addParamter(tlf.getAgent().getId());
			hql1.addParamter(tlf.getAgent().getId());
			hql1.add(" or  (transaction.orderDetails.orderDetailsType=6 and transaction.fromAgent.id=? and transaction.toAgent.id<>?)");
			hql1.addParamter(tlf.getAgent().getId());
			hql1.addParamter(tlf.getAgent().getId());
			hql1.add(" or  (transaction.orderDetails.orderDetailsType=6 and transaction.fromAgent.id=? and transaction.toAgent.id=?))");
			hql1.addParamter(tlf.getAgent().getId());
			hql1.addParamter(tlf.getAgent().getId());
			
			
			hql2.add(" and  ((transaction.orderDetails.orderDetailsType=5 and transaction.fromAgent.id<>? and transaction.toAgent.id=?)");
			hql2.addParamter(tlf.getAgent().getId());
			hql2.addParamter(tlf.getAgent().getId());
			hql2.add(" or  (transaction.orderDetails.orderDetailsType=6 and transaction.fromAgent.id=? and transaction.toAgent.id<>?)");
			hql2.addParamter(tlf.getAgent().getId());
			hql2.addParamter(tlf.getAgent().getId());
			hql2.add(" or  (transaction.orderDetails.orderDetailsType=6 and transaction.fromAgent.id=? and transaction.toAgent.id=?))");
			hql2.addParamter(tlf.getAgent().getId());
			hql2.addParamter(tlf.getAgent().getId());
		
		}
		else if(balanceType==3)//信用余额交易
		{
			
			hql0.add(" and  ((transaction.orderDetails.orderDetailsType=2)");
			hql0.add(" or  (transaction.orderDetails.orderDetailsType=3 and transaction.fromAgent.id<>? and transaction.toAgent.id=?))");
			hql0.addParamter(tlf.getAgent().getId());
			hql0.addParamter(tlf.getAgent().getId());
			
		   
			hql1.add(" and  ((transaction.orderDetails.orderDetailsType=2)");
			hql1.add(" or  (transaction.orderDetails.orderDetailsType=3 and transaction.fromAgent.id<>? and transaction.toAgent.id=?))");
			hql1.addParamter(tlf.getAgent().getId());
			hql1.addParamter(tlf.getAgent().getId());
			
			hql2.add(" and  ((transaction.orderDetails.orderDetailsType=2)");
			hql2.add(" or  (transaction.orderDetails.orderDetailsType=3 and transaction.fromAgent.id<>? and transaction.toAgent.id=?))");
			hql2.addParamter(tlf.getAgent().getId());
			hql2.addParamter(tlf.getAgent().getId());
		}
		hql0.add(" order by tb.transactionId desc");
 
		if (type == 0)
		{
			hql1.add(" and transaction.fromAgent.id=?");
		}
		else
			hql1.add(" and transaction.toAgent.id=?");
 
		hql1.addParamter(tlf.getAgent().getId());
		    
		Hql[] hqls = new Hql[3];
	
		hqls[0] = hql0;
		hqls[1] =  hql1;
		hqls[2] =hql2;
		System.out.println("HQL>>>>" + hql0);
		return hqls;
	}
	public Hql[] _parseHql(TransactionListForm tlf,int type)
	{
		/*
		 * this.id = id; this.transactionId = transactionId; this.transactionType =
		 * transactionType; this.transactionStatus = transactionStatus; this.balance
		 * = balance; this.creditBalance = creditBalance; this.agentId = agentId;
		 * this.status = status; this.transactionDate = transactionDate;
		 * OrderDetailsId = orderDetailsId; OrderDetailsNo = orderDetailsNo;
		 * this.getOrderNo = getOrderNo; this.amount = amount; this.fromAgentId =
		 * fromAgentId; this.toAgentId = toAgentId; this.fromAgentName =
		 * fromAgentName; this.fromAgentEmail = fromAgentEmail; this.toAgentName =
		 * toAgentName; this.toAgentEmail = toAgentEmail; this.shopName = shopName;
		 */
	
		Hql hql0 = new Hql();
		Hql hql1 = new Hql();
		Hql hql2 = new Hql();
		
		hql0.add("select new com.fordays.fdpay.transaction.TempTransactionBalance (tb.id,tb.transactionId,transaction.type,transaction.status,tb.balance,");
		hql0.add("tb.notallowBalance,tb.creditBalance,tb.agentId,tb.status,tb.transactionDate,transaction.orderDetails.id,transaction.orderDetails.orderDetailsNo,transaction.orderDetails.orderNo,transaction.amount,");
		hql0.add("transaction.fromAgent.id,transaction.toAgent.id,transaction.fromAgent.name,transaction.fromAgent.email,transaction.toAgent.name,transaction.toAgent.email,");
		hql0.add("transaction.orderDetails.shopName,transaction.mark)");
		hql0.add(" from TransactionBalance tb,Transaction transaction ");
		hql0.add(" where tb.agentId=? and transaction.id=tb.transactionId and transaction.status in (3,11)");
		// sc  下载时报表 添加判断 不显示自己对自己交易  and transaction.fromAgent.id <> transaction.toAgent.id
	//	if(tlf.getCheck().equals("download")){
	//	hql0.add("and transaction.fromAgent.id <> transaction.toAgent.id");
	//	}
		hql0.addParamter(tlf.getAgent().getId());
		
		hql1.add("select sum(transaction.amount), count(transaction.amount) ") ;
		hql1.add(" from TransactionBalance tb,Transaction transaction ");
		hql1.add(" where tb.agentId=? and transaction.id=tb.transactionId and transaction.status in (3,11)");
		hql1.addParamter(tlf.getAgent().getId());
		
		hql2.add("select count(*) from TransactionBalance tb  where  tb.agentId=? ");
		hql2.addParamter(tlf.getAgent().getId());
		
		String beginDate = tlf.getBeginDate();
		String endDate = tlf.getEndDate();
		if ("".equals(beginDate) == false && "".equals(endDate) == true)
		{
			hql0.add(" and to_char(tb.transactionDate,'yyyy-mm-dd hh24:mi:ss') > ?");
			hql0.addParamter(beginDate);
			
			hql1.add(" and to_char(tb.transactionDate,'yyyy-mm-dd hh24:mi:ss') > ?");
			hql1.addParamter(beginDate);
			
			hql2.add(" and to_char(tb.transactionDate,'yyyy-mm-dd hh24:mi:ss') > ?");
			hql2.addParamter(beginDate);

	
		}
		if ("".equals(beginDate) == true && "".equals(endDate) == false)
		{
			hql0.add(" and to_char(tb.transactionDate,'yyyy-mm-dd hh24:mi:ss') < ?");
			hql0.addParamter(endDate);
			
			hql1.add(" and to_char(tb.transactionDate,'yyyy-mm-dd hh24:mi:ss') < ?");
			hql1.addParamter(endDate);
			
			hql2.add(" and to_char(tb.transactionDate,'yyyy-mm-dd hh24:mi:ss') < ?");
			hql2.addParamter(endDate);
		}
		if ("".equals(beginDate) == false && "".equals(endDate) == false)
		{
			hql0.add(" and  to_char(tb.transactionDate,'yyyy-mm-dd hh24:mi:ss')  between ? and ? ");
			hql0.addParamter(beginDate);
			hql0.addParamter(endDate);
			
			hql1.add(" and  to_char(tb.transactionDate,'yyyy-mm-dd hh24:mi:ss')  between ? and ? ");
			hql1.addParamter(beginDate);
			hql1.addParamter(endDate);
			
			hql2.add(" and  to_char(tb.transactionDate,'yyyy-mm-dd hh24:mi:ss')  between ? and ? ");
			hql2.addParamter(beginDate);
			hql2.addParamter(endDate);
		}
	
		hql0.add(" order by tb.transactionId desc");
	
		if (type == 0)
		{
			hql1.add(" and transaction.fromAgent.id=?");
		}
		else
			hql1.add(" and transaction.toAgent.id=?");
	
		hql1.addParamter(tlf.getAgent().getId());
		    
		Hql[] hqls = new Hql[3];
	
		hqls[0] = hql0;
		hqls[1] =  hql1;
		hqls[2] =hql2;
		return hqls;
	}

	public Object[] statToAgentTransaction(TransactionListForm tlf, int type)
	    throws AppException
	{
		Hql hql=parseHql(tlf,type)[1];
			Query query = this.getQuery(hql);
		Object[] obj=new Object[2];
		if (query != null)
		{
			List list = query.list();
			if (list != null && list.size() > 0)
			{
				obj = (Object[]) list.get(0);
				System.out.println(obj[0]);
				System.out.println(obj[1]);
				return obj;
			}
		}
		System.out.println(hql);
		return obj;
	}
	public int getTransactionBalanceRow(TransactionListForm tlf)throws AppException {
		Hql hql = this.parseHql(tlf,0)[2];
		Query query = this.getQuery(hql);
		Object obj = query.uniqueResult();
		if (obj != null)
			return Constant.toInt(String.valueOf(obj));
		else
			return 0;
	}
}
