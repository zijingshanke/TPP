package com.fordays.fdpay.system;

import java.util.ArrayList;
import java.util.List;

import com.neza.base.ListActionForm;

public class BankListForm  extends ListActionForm{


	private static final long serialVersionUID = 1L;
	public  List citys = new ArrayList<City>();;
	public List provinces = new ArrayList<Province>();
	public List banks = new ArrayList<Bank>();
	public int cityId;
	public int provinceId;
	public int bankId;
	private Bank bank = new Bank();
	public Bank getBank()
	{
		return bank;
	}
	public void setBank(Bank bank)
	{
		this.bank = bank;
	}
	public int getCityId()
	{
		return cityId;
	}
	public void setCityId(int cityId)
	{
		this.cityId = cityId;
	}
	public int getProvinceId()
	{
		return provinceId;
	}
	public void setProvinceId(int provinceId)
	{
		this.provinceId = provinceId;
	}


	
	public List getCitys()
	{
		return citys;
	}
	public void setCitys(List citys)
	{
		this.citys = citys;
	}
	public List getProvinces()
	{
		return provinces;
	}
	public void setProvinces(List provinces)
	{
		this.provinces = provinces;
	}
	public static long getSerialVersionUID() {
		
		return serialVersionUID;
	}
	public List getBanks()
	{
		return banks;
	}
	public void setBanks(List banks)
	{
		this.banks = banks;
	}
	public int getBankId()
	{
		return bankId;
	}
	public void setBankId(int bankId)
	{
		this.bankId = bankId;
	}
}
