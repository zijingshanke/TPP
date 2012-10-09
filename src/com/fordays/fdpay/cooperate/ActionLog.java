package com.fordays.fdpay.cooperate;


import com.fordays.fdpay.cooperate._entity._ActionLog;

public class ActionLog extends _ActionLog{
  	private static final long serialVersionUID = 1L;
  	
  	String logTypeShow="";

	public String getLogTypeShow() {
		if(logType==1)
			return "支付请求";
		if(logType==2)
			return "支付返回";
		if(logType==0)
			return "退款请求";
		if(logType==3)
			return "退款返回";
		if(logType==5)
			return "退款请求URL失败";
		return "";
	}
  	
  	
  	
}
