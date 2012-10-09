package com.fordays.fdpay.transaction;

import java.math.BigDecimal;

import com.fordays.fdpay.agent.Agent;
import com.fordays.fdpay.transaction._entity._Transaction;

public class Transaction extends _Transaction
{
	private static final long serialVersionUID = 1L;

	public static final long TYPE_1 = 1;// 即时到帐付款

	public static final long TYPE_2 = 2;// 担保交易付款

	public static final long TYPE_3 = 3;// 担保交易收款

	public static final long TYPE_4 = 4;// 即时到帐收款

	public static final long TYPE_5 = 5;// 批量收款

	public static final long TYPE_6 = 6;// 批量付款

	public static final long TYPE_20 = 20;// 网银充值
	public static final long TYPE_22 = 22;// 线下充值
	public static final long TYPE_23 = 23;// 线下信用金额充值

	public static final long TYPE_30 = 30;// 提现转账
	
	public static final long TYPE_40 = 40;// 申请提现 
	public static final long TYPE_41 = 41;// 撤销提现 
	
	public static final long TYPE_44 = 44;// 系统扣款

	public static final long TYPE_69 = 69;// 实名认证申请
	public static final long TYPE_70 = 70;// 实名认证提现
	
	public static final long TYPE_99 = 99;//  冻结到可用
	public static final long TYPE_100 = 100;// 可用到冻结
	public static final long TYPE_97 = 97;//  系统解冻
	public static final long TYPE_98 = 98;//  系统冻结

	public static final long TYPE_80 = 80;// 接口支付 ,加多个从其他系统支付成功的状态,目的为了在页面添加个退款按钮
	public static final long TYPE_81 = 81;// 接口支付，接口退款
	public static final long TYPE_82 = 82;// 接口支付，前台手工退款
	
	public static final long TYPE_180 = 180;// 信用支付
	public static final long TYPE_181 = 181;// 信用支付，接口退款
	public static final long TYPE_182 = 182;// 信用支付，前台手工退款
	
	public static final long TYPE_130 = 130;// 担保支付
	public static final long TYPE_131 = 131;// 担保支付，接口退款
	public static final long TYPE_132 = 132;// 担保支付，前台手工退款

	public static final long TYPE_9 = 9;// 借款
	public static final long TYPE_91 = 91;// 还款
	
	public static final long TYPE_109 = 109;// 借款(预支)
	public static final long TYPE_191 = 191;// 报销
	
	
	public static final long TYPE_101 = 101;// 授信支付
	public static final long TYPE_102 = 102;// 授信还款，前台手工还款
	public static final long TYPE_103 = 103;// 授信还款，接口还款

	public static final long TYPE_200 = 200;// 手机网银充值手续费

	public static final long STATUS_1 = 1;// 等待买家付款

	public static final long STATUS_2 = 2;// 等待卖家发货

	public static final long STATUS_3 = 3;// 交易成功

	public static final long STATUS_4 = 4;// 关闭交易

	public static final long STATUS_5 = 5;// 交易失败

	public static final long STATUS_6 = 6;// 卖家已发货,等待买家确认
	
	public static final long STATUS_10 = 10;// 退款协议等待卖家确认中

	public static final long STATUS_11 = 11;// 退款成功
	

	
	
	protected com.fordays.fdpay.agent.Agent fromAgent;
	protected com.fordays.fdpay.agent.Agent toAgent;
	protected com.fordays.fdpay.agent.Agent transactioner;
	protected com.fordays.fdpay.agent.Agent reverseTransactioner;
	protected String actionCaption;
	protected String typeCaption;
	protected String transactionStatus;

	public com.fordays.fdpay.transaction.OrderDetails getOrderDetails()
	{
		if (this.orderDetails.getShopPrice() == null)
		{
			this.orderDetails.setShopPrice(new BigDecimal(0.00));
		}
		if (this.orderDetails.getPaymentPrice() == null)
		{
			this.orderDetails.setPaymentPrice(new BigDecimal(0.00));
		}
		return this.orderDetails;
	}

	public com.fordays.fdpay.agent.Agent getFromAgent()
	{
		return fromAgent;
	}

	public void setFromAgent(com.fordays.fdpay.agent.Agent fromAgent)
	{
		this.fromAgent = fromAgent;
	}

	public com.fordays.fdpay.agent.Agent getToAgent()
	{
		return toAgent;
	}

	public void setToAgent(com.fordays.fdpay.agent.Agent toAgent)
	{
		this.toAgent = toAgent;
	}

	public com.fordays.fdpay.agent.Agent getTransactioner()
	{
		return transactioner;
	}

	public void setTransactioner(com.fordays.fdpay.agent.Agent transactioner)
	{

		if (transactioner.getId() == this.getFromAgent().getId())
		{
			reverseTransactioner = this.getToAgent();
		}
		else if (transactioner.getId() == this.getToAgent().getId())
		{
			reverseTransactioner = this.getFromAgent();
		}
		else
		{

		}
	}

	public com.fordays.fdpay.agent.Agent getReverseTransactioner()
	{
		return reverseTransactioner;
	}

	public String getTypeCaption()
	{
		if (this.getType().intValue() == Transaction.TYPE_1)
			return "即时到帐付款";
		else if (this.getType().intValue() == Transaction.TYPE_2)
			return "担保交易付款";
		else if (this.getType().intValue() == Transaction.TYPE_3)
			return "担保交易收款";
		else if (this.getType().intValue() == Transaction.TYPE_4)
			return "即时到帐收款";
		else if (this.getType().intValue() == Transaction.TYPE_5)
			return "批量收款";
		else if (this.getType().intValue() == Transaction.TYPE_6)
			return "批量付款";
		else if (this.getType().intValue() == Transaction.TYPE_20)
			return "网银充值";
		else if (this.getType().intValue() == Transaction.TYPE_22)
			return "线下充值";
		else if (this.getType().intValue() == Transaction.TYPE_23)
			return "线下信用金额充值";
		else if (this.getType().intValue() == Transaction.TYPE_30)
			return "提现转账";
		else if (this.getType().intValue() == Transaction.TYPE_40)
			return "申请提现";
		else if (this.getType().intValue() == Transaction.TYPE_41)
			return "撤销提现";
		else if (this.getType().intValue() == Transaction.TYPE_44)
			return "系统扣款";
		else if (this.getType().intValue() == Transaction.TYPE_80)
			return "线上支付";//接口支付
		else if (this.getType().intValue() == Transaction.TYPE_81)
			return "线上退款"; //接口支付的接口退款
		else if (this.getType().intValue() == Transaction.TYPE_82)
			return "线下退款";//接口支付的手工退款
		else if (this.getType().intValue() == Transaction.TYPE_180)
			return "信用支付";//信用支付
		else if (this.getType().intValue() == Transaction.TYPE_181)
			return "信用线上退款";//信用线上退款
		else if (this.getType().intValue() == Transaction.TYPE_182)
			return "信用线下退款";//信用线下退款
		else if(this.getType().intValue()==Transaction.TYPE_70)
				return "实名认证提现";//return "70"; //
		else if(this.getType().intValue()==Transaction.TYPE_69)
			return "实名认证申请";//return "69"; //
		else if(this.getType().intValue()==Transaction.TYPE_9)
			return "借款";//return "9"; //
		else if(this.getType().intValue()==Transaction.TYPE_91)
			return "还款";//return "91"; //
		else if(this.getType().intValue()==Transaction.TYPE_101)
			return "授信支付";//return "101"; //
		else if(this.getType().intValue()==Transaction.TYPE_109)
			return "预支";//return "109"; //
		else if(this.getType().intValue()==Transaction.TYPE_191)
			return "报销";//return "191"; //
		else if(this.getType().intValue()==Transaction.TYPE_130)
			return "担保支付";//return "130"; //
		else if(this.getType().intValue()==Transaction.TYPE_131)
			return "担保线上退款";//return "131"; //
		else if(this.getType().intValue()==Transaction.TYPE_132)
			return "担保线下退款";//return "132"; //
		else if(this.getType().intValue()==Transaction.TYPE_200)
			return "手机网银充值手续费";//return "132"; //
		else if(this.getType().intValue()==Transaction.TYPE_100)
			return "可用到冻结";//return "100"; //
		else if(this.getType().intValue()==Transaction.TYPE_99)
			return "冻结到可用";//return "99"; //
		else if(this.getType().intValue()==Transaction.TYPE_97)
			return "系统解冻";//return "97"; //
		else if(this.getType().intValue()==Transaction.TYPE_98)
			return "系统冻结";//return "98"; //
		else
			return "即时到账退款";
	}
	
	public static String getTypeCaption(int type)
	{
		if (type == Transaction.TYPE_1)
			return "即时到帐付款";
		else if (type == Transaction.TYPE_2)
			return "担保交易付款";
		else if (type == Transaction.TYPE_3)
			return "担保交易收款";
		else if (type == Transaction.TYPE_4)
			return "即时到帐收款";
		else if (type == Transaction.TYPE_5)
			return "批量收款";
		else if (type == Transaction.TYPE_6)
			return "批量付款";
		else if (type == Transaction.TYPE_20)
			return "网银充值";
		else if (type == Transaction.TYPE_22)
			return "线下充值";
		else if (type == Transaction.TYPE_23)
			return "线下信用金额充值";
		else if (type == Transaction.TYPE_30)
			return "提现转账";
		else if (type == Transaction.TYPE_40)
			return "申请提现";
		else if (type == Transaction.TYPE_41)
			return "撤销提现";
		else if (type == Transaction.TYPE_44)
			return "系统扣款";
		else if (type == Transaction.TYPE_80)
			return "线上支付";//接口支付
		else if (type == Transaction.TYPE_81)
			return "线上退款"; //接口支付的接口退款
		else if (type == Transaction.TYPE_82)
			return "线下退款";//接口支付的手工退款
		else if (type == Transaction.TYPE_180)
			return "信用支付";//信用支付
		else if (type == Transaction.TYPE_181)
			return "信用线上退款";//信用线上退款
		else if (type == Transaction.TYPE_182)
			return "信用线下退款";//信用线下退款
		else if(type==Transaction.TYPE_70)
				return "实名认证提现";//return "70"; //
		else if(type==Transaction.TYPE_69)
			return "实名认证申请";//return "69"; //
		else if(type==Transaction.TYPE_9)
			return "借款";//return "9"; //
		else if(type==Transaction.TYPE_91)
			return "还款";//return "91"; //
		else if(type==Transaction.TYPE_101)
			return "授信支付";//return "101"; //
		else if(type==Transaction.TYPE_102)
			return "授信线下还款";//return "102"; 
		else if(type==Transaction.TYPE_103)
			return "授信线上还款";//return "103";
		else if(type==Transaction.TYPE_109)
			return "预支";//return "109"; //
		else if(type==Transaction.TYPE_191)
			return "报销";//return "191"; //
		else if(type==Transaction.TYPE_130)
			return "担保支付";//return "130"; //
		else if(type==Transaction.TYPE_131)
			return "担保线上退款";//return "131"; //
		else if(type==Transaction.TYPE_132)
			return "担保线下退款";//return "132"; //
		else if(type==Transaction.TYPE_200)
			return "手机网银充值手续费";//return "200"; //
		else if(type==Transaction.TYPE_100)
			return "可用到冻结";//return "100"; //
		else if(type==Transaction.TYPE_99)
			return "冻结到可用";//return "99"; //
		else if(type==Transaction.TYPE_97)
			return "系统解冻";//return "97"; //
		else if(type==Transaction.TYPE_98)
			return "系统冻结";//return "98"; //
		else
			return "即时到账退款";
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
		//else if (this.getStatus().intValue() == Transaction.STATUS_10)
		//	return "退款协议等待卖家确认中";
		// else if(this.getStatus().intValue()==Transaction.STATUS_10 ||
		// this.getStatus().intValue()==Transaction.STATUS_7)
		// return "退款协议等待卖家确认中";
		else if (this.getStatus().intValue() == Transaction.STATUS_11)
			return "退款成功"; // 这里的退款成功在交易管理页面显示关闭交易
		else
			return "";
	}

	public static long getSerialVersionUID()
	{
		return serialVersionUID;
	}

	public void setReverseTransactioner(
	    com.fordays.fdpay.agent.Agent reverseTransactioner)
	{
		this.reverseTransactioner = reverseTransactioner;
	}

	/*
	 * public void setActionCaption(String actionCaption) { this.actionCaption =
	 * actionCaption; }
	 */

	public void setTypeCaption(String typeCaption)
	{
		this.typeCaption = typeCaption;
	}

	public String getActionCaption()
	{
		Agent agent = this.getFromAgent();

		try
		{
			if (transactioner.getId() == this.getFromAgent().getId())
			{

				this.actionCaption = "买入";
			}
			else if (transactioner.getId() == this.getToAgent().getId())
			{

				this.actionCaption = "卖出";
			}
		}
		catch (Exception e)
		{
			// System.out.println(e.getMessage());
			this.actionCaption = "";
		}

		return this.actionCaption;
	}

	public void setActionCaption(String actionCaption)
	{
		this.actionCaption = actionCaption;
	}

}
