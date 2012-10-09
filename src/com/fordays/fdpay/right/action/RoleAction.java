package com.fordays.fdpay.right.action;

import javax.servlet.http.*;
import org.apache.struts.action.*;

import com.fordays.fdpay.right.Role;
import com.fordays.fdpay.right.RoleForm;
import com.fordays.fdpay.right.UserRightInfo;
import com.neza.base.Inform;
import com.neza.database.*;



public class RoleAction extends Action
{
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	{
		String forwardPage = "";
		RoleForm uf = (RoleForm) form;
		String action = uf.getThisAction();

		UserRightInfo uri = (UserRightInfo) request.getSession()
				.getAttribute("URI");
		if (uri == null)
			return (mapping.findForward("valid"));

		if (action.equals("upsysrole"))
		{
			 if (!uri.hasRight("se01"))
				 return (mapping.findForward("invalid"));
			doUpdate(uf, request,0);
			forwardPage = "indexsys";
			forwardPage = "inform";
		}
		if (action.equals("upuserrole"))
		{
			 if (!uri.hasRight("se01"))
				 return (mapping.findForward("invalid"));
			doUpdate(uf, request,1);
			forwardPage = "indexuser";
			forwardPage = "inform";
		}
		else if (action.equals("inuserrole"))
		{
			 if (!uri.hasRight("se01"))
				 return (mapping.findForward("invalid"));
			doInsert(uf, request, 1);
			forwardPage = "indexuser";
			forwardPage = "inform";
		}
		else if (action.equals("insysrole"))
		{
			 if (!uri.hasRight("se01"))
				 return (mapping.findForward("invalid"));
			doInsert(uf, request, 0);
			forwardPage = "indexsys";
			forwardPage = "inform";
		}

		return (mapping.findForward(forwardPage));
	}

	public void doUpdate(RoleForm rf, HttpServletRequest request,int s)
	{
		String sql = "";
		DBExecuteBean dbe = new DBExecuteBean();
		String page="";
		if(s==0)
			page="sys";
		else
			page="user";
		sql = "update Role set ";
		sql += "role_name='" + rf.getRoleName();
		sql += "',role_key='" + rf.getRoleKey();
		sql += "',role_description='" + rf.getRoleDescription();
		sql += "' where role_id=" + rf.getRoleID();
		dbe.executeUpdateSQL(sql);
		Inform inf = new Inform();
		if(dbe.getStatusCode()>0)
		{
			inf.setMessage("您已经成功修改了用户角色!");
			inf.setForwardPage("/right/rolelist.do?thisAction="+page);
		}
		else
		{
			inf.setMessage("修改用户角色失败!");
			inf.setBack(true);
		}
		request.setAttribute("inf", inf);
	}

	public void doInsert(RoleForm rf, HttpServletRequest request, int s)
	{
		String sql = "";
		DBExecuteBean dbe = new DBExecuteBean();
		String page="";
		if(s==0)
			page="sys";
		else
			page="user";
		Inform inf = new Inform();

			sql = "insert into role(role_id,role_name,role_key,role_description,role_system,role_status,role_type)";
			sql += " values(seq_role.nextval,'" + rf.getRoleName() + "','";
			sql += rf.getRoleKey() + "','";
			sql += rf.getRoleDescription() + "'," + s + ",1,1)";
			dbe.executeUpdateSQL(sql);
				if(dbe.getStatusCode()>0)
			{
				inf.setMessage("您已经成功增加了用户角色!");
				inf.setForwardPage("/right/rolelist.do?thisAction="+page);
			}
			else
			{
				inf.setMessage("增加用户角色失败!");
				inf.setBack(true);
			}			

		request.setAttribute("inf", inf);
	}

}

