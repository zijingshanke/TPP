package com.fordays.fdpay.agent;

import java.sql.Timestamp;
import org.apache.struts.action.*;
import com.neza.base.ListActionForm;
import javax.servlet.http.*;

public class MailAgentListForm extends ListActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String mail;
	private String agentName;
	private String agentEmail;
	private String emails = "";
	private Timestamp createDate;

	public void reset(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		thisAction = "";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAgentEmail() {
		return agentEmail;
	}

	public void setAgentEmail(String agentEmail) {
		this.agentEmail = agentEmail;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getEmails() {
		return emails;
	}

	public String getCurrentMails() {
		String temp = "";		
		for (int i = 0; i < list.size(); i++) {
			MailAgent ma = (MailAgent)list.get(i);
			temp = temp + ma.getAgent().getEmail() + ",";
		}
		return temp;
	}

	public void setEmails(String emails) {
		this.emails = emails;
	}
}
