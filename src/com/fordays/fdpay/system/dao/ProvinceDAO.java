package com.fordays.fdpay.system.dao;

import java.util.List;



import com.fordays.fdpay.system.Province;
import com.fordays.fdpay.system.ProvinceListForm;
import com.neza.base.BaseDAO;
import com.neza.exception.AppException;

public interface ProvinceDAO extends BaseDAO{
	List getProvinces(ProvinceListForm plf)throws AppException; 
	List getProvinces()throws AppException; 
	void  save(Province province) throws AppException;
	public Province getProvinceById(Long provinceId) throws AppException;
	public void deleteById(long id) throws AppException;
}
