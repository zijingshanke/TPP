package com.fordays.fdpay.transaction.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.fordays.fdpay.agent.Agent;
import com.fordays.fdpay.transaction.biz.GatewayBiz;

public class GatewayAction extends Action
{
	private GatewayBiz gatewayBiz;
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	{
		try{
		String partner="200903130123456789";
		double balance=12000.0;
		boolean flag=false;
		flag=gatewayBiz.validatePartner(partner);
		Agent agent=new Agent();
		agent.setId(22);
		Agent result=gatewayBiz.getAgent(agent);
		flag=false;
		if(result!=null){
			flag=gatewayBiz.validateAmount(result, balance);
		}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return new ActionForward("/");
	}
	public GatewayBiz getGatewayBiz() {
		return gatewayBiz;
	}
	public void setGatewayBiz(GatewayBiz gatewayBiz) {
		this.gatewayBiz = gatewayBiz;
	}
}