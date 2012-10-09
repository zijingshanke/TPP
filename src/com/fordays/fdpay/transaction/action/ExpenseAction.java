package com.fordays.fdpay.transaction.action;


import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.fordays.fdpay.agent.Agent;
import com.fordays.fdpay.agent.biz.AgentBiz;
import com.fordays.fdpay.right.UserRightInfo;
import com.fordays.fdpay.transaction.Expense;
import com.fordays.fdpay.transaction.OrderDetails;
import com.fordays.fdpay.transaction.biz.ExpenseBiz;
import com.fordays.fdpay.transaction.biz.ExpenseLogBiz;
import com.fordays.fdpay.transaction.biz.OrderDetailsBiz;
import com.fordays.fdpay.user.SysUser;
import com.fordays.fdpay.user.biz.UserBiz;
import com.neza.base.BaseAction;
import com.neza.base.Inform;
import com.neza.exception.AppException;

public class ExpenseAction extends BaseAction {
	private ExpenseBiz expenseBiz;
	private UserBiz userBiz;
	private AgentBiz agentBiz;
	private OrderDetailsBiz orderDetailsBiz;
	private ExpenseLogBiz expenseLogBiz;
	
	public SysUser getUserByURI(HttpServletRequest request){
		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute("URI");
		if(uri!=null&&uri.getUser()!=null)
			return uri.getUser();
		else{
			return null;
		}
	}
	
	//编辑报销
	public ActionForward editExpense(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		Expense expense = (Expense) form;
		if (expense.getId() > 0) {
			expense = expenseBiz.getExpenseById(expense.getId());
			request.setAttribute("expense", expense);
			forwardPage = "editExpense";
		}
		return mapping.findForward(forwardPage);
	}
	
	//审核报销
	public ActionForward auditExpense(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		Inform inf = new Inform();
		String forwardPage = "";
		// 获取操作人
		SysUser use = this.getUserByURI(request);
		if(use!=null){
			SysUser user = userBiz.getUserByNo(use);
			Expense expense = (Expense) form;
			if(expense.getStatus()!=Expense.STATUS_1 && expense.getStatus()==Expense.STATUS_0){
				Expense tempExpense = expenseBiz.getExpenseById(expense.getId());
				
				tempExpense.setStatus(Expense.STATUS_1);
				tempExpense.setUserName(user.getUserName());
				if(expense.getNote1()!=null)
					tempExpense.setNote(tempExpense.getNote()+"\\n"+expense.getNote1());
				
				tempExpense.setCheckDate(new Timestamp(System.currentTimeMillis()));
				expenseBiz.updateInfo(tempExpense);
				// 添加报销日志
				expenseLogBiz.auditLog(tempExpense,tempExpense.getAgent(),user,expense.getNote1());
			}
			inf.setMessage("您审核了这条报销！");
			inf.setForwardPage("/transaction/expenselist.do");
			inf.setParamId("thisAction");
			inf.setParamValue("listExpense");
		}
		else{
			request.getSession().invalidate();
			return mapping.findForward("exit");
		}
			request.setAttribute("inf", inf);
			forwardPage = "inform";
			return (mapping.findForward(forwardPage));
		
	}
	
	// 批准报销
	public ActionForward approvalExpense(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		Inform inf = new Inform();
		String forwardPage = "";
		//获取操作人
		SysUser use = this.getUserByURI(request);
		if(use!=null){
			SysUser user = userBiz.getUserByNo(use);
			Expense expense = (Expense) form;
			if(expense.getStatus()!=Expense.STATUS_2&&expense.getStatus()==Expense.STATUS_1){
				Expense newExpense = expenseBiz.getExpenseById(expense.getId());
				newExpense.setStatus(Expense.STATUS_2);
				newExpense.setUser1Name(user.getUserName());
				if(expense.getNote1()!=null)
					newExpense.setNote(newExpense.getNote()+"\\n"+expense.getNote1());
				newExpense.setCheck1Date(new Timestamp(System.currentTimeMillis()));
				//批准报销
				expenseBiz.updateInfo(newExpense);
				// 添加报销日志
				expenseLogBiz.approvalLog(newExpense,newExpense.getAgent(),user,expense.getNote1());
			}
			inf.setMessage("您批准了这条报销！");
			inf.setForwardPage("/transaction/expenselist.do");
			inf.setParamId("thisAction");
			inf.setParamValue("listExpense");
		}else{
			request.getSession().invalidate();
			return mapping.findForward("exit");
		}
		request.setAttribute("inf", inf);
		forwardPage = "inform";
		return (mapping.findForward(forwardPage));
	}
	
	//报销成功
	public ActionForward advanceExpense(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		Inform inf = new Inform();
		String forwardPage = "";
		//获取操作人
		SysUser use = this.getUserByURI(request);
		if(use!=null){
			SysUser user = userBiz.getUserByNo(use);
			Expense expense = (Expense) form;
			Agent agent = agentBiz.getAgentByEmail(expense.getAgentLoginName());
			agentBiz.synallowBalance(agent);
			Expense newExpense = expenseBiz.getExpenseById(expense.getId());
			agentBiz.synallowBalance(newExpense.getFromAgent());
			BigDecimal operationAmount=new BigDecimal(0);
			boolean checkAgent=false;
			if(newExpense.getDebit().getAmount().compareTo( newExpense.getAmount())==1){//原来预支金额大于报销金额
			  operationAmount=newExpense.getDebit().getAmount().subtract(newExpense.getAmount());
			  if(agentBiz.getAgentAllowBalance(agent.getId()).compareTo(operationAmount)==1){
				  checkAgent=true;
			  }else{
				  inf.setMessage("商户余额不足完成报销操作！请检查商户余额！");
			  }
			}
			else if(newExpense.getDebit().getAmount().compareTo( newExpense.getAmount())==-1){//原来预支金额小于报销金额
			  operationAmount=newExpense.getAmount().subtract(newExpense.getDebit().getAmount());
			  if(agentBiz.getAgentAllowBalance(newExpense.getFromAgent().getId()).compareTo(operationAmount)==1){
				  checkAgent=true;
			  }else{
				  inf.setMessage("商户余额不足完成报销操作！请通知报销商户！");
			  }
			}else if(newExpense.getDebit().getAmount().compareTo( newExpense.getAmount())==0){
				checkAgent=true;
			}
			if(checkAgent){
				if(expense.getStatus()!=Expense.STATUS_3&&expense.getStatus()==Expense.STATUS_2){
					newExpense.setStatus(Expense.STATUS_3);
					newExpense.setUser2Name(user.getUserName());
					if(expense.getNote1()!=null)
						newExpense.setNote(newExpense.getNote()+"\\n"+expense.getNote1());
					newExpense.setCheck2Date(new Timestamp(System.currentTimeMillis()));
					//完成报销
					expenseBiz.advanceAchieve(newExpense);
					// 添加报销日志
					expenseLogBiz.advanceLog(newExpense,agent,user,expense.getNote1());
				}
				inf.setMessage("报销成功！");
				inf.setForwardPage("/transaction/expenselist.do");
				inf.setParamId("thisAction");
				inf.setParamValue("listExpense");
			}else{
				
				inf.setForwardPage("/transaction/expenselist.do");
				inf.setParamId("thisAction");
				inf.setParamValue("listExpense");
			}
		}else{
			request.getSession().invalidate();
			return mapping.findForward("exit");
		}
		request.setAttribute("inf", inf);
		forwardPage = "inform";
		return (mapping.findForward(forwardPage));
	}
	
	//撤销报销
	public ActionForward revocationExpense(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		Inform inf = new Inform();
		String forwardPage = "";
		Expense expense = (Expense) form;
		Expense tmpExpense=expenseBiz.getExpenseById(expense.getId());
		tmpExpense.setStatus(Expense.STATUS_4);
		expenseBiz.updateInfo(tmpExpense);
		//修改原來orderDetails狀態，讓他可以重新報銷操作
		OrderDetails orderDetails=orderDetailsBiz.getOrderDetalisByOrderNo(tmpExpense.getDebit().getNo());
		orderDetails.setStatus(OrderDetails.STATUS_0);
		orderDetailsBiz.save(orderDetails);
		
		//报销撤销不操作金额
		inf.setMessage("您撤销了这条报销！");
		inf.setForwardPage("/transaction/expenselist.do");
		inf.setParamId("thisAction");
		inf.setParamValue("listExpense");
		request.setAttribute("inf", inf);
		forwardPage = "inform";
		return (mapping.findForward(forwardPage));
	}
	

	public UserBiz getUserBiz() {
		return userBiz;
	}

	public void setUserBiz(UserBiz userBiz) {
		this.userBiz = userBiz;
	}

	public AgentBiz getAgentBiz() {
		return agentBiz;
	}

	public void setAgentBiz(AgentBiz agentBiz) {
		this.agentBiz = agentBiz;
	}

	public ExpenseBiz getExpenseBiz() {
		return expenseBiz;
	}

	public void setExpenseBiz(ExpenseBiz expenseBiz) {
		this.expenseBiz = expenseBiz;
	}

	public ExpenseLogBiz getExpenseLogBiz() {
		return expenseLogBiz;
	}

	public void setExpenseLogBiz(ExpenseLogBiz expenseLogBiz) {
		this.expenseLogBiz = expenseLogBiz;
	}

	public OrderDetailsBiz getOrderDetailsBiz() {
		return orderDetailsBiz;
	}

	public void setOrderDetailsBiz(OrderDetailsBiz orderDetailsBiz) {
		this.orderDetailsBiz = orderDetailsBiz;
	}
}