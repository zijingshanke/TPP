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
import com.fordays.fdpay.system.Province;
import com.fordays.fdpay.system.ProvinceListForm;
import com.fordays.fdpay.system.SysConfigListForm;
import com.fordays.fdpay.system.biz.BankBiz;
import com.fordays.fdpay.system.biz.CityBiz;
import com.fordays.fdpay.system.biz.ProvinceBiz;
import com.fordays.fdpay.system.biz.SysConfigBiz;
import com.fordays.fdpay.system.dao.ProvinceDAO;
import com.fordays.fdpay.user.SysUser;
import com.fordays.fdpay.user.UserListForm;
import com.neza.base.BaseAction;
import com.neza.base.Inform;
import com.neza.exception.AppException;

public class ProvinceListAction extends BaseAction{
	private ProvinceBiz provinceBiz;





	// 显示所有
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		
		ProvinceListForm plf = (ProvinceListForm) form;
		if (plf == null)
			plf = new ProvinceListForm();
		List list =provinceBiz.getProvinces(plf);		
		plf.setList(list);
		request.setAttribute("plf", plf);
		forwardPage = "listprovince";
		return (mapping.findForward(forwardPage));
	}

	public ActionForward add(ActionMapping mapping, ActionForm form,
		    HttpServletRequest request, HttpServletResponse response)
		    throws AppException
		{

			String forwardPage = "";
			ProvinceListForm plf = (ProvinceListForm) form;
			Inform inf = new Inform();
			try
			{
				Province province=plf.getProvince();
				provinceBiz.addProvinces(province);
				inf.setMessage("您已经成功增加了省份！");
				inf.setForwardPage("/system/provincelist.do");
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
			ProvinceListForm plf = (ProvinceListForm) form;
			String forwardPage = "";
			int id = 0;
			Inform inf = new Inform();
			int message = 0;
			try
			{
				for (int i = 0; i < plf.getSelectedItems().length; i++)
				{
					id = plf.getSelectedItems()[i];
					if (id > 0)
						message += provinceBiz.deleteProvincesById(id);
				}

				if (message > 0)
				{
					inf.setMessage("您已经成功删除用户!");
				}
				else
				{
					inf.setMessage("删除失败!");
				}

				inf.setForwardPage("/system/provincelist.do");
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



	
}
