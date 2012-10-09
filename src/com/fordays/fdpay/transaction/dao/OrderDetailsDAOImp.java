package com.fordays.fdpay.transaction.dao;

import org.hibernate.Query;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import com.fordays.fdpay.transaction.OrderDetails;
import com.neza.base.BaseDAOSupport;
import com.neza.base.Hql;
import com.neza.exception.AppException;

public class OrderDetailsDAOImp extends BaseDAOSupport implements OrderDetailsDAO {
	private TransactionTemplate orderDetailsTemplate;
	
	public void setOrderDetailsManager(PlatformTransactionManager orderDetailsTemplate) {
		this.orderDetailsTemplate = new TransactionTemplate(orderDetailsTemplate);
	}
	
	public long save(OrderDetails orderDetails) throws AppException{
		this.getHibernateTemplate().save(orderDetails);
		return orderDetails.getId();
	}
	
	public long update(OrderDetails orderDetails) throws AppException{
		if (orderDetails.getId() > 0)
			return save(orderDetails);
		else
			throw new IllegalArgumentException("id isn't a valid argument.");
	}
	public OrderDetails getOrderDetailById(long id) throws AppException{

		OrderDetails orderDetails;
		if (id > 0) {
			orderDetails = (OrderDetails) this.getHibernateTemplate().get(
					OrderDetails.class, new Long(id));
			return orderDetails;

		} else
			return new OrderDetails();
	}
	public OrderDetails queryOrderDetailByOrderNoAndPartnerId(String orderNo,String partnerId) throws AppException{
		Hql hql = new Hql("from OrderDetails where orderNo=? and partner=?");
		hql.addParamter(orderNo);
		hql.addParamter(partnerId);
		Query query = this.getQuery(hql);
		OrderDetails order=null;
		if (query!=null&& query.list()!=null&&query.list().size() > 0)
		{
			order=(OrderDetails) query.list().get(0);
		}
		return order;
		
	}

	public OrderDetails getOrderDetalisByOrderNo(String orderNo)
			throws AppException {
		Hql hql = new Hql("from OrderDetails where orderNo=?");
		hql.addParamter(orderNo);
		Query query = this.getQuery(hql);
		OrderDetails order=null;
		if (query!=null&& query.list()!=null&&query.list().size() > 0)
		{
			order=(OrderDetails) query.list().get(0);
		}
		return order;
	}
}
