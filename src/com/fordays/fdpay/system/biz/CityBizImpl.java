package com.fordays.fdpay.system.biz;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import com.fordays.fdpay.system.BankListForm;
import com.fordays.fdpay.system.City;
import com.fordays.fdpay.system.CityListForm;
import com.fordays.fdpay.system.Province;
import com.fordays.fdpay.system.SysConfig;
import com.fordays.fdpay.system.SysConfigListForm;
import com.fordays.fdpay.system.SysLog;
import com.fordays.fdpay.system.SysLogListForm;
import com.fordays.fdpay.system.dao.BankDAO;
import com.fordays.fdpay.system.dao.CityDAO;
import com.fordays.fdpay.system.dao.SysConfigDAO;
import com.fordays.fdpay.system.dao.SysLogDAO;
import com.fordays.fdpay.user.SysUser;
import com.fordays.fdpay.user.UserListForm;
import com.fordays.fdpay.user.dao.UserDAO;
import com.neza.exception.AppException;

public class CityBizImpl implements CityBiz {

	private CityDAO cityDAO;

	private TransactionTemplate transactionTemplate;

	public void setTransactionManager(
			HibernateTransactionManager transactionManager) {
		this.transactionTemplate = new TransactionTemplate(transactionManager);
	}

	public CityDAO getCityDAO()
	{
		return cityDAO;
	}

	public void setCityDAO(CityDAO cityDAO)
	{
		this.cityDAO = cityDAO;
	}

	public List getCitys(int provinceId) throws AppException
	{
		// TODO Auto-generated method stub
		return cityDAO.getCityByProvinceId(provinceId);
	}

	public List getCitys(CityListForm clf) throws AppException
	{
		// TODO Auto-generated method stub
		return cityDAO.getCitys(clf);
	}

	public void addCity(City city) throws AppException
	{
		cityDAO.save(city);
		
	}

	public long deleteCityById(long cityId) throws AppException
	{
		try{
			cityDAO.deleteById(cityId);
			return 1;
		}catch(Exception ex){
			return 0;
		}
		
	}

	public City getCityById(long id) throws AppException
	{
		
		return cityDAO.getCityById(id);
	}

	
	
}
