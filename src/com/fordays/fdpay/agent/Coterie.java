package com.fordays.fdpay.agent;


import com.fordays.fdpay.agent._entity._Coterie;

public class Coterie extends _Coterie{
  	private static final long serialVersionUID = 1L;
	private String agentEmail="";
	private String tempAgentEmail="";
	public static long STATUS_YES=1;
  	public static long STATUS_NO=0;
	protected com.fordays.fdpay.agent.Agent tempAgent;
	public static String  ALLOW_MULTCOTERIE_1="1";
	public static String  ALLOW_MULTCOTERIE_0="0";
	private String tempAllow="";
	public com.fordays.fdpay.agent.Agent getTempAgent()
  {
  	return tempAgent;
  }
	public void setTempAgent(com.fordays.fdpay.agent.Agent tempAgent)
  {
  	this.tempAgent = tempAgent;
  }
	public String getAgentEmail() {
		return agentEmail;
	}
	public void setAgentEmail(String agentEmail) {
		this.agentEmail = agentEmail;
	}
	public String getTempAgentEmail() {
		return tempAgentEmail;
	}
	public void setTempAgentEmail(String tempAgentEmail) {
		this.tempAgentEmail = tempAgentEmail;
	}
	public String getTempAllow() {

		String tempAllowMult="";
		if(allowMultcoterie!=null){
   	 if(allowMultcoterie.equals("0")){
   		tempAllowMult="不允许";
   	 }else if(allowMultcoterie.equals("1")){
   		tempAllowMult="允许";
   	 }
	}
		return tempAllowMult;
	}
	public void setTempAllow(String tempAllow) {
		this.tempAllow = tempAllow;
	}
  
	

}
