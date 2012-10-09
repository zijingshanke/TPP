package com.fordays.fdpay.system.dao;

import java.util.List;

import com.fordays.fdpay.transaction.TransactionStatus;
import com.fordays.fdpay.system.TransactionStatusListForm;
import com.neza.base.BaseDAO;
import com.neza.exception.AppException;

public interface TransactionStatusDAO extends BaseDAO {

	public List list(TransactionStatusListForm tslf) throws AppException;

	public long save(TransactionStatus transactionstatus) throws AppException;

	public long merge(TransactionStatus transactionstatus) throws AppException;

	public long update(TransactionStatus transactionstatus) throws AppException;

	public TransactionStatus getTransactionStatusById(long id)
			throws AppException;

	public void deleteById(long id) throws AppException;
}
