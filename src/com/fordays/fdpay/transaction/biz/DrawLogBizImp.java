package com.fordays.fdpay.transaction.biz;

import java.sql.Timestamp;
import java.util.List;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import com.fordays.fdpay.agent.Agent;
import com.fordays.fdpay.transaction.Draw;
import com.fordays.fdpay.transaction.DrawLog;
import com.fordays.fdpay.transaction.DrawLogListForm;
import com.fordays.fdpay.transaction.dao.DrawLogDAO;
import com.neza.exception.AppException;

public class DrawLogBizImp implements DrawLogBiz {
	private DrawLogDAO drawLogDAO;
	private TransactionTemplate transactionTemplate;

	public void setTransactionManager(HibernateTransactionManager thargeManager) {
		this.transactionTemplate = new TransactionTemplate(thargeManager);
	}

	public void setDrawLogDAO(DrawLogDAO drawLogDAO) throws AppException {
		this.drawLogDAO = drawLogDAO;
	}

	public DrawLog getDrawLogById(int id) throws AppException {
		return drawLogDAO.getDrawLogById(id);
	}

	public List getDrawLogs(DrawLogListForm dlf) throws AppException {
		return drawLogDAO.list(dlf);
	}

	public void saveDrawLog(DrawLog drawLog) throws AppException {
		drawLogDAO.save(drawLog);
	}

	public int updateInfo(DrawLog drawLog) throws AppException {
		drawLogDAO.update(drawLog);
		return 0;
	}

	public void deleteDrawLog(int id) throws AppException {
		drawLogDAO.deleteById(id);
	}

	public DrawLog queryDrawLogById(int id) throws AppException {
		return drawLogDAO.queryDrawLogById(id);
	}

	public long approval(Draw draw, Agent agent,String note) throws AppException {
		DrawLog drawLog = new DrawLog();
		drawLog.setOrderNo(draw.getOrderNo());
		drawLog.setAmount(draw.getAmount());
		drawLog.setStatus(Draw.STATUS_1);
		drawLog.setChargeDate(new Timestamp(System.currentTimeMillis()));
		drawLog.setAgent(agent);
		drawLog.setContent(note);
		drawLog.setSysUser(draw.getSysUser());
		return drawLogDAO.save(drawLog);
	}

	public long audit(Draw draw, Agent agent,String note) throws AppException {
		DrawLog drawLog = new DrawLog();
		drawLog.setOrderNo(draw.getOrderNo());
		drawLog.setAmount(draw.getAmount());
		drawLog.setStatus(Draw.STATUS_2);
		drawLog.setSysUser(draw.getSysUser1());
		drawLog.setChargeDate(new Timestamp(System.currentTimeMillis()));
		drawLog.setAgent(agent);
		drawLog.setContent(note);
		return drawLogDAO.save(drawLog);
	}

	public long achieve(Draw draw, Agent agent,String note) throws AppException {
		DrawLog drawLog = new DrawLog();
		drawLog.setOrderNo(draw.getOrderNo());
		drawLog.setAmount(draw.getAmount());
		drawLog.setStatus(Draw.STATUS_3);
		drawLog.setChargeDate(new Timestamp(System.currentTimeMillis()));
		drawLog.setAgent(agent);
		drawLog.setContent(note);
		
		return drawLogDAO.save(drawLog);
	}

	public List getDrawLogByContent(DrawLogListForm tlf) throws AppException {
		return drawLogDAO.getDrawLogByContent(tlf);
	}

	public void save(DrawLog drawLog) throws AppException {
		drawLogDAO.save(drawLog);
	}

}
