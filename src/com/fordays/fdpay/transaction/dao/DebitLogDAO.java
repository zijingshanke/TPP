package com.fordays.fdpay.transaction.dao;

import java.util.List;
import com.neza.exception.AppException;
import com.fordays.fdpay.transaction.ChargeLog;
import com.fordays.fdpay.transaction.ChargeLogListForm;
import com.fordays.fdpay.transaction.DebitLog;
import com.fordays.fdpay.transaction.DebitLogListForm;
import com.neza.base.BaseDAO;



public interface DebitLogDAO extends BaseDAO
{
	public long save(DebitLog debitLog)throws AppException;
	public long update(DebitLog debitLog)throws AppException;
	public List list(DebitLogListForm dlf )throws AppException;
	public List getDebitLogByContent(DebitLogListForm clf)throws AppException;
	public List getDebitLogDetailed(DebitLogListForm clf)throws AppException;
}
