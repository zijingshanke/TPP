package com.fordays.fdpay.system;

import com.fordays.fdpay.system._entity._PatternEmail;

public class PatternEmail extends _PatternEmail
{
	private static final long serialVersionUID = 1L;
private String subject="";
	public String getStatusCaption()
		{
			if (this.status.intValue() == 1)
				return "启用";
			else
				return "停用";
		}

	private String mails;

	public String getContent()
		{
			if (content == null)
				return "";
			else
				return content;
		}

	public String getMails()
		{
			return mails;
		}

	public void setMails(String mails)
		{
			this.mails = mails;
		}

	public String getSubject()
  {
  	return subject;
  }

	public void setSubject(String subject)
  {
  	this.subject = subject;
  }

}
