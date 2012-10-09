package com.fordays.fdpay.transaction.action;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.fordays.fdpay.transaction.Debit;
import com.fordays.fdpay.transaction.DebitListForm;
import com.fordays.fdpay.transaction.DebitLogListForm;
import com.fordays.fdpay.transaction.biz.DebitBiz;
import com.fordays.fdpay.transaction.biz.DebitLogBiz;
import com.neza.base.BaseAction;
import com.neza.base.DownLoadFile;
import com.neza.exception.AppException;
import com.neza.tool.DateUtil;
import com.neza.utility.FileUtil;


public class DebitLogListAction extends BaseAction
{
	private DebitLogBiz debitLogBiz;
	private DebitBiz debitBiz;
	
	public ActionForward listDebitLog(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws AppException{
		String forwardPage = "";
		
		DebitLogListForm dlof = (DebitLogListForm) form;	
			if (dlof == null)
				dlof = new DebitLogListForm();
			
		List list=debitLogBiz.list(dlof);
		dlof.setList(list);
		dlof.addSumField(1,"amount");
		
		request.setAttribute("dlof", dlof);
		forwardPage = "listDebitLog";
		
		return (mapping.findForward(forwardPage));
	}

	public DebitLogBiz getDebitLogBiz() {
		return debitLogBiz;
	}

	public void setDebitLogBiz(DebitLogBiz debitLogBiz) {
		this.debitLogBiz = debitLogBiz;
	}

	public ActionForward listDebitByContent(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws AppException {
		String forwardPage = "";
		DebitLogListForm clf = (DebitLogListForm) form;	
			if (clf == null)
				clf = new DebitLogListForm();
		String orderNo=clf.getOrderNo();
		List list=debitLogBiz.getDebitLogByContent(clf);
		Debit debit=debitBiz.getDebitByNo(orderNo);
		request.setAttribute("debit", debit);
		clf.setList(list);
		request.setAttribute("clf", clf);
		forwardPage = "listDebitLogByContent";
		
		return (mapping.findForward(forwardPage));
	}

	public ActionForward downloadDebitDetailed(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws AppException{
		
		DebitLogListForm dlf = (DebitLogListForm) form;
		String orderNo=dlf.getOrderNo();
		ArrayList<ArrayList<Object>> lists = debitLogBiz.getDebitLogDetailed(dlf);
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
