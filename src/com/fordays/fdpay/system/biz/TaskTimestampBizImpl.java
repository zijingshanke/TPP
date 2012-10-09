package com.fordays.fdpay.system.biz;


import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import com.fordays.fdpay.agent.Agent;
import com.fordays.fdpay.system.TaskTimestamp;
import com.fordays.fdpay.system.dao.TaskTimestampDAO;
import com.neza.exception.AppException;

public class TaskTimestampBizImpl implements TaskTimestampBiz {
	private TransactionTemplate transactionTemplate;
	private TaskTimestampDAO tasktimestampDAO;
	public void setTransactionManager(
			HibernateTransactionManager transactionManager) {
		this.transactionTemplate = new TransactionTemplate(transactionManager);
	}
	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public void saveTaskTimestamp(TaskTimestamp taskTimestamp)
			throws AppException {
		tasktimestampDAO.save(taskTimestamp);
	}

	public TaskTimestampDAO getTasktimestampDAO() {
		return tasktimestampDAO;
	}



	public void setTasktimestampDAO(TaskTimestampDAO tasktimestampDAO) {
		this.tasktimestampDAO = tasktimestampDAO;
	}

	public TaskTimestamp getTaskTimestamp(Agent agent, Long taskType,Long status,Long hour)
			throws AppException {
		return tasktimestampDAO.getTaskTimestamp(agent, taskType,status,hour);
	}

	public void  updateTaskTimestampStatus(TaskTimestamp taskTimestamp)throws AppException{
		 tasktimestampDAO.updateStatus(taskTimestamp);
	}
	public TaskTimestamp getTaskTimestamp(Agent agent, Long taskType)
			throws AppException {
		
		return tasktimestampDAO.getTaskTimestamp(agent, taskType);
	}







}
