package com.fordays.fdpay.system.dao;

import org.hibernate.Query;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import com.fordays.fdpay.agent.Agent;
import com.fordays.fdpay.system.TaskTimestamp;
import com.neza.base.BaseDAOSupport;
import com.neza.base.Hql;
import com.neza.exception.AppException;

public class TaskTimestampDAOImpl extends BaseDAOSupport implements
		TaskTimestampDAO {

	private TransactionTemplate transactionTemplate;

	public void setTransactionManager(
			PlatformTransactionManager transactionManager) {
		this.transactionTemplate = new TransactionTemplate(transactionManager);
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void save(TaskTimestamp tasktimestamp) throws AppException {
		this.getHibernateTemplate().saveOrUpdate(tasktimestamp);
	}

	public TaskTimestamp getTaskTimestamp(Agent agent, Long taskType,
			Long status, Long hour) throws AppException {
		Hql hql = new Hql();
		hql
				.add("from TaskTimestamp a where a.agent.id=? and  a.taskType=? and  a.status=? and (sysdate-a.taskDate)*24<?");
		hql.addParamter(agent.getId());
		hql.addParamter(taskType);
		hql.addParamter(status);
		hql.addParamter(hour);
		Query query = this.getQuery(hql);
		if (query != null && query.list().size() > 0) {
			return (TaskTimestamp) query.list().get(0);
		} else {
			return null;
		}
	}

	public TaskTimestamp getTaskTimestamp(Agent agent, Long taskType) throws AppException {
		Hql hql = new Hql();
		//实名认证任务没有时间限制
		if(TaskTimestamp.type_6==taskType){
			hql.add("from TaskTimestamp a where a.agent.id=? and  a.taskType=?  order by id desc");
			}else{
			hql.add("from TaskTimestamp a where a.agent.id=? and  a.taskType=?  and to_number(sysdate-a.taskDate)<taskHour order by id desc");
		}
		hql.addParamter(agent.getId());
		hql.addParamter(taskType);
		Query query = this.getQuery(hql);
		if (query != null && query.list().size() > 0) {
			return (TaskTimestamp) query.list().get(0);
		} else {			
			return null;
		}
	}

	public void updateStatus(TaskTimestamp tasktimestamp) throws AppException {
		this.getHibernateTemplate().update(tasktimestamp);
	}

}
