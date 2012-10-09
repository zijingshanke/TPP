package com.fordays.fdpay.transaction.action;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.fordays.fdpay.agent.biz.AgentBiz;
import com.fordays.fdpay.transaction.Charge;
import com.fordays.fdpay.transaction.ChargeListForm;
import com.fordays.fdpay.transaction.OrderDetails;
import com.fordays.fdpay.transaction.Transaction;
import com.fordays.fdpay.transaction.biz.ChargeBiz;
import com.fordays.fdpay.transaction.biz.TransactionBiz;
import com.neza.base.BaseAction;
import com.neza.base.DownLoadFile;
import com.neza.exception.AppException;
import com.neza.tool.DateUtil;
import com.neza.utility.FileUtil;


public class ChargeListAction extends BaseAction
{
	private TransactionBiz transactionBiz;
	private AgentBiz agentBiz;
	private ChargeBiz chargeBiz;

	public AgentBiz getAgentBiz() throws AppException{
		return agentBiz;
	}

	public void setAgentBiz(AgentBiz agentBiz) throws AppException{
		this.agentBiz = agentBiz;
	}

	public void setChargeBiz(ChargeBiz chargeBiz) throws AppException{
		this.chargeBiz = chargeBiz;
	}

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws AppException
		{
			String forwardPage = "";
			
			int id = Integer.parseInt( request.getParameter("Id"));
			if (id > 0)
			{
				Transaction transaction = (Transaction) transactionBiz.queryTransactionById(id);
				 
			    OrderDetails orderDetails=transaction.getOrderDetails();
				transaction.setThisAction("view");
				request.setAttribute("orderDetails", orderDetails);
			}
			else
			{
				forwardPage = "login";
			}

			forwardPage = "viewtransaction";
			return (mapping.findForward(forwardPage));
		}

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws AppException
		{
			Charge charge = new Charge();
			charge.setThisAction("insert");
			request.setAttribute("charge", charge);
			String forwardPage = "editcharge";
			return (mapping.findForward(forwardPage));
		}

	
	public ActionForward listGeneralCharge(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws AppException{
		String forwardPage = "";
		
			ChargeListForm clf = (ChargeListForm) form;	
			if (clf == null)
				clf = new ChargeListForm();
		
		List list=chargeBiz.getCharges(clf);
		BigDecimal sumAmount=chargeBiz.getSumAmountByGeneralCharge(clf);
		clf.setList(list);
		clf.addSumField(1, "amount");
		request.setAttribute("sumAmount", sumAmount);
		request.setAttribute("clf", clf);
		forwardPage = "listGeneralCharge";
		
		return (mapping.findForward(forwardPage));
	}
	
	public ActionForward listAllCharge(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws AppException{
		String forwardPage = "";
		
		ChargeListForm clf = (ChargeListForm) form;	
		if (clf == null)
			clf = new ChargeListForm();
		
		List list=chargeBiz.getAllCharge(clf);
		BigDecimal sumAmount=chargeBiz.getSumAmountByCharge(clf);
		clf.setList(list);
		clf.addSumField(1, "amount");
		request.setAttribute("sumAmount", sumAmount);
		request.setAttribute("clf", clf);
		forwardPage = "listAllCharge";
		
		return (mapping.findForward(forwardPage));
	}
	
	
	public ActionForward downloadAllCharge(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws AppException{
		
		ChargeListForm cl = (ChargeListForm) form;
		
		ArrayList<ArrayList<Object>> lists = chargeBiz.listAllCharge(cl);
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
	
	public ActionForward downloadGeneralCharge(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws AppException{
		
		ChargeListForm cl = (ChargeListForm) form;
		
		ArrayList<ArrayList<Object>> lists = chargeBiz.listAllCharge(cl);
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

	public ActionForward listOtherCharge(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws AppException{
		String forwardPage = "";
		
			ChargeListForm clf = (ChargeListForm) form;
			if (clf == null)
				clf = new ChargeListForm();
		
		List list=chargeBiz.getOtherCharges(clf);
		clf.setList(list);
		clf.setPerPageNum(8);
		clf.addSumField(1, "amount");
		request.setAttribute("clf", clf);
		forwardPage = "listothercharge";
		
		return (mapping.findForward(forwardPage));
	}
	
	public ActionForward downloadOtherCharge(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws AppException{
		
		ChargeListForm cc = (ChargeListForm) form;
		
		ArrayList<ArrayList<Object>> lists = chargeBiz.getAllOtherCharge(cc);
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
}
