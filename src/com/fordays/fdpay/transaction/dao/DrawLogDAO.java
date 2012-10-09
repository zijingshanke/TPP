package com.fordays.fdpay.transaction.dao;

import java.util.List;
import com.neza.exception.AppException;
import com.fordays.fdpay.transaction.DrawLog;
import com.fordays.fdpay.transaction.DrawLogListForm;
import com.neza.base.BaseDAO;



public interface DrawLogDAO extends BaseDAO
{
	public List list(DrawLogListForm tlf)throws AppException;
	public long save(DrawLog drawLog)throws AppException;
	public long merge(DrawLog drawLog)throws AppException;
	public long update(DrawLog drawLog)throws AppException;
	public DrawLog getDrawLogById(long id)throws AppException;
	public void deleteById(long id)throws AppException; 
	public DrawLog queryDrawLogById(long id)throws AppException;
	public List getDrawLogByContent(DrawLogListForm tlf) throws AppException;
}
