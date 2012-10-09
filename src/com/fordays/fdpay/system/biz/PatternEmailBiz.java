package com.fordays.fdpay.system.biz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fordays.fdpay.agent.Agent;
import com.fordays.fdpay.system.PatternEmail;
import com.fordays.fdpay.system.PatternEmailListForm;
import com.neza.exception.AppException;

public interface PatternEmailBiz {
	public PatternEmail getPatternEmailById(long id) throws AppException;

	public List getPatternEmails(PatternEmailListForm pelf) throws AppException;

	public void savePatternEmail(PatternEmail patternemail) throws AppException;

	public long updatePatternEmail(PatternEmail patternemail)
			throws AppException;

	public void deletePatternEmail(long id) throws AppException;

	public void sendToAgent(long id, String mails) throws AppException;

	public void sendToAgent(long id, Agent agent) throws AppException;
	
	public int sendToAgent(String mailCode, String mails,Map<String,String> params) throws AppException;
	public int sendToAgent(String subject,String mailCode, String mails,Map<String,String> params) throws AppException;
}
