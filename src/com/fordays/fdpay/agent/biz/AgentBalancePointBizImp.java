package com.fordays.fdpay.agent.biz;

import java.util.List;

import com.fordays.fdpay.agent.AgentBalancePointListForm;
import com.fordays.fdpay.agent.dao.AgentBalancePointDAO;
import com.neza.exception.AppException;

public class AgentBalancePointBizImp implements AgentBalancePointBiz {
	
	private AgentBalancePointDAO agentBalancePointDAO;
	
	public AgentBalancePointDAO getAgentBalancePointDAO() {
		return agentBalancePointDAO;
	}

	public void setAgentBalancePointDAO(AgentBalancePointDAO agentBalancePointDAO) {
		this.agentBalancePointDAO = agentBalancePointDAO;
	}

	public void addAgentToAgentBalancePoint()throws AppException{
		agentBalancePointDAO.addAgentToAgentBalancePoint();
	}
	public boolean truncateAgentBalancePoint() throws AppException{
		return agentBalancePointDAO.truncateAgentBalancePoint();	
	}
	public List listAgentBalancePoint(AgentBalancePointListForm alf) throws AppException{
		return agentBalancePointDAO.listAgentBalancePoint(alf);
	}
	
	public void updateAgentBalancePointAmount(String date) throws AppException{
		agentBalancePointDAO.updateAgentBalancePointAmount(date);
	}
}
