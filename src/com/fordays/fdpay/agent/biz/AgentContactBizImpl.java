package com.fordays.fdpay.agent.biz;

import java.util.List;

import com.fordays.fdpay.agent.dao.AgentContactDAO;
import com.neza.exception.AppException;

public class AgentContactBizImpl implements AgentContactBiz {
	private AgentContactDAO agentContactDAO;
	public List getAgentContacts(Long agentId) throws AppException {
		return agentContactDAO.getAgentContacts(agentId);
	}
	public AgentContactDAO getAgentContactDAO() {
		return agentContactDAO;
	}
	public void setAgentContactDAO(AgentContactDAO agentContactDAO) {
		this.agentContactDAO = agentContactDAO;
	}

	
	
}
