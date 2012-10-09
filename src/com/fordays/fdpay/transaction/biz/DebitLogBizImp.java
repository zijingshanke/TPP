package com.fordays.fdpay.transaction.biz;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import com.fordays.fdpay.agent.Agent;
import com.fordays.fdpay.transaction.Debit;
import com.fordays.fdpay.transaction.DebitLog;
import com.fordays.fdpay.transaction.DebitLogListForm;
import com.fordays.fdpay.transaction.dao.DebitDAO;
import com.fordays.fdpay.transaction.dao.DebitLogDAO;
import com.fordays.fdpay.user.SysUser;
import com.neza.exception.AppException;
import com.neza.tool.DateUtil;

public class DebitLogBizImp implements DebitLogBiz {	
	private DebitLogDAO debitLogDAO;
	private DebitDAO debitDAO;
	private TransactionTemplate transactionTemplate;	
	
	public void setTransactionManager(
			HibernateTransactionManager thargeManager) {
		this.transactionTemplate = new TransactionTemplate(thargeManager);
	}

	public void approvalLog(Debit debit, Agent agent, SysUser user, String note)
			throws AppException {
		DebitLog debitLog = new DebitLog();
		debitLog.setChargeDate(new Timestamp(System.currentTimeMillis()));
		debitLog.setAgent(agent);
		debitLog.setAmount(debit.getAmount());
		debitLog.setDebitNo(debit.getNo());
		debitLog.setContent(note);
		debitLog.setStatus(Debit.DEBIT_STATUS_2);
		debitLog.setOperater(user.getUserName());
		debitLogDAO.save(debitLog);
		
	}
	public void advanceLog(Debit debit, Agent agent, SysUser user, String note)
	throws AppException {
		DebitLog debitLog = new DebitLog();
		debitLog.setChargeDate(new Timestamp(System.currentTimeMillis()));
		debitLog.setAgent(agent);
		debitLog.setAmount(debit.getAmount());
		debitLog.setDebitNo(debit.getNo());
		debitLog.setContent(note);
		debitLog.setStatus(Debit.DEBIT_STATUS_3);
		debitLog.setOperater(user.getUserName());
		debitLogDAO.save(debitLog);
		
	}

	public void auditLog(Debit debit, Agent agent, SysUser user,
			String note) throws AppException {
		DebitLog debitLog = new DebitLog();
		debitLog.setChargeDate(new Timestamp(System.currentTimeMillis()));
		debitLog.setAgent(agent);
		debitLog.setAmount(debit.getAmount());
		debitLog.setDebitNo(debit.getNo());
		debitLog.setContent(note);
		debitLog.setStatus(Debit.DEBIT_STATUS_1);
		debitLog.setOperater(user.getUserName());
		debitLogDAO.save(debitLog);
	}

	public DebitLogDAO getDebitLogDAO() {
		return debitLogDAO;
	}

	public void setDebitLogDAO(DebitLogDAO debitLogDAO) {
		this.debitLogDAO = debitLogDAO;
	}

	public List list(DebitLogListForm dlf) throws AppException {
		List<DebitLog> newlist=debitLogDAO.list(dlf);
		for (int i = 0; i < newlist.size(); i++) {
			DebitLog dl=newlist.get(i);
			Debit d=debitDAO.getDebitByNo(dl.getDebitNo());
			dl.setFromAgent(d.getFromAgent());
			newlist.set(i, dl);
		}
		return debitLogDAO.list(dlf);
	}

	public DebitDAO getDebitDAO() {
		return debitDAO;
	}

	public void setDebitDAO(DebitDAO debitDAO) {
		this.debitDAO = debitDAO;
	}

	public Debit getDebitByOrderNo(String orderNo) throws AppException {
		return debitDAO.getDebitByNo(orderNo);
	}

	public List getDebitLogByContent(DebitLogListForm clf) throws AppException{
		return debitLogDAO.getDebitLogByContent(clf);
	}

	public ArrayList<ArrayList<Object>> getDebitLogDetailed(DebitLogListForm dlf)
			throws AppException {
		Debit debit=debitDAO.getDebitByNo(dlf.getOrderNo());
		List data =debitLogDAO.getDebitLogDetailed(dlf);
		ArrayList<ArrayList<Object>> list_context = new ArrayList<ArrayList<Object>>();
		ArrayList<Object> list_title = new ArrayList<Object>();
		list_title.add("#预支详细");
		list_context.add(list_title);
		list_title = new ArrayList<Object>();
		list_title.add("#下载时间：");
		list_context.add(list_title);
		list_title = new ArrayList<Object>();
		list_title.add(DateUtil.getDateString("yyyy年MM月dd日HH:mm:ss"));
		list_context.add(list_title);
		
		list_title = new ArrayList<Object>();
		list_title.add("-----------------------------------------------------------------------");
		list_context.add(list_title);
		
		list_title =  new ArrayList<Object>();
		list_title.add("交易号");
		list_title.add("说明");
		list_title.add("申请方(账号)");
		list_title.add("预支方(账号)");
		list_title.add("预支金额");
		list_context.add(list_title);
		
		list_title =  new ArrayList<Object>();
		list_title.add(debit.getNo());
		list_title.add(debit.getRemark());
		list_title.add(debit.getAgent().getName()+"("+debit.getAgent().getLoginName()+")");
		list_title.add(debit.getFromAgent().getName()+"("+debit.getFromAgent().getLoginName()+")");
		list_title.add(debit.getAmount());
		list_context.add(list_title);
		
		list_title = new ArrayList<Object>();
		list_title.add("-----------------------------------------------------------------------");
		list_context.add(list_title);
		
		list_title =  new ArrayList<Object>();
		list_title.add("操作时间");
		list_title.add("操作人");
		list_title.add("备注");
		list_title.add("操作说明");
		list_context.add(list_title);
		for (int i = 0; i < data.size(); i++)
		{
			DebitLog debitLog = (DebitLog) data.get(i);
			ArrayList<Object> list_context_item = new ArrayList<Object>();
			list_context_item.add(debitLog.getChargeDate());
			list_context_item.add(debitLog.getOperater());
			list_context_item.add(debitLog.getContent());
			if(debitLog.getStatus()==Debit.DEBIT_STATUS_0){
				list_context_item.add("申请");
			}
			if(debitLog.getStatus()==Debit.DEBIT_STATUS_1){
				list_context_item.add("审核");
			}
			if(debitLog.getStatus()==Debit.DEBIT_STATUS_2){
				list_context_item.add("批准");
			}
			if(debitLog.getStatus()==Debit.DEBIT_STATUS_3){
				list_context_item.add("转账");
			}
			list_context.add(list_context_item);
		}
		return list_context;
	}

}
