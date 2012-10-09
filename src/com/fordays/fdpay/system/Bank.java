package com.fordays.fdpay.system;


import java.util.HashMap;

import com.fordays.fdpay.system._entity._Bank;

public class Bank extends _Bank{
  	private static final long serialVersionUID = 1L;
  	public HashMap banks;
  	public Bank(){
  		banks = new HashMap();
  		banks.put(1, "中国建设银行");
  		banks.put(2, "中国农业银行");
  		banks.put(3, "中国工商银行");
  		banks.put(4, "中国银行");
  		banks.put(5, "中国民生银行");
  		banks.put(6, "招商银行");
  		banks.put(7, "兴业银行");
  		banks.put(8, "北京银行");
  		banks.put(9, "交通银行");
  		banks.put(10, "中国光大银行");
  		banks.put(11, "中信银行");
  		banks.put(12, "广东发展银行");
  		banks.put(13, "上海浦东发展银行");
  		banks.put(14, "深圳发展银行");	  		
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
}
