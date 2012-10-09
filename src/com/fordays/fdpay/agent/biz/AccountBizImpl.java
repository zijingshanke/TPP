package com.fordays.fdpay.agent.biz;

import java.util.List;

import com.fordays.fdpay.agent.Account;
import com.fordays.fdpay.agent.dao.AccountDAO;
import com.neza.exception.AppException;

public class AccountBizImpl implements AccountBiz {
	private AccountDAO accountDAO;

	public void saveAccount(Account account) throws AppException {
		accountDAO.saveAccount(account);
	}

	public void updateAccount(Account account) throws AppException {
		accountDAO.updateAccount(account);
	}

	public Account getAccountByBanknum(String banknum) throws AppException {
		return this.accountDAO.getAccountByBanknum(banknum);
	}

	public Account getAccountByAgentIdcertificationStatus_NoPass(Long agentId)
			throws AppException {
		return accountDAO
				.getAccountByAgentIdcertificationStatus_NoPass(agentId);
	}

	public List getAccountByAgentIdcertificationStatus_Pass(Long agentId)
			throws AppException {
		return accountDAO.getAccountByAgentIdcertificationStatus_Pass(agentId);
	}

	public Account getAccountByBind(Long agentId, Long bindStatus)
			throws AppException {
		return accountDAO.getAccountByBind(agentId, bindStatus);
	}

	public Account getAccountById(Long accountId) throws AppException {
		return accountDAO.getAccountById(accountId);
	}

	public List getAccounts(Long agentId) throws AppException {
		return accountDAO.getAccounts(agentId);
	}

	public void setAccountDAO(AccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}
}
