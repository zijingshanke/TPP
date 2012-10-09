package com.fordays.fdpay.agent.dao;

import java.math.BigDecimal;
import java.util.List;

import com.fordays.fdpay.agent.Agent;
import com.fordays.fdpay.agent.AgentBalance;
import com.fordays.fdpay.agent.AgentListForm;
import com.fordays.fdpay.agent.CertInfo;
import com.fordays.fdpay.agent.MailAgent;
import com.fordays.fdpay.agent.MailAgentListForm;
import com.neza.base.BaseDAO;
import com.neza.exception.AppException;

public interface AgentDAO extends BaseDAO
{
	public Agent getAgentById(long id) throws AppException;
	public String getBandBankNameByAgentId(long id)throws AppException;
	public List list(AgentListForm alf) throws AppException;
	public List listForBalance(AgentListForm alf,String sessionId, int userId) throws AppException;
	public AgentBalance getAgentBalance(long agentId,String sessionId, int userId) throws AppException;
	public List listForAllBalance(AgentListForm alf,String sessionId, int userId) throws AppException;
	public List getAgentList(AgentListForm alf) throws AppException;
	public void createAgentBalance(String sessionId, int userId,AgentListForm alf) throws AppException;

	public Agent login(String userNo, String userPassword) throws AppException;

	public long save(Agent agent) throws AppException;
	public long getCountAgentCertification() throws AppException;

	public long merge(Agent agent) throws AppException;

	public long update(Agent agent) throws AppException;

	public void deleteById(long id) throws AppException;

	public Agent queryByName(String name) throws AppException;
	public void addAmount(Agent agent , BigDecimal amount) throws AppException;
	public void deleteAmount(Agent agent , BigDecimal amount) throws AppException;

	// 增加商户信用余额
	public void addCreditAmount(Agent agent, BigDecimal amount) throws AppException;
	// 扣除商户信用余额
	public void deleteCreditAmount(Agent agent, BigDecimal amount) throws AppException;
	
	public Agent getAgentByName(String name) throws AppException;
	public CertInfo getCertById(long id) throws AppException;
	public Agent queryByPartner(String partner) throws AppException;

	public boolean hasSameUserNo(String agentNo) throws AppException;

	public Agent queryByEmailAndPassword(String email, String password)
	    throws AppException;

	public Agent queryAgentByOrderAndToAgent(long orderId, long agnetId)
	    throws AppException;
	
	public Agent querySellerByOrderAndFromAgent(long orderId, long agnetId)
			throws AppException;

	public void saveMailAgent(String mails, String sessionId)
	    throws AppException;

	public List getMailAgents(MailAgentListForm malf,String sessionId)
	    throws AppException;

	public void deleteMailAgentsById(int mailagentId) throws AppException;
	public Agent getAgentByEmail(String loginName) throws AppException;
	public Agent getAgentByEmailTempEmail(String loginName) throws AppException;
	public MailAgent queryMailAgentById(int mailagentId) throws AppException;
	public void importAgent();
	public void moveNotallowBalanceToAllowBalance(Agent agent, BigDecimal amount)throws AppException;
	public void moveAllowBalanceToNotallowBalance(Agent agent, BigDecimal amount)throws AppException;
	public void reduceNotallowBalance(Agent agent, BigDecimal amount)throws AppException;
	public void synallowBalance(Agent agent) throws AppException;
    public BigDecimal getAgentAllowBalance(long agentId) throws AppException;
    public BigDecimal getAgentNotAllowBalance(long agentId) throws AppException;
	public BigDecimal getAgentCreditBalance(long agentId) throws AppException;
	public AgentBalance getAgentBalance(long agentId,String date) throws AppException;
	public AgentBalance getAgentBalance(long agentId) throws AppException;
	public List getExceptionalAgents()throws AppException; 
	public List getRepeatCharge(int hour) throws AppException;
}
