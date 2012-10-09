package com.fordays.fdpay.system;


import com.fordays.fdpay.system._entity._LoginLog;

public class LoginLog extends _LoginLog{
  	private static final long serialVersionUID = 1L;
    public String loginStatus;
	public String getLoginStatus() {
		if(this.getStatus()==0){
			return "登录失败!";
		}else{
			return "登录成功";
		}
	}

}
