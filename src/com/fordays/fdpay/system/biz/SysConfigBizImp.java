package com.fordays.fdpay.system.biz;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import com.fordays.fdpay.system.SysConfig;
import com.fordays.fdpay.system.SysConfigListForm;
import com.fordays.fdpay.system.SysLog;
import com.fordays.fdpay.system.SysLogListForm;
import com.fordays.fdpay.system.dao.SysConfigDAO;
import com.fordays.fdpay.system.dao.SysLogDAO;
import com.fordays.fdpay.user.SysUser;
import com.fordays.fdpay.user.UserListForm;
import com.fordays.fdpay.user.dao.UserDAO;
import com.neza.exception.AppException;

public class SysConfigBizImp implements SysConfigBiz {

	private SysConfigDAO sysConfigDAO;

	private TransactionTemplate transactionTemplate;

	public void setTransactionManager(
			HibernateTransactionManager transactionManager) {
		this.transactionTemplate = new TransactionTemplate(transactionManager);
	}

	public List getSysConfigs(SysConfigListForm sclf) throws AppException{
		return sysConfigDAO.list(sclf);
	}

	public SysConfigDAO getSysConfigDAO() {
		return sysConfigDAO;
	}

	public void setSysConfigDAO(SysConfigDAO sysConfigDAO) {
		this.sysConfigDAO = sysConfigDAO;
	}

	public SysConfig getSysConfigById(int id) throws AppException {
		return sysConfigDAO.getSysConfigById(id);
	}

	public void saveSysConfig(SysConfig sysconfig) throws AppException {
		this.sysConfigDAO.save(sysconfig);

	}

	public void deleteSysConfig(int id) throws AppException {
		sysConfigDAO.deleteById(id);
	}

	public int updateSysConfig(SysConfig sysconfig) throws AppException {
		// TODO Auto-generated method stub
		sysConfigDAO.update(sysconfig);
		return 0;
	}
}
