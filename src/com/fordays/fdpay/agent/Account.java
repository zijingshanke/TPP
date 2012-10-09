package com.fordays.fdpay.agent;


import com.fordays.fdpay.agent._entity._Account;

public class Account extends _Account{
  	private static final long serialVersionUID = 1L;

  	public String getFlgBindStatus(){
  		if(this.bindStatus.intValue()==2){
  			return "是";
  		}else{
  			return "否";
  		}
  	}
}
