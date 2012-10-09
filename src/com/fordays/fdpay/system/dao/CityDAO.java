package com.fordays.fdpay.system.dao;

import java.util.List;
import com.fordays.fdpay.system.City;
import com.fordays.fdpay.system.CityListForm;
import com.neza.base.BaseDAO;
import com.neza.exception.AppException;

public interface CityDAO extends BaseDAO {
	public List getCityByProvinceId(int provinceId) throws AppException;

	public List getCitys(CityListForm clf) throws AppException;

	public City getCityById(Long cityId) throws AppException;

	public City getCityByCName(String cname) throws AppException;

	public void save(City city) throws AppException;

	public void deleteById(long id) throws AppException;
}
