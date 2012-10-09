package com.fordays.fdpay.system.dao;

import java.util.List;

import com.fordays.fdpay.system.PatternEmail;
import com.fordays.fdpay.system.PatternEmailListForm;
import com.neza.base.BaseDAO;
import com.neza.exception.AppException;

public interface PatternEmailDAO extends BaseDAO {
	public List list(PatternEmailListForm pelf) throws AppException;

	public long save(PatternEmail pelf) throws AppException;

	public long merge(PatternEmail pelf) throws AppException;

	public long update(PatternEmail pelf) throws AppException;

	public PatternEmail getPatternEmailById(long id) throws AppException;

	public void deleteById(long id) throws AppException;
	
	
	public PatternEmail getPatternEmailByCode(String code) throws AppException;
}
