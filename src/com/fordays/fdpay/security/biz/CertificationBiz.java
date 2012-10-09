package com.fordays.fdpay.security.biz;


import javax.servlet.http.HttpServletRequest;

import com.fordays.fdpay.security.CertificationForm;
import com.fordays.fdpay.user.SysUser;
import com.neza.exception.AppException;




public interface CertificationBiz {
	public boolean validateUser(HttpServletRequest request,SysUser user) throws AppException;
	public boolean validateAgent(HttpServletRequest request,CertificationForm certificationForm) throws AppException;
//	public boolean validateUser(HttpServletRequest request,long userId) throws AppException;
}
