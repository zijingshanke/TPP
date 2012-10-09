package com.fordays.fdpay.transaction.biz;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import com.fordays.fdpay.agent.Agent;
import com.fordays.fdpay.transaction.Debit;
import com.fordays.fdpay.transaction.DebitLog;
import com.fordays.fdpay.transaction.Expense;
import com.fordays.fdpay.transaction.ExpenseLog;
import com.fordays.fdpay.transaction.ExpenseLogListForm;
import com.fordays.fdpay.transaction.dao.ExpenseDAO;
import com.fordays.fdpay.transaction.dao.ExpenseLogDAO;
import com.fordays.fdpay.user.SysUser;
import com.neza.exception.AppException;
import com.neza.tool.DateUtil;

public class ExpenseLogBizImp implements ExpenseLogBiz {
	private TransactionTemplate transactionTemplate;
	private ExpenseLogDAO expenseLogDAO;
	private ExpenseDAO expenseDAO;

	public void setTransactionManager(
			HibernateTransactionManager transactionManager) {
		this.transactionTemplate = new TransactionTemplate(transactionManager);
	}

	public List list(ExpenseLogListForm dlf) throws AppException {
		List<ExpenseLog> newlist = expenseLogDAO.list(dlf);
		for (int i = 0; i < newlist.size(); i++) {
			ExpenseLog el = (ExpenseLog) newlist.get(i);
			Expense e = expenseDAO.getExpenseByNo(el.getExpenseNo());
			el.setFromAgent(e.getFromAgent());
			newlist.set(i, el);
		}
		return expenseLogDAO.list(dlf);
	}

	// 完成报销
	public void advanceLog(Expense tempExpense, Agent agent, SysUser user,
			String note) throws AppException {
		ExpenseLog expenseLog = new ExpenseLog();
		expenseLog.setChargeDate(new Timestamp(System.currentTimeMillis()));
		expenseLog.setAgent(agent);
		expenseLog.setAmount(tempExpense.getAmount());
		expenseLog.setExpenseNo(tempExpense.getNo());
		expenseLog.setContent(note);
		expenseLog.setStatus(Expense.STATUS_3);
		expenseLog.setOperater(user.getUserName());
		expenseLogDAO.save(expenseLog);
	}

	// 批准
	public void approvalLog(Expense tempExpense, Agent agent, SysUser user,
			String note) throws AppException {
		ExpenseLog expenseLog = new ExpenseLog();
		expenseLog.setChargeDate(new Timestamp(System.currentTimeMillis()));
		expenseLog.setAgent(agent);
		expenseLog.setAmount(tempExpense.getAmount());
		expenseLog.setExpenseNo(tempExpense.getNo());
		expenseLog.setContent(note);
		expenseLog.setStatus(Expense.STATUS_2);
		expenseLog.setOperater(user.getUserName());
		expenseLogDAO.save(expenseLog);
	}

	// 审核
	public void auditLog(Expense tempExpense, Agent agent, SysUser user,
			String note) throws AppException {
		ExpenseLog expenseLog = new ExpenseLog();
		expenseLog.setChargeDate(new Timestamp(System.currentTimeMillis()));
		expenseLog.setAgent(agent);
		expenseLog.setAmount(tempExpense.getAmount());
		expenseLog.setExpenseNo(tempExpense.getNo());
		expenseLog.setContent(note);
		expenseLog.setStatus(Expense.STATUS_1);
		expenseLog.setOperater(user.getUserName());
		expenseLogDAO.save(expenseLog);
	}

	public List getExpenseLogByContent(ExpenseLogListForm clf)
			throws AppException {
		return expenseLogDAO.getExpenseLogByContent(clf);
	}

	public ExpenseLogDAO getExpenseLogDAO() {
		return expenseLogDAO;
	}

	public void setExpenseLogDAO(ExpenseLogDAO expenseLogDAO) {
		this.expenseLogDAO = expenseLogDAO;
	}

	public ExpenseDAO getExpenseDAO() {
		return expenseDAO;
	}

	public void setExpenseDAO(ExpenseDAO expenseDAO) {
		this.expenseDAO = expenseDAO;
	}

	public ArrayList<ArrayList<Object>> getExpenseLogDetailed(
			ExpenseLogListForm elf) throws AppException {
		Expense expense=expenseDAO.getExpenseByNo(elf.getOrderNo());
		List data =expenseLogDAO.getExpenseLogDetailed(elf);
		ArrayList<ArrayList<Object>> list_context = new ArrayList<ArrayList<Object>>();
		ArrayList<Object> list_title = new ArrayList<Object>();
		list_title.add("#报销详细");
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
		list_title.add("报销金额");
		list_title.add("实际操作金额");
		list_context.add(list_title);
		
		list_title =  new ArrayList<Object>();
		list_title.add(expense.getNo());
		list_title.add(expense.getRemark());
		list_title.add(expense.getFromAgent().getName()+"("+expense.getFromAgent().getLoginName()+")");
		list_title.add(expense.getAgent().getName()+"("+expense.getAgent().getLoginName()+")");
		list_title.add(expense.getDebit().getAmount());
		list_title.add(expense.getAmount());
		if(expense.getDebit().getAmount().compareTo( expense.getAmount())==-1){//原来预支金额小于报销金额
			list_title.add(expense.getAmount().subtract(expense.getDebit().getAmount()));	
		}
		if(expense.getDebit().getAmount().compareTo( expense.getAmount())==1){//原来预支金额大于报销金额
			list_title.add(expense.getDebit().getAmount().subtract(expense.getAmount()));	
		}
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
			ExpenseLog expenseLog = (ExpenseLog) data.get(i);
			ArrayList<Object> list_context_item = new ArrayList<Object>();
			list_context_item.add(DateUtil.getDateString(expenseLog.getChargeDate(), "yyyy年MM月dd日HH:mm:ss"));
			list_context_item.add(expenseLog.getOperater());
			list_context_item.add(expenseLog.getContent());
			if(expenseLog.getStatus()==Expense.STATUS_0){
				list_context_item.add("申请");
			}
			if(expenseLog.getStatus()==Expense.STATUS_1){
				list_context_item.add("审核");
			}
			if(expenseLog.getStatus()==Expense.STATUS_2){
				list_context_item.add("批准");
			}
			if(expenseLog.getStatus()==Expense.STATUS_3){
				list_context_item.add("转账");
			}
			list_context.add(list_context_item);
		}
		return list_context;
	}

}
