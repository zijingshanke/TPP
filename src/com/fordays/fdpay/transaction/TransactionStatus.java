package com.fordays.fdpay.transaction;

import com.fordays.fdpay.transaction._entity._TransactionStatus;

public class TransactionStatus extends _TransactionStatus {
	private static final long serialVersionUID = 1L;

	public String getStatusCaption() {
		if (this.status.intValue() == 1)
			return "启用";
		else
			return "停用";
	}
}
