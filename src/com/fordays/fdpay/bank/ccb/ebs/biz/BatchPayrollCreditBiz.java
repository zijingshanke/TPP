package com.fordays.fdpay.bank.ccb.ebs.biz;

import javax.servlet.http.HttpServletRequest;
import com.fordays.fdpay.bank.ccb.ebs.BatchPayrollCreditResult;
import com.neza.exception.AppException;

public interface BatchPayrollCreditBiz {
	
	public BatchPayrollCreditResult parseResultData(HttpServletRequest request)
			throws AppException;
}
