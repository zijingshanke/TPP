package com.fordays.fdpay.transaction;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.fordays.fdpay.agent.Agent;
import com.neza.base.ListActionForm;

public class TransactionListForm extends ListActionForm
{
	private int transactionId;
	private int agentId;
	private int agentType=0;
	private String agentName = "";
	private String no = "";
	private Transaction transaction = new Transaction();
	private String beginDate="";
	private String endDate="";
	private String shopName="";
	private long mode=0;
	private String orderNo="";
	private String orderDetailsNo="";
	private Agent agent;
	private String Hql = "";
	private String selectDealDate;
	protected long orderDetailsType=0;
	private String check="";
	private int balanceType=0;

	public int getBalanceType() {
		return balanceType;
	}


	public void setBalanceType(int balanceType) {
		this.balanceType = balanceType;
	}


	public String getCheck() {
		return check;
	}


	public void setCheck(String check) {
		this.check = check;
	}


	public String getSelectDealDate() {
		return selectDealDate;
	}


	public void setSelectDealDate(String selectDealDate) {
		this.selectDealDate = selectDealDate;
		if("1".equals(selectDealDate)){
			Calendar ca = Calendar.getInstance();
			int YY = ca.get(Calendar.YEAR);//获取年份
		    int MM=ca.get(Calendar.MONTH)+1;//获取月份
		    int DD=ca.get(Calendar.DATE);//获取日
		    int HH = ca.get(Calendar.HOUR_OF_DAY);//获得时
		    int NN = ca.get(Calendar.MINUTE);//获得分
		    int SS = ca.get(Calendar.SECOND);//获得秒
		    
		    String month="";
		    if(MM<10){month="0"+MM; }else{month=MM+"";}
		    String day="";
		    if(DD<10){day="0"+DD; }else{day=DD+"";}
		    String hours="";
		    if(HH<10){hours="0"+HH; }else{hours=HH+"";}
		    String minutes="";
		    if(NN<10){minutes="0"+NN; }else{minutes=NN+"";}
		    String seconds="";
		    if(SS<10){seconds="0"+SS; }else{seconds=SS+"";}
			this.beginDate=YY+"-"+month+"-"+day+" 00:00:00";
			this.endDate=YY+"-"+month+"-"+day+" 23:59:59";
		}
	}


	public String getHql() {
		return Hql;
	}


	public void setHql(String hql) {
		Hql = hql;
	}


	public String getOrderDetailsNo() {
		return orderDetailsNo;
	}


	public void setOrderDetailsNo(String orderDetailsNo) {
		this.orderDetailsNo = orderDetailsNo;
	}


	public String getShopName() {
		return shopName;
	}


	public void setShopName(String shopName) {
		this.shopName = shopName;
	}


	public String getBeginDate() {
		return beginDate;
	}


	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}



	public String getEndDate() {
		return endDate;
	}



	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}



	public int getAgentId()
		{
			return agentId;
		}



	public void setAgentId(int agentId)
		{
			this.agentId = agentId;
		}



	public int getAgentType()
		{
			return agentType;
		}



	public void setAgentType(int agentType)
		{
			this.agentType = agentType;
		}



	public String getAgentName()
		{
			return agentName;
		}



	public void setAgentName(String agentName)
		{
			this.agentName = agentName;
		}



	public String getNo()
		{
			return no;
		}



	public void setNo(String no)
		{
			this.no = no;
		}



	public void reset(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest)
	{
		mode=0;
		no="";
		shopName="";
		agentName="";
		beginDate="";
		endDate="";
		thisAction = "";
		orderDetailsType=0;
		this.setIntPage(1);
		balanceType=0;
	}



	public int getTransactionId()
		{
			return transactionId;
		}



	public void setTransactionId(int transactionId)
		{
			this.transactionId = transactionId;
		}


	public Transaction getTransaction() {
		return transaction;
	}



	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}


	public long getMode() {
		return mode;
	}


	public void setMode(long mode) {
		this.mode = mode;
	}


	public String getOrderNo() {
		return orderNo;
	}


	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}


	public Agent getAgent() {
		return agent;
	}


	public void setAgent(Agent agent) {
		this.agent = agent;
	}


	public Long getOrderDetailsType() {
		return orderDetailsType;
	}


	public void setOrderDetailsType(Long orderDetailsType) {
		this.orderDetailsType = orderDetailsType;
	}


}
