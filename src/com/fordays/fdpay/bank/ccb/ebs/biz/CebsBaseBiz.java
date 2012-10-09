package com.fordays.fdpay.bank.ccb.ebs.biz;

import com.neza.exception.AppException;

/**
 * @建设银行外联平台基本业务接口
 */
public interface CebsBaseBiz {
	public String getTxCode(String ebsresult) throws AppException;
}
