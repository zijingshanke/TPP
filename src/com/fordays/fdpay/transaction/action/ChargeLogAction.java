package com.fordays.fdpay.transaction.action;

import org.apache.struts.action.*;
import com.neza.exception.AppException;
import com.fordays.fdpay.agent.biz.AgentBiz;
import com.fordays.fdpay.transaction.biz.ChargeLogBiz;
import com.neza.base.BaseAction;


import javax.servlet.http.*;

public class ChargeLogAction extends BaseAction
{
	private ChargeLogBiz chargeLogBiz;
	private AgentBiz agentBiz;

	public ChargeLogBiz getChargeBiz()throws AppException {
		return chargeLogBiz;
	}

	public void setChargeBiz(ChargeLogBiz chargeLogBiz)throws AppException {
		this.chargeLogBiz = chargeLogBiz;
	}

	public AgentBiz getAgentBiz() throws AppException{
		return agentBiz;
	}

	public void setAgentBiz(AgentBiz agentBiz) throws AppException{
		this.agentBiz = agentBiz;
	}

	public ChargeLogBiz getTransactionBiz()throws AppException
		{
			return chargeLogBiz;
		}


	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws AppException
		{

			String forwardPage = "";
			
			forwardPage = "inform";
			return (mapping.findForward(forwardPage));
		}


}