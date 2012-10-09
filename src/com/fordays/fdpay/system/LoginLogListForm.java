package com.fordays.fdpay.system;

import com.neza.base.ListActionForm;

public class LoginLogListForm  extends ListActionForm{


	private static final long serialVersionUID = 1L;

	private String fromDate="";
	private String toDate="";
	private String userNo="";
	private long locate =0;
	public long getLocate() {
		return locate;
	}
	public void setLocate(long locate) {
		this.locate = locate;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getFromDate()
		{
			return fromDate;
		}
	public void setFromDate(String fromDate)
		{
			this.fromDate = fromDate;
		}
	public String getToDate()
		{
			return toDate;
		}
	public void setToDate(String toDate)
		{
			this.toDate = toDate;
		}
	public static long getSerialVersionUID() {
		
		return serialVersionUID;
	}
}
