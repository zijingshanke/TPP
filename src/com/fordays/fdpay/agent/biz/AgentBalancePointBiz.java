package com.fordays.fdpay.agent.biz;

import java.util.List;

import com.fordays.fdpay.agent.AgentBalancePointListForm;
import com.neza.exception.AppException;

public interface AgentBalancePointBiz {
	
	public void addAgentToAgentBalancePoint()throws AppException;
	public boolean truncateAgentBalancePoint() throws AppException;
	public List listAgentBalancePoint(AgentBalancePointListForm alf) throws AppException;
	public void updateAgentBalancePointAmount(String date) throws AppException;

}
