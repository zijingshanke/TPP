package com.fordays.fdpay.watch.biz;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.fordays.fdpay.user.SysUser;
import com.fordays.fdpay.user.UserListForm;
import com.neza.exception.AppException;

public interface WatchBiz {
	public String getExceptionalAgents()throws AppException;
	public String getRepeatCharges()throws AppException;
	
}
