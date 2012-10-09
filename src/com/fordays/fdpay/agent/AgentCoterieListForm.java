package com.fordays.fdpay.agent;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.neza.base.ListActionForm;

public class AgentCoterieListForm extends ListActionForm{
	private String agentEmail="";
	private long agentCoterieId;
	private long coterieId;
	public long getCoterieId() {
		return coterieId;
	}

	public void setCoterieId(long coterieId) {
		this.coterieId = coterieId;
	}

	public void reset(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest)
	{
		thisAction = "";
		agentEmail="";
	}

	public long getAgentCoterieId() {
		return agentCoterieId;
	}

	public void setAgentCoterieId(long agentCoterieId) {
		this.agentCoterieId = agentCoterieId;
	}

	public String getAgentEmail() {
		return agentEmail;
	}

	public void setAgentEmail(String agentEmail) {
		this.agentEmail = agentEmail;
	}

}
