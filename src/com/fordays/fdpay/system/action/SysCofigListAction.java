package com.fordays.fdpay.system.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.fordays.fdpay.system.SysConfigListForm;
import com.fordays.fdpay.system.biz.SysConfigBiz;
import com.neza.base.BaseAction;
import com.neza.exception.AppException;

public class SysCofigListAction extends BaseAction{
	private SysConfigBiz sysConfigBiz;

	public SysConfigBiz getSysConfigBiz() {
		return sysConfigBiz;
	}

	public void setSysConfigBiz(SysConfigBiz sysConfigBiz) {
		this.sysConfigBiz = sysConfigBiz;
	}
	// 显示所有
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		SysConfigListForm sclf = (SysConfigListForm) form;
		if (sclf == null)
			sclf = new SysConfigListForm();

		sclf.setList(sysConfigBiz.getSysConfigs(sclf));
		request.setAttribute("sclf", sclf);
		forwardPage = "listsysconfig";
		return (mapping.findForward(forwardPage));
	}
}
