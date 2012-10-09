package com.fordays.fdpay.system.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.fordays.fdpay.system.Bank;
import com.fordays.fdpay.system.BankListForm;
import com.fordays.fdpay.system.City;
import com.fordays.fdpay.system.CityListForm;
import com.fordays.fdpay.system.Province;
import com.fordays.fdpay.system.ProvinceListForm;
import com.fordays.fdpay.system.SysConfigListForm;
import com.fordays.fdpay.system.biz.BankBiz;
import com.fordays.fdpay.system.biz.CityBiz;
import com.fordays.fdpay.system.biz.ProvinceBiz;
import com.fordays.fdpay.system.biz.SysConfigBiz;
import com.fordays.fdpay.system.dao.ProvinceDAO;
import com.neza.base.BaseAction;
import com.neza.base.Inform;
import com.neza.exception.AppException;

public class CityListAction extends BaseAction{

	private ProvinceBiz provinceBiz;
	private CityBiz cityBiz;



	public ProvinceBiz getProvinceBiz()
	{
		return provinceBiz;
	}



	public CityBiz getCityBiz()
	{
		return cityBiz;
	}



	// 显示所有
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		CityListForm clf = (CityListForm) form;
		if (clf == null)
			clf = new CityListForm();
		
		List list =cityBiz.getCitys(clf);		
		clf.setList(list);
		request.setAttribute("provinces",provinceBiz.getProvinces());
		request.setAttribute("clf", clf);
		forwardPage = "listcity";
		return (mapping.findForward(forwardPage));
	}

	public ActionForward add(ActionMapping mapping, ActionForm form,
		    HttpServletRequest request, HttpServletResponse response)
		    throws AppException
		{

			String forwardPage = "";
			CityListForm clf = (CityListForm) form;
			Inform inf = new Inform();
			try
			{
				City city= new City();
				city.setCname(clf.getCity().getCname());
				city.setEname(clf.getCity().getEname());
				Province province =provinceBiz.getProvinceById(clf.getProvinceId());
				city.setProvince(province);
				cityBiz.addCity(city);
				inf.setMessage("您已经成功增加了市！");
				inf.setForwardPage("/system/citylist.do");
				inf.setParamId("thisAction");
				inf.setParamValue("list");

			}
			catch (Exception ex)
			{
				inf.setMessage("新增信息出错！错误信息是：" + ex.getMessage());
				inf.setBack(true);
			}
			request.setAttribute("inf", inf);
			forwardPage = "inform";
			return (mapping.findForward(forwardPage));
		}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws AppException 
		{
			CityListForm clf = (CityListForm) form;
			String forwardPage = "";
			int id = 0;
			Inform inf = new Inform();
			int message = 0;
			try
			{
				for (int i = 0; i < clf.getSelectedItems().length; i++)
				{
					id = clf.getSelectedItems()[i];
					if (id > 0)
						message += cityBiz.deleteCityById(id);
				}

				if (message > 0)
				{
					inf.setMessage("您已经成功删除该市!");
				}
				else
				{
					inf.setMessage("删除失败!");
				}

				inf.setForwardPage("/system/citylist.do");
				inf.setParamId("thisAction");
				inf.setParamValue("list");
			}
			catch (Exception ex)
			{
				inf.setMessage("删除失败" + ex.getMessage());
				inf.setBack(true);
			}
			request.setAttribute("inf", inf);
			forwardPage = "inform";

			return (mapping.findForward(forwardPage));
		}
	public void setProvinceBiz(ProvinceBiz provinceBiz)
	{
		this.provinceBiz = provinceBiz;
	}



	public void setCityBiz(CityBiz cityBiz)
	{
		this.cityBiz = cityBiz;
	}
}
