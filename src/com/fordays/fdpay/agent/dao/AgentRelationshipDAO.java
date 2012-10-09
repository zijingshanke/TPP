package com.fordays.fdpay.agent.dao;

import java.util.List;

import com.fordays.fdpay.agent.Agent;
import com.fordays.fdpay.agent.AgentRelationship;
import com.fordays.fdpay.agent.AgentRelationshipListForm;
import com.fordays.fdpay.agent.Coterie;
import com.neza.base.BaseDAO;
import com.neza.exception.AppException;

public interface AgentRelationshipDAO extends BaseDAO{
	public List getAgentChildByAgent(AgentRelationshipListForm alf) throws AppException;
	public List getAgentParentByAgent(AgentRelationshipListForm alf) throws AppException;

}
