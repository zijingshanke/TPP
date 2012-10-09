package com.fordays.fdpay.transaction.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.fordays.fdpay.agent.biz.AgentBiz;
import com.fordays.fdpay.right.UserRightInfo;
import com.fordays.fdpay.transaction.TradingStatistics;
import com.fordays.fdpay.transaction.biz.ChargeBiz;
import com.fordays.fdpay.transaction.biz.DrawBiz;
import com.fordays.fdpay.user.SysUser;
import com.fordays.fdpay.user.biz.UserBiz;
import com.neza.base.BaseAction;
import com.neza.exception.AppException;

public class StatisticAction extends BaseAction {
	private UserBiz userBiz;
	private DrawBiz drawBiz;
	private ChargeBiz chargeBiz;
	private AgentBiz agentBiz;
	public UserBiz getUserBiz() {
		return userBiz;
	}
	public void setUserBiz(UserBiz userBiz) {
		this.userBiz = userBiz;
	}
	public DrawBiz getDrawBiz() {
		return drawBiz;
	}
	public void setDrawBiz(DrawBiz drawBiz) {
		this.drawBiz = drawBiz;
	}

	public ActionForward listStatistic(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		//获取登陆SysUser
		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute("URI");
		if(uri!=null){
			SysUser use=uri.getUser();
	        SysUser user=userBiz.getUserByNo(use);
			try{
				long application=chargeBiz.getCountByChargeBank(0);
				long audit=chargeBiz.getCountByChargeBank(3);
				long total=agentBiz.getCountAgentCertification();
				request.setAttribute("application", application);
				request.setAttribute("audit", audit);
				request.setAttribute("total", total);
				List list=drawBiz.getCountByDrawList();
				List ts=new ArrayList<TradingStatistics>();
				for(int i=0;i<list.size();i++){
					ts.add((TradingStatistics)list.get(i));
				}
				request.setAttribute("ts", ts);
				request.setAttribute("user", user);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}else{
			request.getSession().invalidate();
			return mapping.findForward("exit");
		}
		forwardPage = "main";
		return (mapping.findForward(forwardPage));
	}
	public ChargeBiz getChargeBiz() {
		return chargeBiz;
	}
	public void setChargeBiz(ChargeBiz chargeBiz) {
		this.chargeBiz = chargeBiz;
	}
	public AgentBiz getAgentBiz() {
		return agentBiz;
	}
	public void setAgentBiz(AgentBiz agentBiz) {
		this.agentBiz = agentBiz;
	}
}