package com.fordays.fdpay.transaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fordays.fdpay.agent.Agent;

public class TempTransaction
{
	private static final long serialVersionUID = 1L;
	private OrderDetails orderDetails;
	private long id = 0;
	private Date accountDate;
	private String transactionNo = "";
	private Agent fromAgent;
	private Agent toAgent;
	private BigDecimal amount;
	private BigDecimal inaccount;
	private BigDecimal outaccount;
	private long type = 0;
	private String mark;
	private String typeCatption;
	private Long status;
	private int flag;
	protected List operate;
	protected com.fordays.fdpay.agent.Agent transactioner;
	protected com.fordays.fdpay.agent.Agent reverseTransactioner;
	protected BigDecimal Sum;
	protected long Count = 0;

	public TempTransaction(BigDecimal sum, long count)
	{
		super();
		Sum = sum;
		Count = count;
	}

	public BigDecimal getSum()
	{
		if (this.Sum == null) { return new BigDecimal(0); }
		return Sum;
	}

	public void setSum(BigDecimal sum)
	{
		Sum = sum;
	}

	public long getCount()
	{
		return Count;
	}

	public void setCount(long count)
	{
		Count = count;
	}

	public Long getStatus()
	{
		return status;
	}

	public void setStatus(Long status)
	{
		this.status = status;
	}

	public String getTypeCatption()
	{
		if (this.type == Transaction.TYPE_1)
			return "即时到帐付款";
		else if (this.type == Transaction.TYPE_2)
			return "担保交易付款";
		else if (this.type == Transaction.TYPE_3)
			return "担保交易收款";
		else if (this.type == Transaction.TYPE_4)
			return "即时到帐收款";
		else if (this.type == Transaction.TYPE_5)
			return "批量收款";
		else if (this.type == Transaction.TYPE_6)
			return "批量付款";
		else if (this.type == Transaction.TYPE_20)
			return "网银充值";
		else if (this.type == Transaction.TYPE_22)
			return "线下充值";
		else if (this.type == Transaction.TYPE_30)
			return "提现转账";
		else if (this.type == Transaction.TYPE_40)
			return "申请提现";
		else if (this.type == Transaction.TYPE_44)
			return "系统扣款";
		else if (this.type == Transaction.TYPE_80)
			return "线上支付";// 接口支付
		else if (this.type == Transaction.TYPE_81)
			return "线上退款"; // 接口支付的接口退款
		else if (this.type == Transaction.TYPE_82)
			return "线下退款";// 接口支付的手工退款
		else if (this.type == Transaction.TYPE_70)
			return "实名认证提现";// return "70"; //
		else if (this.type == Transaction.TYPE_69)
			return "实名认证申请";// return "69"; //
		else if (this.type == Transaction.TYPE_9)
			return "借款";// return "9"; //
		else if (this.type == Transaction.TYPE_91)
			return "还款";// return "91"; //
		else if (this.type == Transaction.TYPE_109)
			return "预支";// return "9"; //
		else if (this.type == Transaction.TYPE_191)
			return "报销";// return "91"; //
		else
			return "";
	}

	public String getActionCaption()
	{
		if (transactioner.getId() == this.getFromAgent().getId())
		{
			return "买入";
		}
		else if (transactioner.getId() == this.getToAgent().getId())
		{
			return "卖出";
		}
		else
			return "";
	}

	public String getTypeCaptionStat()
	{
		if (this.getType() == Transaction.TYPE_1)
			return "即时到帐付款";
		else if (this.getType() == Transaction.TYPE_2)
			return "担保交易付款";
		else if (this.getType() == Transaction.TYPE_3)
			return "担保交易收款";
		else if (this.getType() == Transaction.TYPE_4)
			return "即时到帐收款";
		else if (this.getType() == Transaction.TYPE_5)
			return "批量收款";
		else if (this.getType() == Transaction.TYPE_6)
			return "批量付款";
		else if (this.getType() == Transaction.TYPE_20)
			return "网银充值";
		else if (this.getType() == Transaction.TYPE_22)
			return "线下充值";
		else if (this.getType() == Transaction.TYPE_30)
			return "提现";
		else if (this.getType() == Transaction.TYPE_40)
			return "转账";
		else if (this.getType() == Transaction.TYPE_80)
			return "线上支付";// 接口支付
		else if (this.getType() == Transaction.TYPE_81)
			return "线上退款"; // 接口支付的接口退款
		else if (this.getType() == Transaction.TYPE_82)
			return "线下退款";// 接口支付的手工退款
		else if (this.getType() == Transaction.TYPE_70)
			return "实名认证提现";// return "70"; //
		else
			return "即时到账退款";
	}

	public TempTransaction(OrderDetails orderDetails, long id, Date accountDate,
	    String transactionNo, Agent fromAgent, Agent toAgent, BigDecimal amount,
	    BigDecimal inaccount, BigDecimal outaccount, long type, String mark,
	    Long status)
	{
		super();
		this.orderDetails = orderDetails;
		this.id = id;
		this.accountDate = accountDate;
		this.transactionNo = transactionNo;
		this.fromAgent = fromAgent;
		this.toAgent = toAgent;
		this.amount = amount;
		this.inaccount = inaccount;
		this.outaccount = outaccount;
		this.type = type;
		this.mark = mark;
		this.status = status;
	}

	public OrderDetails getOrderDetails()
	{
		return orderDetails;
	}

	public void setOrderDetails(OrderDetails orderDetails)
	{
		this.orderDetails = orderDetails;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public Date getAccountDate()
	{
		return accountDate;
	}

	public void setAccountDate(Date accountDate)
	{
		this.accountDate = accountDate;
	}

	public String getTransactionNo()
	{
		return transactionNo;
	}

	public void setTransactionNo(String transactionNo)
	{
		this.transactionNo = transactionNo;
	}

	public Agent getFromAgent()
	{
		return fromAgent;
	}

	public void setFromAgent(Agent fromAgent)
	{
		this.fromAgent = fromAgent;
	}

	public Agent getToAgent()
	{
		return toAgent;
	}

	public void setToAgent(Agent toAgent)
	{
		this.toAgent = toAgent;
	}

	public BigDecimal getAmount()
	{
		return amount;
	}

	public void setAmount(BigDecimal amount)
	{
		this.amount = amount;
	}

	public BigDecimal getInaccount()
	{
		return inaccount;
	}

	public void setInaccount(BigDecimal inaccount)
	{
		this.inaccount = inaccount;
	}

	public BigDecimal getOutaccount()
	{
		return outaccount;
	}

	public void setOutaccount(BigDecimal outaccount)
	{
		this.outaccount = outaccount;
	}

	public BigDecimal getCurrentBalance()
	{
		if (inaccount == null)
		{
			if (outaccount == null)
				return new BigDecimal("0").setScale(2);
			else
				return outaccount.multiply(new BigDecimal("-1"));
		}
		else
		{
			if (outaccount == null)
				return inaccount;
			else
				return inaccount.subtract(outaccount);

		}

	}

	public long getType()
	{
		return type;
	}

	public void setType(long type)
	{
		this.type = type;
	}

	public String getMark()
	{
		return mark;
	}

	public void setMark(String mark)
	{
		this.mark = mark;
	}

	public com.fordays.fdpay.agent.Agent getTransactioner()
	{
		return transactioner;
	}

	public void setTransactioner(com.fordays.fdpay.agent.Agent transactioner)
	{
		this.transactioner = transactioner;
		if (transactioner.getId() == this.getFromAgent().getId())
		{
			this.flag = 0;
			reverseTransactioner = this.getToAgent();
		}
		else if (transactioner.getId() == this.getToAgent().getId())
		{
			this.flag = 1;
			reverseTransactioner = this.getFromAgent();
		}
	}

	public com.fordays.fdpay.agent.Agent getReverseTransactioner()
	{
		return reverseTransactioner;
	}

	public String getTypeCaption()
	{
		if (this.getType() == Transaction.TYPE_1)
			return "即时到帐付款"; // return "1";
		else if (this.getType() == Transaction.TYPE_2)
			return "担保交易付款";// return "2"; //
		else if (this.getType() == Transaction.TYPE_3)
			return "担保交易收款";// return "3"; //
		else if (this.getType() == Transaction.TYPE_4)
			return "即时到帐收款";// return "4"; //
		else if (this.getType() == Transaction.TYPE_5)
			return "批量收款";// return "5"; //
		else if (this.getType() == Transaction.TYPE_6)
			return "批量付款";// return "6"; //
		else if (this.getType() == Transaction.TYPE_20)
			return "网银充值";// return "20"; //
		else if (this.getType() == Transaction.TYPE_22)
			return "线下充值";// return "20"; //
		else if (this.getType() == Transaction.TYPE_30)
			return "提现";// return "30"; //
		else if (this.getType() == Transaction.TYPE_40)
			return "转账";// return "40"; //
		else if (this.getType() == Transaction.TYPE_70)
			return "实名认证提现";// return "70"; //
		else
			return "即时到账退款";// return "0"; //
	}

	public String getTransactionStatus()
	{
		if (this.getStatus().intValue() == Transaction.STATUS_1)
			return "等待买家付款";
		else if (this.getStatus().intValue() == Transaction.STATUS_2)
			return "买家已付款，等待卖家发货";
		else if (this.getStatus().intValue() == Transaction.STATUS_3)
			return "交易成功";
		else if (this.getStatus().intValue() == Transaction.STATUS_4)
			return "关闭交易";
		else if (this.getStatus().intValue() == Transaction.STATUS_5)
			return "交易失败";
		else if (this.getStatus().intValue() == Transaction.STATUS_6)
			return "卖家已发货,等待买家确认";
		else if (this.getStatus().intValue() == Transaction.STATUS_10)
			return "退款协议等待卖家确认中";
		// else if(this.getStatus().intValue()==Transaction.STATUS_10 ||
		// this.getStatus().intValue()==Transaction.STATUS_7)
		// return "退款协议等待卖家确认中";
		else if (this.getStatus().intValue() == Transaction.STATUS_11)
			return "关闭交易"; // 这里的退款成功在交易管理页面显示关闭交易
		else
			return "";
	}

	public int getFlag()
	{
		return flag;
	}

	public void setFlag(int flag)
	{
		this.flag = flag;
	}

	public void setOperate(List operate)
	{
		this.operate = operate;
	}

}
