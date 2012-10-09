package com.fordays.fdpay.system.dao;

import java.util.List;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import com.fordays.fdpay.system.PatternEmail;
import com.fordays.fdpay.system.PatternEmailListForm;
import com.neza.base.BaseDAOSupport;
import com.neza.base.Hql;
import com.neza.exception.AppException;

public class PatternEmailDAOImp extends BaseDAOSupport implements
		PatternEmailDAO {
	private TransactionTemplate transactionTemplate;

	public void setTransactionManager(
			PlatformTransactionManager transactionManager) {
		this.transactionTemplate = new TransactionTemplate(transactionManager);
	}

	public long save(PatternEmail patternemail) throws AppException{
		this.getHibernateTemplate().saveOrUpdate(patternemail);
		return patternemail.getId();
	}

	public long merge(PatternEmail patternemail) throws AppException{
		this.getHibernateTemplate().merge(patternemail);
		return patternemail.getId();
	}

	public long update(PatternEmail patternemail) throws AppException{
		if (patternemail.getId() > 0)
			return save(patternemail);
		else
			throw new IllegalArgumentException("id isn't a valid argument.");
	}

	public void deleteById(long id) throws AppException{
		if (id > 0) {
			PatternEmail patternemail = (PatternEmail) this
					.getHibernateTemplate().get(PatternEmail.class,
							new Long(id));
			this.getHibernateTemplate().delete(patternemail);
		}
	}
	
	public PatternEmail getPatternEmailById(long id) throws AppException{
		PatternEmail patternemail;
		if (id > 0) {
			patternemail = (PatternEmail) this.getHibernateTemplate().get(
					PatternEmail.class, new Long(id));
			return patternemail;
		} else
			return new PatternEmail();
	}
	public PatternEmail getPatternEmailByCode(String code) throws AppException{
			Hql hql=new Hql();
			hql.add("from PatternEmail where code=?");
			hql.addParamter(code);
			PatternEmail patternemail=(PatternEmail)this.getQuery(hql).uniqueResult();
			if (patternemail!=null) {
				return patternemail;
			} else
				return null;
		}

	public List list(PatternEmailListForm pelf) throws AppException{
		String hql = "from PatternEmail";
		return this.list(hql, pelf);
	}
}
