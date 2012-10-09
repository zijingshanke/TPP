package com.fordays.fdpay.cooperate.dao;

import java.util.List;

import org.hibernate.Query;

import com.fordays.fdpay.cooperate.ActionLogListForm;
import com.fordays.fdpay.transaction.OrderDetails;
import com.neza.base.BaseDAOSupport;
import com.neza.base.Hql;
import com.neza.exception.AppException;

public class ActionLogDAOImpl extends BaseDAOSupport implements ActionLogDAO{

	public List getActionLogs(OrderDetails orderDetails) throws AppException {
		Hql hql=new Hql("from ActionLog al where al.orderDetails.id=?");
		hql.addParamter(orderDetails.getId());
		hql.add(" order by al.id");
		Query query = this.getQuery(hql);
		return query.list();
	}

	public List getActionLogs(ActionLogListForm allf) throws AppException {
		Hql hql=new Hql();
		hql.add("from ActionLog al where 1=1");
		
		if("".equals(allf.getOrderNo())==false){
			hql.add(" and al.orderDetails.orderNo like ?");
			hql.addParamter("%"+allf.getOrderNo().trim()+"%");
		}
		
		if("".equals(allf.getContent())==false){
			hql.add(" and al.content like ?");
			hql.addParamter("%"+allf.getContent().trim()+"%");
		}
		
		String beginDate = allf.getBeginDate().toString().trim();
		String endDate = allf.getEndDate().toString().trim();
		
		if ("".equals(beginDate)==false && "".equals(endDate)==true) {
			hql.add(" and to_char(al.logDate,'yyyy-mm-dd hh24:mi:ss') > ?");
			hql.addParamter(beginDate);
		}
		if ("".equals(beginDate)==true && "".equals(endDate)==false) {
			hql.add(" and to_char(al.logDate,'yyyy-mm-dd hh24:mi:ss') < ?");
			hql.addParamter(endDate);
		}
		if ("".equals(beginDate)==false && "".equals(endDate)==false) {
			hql.add(" and  to_char(al.logDate,'yyyy-mm-dd hh24:mi:ss')  between ? and ? ");
			hql.addParamter(beginDate);
			hql.addParamter(endDate);
		}
		hql.add(" order by al.logDate desc");
		System.out.println(">>>>>>>>"+hql);
		return this.list(hql, allf);
	}
  	
}
