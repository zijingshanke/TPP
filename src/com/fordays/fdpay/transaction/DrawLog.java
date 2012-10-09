package com.fordays.fdpay.transaction;


import com.fordays.fdpay.transaction._entity._DrawLog;
import com.fordays.fdpay.user.SysUser;

public class DrawLog extends _DrawLog{
  	private static final long serialVersionUID = 1L;
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
}
