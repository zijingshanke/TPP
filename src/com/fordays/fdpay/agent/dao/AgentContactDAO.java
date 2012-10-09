package com.fordays.fdpay.agent.dao;

import java.util.List;
import com.neza.exception.AppException;
import com.fordays.fdpay.agent.Agent;
import com.fordays.fdpay.agent.AgentListForm;
import com.fordays.fdpay.agent.MailAgent;
import com.fordays.fdpay.agent.MailAgentListForm;
import com.neza.base.BaseDAO;

public interface AgentContactDAO extends BaseDAO
{
	List getAgentContacts(Long agentId)throws AppException;
}
