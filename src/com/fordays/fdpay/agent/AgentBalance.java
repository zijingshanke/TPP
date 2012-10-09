package com.fordays.fdpay.agent;

import java.math.BigDecimal;
import java.sql.Timestamp;
 

import com.fordays.fdpay.agent._entity._AgentBalance;

public class AgentBalance extends _AgentBalance
{   
	 public AgentBalance(){
		 
	 }
	 public AgentBalance(long id, BigDecimal allowBalance,
       BigDecimal notAllowBalance, BigDecimal creditBalance)
   {
	    super();
	    this.id = id;
	    this.allowBalance = allowBalance;
	    this.notAllowBalance = notAllowBalance;
	    this.creditBalance = creditBalance;
   }
	public AgentBalance(BigDecimal allowBalance,
		    BigDecimal creditBalance, BigDecimal notAllowBalance,
		    Timestamp balanceDate, Agent agent)
		{
			super();
			this.allowBalance = allowBalance;
			this.creditBalance = creditBalance;
			this.notAllowBalance = notAllowBalance;
			this.balanceDate = balanceDate;
			this.agent = agent;
		}
	 
}
