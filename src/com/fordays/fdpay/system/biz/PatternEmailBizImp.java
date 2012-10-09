package com.fordays.fdpay.system.biz;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import com.fordays.fdpay.agent.Agent;
import com.fordays.fdpay.agent.biz.AgentBiz;
import com.fordays.fdpay.system.PatternEmail;
import com.fordays.fdpay.system.PatternEmailListForm;
import com.fordays.fdpay.system.dao.PatternEmailDAO;
import com.neza.tool.DateUtil;
import com.neza.encrypt.BASE64;
import com.neza.exception.AppException;
import com.neza.mail.MailSender;

public class PatternEmailBizImp implements PatternEmailBiz
{

	private PatternEmailDAO patternEmailDAO;
	private MailSender mailSender;
	private AgentBiz agentBiz;

	private TransactionTemplate transactionTemplate;

	public void setTransactionManager(
	    HibernateTransactionManager transactionManager)
	{
		this.transactionTemplate = new TransactionTemplate(transactionManager);
	}

	public void sendToAgent(long id, String mails) throws AppException
	{
		// System.out.println("sendToAgent(long id,String mails) id is ---" + id
		// + "--mails--" + mails);
		if (id > 0)
		{
			PatternEmail patternemail = (PatternEmail) this.getPatternEmailById(id);
			mailSender.setFrom("qmpay@qmpay.com");// ------
			mailSender.setSubject(patternemail.getName());
			String content = patternemail.getContent();
			//
			Agent agent = agentBiz.getAgentByEmailTempEmail(mails);
			mailSender.setTo(mails);// agent.getEmail();
			//
			String temp = content.replace("#agentName#", agent.getName());
			temp = content.replace("#agentName#", agent.getName());
			temp = content.replace("#email#", agent.getName());
			temp = content.replace("$date$", DateUtil.getDateString("yyyy年MM月dd日"));
			mailSender.setBody(temp);// --------patternemail.getContent()
			mailSender.send();
		}
	}

	public int sendToAgent(String mailCode, String mails, Map<String,String> params)
	    throws AppException
	{
		try
		{
			PatternEmail patternemail = patternEmailDAO
			    .getPatternEmailByCode(mailCode);
			if (patternemail != null)
			{
				mailSender.setFrom("qmpay@qmpay.com");// ------
				mailSender.setSubject(patternemail.getName());
				String content = patternemail.getContent();
				Agent agent = agentBiz.getAgentByEmailTempEmail(mails);
				mailSender.setTo(mails);// agent.getEmail();

				if (agent == null)
					agent = new Agent();
				String temp = content.replace("#agentName#", agent.getName());
				temp = temp.replace("#email#", agent.getEmail());
				Iterator it = params.entrySet().iterator();
				String key = "";
				String keyValue = "";
				String[] value = new String[0];
				while (it.hasNext())
				{
					Entry<String, String[]> entry = (Entry<String, String[]>) it.next();
					key = entry.getKey();
					value = entry.getValue();
					if (value instanceof String[])
					{
						keyValue = Arrays.toString(value);
					}
					else
					{
						keyValue = value.toString();
					}

					if (key.startsWith("$") && key.endsWith("$"))
					{
						keyValue = BASE64.dencrypt(keyValue);
						temp = temp.replace(key, keyValue);
					}
				}
				mailSender.setBody(temp);
				return mailSender.send();
			}
			else
				return 0;
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			return 0;
		}
	}

	public int sendToAgent(String subject, String mailCode, String mails,
	    Map<String,String> params) throws AppException
	{
		try
		{
			PatternEmail patternemail = patternEmailDAO
			    .getPatternEmailByCode(mailCode);
			if (patternemail != null)
			{
				mailSender.setFrom("qmpay@qmpay.com");// ------
				if (subject.equals(""))
					mailSender.setSubject(patternemail.getName());
				else
					mailSender.setSubject(subject);
				String content = patternemail.getContent();
				mailSender.setTo(mails);// agent.getEmail();
				String key = "";
				String keyValue = "";
				String[] value = new String[0];
				Iterator it = params.entrySet().iterator();
				while (it.hasNext())
				{
					Entry<String, String[]> entry = (Entry<String, String[]>) it.next();
					key = entry.getKey();
					value = entry.getValue();
					if (value instanceof String[])
					{
						keyValue = Arrays.toString(value);
					}
					else
					{
						keyValue = value.toString();
					}

					if (key.startsWith("$") && key.endsWith("$"))
					{
						keyValue = BASE64.dencrypt(keyValue);
						content = content.replace(key, keyValue);
					}
				}
				mailSender.setBody(content);
				return mailSender.send();
			}
			else
				return 0;
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			return 0;
		}
	}

	public void sendToAgent(long id, Agent agent) throws AppException
	{
		// TODO Auto-generated method stub

	}

	public List getPatternEmails(PatternEmailListForm pelf) throws AppException
	{
		return patternEmailDAO.list(pelf);
	}

	public void savePatternEmail(PatternEmail patternemail) throws AppException
	{
		patternEmailDAO.save(patternemail);

	}

	public void deletePatternEmail(long id) throws AppException
	{
		patternEmailDAO.deleteById(id);

	}

	public long updatePatternEmail(PatternEmail patternemail) throws AppException
	{
		patternEmailDAO.update(patternemail);
		return 0;
	}

	public PatternEmail getPatternEmailById(long id) throws AppException
	{
		return patternEmailDAO.getPatternEmailById(id);
	}

	public AgentBiz getAgentBiz()
	{
		return agentBiz;
	}

	public void setAgentBiz(AgentBiz agentBiz)
	{
		this.agentBiz = agentBiz;
	}

	public void setMailSender(MailSender mailSender)
	{
		this.mailSender = mailSender;
	}

	public void setPatternEmailDAO(PatternEmailDAO patternEmailDAO)
	{
		this.patternEmailDAO = patternEmailDAO;
	}
}
