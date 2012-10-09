package com.fordays.fdpay.system;


import com.fordays.fdpay.system._entity._SysConfig;

public class SysConfig extends _SysConfig{
  	private static final long serialVersionUID = 1L;
  	private String active1;
  	public static long ACTIVE_YES=1;
  	public static long ACTIVE_NO=0;
	public String getActive1() {
		if(this.getActive()==ACTIVE_YES)
		{
			return "已激活";
		}else
		if(this.getActive()==ACTIVE_NO)
		{
			return "未激活";
		}else
			return "";
		
	}
	public void setActive1(String active1) {
		this.active1 = active1;
		if(this.active1.equals("未激活"))
			this.setActive(ACTIVE_NO);
		if(this.active1.equals("已激活"))
			this.setActive(ACTIVE_YES);
	}
	
}
