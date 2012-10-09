package com.fordays.fdpay.system.dao;

import java.util.List;

import com.fordays.fdpay.system.Bank;
import com.fordays.fdpay.system.BankListForm;
import com.fordays.fdpay.system.City;
import com.neza.base.BaseDAO;
import com.neza.exception.AppException;

public interface BankDAO extends BaseDAO{
	List getBankByCity(City  city)throws AppException; 
	public Bank getBankById(Long bankId) throws AppException;
	public List getBanks(BankListForm blf) throws AppException;
	public List getBankByCityId(int cityId) throws AppException;
	void  save(Bank bank) throws AppException;
	public void deleteById(long id) throws AppException;
}
