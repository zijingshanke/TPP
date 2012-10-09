package com.fordays.fdpay.agent.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.fordays.fdpay.agent.AgentCoterie;
import com.fordays.fdpay.agent.Coterie;
import com.fordays.fdpay.agent.CoterieListForm;
import com.fordays.fdpay.agent.biz.AgentBiz;
import com.fordays.fdpay.agent.biz.AgentCoterieBiz;
import com.fordays.fdpay.agent.biz.CoterieBiz;
import com.neza.base.BaseAction;
import com.neza.base.Inform;
import com.neza.exception.AppException;

public class CoterieListAction extends BaseAction {
	private AgentCoterieBiz agentCoterieBiz;
	private CoterieBiz coterieBiz;
	private AgentBiz agentBiz;
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
	
	
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws AppException{
		
		String forwardPage = "";
		CoterieListForm clf = (CoterieListForm) form;
		
		long id = 0;
		if (clf.getSelectedItems().length > 0) {
			id = clf.getSelectedItems()[0];
		} else
			id = clf.getCoterieId();
		if (id > 0) {
			Coterie coterie = (Coterie) coterieBiz.getCoterieById(id);
			if(coterie.getAgent()!=null){
				coterie.setAgentEmail(coterie.getAgent().getLoginName());
			}
			else{
				coterie.setAgentEmail("");
			}
			if(coterie.getTempAgent()!=null){
				
				coterie.setTempAgentEmail(coterie.getTempAgent().getLoginName());
			}else{
				coterie.setTempAgentEmail("");
			}
			int count=agentCoterieBiz.getAgentCoterieByCoterieId(coterie.getId()).size();
			request.setAttribute("count",count);
			coterie.setThisAction("update");
			request.setAttribute("coterie",  coterie);
		} else
			request.setAttribute("coterie", new Coterie());
		forwardPage = "editcoterie";
		return (mapping.findForward(forwardPage));
	}

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws AppException
	{
		Coterie coterie = new Coterie();
		coterie.setStatus(coterie.STATUS_YES);
		coterie.setAllowMultcoterie(coterie.ALLOW_MULTCOTERIE_1);
		coterie.setThisAction("insert");
		request.setAttribute("coterie", coterie);
		String forwardPage = "editcoterie";
		return (mapping.findForward(forwardPage));
	}

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws AppException {
		String forwardPage = "";

		CoterieListForm clf = (CoterieListForm) form;
		if (clf == null)
			clf = new CoterieListForm();
		
		
		List list=coterieBiz.getCoteries(clf);
/*		for(int j=0;j<list.size();j++){.
 * 
			Coterie cot=(Coterie)list.get(j).;
			Agent ag=agentBiz.getAgentById(cot.getTempAgentId());
			cot.setTempAgentEmail(ag.getLoginName());
		}*/
		clf.setList(list);
		if(clf.getSelectedItems()!=null){
			int [] items=new int[clf.getSelectedItems().length];
			for(int i=0;i<clf.getSelectedItems().length;i++){
				items[i]=0;
			}
			clf.setSelectedItems(items);
		}
		request.setAttribute("clf", clf);
		forwardPage = "listcoterie";
		return (mapping.findForward(forwardPage));
	}
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws AppException
		{
		CoterieListForm alf = (CoterieListForm) form;
		
			String forwardPage = "";
			int id = 0;
			int count=0;
			long agentid=0;
			Inform inf = new Inform();
			ArrayList list=new ArrayList<Coterie>();
			String coterieName="  ";
				try
				{
					for (int i = 0; i < alf.getSelectedItems().length; i++)
					{
						id = alf.getSelectedItems()[i];
						if (id > 0){
							Coterie ce=coterieBiz.getCoterieById(id);
							List ac=agentCoterieBiz.getAgentCoterieByCoterieId(id);
							for(int j=0;j<ac.size();j++){
								AgentCoterie	ace=(AgentCoterie)ac.get(j);
								if(ce.getAgent().getId()!=ace.getAgent().getId()){
									count++;
								}else{
									agentid=ace.getId();
								}
							}
							if(count>0)
							{
								list.add(coterieBiz.getCoterieById(id).getName());
							}else{
								if(agentid>0){
								agentCoterieBiz.deleteAgentCoterie(agentid);
								}
								coterieBiz.deleteCoterie(id);
								inf.setMessage("成功删除此商户圈！");
							}
						}
					}
					if(list.size()>0){
						for(int j=0;j<list.size();j++)
						{
							coterieName+=list.get(j).toString()+" ";
						}
						inf.setMessage("商户圈�："+ coterieName +"内还存在加入的商户");
					}
					inf.setForwardPage("/agent/coterielist.do");
					inf.setParamId("thisAction");
					inf.setParamValue("list");
				}
				catch (Exception ex)
				{
					inf.setMessage("删除商户圈错误" + ex.getMessage());
					inf.setBack(true);
				}
			request.setAttribute("inf", inf);
			forwardPage = "inform";
			return (mapping.findForward(forwardPage));
		}

	public AgentCoterieBiz getAgentCoterieBiz() {
		return agentCoterieBiz;
	}

	public void setAgentCoterieBiz(AgentCoterieBiz agentCoterieBiz) {
		this.agentCoterieBiz = agentCoterieBiz;
	}

}
