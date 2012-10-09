package com.fordays.fdpay.system.dao;

import java.util.List;

import org.hibernate.Query;

import com.fordays.fdpay.system.Bank;
import com.fordays.fdpay.system.BankListForm;
import com.fordays.fdpay.system.City;
import com.neza.base.BaseDAOSupport;
import com.neza.base.Hql;
import com.neza.exception.AppException;
public class BankDAOImpl extends BaseDAOSupport implements BankDAO {
	public BankDAOImpl(){
		
	}

	public List getBankByCity(City city) throws AppException {
		Hql hql=new Hql("from Bank where city.id=? ");	
		hql.addParamter(city.getId());
		Query query = this.getQuery(hql);
		return query.list();
	}

	public List getBanks(BankListForm blf) throws AppException {
		Hql hql=new Hql("from Bank where 1=1");	
		if(blf.provinceId==0){
			
		}else if(blf.cityId==0){
			hql.add(" and city.province.id =?");
			hql.addParamter(blf.getProvinceId());
		}else if(blf.getBankId()!=0){
			hql.add(" and id =?");
			hql.addParamter(blf.getBankId());
		}else if(blf.getCityId()!=0){
			hql.add(" and  city.id =?");
			hql.addParamter(blf.getCityId());
		}else if(blf.getProvinceId()!=0){
			hql.add(" and city.province.id =?");
			hql.addParamter(blf.getProvinceId());
		}
		return this.list(hql,blf);
	}
	public Bank getBankById(Long bankId) throws AppException {
		Hql hql=new Hql("from Bank where id=? ");
		hql.addParamter(bankId);
		Query query = this.getQuery(hql);
		Bank bank=null;
		if(query!=null&&query.list()!=null&&query.list().size()>0){
			bank =(Bank)query.list().get(0);
		}
		return bank;
	}

	public List getBankByCityId(int cityId) throws AppException
	{
		Hql hql=new Hql("from Bank where city.id=?");
		hql.addParamter(cityId);
		Query query = this.getQuery(hql);
		return query.list();
	}

	public void save(Bank bank) throws AppException
	{
		this.getHibernateTemplate().saveOrUpdate(bank);
		
	}

	public void deleteById(long id) throws AppException
	{
		if (id > 0)
		{
			Bank bank = (Bank) this.getHibernateTemplate().get(Bank.class,
					new Long(id));
			this.getHibernateTemplate().delete(bank);
			
		}
	}


}
