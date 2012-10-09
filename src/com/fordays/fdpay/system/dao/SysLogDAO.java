package com.fordays.fdpay.system.dao;

import java.util.List;
import com.fordays.fdpay.system.SysLog;
import com.fordays.fdpay.system.SysLogListForm;
import com.neza.base.BaseDAO;
import com.neza.exception.AppException;

public interface SysLogDAO extends BaseDAO {

	public List list(SysLogListForm tslf) throws AppException;

	public long save(SysLog syslog) throws AppException;

	public long merge(SysLog syslog) throws AppException;

	public long update(SysLog syslog) throws AppException;

	public SysLog getSysLogById(long id) throws AppException;

	public void deleteById(long id) throws AppException;

	public boolean getSysLogByUserId(long id) throws AppException;
}
