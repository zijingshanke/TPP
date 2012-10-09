package com.fordays.fdpay.system.biz;


import java.util.List;

import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import com.fordays.fdpay.system.LoginLog;
import com.fordays.fdpay.system.LoginLogListForm;
import com.fordays.fdpay.system.dao.LoginLogDAO;
import com.neza.exception.AppException;

public class LoginLogBizImpl implements LoginLogBiz {

	private LoginLogDAO loginlogDao;
	private TransactionTemplate transactionTemplate;

	public void setTransactionManager(
			HibernateTransactionManager transactionManager) {
		this.transactionTemplate = new TransactionTemplate(transactionManager);
	}



	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}



	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}



	public void saveLoginLog(LoginLog loginlog) throws AppException {
		loginlogDao.save(loginlog);
	}



	public void setLoginlogDao(LoginLogDAO loginlogDao) {
		this.loginlogDao = loginlogDao;
	}



	public List getLoginLogs(LoginLogListForm lllf) throws AppException {
		// TODO Auto-generated method stub
		return loginlogDao.list(lllf);
		
	}







}
