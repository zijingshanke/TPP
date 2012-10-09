package com.fordays.fdpay.user;


import com.fordays.fdpay.user._entity._SysUser;

public class SysUser extends _SysUser{
 	private static final long serialVersionUID = 1L;
	private String userPassword1 = "";
	private String userPassword2 = "";
	private String rand = "";


	public String getUserPassword1() {
		return userPassword1;
	}
	public void setUserPassword1(String userPassword1) {
		this.userPassword1 = userPassword1;
	}
	public String getUserPassword2() {
		return userPassword2;
	}
	public void setUserPassword2(String userPassword2) {
		this.userPassword2 = userPassword2;
	}
	public String getRand() {
		return rand;
	}
	public void setRand(String rand) {
		this.rand = rand;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
  	
	public String getUserStatusCaption() {
		if(this.userStatus.intValue()==1)
			return "启用";
		else
			return "停用";
	}


}
