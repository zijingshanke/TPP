package com.fordays.fdpay.transaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TempTransactionBalance
{
	private static final long serialVersionUID = 1L;

	public static final long max_download_amount = 60000;
	protected long id;
	protected Long transactionId;
	protected Long transactionType;
	protected Long transactionStatus;
	protected BigDecimal balance;
	protected BigDecimal creditBalance;
	protected BigDecimal notallowBalance;
	protected Long agentId;
	protected Long status;
	protected Date transactionDate;
	protected long orderDetailsId;
	protected String orderDetailsNo;
	protected String orderNo;
	protected BigDecimal amount;
	protected long fromAgentId;	
	protected long toAgentId;
	protected String fromAgentName;
	protected String fromAgentEmail;

	protected String toAgentName;
	protected String toAgentEmail;

	protected String typeCaption;
	protected String shopName;
	protected String remark;

	protected List operate;

	private BigDecimal conteBalance =new BigDecimal(0);
	public BigDecimal getConteBalance() {
		
		if(this.balance==null ){
			this.balance=new BigDecimal(0);
		}
		if(this.creditBalance==null ){
			this.creditBalance=new BigDecimal(0);
		}
		if(this.notallowBalance==null ){
			this.notallowBalance=new BigDecimal(0);
		}
		return this.balance.add(this.creditBalance).add(this.notallowBalance);
		
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public java.math.BigDecimal getBalance()
	{
		return balance;
	}

	public void setBalance(java.math.BigDecimal balance)
	{
		this.balance = balance;
	}

	public java.math.BigDecimal getCreditBalance()
	{
		return creditBalance;
	}

	public void setCreditBalance(java.math.BigDecimal creditBalance)
	{
		this.creditBalance = creditBalance;
	}

	public Long getAgentId()
	{
		return agentId;
	}

	public void setAgentId(Long agentId)
	{
		this.agentId = agentId;
	}

	public Long getStatus()
	{
		return status;
	}

	public void setStatus(Long status)
	{
		this.status = status;
	}

	public Date getTransactionDate()
	{
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate)
	{
		this.transactionDate = transactionDate;
	}

	public Long getTransactionId()
	{
		return transactionId;
	}

	public void setTransactionId(Long transactionId)
	{
		this.transactionId = transactionId;
	}

	public TempTransactionBalance()
	{
	}
 
	public TempTransactionBalance(long id, Long transactionId,
      Long transactionType, Long transactionStatus, BigDecimal balance,BigDecimal notallowBalance,
      BigDecimal creditBalance, Long agentId, Long status,
      Date transactionDate, long orderDetailsId, String orderDetailsNo,
      String orderNo, BigDecimal amount, long fromAgentId, long toAgentId,
      String fromAgentName, String fromAgentEmail, String toAgentName,
      String toAgentEmail, String shopName, String remark)
  {
	  super();
	  this.id = id;
	  this.transactionId = transactionId;
	  this.transactionType = transactionType;
	  this.transactionStatus = transactionStatus;
	  this.balance = balance;
	  this.notallowBalance=notallowBalance;
	  this.creditBalance = creditBalance;
	  this.agentId = agentId;
	  this.status = status;
	  this.transactionDate = transactionDate;
	  this.orderDetailsId = orderDetailsId;
	  this.orderDetailsNo = orderDetailsNo;
	  this.orderNo = orderNo;
	  this.amount = amount;
	  this.fromAgentId = fromAgentId;
	  this.toAgentId = toAgentId;
	  this.fromAgentName = fromAgentName;
	  this.fromAgentEmail = fromAgentEmail;
	  this.toAgentName = toAgentName;
	  this.toAgentEmail = toAgentEmail;
	  this.shopName = shopName;
	  this.remark = remark;
  }
	


	public List getOperate()
	{
		List<Operate> list = new ArrayList<Operate>();
		Operate op = new Operate();
		String url = "";

		url = "javascript:getTransactionSuccessAndFailDetail('" + transactionId
		    + "','" + transactionStatus + "','" + orderDetailsId + "','1','"
		    + fromAgentId + "','" + transactionType + "')";

		op.setUrl(url);
		op.setUrlName("查看");
		list.add(op);
		return list;
	}
	/*
	 * public List _getOperate(){ List<Operate> list = new ArrayList<Operate>();
	 * Operate op = new Operate(); String url = "";
	 * 
	 * url = "javascript:getTransactionSuccessAndFailDetail('"
	 * +this.getTransaction().getId() +"','"+this.getTransaction().getStatus()
	 * +"','"+this.getTransaction().getOrderDetails().getId()
	 * +"','1','"+this.getTransaction().getFromAgent().getId()
	 * +"','"+this.getTransaction().getType() +"')";
	 * 
	 * op.setUrl(url); op.setUrlName("查看"); list.add(op); return list; }
	 */

	public Long getTransactionType()
  {
		if(transactionType==null)
			transactionType=0l; 
  	return transactionType;
  }

	public void setTransactionType(Long transactionType)
  {
  	this.transactionType = transactionType;
  }

	public Long getTransactionStatus()
  {
  	return transactionStatus;
  }

	public void setTransactionStatus(Long transactionStatus)
  {
  	this.transactionStatus = transactionStatus;
  }



	public long getOrderDetailsId()
  {
  	return orderDetailsId;
  }

	public void setOrderDetailsId(long orderDetailsId)
  {
  	this.orderDetailsId = orderDetailsId;
  }

	public String getOrderDetailsNo()
  {
  	return orderDetailsNo;
  }

	public void setOrderDetailsNo(String orderDetailsNo)
  {
  	this.orderDetailsNo = orderDetailsNo;
  }

 

	public String getOrderNo()
  {
  	return orderNo;
  }

	public void setOrderNo(String orderNo)
  {
  	this.orderNo = orderNo;
  }

	public BigDecimal getAmount()
  {
  	return amount;
  }

	public void setAmount(BigDecimal amount)
  {
  	this.amount = amount;
  }

	public long getFromAgentId()
  {
  	return fromAgentId;
  }

	public void setFromAgentId(long fromAgentId)
  {
  	this.fromAgentId = fromAgentId;
  }

	public long getToAgentId()
  {
  	return toAgentId;
  }

	public void setToAgentId(long toAgentId)
  {
  	this.toAgentId = toAgentId;
  }

	public String getFromAgentName()
  {
  	return fromAgentName;
  }

	public void setFromAgentName(String fromAgentName)
  {
  	this.fromAgentName = fromAgentName;
  }

	public String getFromAgentEmail()
  {
  	return fromAgentEmail;
  }

	public void setFromAgentEmail(String fromAgentEmail)
  {
  	this.fromAgentEmail = fromAgentEmail;
  }

	public String getToAgentName()
  {
  	return toAgentName;
  }

	public void setToAgentName(String toAgentName)
  {
  	this.toAgentName = toAgentName;
  }

	public String getToAgentEmail()
  {
  	return toAgentEmail;
  }

	public void setToAgentEmail(String toAgentEmail)
  {
  	this.toAgentEmail = toAgentEmail;
  }

	public String getShopName()
  {
  	return shopName;
  }

	public void setShopName(String shopName)
  {
  	this.shopName = shopName;
  }

	public String getRemark()
  {
  	return remark;
  }

	public void setRemark(String remark)
  {
  	this.remark = remark;
  }

	public String getTypeCaption()
  {
		
			return Transaction.getTypeCaption(this.getTransactionType().intValue());
  }

	public BigDecimal getNotallowBalance() {
		return notallowBalance;
	}

	public void setNotallowBalance(BigDecimal notallowBalance) {
		this.notallowBalance = notallowBalance;
	}
	
	
}
