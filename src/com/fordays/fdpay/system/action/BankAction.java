package com.fordays.fdpay.system.action;

import org.apache.struts.action.*;

import com.fordays.fdpay.system.SysConfig;
import com.fordays.fdpay.system.SysConfigListForm;

import com.fordays.fdpay.system.biz.SysConfigBiz;
import com.fordays.fdpay.user.SysUser;
import com.neza.base.BaseAction;
import com.neza.base.Inform;
import com.neza.exception.AppException;

import javax.servlet.http.*;

public class BankAction extends BaseAction {

	private SysConfigBiz sysConfigBiz;

	
	// 显示所有
	

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		// sysconfig.setThisAction("insert");
		request.setAttribute("thisaction", "insert");
		String forwardPage = "editsysconfig";
		return (mapping.findForward(forwardPage));
	}

	public ActionForward insert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {

		String forwardPage = "";
		SysConfigListForm sysconfiglistform = (SysConfigListForm) form;
		SysConfig sysconfig = sysconfiglistform.getSysConfig();
		Inform inf = new Inform();
		if (isTokenValid(request, true)) {
		try {
			SysConfig temp = new SysConfig();
			temp.setName(sysconfig.getName());
			temp.setCode(sysconfig.getCode());
			temp.setActive(sysconfig.getActive());
			temp.setValue(sysconfig.getValue());
			temp.setDescription(sysconfig.getDescription());
			sysConfigBiz.saveSysConfig(temp);
			request.setAttribute("user", temp);

			inf.setMessage("您已经成功增加了新的参数项！");
			inf.setForwardPage("/system/sysConfigList.do");
			inf.setParamId("thisAction");
			inf.setParamValue("list");

		} catch (Exception ex) {
			inf.setMessage("增加用户出错！错误信息是：" + ex.getMessage());
			inf.setBack(true);
		}
		request.setAttribute("inf", inf);
		forwardPage = "inform";
		}else {
			saveToken(request);
			inf.setMessage("请不要重复提交!!");
			request.setAttribute("message", inf);
		}
		return (mapping.findForward(forwardPage));
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";

		SysConfigListForm sclf = (SysConfigListForm) form;
		int id = 0;
		if (sclf.getSelectedItems().length > 0) {
			id = sclf.getSelectedItems()[0];
		} else
			id = sclf.getSysConfigId();
		if (id > 0) {

			SysConfig sysconfig = (SysConfig) sysConfigBiz.getSysConfigById(id);
			request.setAttribute("sysconfigId", id);
			request.setAttribute("thisaction", "update");
			request.setAttribute("sysconfig", sysconfig);

		} else
			request.setAttribute("sysconfig", new SysUser());
		forwardPage = "editsysconfig";
		return (mapping.findForward(forwardPage));
	}

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {

		String forwardPage = "";

		SysConfigListForm sclf = (SysConfigListForm) form;
		Inform inf = new Inform();
		try {
			SysConfig temp = (SysConfig) sysConfigBiz.getSysConfigById(sclf
					.getSysConfigId());
			temp.setName(sclf.getSysConfig().getName());
			temp.setCode(sclf.getSysConfig().getCode());
			temp.setActive(sclf.getSysConfig().getActive());
			temp.setDescription(sclf.getSysConfig().getDescription());
			sysConfigBiz.updateSysConfig(temp);

			inf.setMessage("您已经成功更新了用户资料！");
			inf.setForwardPage("/system/sysConfigList.do");
			inf.setParamId("thisAction");
			inf.setParamValue("list");

		} catch (Exception ex) {
			inf.setMessage("更新用户资料出错！错误信息是：" + ex.getMessage());
			inf.setBack(true);
		}
		request.setAttribute("inf", inf);
		forwardPage = "inform";
		return (mapping.findForward(forwardPage));
	}

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";

		SysConfigListForm sclf = (SysConfigListForm) form;
		int id = Integer.parseInt(request.getParameter("id").toString());
		if (id > 0) {
			SysConfig sysConfig =  sysConfigBiz.getSysConfigById(id);

			request.setAttribute("sysConfig", sysConfig);
		} else {
			forwardPage = "login";
		}

		forwardPage = "viewsysconfig";
		return (mapping.findForward(forwardPage));
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		SysConfigListForm sclf = (SysConfigListForm) form;
		String forwardPage = "";
		int id = 0;
		Inform inf = new Inform();
		try {
			for (int i = 0; i < sclf.getSelectedItems().length; i++) {
				id = sclf.getSelectedItems()[i];
				if (id > 0) {
					sysConfigBiz.deleteSysConfig(id);
				}
			}

			inf.setMessage("您已经成功删除了此参数！");
			inf.setForwardPage("/system/sysConfigList.do");
			inf.setParamId("thisAction");
			inf.setParamValue("list");
		} catch (Exception ex) {
			inf.setMessage("删除失败!" + ex.getMessage());
			inf.setBack(true);
		}
		request.setAttribute("inf", inf);
		forwardPage = "inform";

		return (mapping.findForward(forwardPage));
	}
}