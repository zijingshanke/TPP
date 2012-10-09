package com.fordays.fdpay.system.action;

import org.apache.struts.action.*;

import com.fordays.fdpay.transaction.TransactionStatus;
import com.fordays.fdpay.system.biz.TransactionStatusBiz;
import com.neza.base.BaseAction;
import com.neza.base.Inform;
import com.neza.exception.AppException;

import javax.servlet.http.*;

public class TransactionStatusAction extends BaseAction {
	private TransactionStatusBiz tansactionStatusBiz;

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		TransactionStatus transactionstatus = (TransactionStatus) form;
		Inform inf = new Inform();
		try {
			TransactionStatus tempTransactionStatus = (TransactionStatus) tansactionStatusBiz
					.getTransactionStatusById(transactionstatus.getId());
			tempTransactionStatus.setName(transactionstatus.getName());
			tempTransactionStatus.setStatus(transactionstatus.getStatus());
			tempTransactionStatus.setCode(transactionstatus.getCode());

			tansactionStatusBiz.updateTransactionStatus(tempTransactionStatus);
			request.setAttribute("tansactionStatus", tempTransactionStatus);
			inf.setMessage("您已经成功更新了系统状态资料！");
			inf.setForwardPage("/system/transactionStatuslist.do");
			inf.setParamId("thisAction");
			inf.setParamValue("list");
		} catch (Exception ex) {
			inf.setMessage("更新系统状态资料出错！错误信息是：" + ex.getMessage());
			inf.setBack(true);
		}
		request.setAttribute("inf", inf);
		forwardPage = "inform";
		return (mapping.findForward(forwardPage));
	}

	public ActionForward insert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		TransactionStatus tansactionStatus = (TransactionStatus) form;
		Inform inf = new Inform();
		if (isTokenValid(request, true)) {
			try {
				TransactionStatus tempTransactionStatus = new TransactionStatus();

				tempTransactionStatus.setName(tansactionStatus.getName());
				tempTransactionStatus.setStatus(tansactionStatus.getStatus());
				tempTransactionStatus.setCode(tansactionStatus.getCode());

				tansactionStatusBiz
						.saveTransactionStatus(tempTransactionStatus);

				request.setAttribute("tansactionStatus", tempTransactionStatus);
				inf.setMessage("您已经成功增加了系统状态！");
				inf.setForwardPage("/system/transactionStatuslist.do");
				inf.setParamId("thisAction");
				inf.setParamValue("list");
			} catch (Exception ex) {
				inf.setMessage("增加系统状态出错！错误信息是：" + ex.getMessage());
				inf.setBack(true);
			}
			request.setAttribute("inf", inf);
			forwardPage = "inform";
		} else {
			saveToken(request);
			inf.setMessage("请不要重复提交!!");
			request.setAttribute("message", inf);
		}
		return (mapping.findForward(forwardPage));
	}

	public void setTransactionStatusBiz(TransactionStatusBiz tansactionStatusBiz) {
		this.tansactionStatusBiz = tansactionStatusBiz;
	}
}