package com.fordays.fdpay.system.biz;


import com.fordays.fdpay.agent.dao.AgentDAO;
import com.neza.exception.AppException;

public class SysInitBizImp implements SysInitBiz {

	private AgentDAO agentDAO;
	public  String  platAgentEmail="";
	public  String  platChargeAgentEmail="";
	public  String  platRateAgentEmail="";
	


	public void setPlatAgentEmail(String platAgentEmail)
  {
  	this.platAgentEmail = platAgentEmail;
  }

	public void setPlatChargeAgentEmail(String platChargeAgentEmail)
  {
  	this.platChargeAgentEmail = platChargeAgentEmail;
  }

	public void setPlatRateAgentEmail(String platRateAgentEmail)
  {
  	this.platRateAgentEmail = platRateAgentEmail;
  }

	public void setAgentDAO(AgentDAO agentDAO)
  {
  	this.agentDAO = agentDAO;
  }

	public void initPlatAgent() throws AppException{
		try
		{
			if(com.fordays.fdpay.base.Constant.platAgent==null)
				com.fordays.fdpay.base.Constant.platAgent =agentDAO.getAgentByEmail(platAgentEmail);
			if(com.fordays.fdpay.base.Constant.platChargeAgent==null)
				com.fordays.fdpay.base.Constant.platChargeAgent =agentDAO.getAgentByEmail(platChargeAgentEmail);
			if(com.fordays.fdpay.base.Constant.platRateAgent==null)
				com.fordays.fdpay.base.Constant.platRateAgent =agentDAO.getAgentByEmail(platRateAgentEmail);
		//	agentDAO.importAgent();
		}
		catch (Exception ex)
		{
			System.out.println("init system plat agent fails... "+ex.getMessage());
		}
	}
}
