package com.fordays.fdpay.system.biz;



import java.util.List;

import com.fordays.fdpay.system.LoginLog;
import com.fordays.fdpay.system.LoginLogListForm;
import com.neza.exception.AppException;

public interface LoginLogBiz {
	public void saveLoginLog(LoginLog loginlog) throws AppException;
	public List getLoginLogs(LoginLogListForm lllf) throws AppException;
}