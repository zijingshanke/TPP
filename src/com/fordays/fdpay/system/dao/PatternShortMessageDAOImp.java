package com.fordays.fdpay.system.dao;

import java.util.List;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import com.fordays.fdpay.system.PatternShortMessage;
import com.fordays.fdpay.system.PatternShortMessageListForm;
import com.neza.base.BaseDAOSupport;
import com.neza.exception.AppException;

public class PatternShortMessageDAOImp extends BaseDAOSupport implements
		PatternShortMessageDAO {
	private TransactionTemplate transactionTemplate;

	public void setTransactionManager(
			PlatformTransactionManager transactionManager) {
		this.transactionTemplate = new TransactionTemplate(transactionManager);
	}

	public long save(PatternShortMessage patternShortMessage)
			throws AppException {
		this.getHibernateTemplate().saveOrUpdate(patternShortMessage);
		return patternShortMessage.getId();
	}

	public long merge(PatternShortMessage patternShortMessage)
			throws AppException {
		this.getHibernateTemplate().merge(patternShortMessage);
		return patternShortMessage.getId();
	}

	public long update(PatternShortMessage patternShortMessage)
			throws AppException {
		if (patternShortMessage.getId() > 0)
			return save(patternShortMessage);
		else
			throw new IllegalArgumentException("id isn't a valid argument.");
	}

	public void deleteById(long id) throws AppException {
		if (id > 0) {
			PatternShortMessage patternShortMessage = (PatternShortMessage) this
					.getHibernateTemplate().get(PatternShortMessage.class,
							new Long(id));
			this.getHibernateTemplate().delete(patternShortMessage);
		}
	}

	public PatternShortMessage getPatternShortMessageById(long id)
			throws AppException {
		PatternShortMessage patternShortMessage;
		if (id > 0) {
			patternShortMessage = (PatternShortMessage) this
					.getHibernateTemplate().get(PatternShortMessage.class,
							new Long(id));
			return patternShortMessage;
		} else
			return new PatternShortMessage();
	}

	public List list(PatternShortMessageListForm psmlf) throws AppException {
		String hql = "from PatternShortMessage";
		return this.list(hql, psmlf);
	}
}
