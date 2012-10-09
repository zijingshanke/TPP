package com.fordays.fdpay.transaction.dao;

import java.util.List;
import com.neza.exception.AppException;
import com.fordays.fdpay.transaction.ChargeLog;
import com.fordays.fdpay.transaction.ChargeLogListForm;
import com.neza.base.BaseDAO;



public interface ChargeLogDAO extends BaseDAO
{
	public List list(ChargeLogListForm tlf)throws AppException;
	public long save(ChargeLog chargeLog)throws AppException;
	public long merge(ChargeLog chargeLog)throws AppException;
	public long update(ChargeLog chargeLog)throws AppException;
	public ChargeLog getChargeLogById(long id)throws AppException;
	public void deleteById(long id)throws AppException; 
	public ChargeLog queryChargeLogById(long id)throws AppException;
	public List getChargeLogByContent(ChargeLogListForm clf) throws AppException;
}
