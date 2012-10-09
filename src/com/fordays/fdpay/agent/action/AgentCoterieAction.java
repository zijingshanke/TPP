package com.fordays.fdpay.agent.action;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.fordays.fdpay.agent.AgentCoterie;
import com.fordays.fdpay.agent.biz.AgentBiz;
import com.fordays.fdpay.agent.biz.AgentCoterieBiz;
import com.fordays.fdpay.agent.biz.CoterieBiz;
import com.neza.base.BaseAction;
import com.neza.base.Inform;
import com.neza.exception.AppException;

public class AgentCoterieAction extends BaseAction
{
	private AgentCoterieBiz agentCoterieBiz;
	private AgentBiz agentBiz;
	private CoterieBiz coterieBiz;

	public void setAgentCoterieBiz(AgentCoterieBiz agentCoterieBiz)
	    throws AppException
	{
		this.agentCoterieBiz = agentCoterieBiz;
	}
	//添加商户圈中商户
	public ActionForward insert(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws AppException
	{
		String forwardPage = "";
		AgentCoterie agentCoterie = (AgentCoterie) form;
		com.fordays.fdpay.agent.Agent ag = null;
		com.fordays.fdpay.agent.Coterie ct = null;
		Inform inf = new Inform();
		String email = agentCoterie.getAgentEmail();
		ag = agentBiz.getAgentByEmail(email);
		long coterieId = Long.parseLong(request.getParameter("coterieId"));
		ct = coterieBiz.getCoterieById(coterieId);
		List list = agentCoterieBiz.getAgentCoterieByCoterieId(ct.getId());
	
		
		if (ag.getLoginName() == null)
		{
			inf.setMessage("商户Email不存在！");
			inf.setBack(true);
			request.setAttribute("inf", inf);
			inf.setForwardPage("/agent/agentcoterielist.do?thisAction=add&coterieId="+coterieId);
			forwardPage = "inform";
			return (mapping.findForward(forwardPage));
		}
		else if (list.size() > 0)
		{
			
			for (int i = 0; i < list.size(); i++)
			{
				AgentCoterie ac = (AgentCoterie) list.get(i);
		
				
				if (ag.getLoginName().equals(ac.getAgent().getLoginName()))
				{
					inf.setMessage("该商户在商户圈中已经存在！");
					inf.setForwardPage("/agent/agentcoterielist.do?thisAction=add&coterieId="+coterieId);
					request.setAttribute("inf", inf);
					forwardPage = "inform";
					return (mapping.findForward(forwardPage));
				}
				
			}
		}
		int  count= agentCoterieBiz.getAgentCoterieByAgent_Id(ag.getId());
		boolean check= coterieBiz.checkCoerieByAllowMult(ag.getId());
		if(check){
			inf.setMessage("该账户已经加入了独立的商户圈，不能加入："+ct.getName()+"！");
			inf.setForwardPage("/agent/agentcoterielist.do?thisAction=add&coterieId="+coterieId);
			request.setAttribute("inf", inf);
			forwardPage = "inform";
			return (mapping.findForward(forwardPage));
			
		}else{
		
			if(ct.getAllowMultcoterie().equals("0")&&count>0){
				inf.setMessage("该账户已加入商户圈！不能加入独立商户圈！！");
				inf.setForwardPage("/agent/agentcoterielist.do?thisAction=add&coterieId="+coterieId);
				request.setAttribute("inf", inf);
				forwardPage = "inform";
				return (mapping.findForward(forwardPage));
			}
		}

		try
		{
			AgentCoterie tempAgentCoterie = new AgentCoterie();
			tempAgentCoterie.setAgent(ag);
			tempAgentCoterie.setCoterie(ct);
			tempAgentCoterie.setCreateDate(new Timestamp(System.currentTimeMillis()));
			tempAgentCoterie.setStatus(AgentCoterie.STATUS_YES);
			//tempAgentCoterie.setCreditAmount(agentCoterie.getCreditAmount());
			tempAgentCoterie.setFromDate(agentCoterie.getFromDate());
			tempAgentCoterie.setExpireDate(agentCoterie.getExpireDate());
			tempAgentCoterie.setLeaveDays(agentCoterie.getLeaveDays());
			tempAgentCoterie.setMinAmount(agentCoterie.getMinAmount());
			tempAgentCoterie.setTransactionScope(agentCoterie.getTransactionScope());
			tempAgentCoterie.setRepaymentType(agentCoterie.getRepaymentType());
			tempAgentCoterie.setStatus(agentCoterie.getStatus());
			
			agentCoterieBiz.saveAgentCoterie(tempAgentCoterie);
			// request.setAttribute("agentCoterie", tempAgentCoterie);
			inf.setMessage("您已经成功添加了商户！");
			inf.setForwardPage("/agent/coterielist.do");
			inf.setParamId("thisAction");
			inf.setParamValue("list");

		}
		catch (Exception ex)
		{
			inf.setMessage("添加商户圈商户资料出错！错误信息是：" + ex.getMessage());
			inf.setBack(true);
		}
		request.setAttribute("inf", inf);
		forwardPage = "inform";
		return (mapping.findForward(forwardPage));

	}
//批量添加商户圈中商户
	public ActionForward insertAll(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws AppException
	{
		String forwardPage = "";
		AgentCoterie agentCoterie = (AgentCoterie) form;
		String allAgentEmail = agentCoterie.getAgentEmail();
		Inform inf = new Inform();
		String[] str = allAgentEmail.split("[,;\\n\\r]");
		String[] agentEmail = new String[str.length];
		String err = "";
		int cont = 0;
		String err1 = "";
		int cont1 = 0;
		com.fordays.fdpay.agent.Agent ag = null;
		com.fordays.fdpay.agent.Coterie ct = null;
		long coterieId = Long.parseLong(request.getParameter("coterieId"));
		ct = coterieBiz.getCoterieById(coterieId);
		String err2="";
		int cont2=0;
		String err3="";
		int cont3=0;
		for (int i = 0; i < str.length; i++)
		{
			
			List list = agentCoterieBiz.getAgentCoterieByCoterieId(ct.getId());
			agentEmail[i] = str[i].trim();

			String email = agentEmail[i];
			ag = agentBiz.getAgentByEmail(email);

			if (ag != null && ag.getLoginName() != null)
			{
				
				if (list.size() > 0)
				{
					for (int j = 0; j < list.size(); j++)
					{
						AgentCoterie ac = (AgentCoterie) list.get(j);
						if (ag != null && ag.getLoginName() != null
						    && ag.getLoginName().equalsIgnoreCase(ac.getAgent().getLoginName()))
						{
							ag = null;
							err = err +"【"+ email + "】 ，";
							cont++;
						}else if(ag!=null){
							int  count= agentCoterieBiz.getAgentCoterieByAgent_Id(ag.getId());
							boolean check= coterieBiz.checkCoerieByAllowMult(ag.getId());
							if(check){
								ag = null;
								err2 = err2 +"【"+ email + "】 ，";
								cont2++;
							}else{
								if(ct.getAllowMultcoterie().equals("0")&&count>0){
									ag = null;
									err3 = err3 +"【"+ email + "】 ，";
									cont3++;
								}
							}
						}
					}
				}
		
				try
				{
					if (ag != null)
					{
						AgentCoterie tempAgentCoterie = new AgentCoterie();
						tempAgentCoterie.setAgent(ag);
						tempAgentCoterie.setCoterie(ct);
						tempAgentCoterie.setCreateDate(new Timestamp(System
						    .currentTimeMillis()));
						tempAgentCoterie.setStatus(AgentCoterie.STATUS_YES);
						tempAgentCoterie.setCreditAmount(new BigDecimal(0));
						tempAgentCoterie.setFromDate(new Timestamp(System
							    .currentTimeMillis()));
						tempAgentCoterie.setExpireDate(new Timestamp(System
							    .currentTimeMillis()));
						tempAgentCoterie.setLeaveDays(10l);
						tempAgentCoterie.setMinAmount(new BigDecimal(0));
						tempAgentCoterie.setTransactionScope(AgentCoterie.TRANSACTIONSCOPE_0);
						tempAgentCoterie.setRepaymentType(AgentCoterie.REPAYMENT_TYPE_1);
						tempAgentCoterie.setStatus(0l);
						agentCoterieBiz.saveAgentCoterie(tempAgentCoterie);
					}
				}
				catch (Exception ex)
				{
					ex.getMessage();
				}
			}
			else
			{
				err1 = err1 +"【"+ email + "】 ，";
				cont1++;
			}
		}
		
		inf.setMessage("添加完成！\n 其中"+err+"共"+cont+"条，重复添加！\n其中"+err1+"共"+cont1+"条，是不存在的商户！\n其中"+err2+"共"+cont2+
				"条，已经加入了独立的商户圈！\n其中"+err3+"共"+cont3+"条，已加入商户圈！不能加入该独立商户圈");
		inf.setForwardPage("/agent/agentcoterielist.do?thisAction=list");
		request.setAttribute("inf", inf);
		forwardPage = "inform";
		return mapping.findForward(forwardPage);
	}
	//update  商户圈中商户
	public ActionForward update(ActionMapping mapping, ActionForm form,
		    HttpServletRequest request, HttpServletResponse response)
		    throws AppException
		{
		Inform inf = new Inform();
		AgentCoterie agentCoterie = (AgentCoterie) form;
		AgentCoterie tempAgentCoterie= agentCoterieBiz.getAgentCoterieById(agentCoterie.getId());
		tempAgentCoterie.setCreditAmount(agentCoterie.getCreditAmount());
		tempAgentCoterie.setLeaveDays(agentCoterie.getLeaveDays());
		tempAgentCoterie.setExpireDate(agentCoterie.getExpireDate());
		tempAgentCoterie.setFromDate(agentCoterie.getFromDate());
		tempAgentCoterie.setMinAmount(agentCoterie.getMinAmount());
		tempAgentCoterie.setTransactionScope(agentCoterie.getTransactionScope());
		tempAgentCoterie.setRepaymentType(agentCoterie.getRepaymentType());
		tempAgentCoterie.setStatus(agentCoterie.getStatus());
		
		long i= agentCoterieBiz.updateAgentCoterieInfo(tempAgentCoterie);
		if(i==0){
			inf.setMessage("修改成功！");
			inf.setForwardPage("/agent/agentcoterielist.do?thisAction=list");
			
		}else{
			inf.setMessage("修改失败！");
			inf.setForwardPage("/agent/agentcoterielist.do?thisAction=list");
		}
		request.setAttribute("inf", inf);
		String forwardPage = "inform";
		return (mapping.findForward(forwardPage));
	}
	
	

	public AgentBiz getAgentBiz()
	{
		return agentBiz;
	}

	public void setAgentBiz(AgentBiz agentBiz)
	{
		this.agentBiz = agentBiz;
	}

	public CoterieBiz getCoterieBiz()
	{
		return coterieBiz;
	}

	public void setCoterieBiz(CoterieBiz coterieBiz)
	{
		this.coterieBiz = coterieBiz;
	}

	public AgentCoterieBiz getAgentCoterieBiz()
	{
		return agentCoterieBiz;
	}
}