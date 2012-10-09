package com.fordays.fdpay.transaction.dao;

import java.util.List;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import com.fordays.fdpay.transaction.DrawLog;
import com.fordays.fdpay.transaction.DrawLogListForm;
import com.neza.base.BaseDAOSupport;
import com.neza.base.Hql;
import com.neza.exception.AppException;

public class DrawLogDAOImp extends BaseDAOSupport implements DrawLogDAO {
	private TransactionTemplate drawLogTemplate;

	public void setChargeManager(
			PlatformTransactionManager drawLogManager) {
		this.drawLogTemplate = new TransactionTemplate(drawLogManager);
	}

	public long save(DrawLog  drawLog)throws AppException {
		this.getHibernateTemplate().saveOrUpdate(drawLog);
		return drawLog.getId();
	}

	public long merge(DrawLog drawLog) throws AppException{
		this.getHibernateTemplate().merge(drawLog);
		return drawLog.getId();
	}

	public long update(DrawLog drawLog) throws AppException{
		if (drawLog.getId() > 0)
			return save(drawLog);
		else
			throw new IllegalArgumentException("id isn't a valid argument.");
	}

	public void deleteById(long id) throws AppException{
		if (id > 0) {
			DrawLog drawLog = (DrawLog) this.getHibernateTemplate()
					.get(DrawLog.class, new Long(id));
			this.getHibernateTemplate().delete(drawLog);

		}
	}

	public DrawLog getDrawLogById(long id) throws AppException{

		DrawLog drawLog;
		if (id > 0) {
			drawLog = (DrawLog) this.getHibernateTemplate().get(
					DrawLog.class, new Long(id));
			return drawLog;

		} else
			return new DrawLog();
	}

	public DrawLog queryDrawLogById(long id) throws AppException{

		if (id > 0) {
			DrawLog drawLog = (DrawLog) this.getQuery(
					"from DrawLog where id=" + id).uniqueResult();
			if (drawLog != null) {
				return drawLog;
			} else {
				return new DrawLog();
			}

		} else
			return new DrawLog();
	}



	public List list(DrawLogListForm tlf) throws AppException{
		Hql hql=new Hql();
		hql.add("from DrawLog tat where 1=1");
		 
		hql.add("  and (tat.agent.name like ? or LOWER(tat.agent.loginName) like LOWER(?))");
		hql.addParamter("%" + tlf.getAgentName().trim()+"%");
		hql.addParamter("%" + tlf.getAgentName().trim()+"%");
	
		String beginDate = tlf.getBeginDate().toString().trim();
		String endDate = tlf.getEndDate().toString().trim();
		
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
		System.out.println("hql==========="+hql);
		return this.list(hql, tlf);
	}
	
	
	public List getDrawLogByContent(DrawLogListForm tlf) throws AppException{
		Hql hql=new Hql();
		hql.add("from DrawLog tat where tat.orderNo=? order by tat.chargeDate");
		hql.addParamter(tlf.getOrderNo());
		System.out.println(hql);
		return this.list(hql, tlf);
	}

}
