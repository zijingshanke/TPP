package com.fordays.fdpay.system;
import java.util.ArrayList;
import java.util.List;

import com.neza.base.ListActionForm;

public class CityListForm  extends ListActionForm{


	private static final long serialVersionUID = 1L;
	private City city =new City();
	private List provinces = new ArrayList<Province>();
	private int provinceId;

	public int getProvinceId()
	{
		return provinceId;
	}



	public void setProvinceId(int provinceId)
	{
		this.provinceId = provinceId;
	}



	public City getCity()
	{
		return city;
	}



	public void setCity(City city)
	{
		this.city = city;
	}



	public static long getSerialVersionUID()
	{
		return serialVersionUID;
	}



	public List getProvinces()
	{
		return provinces;
	}



	public void setProvinces(List provinces)
	{
		this.provinces = provinces;
	}




}
