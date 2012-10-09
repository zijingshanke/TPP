package com.fordays.fdpay.cooperate.biz;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import com.fordays.fdpay.cooperate.ActionLogListForm;
import com.fordays.fdpay.cooperate.dao.ActionLogDAO;
import com.fordays.fdpay.transaction.OrderDetails;
import com.neza.exception.AppException;

public class ActionLogBizImpl implements ActionLogBiz{
	private ActionLogDAO actionLogDAO;
	private TransactionTemplate transactionTemplate;

	public void setTransactionManager(
			HibernateTransactionManager transactionManager) {
		this.transactionTemplate = new TransactionTemplate(transactionManager);
	}
	public List getActionLogs(OrderDetails orderDetails) throws AppException {
		
		return actionLogDAO.getActionLogs(orderDetails);
	}
	public ActionLogDAO getActionLogDAO() {
		return actionLogDAO;
	}
	public void setActionLogDAO(ActionLogDAO actionLogDAO) {
		this.actionLogDAO = actionLogDAO;
	}
	public List getActionLogs(ActionLogListForm allf) throws AppException {
		
		return actionLogDAO.getActionLogs(allf);
	}
}
