package com.fordays.fdpay.system.biz;


import java.util.List;

import com.fordays.fdpay.system.BankListForm;
import com.fordays.fdpay.system.City;
import com.fordays.fdpay.system.CityListForm;
import com.fordays.fdpay.system.Province;
import com.neza.exception.AppException;




public interface CityBiz {


	public List getCitys(int provinceId) throws AppException;
	public List getCitys(CityListForm clf) throws AppException;
	public void addCity(City city) throws AppException;
	public long deleteCityById(long cityId) throws AppException;
	public City getCityById(long id) throws AppException;
}
