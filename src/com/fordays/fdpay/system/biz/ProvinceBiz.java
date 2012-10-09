package com.fordays.fdpay.system.biz;

import java.util.List;
import com.fordays.fdpay.system.Province;
import com.fordays.fdpay.system.ProvinceListForm;
import com.neza.exception.AppException;

public interface ProvinceBiz {
	public List getProvinces(ProvinceListForm plf) throws AppException;

	public List getProvinces() throws AppException;

	public void addProvinces(Province province) throws AppException;

	public long deleteProvincesById(long id) throws AppException;

	public Province getProvinceById(long id) throws AppException;
}
