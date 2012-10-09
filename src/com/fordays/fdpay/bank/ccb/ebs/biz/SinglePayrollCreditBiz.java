package com.fordays.fdpay.bank.ccb.ebs.biz;

import com.fordays.fdpay.bank.ccb.ebs.SinglePayrollCreditResult;
import com.fordays.fdpay.transaction.Draw;
import com.neza.exception.AppException;

public interface SinglePayrollCreditBiz {
	public String getSinglePayrollCmd(Draw draw)
			throws AppException;

	public SinglePayrollCreditResult getSinglePayrollResult(
			String queryString) throws AppException;
}
