package com.fordays.fdpay.transaction.dao;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Query;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import com.fordays.fdpay.transaction.Charge;
import com.fordays.fdpay.transaction.ChargeListForm;
import com.neza.base.BaseDAOSupport;
import com.neza.base.Hql;
import com.neza.exception.AppException;

public class ChargeDAOImp extends BaseDAOSupport implements ChargeDAO {
	private TransactionTemplate chargeTemplate;

	public void setChargeManager(PlatformTransactionManager chargeManager) {
		this.chargeTemplate = new TransactionTemplate(chargeManager);
	}

	public long save(Charge charge) throws AppException {
		this.getHibernateTemplate().saveOrUpdate(charge);
		return charge.getId();
	}

	public long merge(Charge charge) throws AppException {
		this.getHibernateTemplate().merge(charge);
		return charge.getId();
	}

	public long update(Charge charge) throws AppException {
		if (charge.getId() > 0)
			return save(charge);
		else
			throw new IllegalArgumentException("id isn't a valid argument.");
	}

	public void deleteById(long id) throws AppException {
		if (id > 0) {
			Charge charge = (Charge) this.getHibernateTemplate().get(
					Charge.class, new Long(id));
			this.getHibernateTemplate().delete(charge);

		}
	}

	public Charge getChargeById(long id) throws AppException {

		Charge charge;
		if (id > 0) {
			charge = (Charge) this.getHibernateTemplate().get(Charge.class,
					new Long(id));
			return charge;

		} else
			return new Charge();
	}

	public Charge queryChargeById(long id) throws AppException {

		if (id > 0) {

			Charge charge = (Charge) this.getQuery(
					"from Transaction where id=" + id).uniqueResult();
			if (charge != null) {
				return charge;
			} else {
				return new Charge();
			}

		} else
			return new Charge();
	}

	public Hql getChargeHQL(ChargeListForm tlf ){
		Hql hql = new Hql();
		hql.add("from Charge tat where 1=1");

		hql.add("  and (tat.agent.name like ? or LOWER(tat.agent.loginName) like LOWER(?))");
		hql.addParamter("%" + tlf.getAgentName().trim()+"%");
		hql.addParamter("%" + tlf.getAgentName().trim()+"%");
	
		String beginDate = tlf.getBeginDate().toString().trim();
		String endDate = tlf.getEndDate().toString().trim();
		
		if ("".equals(beginDate)==false && "".equals(endDate)==true) {
			hql.add(" and to_char(tat.chargeDate,'yyyy-mm-dd hh24:mi:ss') >= ?");
			hql.addParamter(beginDate);
		}
		if ("".equals(beginDate)==true && "".equals(endDate)==false) {
			hql.add(" and to_char(tat.chargeDate,'yyyy-mm-dd hh24:mi:ss') <= ?");
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
		
		if(tlf.getOrderNo()!=null && !"".equals(tlf.getOrderNo())){
			hql.add(" and tat.orderNo like ?");
			hql.addParamter("%" + tlf.getOrderNo().trim()+"%");
		}
		
		hql.add(" and tat.type not in(?,?)");
		hql.addParamter(Charge.CHARGE_TYPE_BACKGROUND);
		hql.addParamter(Charge.CHARGE_TYPE_CREDIT);
		
		if(!tlf.getBank().equals("0")){
			hql.add(" and tat.bank=?");
			hql.addParamter(tlf.getBank());
		}
		
		if( tlf.getStatus()!=99){
			if(tlf.getStatus()==0){
				hql.add(" and tat.status in (0,3,6,8,7)");
			}
			else if(tlf.getStatus()==2){
				hql.add(" and tat.status in (2,5)");
			}else
			{
				hql.add(" and tat.status=?");
				hql.addParamter(tlf.getStatus());
			}
		}
	
		hql.add(" order by tat.chargeDate desc");
		System.out.println("hql===========" + hql);
		return hql;
	}
	
	
	public List list(ChargeListForm tlf) throws AppException {
		Hql hql=this.getChargeHQL(tlf);
		return this.list(hql, tlf);
	}
	public List getAllChargeList(ChargeListForm cf) throws AppException{
		Hql hql = this.getChargeHQL(cf);
		Query query=this.getQuery(hql);
		return query.list();
	}
	
	public Hql getOtherChargeHQL(ChargeListForm cf){
		Hql hql = new Hql();
		hql.add("from Charge tat where 1=1");

		hql.add("  and (tat.agent.name like ? or LOWER(tat.agent.loginName) like LOWER(?))");
		hql.addParamter("%" + cf.getAgentName().trim()+"%");
		hql.addParamter("%" + cf.getAgentName().trim()+"%");
	
		String beginDate = cf.getBeginDate().toString().trim();
		String endDate = cf.getEndDate().toString().trim();
		
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
			hql.addParamter(cf.getMinAmount());
			hql.addParamter(cf.getMaxAmount());
		
			if(cf.getOrderNo()!=null && !"".equals(cf.getOrderNo())){
				hql.add(" and tat.orderNo like ?");
				hql.addParamter("%" + cf.getOrderNo().trim()+"%");
			}
			
		hql.add(" and tat.type in (?,?)");
		hql.addParamter(Charge.CHARGE_TYPE_BACKGROUND);
		hql.addParamter(Charge.CHARGE_TYPE_CREDIT);
		
		if(!cf.getBank().equals("0")){
			hql.add(" and tat.bank=?");
			hql.addParamter(cf.getBank());
		}	
			
		if( cf.getStatus()!=99){
			hql.add(" and tat.status=?");
			hql.addParamter(cf.getStatus());
		}
	
		hql.add(" order by tat.id desc");
		System.out.println("hql===========" + hql);
		return hql;
	}
	
	
	public List getAllOtherChargeList(ChargeListForm cf) throws AppException{
		Hql hql=this.getOtherChargeHQL(cf);
		Query query=this.getQuery(hql);
		return query.list();
	}
	public List getOtherCharges(ChargeListForm tlf) throws AppException {
		Hql hql=this.getOtherChargeHQL(tlf);
		return this.list(hql, tlf);
	}

	public Charge getChargeByOrderNo(String orderNo) throws AppException {
		Hql hql=new Hql();
		hql.add("from Charge tat where tat.orderNo=?");
		hql.addParamter(orderNo);
		Charge charge=(Charge)this.getQuery(hql).uniqueResult();
		if(charge!=null)
		return charge;
		else
			return new Charge();
	}

	public long getCountByChargeBank(long app) throws AppException {
		Hql hql=new Hql();
		hql.add("select count(*) from Charge tat where tat.bank=? and tat.status=?");
		hql.addParamter("OTHER");
		hql.addParamter(app);
		Query query = this.getQuery(hql);
		long count=Long.parseLong( query.uniqueResult().toString());
			return count;
	}

	public Hql getAllChargeHQL(ChargeListForm cf){
		Hql hql = new Hql();
		hql.add("from Charge tat where 1=1");

		hql.add("  and (tat.agent.name like ? or LOWER(tat.agent.loginName) like LOWER(?))");
		hql.addParamter("%" + cf.getAgentName().trim()+"%");
		hql.addParamter("%" + cf.getAgentName().trim()+"%");
	
		String beginDate = cf.getBeginDate().toString().trim();
		String endDate = cf.getEndDate().toString().trim();
		
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
			hql.addParamter(cf.getMinAmount());
			hql.addParamter(cf.getMaxAmount());
		
		if(cf.getOrderNo()!=null && !"".equals(cf.getOrderNo())){
			hql.add(" and tat.orderNo like ?");
			hql.addParamter("%" + cf.getOrderNo().trim()+"%");
		}
			
		if(!cf.getBank().equals("0")){
			hql.add(" and tat.bank=?");
			hql.addParamter(cf.getBank());
		}
			
		if( cf.getStatus()!=99){
			if(cf.getStatus()==0){
				hql.add(" and tat.status in (0,3,6,8,7)");
			}
			else if(cf.getStatus()==2){
				hql.add(" and tat.status in (2,5)");
			}else
			{
				hql.add(" and tat.status=?");
				hql.addParamter(cf.getStatus());
			}
		}
	
		hql.add(" order by tat.id desc");
		System.out.println("hql===========" + hql);
		return hql;
	}
	
	
	public List getAllChargePaging(ChargeListForm tlf) throws AppException {
		Hql hql=getAllChargeHQL(tlf);
		return this.list(hql,tlf);
	}
	public List getAllChargeNoPaging(ChargeListForm tlf) throws AppException {
		Hql hql=getAllChargeHQL(tlf);
		Query query=this.getQuery(hql);
		return query.list();
	}

	public BigDecimal getSumAmountByCharge(ChargeListForm cf)
			throws AppException {
		Hql hql = new Hql();
		hql.add("select sum(tat.amount) from Charge tat where 1=1");

		hql.add("  and (tat.agent.name like ? or LOWER(tat.agent.loginName) like LOWER(?))");
		hql.addParamter("%" + cf.getAgentName().trim()+"%");
		hql.addParamter("%" + cf.getAgentName().trim()+"%");
	
		String beginDate = cf.getBeginDate().toString().trim();
		String endDate = cf.getEndDate().toString().trim();
		
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
			hql.addParamter(cf.getMinAmount());
			hql.addParamter(cf.getMaxAmount());
		
		if(cf.getOrderNo()!=null && !"".equals(cf.getOrderNo())){
			hql.add(" and tat.orderNo like ?");
			hql.addParamter("%" + cf.getOrderNo().trim()+"%");
		}
			
		if(!cf.getBank().equals("0")){
			hql.add(" and tat.bank=?");
			hql.addParamter(cf.getBank());
		}
			
		if( cf.getStatus()!=99){
			if(cf.getStatus()==0){
				hql.add(" and tat.status in (0,3,6,8,7)");
			}
			else if(cf.getStatus()==2){
				hql.add(" and tat.status in (2,5)");
			}else
			{
				hql.add(" and tat.status=?");
				hql.addParamter(cf.getStatus());
			}
		}
		
		System.out.println(hql+"------------");
		Query query=this.getQuery(hql);
		BigDecimal sumChargeAmount=(BigDecimal)query.list().get(0);
		if(sumChargeAmount!=null)
			return sumChargeAmount;
		else
			return new BigDecimal(0);
	}
	
	
	public BigDecimal getSumAmountByGeneralCharge(ChargeListForm cf)
	throws AppException {
		Hql hql = new Hql();
		hql.add("select sum(tat.amount) from Charge tat where 1=1");
		
		hql.add("  and (tat.agent.name like ? or LOWER(tat.agent.loginName) like LOWER(?))");
		hql.addParamter("%" + cf.getAgentName().trim()+"%");
		hql.addParamter("%" + cf.getAgentName().trim()+"%");
		
		String beginDate = cf.getBeginDate().toString().trim();
		String endDate = cf.getEndDate().toString().trim();
		
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
		
		hql.add(" and tat.type<>?");
		hql.addParamter(Charge.CHARGE_TYPE_BACKGROUND);
		
		hql.add(" and(  tat.amount >= ? and tat.amount <= ? )");
		hql.addParamter(cf.getMinAmount());
		hql.addParamter(cf.getMaxAmount());
		
		if(cf.getOrderNo()!=null && !"".equals(cf.getOrderNo())){
			hql.add(" and tat.orderNo like ?");
			hql.addParamter("%" + cf.getOrderNo().trim()+"%");
		}
		
		if(!cf.getBank().equals("0")){
			hql.add(" and tat.bank=?");
			hql.addParamter(cf.getBank());
		}
		
		if( cf.getStatus()!=99){
			if(cf.getStatus()==0){
				hql.add(" and tat.status in (0,3,6,8,7)");
			}
			else if(cf.getStatus()==2){
				hql.add(" and tat.status in (2,5)");
			}else
			{
				hql.add(" and tat.status=?");
				hql.addParamter(cf.getStatus());
			}
		}
		
		System.out.println(hql+"------------");
		Query query=this.getQuery(hql);
		BigDecimal sumChargeAmount=(BigDecimal)query.list().get(0);
		if(sumChargeAmount!=null)
			return sumChargeAmount;
		else
			return new BigDecimal(0);
	}


}
