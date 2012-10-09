package com.fordays.fdpay.agent;


import com.fordays.fdpay.agent._entity._AgentRelationship;

public class AgentRelationship extends _AgentRelationship{
  	private static final long serialVersionUID = 1L;
  	protected com.fordays.fdpay.agent.Agent parentAgent;


	public com.fordays.fdpay.agent.Agent getParentAgent() {
		return parentAgent;
	}
	public void setParentAgent(com.fordays.fdpay.agent.Agent parentAgent) {
		this.parentAgent = parentAgent;
	}

	
  	
  	
  	
}
