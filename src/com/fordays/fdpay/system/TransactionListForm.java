package com.fordays.fdpay.system;

import org.apache.struts.action.*;
import com.neza.base.ListActionForm;
import javax.servlet.http.*;

public class TransactionListForm extends ListActionForm {
	private int transactionId;
	private int agentId;
	private int agentType = 0;
	private String agentName = "";
	private String no = "";

	public int getAgentId() {
		return agentId;
	}

	public void setAgentId(int agentId) {
		this.agentId = agentId;
	}

	public int getAgentType() {
		return agentType;
	}

	public void setAgentType(int agentType) {
		this.agentType = agentType;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public void reset(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {

		thisAction = "";
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

}
