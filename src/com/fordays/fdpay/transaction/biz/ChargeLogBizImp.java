package com.fordays.fdpay.transaction.biz;

import java.sql.Timestamp;
import java.util.List;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import com.fordays.fdpay.agent.Agent;
import com.fordays.fdpay.transaction.Charge;
import com.fordays.fdpay.transaction.ChargeLog;
import com.fordays.fdpay.transaction.ChargeLogListForm;
import com.fordays.fdpay.transaction.dao.ChargeLogDAO;
import com.fordays.fdpay.user.SysUser;
import com.neza.exception.AppException;

public class ChargeLogBizImp implements ChargeLogBiz {	
	private ChargeLogDAO chargeLogDAO;
	private TransactionTemplate transactionTemplate;	
	
	public void setTransactionManager(
			HibernateTransactionManager thargeManager) {
		this.transactionTemplate = new TransactionTemplate(thargeManager);
	}

	public ChargeLogDAO getChargeLogDAO()throws AppException {
		return chargeLogDAO;
	}

	public void setChargeLogDAO(ChargeLogDAO chargeLogDAO)throws AppException {
		this.chargeLogDAO = chargeLogDAO;
	}

	public ChargeLog getChargeLogById(int id)throws AppException
	{
		return chargeLogDAO.getChargeLogById(id);
	}
	
	public List getChargeLogs(ChargeLogListForm ulf) throws AppException{		
		return chargeLogDAO.list(ulf);
	}

	public void saveChargeLog(ChargeLog chargeLog)throws AppException {
		chargeLogDAO.save(chargeLog);
	}

	public int updateInfo(ChargeLog chargeLog) throws AppException{
		chargeLogDAO.update(chargeLog);
		return 0;
	}
	
	public void deleteChargeLog(int id)throws AppException {
		chargeLogDAO.deleteById(id);
	}

	public ChargeLog queryChargeLogById(int id) throws AppException
	{
		return chargeLogDAO.queryChargeLogById(id);
	}
	
	public List getChargeLogByContent(ChargeLogListForm clf)
			throws AppException {
		return chargeLogDAO.getChargeLogByContent(clf);
	}

	public void createChargeLog(Charge charge, Agent agent,SysUser user) throws AppException {
		ChargeLog chargeLog = new ChargeLog();
		chargeLog.setChargeDate(new Timestamp(System.currentTimeMillis()));
		chargeLog.setAgent(agent);
		chargeLog.setAmount(charge.getAmount());
		chargeLog.setOrderNo(charge.getOrderNo());
		chargeLog.setContent(charge.getNote());
		chargeLog.setStatus(ChargeLog.CHARGELOG_STATUS_0);
		chargeLog.setOperater(user.getUserName());
		chargeLogDAO.save(chargeLog);
	}
	public void creditChargeLog(Charge charge, Agent agent,SysUser user) throws AppException {
		ChargeLog chargeLog = new ChargeLog();
		chargeLog.setChargeDate(new Timestamp(System.currentTimeMillis()));
		chargeLog.setAgent(agent);
		chargeLog.setAmount(charge.getAmount());
		chargeLog.setOrderNo(charge.getOrderNo());
		chargeLog.setContent(charge.getNote());
		chargeLog.setStatus(ChargeLog.CHARGELOG_STATUS_0);
		chargeLog.setOperater(user.getUserName());
		chargeLogDAO.save(chargeLog);
	}

	public void approvalLog(Charge charge, Agent agent, SysUser user,String note)
			throws AppException {
		ChargeLog chargeLog = new ChargeLog();
		chargeLog.setChargeDate(new Timestamp(System.currentTimeMillis()));
		chargeLog.setAgent(agent);
		chargeLog.setAmount(charge.getAmount());
		chargeLog.setOrderNo(charge.getOrderNo());
		chargeLog.setContent(note);
		chargeLog.setStatus(ChargeLog.CHARGELOG_STATUS_3);
		chargeLog.setOperater(user.getUserName());
		chargeLogDAO.save(chargeLog);
	}

	public void anditLog(Charge newcharge, Agent agent, SysUser user,String note)
			throws AppException {
		ChargeLog chargeLog = new ChargeLog();
		chargeLog.setChargeDate(new Timestamp(System.currentTimeMillis()));
		chargeLog.setAgent(agent);
		chargeLog.setAmount(newcharge.getAmount());
		chargeLog.setOrderNo(newcharge.getOrderNo());
		chargeLog.setContent(note);
		chargeLog.setStatus(ChargeLog.CHARGELOG_STATUS_4);
		chargeLog.setOperater(user.getUserName());
		chargeLogDAO.save(chargeLog);
	}
}
