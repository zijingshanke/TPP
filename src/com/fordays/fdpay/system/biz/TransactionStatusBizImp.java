package com.fordays.fdpay.system.biz;

import java.util.List;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import com.fordays.fdpay.transaction.TransactionStatus;
import com.fordays.fdpay.system.TransactionStatusListForm;
import com.fordays.fdpay.system.dao.TransactionStatusDAO;
import com.neza.exception.AppException;



public class TransactionStatusBizImp implements TransactionStatusBiz {

	private TransactionStatusDAO transactionStatusDAO;
	//----------------
	private TransactionTemplate transactionTemplate;

	public void setTransactionManager(
			HibernateTransactionManager transactionManager) {
		this.transactionTemplate = new TransactionTemplate(transactionManager);
	}
	//-----------------------
	public void setTransactionStatusDAO(TransactionStatusDAO transactionStatusDAO) {
		this.transactionStatusDAO = transactionStatusDAO;
	}

	public List getTransactionStatuss(TransactionStatusListForm tslf) throws AppException {		
		return transactionStatusDAO.list(tslf);
	}

	public void saveTransactionStatus(TransactionStatus user) throws AppException {
		transactionStatusDAO.save(user);

	}

	public void deleteTransactionStatus(long id) throws AppException {
		transactionStatusDAO.deleteById(id);
		
	}

	public long updateTransactionStatus(TransactionStatus transactionstatus) throws AppException {
		transactionStatusDAO.update(transactionstatus);
		return 0;
	}
	public TransactionStatus getTransactionStatusById(long id) throws AppException{
		return transactionStatusDAO.getTransactionStatusById(id);
	}
}
