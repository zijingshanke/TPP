package com.fordays.fdpay.agent;



import com.fordays.fdpay.agent._entity._AgentCoterie;
public class AgentCoterie extends _AgentCoterie{
  	private static final long serialVersionUID = 1L;
 
  	private String agentEmail;
  	private String coterieName;
  	private String tempStatus="";
  	public static long STATUS_YES=1;
  	public static long STATUS_NO=0;
  	public static long  TRANSACTIONSCOPE_0=0;// 交易限制(内买0)
  	public static long  TRANSACTIONSCOPE_1=1;// 交易限制(外买1)
  	public static long  REPAYMENT_TYPE_2=2;//还款方式(2--多笔还)
  	public static long  REPAYMENT_TYPE_1=1;//还款方式(1--逐笔还)
  	
  	
  	public String getTempStatus(){
  		tempStatus="";
  		if(status==STATUS_NO){
  			tempStatus="不可用";
  		}else if(status==STATUS_YES){
  			tempStatus="可用";
  		}
  		return tempStatus;
  	}
	public String getAgentEmail() {
		return agentEmail;
	}
	public void setAgentEmail(String agentEmail) {
		this.agentEmail = agentEmail;
	}
	public String getCoterieName() {
		return coterieName;
	}
	public void setCoterieName(String coterieName) {
		this.coterieName = coterieName;
	}
		public com.fordays.fdpay.agent.Agent getAgent()
			{
				return agent;
			}
		public void setAgent(com.fordays.fdpay.agent.Agent agent)
			{
				this.agent = agent;
			}

}
