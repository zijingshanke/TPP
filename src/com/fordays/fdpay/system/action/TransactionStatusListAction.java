package com.fordays.fdpay.system.action;

import javax.servlet.http.*;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.fordays.fdpay.transaction.TransactionStatus;
import com.fordays.fdpay.system.TransactionStatusListForm;
import com.fordays.fdpay.system.biz.TransactionStatusBiz;
import com.neza.base.BaseAction;
import com.neza.base.Inform;
import com.neza.exception.AppException;

public class TransactionStatusListAction extends BaseAction {
	private TransactionStatusBiz transactionStatusBiz;

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		TransactionStatusListForm tslf = (TransactionStatusListForm) form;
		int id = 0;
		if (tslf.getSelectedItems().length > 0) {
			id = tslf.getSelectedItems()[0];
		} else
			id = tslf.getId();
		if (id > 0) {
			TransactionStatus transactionStatus = (TransactionStatus) transactionStatusBiz
					.getTransactionStatusById(id);

			transactionStatus.setThisAction("update");
			request.setAttribute("transactionStatus", transactionStatus);
		} else
			request.setAttribute("transactionStatus", new TransactionStatus());
		forwardPage = "editTransactionStatus";
		return (mapping.findForward(forwardPage));
	}

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		TransactionStatusListForm tslf = (TransactionStatusListForm) form;
		int id = tslf.getId();
		if (id > 0) {
			TransactionStatus transactionStatus = (TransactionStatus) transactionStatusBiz
					.getTransactionStatusById(id);
			transactionStatus.setThisAction("view");
			request.setAttribute("transactionStatus", transactionStatus);
		} else {
			forwardPage = "login";
		}

		forwardPage = "viewTransactionStatus";
		return (mapping.findForward(forwardPage));
	}

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		TransactionStatus transactionStatus = new TransactionStatus();
		transactionStatus.setThisAction("insert");
		request.setAttribute("transactionStatus", transactionStatus);
		String forwardPage = "editTransactionStatus";
		return (mapping.findForward(forwardPage));
	}

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		TransactionStatusListForm tslf = (TransactionStatusListForm) form;
		if (tslf == null)
			tslf = new TransactionStatusListForm();
		tslf.setList(transactionStatusBiz.getTransactionStatuss(tslf));

		request.setAttribute("tslf", tslf);
		forwardPage = "listTransactionStatus";
		return (mapping.findForward(forwardPage));
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		TransactionStatusListForm tslf = (TransactionStatusListForm) form;
		String forwardPage = "";
		int id = 0;
		Inform inf = new Inform();
		try {
			for (int i = 0; i < tslf.getSelectedItems().length; i++) {
				id = tslf.getSelectedItems()[i];
				if (id > 0)
					transactionStatusBiz.deleteTransactionStatus(id);
			}
			inf.setMessage("您已经成功删除了系统状态！");
			inf.setForwardPage("/system/transactionStatuslist.do");
			inf.setParamId("thisAction");
			inf.setParamValue("list");
		} catch (Exception ex) {
			inf.setMessage("删除系统状态出错！错误信息是：" + ex.getMessage());
			inf.setBack(true);
		}
		request.setAttribute("inf", inf);
		forwardPage = "inform";
		return (mapping.findForward(forwardPage));
	}
	
	public void setTransactionStatusBiz(
			TransactionStatusBiz transactionStatusBiz) {
		this.transactionStatusBiz = transactionStatusBiz;
	}
}
