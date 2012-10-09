package com.fordays.fdpay.system.biz;


import java.util.List;

import com.fordays.fdpay.system.SysLog;
import com.fordays.fdpay.system.SysConfig;
import com.fordays.fdpay.system.SysConfigListForm;
import com.neza.exception.AppException;




public interface SysConfigBiz {

	public SysConfig getSysConfigById(int id) throws AppException;
	public List getSysConfigs(SysConfigListForm ulf) throws AppException;
	public void  saveSysConfig(SysConfig sysconfig) throws AppException;
	public int updateSysConfig(SysConfig sysconfig) throws AppException;
	public void deleteSysConfig(int id) throws AppException;
}
