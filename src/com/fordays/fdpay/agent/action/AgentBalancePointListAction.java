package com.fordays.fdpay.agent.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.fordays.fdpay.agent.Agent;
import com.fordays.fdpay.agent.AgentBalancePointListForm;
import com.fordays.fdpay.agent.biz.AgentBalancePointBiz;
import com.fordays.fdpay.right.UserRightInfo;
import com.neza.base.BaseAction;
import com.neza.base.Inform;
import com.neza.exception.AppException;

public class AgentBalancePointListAction extends BaseAction
{

	private AgentBalancePointBiz agentBalancePointBiz;

	public ActionForward listAgentBalancePoint(ActionMapping mapping,
	    ActionForm form, HttpServletRequest request, HttpServletResponse response)
	    throws AppException
	{

		String forwardPage = "";

		AgentBalancePointListForm alf = (AgentBalancePointListForm) form;
		if (alf == null)
			alf = new AgentBalancePointListForm();
		List list = agentBalancePointBiz.listAgentBalancePoint(alf);
		alf.setList(list);
		request.setAttribute("alf", alf);
		forwardPage = "listagentbalancePoint";
		return (mapping.findForward(forwardPage));

	}

	// 更新商户
	public ActionForward updateAgentBalancePointByAgent(ActionMapping mapping,
	    ActionForm form, HttpServletRequest request, HttpServletResponse response)
	    throws AppException
	{

		try
		{
			//if (agentBalancePointBiz.truncateAgentBalancePoint())// 清空表中数据
			
				agentBalancePointBiz.addAgentToAgentBalancePoint();
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return listAgentBalancePoint(mapping, form, request, response);
	}

	// 转到更新盘点商户
	public ActionForward toUpdateAgentBalancePoint(ActionMapping mapping,
	    ActionForm form, HttpServletRequest request, HttpServletResponse response)
	    throws AppException
	{
		String forwardPage = "inform";
		UserRightInfo uri;
		if (request.getSession().getAttribute("URI") != null)
		{
			uri = (UserRightInfo) request.getSession().getAttribute("URI");
			if (uri.hasRight("sb11"))
			{
				forwardPage = "editagentbalancepoint";
				return (mapping.findForward(forwardPage));
			}
		}

		Inform inf = new Inform();
		inf.setMessage("您没有相应权限！");
		inf.setBack(true);
		forwardPage = "inform";
		request.setAttribute("inf",inf);
		return (mapping.findForward(forwardPage));
	}

	// 更新盘点商户
	public ActionForward updateAgentBalancePoint(ActionMapping mapping,
	    ActionForm form, HttpServletRequest request, HttpServletResponse response)
	    throws AppException
	{
		AgentBalancePointListForm alf = (AgentBalancePointListForm) form;
		UserRightInfo uri;
		if (request.getSession().getAttribute("URI") != null)
		{
			uri = (UserRightInfo) request.getSession().getAttribute("URI");
			if (uri.hasRight("sb11"))
			{
				agentBalancePointBiz.updateAgentBalancePointAmount(alf.getBalanceDate()
				    .toString());
			}
		}
		return listAgentBalancePoint(mapping, form, request, response);
	}

	public AgentBalancePointBiz getAgentBalancePointBiz()
	{
		return agentBalancePointBiz;
	}

	public void setAgentBalancePointBiz(AgentBalancePointBiz agentBalancePointBiz)
	{
		this.agentBalancePointBiz = agentBalancePointBiz;
	}

}
