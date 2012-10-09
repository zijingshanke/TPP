package com.fordays.fdpay.transaction.biz;

import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import com.fordays.fdpay.transaction.OrderDetails;
import com.fordays.fdpay.transaction.dao.OrderDetailsDAO;
import com.neza.exception.AppException;


public class OrderDetailsBizImp implements OrderDetailsBiz{
	private OrderDetailsDAO orderDetailsDAO;
	private TransactionTemplate transactionTemplate;
    
	
	
	public void setTransactionManager(
			HibernateTransactionManager thargeManager) {
		this.transactionTemplate = new TransactionTemplate(thargeManager);
	}

	public long save(OrderDetails orderDetails) throws AppException {
		return orderDetailsDAO.save(orderDetails);
	}

	public OrderDetailsDAO getOrderDetailsDAO() {
		return orderDetailsDAO;
	}

	public void setOrderDetailsDAO(OrderDetailsDAO orderDetailsDAO) {
		this.orderDetailsDAO = orderDetailsDAO;
	}

	public OrderDetails getOrderDetalisByOrderNo(String orderNo)
			throws AppException {
		return orderDetailsDAO.getOrderDetalisByOrderNo(orderNo);
	}
	

	
}
