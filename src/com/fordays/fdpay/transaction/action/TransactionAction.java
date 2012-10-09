package com.fordays.fdpay.transaction.action;

import org.apache.struts.action.*;
import com.neza.exception.AppException;
import com.fordays.fdpay.agent.biz.AgentBiz;
import com.fordays.fdpay.transaction.Transaction;
import com.fordays.fdpay.transaction.biz.TransactionBiz;
import com.neza.base.BaseAction;
import com.neza.base.Inform;


import javax.servlet.http.*;

public class TransactionAction extends BaseAction
{
	private TransactionBiz transactionBiz;
	private AgentBiz agentBiz;

	public TransactionBiz getTransactionBiz()throws AppException
		{
			return transactionBiz;
		}

	public void setTransactionBiz(TransactionBiz transactionBiz)throws AppException
		{
			this.transactionBiz = transactionBiz;
		}

	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws AppException
		{

			String forwardPage = "";
			Transaction transaction = (Transaction) form;
			Inform inf = new Inform();
			try
			{
				Transaction tempTransaction = (Transaction) transactionBiz.getTransactionById(transaction.getId());
				tempTransaction.setNo(transaction.getNo());

				transactionBiz.updateInfo(tempTransaction);
				request.setAttribute("transaction", tempTransaction);

				inf.setMessage("您已经成功更新了用户资料！");
				inf.setForwardPage("/user/userlist.do");
				inf.setParamId("thisAction");
				inf.setParamValue("list");

			}
			catch (Exception ex)
			{
				inf.setMessage("更新用户资料出错！错误信息是：" + ex.getMessage());
				inf.setBack(true);
			}
			request.setAttribute("inf", inf);
			forwardPage = "inform";
			return (mapping.findForward(forwardPage));
		}

	public AgentBiz getAgentBiz() {
		return agentBiz;
	}

	public void setAgentBiz(AgentBiz agentBiz) {
		this.agentBiz = agentBiz;
	}

}