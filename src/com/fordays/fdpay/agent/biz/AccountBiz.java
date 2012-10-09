package com.fordays.fdpay.agent.biz;

import java.util.List;

import com.fordays.fdpay.agent.Account;
import com.neza.exception.AppException;

public interface AccountBiz {
	void saveAccount(Account account) throws AppException;
	void updateAccount(Account account) throws AppException;
	Account getAccountByBanknum(String banknum)throws AppException;
	Account getAccountByAgentIdcertificationStatus_NoPass(Long agentId)throws AppException;
	List getAccountByAgentIdcertificationStatus_Pass(Long agentId)throws AppException;;
	Account getAccountByBind(Long agentId,Long bindStatus)throws AppException;
	Account getAccountById(Long accountId) throws AppException;
	List getAccounts(Long agentId)throws AppException;
}
