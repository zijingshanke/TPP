package com.fordays.fdpay.agent;


import java.math.BigDecimal;

import com.fordays.fdpay.agent._entity._AgentBalancePoint;

public class AgentBalancePoint extends _AgentBalancePoint{
  	private static final long serialVersionUID = 1L;
  	
  	private BigDecimal balanceAndCheckAmount =new BigDecimal(0);

	public BigDecimal getBalanceAndCheckAmount() {
		
		if(this.allowBalance==null ){
			this.allowBalance=new BigDecimal(0);
		}
		if(this.creditBalance==null ){
			this.creditBalance=new BigDecimal(0);
		}
		if(this.notAllowBalance==null ){
			this.notAllowBalance=new BigDecimal(0);
		}
		
		return this.allowBalance.add(this.notAllowBalance).add(this.creditBalance);
	}
  	
	
}
