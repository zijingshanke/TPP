package com.fordays.fdpay.transaction.biz;

import com.fordays.fdpay.transaction.OrderDetails;
import com.neza.exception.AppException;


public interface OrderDetailsBiz {
	public long save(OrderDetails orderDetails) throws AppException;
	public OrderDetails getOrderDetalisByOrderNo(String orderNo) throws AppException;
}
