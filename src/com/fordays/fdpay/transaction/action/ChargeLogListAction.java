package com.fordays.fdpay.transaction.action;


import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.fordays.fdpay.agent.biz.AgentBiz;
import com.fordays.fdpay.transaction.Charge;
import com.fordays.fdpay.transaction.ChargeLogListForm;
import com.fordays.fdpay.transaction.biz.ChargeBiz;
import com.fordays.fdpay.transaction.biz.ChargeLogBiz;
import com.neza.base.BaseAction;
import com.neza.exception.AppException;

public class ChargeLogListAction extends BaseAction
{
	private AgentBiz agentBiz;
	private ChargeLogBiz chargeLogBiz;
	private ChargeBiz chargeBiz;
	public AgentBiz getAgentBiz() throws AppException{
		return agentBiz;
	}

	public void setAgentBiz(AgentBiz agentBiz) throws AppException{
		this.agentBiz = agentBiz;
	}

	

	public ChargeLogBiz getChargeLogBiz() throws AppException{
		return chargeLogBiz;
	}

	public void setChargeLogBiz(ChargeLogBiz chargeLogBiz) throws AppException{
		this.chargeLogBiz = chargeLogBiz;
	}

	public ActionForward listChargeLog(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws AppException{
		String forwardPage = "";
		
			ChargeLogListForm clof = (ChargeLogListForm) form;	
			if (clof == null)
				clof = new ChargeLogListForm();
			
		clof.setPerPageNum(5);	
		List list=chargeLogBiz.getChargeLogs(clof);
		

		clof.setList(list);
		clof.addSumField(1,"amount");
		
		request.setAttribute("clof", clof);
		forwardPage = "listchargeLog";
		
		return (mapping.findForward(forwardPage));
	}

	public ActionForward listChargeByContent(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws AppException {
		String forwardPage = "";
		ChargeLogListForm clf = (ChargeLogListForm) form;	
			if (clf == null)
				clf = new ChargeLogListForm();
		String orderNo=clf.getOrderNo();
		List list=chargeLogBiz.getChargeLogByContent(clf);
		Charge charge=chargeBiz.getChargeByOrderNo(orderNo);
		request.setAttribute("charge", charge);
		clf.setList(list);
		request.setAttribute("clf", clf);
		forwardPage = "listChargeLogByContent";
		
		return (mapping.findForward(forwardPage));
	}

	public ChargeBiz getChargeBiz() {
		return chargeBiz;
	}

	public void setChargeBiz(ChargeBiz chargeBiz) {
		this.chargeBiz = chargeBiz;
	}
}
