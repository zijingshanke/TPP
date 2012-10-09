package com.fordays.fdpay.system;


import com.fordays.fdpay.system._entity._TaskTimestamp;

public class TaskTimestamp extends _TaskTimestamp{
  	private static final long serialVersionUID = 1L;

	public static long  type_1=1; //激活
	public static long  type_2=2; //找回密码
	public static long  type_3=3; //修改邮箱
	public static long  type_4=4; //连续登录失败

	public static long  type_5=5; //...
	public static long  type_6=6; //实名认证
	public static long type_7=7; //实名认证失败任务
	public static long type_8=8; //手机支付短信验证任务
	public static long  type_9=9; //证书登录
	
	
	public static long status_1=1; //开启
	public static long status_0=0;//关闭
	public static long count_3=3;//3次机会
}
