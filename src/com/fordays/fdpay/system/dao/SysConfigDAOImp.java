package com.fordays.fdpay.system.dao;


import java.util.List;
import org.hibernate.Query;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import com.fordays.fdpay.system.SysConfig;
import com.fordays.fdpay.system.SysConfigListForm;
import com.fordays.fdpay.system.SysLog;
import com.neza.base.BaseDAOSupport;
import com.neza.exception.AppException;


public class SysConfigDAOImp extends BaseDAOSupport implements SysConfigDAO
{
	
	private TransactionTemplate transactionTemplate;

	public void setTransactionManager(
			PlatformTransactionManager transactionManager)
		{
			this.transactionTemplate = new TransactionTemplate(transactionManager);
		}

	public SysConfig getSysConfigById(long id) throws AppException
		{

		SysConfig sysconfig;
			try{
			if (id > 0)
			{
				
				sysconfig = (SysConfig) this.getHibernateTemplate().get(SysConfig.class,
						new Long(id));
				
				return sysconfig;
			}
			else
				return new SysConfig();
			}catch(Exception ex){
					ex.printStackTrace();
				}
			return new SysConfig();
		}

	public List list(SysConfigListForm ulf) throws AppException
		{
			String hql = "from SysConfig where 1=1 ";
		
			
			return this.list(hql, ulf);
		}

	public long save(SysConfig sysconfig) throws AppException
	{
		this.getHibernateTemplate().saveOrUpdate(sysconfig);
		return sysconfig.getId();
	}

	public void deleteById(long id) throws AppException
	{
		if (id > 0)
		{
			SysConfig SysLog = (SysConfig) this.getHibernateTemplate().get(SysConfig.class,
					new Long(id));
			this.getHibernateTemplate().delete(SysLog);
		}
	}

	public long merge(SysConfig sysconfig) throws AppException
	{
		this.getHibernateTemplate().merge(sysconfig);
		return sysconfig.getId();
	}

	public long update(SysConfig sysconfig) throws AppException
	{
		if (sysconfig.getId() > 0)
			return save(sysconfig);
		else
			throw new IllegalArgumentException("id isn't a valid argument.");
	}
	



}
