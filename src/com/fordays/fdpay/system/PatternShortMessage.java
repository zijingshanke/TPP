package com.fordays.fdpay.system;

import com.fordays.fdpay.system._entity._PatternShortMessage;

public class PatternShortMessage extends _PatternShortMessage {
	private static final long serialVersionUID = 1L;

	public String getStatusCaption() {
		if (this.status.intValue() == 1)
			return "启用";
		else
			return "停用";
	}
}
