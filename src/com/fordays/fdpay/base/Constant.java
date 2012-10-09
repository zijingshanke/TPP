package com.fordays.fdpay.base;

import java.util.ArrayList;

import com.fordays.fdpay.agent.Agent;

public class Constant
{
	public static Agent platAgent=null;
	public static Agent platChargeAgent=null;
	public static Agent platRateAgent=null;	
	public static ArrayList<String> url=null;

	public void setUrl(ArrayList<String> url)
  {
		Constant.url = url;
  }



}