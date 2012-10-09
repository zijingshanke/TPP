package com.fordays.fdpay.system.dao;

import java.util.List;

import com.fordays.fdpay.system.SysConfig;
import com.fordays.fdpay.system.SysConfigListForm;
import com.fordays.fdpay.system.SysLog;
import com.neza.base.BaseDAO;
import com.neza.exception.AppException;

public interface SysConfigDAO extends BaseDAO {

	public List list(SysConfigListForm ulf) throws AppException;

	public SysConfig getSysConfigById(long id) throws AppException;

	public long save(SysConfig sysconfig) throws AppException;

	public void deleteById(long id) throws AppException;

	public long merge(SysConfig sysconfig) throws AppException;

	public long update(SysConfig sysconfig) throws AppException;
}