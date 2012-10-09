package com.fordays.fdpay.system.biz;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import com.fordays.fdpay.system.BankListForm;
import com.fordays.fdpay.system.Province;
import com.fordays.fdpay.system.ProvinceListForm;
import com.fordays.fdpay.system.SysConfig;
import com.fordays.fdpay.system.SysConfigListForm;
import com.fordays.fdpay.system.SysLog;
import com.fordays.fdpay.system.SysLogListForm;
import com.fordays.fdpay.system.dao.BankDAO;
import com.fordays.fdpay.system.dao.ProvinceDAO;
import com.fordays.fdpay.system.dao.SysConfigDAO;
import com.fordays.fdpay.system.dao.SysLogDAO;
import com.fordays.fdpay.user.SysUser;
import com.fordays.fdpay.user.UserListForm;
import com.fordays.fdpay.user.dao.UserDAO;
import com.neza.exception.AppException;

public class ProvinceBizImpl implements ProvinceBiz {

	private ProvinceDAO provinceDAO;

	private TransactionTemplate transactionTemplate;

	public void setTransactionManager(
			HibernateTransactionManager transactionManager) {
		this.transactionTemplate = new TransactionTemplate(transactionManager);
	}

	public ProvinceDAO getProvinceDAO()
	{
		return provinceDAO;
	}

	public void setProvinceDAO(ProvinceDAO provinceDAO)
	{
		this.provinceDAO = provinceDAO;
	}

	public List getProvinces(ProvinceListForm plf) throws AppException
	{
		// TODO Auto-generated method stub
		return provinceDAO.getProvinces(plf);
	}
	public List getProvinces() throws AppException
	{
		// TODO Auto-generated method stub
		return provinceDAO.getProvinces();
	}

	public void addProvinces(Province province) throws AppException
	{
		 provinceDAO.save(province);
	}

	public long deleteProvincesById(long id) throws AppException
	{ 
		try{
			provinceDAO.deleteById(id);
			return 1;
			
		}catch(Exception ex){
			return 0;
		} 
		
	}

	public Province getProvinceById(long id) throws AppException
	{
		// TODO Auto-generated method stub
		return provinceDAO.getProvinceById(id);
	}



	
}
