package com.fordays.fdpay.transaction.dao;

import java.util.List;
import com.neza.exception.AppException;
import com.fordays.fdpay.transaction.Draw;
import com.fordays.fdpay.transaction.DrawListForm;
import com.neza.base.BaseDAO;

public interface DrawDAO extends BaseDAO {

	/**
	 * 获取向银行发送的提现订单
	 */
//	public List getToBankDrawList(String bank) throws AppException;

	/**
	 * 根据复选框选中状态，获取提现单
	 */
	public List getToBankDrawList(String bank, int[] drawlistid)
			throws AppException;

	public List getAllDrawsInfo(DrawListForm dlf) throws AppException;

	public List list(DrawListForm tlf) throws AppException;

	public List listSubtract(DrawListForm tlf) throws AppException;

	public List getAllSubtract(DrawListForm tlf) throws AppException;

	public long save(Draw draw) throws AppException;

	public long merge(Draw draw) throws AppException;

	public long update(Draw draw) throws AppException;

	public Draw getDrawById(long id) throws AppException;

	public void deleteById(long id) throws AppException;

	public Draw queryDrawById(long id) throws AppException;

	public Draw getDrawByOrderNo(String orderNo) throws AppException;

	public List getCountByDrawList() throws AppException;

	public List getDrawsInfo(DrawListForm clf) throws AppException;

	public List getAllDrawList(DrawListForm tlf) throws AppException;
}
