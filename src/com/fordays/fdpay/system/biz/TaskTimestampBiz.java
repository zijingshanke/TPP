package com.fordays.fdpay.system.biz;



import com.fordays.fdpay.agent.Agent;
import com.fordays.fdpay.system.TaskTimestamp;
import com.neza.exception.AppException;

public interface TaskTimestampBiz {


	public void saveTaskTimestamp(TaskTimestamp taskTimestamp) throws AppException;

	public TaskTimestamp getTaskTimestamp(Agent agent,Long taskType,Long status,Long hour) throws AppException;
	public TaskTimestamp getTaskTimestamp(Agent agent, Long taskType) throws AppException;
	public void  updateTaskTimestampStatus(TaskTimestamp taskTimestamp)throws AppException;

}