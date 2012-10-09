package com.fordays.fdpay.agent.biz;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import com.fordays.fdpay.agent.Agent;
import com.fordays.fdpay.agent.AgentBalance;
import com.fordays.fdpay.agent.AgentListForm;
import com.fordays.fdpay.agent.CertInfo;
import com.fordays.fdpay.agent.MailAgentListForm;
import com.fordays.fdpay.agent.dao.AgentDAO;
import com.fordays.fdpay.right.UserRightInfo;
import com.fordays.fdpay.transaction.OrderDetails;
import com.fordays.fdpay.transaction.Transaction;
import com.fordays.fdpay.transaction.dao.TransactionDAOImp;
import com.neza.base.NoUtil;
import com.neza.encrypt.MD5;
import com.neza.exception.AppException;
import com.neza.tool.DateUtil;

public class AgentBizImp implements AgentBiz
{
	private AgentDAO agentDAO;
	private TransactionTemplate transactionTemplate;
	private TransactionDAOImp transactionDAO;
	private NoUtil noUtil;
	public NoUtil getNoUtil() {
		return noUtil;
	}

	public void setNoUtil(NoUtil noUtil) {
		this.noUtil = noUtil;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public void setTransactionManager(
	    HibernateTransactionManager transactionManager)
	{
		this.transactionTemplate = new TransactionTemplate(transactionManager);
	}

	public void setAgentDAO(AgentDAO agentDAO)
	{
		this.agentDAO = agentDAO;
	}

	public Agent getAgentById(long id) throws AppException
	{
		agentDAO.synallowBalance(agentDAO.getAgentById(id));
		return  agentDAO.getAgentById(id);
	}

	public Agent getAgentByName(String name) throws AppException
	{
		return agentDAO.getAgentByName(name);
	}

	public Agent getAgentByEmail(String email) throws AppException
	{
		return agentDAO.getAgentByEmail(email);
	}

	public CertInfo getCertById(long id) throws AppException
	{
		return agentDAO.getCertById(id);
	}
	
	
	public Agent getAgentByEmailTempEmail(String email) throws AppException
	{
		return agentDAO.getAgentByEmailTempEmail(email);
	}
	
	public List getAgents(AgentListForm alf) throws AppException
	{
		return agentDAO.list(alf);
	}

	public Agent login(String userNo, String userPassword) throws AppException
	{
		return null;
	}

	public void saveAgent(Agent agent) throws AppException
	{
		Agent tempAgent = (Agent) agentDAO.getAgentById(agent.getId());
		tempAgent.setName(agent.getName());
		tempAgent.setEmail(agent.getEmail());
		tempAgent.setMobile(agent.getMobile());
		tempAgent.setTelephone(agent.getTelephone());
		tempAgent.setPassword(agent.getPassword());
		tempAgent.setPayPassword(agent.getPayPassword());
		tempAgent.setLoginName(agent.getLoginName());
		tempAgent.setRegisterType(agent.getRegisterType());
		tempAgent.setCertType(agent.getCertType());
		tempAgent.setCertNum(agent.getCertNum());
		agentDAO.save(tempAgent);

	}

	public void achieveAmount(Agent agent , BigDecimal amount) throws AppException
	{
		
		 agentDAO.addAmount(agent,amount);
		 agentDAO.deleteAmount(com.fordays.fdpay.base.Constant.platChargeAgent, amount);
	}
	
	public long updateAgentPassword(Agent agent) throws AppException
	{
		return 0;
	}

	public long updateAgentInfo(Agent agent) throws AppException
	{
		return agentDAO.update(agent);
	}

	public void deleteAgent(long id) throws AppException
	{
		agentDAO.deleteById(id);
	}

	public boolean hasSameNo(String agentNo) throws AppException
	{
		return false;
	}

	public AgentDAO getAgentDAO()
	{
		return agentDAO;
	}

	public Agent queryByEmail(String email) throws AppException
	{
		return agentDAO.getAgentByEmail(email);
	}


	public Agent queryByName(String name) throws AppException
	{
		return agentDAO.queryByName(name);
	}

	public void deleteMailAgentsById(int mailagentId) throws AppException
	{
		agentDAO.deleteMailAgentsById(mailagentId);
	}

	public List getMailAgents(MailAgentListForm malf, String mails,
	    String sessionId) throws AppException
	{
		agentDAO.saveMailAgent(mails, sessionId);
		return agentDAO.getMailAgents(malf, sessionId);
	}

	public List showMailAgents(MailAgentListForm malf, String sessionId)
	    throws AppException
	{
		return agentDAO.getMailAgents(malf, sessionId);
	}

	public long updateAgentPersonal(Agent agent) throws AppException
	{
		Agent tempAgent = (Agent) agentDAO.getAgentById(agent.getId());
		tempAgent.setEmail(agent.getEmail());
		tempAgent.setTempEmail(agent.getTempEmail());
		tempAgent.setMobile(agent.getMobile());
		tempAgent.setTelephone(agent.getTelephone());
		tempAgent.setAddress(agent.getAddress());
		return agentDAO.update(tempAgent);
	}
	
	public long updateDefaultAmount(Agent agent) throws AppException {
		Agent tempAgent = (Agent) agentDAO.getAgentById(agent.getId());
		tempAgent.setMaxDayAmount(agent.getMaxDayAmount());
		tempAgent.setMaxDrawAmount(agent.getMaxDrawAmount());
		tempAgent.setMaxItemDrawAmount(agent.getMaxItemDrawAmount());
		tempAgent.setMaxItermAmount(agent.getMaxItermAmount());
		return agentDAO.update(tempAgent);
	}

	public long updatePassword(Agent agent) throws AppException
	{
		Agent tempAgent = (Agent) agentDAO.getAgentById(agent.getId());
		
		if(agent.getPassword()!=null&&!agent.getPassword().equals(""))
			tempAgent.setPassword(MD5.encrypt(agent.getPassword()));
		if(agent.getPayPassword()!=null&&!agent.getPayPassword().equals(""))
			tempAgent.setPayPassword(MD5.encrypt(agent.getPayPassword()));
		
		return agentDAO.update(tempAgent);
	}
	public long updateAccountStatus(Agent agent) throws AppException
	{
		Agent tempAgent = (Agent) agentDAO.getAgentById(agent.getId());
		tempAgent.setAccountStatus(agent.getAccountStatus());
		return agentDAO.update(tempAgent);
	}

	public List list(String sessionId, int userId,AgentListForm alf) throws AppException {
		/*	String date=alf.getDownloadDate();
		String currently=com.neza.tool.DateUtil.getDateString("yyyy-MM-dd HH:mm:ss");
		if(!"".equals(date)){
			if(date.substring(0,10).equals(currently.substring(0,10))){
				int dd=Integer.parseInt(date.substring(11,13))-Integer.parseInt(currently.substring(11,13));
				//2009-9-18 如果和当前时间小时数相差2小时以上，重新查询一次Balance
				if(dd<-2 || dd>2){
					agentDAO.createAgentBalance(sessionId,userId,alf);
				}
			}
		}else{
			alf.setDownloadDate(currently);
		}*/
		agentDAO.createAgentBalance(sessionId,userId,alf);
		return agentDAO.listForBalance(alf, sessionId,userId);
	}

	public String getBandBankNameByAgentId(long id) throws AppException {
		
		return agentDAO.getBandBankNameByAgentId(id);
	}

	public List getList(AgentListForm alf) throws AppException {
		return agentDAO.list(alf);
	}

	public long getCountAgentCertification() throws AppException {
//		agentDAO.createAgentBalance(sessionId, userId, new AgentListForm());
		return agentDAO.getCountAgentCertification();
	}

	// 移动商户从冻结余额到可用余额
	public void moveNotallowBalanceToAllowBalance(Agent agent, BigDecimal amount,String mark)
			throws AppException {
		    agentDAO.moveNotallowBalanceToAllowBalance(agent, amount);
			Transaction transaction = new Transaction();
			OrderDetails orderDetails = new OrderDetails();
			orderDetails.setPaymentPrice(amount);
			String shopName = "系统解冻冻结余额";
			transaction.setType(Transaction.TYPE_97);
			orderDetails.setShopName(shopName);
			transaction.setMark(mark);
			transaction.setAmount(amount);
			transaction.setFromAgent(agent);
			transaction.setToAgent(agent);
			transaction.setStatus(Transaction.STATUS_3);
			transaction.setPayDate(new Timestamp(System.currentTimeMillis()));
			orderDetails.setFinishDate(new Timestamp(System.currentTimeMillis()));
			orderDetails.setDetailsContent(mark);
			orderDetails.setOrderDetailsNo(noUtil.getOrderDetailsNo());
			orderDetails.setShopPrice(amount);
			orderDetails.setShopTotal(new Long(1));
			orderDetails.setOrderDetailsType(OrderDetails.ORDER_DETAILS_TYPE_6);
			String no = noUtil.getTransactionNo();
			transaction.setNo(no);
			transactionDAO.addOrderDetails(orderDetails);
			transaction.setOrderDetails(orderDetails);
			transaction.setAccountDate(new Timestamp(System.currentTimeMillis()));
			transactionDAO.save(transaction);
	}
	
	// 移动商户从可用余额到冻结余额
	public void moveAllowBalanceToNotallowBalance(Agent agent, BigDecimal amount,String mark)
    throws AppException{
		agentDAO.moveAllowBalanceToNotallowBalance(agent, amount);
		Transaction transaction = new Transaction();
		OrderDetails orderDetails = new OrderDetails();
		orderDetails.setPaymentPrice(amount);
		String shopName = "系统冻结可用余额";
		transaction.setType(Transaction.TYPE_98);
		orderDetails.setShopName(shopName);
		transaction.setMark(mark);
		transaction.setAmount(amount);
		transaction.setFromAgent(agent);
		transaction.setToAgent(agent);
		transaction.setStatus(Transaction.STATUS_3);
		transaction.setPayDate(new Timestamp(System.currentTimeMillis()));
		orderDetails.setFinishDate(new Timestamp(System.currentTimeMillis()));
		orderDetails.setDetailsContent(mark);
		orderDetails.setOrderDetailsNo(noUtil.getOrderDetailsNo());
		orderDetails.setShopPrice(amount);
		orderDetails.setShopTotal(new Long(1));
		orderDetails.setOrderDetailsType(OrderDetails.ORDER_DETAILS_TYPE_5);
		String no = noUtil.getTransactionNo();
		transaction.setNo(no);
		transactionDAO.addOrderDetails(orderDetails);
		transaction.setOrderDetails(orderDetails);
		transaction.setAccountDate(new Timestamp(System.currentTimeMillis()));
		transactionDAO.save(transaction);
	}
	
	//下载用
	public ArrayList<ArrayList<Object>> getAgentAll(String sessionId, int userId,AgentListForm alf)
    throws AppException
{
		String downloadDate="";
		if(alf.getDownloadDate() == null || alf.getDownloadDate().equals(""))
			alf.setDownloadDate(com.neza.tool.DateUtil.getDateString("yyyy-MM-dd HH:mm:ss"));
		
	downloadDate=alf.getDownloadDate();
	agentDAO.createAgentBalance(sessionId,userId,alf);
	List data =agentDAO.listForAllBalance(alf, sessionId, userId);
	ArrayList<ArrayList<Object>> list_context = new ArrayList<ArrayList<Object>>(); 
	ArrayList<Object> list_title = new ArrayList<Object>();
	list_title.add("#钱门商户余额查询");
	list_context.add(list_title);
	list_title = new ArrayList<Object>();
	list_title.add("#指定查询时间：");
	list_context.add(list_title);
	list_title = new ArrayList<Object>();
	list_title.add(downloadDate);
	list_context.add(list_title);
	list_title =  new ArrayList<Object>();
	list_title.add("商户姓名");
	list_title.add("商户账号");
	list_title.add("余额（元）");
	//list_title.add("冻结余额（元）");
	//list_title.add("账户总额（元）");
	list_context.add(list_title);
	for (int i = 0; i < data.size(); i++)
	{
		AgentBalance ag = (AgentBalance) data.get(i);
		ArrayList<Object> list_context_item = new ArrayList<Object>();
		list_context_item.add(ag.getAgent().getName());
		if(ag.getAgent().getRegisterType()!=null&&ag.getAgent().getRegisterType()==1){
			list_context_item.add(ag.getAgent().getLoginName()+"     （"+ag.getAgent().getCompanyName()+"）");
		}else if(ag.getAgent().getRegisterType()!=null&&ag.getAgent().getRegisterType()==0){
			list_context_item.add(ag.getAgent().getLoginName());
		}else{
			list_context_item.add("");
		}
		list_context_item.add(ag.getAllowBalance());
		//list_context_item.add(ag.getNotallowBalance());
		//list_context_item.add(ag.getNotallowBalance().add(ag.getAllowBalance()));
		list_context.add(list_context_item);
	}
	return list_context;
}

	public AgentBalance getAgentBalance(String sessionId,
			int userId, long agentId) throws AppException {
		return agentDAO.getAgentBalance(agentId, sessionId, userId);
	}

	public TransactionDAOImp getTransactionDAO() {
		return transactionDAO;
	}

	public void setTransactionDAO(TransactionDAOImp transactionDAO) {
		this.transactionDAO = transactionDAO;
	}

	public void synallowBalance(Agent agent) throws AppException {
		this.agentDAO.synallowBalance(agent);

	}

	public BigDecimal getAgentAllowBalance(long agentId) throws AppException {
		return agentDAO.getAgentAllowBalance(agentId);
	}
	
	public BigDecimal getAgentCreditBalance(long agentId) throws AppException {
		return agentDAO.getAgentCreditBalance(agentId);
	}

	public BigDecimal getAgentNotAllowBalance(long agentId) throws AppException {
		return agentDAO.getAgentNotAllowBalance(agentId);
	}
	public AgentBalance getAgentBalance(long agentId,String date) throws AppException{
		return agentDAO.getAgentBalance(agentId,date);
	}
	public AgentBalance getAgentBalance(long agentId) throws AppException{
		return agentDAO.getAgentBalance(agentId);
	}
}
