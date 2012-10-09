package com.fordays.fdpay.agent.action;


import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.fordays.fdpay.agent.AccountListForm;
import com.fordays.fdpay.agent.Agent;
import com.fordays.fdpay.agent.AgentAdderssListForm;
import com.fordays.fdpay.agent.AgentContactListForm;
import com.fordays.fdpay.agent.CertInfo;
import com.fordays.fdpay.agent.biz.AccountBiz;
import com.fordays.fdpay.agent.biz.AgentAddressBiz;
import com.fordays.fdpay.agent.biz.AgentBiz;
import com.fordays.fdpay.agent.biz.AgentContactBiz;
import com.fordays.fdpay.agent.biz.CoterieBiz;
import com.fordays.fdpay.right.UserRightInfo;
import com.fordays.fdpay.user.SysUser;
import com.neza.base.BaseAction;
import com.neza.base.Inform;
import com.neza.exception.AppException;

public class AgentAction extends BaseAction
{
	private AgentBiz agentBiz;
	private AccountBiz accountBiz;
	private CoterieBiz coterieBiz;
	private AgentAddressBiz agentAddressBiz;
	private AgentContactBiz agentContactBiz;
	

	//修改个人信息
	public ActionForward updatePersonal(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException
	{
  
		String forwardPage = "";
		Agent agent = (Agent) form;
		Inform inf = new Inform();
		try
		{
			agentBiz.updateAgentPersonal(agent);
			request.setAttribute("agent", agent);

			inf.setMessage("修改用户信息成功！");
			inf.setForwardPage("/agent/agentlist.do");
			inf.setParamId("thisAction");
			inf.setParamValue("edit");

		} catch (Exception ex)
		{
			inf.setMessage("修改用户信息失败！" + ex.getMessage());
			inf.setBack(true);
		}
		request.setAttribute("inf", inf);
		forwardPage = "inform";
		return (mapping.findForward(forwardPage));
	}

	
	//修改个人预设金额
	public ActionForward updateDefaultAmount(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException{

		String forwardPage = "";
		Agent agent = (Agent) form;
		Inform inf = new Inform();
		try
		{
			agentBiz.updateDefaultAmount(agent);
			request.setAttribute("agent", agent);

			inf.setMessage("修改用户预设金额成功！");
			inf.setForwardPage("/agent/agentlist.do");
			inf.setParamId("thisAction");
			inf.setParamValue("edit");

		} catch (Exception ex)
		{
			inf.setMessage("修改用户信息失败！" + ex.getMessage());
			inf.setBack(true);
		}
		request.setAttribute("inf", inf);
		forwardPage = "inform";
		return (mapping.findForward(forwardPage));
	}
	
	//修改密码相关信息
	public ActionForward updatePassword(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws AppException
	{
		String forwardPage = "";
		Agent agent = (Agent) form;
		Inform inf = new Inform();
		try
		{	
			agentBiz.updatePassword(agent);
			request.setAttribute("agent", agent);
			inf.setMessage("修改用户密码成功！");
			inf.setForwardPage("/agent/agentlist.do");
			inf.setParamId("thisAction");
			inf.setParamValue("edit");
			
		} catch (Exception ex)
		{
			inf.setMessage("修改用户密码失败！" + ex.getMessage());
			inf.setBack(true);
		}
		request.setAttribute("inf", inf);
		forwardPage = "inform";
		return (mapping.findForward(forwardPage));
	}
	//修改账户状态
	public ActionForward updateAccountStatus(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws AppException{
		String forwardPage = "";
		Agent agent = (Agent) form;
		Inform inf = new Inform();
		try{
			
			agentBiz.updateAccountStatus(agent);
			request.setAttribute("agent", agent);
			inf.setMessage("修改账户状态成功！");
			inf.setForwardPage("/agent/agentlist.do");
			inf.setParamId("thisAction");
			inf.setParamValue("edit");
		}catch (Exception e) {
			inf.setMessage("修改账户状态失败！" + e.getMessage());
			inf.setBack(true);
		}
		request.setAttribute("inf", inf);
		forwardPage = "inform";
		return (mapping.findForward(forwardPage));
	}
	//银行卡信息
	public ActionForward listAccount(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException
	{
		String agentId = request.getParameter("agentId");
		AccountListForm alf = new AccountListForm();
		List list = accountBiz.getAccounts(new Long(agentId));
		alf.setList(list);
		request.setAttribute("alf", alf);
		return mapping.findForward("listaccount");
	}
	//联系人地址信息�
	public ActionForward listAgentAddress(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException
	{
		String agentId = request.getParameter("agentId");
		AgentAdderssListForm alf = new AgentAdderssListForm();
		List list = agentAddressBiz.getAgentAddersss(new Long(agentId));
		alf.setList(list);
		request.setAttribute("alf", alf);
		return mapping.findForward("listagentaddress");
	}
	//联系人信息�
	public ActionForward listAgentContact(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException
	{
		String agentId = request.getParameter("agentId");
		AgentContactListForm alf = new AgentContactListForm();
		List list = agentContactBiz.getAgentContacts(new Long(agentId));
		alf.setList(list);
		request.setAttribute("alf", alf);
		return mapping.findForward("listagentcontact");
	}
	// 证书信息
	public ActionForward showCertificate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException{
			String agentId=request.getParameter("agentId");
			Agent agent=agentBiz.getAgentById(Long.parseLong(agentId));
			CertInfo certInfo=agent.getCertInfo();
			request.setAttribute("certInfo", certInfo);
		return mapping.findForward("viewCertInfo");
	}
	//资金处理页面
	public ActionForward fundsTreatment(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException{
		String type=request.getParameter("type");
		String agentId=request.getParameter("agentId");
		Agent agent=agentBiz.getAgentById(Long.parseLong(agentId));
		
//		BigDecimal balance=agentBiz.getAgentAllowBalance(agent.getId());
//		BigDecimal notAllowBalance=agentBiz.getAgentNotAllowBalance(agent.getId());
//		BigDecimal creditBalance=agentBiz.getAgentCreditBalance(agent.getId());
//		request.setAttribute("balance", balance);
//		request.setAttribute("notAllowBalance", notAllowBalance);
//		request.setAttribute("creditBalance", creditBalance);
		request.setAttribute("type", type);
		request.setAttribute("agent", agent);
		saveToken(request);
		return mapping.findForward("editMoney");
		
	}
//Freeze冻结
	public ActionForward freezeMoney(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException{
		String forwardPage = "";
		Inform inf = new Inform();
		Agent agent=(Agent)form;
		 if (isTokenValid(request, true)){
			try {
				SysUser user=this.getUserByURI(request);
				//AgentBalance ab=agentBiz.getAgentBalanceByLoginName(request.getSession().getId(),(int)user.getUserId(),agent.getId());
				Agent tempAgent=agentBiz.getAgentById(agent.getId());
				agentBiz.synallowBalance(tempAgent);
				if(agentBiz.getAgentAllowBalance(tempAgent.getId()).compareTo(agent.getTempAmount())==-1){
					inf.setMessage("输入金额比商户可用余额大！");
					inf.setForwardPage("/agent/agent.do?thisAction=fundsTreatment&type=1&agentId="+agent.getId());
				}else{
					agentBiz.moveAllowBalanceToNotallowBalance(tempAgent, agent.getTempAmount(),agent.getTransactionMark());
					inf.setMessage("冻结资金成功！");
					inf.setForwardPage("/agent/agentlist.do?thisAction=view&agentId="+agent.getId());
				}
			} catch (Exception e) {
				inf.setMessage("冻结资金失败！" + e.getMessage());
				inf.setBack(true);
			}
			
		 }else{
			inf.setMessage("重复提交！");
			inf.setForwardPage("/agent/agentlist.do?thisAction=listAgent&sort=3");
		 }
			request.setAttribute("inf", inf);
			forwardPage = "inform";
			return (mapping.findForward(forwardPage));
	}
//Thaw解冻
	public ActionForward thawMoney(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException{
		String forwardPage = "";
		Inform inf = new Inform();
		Agent agent=(Agent)form;
		 if (isTokenValid(request, true)){
				try {
					SysUser user=this.getUserByURI(request);
					//AgentBalance ab=agentBiz.getAgentBalanceByLoginName(request.getSession().getId(),(int)user.getUserId(),agent.getId());
					Agent tempAgent=agentBiz.getAgentById(agent.getId());
					if(agentBiz.getAgentNotAllowBalance(tempAgent.getId()).compareTo(agent.getTempAmount())==-1){
						inf.setMessage("输入金额比所冻结余额大！");
						inf.setForwardPage("/agent/agent.do?thisAction=fundsTreatment&type=2&agentId="+agent.getId());
					}
					else{
						agentBiz.moveNotallowBalanceToAllowBalance(tempAgent, agent.getTempAmount(),agent.getTransactionMark());
						inf.setMessage("解冻资金成功！");
						inf.setForwardPage("/agent/agentlist.do?thisAction=view&agentId="+agent.getId());
					}
				} catch (Exception e) {
					inf.setMessage("解冻资金失败！" + e.getMessage());
					inf.setBack(true);
				}
		 }else{
		 	inf.setMessage("重复提交！");
			inf.setForwardPage("/agent/agentlist.do?thisAction=listAgent&sort=3");
		 }
			request.setAttribute("inf", inf);
			forwardPage = "inform";
			return (mapping.findForward(forwardPage));
	}
	
	
	
	public SysUser getUserByURI(HttpServletRequest request){
		if(request.getSession().getAttribute("URI")==null)
			return null;
		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute("URI");
		if(uri!=null&&uri.getUser()!=null)
			return uri.getUser();
		else{
			return null;
		}
	}
	
	public void setAgentBiz(AgentBiz agentBiz){
		this.agentBiz = agentBiz;
	}
	
	public void setAccountBiz(AccountBiz accountBiz){
		this.accountBiz = accountBiz;
	}

	public void setCoterieBiz(CoterieBiz coterieBiz) {
		this.coterieBiz = coterieBiz;
	}

	public void setAgentAddressBiz(AgentAddressBiz agentAddressBiz) {
		this.agentAddressBiz = agentAddressBiz;
	}

	public void setAgentContactBiz(AgentContactBiz agentContactBiz) {
		this.agentContactBiz = agentContactBiz;
	}	
}