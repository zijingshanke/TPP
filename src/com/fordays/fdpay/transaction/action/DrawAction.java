package com.fordays.fdpay.transaction.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.fordays.fdpay.agent.Agent;
import com.fordays.fdpay.agent.AgentBalance;
import com.fordays.fdpay.agent.biz.AgentBiz;
import com.fordays.fdpay.right.UserRightInfo;
import com.fordays.fdpay.transaction.Charge;
import com.fordays.fdpay.transaction.Draw;
import com.fordays.fdpay.transaction.DrawListForm;
import com.fordays.fdpay.transaction.DrawLog;
import com.fordays.fdpay.transaction.biz.DrawBiz;
import com.fordays.fdpay.transaction.biz.DrawLogBiz;
import com.fordays.fdpay.user.SysUser;
import com.fordays.fdpay.user.biz.UserBiz;
import com.neza.base.BaseAction;
import com.neza.base.Inform;
import com.neza.base.NoUtil;
import com.neza.exception.AppException;

public class DrawAction extends BaseAction {
	private UserBiz userBiz;
	private AgentBiz agentBiz;
	private DrawBiz drawBiz;
	private NoUtil noUtil;
	private DrawLogBiz drawLogBiz;
	
	
	public SysUser getUserByURI(HttpServletRequest request){
		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute("URI");
		if(uri!=null&&uri.getUser()!=null)
			return uri.getUser();
		else{
			return null;
		}
	}
	//添加扣款 
	public ActionForward addSubtract(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		Inform inf = new Inform();
		String forwardPage = "";
		SysUser use = this.getUserByURI(request);
		if(use!=null){
			SysUser user = userBiz.getUserByNo(use);
			Draw draw=(Draw)form;
			Agent agent = agentBiz.getAgentByEmail(draw.getAgentLoginName());
			if (agent.getId() > 0) {
				//2009-9-15 
//				if(draw.getAmount().compareTo(agent.getAllowBalance())>0)
//				{
//					inf.setMessage("该账户可用余额不足扣款！");
//					inf.setBack(true);
//				}else{
					//添加扣款
					Draw tempDraw=new Draw();
					tempDraw.setOrderNo(noUtil.getDrawNo());
					tempDraw.setAgent(agent);
					tempDraw.setBank("OTHER");
					tempDraw.setCardNo("OTHER");
					tempDraw.setAmount(draw.getAmount());
					tempDraw.setRequestDate(new Timestamp(System.currentTimeMillis()));
					tempDraw.setNote(draw.getNote());
					tempDraw.setType(Draw.TYPE_2);
					tempDraw.setStatus(Draw.STATUS_0);
					drawBiz.save(tempDraw);
					
					//添加提现日志
					DrawLog drawLog=new DrawLog();
					drawLog.setAgent(agent);
					drawLog.setAmount(tempDraw.getAmount());
					drawLog.setChargeDate(new Timestamp(System.currentTimeMillis()));
					drawLog.setOrderNo(tempDraw.getOrderNo());
					AgentBalance ab =agentBiz.getAgentBalance(agent.getId());
					drawLog.setContent(draw.getNote()+" 当前商户可用余额："+ab.getAllowBalance());
					drawLog.setStatus(Draw.STATUS_0);
					drawLog.setSysUser(user);
					drawLogBiz.save(drawLog);
					
					inf.setMessage("您已经添加了扣款！");
					inf.setForwardPage("/transaction/drawlist.do");
					inf.setParamId("thisAction");
					inf.setParamValue("listSubtract");
				
			}else{
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

	//编辑扣款页面
	public ActionForward editSubtract(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		Draw draw = (Draw) form;
		if (draw.getId() > 0) {
			draw = drawBiz.getDrawById(draw.getId());
			request.setAttribute("draw", draw);
			forwardPage = "editSubtract";
		}
		return mapping.findForward(forwardPage);
	}
	
	//批准扣款
	public ActionForward approvalSubtract(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws AppException {
		String forwardPage = "";
		Inform inf = new Inform();
		// 获取登陆SysUser
		SysUser user = this.getUserByURI(request);
		Draw draw = (Draw) form;
		String message="";
		try {
				Draw tempDraw = drawBiz.getDrawById(draw.getId());
				Agent agent = agentBiz.getAgentByEmail(draw.getAgentLoginName());
//				if(agent.getAllowBalance().doubleValue()>=tempDraw.getAmount().doubleValue()){
				if(tempDraw.getStatus()!=Draw.STATUS_1 && tempDraw.getStatus()==Draw.STATUS_0){
					if(draw.getNote1()!=null){
						tempDraw.setNote(tempDraw.getNote() + "\\n" + draw.getNote1());
					}
					tempDraw.setStatus(Draw.STATUS_1);
					tempDraw.setSysUser(user);
					tempDraw.setCheckDate(new Timestamp(System.currentTimeMillis()));
					drawBiz.updateInfo(tempDraw);
					// 添加提现日志
					drawLogBiz.approval(tempDraw, agent,draw.getNote1());
					message="您已经成功批准扣款！";
				}else{
					message="您已经成功批准扣款！";
				}
//				}else{
//					message="批准失败！由于扣款金额不足需要撤销！";
//				}
				inf.setMessage(message);
				inf.setForwardPage("/transaction/drawlist.do?thisAction=listSubtract&status="+ draw.getStatus());
		} catch (Exception ex) {
			inf.setMessage("批准出错！错误信息是：" + ex.getMessage());
			inf.setBack(true);
			ex.printStackTrace();
		}
		request.setAttribute("inf", inf);
		forwardPage = "inform";
		return (mapping.findForward(forwardPage));
	}
	
	//核准扣款，完成
	public ActionForward auditSubtract(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws AppException {
		String forwardPage = "";
		Inform inf = new Inform();
		// 获取登陆SysUser
		SysUser user = this.getUserByURI(request);
		Draw draw = (Draw) form;
		String message="";
		try {
				Draw tempDraw = drawBiz.getDrawById(draw.getId());
				Agent agent = agentBiz.getAgentByEmail(draw.getAgentLoginName());
//				if(agent.getAllowBalance().doubleValue()>=tempDraw.getAmount().doubleValue()){
				if(tempDraw.getStatus()!=Draw.STATUS_3 && tempDraw.getStatus()==Draw.STATUS_1){
					if(draw.getNote1()!=null){
						tempDraw.setNote(tempDraw.getNote() + "\\n" + draw.getNote1());
					}
					tempDraw.setStatus(Draw.STATUS_3);
					tempDraw.setSysUser1(user);
					tempDraw.setSysUser3(user);
					tempDraw.setCheck1Date(new Timestamp(System.currentTimeMillis()));
					tempDraw.setCheck3Date(new Timestamp(System.currentTimeMillis()));
					tempDraw.setDrawDate(new Timestamp(System.currentTimeMillis()));
					drawBiz.updateInfo(tempDraw);
					// 修改商户金额,生成交易记录
					drawBiz.achieveSubtract(tempDraw, agent, tempDraw.getAmount());
					// 添加提现日志
					drawLogBiz.achieve(tempDraw, agent,draw.getNote1());
					message="您已经完成扣款操作！";
				}
				else{
					message="您已经完成扣款操作！";
				}
//				}else{
//					message="扣款失败！由于扣款金额不足需要撤销！";
//				}
				inf.setMessage(message);
				inf.setForwardPage("/transaction/drawlist.do?thisAction=listSubtract&status="+ draw.getStatus());
		} catch (Exception ex) {
			inf.setMessage("核准出错！错误信息是：" + ex.getMessage());
			inf.setBack(true);
			ex.printStackTrace();
		}
		request.setAttribute("inf", inf);
		forwardPage = "inform";
		return (mapping.findForward(forwardPage));
	}
	
	
	// 撤销扣款
	public ActionForward revocationSubtract(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws AppException {
		String forwardPage = "";
		Inform inf = new Inform();
		Draw draw = (Draw) form;
		try {
			Draw tempDraw = drawBiz.getDrawById(draw.getId());
			tempDraw.setStatus(Draw.STATUS_4);
			drawBiz.updateInfo(tempDraw);
			//撤销扣款，对商户金额不做处理
			inf.setMessage("您已经成功撤销这些扣款！");
			inf.setForwardPage("/transaction/drawlist.do?thisAction=listSubtract&status="+ draw.getStatus());
		} catch (Exception e) {
			inf.setMessage("出错！错误信息是：" + e.getMessage());
			inf.setBack(true);
			e.printStackTrace();
		}
		request.setAttribute("inf", inf);
		forwardPage = "inform";
		return (mapping.findForward(forwardPage));
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

	public DrawBiz getDrawBiz() {
		return drawBiz;
	}

	public void setDrawBiz(DrawBiz drawBiz) {
		this.drawBiz = drawBiz;
	}

	public NoUtil getNoUtil() {
		return noUtil;
	}

	public void setNoUtil(NoUtil noUtil) {
		this.noUtil = noUtil;
	}

	public DrawLogBiz getDrawLogBiz() {
		return drawLogBiz;
	}

	public void setDrawLogBiz(DrawLogBiz drawLogBiz) {
		this.drawLogBiz = drawLogBiz;
	}
}