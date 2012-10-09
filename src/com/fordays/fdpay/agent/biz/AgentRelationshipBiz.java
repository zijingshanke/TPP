package com.fordays.fdpay.agent.biz;

import java.util.List;

import com.fordays.fdpay.agent.AgentRelationshipListForm;
import com.neza.exception.AppException;

public interface AgentRelationshipBiz {
 
	public List getAgentChildByAgent(AgentRelationshipListForm alf) throws AppException;
	public List getAgentParentByAgent(AgentRelationshipListForm alf) throws AppException;
}
