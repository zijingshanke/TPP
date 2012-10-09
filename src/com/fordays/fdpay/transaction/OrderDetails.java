package com.fordays.fdpay.transaction;

import com.fordays.fdpay.transaction._entity._OrderDetails;

public class OrderDetails extends _OrderDetails
{
	public  static final long STATUS_0=0; //表示没申请过报销
  	public  static final long STATUS_1=1; //表示已经申请过报销
	public static final long STATUS_6 = 6; // 借款还款时用到的状态，借款时值为6，
	public static final long STATUS_7 = 7; // 借款还款时用到的状态，部份还款时值为7，
	public static final long STATUS_8 = 8; // 借款还款时用到的状态，全部还完款时值为8，
	public  static final long ORDER_DETAILS_TYPE_1=1; //现金到现金
  	public  static final long ORDER_DETAILS_TYPE_2=2; //授信金额到授信金额
  	public  static final long ORDER_DETAILS_TYPE_3=3; //现金到授信金额
	public  static final long ORDER_DETAILS_TYPE_4=4; //授信金额到现金
	public  static final long ORDER_DETAILS_TYPE_5=5; //现金到冻结
	public  static final long ORDER_DETAILS_TYPE_6=6; //冻结到现金
	private static final long serialVersionUID = 1L;
	private int payedNum = 0;
	private String buyerEmail;

	public int getPayedNum()
	{
		return payedNum;
	}

	public void setPayedNum(int payedNum)
	{
		this.payedNum = payedNum;
	}

	public String getBuyerEmail()
	{
		return buyerEmail;
	}

	public void setBuyerEmail(String buyerEmail)
	{
		this.buyerEmail = buyerEmail;
	}

}
