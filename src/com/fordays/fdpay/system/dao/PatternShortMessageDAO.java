package com.fordays.fdpay.system.dao;

import java.util.List;

import com.fordays.fdpay.system.PatternShortMessage;
import com.fordays.fdpay.system.PatternShortMessageListForm;
import com.neza.base.BaseDAO;
import com.neza.exception.AppException;

public interface PatternShortMessageDAO extends BaseDAO {
	public List list(PatternShortMessageListForm psmlf) throws AppException;

	public long save(PatternShortMessage psmlf) throws AppException;

	public long merge(PatternShortMessage psmlf) throws AppException;

	public long update(PatternShortMessage psmlf) throws AppException;

	public PatternShortMessage getPatternShortMessageById(long id)
			throws AppException;

	public void deleteById(long id) throws AppException;
}
