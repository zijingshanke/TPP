package com.fordays.fdpay.cooperate.action;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.fordays.fdpay.cooperate.ActionLogListForm;
import com.fordays.fdpay.cooperate.biz.ActionLogBiz;
import com.neza.exception.AppException;

public class ActionLogListAction extends DispatchAction {
	ActionLogBiz actionLogBiz;

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		ActionLogListForm allf = (ActionLogListForm) form;
		
		if (allf == null)
			allf = new ActionLogListForm();
		allf.setPerPageNum(5);	
		allf.setList(actionLogBiz.getActionLogs(allf));
		request.setAttribute("clf", allf);
		forwardPage = "listActionLog";
		return mapping.findForward(forwardPage);
	}

	public ActionLogBiz getActionLogBiz() {
		return actionLogBiz;
	}

	public void setActionLogBiz(ActionLogBiz actionLogBiz) {
		this.actionLogBiz = actionLogBiz;
	}
	
	
}
