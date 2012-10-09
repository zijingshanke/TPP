package com.fordays.fdpay.transaction.dao;

import com.fordays.fdpay.transaction.OrderDetails;
import com.neza.base.BaseDAO;
import com.neza.exception.AppException;
public  interface OrderDetailsDAO extends BaseDAO{
	public long save(OrderDetails orderDetails) throws AppException;
	public long update(OrderDetails orderDetails) throws AppException;
	public OrderDetails getOrderDetailById(long id) throws AppException;
	public OrderDetails queryOrderDetailByOrderNoAndPartnerId(String orderNo,String partnerId) throws AppException;
	public OrderDetails getOrderDetalisByOrderNo(String orderNo) throws AppException;
}
