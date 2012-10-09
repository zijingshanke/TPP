package com.fordays.fdpay.agent.biz;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.fordays.fdpay.agent.Agent;
import com.fordays.fdpay.agent.AgentBalance;
import com.fordays.fdpay.agent.AgentListForm;
import com.fordays.fdpay.agent.CertInfo;
import com.fordays.fdpay.agent.MailAgentListForm;
import com.neza.exception.AppException;
import com.neza.tool.DateUtil;

public interface AgentBiz {
	public Agent getAgentById(long id) throws AppException;

	public List getAgents(AgentListForm alf) throws AppException;
	public List getList(AgentListForm alf) throws AppException;
	public List list(String sessionId, int userId, AgentListForm alf) throws AppException;
	public AgentBalance getAgentBalance(String sessionId, int userId, long agentId) throws AppException;
	public String getBandBankNameByAgentId(long id)throws AppException;
	public List getMailAgents(MailAgentListForm malf, String mails,
			String sessionId) throws AppException;

	public void moveNotallowBalanceToAllowBalance(Agent agent, BigDecimal amount,String mark)throws AppException;
	public void deleteMailAgentsById(int mailagentId) throws AppException;
	public void moveAllowBalanceToNotallowBalance(Agent agent, BigDecimal amount,String mark)throws AppException;
	public Agent login(String userNo, String userPassword) throws AppException;

	public void saveAgent(Agent agent) throws AppException;


	public long updateAgentPassword(Agent agent) throws AppException;
	public long getCountAgentCertification() throws AppException;

	public long updateAgentInfo(Agent agent) throws AppException;
	public Agent getAgentByEmailTempEmail(String email) throws AppException;
	public long updateAgentPersonal(Agent agent) throws AppException;
	public long updatePassword(Agent agent) throws AppException;
	public long updateAccountStatus(Agent agent) throws AppException;
	public CertInfo getCertById(long id) throws AppException;
	public Agent queryByName(String name) throws AppException;
	
	public void deleteAgent(long id) throws AppException;
	public void achieveAmount(Agent agent,BigDecimal amount)throws AppException;
	public Agent getAgentByName(String name) throws AppException;
	public Agent getAgentByEmail(String email) throws AppException;
	public boolean hasSameNo(String agentNo) throws AppException;
	public List showMailAgents(MailAgentListForm malf,String sessionId) throws AppException;
	public ArrayList<ArrayList<Object>> getAgentAll(String sessionId, int userId,AgentListForm alf)throws AppException;

	public long updateDefaultAmount(Agent agent)throws AppException;
	public void synallowBalance(Agent agent) throws AppException;
	
	public BigDecimal getAgentAllowBalance(long agentId) throws AppException;
	public BigDecimal getAgentNotAllowBalance(long agentId) throws AppException;
	public BigDecimal getAgentCreditBalance(long agentId) throws AppException;
	public AgentBalance getAgentBalance(long agentId,String date) throws AppException;
	public AgentBalance getAgentBalance(long agentId) throws AppException;
}
