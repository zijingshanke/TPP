package com.fordays.fdpay.agent.biz;

import java.util.List;

import com.fordays.fdpay.agent.Agent;
import com.fordays.fdpay.agent.AgentRelationship;
import com.fordays.fdpay.agent.AgentRelationshipListForm;
import com.fordays.fdpay.agent.dao.AgentRelationshipDAO;
import com.neza.exception.AppException;

public class AgentRelationshipBizImpl implements AgentRelationshipBiz{

	public AgentRelationshipDAO agentRelationshipDAO;
	
	public void setAgentRelationshipDAO(AgentRelationshipDAO agentRelationshipDAO)
	{
		this.agentRelationshipDAO = agentRelationshipDAO;
	}

	public List getAgentChildByAgent(AgentRelationshipListForm alf) throws AppException
	{
		return agentRelationshipDAO.getAgentChildByAgent(alf);
	}

	public List getAgentParentByAgent(AgentRelationshipListForm alf) throws AppException
	{
		return agentRelationshipDAO.getAgentParentByAgent(alf);
	}
	
	
}
