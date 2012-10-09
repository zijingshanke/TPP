package com.fordays.fdpay.base;

import java.util.HashMap;

public class Bank {

	public static final int ICBC = 0;// ��������
	public static HashMap banks=null;
  
  	public static  String getCName(String obj){
  		if(banks==null){
	  		banks = new HashMap();
	  		banks.put("1c", "中国建设银行");
	  		banks.put("2c", "中国农业银行");
	  		banks.put("3c", "中国工商银行");
	  		banks.put("4c", "中国银行");
	  		banks.put("5c", "中国民生银行");
	  		banks.put("6c", "招商银行");
	  		banks.put("7c", "兴业银行");
	  		banks.put("8c", "北京银行");
	  		banks.put("9c", "交通银行");
	  		banks.put("10c", "中国光大银行");
	  		banks.put("11c", "中信银行");
	  		banks.put("12c", "广东发展银行");
	  		banks.put("13c", "上海浦东发展银行");
	  		banks.put("14c", "深圳发展银行");	  
	  		
	  		banks.put("1s", "CCB");
	  		banks.put("2s", "ABC");
	  		banks.put("3s", "ICBC");
	  		banks.put("4s", "BOC");
	  		banks.put("5s", "CMBC");
	  		banks.put("6s", "CMB");
	  		banks.put("7s", "CIB");
	  		banks.put("8s", "BOB");
	  		banks.put("9s", "BCM");
	  		banks.put("10c", "CEB");
	  		banks.put("11c", "CITIC");
	  		banks.put("12c", "GDB");
	  		banks.put("13c", "SPDB");
	  		banks.put("14c", "SDB");	
	  		
	  		banks.put("CCB", "中国建设银行");
	  		banks.put("ABC", "中国农业银行");
	  		banks.put("ICBC", "中国工商银行");
	  		banks.put("BOC", "中国银行");
	  		banks.put("CMBC", "中国民生银行");
	  		banks.put("CMB", "招商银行");
	  		banks.put("CIB", "兴业银行");
	  		banks.put("BOB", "北京银行");
	  		banks.put("BCM", "交通银行");
	  		banks.put("CEB", "中国光大银行");
	  		banks.put("CITIC", "中信银行");
	  		banks.put("GDB", "广东发展银行");
	  		banks.put("SPDB", "上海浦东发展银行");
	  		banks.put("SDB", "深圳发展银行");	
  		}
  		return Bank.banks.get(obj).toString();
  	}
	
}
