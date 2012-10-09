package com.fordays.fdpay.system.biz;

import java.util.List;

import com.fordays.fdpay.transaction.TransactionStatus;
import com.fordays.fdpay.system.TransactionStatusListForm;
import com.neza.exception.AppException;

public interface TransactionStatusBiz {

	public TransactionStatus getTransactionStatusById(long id)
			throws AppException;

	public List getTransactionStatuss(TransactionStatusListForm tslf)
			throws AppException;

	public void saveTransactionStatus(TransactionStatus transactionstatus)
			throws AppException;

	public long updateTransactionStatus(TransactionStatus transactionstatus)
			throws AppException;

	public void deleteTransactionStatus(long id) throws AppException;

}
