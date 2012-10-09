package com.fordays.fdpay.system.biz;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import com.fordays.fdpay.system.PatternShortMessage;
import com.fordays.fdpay.system.PatternShortMessageListForm;
import com.fordays.fdpay.system.dao.PatternShortMessageDAO;
import com.neza.exception.AppException;

public class PatternShortMessageBizImp implements PatternShortMessageBiz {

	private PatternShortMessageDAO patternShortMessageDAO;

	public void setPatternShortMessageDAO(
			PatternShortMessageDAO patternShortMessageDAO) {
		this.patternShortMessageDAO = patternShortMessageDAO;
	}

	private TransactionTemplate transactionTemplate;

	public void setTransactionManager(
			HibernateTransactionManager transactionManager) {
		this.transactionTemplate = new TransactionTemplate(transactionManager);
	}

	
	public void deletePatternShortMessage(long id) throws AppException{
		patternShortMessageDAO.deleteById(id);
	}

	public PatternShortMessage getPatternShortMessageById(long id) throws AppException{
		
		return patternShortMessageDAO.getPatternShortMessageById(id);
	}

	public List getPatternShortMessages(PatternShortMessageListForm psmlf) throws AppException{
		return patternShortMessageDAO.list(psmlf);
	}

	public void savePatternShortMessage(PatternShortMessage patternShortMessage) throws AppException{
			patternShortMessageDAO.save(patternShortMessage);
	}

	public long updatePatternShortMessage(PatternShortMessage patternShortMessage) throws AppException{
		patternShortMessageDAO.update(patternShortMessage);
		return 0;
	}
}
