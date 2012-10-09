package com.fordays.fdpay.transaction.biz;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.neza.exception.AppException;
import com.fordays.fdpay.transaction.Draw;
import com.fordays.fdpay.agent.Agent;
import com.fordays.fdpay.transaction.DrawListForm;

public interface DrawBiz {
	public void save(Draw draw) throws AppException;

	public String exportDrawFile(DrawListForm drawlistform,
			HttpServletResponse response) throws AppException;

	public List getToBankDrawList(DrawListForm drawlistform)
			throws AppException;

	public void saveDrawOprationLog(HttpServletRequest request)
			throws AppException;

	public Draw getDrawById(long id) throws AppException;

	public Draw queryDrawById(long id) throws AppException;

	public List getDraws(DrawListForm clf) throws AppException;

	public List getSubtracts(DrawListForm clf) throws AppException;

	public List getDrawsInfo(DrawListForm clf) throws AppException;

	public long updateInfo(Draw draw) throws AppException;

	public void achieveDraw(Draw draw, Agent agent, BigDecimal amount)
			throws AppException;

	public void achieveSubtract(Draw draw, Agent agent, BigDecimal amount)
			throws AppException;

	public Draw getDrawByOrderNo(String orderNo) throws AppException;

	public List getCountByDrawList() throws AppException;

	public Long[] getAllDrawInfoIds(DrawListForm dlf) throws AppException;

	public void modifiedAuthentication(Draw draw) throws AppException;

	public void modifiedDraw(Draw draw) throws AppException;

	public ArrayList<ArrayList<Object>> getAllDraw(DrawListForm cf)
			throws AppException;

	public ArrayList<ArrayList<Object>> getAllSubtract(DrawListForm cf)
			throws AppException;

}
