package com.fordays.fdpay.transaction;


import com.fordays.fdpay.transaction._entity._TransactionBalance;

public class TransactionBalance extends _TransactionBalance{
  	private static final long serialVersionUID = 1L;
  	
  	public static final long STATUS_0 = 0;//默认
  	public static final long STATUS_1 = 1;
  	public static final long STATUS_2 = 2;
  	public Transaction transaction=new Transaction();
	public Transaction getTransaction() {
		return transaction;
	}
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

  	
}
