package com.fordays.fdpay.transaction.dao;

import java.math.BigDecimal;
import java.util.List;
import com.neza.exception.AppException;
import com.fordays.fdpay.transaction.Charge;
import com.fordays.fdpay.transaction.ChargeListForm;
import com.neza.base.BaseDAO;



public interface ChargeDAO extends BaseDAO
{
	public List getAllChargeNoPaging(ChargeListForm tlf)throws AppException;
	public List getAllChargePaging(ChargeListForm tlf)throws AppException;
	public List list(ChargeListForm tlf)throws AppException;
	public long save(Charge charge)throws AppException;
	public long merge(Charge charge)throws AppException;
	public long update(Charge charge)throws AppException;
	public Charge getChargeById(long id)throws AppException;
	public void deleteById(long id)throws AppException; 
	public Charge queryChargeById(long id)throws AppException;
	public List getOtherCharges(ChargeListForm clf) throws AppException;
	public Charge getChargeByOrderNo(String orderNo) throws AppException;
	public long getCountByChargeBank(long app)throws AppException;
	public List getAllOtherChargeList(ChargeListForm cf) throws AppException;
	public List getAllChargeList(ChargeListForm cf) throws AppException;
	public BigDecimal getSumAmountByCharge(ChargeListForm clf)throws AppException;
	public BigDecimal getSumAmountByGeneralCharge(ChargeListForm clf)throws AppException;
}
