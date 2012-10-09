package com.fordays.fdpay.transaction;


import com.fordays.fdpay.transaction._entity._Debit;

public class Debit extends _Debit{
  	private static final long serialVersionUID = 1L;
  	public static final long DEBIT_STATUS_0 = 0;// 申请预支
  	public static final long DEBIT_STATUS_1 = 1;// 审核预支
  	public static final long DEBIT_STATUS_2 = 2;// 批准预支
  	public static final long DEBIT_STATUS_3 = 3;// 预支成功
  	public static final long DEBIT_STATUS_4 = 4;// 撤销预支
    protected com.fordays.fdpay.agent.Agent fromAgent;
    private String note1="";
    private String agentLoginName="";
    private String expenseStatus="";
		public String getExpenseStatus() {
		return expenseStatus;
	}
	public void setExpenseStatus(String expenseStatus) {
		this.expenseStatus = expenseStatus;
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
  		
  		if(status==DEBIT_STATUS_0){
  			return "待审核";
  		}
  		if(status==DEBIT_STATUS_1){
  			return "待批准";
  		}
  		if(status==DEBIT_STATUS_2){
  			return "待转账";
  		}
  		if(status==DEBIT_STATUS_3){
  			return "已转账";
  		}
  		if(status==DEBIT_STATUS_4){
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
  	
}
