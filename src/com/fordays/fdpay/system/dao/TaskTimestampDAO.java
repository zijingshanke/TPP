package com.fordays.fdpay.system.dao;

import com.fordays.fdpay.agent.Agent;
import com.fordays.fdpay.system.TaskTimestamp;
import com.neza.base.BaseDAO;
import com.neza.exception.AppException;

public interface TaskTimestampDAO extends BaseDAO {
	public void save(TaskTimestamp tasktimestamp) throws AppException; 
	public TaskTimestamp getTaskTimestamp(Agent agent,Long taskType,Long status,Long hour) throws AppException;
	public TaskTimestamp getTaskTimestamp(Agent agent, Long taskType) throws AppException;
	public void  updateStatus(TaskTimestamp tasktimestamp)throws AppException;
}
