package com.fordays.fdpay.agent.biz;

import java.util.List;

import com.fordays.fdpay.agent.Account;
import com.neza.exception.AppException;

public interface AgentAddressBiz {
	
	 List getAgentAddersss(Long agentId)throws AppException;
}
