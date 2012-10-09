package com.fordays.fdpay.agent;

import com.fordays.fdpay.agent._entity._MailAgent;

public class MailAgent extends _MailAgent{
  	private static final long serialVersionUID = 1L;
  	
  	private Agent agent;

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public MailAgent() {
		super();
	}
	
	public MailAgent(long id,Agent agent) {
		super();
		this.agent = agent;
		this.id=id;
	}	
}
