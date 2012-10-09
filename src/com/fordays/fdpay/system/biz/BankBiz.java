package com.fordays.fdpay.system.biz;


import java.util.List;

import com.fordays.fdpay.system.Bank;
import com.fordays.fdpay.system.BankListForm;
import com.fordays.fdpay.system.Province;
import com.neza.exception.AppException;




public interface BankBiz {


	public List getBanks(BankListForm blf) throws AppException;
	public List getBanksByCityId(int cityId) throws AppException;
	public void addBank(Bank bank) throws AppException;
	public long deleteBankById(long id) throws AppException;
}
