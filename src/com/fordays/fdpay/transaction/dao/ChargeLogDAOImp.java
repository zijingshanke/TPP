package com.fordays.fdpay.transaction.dao;

import java.util.List;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import com.fordays.fdpay.transaction.ChargeLog;
import com.fordays.fdpay.transaction.ChargeLogListForm;
import com.neza.base.BaseDAOSupport;
import com.neza.base.Hql;
import com.neza.exception.AppException;

public class ChargeLogDAOImp extends BaseDAOSupport implements ChargeLogDAO {
	private TransactionTemplate chargeLogTemplate;

	public void setChargeManager(PlatformTransactionManager chargeLogManager) {
		this.chargeLogTemplate = new TransactionTemplate(chargeLogManager);
	}

	public long save(ChargeLog chargeLog) throws AppException {
		this.getHibernateTemplate().saveOrUpdate(chargeLog);
		return chargeLog.getId();
	}

	public long merge(ChargeLog chargeLog) throws AppException {
		this.getHibernateTemplate().merge(chargeLog);
		return chargeLog.getId();
	}

	public long update(ChargeLog chargeLog) throws AppException {
		if (chargeLog.getId() > 0)
			return save(chargeLog);
		else
			throw new IllegalArgumentException("id isn't a valid argument.");
	}

	public void deleteById(long id) throws AppException {
		if (id > 0) {
			ChargeLog chargeLog = (ChargeLog) this.getHibernateTemplate().get(
					ChargeLog.class, new Long(id));
			this.getHibernateTemplate().delete(chargeLog);

		}
	}

	public ChargeLog getChargeLogById(long id) throws AppException {

		ChargeLog chargeLog;
		if (id > 0) {
			chargeLog = (ChargeLog) this.getHibernateTemplate().get(
					ChargeLog.class, new Long(id));
			return chargeLog;

		} else
			return new ChargeLog();
	}

	public ChargeLog queryChargeLogById(long id) throws AppException {

		if (id > 0) {
			ChargeLog chargeLog = (ChargeLog) this.getQuery(
					"from ChargeLog where id=" + id).uniqueResult();

			if (chargeLog != null) {
				return chargeLog;
			} else {
				return new ChargeLog();
			}

		} else
			return new ChargeLog();
	}

	public List list(ChargeLogListForm tlf) throws AppException {
		Hql hql = new Hql();
		hql.add("from ChargeLog tat where 1=1");

		hql.add("  and (tat.agent.name like ? or LOWER(tat.agent.loginName) like LOWER(?))");
		hql.addParamter("%"+tlf.getAgentName().trim()+"%");
		hql.addParamter("%"+tlf.getAgentName().trim()+"%");
		String beginDate = tlf.getBeginDate();
		String endDate = tlf.getEndDate();
		if ("".equals(beginDate)==false && "".equals(endDate)==true) {
			hql.add(" and to_char(tat.chargeDate,'yyyy-mm-dd hh24:mi:ss') > ?");
			hql.addParamter(beginDate);
		}
		if ("".equals(beginDate)==true && "".equals(endDate)==false) {
			hql.add(" and to_char(tat.chargeDate,'yyyy-mm-dd hh24:mi:ss') < ?");
			hql.addParamter(endDate);
		}
		if ("".equals(beginDate)==false && "".equals(endDate)==false) {
			hql.add(" and  to_char(tat.chargeDate,'yyyy-mm-dd hh24:mi:ss')  between ? and ? ");
			hql.addParamter(beginDate);
			hql.addParamter(endDate);
		}

		
			hql.add(" and(  tat.amount >= ? and tat.amount <= ? )");
			hql.addParamter(tlf.getMinAmount());
			hql.addParamter(tlf.getMaxAmount());
		

		hql.add(" order by tat.chargeDate desc");
		System.out.println("hql===========" + hql);
		return this.list(hql, tlf);
	}

	public List getChargeLogByContent(ChargeLogListForm clf)
			throws AppException {
		Hql hql=new Hql();
		hql.add("from ChargeLog tat where tat.orderNo=? order by tat.chargeDate");
		hql.addParamter(clf.getOrderNo());
		System.out.println(hql);
		return this.list(hql, clf);
	}

}
