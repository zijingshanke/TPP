package com.fordays.fdpay.system.biz;

import java.util.List;

import com.fordays.fdpay.system.PatternShortMessage;
import com.fordays.fdpay.system.PatternShortMessageListForm;
import com.neza.exception.AppException;

public interface PatternShortMessageBiz {
	public PatternShortMessage getPatternShortMessageById(long id) throws AppException;

	public List getPatternShortMessages(PatternShortMessageListForm pelf) throws AppException;

	public void savePatternShortMessage(PatternShortMessage patternShortMessage) throws AppException;

	public long updatePatternShortMessage(PatternShortMessage patternShortMessage) throws AppException;

	public void deletePatternShortMessage(long id) throws AppException;
}
