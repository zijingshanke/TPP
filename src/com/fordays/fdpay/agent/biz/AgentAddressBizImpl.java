package com.fordays.fdpay.agent.biz;

import java.util.List;

import com.fordays.fdpay.agent.dao.AgentAddressDAO;
import com.neza.exception.AppException;

public class AgentAddressBizImpl implements AgentAddressBiz {
	private AgentAddressDAO agentAddressDAO;
	public List getAgentAddersss(Long agentId) throws AppException {
		return agentAddressDAO.getAgentAddresss(agentId);
	}
	public AgentAddressDAO getAgentAddressDAO() {
		return agentAddressDAO;
	}
	public void setAgentAddressDAO(AgentAddressDAO agentAddressDAO) {
		this.agentAddressDAO = agentAddressDAO;
	}

}
