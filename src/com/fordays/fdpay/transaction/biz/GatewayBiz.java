package com.fordays.fdpay.transaction.biz;

import com.fordays.fdpay.agent.Agent;
import com.neza.exception.AppException;

public interface GatewayBiz {
	public boolean validatePartner(String partner) throws AppException;
	public Agent getAgent(Agent agent)throws AppException;
	public boolean validateAmount(Agent agent,double balance) throws AppException;
}
