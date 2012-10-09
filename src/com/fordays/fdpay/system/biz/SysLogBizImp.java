package com.fordays.fdpay.system.biz;

import java.util.List;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import com.fordays.fdpay.system.SysLog;
import com.fordays.fdpay.system.SysLogListForm;
import com.fordays.fdpay.system.dao.SysLogDAO;
import com.neza.exception.AppException;

public class SysLogBizImp implements SysLogBiz {
	private SysLogDAO sysLogDAO;
	private TransactionTemplate transactionTemplate;

	public void setTransactionManager(
			HibernateTransactionManager transactionManager) {
		this.transactionTemplate = new TransactionTemplate(transactionManager);
	}

	public void setSysLogDAO(SysLogDAO sysLogDAO) {
		this.sysLogDAO = sysLogDAO;
	}

	public List getSysLogs(SysLogListForm tslf) throws AppException {
		return sysLogDAO.list(tslf);
	}

	public void saveSysLog(SysLog syslog) throws AppException {
		sysLogDAO.save(syslog);
	}

	public void deleteSysLog(int id) throws AppException {
		sysLogDAO.deleteById(id);
	}

	public int updateSysLog(SysLog SysLog) throws AppException {
		sysLogDAO.update(SysLog);
		return 0;
	}

	public SysLog getSysLogById(int id) throws AppException {
		return sysLogDAO.getSysLogById(id);
	}
}
