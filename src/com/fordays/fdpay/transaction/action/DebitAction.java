package com.fordays.fdpay.transaction.action;


import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


import com.fordays.fdpay.agent.Agent;
import com.fordays.fdpay.agent.biz.AgentBiz;
import com.fordays.fdpay.right.UserRightInfo;
import com.fordays.fdpay.transaction.Debit;
import com.fordays.fdpay.transaction.biz.DebitBiz;
import com.fordays.fdpay.transaction.biz.DebitLogBiz;
import com.fordays.fdpay.user.SysUser;
import com.fordays.fdpay.user.biz.UserBiz;
import com.neza.base.BaseAction;
import com.neza.base.Inform;
import com.neza.exception.AppException;

public class DebitAction extends BaseAction {
	private DebitBiz debitBiz;
	private UserBiz userBiz;
	private AgentBiz agentBiz;
	private DebitLogBiz debitLogBiz;
	
	public SysUser getUserByURI(HttpServletRequest request){
		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute("URI");
		if(uri!=null&&uri.getUser()!=null)
			return uri.getUser();
		else{
			return null;
		}
	}
	
	//编辑预支
	public ActionForward editDebit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		Debit debit = (Debit) form;
		if (debit.getId() > 0) {
			debit = debitBiz.getDebitById(debit.getId());
			request.setAttribute("debit", debit);
			forwardPage = "editdebit";
		}
		return mapping.findForward(forwardPage);
	}
	
	//审核预支
	public ActionForward auditDebit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		Inform inf = new Inform();
		String forwardPage = "";
		// 获取操作人
		SysUser use = this.getUserByURI(request);
		if(use!=null){
			SysUser user = userBiz.getUserByNo(use);
			Debit debit = (Debit) form;
			if(debit.getStatus()!=Debit.DEBIT_STATUS_1 && debit.getStatus()==Debit.DEBIT_STATUS_0){
				Debit tempDebit = debitBiz.getDebitById(debit.getId());
				
				tempDebit.setStatus(Debit.DEBIT_STATUS_1);
				tempDebit.setCheckName(user.getUserName());
				if(debit.getNote1()!=null)
					tempDebit.setNote(tempDebit.getNote()+"\\n"+debit.getNote1());
				
				tempDebit.setCheckDate(new Timestamp(System.currentTimeMillis()));
				debitBiz.updateInfo(tempDebit);
				// 添加预支日志
				debitLogBiz.auditLog(tempDebit,tempDebit.getAgent(),user,debit.getNote1());
			}
			inf.setMessage("您审核了这条预支！");
			inf.setForwardPage("/transaction/debitlist.do");
			inf.setParamId("thisAction");
			inf.setParamValue("listDebit");
		}
		else{
			request.getSession().invalidate();
			return mapping.findForward("exit");
		}
			request.setAttribute("inf", inf);
			forwardPage = "inform";
			return (mapping.findForward(forwardPage));
		
	}
	
	// 批准
	public ActionForward approvalDebit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		Inform inf = new Inform();
		String forwardPage = "";
		//获取操作人
		SysUser use = this.getUserByURI(request);
		if(use!=null){
			SysUser user = userBiz.getUserByNo(use);
			Debit debit = (Debit) form;
			if(debit.getStatus()!=Debit.DEBIT_STATUS_2&&debit.getStatus()==Debit.DEBIT_STATUS_1){
				Debit newDebit = debitBiz.getDebitById(debit.getId());
				newDebit.setStatus(Debit.DEBIT_STATUS_2);
				newDebit.setUser1Name(user.getUserName());
				if(debit.getNote1()!=null)
					newDebit.setNote(newDebit.getNote()+"\\n"+debit.getNote1());
				newDebit.setCheck1Date(new Timestamp(System.currentTimeMillis()));
				//批准预支
				debitBiz.updateInfo(newDebit);
				// 添加预支日志
				debitLogBiz.approvalLog(newDebit,newDebit.getAgent(),user,debit.getNote1());
			}
			inf.setMessage("您批准了这条预支！");
			inf.setForwardPage("/transaction/debitlist.do");
			inf.setParamId("thisAction");
			inf.setParamValue("listDebit");
		}else{
			request.getSession().invalidate();
			return mapping.findForward("exit");
		}
		request.setAttribute("inf", inf);
		forwardPage = "inform";
		return (mapping.findForward(forwardPage));
	}
	
	//撤销预支
	public ActionForward revocationDebit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		Inform inf = new Inform();
		String forwardPage = "";
		Debit debit = (Debit) form;
		Debit tmpDebit = debitBiz.getDebitById(debit.getId());
		tmpDebit.setStatus(Debit.DEBIT_STATUS_4);
		debitBiz.updateInfo(tmpDebit);
		//预支撤销不操作金额
		inf.setMessage("您撤销了这条预支！");
		inf.setForwardPage("/transaction/debitlist.do");
		inf.setParamId("thisAction");
		inf.setParamValue("listDebit");
		request.setAttribute("inf", inf);
		forwardPage = "inform";
		return (mapping.findForward(forwardPage));
	}
	
	//预支
	public ActionForward advanceDebit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		Inform inf = new Inform();
		String forwardPage = "";
		//获取操作人
		SysUser use = this.getUserByURI(request);
		if(use!=null){
			SysUser user = userBiz.getUserByNo(use);
			Debit debit = (Debit) form;
			Agent agent = agentBiz.getAgentByEmail(debit.getAgentLoginName());
			Debit newDebit = debitBiz.getDebitById(debit.getId());
			agentBiz.synallowBalance(newDebit.getFromAgent());
			if(agentBiz.getAgentAllowBalance(newDebit.getFromAgent().getId()).compareTo(newDebit.getAmount())==-1){
				inf.setMessage("预支方余额不足预支，预支失败！");
				inf.setForwardPage("/transaction/debitlist.do");
				inf.setParamId("thisAction");
				inf.setParamValue("listDebit");
			}else{
				if(debit.getStatus()!=Debit.DEBIT_STATUS_3&&debit.getStatus()==Debit.DEBIT_STATUS_2){
					newDebit.setStatus(Debit.DEBIT_STATUS_3);
					newDebit.setUser2Name(user.getUserName());
					if(debit.getNote1()!=null)
						newDebit.setNote(newDebit.getNote()+"\\n"+debit.getNote1());
					newDebit.setCheck2Date(new Timestamp(System.currentTimeMillis()));
					//完成预支
					debitBiz.advanceAchieve(newDebit);
					// 添加预支日志
					debitLogBiz.advanceLog(newDebit,agent,user,debit.getNote1());
				}
				inf.setMessage("预支成功！");
				inf.setForwardPage("/transaction/debitlist.do");
				inf.setParamId("thisAction");
				inf.setParamValue("listDebit");
			}
		}else{
			request.getSession().invalidate();
			return mapping.findForward("exit");
		}
		request.setAttribute("inf", inf);
		forwardPage = "inform";
		return (mapping.findForward(forwardPage));
	}
	
	public DebitBiz getDebitBiz() {
		return debitBiz;
	}
	public void setDebitBiz(DebitBiz debitBiz) {
		this.debitBiz = debitBiz;
	}

	public UserBiz getUserBiz() {
		return userBiz;
	}

	public void setUserBiz(UserBiz userBiz) {
		this.userBiz = userBiz;
	}

	public AgentBiz getAgentBiz() {
		return agentBiz;
	}

	public void setAgentBiz(AgentBiz agentBiz) {
		this.agentBiz = agentBiz;
	}

	public DebitLogBiz getDebitLogBiz() {
		return debitLogBiz;
	}

	public void setDebitLogBiz(DebitLogBiz debitLogBiz) {
		this.debitLogBiz = debitLogBiz;
	}
	
}