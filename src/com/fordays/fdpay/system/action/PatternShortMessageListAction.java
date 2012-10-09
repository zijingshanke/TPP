package com.fordays.fdpay.system.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.fordays.fdpay.system.PatternShortMessage;
import com.fordays.fdpay.system.PatternShortMessageListForm;
import com.fordays.fdpay.system.biz.PatternShortMessageBiz;
import com.neza.base.BaseAction;
import com.neza.base.Inform;
import com.neza.exception.AppException;

public class PatternShortMessageListAction extends BaseAction {
	private PatternShortMessageBiz patternShortMessageBiz;

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		PatternShortMessageListForm psmlf = (PatternShortMessageListForm) form;
		int id = 0;
		if (psmlf.getSelectedItems().length > 0) {
			id = psmlf.getSelectedItems()[0];
		} else
			id = psmlf.getId();
		if (id > 0) {
			PatternShortMessage patternemail = (PatternShortMessage) patternShortMessageBiz
					.getPatternShortMessageById(id);

			patternemail.setThisAction("update");
			request.setAttribute("patternShortMessage", patternemail);
		} else
			request.setAttribute("patternShortMessage",
					new PatternShortMessage());
		forwardPage = "editPatternShortMessage";
		return (mapping.findForward(forwardPage));
	}

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		PatternShortMessageListForm psmlf = (PatternShortMessageListForm) form;
		int id = psmlf.getId();
		if (id > 0) {
			PatternShortMessage patternemail = (PatternShortMessage) patternShortMessageBiz
					.getPatternShortMessageById(id);
			patternemail.setThisAction("view");
			request.setAttribute("patternShortMessage", patternemail);
		} else {
			forwardPage = "login";
		}

		forwardPage = "viewPatternShortMessage";
		return (mapping.findForward(forwardPage));
	}

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		PatternShortMessage patternemail = new PatternShortMessage();
		patternemail.setThisAction("insert");
		request.setAttribute("patternShortMessage", patternemail);
		String forwardPage = "editPatternShortMessage";
		return (mapping.findForward(forwardPage));
	}

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		PatternShortMessageListForm psmlf = (PatternShortMessageListForm) form;
		if (psmlf == null)
			psmlf = new PatternShortMessageListForm();
		psmlf.setList(patternShortMessageBiz.getPatternShortMessages(psmlf));

		request.setAttribute("psmlf", psmlf);
		forwardPage = "listPatternShortMessage";
		return (mapping.findForward(forwardPage));
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		PatternShortMessageListForm psmlf = (PatternShortMessageListForm) form;
		String forwardPage = "";
		int id = 0;
		Inform inf = new Inform();
		try {
			for (int i = 0; i < psmlf.getSelectedItems().length; i++) {
				id = psmlf.getSelectedItems()[i];
				if (id > 0)
					patternShortMessageBiz.deletePatternShortMessage(id);
			}
			inf.setMessage("���您已经成功删除了短信��ɹ�ɾ���˶��ţ�");
			inf.setForwardPage("/system/patternShortMessagelist.do");
			inf.setParamId("thisAction");
			inf.setParamValue("list");
		} catch (Exception ex) {
			inf.setMessage("删除短信出错！错误信息是：" + ex.getMessage());
			inf.setBack(true);
		}
		request.setAttribute("inf", inf);
		forwardPage = "inform";
		return (mapping.findForward(forwardPage));
	}

	public void setPatternShortMessageBiz(
			PatternShortMessageBiz patternShortMessageBiz) {
		this.patternShortMessageBiz = patternShortMessageBiz;
	}
}
