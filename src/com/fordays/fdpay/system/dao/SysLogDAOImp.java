package com.fordays.fdpay.system.dao;

import java.util.List;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import com.fordays.fdpay.system.SysLog;
import com.fordays.fdpay.system.SysLogListForm;
import com.neza.base.BaseDAOSupport;
import com.neza.exception.AppException;

public class SysLogDAOImp extends BaseDAOSupport implements SysLogDAO {
	private TransactionTemplate transactionTemplate;

	public void setTransactionManager(
			PlatformTransactionManager transactionManager) {
		this.transactionTemplate = new TransactionTemplate(transactionManager);
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public long save(SysLog SysLog) throws AppException {
		this.getHibernateTemplate().save(SysLog);
		return SysLog.getId();
	}

	public long merge(SysLog SysLog) throws AppException {
		this.getHibernateTemplate().merge(SysLog);
		return SysLog.getId();
	}

	public long update(SysLog SysLog) throws AppException {
		if (SysLog.getId() > 0)
			return save(SysLog);
		else
			throw new IllegalArgumentException("id isn't a valid argument.");
	}

	public void deleteById(long id) throws AppException {
		if (id > 0) {
			SysLog SysLog = (SysLog) this.getHibernateTemplate().get(
					SysLog.class, new Long(id));
			this.getHibernateTemplate().delete(SysLog);
		}
	}

	public SysLog getSysLogById(long id) throws AppException {
		SysLog SysLog;
		if (id > 0) {
			SysLog = (SysLog) this.getHibernateTemplate().get(SysLog.class,
					new Long(id));
			return SysLog;
		} else
			return new SysLog();
	}

	public boolean getSysLogByUserId(long id) throws AppException {
		String hql = " from SysLog where sysUser.userId=" + id;
		List list = this.list(hql);
		if (list.size() > 0) {
			return true;
		} else
			return false;
	}

	public List list(SysLogListForm sllf) throws AppException {
		String hql = "from SysLog where locate=" + sllf.getLocate();
		try {
			String logUser = sllf.getUserNo().toString().trim();
			String formDate = sllf.getFromDate().toString().trim();
			String toDate = sllf.getToDate().toString().trim();
			if (logUser != "" && logUser != null) {
				hql += " and sysUser.userNo like '%" + logUser + "%'";
			}
			if (!"".equals(formDate) && formDate != null && !"".equals(toDate)
					&& toDate != null) {
				hql += " and  to_char(logDate,'yyyy-MM-dd') between '"
						+ formDate + "' and '" + toDate + "'";

			}
			hql += " order by logDate desc ";
			System.out.println("--hql:-" + hql);
		} catch (Exception ex) {
			ex.getMessage();
		}
		return this.list(hql, sllf);
	}

	public List list(SysLog syslog) throws AppException {
		return null;
	}
}
