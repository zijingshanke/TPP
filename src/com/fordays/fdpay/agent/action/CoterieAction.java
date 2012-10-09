package com.fordays.fdpay.agent.action;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.fordays.fdpay.agent.AgentCoterie;
import com.fordays.fdpay.agent.Coterie;
import com.fordays.fdpay.agent.biz.AgentBiz;
import com.fordays.fdpay.agent.biz.AgentCoterieBiz;
import com.fordays.fdpay.agent.biz.CoterieBiz;
import com.neza.base.BaseAction;
import com.neza.base.Inform;
import com.neza.exception.AppException;

public class CoterieAction extends BaseAction {
	private CoterieBiz coterieBiz;
	private AgentBiz agentBiz;
	private AgentCoterieBiz agentCoterieBiz;

	public AgentCoterieBiz getAgentCoterieBiz() {
		return agentCoterieBiz;
	}

	public void setAgentCoterieBiz(AgentCoterieBiz agentCoterieBiz) {
		this.agentCoterieBiz = agentCoterieBiz;
	}

	public CoterieBiz getCoterieBiz() {
		return coterieBiz;
	}

	public void setCoterieBiz(CoterieBiz coterieBiz) {
		this.coterieBiz = coterieBiz;
	}

	public AgentBiz getAgentBiz() {
		return agentBiz;
	}

	public void setAgentBiz(AgentBiz agentBiz) {
		this.agentBiz = agentBiz;
	}

	public void setAgentCoterieBiz(CoterieBiz coterieBiz) throws AppException {
		this.coterieBiz = coterieBiz;
	}

	public ActionForward insert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {

		String forwardPage = "";
		Coterie coterie = (Coterie) form;
		com.fordays.fdpay.agent.Agent ag = null;
		Inform inf = new Inform();
		String email = coterie.getAgentEmail();
		ag = agentBiz.getAgentByEmail(email);
		com.fordays.fdpay.agent.Agent agent = agentBiz.getAgentByEmail(coterie
				.getTempAgentEmail());
		if (coterieBiz.getCoterieByName(coterie.getName()).getId() > 0) {
			inf.setMessage("不能创建两个相同的商户圈！");
			inf.setBack(true);
			request.setAttribute("inf", inf);
			forwardPage = "inform";
			return (mapping.findForward(forwardPage));
		}
		
		 if(ag.getLoginName() == null) {
			inf.setMessage("中间帐户Email不存在！");
			inf.setBack(true);
			request.setAttribute("inf", inf);
			forwardPage = "inform";
			return (mapping.findForward(forwardPage));
		}
		if (agent.getId() <= 0) {
			inf.setMessage("临时帐户Email不存在！");
			inf.setBack(true);
			request.setAttribute("inf", inf);
			forwardPage = "inform";
			return (mapping.findForward(forwardPage));
		}
		
        		
		int count = coterieBiz.getCoterieByAgent_Id(ag.getId());
		if (count > 0) {
			inf.setMessage("一个帐号只能创建一个商户圈！");
			inf.setBack(true);
			request.setAttribute("inf", inf);
			forwardPage = "inform";
			return (mapping.findForward(forwardPage));
		}
		
		boolean checkCoertie=coterieBiz.checkCoerieByAllowMult(ag.getId());
		int  count2= agentCoterieBiz.getAgentCoterieByAgent_Id(ag.getId());    
		 if(checkCoertie){
			 
			    inf.setMessage("该账户已加入一个独立商户圈！不能创建商户圈！");
			    inf.setBack(true);
				request.setAttribute("inf", inf);
				forwardPage = "inform";
				return (mapping.findForward(forwardPage));
			 
		 }else{		 
			 if(coterie.getAllowMultcoterie().equals("0")&&count2>0){
				 
				 inf.setMessage("该账户已加入商户圈！不能创建独立商户圈！");
				 inf.setBack(true);
					request.setAttribute("inf", inf);
					forwardPage = "inform";
					return (mapping.findForward(forwardPage));
		    }
		 }
		

		if (ag != null && ag.getLoginName() != null) {
			try {
			  	
				Coterie tempCoterie = (Coterie) coterieBiz
						.getCoterieById(coterie.getId());
				tempCoterie.setAgent(ag);
				tempCoterie.setName(coterie.getName().trim());
				tempCoterie.setRate(coterie.getRate());
				tempCoterie.setPartner(coterie.getPartner().trim());
				tempCoterie.setSignKey(coterie.getSignKey().trim());
				tempCoterie.setSignType(coterie.getSignType().trim());
				tempCoterie.setTempAgent(agent);
				tempCoterie.setStatus(coterie.getStatus());
				tempCoterie.setAllowMultcoterie(coterie.getAllowMultcoterie().trim());
				request.setAttribute("Coterie", tempCoterie);
				coterieBiz.saveCoterie(tempCoterie);

				// 创建商户圈同时把该用户加入该圈
				Coterie ce = coterieBiz.getCoterieByEmail(email);
				AgentCoterie tempAgentCoterie = new AgentCoterie();
				tempAgentCoterie.setAgent(ag);
				tempAgentCoterie.setCoterie(ce);
				tempAgentCoterie.setCreateDate(new Timestamp(System
						.currentTimeMillis()));
				tempAgentCoterie.setStatus(AgentCoterie.STATUS_YES);
				tempAgentCoterie.setCreditAmount(new BigDecimal(0));
				tempAgentCoterie.setFromDate(new Timestamp(System
						.currentTimeMillis()));
				tempAgentCoterie.setExpireDate(new Timestamp(System
						.currentTimeMillis()));
				tempAgentCoterie.setLeaveDays(0l);
				tempAgentCoterie.setMinAmount(new BigDecimal(0));
				tempAgentCoterie.setTransactionScope(AgentCoterie.TRANSACTIONSCOPE_0);
				tempAgentCoterie.setRepaymentType(AgentCoterie.REPAYMENT_TYPE_1);
				tempAgentCoterie.setStatus(0l);
				agentCoterieBiz.saveAgentCoterie(tempAgentCoterie);

				inf.setMessage("您已经成功添加了商户圈！");
				inf.setForwardPage("/agent/coterielist.do");
				inf.setParamId("thisAction");
				inf.setParamValue("list");
			} catch (Exception ex) {
				inf.setMessage("添加商户圈资料出错！错误信息是：" + ex.getMessage());
				inf.setBack(true);
			}
			request.setAttribute("inf", inf);
			forwardPage = "inform";
			return (mapping.findForward(forwardPage));

		} else {
			inf.setMessage("中间Email不存在！");
			inf.setBack(true);
			request.setAttribute("inf", inf);
			forwardPage = "inform";
			return (mapping.findForward(forwardPage));
		}
	}

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {

		String forwardPage = "";
		Coterie coterie = (Coterie) form;
		com.fordays.fdpay.agent.Agent ag = null;
		Inform inf = new Inform();
		ag = agentBiz.getAgentByEmail(coterie.getAgentEmail());
		com.fordays.fdpay.agent.Agent agent = agentBiz.getAgentByEmail(coterie
				.getTempAgentEmail());
		if (ag.getLoginName() == null) {
			inf.setMessage("中间帐户Email不存在！");
			inf.setBack(true);
			request.setAttribute("inf", inf);
			forwardPage = "inform";
			return (mapping.findForward(forwardPage));
		} else if (agent.getLoginName() == null) {
			inf.setMessage("临时帐户Email不存在！");
			inf.setBack(true);
			request.setAttribute("inf", inf);
			forwardPage = "inform";
			return (mapping.findForward(forwardPage));
		} else {
			try {
			 
				Coterie tempCoterie = (Coterie) coterieBiz.getCoterieById(coterie.getId());
				
				//判断是否修改了中间帐户 如修改同时修改圈内圈主帐号
	            if(ag.getId()!=tempCoterie.getAgent().getId()){
		           	List list=agentCoterieBiz.getAgentCoterieByAgent_Id(tempCoterie.getAgent().getId(), tempCoterie.getId());
		           	if(list.size()>0){
		           		AgentCoterie ac=(AgentCoterie)list.get(0);
			            ac.setAgent(ag);
						agentCoterieBiz.updateAgentCoterieInfo(ac);
		           	}
				}
				
				tempCoterie.setAgent(ag);
				tempCoterie.setName(coterie.getName().trim());
				tempCoterie.setRate(coterie.getRate());
				tempCoterie.setPartner(coterie.getPartner().trim());
				tempCoterie.setSignKey(coterie.getSignKey().trim());
				tempCoterie.setSignType(coterie.getSignType().trim());
				tempCoterie.setTempAgent(agent);
				tempCoterie.setStatus(coterie.getStatus());
				tempCoterie.setAllowMultcoterie(coterie.getAllowMultcoterie());
				// ag.setPartner(coterie.getPartner());
				// ag.setKey(coterie.getSignKey());
				// agentBiz.updateAgentKey(ag);
				request.setAttribute("Coterie", tempCoterie);
				coterieBiz.updateCoterieInfo(tempCoterie);
				
				inf.setMessage("您已经成功修改了商户圈商户！");
				inf.setForwardPage("/agent/coterielist.do");
				inf.setParamId("thisAction");
				inf.setParamValue("list");
			} catch (Exception ex) {
				inf.setMessage("修改商户圈出错！错误信息是：" + ex.getMessage());
				inf.setBack(true);
			}
			request.setAttribute("inf", inf);
			forwardPage = "inform";
			return (mapping.findForward(forwardPage));

		}
	}
}