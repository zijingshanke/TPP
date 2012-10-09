package com.fordays.fdpay.system.dao;

import java.util.List;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import com.fordays.fdpay.transaction.TransactionStatus;
import com.fordays.fdpay.system.TransactionStatusListForm;
import com.neza.base.BaseDAOSupport;
import com.neza.exception.AppException;

public class TransactionStatusDAOImp extends BaseDAOSupport implements
		TransactionStatusDAO {
	private TransactionTemplate transactionTemplate;

	public void setTransactionManager(
			PlatformTransactionManager transactionManager) {
		this.transactionTemplate = new TransactionTemplate(transactionManager);
	}

	public long save(TransactionStatus transactionStatus) throws AppException {
		this.getHibernateTemplate().saveOrUpdate(transactionStatus);
		return transactionStatus.getId();
	}

	public long merge(TransactionStatus transactionStatus) throws AppException  {
		this.getHibernateTemplate().merge(transactionStatus);
		return transactionStatus.getId();
	}

	public long update(TransactionStatus transactionStatus) throws AppException {
		if (transactionStatus.getId() > 0)
			return save(transactionStatus);
		else
			throw new IllegalArgumentException("id isn't a valid argument.");
	}

	public void deleteById(long id) throws AppException{
		if (id > 0) {
			TransactionStatus transactionStatus = (TransactionStatus) this
					.getHibernateTemplate().get(TransactionStatus.class,
							new Long(id));
			this.getHibernateTemplate().delete(transactionStatus);
		}
	}

	public TransactionStatus getTransactionStatusById(long id) throws AppException{
		TransactionStatus transactionStatus;
		if (id > 0) {
			transactionStatus = (TransactionStatus) this.getHibernateTemplate()
					.get(TransactionStatus.class, new Long(id));
			return transactionStatus;
		} else
			return new TransactionStatus();
	}

	public List list(TransactionStatusListForm tslf) throws AppException{
		String hql = "from TransactionStatus";
		return this.list(hql, tslf);
	}
}
