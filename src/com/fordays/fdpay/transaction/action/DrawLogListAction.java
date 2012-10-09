package com.fordays.fdpay.transaction.action;


import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.*;
import com.neza.exception.AppException;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.fordays.fdpay.agent.Agent;
import com.fordays.fdpay.agent.AgentListForm;
import com.fordays.fdpay.agent.biz.AgentBiz;
import com.fordays.fdpay.transaction.biz.DrawBiz;
import com.fordays.fdpay.transaction.biz.DrawLogBiz;
import com.fordays.fdpay.transaction.Draw;
import com.fordays.fdpay.transaction.DrawLog;
import com.fordays.fdpay.transaction.DrawLogListForm;
import com.fordays.fdpay.user.biz.UserBiz;
import com.neza.base.BaseAction;


public class DrawLogListAction extends BaseAction
{
	private DrawLogBiz drawLogBiz;
	private AgentBiz agentBiz;
	private UserBiz userBiz;
	private DrawBiz drawBiz;

	public DrawLogBiz getDrawLogBiz() throws AppException{
		return drawLogBiz;
	}

	public void setDrawLogBiz(DrawLogBiz drawLogBiz) throws AppException{
		this.drawLogBiz = drawLogBiz;
	}

	public UserBiz getUserBiz() throws AppException{
		return userBiz;
	}

	public void setUserBiz(UserBiz userBiz) throws AppException{
		this.userBiz = userBiz;
	}

	public AgentBiz getAgentBiz() throws AppException{
		return agentBiz;
	}

	public void setAgentBiz(AgentBiz agentBiz) throws AppException{
		this.agentBiz = agentBiz;
	}

	

//	public ActionForward view(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)throws AppException
//		{
//			String forwardPage = "";
//			
//			int id = Integer.parseInt( request.getParameter("Id"));
//			if (id > 0)
//			{
//				DrawLog drawLog = (DrawLog) drawLogBiz.queryDrawLogById(id);
//				 
//			  
//				drawLog.setThisAction("view");
//				request.setAttribute("drawLog", drawLog);
//			}
//			else
//			{
//				forwardPage = "login";
//			}
//
//			forwardPage = "viewdrawLog";
//			return (mapping.findForward(forwardPage));
//		}

//	public ActionForward add(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)throws AppException
//		{
//		DrawLog drawLog = new DrawLog();
//		drawLog.setThisAction("insert");
//			request.setAttribute("drawLog", drawLog);
//			String forwardPage = "editdrawLog";
//			return (mapping.findForward(forwardPage));
//		}

	
	public ActionForward listDrawLog(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws AppException {
		String forwardPage = "";
		
		DrawLogListForm clf = (DrawLogListForm) form;	
			if (clf == null)
				clf = new DrawLogListForm();
			
		List list=drawLogBiz.getDrawLogs(clf);
		clf.setList(list);
	
		clf.addSumField(1, "amount");
		request.setAttribute("clf", clf);
		forwardPage = "listDrawLog";
		
		return (mapping.findForward(forwardPage));
	}
	
	public ActionForward listDrawLogByContent(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws AppException {
		String forwardPage = "";
		DrawLogListForm clf = (DrawLogListForm) form;	
			if (clf == null)
				clf = new DrawLogListForm();
		String orderNo=clf.getOrderNo();
		List list=drawLogBiz.getDrawLogByContent(clf);
		Draw draw=drawBiz.getDrawByOrderNo(orderNo);
		request.setAttribute("draw", draw);
		clf.setList(list);
		request.setAttribute("clf", clf);
		forwardPage = "listDrawLogByContent";
		
		return (mapping.findForward(forwardPage));
	}

	public DrawBiz getDrawBiz() {
		return drawBiz;
	}

	public void setDrawBiz(DrawBiz drawBiz) {
		this.drawBiz = drawBiz;
	}
}
