package com.fordays.fdpay.system.biz;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import com.fordays.fdpay.system.Bank;
import com.fordays.fdpay.system.BankListForm;
import com.fordays.fdpay.system.SysConfig;
import com.fordays.fdpay.system.SysConfigListForm;
import com.fordays.fdpay.system.SysLog;
import com.fordays.fdpay.system.SysLogListForm;
import com.fordays.fdpay.system.dao.BankDAO;
import com.fordays.fdpay.system.dao.SysConfigDAO;
import com.fordays.fdpay.system.dao.SysLogDAO;
import com.fordays.fdpay.user.SysUser;
import com.fordays.fdpay.user.UserListForm;
import com.fordays.fdpay.user.dao.UserDAO;
import com.neza.exception.AppException;

public class BankBizImpl implements BankBiz {

	private BankDAO bankDAO;

	private TransactionTemplate transactionTemplate;

	public void setTransactionManager(
			HibernateTransactionManager transactionManager) {
		this.transactionTemplate = new TransactionTemplate(transactionManager);
	}

	public List getBanks(BankListForm blf) throws AppException
	{
		// TODO Auto-generated method stub
		return bankDAO.getBanks(blf);
	}

	public void setBankDAO(BankDAO bankDAO)
	{
		this.bankDAO = bankDAO;
	}

	public List getBanksByCityId(int cityId) throws AppException
	{
		// TODO Auto-generated method stub
		return bankDAO.getBankByCityId(cityId);
	}

	public void addBank(Bank bank) throws AppException
	{
		bankDAO.save(bank);
		
	}

	public long deleteBankById(long id) throws AppException
	{
		try{
			bankDAO.deleteById(id);
			return 1;
		}catch(Exception ex){
			return 0;	
		}
		
	}

	
}
