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
import com.fordays.fdpay.system.SysConfigListForm;
import com.fordays.fdpay.system.biz.BankBiz;
import com.fordays.fdpay.system.biz.CityBiz;
import com.fordays.fdpay.system.biz.ProvinceBiz;
import com.fordays.fdpay.system.biz.SysConfigBiz;
import com.fordays.fdpay.system.dao.ProvinceDAO;
import com.neza.base.BaseAction;
import com.neza.base.Inform;
import com.neza.exception.AppException;

public class BankListAction extends BaseAction{
	private BankBiz bankBiz;
	private ProvinceBiz provinceBiz;
	private CityBiz cityBiz;

	public void setBankBiz(BankBiz bankBiz)
	{
		this.bankBiz = bankBiz;
	}



	// 显示所有
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		BankListForm blf = (BankListForm) form;
		if (blf == null)
			blf = new BankListForm();
		List list =bankBiz.getBanks(blf);
		
		blf.setList(list);
	
	
		if(blf.getProvinceId()!=0){
		 List citys=cityBiz.getCitys(blf.provinceId);
		 blf.setCitys(citys);
		}
		if(blf.getProvinceId()!=0&&blf.getCityId()!=0){
			List banks=bankBiz.getBanksByCityId(blf.getCityId());
			blf.setBanks(banks);
		}
		request.setAttribute("provinces",provinceBiz.getProvinces());
		request.setAttribute("citys",blf.getCitys());
		request.setAttribute("banks",blf.getBanks());
		request.setAttribute("blf", blf);
		forwardPage = "listbank";
		return (mapping.findForward(forwardPage));
	}
	public ActionForward viewBank(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		BankListForm blf = (BankListForm) form;
		if (blf == null)
			blf = new BankListForm();
		
		if(blf.getProvinceId()!=0){
			 List citys=cityBiz.getCitys(blf.provinceId);
			 blf.setCitys(citys);
		}else{
			List  provinces =provinceBiz.getProvinces();
			blf.setProvinces(provinces);
		}

		request.setAttribute("provinces",provinceBiz.getProvinces());
		request.setAttribute("citys",blf.getCitys());
		forwardPage = "viewbank";
		return (mapping.findForward(forwardPage));
	}

	public ActionForward add(ActionMapping mapping, ActionForm form,
		    HttpServletRequest request, HttpServletResponse response)
		    throws AppException
		{

			String forwardPage = "";
			BankListForm blf = (BankListForm) form;
			Inform inf = new Inform();
			try
			{
				Bank bank= new Bank();
				City city = cityBiz.getCityById(blf.getCityId());
				bank.setCity(city);
				bank.setCname(blf.getBank().getCname());
				bank.setEname(blf.getBank().getEname());
				bank.setSname(blf.getBank().getSname());
				bankBiz.addBank(bank);
				inf.setMessage("您已经成功增加了银行！");
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
			BankListForm blf = (BankListForm) form;
			String forwardPage = "";
			int id = 0;
			Inform inf = new Inform();
			int message = 0;
			try
			{
				for (int i = 0; i < blf.getSelectedItems().length; i++)
				{
					id = blf.getSelectedItems()[i];
					if (id > 0)
						message += bankBiz.deleteBankById(id);
				}

				if (message > 0)
				{
					inf.setMessage("您已经成功删除此银行!");
				}
				else
				{
					inf.setMessage("删除失败!");
				}

				inf.setForwardPage("/system/banklist.do");
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
