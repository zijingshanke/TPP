package com.fordays.fdpay.system.action;

import org.apache.struts.action.*;

import com.fordays.fdpay.system.SysLogListForm;
import com.fordays.fdpay.system.TransactionStatusListForm;
import com.fordays.fdpay.system.biz.SysLogBiz;
import com.fordays.fdpay.system.biz.TransactionStatusBiz;
import com.fordays.fdpay.transaction.TransactionStatus;
import com.neza.base.BaseAction;
import com.neza.base.Inform;
import com.neza.exception.AppException;


import javax.servlet.http.*;

public class SysLogAction extends BaseAction {
	
	private SysLogBiz sysLogBiz;


	public void setSysLogBiz(SysLogBiz sysLogBiz) {

			this.sysLogBiz = sysLogBiz;
		}
//显示所有
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws AppException{
		String forwardPage = "";
		SysLogListForm tslf = (SysLogListForm) form;
		if (tslf == null)
			tslf = new SysLogListForm();	
		tslf.setLocate(com.fordays.fdpay.system.SysLog.TYPE_LOGIN);
		tslf.setList(sysLogBiz.getSysLogs(tslf));
		request.setAttribute("tslf", tslf);
		forwardPage = "listsyslog";
		return (mapping.findForward(forwardPage));
	}
	
	public ActionForward listclient(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws AppException{
		String forwardPage = "";
		SysLogListForm tslf = (SysLogListForm) form;
		if (tslf == null)
			tslf = new SysLogListForm();	
		tslf.setLocate(new Long(2));
		tslf.setList(sysLogBiz.getSysLogs(tslf));
		request.setAttribute("tslf", tslf);
		forwardPage = "listclientsyslog";
		return (mapping.findForward(forwardPage));
	}
		
}