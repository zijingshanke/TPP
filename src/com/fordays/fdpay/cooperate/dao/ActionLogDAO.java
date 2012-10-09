package com.fordays.fdpay.cooperate.dao;

import java.util.List;

import com.fordays.fdpay.cooperate.ActionLogListForm;
import com.fordays.fdpay.transaction.OrderDetails;
import com.neza.exception.AppException;

public interface ActionLogDAO {
	public List getActionLogs(OrderDetails orderDetails)throws AppException;
	public List getActionLogs(ActionLogListForm allf)throws AppException;
}
