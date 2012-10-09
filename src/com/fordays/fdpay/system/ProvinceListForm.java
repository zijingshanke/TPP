package com.fordays.fdpay.system;
import com.neza.base.ListActionForm;

public class ProvinceListForm  extends ListActionForm{


	private static final long serialVersionUID = 1L;
	private Province province = new Province();
	

	
	public Province getProvince()
	{
		return province;
	}
	public void setProvince(Province province)
	{
		this.province = province;
	}
	public static long getSerialVersionUID()
	{
		return serialVersionUID;
	}
}
