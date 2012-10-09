package com.fordays.fdpay.transaction.action;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.fordays.fdpay.agent.Agent;
import com.fordays.fdpay.agent.biz.AgentBiz;
import com.fordays.fdpay.cooperate.biz.ActionLogBiz;
import com.fordays.fdpay.transaction.OrderDetails;
import com.fordays.fdpay.transaction.TempTransaction;
import com.fordays.fdpay.transaction.TempTransactionBalance;
import com.fordays.fdpay.transaction.Transaction;
import com.fordays.fdpay.transaction.TransactionBalance;
import com.fordays.fdpay.transaction.TransactionListForm;
import com.fordays.fdpay.transaction.biz.TransactionBiz;
import com.neza.base.BaseAction;
import com.neza.base.DownLoadFile;
import com.neza.exception.AppException;
import com.neza.tool.DateUtil;
import com.neza.utility.FileUtil;


public class TransactionListAction extends BaseAction
{
	private TransactionBiz transactionBiz;
	private ActionLogBiz actionLogBiz;
	private AgentBiz agentBiz;
	public void setTransactionBiz(TransactionBiz transactionBiz)throws AppException
		{
			this.transactionBiz = transactionBiz;
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
				
				List list=actionLogBiz.getActionLogs(orderDetails);
				request.setAttribute("list", list);
				request.setAttribute("listSize", list.size());
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
			Transaction transaction = new Transaction();
			transaction.setThisAction("insert");
			request.setAttribute("transaction", transaction);
			String forwardPage = "edittransaction";
			return (mapping.findForward(forwardPage));
		}

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws AppException{
		String forwardPage = "";
		TransactionListForm tlf = (TransactionListForm) form;	
		List list=transactionBiz.getTransactions(tlf);		
		tlf.setList(list);
		tlf.addSumField(1,"amount");

		request.setAttribute("tlf", tlf);
		
		forwardPage = "listtransaction";
		
		return (mapping.findForward(forwardPage));
	}
	
	public  ActionForward notPagingList(ActionMapping mapping,
		    ActionForm form, HttpServletRequest request, HttpServletResponse response)throws AppException{
		TransactionListForm tlf = (TransactionListForm) form;
		
		ArrayList<ArrayList<Object>> lists = transactionBiz.notPagingList(tlf);
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
	
	
	//下载
	public ActionForward getAllTransationListByAgent(ActionMapping mapping,
		    ActionForm form, HttpServletRequest request, HttpServletResponse response)
		    throws AppException{
		TransactionListForm tlf = (TransactionListForm) form;
	
		Agent agent=agentBiz.getAgentById(tlf.getAgent().getId());
		ArrayList<ArrayList<Object>> lists = transactionBiz.getAllTransationList(tlf,agent);
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
	
	
	public ActionForward getAllTransationBalanceByAgent(ActionMapping mapping,
			ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws AppException{
		TransactionListForm tlf = (TransactionListForm) form;
		
		int i=transactionBiz.getTransactionBalanceRow(tlf);
		if(i<=TempTransactionBalance.max_download_amount){
			Agent agent=agentBiz.getAgentById(tlf.getAgent().getId());
			//tlf.setCheck("download");
			ArrayList<ArrayList<Object>> lists = transactionBiz.getAllTransationBalanceByAgent(tlf,agent);
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
		}else{
			request.setAttribute("magMaxDownloadAmount", "您所需数据量太大，请缩小查询时间范围再进行下载！");
			return accountDetaillist2( mapping, tlf,request,response);
		}
		
	}
	
	
	public ActionForward accountDetaillist(ActionMapping mapping,
		    ActionForm form, HttpServletRequest request, HttpServletResponse response)
		    throws AppException
		{
			TransactionListForm talf = (TransactionListForm)form;
		
//			Calendar ca = Calendar.getInstance();
//			int YY = ca.get(Calendar.YEAR);//获取年份
//		    int MM=ca.get(Calendar.MONTH)+1;//获取月份
//		    int DD=ca.get(Calendar.DATE);//获取日
//		    int HH = ca.get(Calendar.HOUR_OF_DAY);//获得时
//		    int NN = ca.get(Calendar.MINUTE);//获得分
//		    int SS = ca.get(Calendar.SECOND);//获得秒
//		    
//		    String month="";
//		    if(MM<10){month="0"+MM; }else{month=MM+"";}
//		    String day="";
//		    if(DD<10){day="0"+DD; }else{day=DD+"";}
//		    String hours="";
//		    if(HH<10){hours="0"+HH; }else{hours=HH+"";}
//		    String minutes="";
//		    if(NN<10){minutes="0"+NN; }else{minutes=NN+"";}
//		    String seconds="";
//		    if(SS<10){seconds="0"+SS; }else{seconds=SS+"";}
//			if(talf.getSelectDealDate().equals("1")){
//				talf.setBeginDate(YY+"-"+month+"-"+day+" 00:00:00");
//				talf.setEndDate(YY+"-"+month+"-"+day+" "+hours+":"+minutes+":"+seconds);
//			}
//			if(talf.getSelectDealDate().equals("2")){
//				talf.setBeginDate(YY+"-"+month+"-01 00:00:00");
//				talf.setEndDate(YY+"-"+month+"-"+day+" "+hours+":"+minutes+":"+seconds);
//			}
//			if(talf.getSelectDealDate().equals("3")){
//				String Nmonth="";
//				int NM=ca.get(Calendar.MONTH);
//				if(NM<10){Nmonth="0"+NM; }else{Nmonth=NM+"";}
//				talf.setBeginDate(YY+"-"+Nmonth+"-01 00:00:00");
//				talf.setEndDate(YY+"-"+month+"-01 00:00:00");
//			}
//			if(talf.getSelectDealDate().equals("4")){
//				talf.setBeginDate(null);
//				talf.setEndDate(null);
//			}
			
			if (talf == null)
			{
				talf = new TransactionListForm();
			}
			Agent agent = agentBiz.getAgentById(Long.parseLong(request.getParameter("agentId")));
			talf.setAgent(agent);
			talf.setPerPageNum(10);
			boolean isPass = true;	
			List talflist = transactionBiz.getAgentTransactions(talf, isPass);
			for (int i = 0; i < talflist.size(); i++)
			{
				TempTransaction transaction = (TempTransaction) talflist.get(i);
				transaction.setTransactioner(agent);
			}
			talf.setList(talflist);
			request.setAttribute("talf", talf);
			request.setAttribute("agent", agent);
			return mapping.findForward("listTransactionByAgent");
			
		}
	public ActionForward accountDetaillist2(ActionMapping mapping,
			ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws AppException
	{
		TransactionListForm tlf = (TransactionListForm)form;
		if (tlf == null)
		{
			tlf = new TransactionListForm();
		}
		Agent agent = agentBiz.getAgentById(Long.parseLong(request.getParameter("agentId")));
		//tlf.setCheck("");
		tlf.setBalanceType(tlf.getBalanceType());
		
		tlf.setAgent(agent);
		tlf.setPerPageNum(10);
		List talflist = transactionBiz.getTransactionBalanceByAgent(tlf);
		tlf.setList(talflist);
		request.setAttribute("tlf", tlf);
		request.setAttribute("agent", agent);
		request.setAttribute("balanceType", tlf.getBalanceType());
		return mapping.findForward("listTransactionBalanceByAgent");
		
	}
	
	public AgentBiz getAgentBiz() {
		return agentBiz;
	}

	public void setAgentBiz(AgentBiz agentBiz) {
		this.agentBiz = agentBiz;
	}
	public ActionLogBiz getActionLogBiz() {
		return actionLogBiz;
	}

	public void setActionLogBiz(ActionLogBiz actionLogBiz) {
		this.actionLogBiz = actionLogBiz;
	}

}
