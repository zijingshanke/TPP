package com.fordays.fdpay.transaction;


import com.fordays.fdpay.transaction._entity._ChargeLog;
import com.fordays.fdpay.user.SysUser;

public class ChargeLog extends _ChargeLog{
  	private static final long serialVersionUID = 1L;
  	public static long CHARGELOG_STATUS_0=0;//申请
  	public static long CHARGELOG_STATUS_1=1;//成功
  	public static long CHARGELOG_STATUS_3=3;//批准
  	public static long CHARGELOG_STATUS_4=4;//核准
  	private SysUser sysUser;
  	private SysUser sysUser1;
	public SysUser getSysUser() {
		return sysUser;
	}
	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}
	public SysUser getSysUser1() {
		return sysUser1;
	}
	public void setSysUser1(SysUser sysUser1) {
		this.sysUser1 = sysUser1;
	}
  	public String getNoteCaption() {
		
		if(content!=null)
		{
			return content.replace("\\n","<br/>");
		}
		else
			return "";
	}
}
