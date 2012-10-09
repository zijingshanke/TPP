package com.fordays.fdpay.system;

import com.neza.base.ListActionForm;

public class SysLogListForm extends ListActionForm {
	private static final long serialVersionUID = 1L;
	private SysLog syslog = new SysLog();
	private String fromDate = "";
	private String toDate = "";
	private String userNo = "";
	private long locate;

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

	public SysLog getSyslog() {

		return syslog;
	}

	public void setSyslog(SysLog syslog) {

		this.syslog = syslog;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
}
