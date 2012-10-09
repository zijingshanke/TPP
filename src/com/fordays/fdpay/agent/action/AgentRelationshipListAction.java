package com.fordays.fdpay.agent.action;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.fordays.fdpay.agent.Agent;
import com.fordays.fdpay.agent.AgentRelationshipListForm;
import com.fordays.fdpay.agent.biz.AgentBiz;
import com.fordays.fdpay.agent.biz.AgentRelationshipBiz;
import com.neza.base.BaseAction;
import com.neza.exception.AppException;

public class AgentRelationshipListAction extends BaseAction {
	
	
	public AgentRelationshipBiz agentRelationshipBiz;
	private AgentBiz agentBiz;

	public void setAgentBiz(AgentBiz agentBiz)
	{
		this.agentBiz = agentBiz;
	}







	public ActionForward listParent(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws AppException {
		String forwardPage = "";

		AgentRelationshipListForm alf = (AgentRelationshipListForm) form;
		long id=Long.parseLong(request.getParameter("id"));
		if (alf == null)
			alf = new AgentRelationshipListForm();
		Agent agent =agentBiz.getAgentById(id);
		alf.setId(id);
		alf.setList(agentRelationshipBiz.getAgentParentByAgent(alf));
		request.setAttribute("alf", alf);
		forwardPage = "listagentparent";
		return (mapping.findForward(forwardPage));
	}
	public ActionForward listChild(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws AppException {
		String forwardPage = "";
		long id=Long.parseLong(request.getParameter("id"));
		request.setAttribute("id", id);
		AgentRelationshipListForm alf = (AgentRelationshipListForm) form;
		if (alf == null)
			alf = new AgentRelationshipListForm();
		alf.setId(id);
		alf.setList(agentRelationshipBiz.getAgentChildByAgent(alf));
		
		request.setAttribute("alf", alf);
		forwardPage = "listagentchild";
		return (mapping.findForward(forwardPage));
	}







	public void setAgentRelationshipBiz(AgentRelationshipBiz agentRelationshipBiz)
	{
		this.agentRelationshipBiz = agentRelationshipBiz;
	}

}
