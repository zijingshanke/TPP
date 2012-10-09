package com.fordays.fdpay.transaction.action;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.fordays.fdpay.right.UserRightInfo;
import com.fordays.fdpay.transaction.DebitLogListForm;
import com.fordays.fdpay.transaction.Expense;
import com.fordays.fdpay.transaction.ExpenseLogListForm;
import com.fordays.fdpay.transaction.biz.ExpenseBiz;
import com.fordays.fdpay.transaction.biz.ExpenseLogBiz;
import com.fordays.fdpay.user.SysUser;
import com.neza.base.BaseAction;
import com.neza.base.DownLoadFile;
import com.neza.exception.AppException;
import com.neza.tool.DateUtil;
import com.neza.utility.FileUtil;

public class ExpenseLogListAction extends BaseAction {
	private ExpenseLogBiz expenseLogBiz;
	
	private ExpenseBiz expenseBiz;
	
	
	
	public SysUser getUserByURI(HttpServletRequest request){
		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute("URI");
		if(uri!=null&&uri.getUser()!=null)
			return uri.getUser();
		else{
			return null;
		}
	}
	public ActionForward listExpenseByContent(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws AppException {
		String forwardPage = "";
		ExpenseLogListForm clf = (ExpenseLogListForm) form;	
			if (clf == null)
				clf = new ExpenseLogListForm();
		String orderNo=clf.getOrderNo();
		List list=expenseLogBiz.getExpenseLogByContent(clf);
		Expense expense=expenseBiz.getDebitByNo(orderNo);
		request.setAttribute("expense", expense);
		BigDecimal finalAmount=new BigDecimal(0);
		if(expense.getDebit().getAmount().compareTo( expense.getAmount())==-1){//原来预支金额小于报销金额
			finalAmount=expense.getAmount().subtract(expense.getDebit().getAmount());	
		}
		if(expense.getDebit().getAmount().compareTo( expense.getAmount())==1){//原来预支金额大于报销金额
			finalAmount=expense.getDebit().getAmount().subtract(expense.getAmount());	
		}
		request.setAttribute("finalAmount", finalAmount);
		clf.setList(list);
		request.setAttribute("clf", clf);
		forwardPage = "listExpenseLogByContent";
		return mapping.findForward(forwardPage);
	}
	public ActionForward listExpenseLog(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		
		ExpenseLogListForm elof = (ExpenseLogListForm) form;	
			if (elof == null)
				elof = new ExpenseLogListForm();
			
		List list=expenseLogBiz.list(elof);
		
		elof.setList(list);
		elof.addSumField(1,"amount");
		
		request.setAttribute("elof", elof);
		forwardPage = "listExpenseLog";
		
		return (mapping.findForward(forwardPage));
	}
	
	
	public ActionForward downloadExpenseDetailed(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws AppException{
		
		ExpenseLogListForm dlf = (ExpenseLogListForm) form;
		String orderNo=dlf.getOrderNo();
		ArrayList<ArrayList<Object>> lists = expenseLogBiz.getExpenseLogDetailed(dlf);
		String outFileName = DateUtil.getDateString("yyyyMMddhhmmss") + ".csv";
		String outText = FileUtil.createCSVFile(lists);
		try
		{
			outText = new String(outText.getBytes("UTF-8"));
		}
		catch (Exception ex)
		{

		}
		DownLoadFile df = new DownLoadFile();
		df.performTask(response, outText, outFileName, "GBK");
		return null;
	}
	public ExpenseLogBiz getExpenseLogBiz() {
		return expenseLogBiz;
	}
	public void setExpenseLogBiz(ExpenseLogBiz expenseLogBiz) {
		this.expenseLogBiz = expenseLogBiz;
	}
	public ExpenseBiz getExpenseBiz() {
		return expenseBiz;
	}
	public void setExpenseBiz(ExpenseBiz expenseBiz) {
		this.expenseBiz = expenseBiz;
	}
	
}