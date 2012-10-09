package com.fordays.fdpay.transaction.action;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.fordays.fdpay.transaction.ChargeListForm;
import com.fordays.fdpay.transaction.Debit;
import com.fordays.fdpay.transaction.DebitListForm;
import com.fordays.fdpay.transaction.Expense;
import com.fordays.fdpay.transaction.biz.DebitBiz;
import com.neza.base.BaseAction;
import com.neza.base.DownLoadFile;
import com.neza.exception.AppException;
import com.neza.tool.DateUtil;
import com.neza.utility.FileUtil;


public class DebitListAction extends BaseAction
{
	private DebitBiz debitBiz;

	public ActionForward listDebit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws AppException{
		String forwardPage = "";
		
			DebitListForm dlf = (DebitListForm) form;	
			if (dlf == null)
				dlf = new DebitListForm();
		
		List<Debit> list=debitBiz.list(dlf);
		for(int i=0;i<list.size();i++)
		{
			Debit debit=list.get(i);
			Expense exp=debitBiz.getExpenseByDebitId(debit.getId());
			if(exp!=null){
			//	System.out.println(exp.getStatus());
				if(exp.getStatus()!=null){
					if(exp.getStatus()==exp.STATUS_0){
						debit.setExpenseStatus("");
					}
				  if(exp.getStatus()==exp.STATUS_1){
					  
					  debit.setExpenseStatus("审核报销");	
					}
				  if(exp.getStatus()==exp.STATUS_2){
					  debit.setExpenseStatus("批准报销");	
					}
				  if(exp.getStatus()==exp.STATUS_3){
					  debit.setExpenseStatus("报销成功");
					}
				  if(exp.getStatus()==exp.STATUS_4){
					  debit.setExpenseStatus("撤销报销");
					}
				}else{
					debit.setExpenseStatus("未申请报销");
				}
				
			}
			
		}
		
		dlf.setList(list);
		dlf.addSumField(1, "amount");
		request.setAttribute("dlf", dlf);
		forwardPage = "listdebit";
		
		return (mapping.findForward(forwardPage));
	}
	public ActionForward downloadDebit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws AppException{
		
		DebitListForm dlf = (DebitListForm) form;
		
		ArrayList<ArrayList<Object>> lists = debitBiz.listAllDebit(dlf);
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
	public DebitBiz getDebitBiz() {
		return debitBiz;
	}

	public void setDebitBiz(DebitBiz debitBiz) {
		this.debitBiz = debitBiz;
	}
}
