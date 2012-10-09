package com.fordays.fdpay.transaction.biz;

import java.util.List;
import com.neza.exception.AppException;
import com.fordays.fdpay.agent.Agent;
import com.fordays.fdpay.transaction.Charge;
import com.fordays.fdpay.transaction.ChargeLog;
import com.fordays.fdpay.transaction.ChargeLogListForm;
import com.fordays.fdpay.user.SysUser;

public interface ChargeLogBiz {
	public void saveChargeLog(ChargeLog chargelog) throws AppException;
	public void createChargeLog(Charge charge,Agent agent,SysUser user) throws AppException;
	public void creditChargeLog(Charge charge,Agent agent,SysUser user) throws AppException;
	public void approvalLog(Charge charge,Agent agent,SysUser user,String note) throws AppException;
	public void anditLog(Charge charge,Agent agent,SysUser user,String note) throws AppException;

	public ChargeLog getChargeLogById(int id) throws AppException;

	public ChargeLog queryChargeLogById(int id) throws AppException;

	public List getChargeLogs(ChargeLogListForm clf) throws AppException;

	public int updateInfo(ChargeLog chargeLog) throws AppException;
	public List getChargeLogByContent(ChargeLogListForm clf) throws AppException;
}
