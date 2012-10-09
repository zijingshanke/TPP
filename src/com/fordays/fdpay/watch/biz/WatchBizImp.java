package com.fordays.fdpay.watch.biz;

import java.util.Date;
import java.util.List;
import com.fordays.fdpay.agent.AgentException;
import com.fordays.fdpay.agent.RepeatCharge;
import com.fordays.fdpay.agent.dao.AgentDAO;
import com.fordays.fdpay.system.dao.TaskTimestampDAO;

import com.neza.exception.AppException;
import com.neza.tool.DateUtil;

public class WatchBizImp implements WatchBiz {
	private  AgentDAO agentDAO;
	private TaskTimestampDAO tasktimestampDAO;
	
	public AgentDAO getAgentDAO() {
		return agentDAO;
	}
	public void setAgentDAO(AgentDAO agentDAO) {
		this.agentDAO = agentDAO;
	}
	public TaskTimestampDAO getTasktimestampDAO() {
		return tasktimestampDAO;
	}
	public void setTasktimestampDAO(TaskTimestampDAO tasktimestampDAO) {
		this.tasktimestampDAO = tasktimestampDAO;
	}
	public String getExceptionalAgents() throws AppException {
		// TODO Auto-generated method stub
		List list =agentDAO.getExceptionalAgents();
		if(list==null){
			return "none";
		}
		StringBuffer xml = new StringBuffer();
		xml.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
		xml.append("<agents>");
		for(int i=0;i<list.size();i++){
			AgentException ae = (AgentException)list.get(i);
		xml.append("<agent>");
		xml.append("<agent_id>"+(i+1)+"</agent_id>");
		xml.append("<name>"+ae.getName()+"</name>");
		xml.append("<emai>"+ae.getEmail()+"</emai>");
		xml.append("<allow_balance>"+ae.getAllowBalance()+"</allow_balance>");
		xml.append("<notallow_balance>"+ae.getNotallowBalance()+"</notallow_balance>");
		xml.append("<credit_balance>"+ae.getCreditBalance()+"</credit_balance>");
		Date date = new Date(System.currentTimeMillis());
		xml.append("<time>"+DateUtil.getDateString(date, "yyyy-MM-dd HH:mm:ss")+"</time>");
		xml.append("</agent>");
		}
		xml.append("</agents>"); 
		return xml.toString();
	}
	public String getRepeatCharges() throws AppException {
		// TODO Auto-generated method stub
		List list =agentDAO.getRepeatCharge(RepeatCharge.hour);
		if(list==null){
			return "none";
		}
		StringBuffer xml = new StringBuffer();
		xml.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
		xml.append("<RepeatCharges>");
		for(int i=0;i<list.size();i++){
			RepeatCharge rc = (RepeatCharge)list.get(i);
		xml.append("<RepeatCharge>");
		xml.append("<id>"+(i+1)+"</id>");
		xml.append("<name>"+rc.getAgentName()+"</name>");
		xml.append("<emai>"+rc.getAgentEmail()+"</emai>");
		xml.append("<order_no>"+rc.getOrderNo()+"</order_no>");
		xml.append("<amount>"+rc.getAmount()+"</amount>");
		xml.append("<mark>"+rc.getMark()+"</mark>");
		xml.append("<re_count>"+rc.getRepeatCount()+"</re_count>");
//		Date date = new Date(System.currentTimeMillis());
//		xml.append("<time>"+DateUtil.getDateString(date, "yyyy-MM-dd HH:mm:ss")+"</time>");
		xml.append("</RepeatCharge>");
		}
		xml.append("</RepeatCharges>"); 
		return xml.toString();
	}
	
	

}
