package com.fordays.fdpay.transaction;


import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.fordays.fdpay.transaction._entity._Charge;

public class Charge extends _Charge{
  	private static final long serialVersionUID = 1L;
  	private com.fordays.fdpay.user.SysUser sysUser1;
  	private com.fordays.fdpay.user.SysUser sysUser0;
    private String statu;
    private String agentLoginName;
    private String note1;
   
	
	public static final long CHARGE_STATUS_0 = 0;// 申请充值
	public static final long CHARGE_STATUS_1 = 1;// 核准通过，充值成功
	public static final long CHARGE_STATUS_2 = 2;// 充值失败
	public static final long CHARGE_STATUS_3 = 3;// 已批准，待核准
	public static final long CHARGE_STATUS_5 = 5;// 以撤销
	public static final long CHARGE_STATUS_6 = 6;// 客户放弃支付
	public static final long CHARGE_STATUS_7 = 7;// 可疑交易
	public static final long CHARGE_STATUS_8 = 8;// 等待授权、审核（部分企业网银充值订单专用）
	
	public static final String CHARGE_TYPE_SELF = "0,0";// 给自己充值
	public static final String CHARGE_TYPE_TRANSACTION = "1,0";// 登录到钱门付款
	public static final String CHARGE_TYPE_NOACCOUNT = "2,0";// 无钱门帐号，外部订单充值付款
	public static final String CHARGE_TYPE_DIRECTPAYMENT = "3,0";// 有钱门帐号,外部订单充值付款
	public static final String CHARGE_TYPE_OTHER = "4,0";// 给别人充值
	public static final String CHARGE_TYPE_BACKGROUND = "5,0";// 可用余额后台线下充值
	public static final String CHARGE_TYPE_GUARANTEE = "6,0";//担保
	public static final String CHARGE_TYPE_CREDIT = "7,0";//信用金额后台线下充值
	
		public String getNote1() {
		return note1;
	}
		
		public String getTypeCaption(){
			String typeCaption="";
			if(type.equals(CHARGE_TYPE_SELF)){
				typeCaption=CHARGE_TYPE_SELF+"给自己充值";
			}
			if(type.equals(CHARGE_TYPE_TRANSACTION)){
				typeCaption=CHARGE_TYPE_TRANSACTION+"登录到钱门付款";
			}
			if(type.equals(CHARGE_TYPE_NOACCOUNT)){
				typeCaption=CHARGE_TYPE_NOACCOUNT+"无钱门帐号，外部订单充值付款";
			}
			if(type.equals(CHARGE_TYPE_DIRECTPAYMENT)){
				typeCaption=CHARGE_TYPE_DIRECTPAYMENT+"有钱门帐号,外部订单充值付款";
			}
			if(type.equals(CHARGE_TYPE_OTHER)){
				typeCaption=CHARGE_TYPE_OTHER+" 给别人充值";
			}
			if(type.equals(CHARGE_TYPE_BACKGROUND)){
				typeCaption=CHARGE_TYPE_BACKGROUND+" 可用余额后台线下充值";
			}
			if(type.equals(CHARGE_TYPE_GUARANTEE)){
				typeCaption=CHARGE_TYPE_GUARANTEE+" 担保";
			}
			if(type.equals(CHARGE_TYPE_CREDIT)){
				typeCaption=CHARGE_TYPE_CREDIT+" 信用金额后台线下充值";
			}
			return typeCaption;
		}
		
	public void reset(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) 
	{
		this.agentLoginName = "";
		this.note ="";
		this.amount = new BigDecimal(0);
	}

	public void setNote1(String note1) {
		this.note1 = note1;
	}

		public String getAgentLoginName() {
		return agentLoginName;
	}

	public void setAgentLoginName(String agentLoginName) {
		this.agentLoginName = agentLoginName;
	}
	//线下充值
		public String getStatusByOther() {
			if(status==CHARGE_STATUS_0){
				statu="待批准";
			}else if(status==CHARGE_STATUS_1){
				statu="已核准";
			}
			else if(status==CHARGE_STATUS_3){
				statu="待核准";
			}
			else if(status==CHARGE_STATUS_5){
				statu="已撤销";
			}
		return statu;
	}
		//网银充值时
		public String getStatusByBank() {
			long currenTime = new Date().getTime();
			long chargeTime = chargeDate.getTime();
			boolean isTimeOut = false;
			if (currenTime - chargeTime >= 1000 * 60 * 60 * 24) {
				isTimeOut = true;
			}
			if(status==CHARGE_STATUS_0){
				statu="申请中";
				if (isTimeOut) {
					statu = "支付超时";
				}
			}else if(status==CHARGE_STATUS_1){
				statu="成功";
			}else if(status==CHARGE_STATUS_2){
				statu="失败";
			}else if(status==CHARGE_STATUS_6){
				statu="放弃支付";
			}
			else if(status==CHARGE_STATUS_7){
				statu="可疑交易";
			}
		return statu;
	}
		
		public String getStatusByAll() {
			if(status==CHARGE_STATUS_0||status==CHARGE_STATUS_3||status==CHARGE_STATUS_6||status==CHARGE_STATUS_7||status==CHARGE_STATUS_8){
				statu="处理中";
			}else if(status==CHARGE_STATUS_1){
				statu="成功";
			}
			else if(status==CHARGE_STATUS_2||status==CHARGE_STATUS_5){
				statu="失败";
			}
			
			return statu;
		}
		public String getNoteCaption() {
			
			if(note!=null)
			{
				return note.replace("\\n","<br/>");
			}
			else
				return "";
		}
		public com.fordays.fdpay.user.SysUser getSysUser1()
    	{
    		return sysUser1;
    	}
		public void setSysUser1(com.fordays.fdpay.user.SysUser sysUser1)
    	{
    		this.sysUser1 = sysUser1;
    	}
		 public String getBankTo() {
			 String bk="";
			 if(bank!=null){
				 if(bank.equals("OTHER")){
					 bk="线下充值";
				 }
				 if (bank.equals("ICBC"))
					{
						bk = "工商银行";
					}
					if (bank.equals("CCB"))
					{
						bk = "建设银行";
					}
					if (bank.equals("ABC"))
					{
						bk = "农业银行";
					}
					if (bank.equals("CMBC"))
					{
						bk = "民生银行";
					}
					if (bank.equals("BCM"))
					{
						bk = "交通银行";
					}
					if (bank.equals("BOC"))
					{
						bk = "中国银行";
					}
					if (bank.equals("CMB"))
					{
						bk = "招商银行";
					}
					if (bank.equals("CIB"))
					{
						bk = "兴业银行";
					}
					if (bank.equals("BOB"))
					{
						bk = "北京银行";
					}
					if (bank.equals("CEB"))
					{
						bk = "光大银行";
					}
					if (bank.equals("CITIC"))
					{
						bk = "中信银行";
					}
					if (bank.equals("GDB"))
					{
						bk = "广发银行";
					}
					if (bank.equals("SPDB"))
					{
						bk = "浦发银行";
					}
					if (bank.equals("SDB"))
					{
						bk = "深发银行";
					}
					if (bank.equals("CREDIT"))
					{
						bk = "信用金额";
					}
			 }
			 return bk;
		 }
		 
		public com.fordays.fdpay.user.SysUser getSysUser0() {
			return sysUser0;
		}

		public void setSysUser0(com.fordays.fdpay.user.SysUser sysUser0) {
			this.sysUser0 = sysUser0;
		}
}
