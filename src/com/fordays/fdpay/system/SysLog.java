package com.fordays.fdpay.system;

import com.fordays.fdpay.system._entity._SysLog;

public class SysLog extends _SysLog {
	private static final long serialVersionUID = 1L;
	public static long TYPE_LOGIN = 1;
	public static long TYPE_TRANSACTION = 2;
	public static long TYPE_TRANSACTION_CHARGE = 3;
	public static long TYPE_TRANSACTION_DRAW = 4;
}
