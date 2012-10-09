package com.fordays.fdpay.system.action;

import org.apache.struts.action.*;

import com.fordays.fdpay.system.LoginLogListForm;
import com.fordays.fdpay.system.SysLogListForm;
import com.fordays.fdpay.system.TransactionStatusListForm;
import com.fordays.fdpay.system.biz.LoginLogBiz;
import com.fordays.fdpay.system.biz.SysLogBiz;
import com.fordays.fdpay.system.biz.TransactionStatusBiz;
import com.fordays.fdpay.transaction.TransactionStatus;
import com.neza.base.BaseAction;
import com.neza.base.Inform;
import com.neza.exception.AppException;


import javax.servlet.http.*;

public class LoginLogListAction extends BaseAction {
	private LoginLogBiz loginlogBiz;
	public void setLoginlogBiz(LoginLogBiz loginlogBiz) {
		this.loginlogBiz = loginlogBiz;
	}

//显示所有
//	public ActionForward list(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response) throws AppException{
//		String forwardPage = "";
//		SysLogListForm tslf = (SysLogListForm) form;
//		if (tslf == null)
//			tslf = new SysLogListForm();	
//		tslf.setLocate(new Long(2));
//		tslf.setList(sysLogBiz.getSysLogs(tslf));
//		request.setAttribute("tslf", tslf);
//		forwardPage = "listsyslog";
//		return (mapping.findForward(forwardPage));
//	}
	
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws AppException{
		String locate = request.getParameter("locate");
		String forwardPage = "";
		LoginLogListForm lllf = (LoginLogListForm) form;
		if (lllf == null)
			lllf = new LoginLogListForm();	
		lllf.setLocate(new Long(locate));
		lllf.setList(loginlogBiz.getLoginLogs(lllf));
		request.setAttribute("lllf", lllf);
		request.setAttribute("locate",locate);
		forwardPage = "listclientloginlog";
		
		return (mapping.findForward(forwardPage));
	}
		
}