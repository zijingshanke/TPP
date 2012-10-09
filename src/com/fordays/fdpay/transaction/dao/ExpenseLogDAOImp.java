package com.fordays.fdpay.transaction.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import com.fordays.fdpay.transaction.ExpenseLog;
import com.fordays.fdpay.transaction.ExpenseLogListForm;
import com.neza.base.BaseDAOSupport;
import com.neza.base.Hql;
import com.neza.exception.AppException;

public class ExpenseLogDAOImp extends BaseDAOSupport implements ExpenseLogDAO {
	private TransactionTemplate chargeTemplate;
	
	public void setChargeManager(PlatformTransactionManager chargeManager) {
		this.chargeTemplate = new TransactionTemplate(chargeManager);
	}
	
	public Hql getHql(ExpenseLogListForm tlf){
		Hql hql=new Hql();
		hql.add("from ExpenseLog d where 1=1");
		if(tlf.getAgentName()!=null && !"".equals(tlf.getAgentName())){
			hql.add("  and (d.agent.name like ? or LOWER(d.agent.loginName) like LOWER(?))");
			hql.addParamter("%" + tlf.getAgentName().trim()+"%");
			hql.addParamter("%" + tlf.getAgentName().trim()+"%");
			}
			String beginDate = tlf.getBeginDate().toString().trim();
			String endDate = tlf.getEndDate().toString().trim();
			
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
				hql.addParamter(tlf.getMinAmount());
				hql.addParamter(tlf.getMaxAmount());
			hql.add(" order by d.chargeDate desc");
		System.out.println("hql==="+hql);
		return hql;
	}
	
	public List list(ExpenseLogListForm tlf) throws AppException {
		Hql hql=getHql(tlf);
		return this.list(hql, tlf);
	}

	public long save(ExpenseLog el) throws AppException {
		this.getHibernateTemplate().saveOrUpdate(el);
		return el.getId();
	}

	public List getExpenseLogByContent(ExpenseLogListForm clf)
			throws AppException {
		Hql hql=new Hql();
		hql.add("from ExpenseLog tat where tat.expenseNo=? order by tat.chargeDate");
		hql.addParamter(clf.getOrderNo());
		System.out.println(hql);
		return this.list(hql, clf);
	}

	public List getExpenseLogDetailed(ExpenseLogListForm clf)
			throws AppException {
		Hql hql=new Hql();
		hql.add("from ExpenseLog tat where tat.expenseNo=? order by tat.chargeDate");
		hql.addParamter(clf.getOrderNo());
		System.out.println(hql);
		Query query=this.getQuery(hql);
		return query.list();
	}

	

}
