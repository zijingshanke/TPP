package com.fordays.fdpay.cooperate;
import com.neza.base.ListActionForm;

public class ActionLogListForm  extends ListActionForm{


	private static final long serialVersionUID = 1L;
	private String content="";
	private String beginDate = "";
	private String endDate = "";
	private String orderNo="";
	
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
