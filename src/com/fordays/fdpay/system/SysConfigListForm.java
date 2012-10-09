package com.fordays.fdpay.system;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.neza.base.ListActionForm;

public class SysConfigListForm  extends ListActionForm{


	private static final long serialVersionUID = 1L;
 	private SysConfig sysConfig = new SysConfig();
 	private int sysConfigId=0;
	public int getSysConfigId() {
		return sysConfigId;
	}
	public void setSysConfigId(int sysConfigId) {
		this.sysConfigId = sysConfigId;
	}
	public SysConfig getSysConfig() {
		return sysConfig;
	}
	public void setSysConfig(SysConfig sysConfig) {
		this.sysConfig = sysConfig;
	}
	public void reset(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest)
	{
		thisAction = "";
		this.setIntPage(1);
	}
}
