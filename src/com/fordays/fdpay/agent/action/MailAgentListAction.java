package com.fordays.fdpay.agent.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.fordays.fdpay.agent.MailAgentListForm;
import com.fordays.fdpay.agent.biz.AgentBiz;
import com.neza.base.BaseAction;
import com.neza.exception.AppException;

public class MailAgentListAction extends BaseAction {

	private AgentBiz agentBiz;

	public void setAgentBiz(AgentBiz agentBiz) throws AppException {
		this.agentBiz = agentBiz;
	}

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		MailAgentListForm malf = (MailAgentListForm) form;
		if (malf == null)
			malf = new MailAgentListForm();
		
		malf.setList(agentBiz.getMailAgents(malf, malf.getEmails(), request
				.getSession().getId()));
		request.setAttribute("malf", malf);
		forwardPage = "listagent";
		return (mapping.findForward(forwardPage));
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		MailAgentListForm malf = (MailAgentListForm) form;
		String forwardPage = "";
		int id = 0;
		try {
			for (int i = 0; i < malf.getSelectedItems().length; i++) {
				id = malf.getSelectedItems()[i];
				if (id > 0)
					agentBiz.deleteMailAgentsById(id);
			}
		} catch (Exception ex) {
			System.out.println("删除发送邮件商户出错！错误信息是：" + ex.getMessage());
		}
		malf.setList(agentBiz
				.showMailAgents(malf, request.getSession().getId()));
		request.setAttribute("malf", malf);
		forwardPage = "listAgent";
		return (mapping.findForward(forwardPage));
	}
}
