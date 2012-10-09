package com.fordays.fdpay.agent;

import java.math.BigDecimal;

public class AgentException {
	private long id;
	private String email;
	private String name;
	private BigDecimal allowBalance;
	private BigDecimal notallowBalance;
	private BigDecimal creditBalance;

	
	public AgentException(long id, String email, BigDecimal allowBalance) {
		super();
		this.id = id;
		this.email = email;
		this.allowBalance = allowBalance;
	}
	public AgentException() {
		
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public BigDecimal getAllowBalance() {
		return allowBalance;
	}
	public void setAllowBalance(BigDecimal allowBalance) {
		this.allowBalance = allowBalance;
	}
	public BigDecimal getNotallowBalance() {
		return notallowBalance;
	}
	public void setNotallowBalance(BigDecimal notallowBalance) {
		this.notallowBalance = notallowBalance;
	}
	public BigDecimal getCreditBalance() {
		return creditBalance;
	}
	public void setCreditBalance(BigDecimal creditBalance) {
		this.creditBalance = creditBalance;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
