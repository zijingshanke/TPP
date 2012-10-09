package com.fordays.fdpay.transaction.action;

import java.sql.Timestamp;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.fordays.fdpay.agent.Agent;
import com.fordays.fdpay.agent.biz.AgentBiz;
import com.fordays.fdpay.agent.biz.AgentCoterieBiz;
import com.fordays.fdpay.right.UserRightInfo;
import com.fordays.fdpay.transaction.Charge;
import com.fordays.fdpay.transaction.biz.ChargeBiz;
import com.fordays.fdpay.transaction.biz.ChargeLogBiz;
import com.fordays.fdpay.user.SysUser;
import com.fordays.fdpay.user.biz.UserBiz;
import com.neza.base.BaseAction;
import com.neza.base.Inform;
import com.neza.base.NoUtil;
import com.neza.exception.AppException;
import com.neza.mail.MailUtil;

public class ChargeAction extends BaseAction {
	private NoUtil noUtil;
	private ChargeLogBiz chargeLogBiz;
	private AgentBiz agentBiz;
	private UserBiz userBiz;
	private ChargeBiz chargeBiz;
	private AgentCoterieBiz agentCoterieBiz;

	
	public SysUser getUserByURI(HttpServletRequest request){
		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute("URI");
		if(uri!=null&&uri.getUser()!=null)
			return uri.getUser();
		else{
			return null;
		}
	}
	// 添加线下充值 帐户可用金额 
	public ActionForward otherCharge(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		Inform inf = new Inform();
		String forwardPage = "";
		// 获取操作人
		SysUser use = this.getUserByURI(request);
		if(use!=null){
			SysUser user = userBiz.getUserByNo(use);
			Charge charge = (Charge) form;
			Agent agent = agentBiz.getAgentByEmail(charge.getAgentLoginName());
			if (agent.getId() > 0) {
				// 创建充值记录
				Charge newcharge=chargeBiz.createCharge(charge,agent);
				// 添加充值日志
				chargeLogBiz.createChargeLog(newcharge,agent,user);
				request.setAttribute("charge", newcharge);
				inf.setMessage("您已经添加了充值！");
				inf.setForwardPage("/transaction/chargelist.do");
				inf.setParamId("thisAction");
				inf.setParamValue("listOtherCharge");
			} else {
				inf.setMessage("该商户不存在！");
				inf.setBack(true);
			}
		}else{
			request.getSession().invalidate();
			return mapping.findForward("exit");
		}
		request.setAttribute("inf", inf);
		forwardPage = "inform";
		return (mapping.findForward(forwardPage));
	}
	
	
	// 添加线下充值  帐户信用金额
	public ActionForward creditCharge(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		Inform inf = new Inform();
		String forwardPage = "";
		// 获取操作人
		SysUser use = this.getUserByURI(request);
		if(use!=null){
			SysUser user = userBiz.getUserByNo(use);
			Charge charge = (Charge) form;
			Agent agent = agentBiz.getAgentByEmail(charge.getAgentLoginName());
			if (agent.getId() > 0) {
				
			int  count= agentCoterieBiz.getAgentCoterieByAgent_Id(agent.getId());
			if(count>0){
				
				// 创建充值记录
				Charge newcharge=chargeBiz.creditCharge(charge,agent);
				// 添加充值日志
				chargeLogBiz.createChargeLog(newcharge,agent,user);
				request.setAttribute("charge", newcharge);
				inf.setMessage("您已经添加了充值！");
				inf.setForwardPage("/transaction/chargelist.do");
				inf.setParamId("thisAction");
				inf.setParamValue("listOtherCharge");
				}else{
					inf.setMessage("该商户不存在商务圈内！");
					inf.setBack(true);
				}
			} else {
				inf.setMessage("该商户不存在！");
				inf.setBack(true);
			}
		}else{
			request.getSession().invalidate();
			return mapping.findForward("exit");
		}
		request.setAttribute("inf", inf);
		forwardPage = "inform";
		return (mapping.findForward(forwardPage));
	}
	
//编辑充值页面
	public ActionForward editCharge(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		Charge charge = (Charge) form;
		if (charge.getId() > 0) {
			charge = chargeBiz.getChargeById(charge.getId());
			request.setAttribute("bank", request.getParameter("bank"));
			request.setAttribute("status", request.getParameter("status"));
			request.setAttribute("charge", charge);
			forwardPage = "editcharge";
		}
		return mapping.findForward(forwardPage);
	}
	
	// 线下充值批准
	public ActionForward approvalCharge(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws AppException {
		Inform inf = new Inform();
		String fromBank=request.getParameter("fromBank");
		String fromStatus=request.getParameter("fromStatus");
		String forwardPage = "";
		// 获取操作人
		SysUser use = this.getUserByURI(request);
		if(use!=null){
			SysUser user = userBiz.getUserByNo(use);
			Charge charge = (Charge) form;
			if(charge.getStatus()!=Charge.CHARGE_STATUS_3 && charge.getStatus()==Charge.CHARGE_STATUS_0){
				Agent agent = agentBiz.getAgentByEmail(charge.getAgentLoginName());
				Charge newcharge = chargeBiz.getChargeById(charge.getId());
				newcharge.setStatus(Charge.CHARGE_STATUS_3);
				newcharge.setSysUser(user);
				if(charge.getNote1()!=null)
					newcharge.setNote(newcharge.getNote()+"\\n"+charge.getNote1());
				
				newcharge.setCheckDate(new Timestamp(System.currentTimeMillis()));
				chargeBiz.updateInfo(newcharge);
				// 添加充值日志
				chargeLogBiz.approvalLog(newcharge,agent,user,charge.getNote1());
			}
			inf.setMessage("您批准了这条充值！");
			inf.setForwardPage("/transaction/chargelist.do?thisAction=listOtherCharge&bank="+fromBank+"&status="+fromStatus);
		}
		else{
			request.getSession().invalidate();
			return mapping.findForward("exit");
		}
			request.setAttribute("inf", inf);
			forwardPage = "inform";
			return (mapping.findForward(forwardPage));
	}

	// 线下充值核准
	public ActionForward auditCharge(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		Inform inf = new Inform();
		String forwardPage = "";
		//获取操作人
		SysUser use = this.getUserByURI(request);
		if(use!=null){
			SysUser user = userBiz.getUserByNo(use);
			Charge charge = (Charge) form;
			String fromBank=request.getParameter("fromBank");
			String fromStatus=request.getParameter("fromStatus");
			if(charge.getStatus()!=Charge.CHARGE_STATUS_1&&charge.getStatus()==Charge.CHARGE_STATUS_3){
				Agent agent = agentBiz.getAgentByEmail(charge.getAgentLoginName());
				// 补全充值
				Charge newcharge = chargeBiz.getChargeById(charge.getId());
				newcharge.setStatus(Charge.CHARGE_STATUS_1);
				newcharge.setSysUser1(user);
				if(charge.getNote1()!=null)
					newcharge.setNote(newcharge.getNote()+"\\n"+charge.getNote1());
				newcharge.setCheck1Date(new Timestamp(System.currentTimeMillis()));
				newcharge.setChargeDate(new Timestamp(System.currentTimeMillis()));
				
				//完成充值
				chargeBiz.achieveCharge(newcharge,agent);
				// 添加充值日志
				chargeLogBiz.anditLog(newcharge,agent,user,charge.getNote1());
				// 发送邮件
				HashMap<String, String> params = new HashMap<String, String>();
				params.put("$toAgentName$", agent.getName());
				params.put("$amount$", newcharge.getAmount().toString());
				params.put("$orderId$", newcharge.getOrderNo());
				String result = MailUtil.send("钱门充值成功", "0008", agent.getLoginName(),
						params); // 发邮件
			}
			inf.setMessage("您核准了这条充值！");
			inf.setForwardPage("/transaction/chargelist.do?thisAction=listOtherCharge&bank="+fromBank+"&status="+fromStatus);
			
		}else{
			request.getSession().invalidate();
			return mapping.findForward("exit");
		}
		request.setAttribute("inf", inf);
		forwardPage = "inform";
		return (mapping.findForward(forwardPage));
	}
	//撤销
	public ActionForward revocation(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		Inform inf = new Inform();
		String forwardPage = "";
		Charge charge = (Charge) form;
		Charge tmpCharge=chargeBiz.getChargeById(charge.getId());
		tmpCharge.setStatus(Charge.CHARGE_STATUS_5);
		chargeBiz.save(tmpCharge);
		//线下充值撤销不操作金额
		inf.setMessage("您撤销了这条充值！");
		inf.setForwardPage("/transaction/chargelist.do");
		inf.setParamId("thisAction");
		inf.setParamValue("listOtherCharge");
		request.setAttribute("inf", inf);
		forwardPage = "inform";
		return (mapping.findForward(forwardPage));
	}
	
	public void setNoUtil(NoUtil noUtil) {
		this.noUtil = noUtil;
	}

	public void setChargeLogBiz(ChargeLogBiz chargeLogBiz) {
		this.chargeLogBiz = chargeLogBiz;
	}


	public void setAgentBiz(AgentBiz agentBiz) {
		this.agentBiz = agentBiz;
	}

	public void setUserBiz(UserBiz userBiz) {
		this.userBiz = userBiz;
	}

	public void setChargeBiz(ChargeBiz chargeBiz) {
		this.chargeBiz = chargeBiz;
	}
	public AgentCoterieBiz getAgentCoterieBiz() {
		return agentCoterieBiz;
	}
	public void setAgentCoterieBiz(AgentCoterieBiz agentCoterieBiz) {
		this.agentCoterieBiz = agentCoterieBiz;
	}

}