package com.fordays.fdpay.system.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.fordays.fdpay.agent.AgentListForm;
import com.fordays.fdpay.agent.biz.AgentBiz;
import com.fordays.fdpay.system.PatternEmail;
import com.fordays.fdpay.system.PatternEmailListForm;
import com.fordays.fdpay.system.biz.PatternEmailBiz;
import com.neza.base.BaseAction;
import com.neza.base.Inform;
import com.neza.exception.AppException;

public class PatternEmailListAction extends BaseAction {
	private PatternEmailBiz patternEmailBiz;
	private AgentBiz agentBiz;

	// 选择商户
	public ActionForward selectAgent(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		AgentListForm alf = new AgentListForm();
		alf.setList(agentBiz.getAgents(alf));

		alf.setThisAction("select");

		request.setAttribute("alf", alf);
		forwardPage = "selectAgent";
		return (mapping.findForward(forwardPage));
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		PatternEmailListForm pelf = (PatternEmailListForm) form;
		int id = 0;
		if (pelf.getSelectedItems().length > 0) {
			id = pelf.getSelectedItems()[0];
		} else
			id = pelf.getId();
		if (id > 0) {
			PatternEmail patternemail = (PatternEmail) patternEmailBiz
					.getPatternEmailById(id);

			patternemail.setThisAction("update");
			request.setAttribute("patternEmail", patternemail);
		} else
			request.setAttribute("patternEmail", new PatternEmail());
		forwardPage = "editPatternEmail";
		return (mapping.findForward(forwardPage));
	}

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		PatternEmailListForm pelf = (PatternEmailListForm) form;
		int id = pelf.getId();
		if (id > 0) {
			PatternEmail patternemail = (PatternEmail) patternEmailBiz
					.getPatternEmailById(id);
			patternemail.setThisAction("view");
			request.setAttribute("patternEmail", patternemail);
		} else {
			forwardPage = "login";
		}
		forwardPage = "viewPatternEmail";
		return (mapping.findForward(forwardPage));
	}

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		PatternEmail patternemail = new PatternEmail();
		patternemail.setThisAction("insert");
		request.setAttribute("patternEmail", patternemail);
		String forwardPage = "editPatternEmail";
		return (mapping.findForward(forwardPage));
	}

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		PatternEmailListForm pelf = (PatternEmailListForm) form;
		if (pelf == null)
			pelf = new PatternEmailListForm();
		pelf.setList(patternEmailBiz.getPatternEmails(pelf));

		request.setAttribute("pelf", pelf);
		forwardPage = "listPatternEmail";
		return (mapping.findForward(forwardPage));
	}
	public ActionForward listPatternEmails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		PatternEmailListForm pelf = (PatternEmailListForm) form;
		if (pelf == null)
			pelf = new PatternEmailListForm();
		pelf.setList(patternEmailBiz.getPatternEmails(pelf));

		request.setAttribute("pelf", pelf);
		forwardPage = "listPatternEmail";
		return (mapping.findForward(forwardPage));
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		PatternEmailListForm pelf = (PatternEmailListForm) form;
		String forwardPage = "";
		int id = 0;
		Inform inf = new Inform();
		try {
			for (int i = 0; i < pelf.getSelectedItems().length; i++) {
				id = pelf.getSelectedItems()[i];
				if (id > 0)
					patternEmailBiz.deletePatternEmail(id);
			}
			inf.setMessage("删除邮件模板成功!");
			inf.setForwardPage("/system/patternEmaillist.do");
			inf.setParamId("thisAction");
			inf.setParamValue("list");
		} catch (Exception ex) {
			inf.setMessage("删除邮件模板失败，提示信息：" + ex.getMessage());
			inf.setBack(true);
		}
		request.setAttribute("inf", inf);
		forwardPage = "inform";
		return (mapping.findForward(forwardPage));
	}
	
	public void setAgentBiz(AgentBiz agentBiz) {
		this.agentBiz = agentBiz;
	}

	public void setPatternEmailBiz(PatternEmailBiz patternEmailBiz) {
		this.patternEmailBiz = patternEmailBiz;
	}
}
