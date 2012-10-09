package com.fordays.fdpay.user.action;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.fordays.fdpay.user.SysUser;
import com.fordays.fdpay.user.UserListForm;
import com.fordays.fdpay.user.biz.UserBiz;
import com.neza.base.BaseAction;
import com.neza.base.Inform;
import com.neza.base.NoUtil;
import com.neza.exception.AppException;


public class UserListAction extends BaseAction
{
	private UserBiz userBiz;
	private NoUtil noUtil;
	
	
	

	public void setUserBiz(UserBiz userBiz)
		{
			this.userBiz = userBiz;
		}

	public void setNoUtil(NoUtil noUtil)
  	{
  		this.noUtil = noUtil;
  	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)  throws AppException
		{
			String forwardPage = "";

			UserListForm ulf = (UserListForm) form;
			int id = 0;
			if (ulf.getSelectedItems().length > 0)
			{
				id = ulf.getSelectedItems()[0];
			}
			else
				id = ulf.getUserId();
			if (id > 0)
			{
				System.out.println("Id" + id);
				SysUser user = (SysUser) userBiz.getUserById(id);
				if (user == null)
				{
					System.out.println("User==null");
				}
				user.setThisAction("update");
				request.setAttribute("user", user);
			}
			else
				request.setAttribute("user", new SysUser());
			forwardPage = "edituser";
			return (mapping.findForward(forwardPage));
		}

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws AppException 
		{
			String forwardPage = "";
			UserListForm ulf = (UserListForm) form;
			int id = ulf.getUserId();
			if (id > 0)
			{
				SysUser user = (SysUser) userBiz.getUserById(id);
				user.setThisAction("view");
				request.setAttribute("user", user);
			}
			else
			{
				forwardPage = "login";
			}

			forwardPage = "viewuser";
			return (mapping.findForward(forwardPage));
		}

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws AppException 
		{
			SysUser user = new SysUser();
			user.setThisAction("insert");
			request.setAttribute("user", user);
			user.setUserStatus(new Long(1));
			String forwardPage = "edituser";
			return (mapping.findForward(forwardPage));
		}

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws AppException 
		{
			String forwardPage = "";
			UserListForm ulf = (UserListForm) form;
			if (ulf == null)
				ulf = new UserListForm();
		
			try
			{
				ulf.setList(userBiz.getUsers(ulf));
			}
			catch (Exception ex)
			{
				ulf.setList(new ArrayList());
			}
			request.setAttribute("ulf", ulf);
			forwardPage = "listuser";
			
			return (mapping.findForward(forwardPage));
		}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws AppException 
		{
			UserListForm ulf = (UserListForm) form;
			String forwardPage = "";
			int id = 0;
			Inform inf = new Inform();
			int message = 0;
			try
			{
				for (int i = 0; i < ulf.getSelectedItems().length; i++)
				{
					id = ulf.getSelectedItems()[i];
					if (id > 0)
						message += userBiz.deleteUser(id);
				}

				if (message > 0)
				{
					inf.setMessage("您已经成功删除用户!");
				}
				else
				{
					inf.setMessage("删除失败!");
				}

				inf.setForwardPage("/user/userlist.do");
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



	public void doOpenClose(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws AppException 
		{
			UserListForm ulf = (UserListForm) form;
			int id = 0;
			try
			{
				for (int i = 0; i < ulf.getSelectedItems().length; i++)
				{
					id = ulf.getSelectedItems()[i];
					if (id > 0)
					{
						SysUser user = userBiz.getUserById(id);
						// / user.setUserStatus(new Integer(flag));
						userBiz.updateUserInfo(user);
					}
				}
			}
			catch (Exception ex)
			{

			}
		}

}
