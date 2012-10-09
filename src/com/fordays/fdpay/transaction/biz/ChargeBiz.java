package com.fordays.fdpay.transaction.biz;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import com.neza.exception.AppException;
import com.fordays.fdpay.agent.Agent;
import com.fordays.fdpay.transaction.Charge;
import com.fordays.fdpay.transaction.ChargeListForm;


public interface ChargeBiz
{

	public Charge getChargeById(long id)throws AppException;
	public Charge getChargeByOrderNo(String  orderNo)throws AppException;
	
	public Charge queryChargeById(int id)throws AppException;
	public long getCountByChargeBank(long app)throws AppException;

	public BigDecimal getSumAmountByCharge(ChargeListForm clf)throws AppException;
	public BigDecimal getSumAmountByGeneralCharge(ChargeListForm clf)throws AppException;
	public List getAllCharge(ChargeListForm clf)throws AppException;
	public List getCharges(ChargeListForm clf)throws AppException;
	public ArrayList<ArrayList<Object>> listAllCharge(ChargeListForm clf)throws AppException;
	public ArrayList<ArrayList<Object>> listAllGeneralCharge(ChargeListForm clf)throws AppException;
	public ArrayList<ArrayList<Object>> getAllOtherCharge(ChargeListForm clf)throws AppException;
	public List getOtherCharges(ChargeListForm clf)throws AppException;
	public int updateInfo(Charge charge)throws AppException;
	public long save(Charge charge)throws AppException;
	public Charge createCharge(Charge charge,Agent agent)throws AppException;
	public Charge creditCharge(Charge charge,Agent agent)throws AppException;
	public void achieveCharge(Charge charge,Agent agent)throws AppException;
}
