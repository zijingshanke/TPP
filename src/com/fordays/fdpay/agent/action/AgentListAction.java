package com.fordays.fdpay.agent.action;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.fordays.fdpay.agent.Agent;
import com.fordays.fdpay.agent.AgentBalance;
import com.fordays.fdpay.agent.AgentListForm;
import com.fordays.fdpay.agent.biz.AgentBiz;
import com.fordays.fdpay.right.UserRightInfo;
import com.fordays.fdpay.transaction.TransactionBalance;
import com.fordays.fdpay.transaction.biz.TransactionBiz;
import com.fordays.fdpay.user.SysUser;
import com.neza.base.BaseAction;
import com.neza.base.DownLoadFile;
import com.neza.base.Inform;
import com.neza.exception.AppException;
import com.neza.tool.DateUtil;
import com.neza.utility.FileUtil;

public class AgentListAction extends BaseAction
{
	private AgentBiz agentBiz;
	private TransactionBiz transactionBiz;

	public SysUser getUserByURI(HttpServletRequest request){
		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute("URI");
		if(uri!=null&&uri.getUser()!=null)
			return uri.getUser();
		else{
			return null;
		}
	}
	// 选择商户
	public ActionForward select(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws AppException
	{
		String forwardPage = "";
		AgentListForm alf = (AgentListForm) form;
		alf.setList(agentBiz.getAgents(alf));
		request.setAttribute("alf", alf);
		forwardPage = "selectAgent";
		return (mapping.findForward(forwardPage));
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws AppException
	{
		AgentListForm alf = (AgentListForm) form;
		String forwardPage = "";
		int id = 0;
		Inform inf = new Inform();
		try
		{
			for (int i = 0; i < alf.getSelectedItems().length; i++)
			{
				id = alf.getSelectedItems()[i];
				if (id > 0)
					agentBiz.deleteAgent(id);
			}
			inf.setMessage("您已经成功删除了用户！");
			inf.setForwardPage("/agent/agentlist.do");
			inf.setParamId("thisAction");
			inf.setParamValue("list");
		}
		catch (Exception ex)
		{
			inf.setMessage("删除用户出错！错误信息是：" + ex.getMessage());
			inf.setBack(true);
		}
		request.setAttribute("inf", inf);
		forwardPage = "inform";
		return (mapping.findForward(forwardPage));
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws AppException
	{
		String forwardPage = "";
		AgentListForm alf = (AgentListForm) form;
		int id = 0;
		if (alf.getSelectedItems().length > 0)
		{
			id = alf.getSelectedItems()[0];
		}
		else
			id = alf.getAgentId();
		if (id > 0)
		{
			Agent agent = (Agent) agentBiz.getAgentById(id);
			agent.setThisAction("update");
			request.setAttribute("agent", agent);
		}
		else
			request.setAttribute("agent", new Agent());
		forwardPage = "editagent";
		return (mapping.findForward(forwardPage));
	}

	 public ActionForward editPassword(ActionMapping mapping, ActionForm form,
	 HttpServletRequest request, HttpServletResponse response)
	 throws AppException {
	 String forwardPage = "";
	 AgentListForm alf = (AgentListForm) form;
	 BigDecimal allowBalance=agentBiz.getAgentAllowBalance(alf.getAgentId());
	 System.out.println(allowBalance);
	 Agent agent = (Agent) agentBiz.getAgentById(alf.getAgentId());
	 agent.setThisAction("update");
	 request.setAttribute("agent", agent);
	 forwardPage = "editagentPassword";
	 return (mapping.findForward(forwardPage));
	 }
	 
	 public ActionForward editDefaultAmount(ActionMapping mapping, ActionForm form,
			 HttpServletRequest request, HttpServletResponse response)
	 throws AppException {
		 String forwardPage = "";
		 AgentListForm alf = (AgentListForm) form;
		 Agent agent = (Agent) agentBiz.getAgentById(alf.getAgentId());
		 agent.setThisAction("update");
		 request.setAttribute("agent", agent);
		 forwardPage = "editDefaultAmount";
		 return (mapping.findForward(forwardPage));
	 }
	 
	 public ActionForward editAccountStatus(ActionMapping mapping, ActionForm form,
			 HttpServletRequest request, HttpServletResponse response)
	 throws AppException {
		 String forwardPage = "";
		 AgentListForm alf = (AgentListForm) form;
		 Agent agent = (Agent) agentBiz.getAgentById(alf.getAgentId());
		 agent.setThisAction("update");
		 request.setAttribute("agent", agent);
		 forwardPage = "editAccountStatus";
		 return mapping.findForward(forwardPage);
	 }

	public ActionForward editPersonal(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws AppException
	{
		String forwardPage = "";
		AgentListForm alf = (AgentListForm) form;
		int id = 0;
		if (alf.getSelectedItems().length > 0)
		{
			id = alf.getSelectedItems()[0];
		}
		else
			id = alf.getAgentId();
		if (id > 0)
		{
			Agent agent = (Agent) agentBiz.getAgentById(id);
			agent.setThisAction("update");
			request.setAttribute("agent", agent);
		}
		else
			request.setAttribute("agent", new Agent());
		forwardPage = "editagentpersonal";
		return (mapping.findForward(forwardPage));
	}
	public ActionForward updateBalance(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException{
		Agent agent=agentBiz.getAgentById(Long.parseLong(request.getParameter("agentId")));
		transactionBiz.statistAgentTransactionBalance(agent);
		AgentListForm ulf=new AgentListForm();
		ulf.setAgentId((int) agent.getId());
		return view(mapping,ulf,request,response);
	}
	
	public ActionForward view(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws AppException
	{
		String forwardPage = "";
		AgentListForm ulf = (AgentListForm) form;
		SysUser user=this.getUserByURI(request);
		if(user!=null){
			Agent agent = (Agent) agentBiz.getAgentById(ulf.getAgentId());
			if (agent != null)
				agent.setThisAction("view");
			//AgentBalance agentBalance=agentBiz.getAgentBalance(request.getSession().getId(),(int)user.getUserId(),agent.getId());
			TransactionBalance transactionBalance=transactionBiz.getLatestTransationBalance(agent);
			BigDecimal balance=new BigDecimal(0);
			BigDecimal creditBalance=new BigDecimal(0);
			BigDecimal notallowBalance=new BigDecimal(0);
			if(transactionBalance!=null){
				if(transactionBalance.getBalance()!=null)
					balance=transactionBalance.getBalance();
				if(transactionBalance.getCreditBalance()!=null)
					creditBalance=transactionBalance.getCreditBalance();
				if(transactionBalance.getNotallowBalance()!=null)
					notallowBalance=transactionBalance.getNotallowBalance();
			}
			BigDecimal statAllowBalance=balance.add(creditBalance).add(notallowBalance);
			
			request.setAttribute("balance", balance);
			request.setAttribute("creditBalance", creditBalance);
			request.setAttribute("notallowBalance", notallowBalance);
			request.setAttribute("statAllowBalance", statAllowBalance);
			request.setAttribute("agent", agent);
			forwardPage = "viewagent";
		}else{
			request.getSession().invalidate();
			return mapping.findForward("exit");
		}
		return (mapping.findForward(forwardPage));
	}

	public ActionForward listAgentBalance(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws AppException
	{
		String forwardPage = "";
		SysUser user=this.getUserByURI(request);
	
		if(user!=null){
			AgentListForm alf = (AgentListForm) form;
			
			if (alf == null)
				alf = new AgentListForm();
			List<Agent> list = agentBiz.getList(alf);
		
			if(alf.getDownloadDate()!=null&&!"".equals(alf.getDownloadDate())){
			
			for(int i=0;i<list.size();i++)
			{
			    Agent agent=(Agent)list.get(i);
			    AgentBalance ab=agentBiz.getAgentBalance(agent.getId(), alf.getDownloadDate());
			
				agent.setAllowBalance(ab.getAllowBalance());
				agent.setNotallowBalance(ab.getNotAllowBalance());
				agent.setCreditBalance(ab.getCreditBalance());
				}
			}
			alf.setList(list);
//			alf.addSumField(1, "allowBalance");
//			alf.addSumField(2, "notAllowBalance");
			request.setAttribute("alf", alf);
			forwardPage = "listagentBalance";
		}else{
			request.getSession().invalidate();
			return mapping.findForward("exit");
		}
		return (mapping.findForward(forwardPage));
	}

	
	public ActionForward listAgent(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws AppException
	{
		String forwardPage = "";
		SysUser user=this.getUserByURI(request);
	
		if(user!=null){
			AgentListForm alf = (AgentListForm) form;
			
			if (alf == null)
				alf = new AgentListForm();
			List list = agentBiz.getList(alf);
			alf.setList(list);
//			alf.addSumField(1, "allowBalance");
//			alf.addSumField(2, "notAllowBalance");
			
			request.setAttribute("alf", alf);
			forwardPage = "listagent";
		}else{
			request.getSession().invalidate();
			return mapping.findForward("exit");
		}
		return (mapping.findForward(forwardPage));
	}

	public ActionForward listAgentRelation(ActionMapping mapping,
	    ActionForm form, HttpServletRequest request, HttpServletResponse response)
	    throws AppException
	{
		String forwardPage = "";

		AgentListForm alf = (AgentListForm) form;
		if (alf == null)
			alf = new AgentListForm();
		
		if (alf.getSelectedItems() != null)
		{
			int[] items = new int[alf.getSelectedItems().length];
			for (int i = 0; i < alf.getSelectedItems().length; i++)
			{
				items[i] = 0;
			}
			alf.setSelectedItems(items);
		}
		alf.setList(agentBiz.getAgents(alf));
		request.setAttribute("alf", alf);
		forwardPage = "listagentrelation";
		return (mapping.findForward(forwardPage));
	}

	public ActionForward download(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws AppException
	{
		SysUser user=this.getUserByURI(request);
		if(user!=null){
			AgentListForm alf = (AgentListForm) form;
			
			ArrayList<ArrayList<Object>> lists = agentBiz.getAgentAll(request.getSession().getId().toString(),(int)user.getUserId(),alf);
			String outFileName = DateUtil.getDateString("yyyyMMddhhmmss") + ".csv";
			String outText = FileUtil.createCSVFile(lists);
			try
			{
				outText = new String(outText.getBytes("UTF-8"));
			}
			catch (Exception ex)
			{
	
			}
			DownLoadFile df = new DownLoadFile();
			df.performTask(response, outText, outFileName, "GBK");
			return null;
		}else{
			request.getSession().invalidate();
			return mapping.findForward("exit");
		}
		
	}

	public void setAgentBiz(AgentBiz agentBiz) throws AppException
	{
		this.agentBiz = agentBiz;
	}

	public AgentBiz getAgentBiz()
	{
		return agentBiz;
	}
	public TransactionBiz getTransactionBiz() {
		return transactionBiz;
	}
	public void setTransactionBiz(TransactionBiz transactionBiz) {
		this.transactionBiz = transactionBiz;
	}

}
