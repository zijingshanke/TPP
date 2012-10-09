package com.fordays.fdpay.transaction.dao;

import java.util.List;
import org.springframework.transaction.support.TransactionTemplate;
import com.fordays.fdpay.transaction.DebitLog;
import com.fordays.fdpay.transaction.DebitLogListForm;
import com.neza.base.BaseDAOSupport;
import com.neza.base.Hql;
import com.neza.exception.AppException;
import org.hibernate.Query;
public class DebitLogDAOImp extends BaseDAOSupport implements DebitLogDAO {
	private TransactionTemplate chargeLogTemplate;

	
	public long save(DebitLog debitLog) throws AppException {
		this.getHibernateTemplate().saveOrUpdate(debitLog);
		return debitLog.getId();
	}

	public long update(DebitLog debitLog) throws AppException {
		if (debitLog.getId() > 0)
			return save(debitLog);
		else
			throw new IllegalArgumentException("id isn't a valid argument.");
	}

	public List list(DebitLogListForm dlf) throws AppException {
		Hql hql=new Hql();
		hql.add("from DebitLog d where 1=1");
		if(dlf.getAgentName()!=null && !"".equals(dlf.getAgentName())){
		hql.add("  and (d.agent.name like ? or LOWER(d.agent.loginName) like LOWER(?))");
		hql.addParamter("%" + dlf.getAgentName().trim()+"%");
		hql.addParamter("%" + dlf.getAgentName().trim()+"%");
		}
		String beginDate = dlf.getBeginDate().toString().trim();
		String endDate = dlf.getEndDate().toString().trim();
		
		if ("".equals(beginDate)==false && "".equals(endDate)==true) {
			hql.add(" and to_char(d.chargeDate,'yyyy-mm-dd hh24:mi:ss') > ?");
			hql.addParamter(beginDate);
		}
		if ("".equals(beginDate)==true && "".equals(endDate)==false) {
			hql.add(" and to_char(d.chargeDate,'yyyy-mm-dd hh24:mi:ss') < ?");
			hql.addParamter(endDate);
		}
		if ("".equals(beginDate)==false && "".equals(endDate)==false) {
			hql.add(" and  to_char(d.chargeDate,'yyyy-mm-dd hh24:mi:ss')  between ? and ? ");
			hql.addParamter(beginDate);
			hql.addParamter(endDate);
		}
	
	
			hql.add(" and(  d.amount >= ? and d.amount <= ? )");
			hql.addParamter(dlf.getMinAmount());
			hql.addParamter(dlf.getMaxAmount());
		hql.add(" order by d.chargeDate desc");
		System.out.println("hql==========="+hql);
		return this.list(hql,dlf);
	}
	public List getDebitLogByContent(DebitLogListForm clf) throws AppException {
		Hql hql=new Hql();
		hql.add("from DebitLog tat where tat.debitNo=? order by tat.chargeDate");
		hql.addParamter(clf.getOrderNo());
		System.out.println(hql);
		return this.list(hql, clf);
	}
	public List getDebitLogDetailed(DebitLogListForm clf) throws AppException {
		Hql hql=new Hql();
		hql.add("from DebitLog tat where tat.debitNo=? order by tat.chargeDate");
		hql.addParamter(clf.getOrderNo());
		System.out.println(hql);
		Query query=this.getQuery(hql);
		return query.list();
	}
	
}
