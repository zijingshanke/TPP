package com.fordays.fdpay.transaction.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import com.fordays.fdpay.transaction.Charge;
import com.fordays.fdpay.transaction.Debit;
import com.fordays.fdpay.transaction.DebitListForm;
import com.fordays.fdpay.transaction.Draw;
import com.fordays.fdpay.transaction.Expense;
import com.fordays.fdpay.transaction.ExpenseListForm;
import com.neza.base.BaseDAOSupport;
import com.neza.base.Hql;
import com.neza.exception.AppException;

public class ExpenseDAOImp extends BaseDAOSupport implements ExpenseDAO {
	private TransactionTemplate chargeTemplate;

	public long save(Expense expense) throws AppException {
		this.getHibernateTemplate().saveOrUpdate(expense);
		return expense.getId();
	}
	
	public Hql getHql(ExpenseListForm dlf) throws AppException{
		Hql hql=new Hql();
		hql.add("from Expense d where 1=1");
		if(dlf.getAgentName()!=null && !"".equals(dlf.getAgentName())){
			hql.add(" and (d.agent.name like ? or LOWER(d.agent.loginName) like LOWER(?) or d.fromAgent.name like ? or LOWER(d.fromAgent.loginName) like LOWER(?))");
			hql.addParamter("%"+dlf.getAgentName().trim()+"%");
			hql.addParamter("%"+dlf.getAgentName().trim()+"%");
			hql.addParamter("%"+dlf.getAgentName().trim()+"%");
			hql.addParamter("%"+dlf.getAgentName().trim()+"%");
		}
		if(dlf.getOrderNo()!=null && !"".equals(dlf.getOrderNo())){
			hql.add(" and d.no like ?");
			hql.addParamter("%"+dlf.getOrderNo().trim()+"%");
		}
		String beginDate = dlf.getBeginDate();
		String endDate = dlf.getEndDate();
		if ("".equals(beginDate) == false && "".equals(endDate) == true) {
			hql
					.add(" and to_char(d.applyDate,'yyyy-mm-dd hh24:mi:ss') > ?");
			hql.addParamter(beginDate);
		}
		if ("".equals(beginDate) == true && "".equals(endDate) == false) {
			hql
					.add(" and to_char(d.applyDate,'yyyy-mm-dd hh24:mi:ss') < ?");
			hql.addParamter(endDate);
		}
		if ("".equals(beginDate) == false && "".equals(endDate) == false) {
			hql
					.add(" and  to_char(d.applyDate,'yyyy-mm-dd hh24:mi:ss')  between ? and ? ");
			hql.addParamter(beginDate);
			hql.addParamter(endDate);
		}
		hql.add(" and(  d.amount >= ? and d.amount <= ? )");
		hql.addParamter(dlf.getMinAmount());
		hql.addParamter(dlf.getMaxAmount());
		if (dlf.getStatus() != 99) {
			hql.add(" and d.status = ?");
			hql.addParamter(dlf.getStatus());
		}
		hql.add(" order by d.applyDate desc");
		System.out.println(">>>>>>>>>>>>>>"+hql);
		return hql;
		
	}
	
	
	public List list(ExpenseListForm dlf) throws AppException {
		Hql hql=getHql(dlf);
		return this.list(hql, dlf);
	}
	public List getAllExpenseList(ExpenseListForm elf) throws AppException {
		Hql hql=getHql(elf);
		Query query =this.getQuery(hql);
		return query.list();
	}

	public Expense getExpenseById(long id) throws AppException {
		Expense expense;
		if (id > 0) {
			expense = (Expense) this.getHibernateTemplate().get(Expense.class,
					new Long(id));
			return expense;

		} else
			return new Expense();
	}

	
	
	public long update(Expense expense) throws AppException {
		if (expense.getId() > 0)
			return save(expense);
		else
			throw new IllegalArgumentException("id isn't a valid argument.");
	}


	public Expense getExpenseByNo(String no) throws AppException {
		Hql hql=new Hql();
		hql.add("from Expense tat where tat.no=?");
		hql.addParamter(no);
		Expense expense=(Expense)this.getQuery(hql).uniqueResult();
		if(expense!=null)
		return expense;
		else
			return new Expense();
	}

	
}
