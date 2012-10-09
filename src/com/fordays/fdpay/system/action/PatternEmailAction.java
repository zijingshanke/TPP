package com.fordays.fdpay.system.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts.action.*;
import com.fordays.fdpay.agent.MailAgentListForm;
import com.fordays.fdpay.agent.biz.AgentBiz;
import com.fordays.fdpay.system.PatternEmail;
import com.fordays.fdpay.system.biz.PatternEmailBiz;
import com.neza.base.BaseAction;
import com.neza.base.Inform;
import com.neza.encrypt.BASE64;
import com.neza.exception.AppException;
import com.neza.utility.RegexUtil;

import javax.servlet.http.*;

public class PatternEmailAction extends BaseAction
{
	private PatternEmailBiz patternEmailBiz;
	private AgentBiz agentBiz;

	// 查看发送邮件的商户详细信息
	public ActionForward listAgent(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws AppException
	{
		String forwardPage = "";
		PatternEmail pe = (PatternEmail) form;
		MailAgentListForm malf = new MailAgentListForm();
		malf.setList(agentBiz.getMailAgents(malf, pe.getMails(), request
		    .getSession().getId()));
		malf.setThisAction("listAgent");
		request.setAttribute("malf", malf);

		forwardPage = "listAgent"; //
		return (mapping.findForward(forwardPage));
	}

	// 发送邮件
	public ActionForward sendMail(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws AppException
	{
		Inform inf = new Inform();
		String forwardPage = "";
		PatternEmail pe = (PatternEmail) form;
		String mails = pe.getMails();
		String mailArray[] = mails.substring(0, mails.length() - 1).split(",");
		try
		{
			for (int i = 0; i < mailArray.length; i++)
			{
				if (pe.getId() != 0)
				{
					patternEmailBiz.sendToAgent(pe.getId(), mailArray[i]);
				}
			}
			inf.setMessage("你已成功发送了邮件");
			inf.setForwardPage("/system/patternEmaillist.do");
			inf.setParamId("thisAction");
			inf.setParamValue("list");
		}
		catch (Exception ex)
		{
			inf.setMessage("发邮件出错！错误信息是:" + ex.getMessage());
			inf.setBack(true);
		}
		request.setAttribute("inf", inf);
		forwardPage = "inform";
		return (mapping.findForward(forwardPage));
	}

	// 发送邮件
	public ActionForward send(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws AppException
	{
		PatternEmail pe = (PatternEmail) form;
		String mails = pe.getMails();
		String mailArray[] = mails.split(",");
		String temp = "";
		Map<String, String> map = (Map<String, String>) request.getParameterMap();



		for (int i = 0; i < mailArray.length; i++)
		{
			try
			{
				if (RegexUtil.isEMail(mailArray[i]))
				{
					int x = patternEmailBiz.sendToAgent(BASE64.dencrypt(pe.getSubject()),pe.getCode(), mailArray[i],
							map);
					if (x != 1)
					{
						if (temp.equals(""))
							temp = mailArray[i];
						else
							temp = temp + "," + mailArray[i];
					}
				}
				else
				{
					if (temp.equals(""))
						temp = mailArray[i];
					else
						temp = temp + "," + mailArray[i];
				}
			}
			catch (Exception ex)
			{
				if (temp.equals(""))
					temp = mailArray[i];
				else
					temp = temp + "," + mailArray[i];
			}
		}
		try
		{
			response.getWriter().write(temp);
		}
		catch (Exception ex)
		{
			System.out.println("邮件返回出错：" + ex.getMessage());
		}
		return null;
	}

	public ActionForward update(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws AppException
	{
		String forwardPage = "";
		PatternEmail patternemail = (PatternEmail) form;
		Inform inf = new Inform();
		// if (isTokenValid(request, true)) {
		try
		{
			PatternEmail tempPatternEmail = (PatternEmail) patternEmailBiz
			    .getPatternEmailById(patternemail.getId());

			tempPatternEmail.setName(patternemail.getName());
			tempPatternEmail.setStatus(patternemail.getStatus());
			tempPatternEmail.setContent(patternemail.getContent());
			tempPatternEmail.setCode(patternemail.getCode());
			tempPatternEmail.setDescription(patternemail.getDescription());

			patternEmailBiz.updatePatternEmail(tempPatternEmail);
			request.setAttribute("patternEmail", tempPatternEmail);
			inf.setMessage("你已成功更新了邮件模板");
			inf.setForwardPage("/system/patternEmaillist.do");
			inf.setParamId("thisAction");
			inf.setParamValue("list");
		}
		catch (Exception ex)
		{
			inf.setMessage("更新邮件模板出错！错误信息是:" + ex.getMessage());
			inf.setBack(true);
		}
		request.setAttribute("inf", inf);

		// } else {
		// saveToken(request);
		// inf.setMessage("请不要重复提交!!");
		// request.setAttribute("message", inf);
		// }
		forwardPage = "inform";
		return (mapping.findForward(forwardPage));
	}

	public ActionForward insert(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws AppException
	{
		String forwardPage = "";
		PatternEmail patternemail = (PatternEmail) form;
		Inform inf = new Inform();
		// if (isTokenValid(request, true)) {
		try
		{
			PatternEmail tempPatternEmail = new PatternEmail();

			tempPatternEmail.setName(patternemail.getName());
			tempPatternEmail.setStatus(patternemail.getStatus());
			tempPatternEmail.setContent(patternemail.getContent());
			tempPatternEmail.setCode(patternemail.getCode());
			tempPatternEmail.setDescription(patternemail.getDescription());

			patternEmailBiz.savePatternEmail(tempPatternEmail);
			request.setAttribute("patternEmail", tempPatternEmail);
			inf.setMessage("你已成功增加了邮件模板��ɹ");
			inf.setForwardPage("/system/patternEmaillist.do");
			inf.setParamId("thisAction");
			inf.setParamValue("list");
		}
		catch (Exception ex)
		{
			inf.setMessage("增加邮件模板失败，出错信息是:" + ex.getMessage());
			inf.setBack(true);
		}
		request.setAttribute("inf", inf);

		// } else {
		// saveToken(request);
		// inf.setMessage("请不要重复提交!!");
		// request.setAttribute("message", inf);
		// }

		forwardPage = "inform";

		return (mapping.findForward(forwardPage));
	}

	public void setPatternEmailBiz(PatternEmailBiz patternEmailBiz)
	{
		this.patternEmailBiz = patternEmailBiz;
	}

	public void setAgentBiz(AgentBiz agentBiz)
	{
		this.agentBiz = agentBiz;
	}
}
