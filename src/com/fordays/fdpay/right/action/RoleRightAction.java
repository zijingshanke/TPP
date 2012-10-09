package com.fordays.fdpay.right.action;

import javax.servlet.http.*;
import org.apache.struts.action.*;

import com.fordays.fdpay.right.RightForm;
import com.fordays.fdpay.right.RoleRightForm;
import com.fordays.fdpay.right.UserRightInfo;
import com.neza.database.*;
import com.neza.utility.*;

public class RoleRightAction extends Action
{
	public ActionForward execute(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	{
		String forwardPage = "";
		UserRightInfo uri = (UserRightInfo) request.getSession()
		    .getAttribute("URI");
		if (uri == null)
			return (mapping.findForward("valid"));

		RoleRightForm rrf = (RoleRightForm) form;
		String action = rrf.getThisAction();

		if (action.equals("insysright"))
		{
			if (!uri.hasRight("se01"))
				return (mapping.findForward("invalid"));
			doInsert(rrf, request);
			forwardPage = "indexsys";
		}
		else if (action.equals("upsysright"))
		{
			if (!uri.hasRight("se01"))
				return (mapping.findForward("invalid"));
			doUpdate(rrf, request);
			forwardPage = "indexsys";
		}
		forwardPage = "indexsys";
		return (mapping.findForward(forwardPage));
	}

	private void hasRight(RightForm rf, HttpServletRequest request)
	{
		String sql = "";
		String userId = rf.getUserId();
		SelectDataBean sdb = new SelectDataBean();

		try
		{
			if (userId == null || userId.equals(""))
			{
				UserRightInfo uri = null;
				uri = (UserRightInfo) request.getSession().getAttribute("URI");
				if (uri == null)
					userId = "0";
				else
					userId = String.valueOf(uri.getUserId());
			}
			sql = "select * from sys_right where user_id=" + userId;

			sdb.setQuerySQL(sql);
			sdb.executeQuery();

			rf = new RightForm();
			rf.setUserId(userId);

			sql = "select user_id,user_name from sys_user order by user_type desc,user_name";
			ListMenu listusers = new ListMenu(sql);
			request.setAttribute("listusers", listusers);
			rf.setThisAction("");
			request.setAttribute("rf", rf);
		}
		catch (Exception ex)
		{
			System.out.println("ȡȨ�޳�?" + ex.getMessage());

		}
	}

	public void doUpdate(RoleRightForm rrf, HttpServletRequest request)
	{
		String sql = "";
		DBExecuteBean dbe = new DBExecuteBean();
		sql = "update role_right set ";
		sql += "right_name='" + rrf.getRightName();
		sql += "',right_code='" + rrf.getRightCode();
		sql += "',right_action='" + rrf.getRightAction();
		sql += "',right_event='" + rrf.getRightEvent();
		sql += "',right_description='" + rrf.getRightDescription();
		sql += "' where right_key=" + rrf.getRightKey();
		// System.out.println("sql=" + sql);
		dbe.executeUpdateSQL(sql);
	}

	public void doInsert(RoleRightForm rrf, HttpServletRequest request)
	{
		String sql = "";
		DBExecuteBean dbe = new DBExecuteBean();
		sql = "insert into role_right(right_key,right_name,right_code,right_action,right_event,right_description,role_id,right_status)";
		sql += " values(seq_roleright.nextval,'" + rrf.getRightName() + "','"
		    + rrf.getRightCode() + "','";
		sql += rrf.getRightAction() + "','" + rrf.getRightEvent() + "','";
		;
		sql += rrf.getRightDescription() + "'," + rrf.getSysRoleID() + ",1)";
		// System.out.println("sql=" + sql);
		dbe.executeUpdateSQL(sql);

	}

	private boolean stringToBoolean(String strBoolean)
	{
		if (strBoolean.equals("1") || strBoolean.equals("true"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	private String booleanToString(boolean blnInt)
	{
		if (blnInt)
		{
			return "1";
		}
		else
		{
			return "0";
		}
	}

}