package com.fordays.fdpay.agent.biz;

import java.util.List;

import com.fordays.fdpay.agent.Account;
import com.neza.exception.AppException;

public interface AgentContactBiz {

	 List getAgentContacts(Long agentId)throws AppException;
}
