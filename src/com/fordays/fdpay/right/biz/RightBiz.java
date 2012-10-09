package com.fordays.fdpay.right.biz;


import com.fordays.fdpay.right.UserRightInfo;
import com.neza.exception.AppException;


public interface RightBiz {
	public void setRights(UserRightInfo uri,long userId) throws AppException;
}
