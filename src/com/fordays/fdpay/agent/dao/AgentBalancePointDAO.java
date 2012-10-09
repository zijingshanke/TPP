package com.fordays.fdpay.agent.dao;

import java.util.List;

import com.fordays.fdpay.agent.AgentBalancePointListForm;
import com.neza.base.BaseDAO;
import com.neza.exception.AppException;

public interface AgentBalancePointDAO extends BaseDAO{
	
	public void addAgentToAgentBalancePoint()throws AppException;
	public boolean truncateAgentBalancePoint() throws AppException;
	public List listAgentBalancePoint(AgentBalancePointListForm alf) throws AppException;
	public void updateAgentBalancePointAmount(String date) throws AppException;
	

}
