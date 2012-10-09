package com.fordays.fdpay.transaction.dao;

import java.util.List;
import org.hibernate.Query;
import org.springframework.transaction.support.TransactionTemplate;
import com.fordays.fdpay.transaction.Debit;
import com.fordays.fdpay.transaction.DebitListForm;
import com.fordays.fdpay.transaction.Expense;
import com.neza.base.BaseDAOSupport;
import com.neza.base.Hql;
import com.neza.exception.AppException;

public class DebitDAOImp extends BaseDAOSupport implements DebitDAO {
	private TransactionTemplate chargeTemplate;

	public long save(Debit debit) throws AppException {
		this.getHibernateTemplate().saveOrUpdate(debit);
		return debit.getId();
	}
	
	public Hql getHql(DebitListForm dlf) throws AppException{
		Hql hql=new Hql();
		hql.add("from Debit d where 1=1");
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
	
	public List getAllDebitList(DebitListForm dlf) throws AppException {
		Hql hql=getHql(dlf);
		Query query=this.getQuery(hql);
		return query.list();
	}

	
	public List list(DebitListForm dlf) throws AppException {
		Hql hql=getHql(dlf);
		return this.list(hql, dlf);
	}

	public Debit getDebitById(long id) throws AppException {
		Debit debit;
		if (id > 0) {
			debit = (Debit) this.getHibernateTemplate().get(Debit.class,
					new Long(id));
			return debit;

		} else
			return new Debit();
	}

	
	
	public long update(Debit debit) throws AppException {
		if (debit.getId() > 0)
			return save(debit);
		else
			throw new IllegalArgumentException("id isn't a valid argument.");
	}

	public Debit getDebitByNo(String no) throws AppException {
		Hql hql=new Hql();
		hql.add("from Debit tat where tat.no=?");
		hql.addParamter(no);
		Debit debit=(Debit)this.getQuery(hql).uniqueResult();
		if(debit!=null)
		return debit;
		else
			return new Debit();
	}

	public Expense getExpenseByDebitId(long id){
		Hql hql=new Hql();
		hql.add("select new com.fordays.fdpay.transaction.Expense(tat.status) from Expense tat where tat.debit.id=?");
		hql.addParamter(id);
		Expense expense=(Expense)this.getQuery(hql).uniqueResult();
		if(expense!=null)
		return expense;
		else
			return new Expense();
	}
		


}
