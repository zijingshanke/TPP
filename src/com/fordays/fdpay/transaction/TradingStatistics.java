package com.fordays.fdpay.transaction;

import java.math.BigDecimal;


public class TradingStatistics{
  	private static final long serialVersionUID = 1L;
  	private Long article;
  	private BigDecimal money;
  	private Long status;
  	private String bank;
	public Long getArticle() {
		return article;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public Long getStatus() {
		return status;
	}
	public String getBank() {
		return bank;
	}
	
	public TradingStatistics(Long article,BigDecimal money,Long status,String bank){
		super();
		this.article=article;
		this.money=money;
		this.status=status;
		this.bank=bank;
	}
}
