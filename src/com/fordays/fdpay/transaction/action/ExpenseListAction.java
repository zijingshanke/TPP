package com.fordays.fdpay.transaction.action;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


import com.fordays.fdpay.transaction.DebitListForm;
import com.fordays.fdpay.transaction.ExpenseListForm;
import com.fordays.fdpay.transaction.biz.ExpenseBiz;
import com.neza.base.BaseAction;
import com.neza.base.DownLoadFile;
import com.neza.exception.AppException;
import com.neza.tool.DateUtil;
import com.neza.utility.FileUtil;

public class ExpenseListAction extends BaseAction {
	
	private ExpenseBiz expenseBiz;
	
	public ActionForward listExpense(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws AppException{
		String forwardPage = "";
		
			ExpenseListForm dlf = (ExpenseListForm) form;	
			if (dlf == null)
				dlf = new ExpenseListForm();
		
		List list=expenseBiz.list(dlf);
		dlf.setList(list);
		dlf.addSumField(1, "amount");
		//有待解决
		//dlf.addSumField(2, "");
	
		request.setAttribute("dlf", dlf);
		forwardPage = "listexpense";
		
		return (mapping.findForward(forwardPage));
	}
	public ActionForward downloadExpense(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws AppException{
		
		ExpenseListForm elf = (ExpenseListForm) form;
		
		ArrayList<ArrayList<Object>> lists = expenseBiz.listAllExpense(elf);
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
	public ExpenseBiz getExpenseBiz() {
		return expenseBiz;
	}

	public void setExpenseBiz(ExpenseBiz expenseBiz) {
		this.expenseBiz = expenseBiz;
	}

}