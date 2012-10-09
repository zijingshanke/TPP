package com.fordays.fdpay.transaction;


import com.fordays.fdpay.transaction._entity._Expense;

public class Expense extends _Expense{
  	private static final long serialVersionUID = 1L;
  	public static final long STATUS_0 = 0;// 申请报销
  	public static final long STATUS_1 = 1;// 审核报销
  	public static final long STATUS_2 = 2;// 批准报销
  	public static final long STATUS_3 = 3;// 报销成功
  	public static final long STATUS_4 = 4;// 撤销报销
    protected com.fordays.fdpay.agent.Agent fromAgent;
    private String note1="";
    private String agentLoginName="";
    private Long status;
    public Expense(){}
	public Expense(Long status) {
		super();
		this.status = status;
	}
		public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
		public String getNote1() {
		return note1;
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
		public com.fordays.fdpay.agent.Agent getFromAgent()
    {
    	return fromAgent;
    }
		public void setFromAgent(com.fordays.fdpay.agent.Agent fromAgent)
    {
    	this.fromAgent = fromAgent;
    }
		public String getStatusTo(){
	  		
	  		if(status.intValue()==STATUS_0){
	  			return "待审核";
	  		}
	  		if(status.intValue()==STATUS_1){
	  			return "待批准";
	  		}
	  		if(status.intValue()==STATUS_2){
	  			return "待转账";
	  		}
	  		if(status.intValue()==STATUS_3){
	  			return "已转账";
	  		}
	  		if(status.intValue()==STATUS_4){
	  			return "已撤销";
	  		}
	  		return "";

		}
		
		public String getNoteCaption()
		{

			if (note != null && !note.equals(""))
			{
				return note.replace("\\n", "<br/>");
			}
			else
				return "";
		}
}
