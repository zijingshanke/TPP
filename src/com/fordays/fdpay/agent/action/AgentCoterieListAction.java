package com.fordays.fdpay.agent.action;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.fordays.fdpay.agent.AgentCoterie;
import com.fordays.fdpay.agent.AgentCoterieListForm;
import com.fordays.fdpay.agent.Coterie;
import com.fordays.fdpay.agent.biz.AgentBiz;
import com.fordays.fdpay.agent.biz.AgentCoterieBiz;
import com.fordays.fdpay.agent.biz.CoterieBiz;
import com.neza.base.BaseAction;
import com.neza.base.Inform;
import com.neza.exception.AppException;

public class AgentCoterieListAction extends BaseAction {
	
	private AgentCoterieBiz agentCoterieBiz;
	private AgentBiz agentBiz;
	private CoterieBiz coterieBiz;

	
	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws AppException{
		String forwardPage = "";
		AgentCoterieListForm ulf = (AgentCoterieListForm) form;
		
		
		AgentCoterie agentCoterie = (AgentCoterie) agentCoterieBiz.getAgentCoterieById(ulf.getAgentCoterieId());
		agentCoterie.setThisAction("view");
			request.setAttribute("agentCoterie", agentCoterie);
			
		
		forwardPage = "viewagentcoterie";
		return (mapping.findForward(forwardPage));
	}

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws AppException
	{
		AgentCoterie agentCoterie = new AgentCoterie();
		agentCoterie.setThisAction("insert");
		agentCoterie.setStatus(agentCoterie.STATUS_NO);
		agentCoterie.setRepaymentType(agentCoterie.REPAYMENT_TYPE_1);
		agentCoterie.setTransactionScope(agentCoterie.TRANSACTIONSCOPE_0);
		Coterie c=coterieBiz.getCoterieById(Long.parseLong(request.getParameter("coterieId")));
		request.setAttribute("agentCoterie", agentCoterie);
		request.setAttribute("coterie", c);
		
		String forwardPage = "editagentcoterie";
		return (mapping.findForward(forwardPage));
	}
	public ActionForward addAll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws AppException
			{
		AgentCoterie agentCoterie = new AgentCoterie();
		agentCoterie.setThisAction("insertAll");
		
		Coterie c=coterieBiz.getCoterieById(Long.parseLong(request.getParameter("coterieId")));
		request.setAttribute("agentCoterie", agentCoterie);
		request.setAttribute("coterie", c);
		
		String forwardPage = "addAllAgentCoterie";
		return (mapping.findForward(forwardPage));
			}

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws AppException {
		String forwardPage = "";
 
		AgentCoterieListForm alf = (AgentCoterieListForm) form;
		if (alf == null)
			alf = new AgentCoterieListForm();
		alf.setList(agentCoterieBiz.getAgentCoterieByCoterieId(alf));
		
		if(alf.getSelectedItems()!=null){
			int [] items=new int[alf.getSelectedItems().length];
			for(int i=0;i<alf.getSelectedItems().length;i++){
				items[i]=0;
			}
			alf.setSelectedItems(items);
		}
		
		Coterie c=coterieBiz.getCoterieById(alf.getCoterieId());
		request.setAttribute("coterieName",c.getName());
		request.setAttribute("alf", alf);
		forwardPage = "listagentcoterie";
		return (mapping.findForward(forwardPage));
	}
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws AppException
		{
		AgentCoterieListForm alf = (AgentCoterieListForm) form;
			String forwardPage = "";
			int id = 0;
			String err = "";
			int cont = 0;
			String err1 = "";
			int cont1 = 0;
			Inform inf = new Inform();
			Coterie c=coterieBiz.getCoterieById(alf.getCoterieId());
			try
			{
				for (int i = 0; i < alf.getSelectedItems().length; i++)
				{
										
				id = alf.getSelectedItems()[i];
				
				if (id > 0){
					AgentCoterie ag=agentCoterieBiz.getAgentCoterieById(id);
					String email=ag.getAgent().getLoginName();
					if(c.getAgent().getId()==ag.getAgent().getId()){
						err=err+"【"+ email + "】 ";
						cont++;
					}else{
						
						agentCoterieBiz.deleteAgentCoterie(id);
						err1 = err1 +"【"+ email + "】 ，";
						cont1++;
					}
				 }
				
				}
				String msg="";
				if(cont>0){
					msg="\n 用户："+err+"是圈主帐号无法删除!";
				}
				inf.setMessage("您已经成功删除了用户"+err1+"共"+cont1+"条\n "+msg);
				inf.setForwardPage("/agent/agentcoterielist.do");
				inf.setParamId("thisAction");
				inf.setParamValue("list");				
			}
			catch (Exception ex)
			{
				inf.setMessage("删除商户圈商户出错！错误信息是：" + ex.getMessage());
				inf.setBack(true);
			}
			request.setAttribute("inf", inf);
			forwardPage = "inform";
			return (mapping.findForward(forwardPage));
		}

	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws AppException
		{
		AgentCoterieListForm alf = (AgentCoterieListForm) form;
		long	id = alf.getSelectedItems()[0];
		try
		{
			String forwardPage = "editagentcoterie";
			AgentCoterie agentCoterie=agentCoterieBiz.getAgentCoterieById(id);
			agentCoterie.setThisAction("update");
			Coterie coterie=coterieBiz.getCoterieById(agentCoterie.getCoterie().getId());
			
			request.setAttribute("agentCoterie", agentCoterie);
			request.setAttribute("coterie", coterie);
			
		
			return (mapping.findForward(forwardPage));
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		
		return list(mapping,form,request,response);
		}
	

	public AgentCoterieBiz getAgentCoterieBiz() {
		return agentCoterieBiz;
	}


	public void setAgentCoterieBiz(AgentCoterieBiz agentCoterieBiz) {
		this.agentCoterieBiz = agentCoterieBiz;
	}


	public AgentBiz getAgentBiz() {
		return agentBiz;
	}


	public void setAgentBiz(AgentBiz agentBiz) {
		this.agentBiz = agentBiz;
	}


	public CoterieBiz getCoterieBiz() {
		return coterieBiz;
	}


	public void setCoterieBiz(CoterieBiz coterieBiz) {
		this.coterieBiz = coterieBiz;
	}

}
