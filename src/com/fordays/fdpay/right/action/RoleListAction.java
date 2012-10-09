package com.fordays.fdpay.right.action;

import java.util.*;
import javax.servlet.http.*;
import org.apache.struts.action.*;

import com.fordays.fdpay.right.RoleForm;
import com.fordays.fdpay.right.RoleListForm;
import com.fordays.fdpay.right.RoleRightForm;
import com.fordays.fdpay.right.UserRightInfo;
import com.fordays.fdpay.user.SysUser;
import com.neza.base.Constant;
import com.neza.base.Inform;
import com.neza.database.*;
import com.neza.tool.XMLUtil;
import com.neza.utility.*;

public class RoleListAction extends Action
{

	public ActionForward execute(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	{
		String forwardPage = "";
		RoleListForm ulf = (RoleListForm) form;
		UserRightInfo uri = (UserRightInfo) request.getSession()
		    .getAttribute("URI");
		if (uri == null)
			return (mapping.findForward("valid"));
		String action = ulf.getThisAction();

		if (action.equals("delsysrole"))
		{
			if (!uri.hasRight("se01"))
				return (mapping.findForward("invalid"));
			doDelRole(ulf, 0, request);
			doSysList(ulf, request);

			forwardPage = "indexsys";
		}
		else if (action.equals("addsysrole"))
		{
			if (!uri.hasRight("se01"))
				return (mapping.findForward("invalid"));
			RoleForm rf = new RoleForm();
			rf.setRoleSystem("0");
			rf.setThisAction("insysrole");
			request.setAttribute("rf", rf);
			forwardPage = "editrole";
		}
		else if (action.equals("adduserrole"))
		{
			if (!uri.hasRight("se01"))
				return (mapping.findForward("invalid"));
			RoleForm rf = new RoleForm();
			rf.setRoleSystem("1");
			rf.setThisAction("inuserrole");
			request.setAttribute("rf", rf);
			forwardPage = "editrole";
		}
		else if (action.equals("editsysrole"))
		{
			if (!uri.hasRight("se01"))
				return (mapping.findForward("invalid"));
			doEditRole(ulf, request, 0);
			forwardPage = "editrole";
		}
		else if (action.equals("edituserrole"))
		{
			if (!uri.hasRight("se01"))
				return (mapping.findForward("invalid"));
			doEditRole(ulf, request, 1);

			forwardPage = "editrole";
		}
		else if (action.equals("deluserrole"))
		{
			if (!uri.hasRight("se01"))
				return (mapping.findForward("invalid"));
			doDelRole(ulf, 1, request);
			// _doUserList(ulf, request);
			// DB2XML.out(Constant.SERVLET_CN_PATH,"role");
			forwardPage = "inform";
		}
		else if (action.equals("delsysright"))
		{
			if (!uri.hasRight("se01"))
				return (mapping.findForward("invalid"));
			doDeleteRight(ulf, request);
			doSysList(ulf, request);
			forwardPage = "indexsys";
		}
		else if (action.equals("addsysright"))
		{
			if (!uri.hasRight("se01"))
				return (mapping.findForward("invalid"));
			RoleRightForm rrf = new RoleRightForm();
			rrf.setSysRoleID(ulf.getSysRoleID());
			rrf.setThisAction("insysright");
			request.setAttribute("rrf", rrf);
			forwardPage = "newsysright";
		}
		else if (action.equals("editsysright"))
		{
			if (!uri.hasRight("se01"))
				return (mapping.findForward("invalid"));
			doEditSysRight(ulf, request, 0);
			forwardPage = "editsysright";
		}
		else if (action.equals("deluserright"))
		{
			if (!uri.hasRight("se01"))
				return (mapping.findForward("invalid"));
			doDeleteRight(ulf, request);
			doList(ulf, request);
			forwardPage = "indexuser";
		}
		else if (action.equals("edituserright"))
		{
			if (!uri.hasRight("se02"))
				return (mapping.findForward("invalid"));
			doEditUserRight(ulf, request, uri);
			_doUserList(ulf, request);
			forwardPage = "indexuser";
		}
		else if (action.equals("editselecteduserright"))
		{
			if (!uri.hasRight("se02"))
				return (mapping.findForward("invalid"));
			doEditSelectedUserRight(ulf, request);
			forwardPage = "editselecteduserright";
		}
		else if (action.equals("upselecteduserright"))
		{
			if (!uri.hasRight("se02"))
				return (mapping.findForward("invalid"));
			forwardPage = doUpSelectedUserRight(ulf, request, uri);
			if (forwardPage.equals(""))
			{
				ulf.setUserRoleID("");
				doRight(ulf, request, uri);
				forwardPage = "indexright";
			}
			else
				forwardPage = "inform";
		}

		else if (action.equals("sys"))
		{
			if (!uri.hasRight("se01"))
				return (mapping.findForward("invalid"));
			doSysList(ulf, request);
			forwardPage = "indexsys";
		}
		else if (action.equals("user"))
		{
			if (!uri.hasRight("se01"))
				return (mapping.findForward("invalid"));
			_doUserList(ulf, request);
			forwardPage = "indexuser";
		}
		else if (action.equals("edituser4role"))
		{
			if (!uri.hasRight("se02"))
				return (mapping.findForward("invalid"));
			doEditUser4role(ulf, request, uri);
			forwardPage = "edituser4role";
		}
		else if (action.equals("updateuser4role"))
		{
			if (!uri.hasRight("se02"))
				return (mapping.findForward("invalid"));
			doUpdateUser4role(ulf, request, uri);
			forwardPage = "inform";
		}
		else if (action.equals("editrole4user"))
		{
			if (!uri.hasRight("se02"))
				return (mapping.findForward("invalid"));
			doEditRole4User(ulf, request, uri);
			forwardPage = "editrole4user";
		}
		else if (action.equals("updaterole4user"))
		{
			if (!uri.hasRight("se02"))
				return (mapping.findForward("invalid"));
			doUpdateRole4User(ulf, request, uri);
			forwardPage = "inform";
		}
		else if (action.equals("right"))
		{
			if (!uri.hasRight("se02"))
				return (mapping.findForward("invalid"));
			doRight(ulf, request, uri);
			forwardPage = "indexright";
		}
		else if (action.equals("upuserright"))
		{
			if (!uri.hasRight("se02"))
				return (mapping.findForward("invalid"));
			forwardPage = doUpUserRight(ulf, request, uri);
			if (forwardPage.equals(""))
			{
				forwardPage = "indexright";
				doRight(ulf, request, uri);
			}
		}
		else if (action.equals("editroleright"))
		{
			if (!uri.hasRight("se02"))
				return (mapping.findForward("invalid"));
			forwardPage = "editroleright";
			__doUserList(ulf, request);
		}
		else
		{
			if (!uri.hasRight("se02"))
				return (mapping.findForward("invalid"));
			_doUserList(ulf, request);
			forwardPage = "indexuser";
		}
		return (mapping.findForward(forwardPage));
	}

	public void doSysList(RoleListForm ulf, HttpServletRequest request)
	{
		String sql = "";
		String temp = "";
		int rowcount = 0;
		int srowcount = 0;

		ArrayList list = new ArrayList();
		ArrayList slist = new ArrayList();
		sql = "select * from role where role_status=1 and role_system=0";
		SelectDataBean sdb = new SelectDataBean();
		sdb.setQuerySQL(sql);
		sdb.executeQuery();
		rowcount = sdb.getRowCount();

		for (int i = 1; i < rowcount + 1; i++)
		{
			RoleForm rf = new RoleForm();
			temp = sdb.getColValue(i, "role_id");
			if (ulf.getSysRoleID().equals("") && i == 1)
				ulf.setSysRoleID(temp);
			rf.setRoleID(temp);
			rf.setRoleName(sdb.getColValue(i, "role_name"));
			rf.setRoleDescription(sdb.getColValue(i, "role_description"));
			rf.setRoleSystem((sdb.getColValue(i, "role_system").equals("0") ? "true"
			    : "false"));
			list.add(rf);
		}
		sql = "select * from role_right r join role o on r.role_id=o.role_id where r.right_status=1 and  r.role_id="
		    + ulf.getSysRoleID() + " order by r.right_code";
		sdb.setQuerySQL(sql);
		sdb.executeQuery();
		srowcount = sdb.getRowCount();
		for (int j = 1; j < srowcount + 1; j++)
		{
			RoleRightForm rrf = new RoleRightForm();
			rrf.setRightKey(sdb.getColValue(j, "right_key"));
			rrf.setRightName(sdb.getColValue(j, "right_name"));
			rrf.setRightCode(sdb.getColValue(j, "right_code"));
			ulf.setRoleName(sdb.getColValue(j, "role_name"));
			rrf.setRightDescription(sdb.getColValue(j, "right_description"));
			rrf.setRightAction(sdb.getColValue(j, "right_action"));
			rrf.setRightEvent(sdb.getColValue(j, "right_event"));
			slist.add(rrf);
		}
		ulf.setList(list);
		ulf.setThisAction("");
		request.setAttribute("ulf", ulf);
		request.setAttribute("slist", slist);
		sdb.close();
	}

	public void _doUserList(RoleListForm ulf, HttpServletRequest request)
	{
		String sql = "";
		String userRoleID = ulf.getUserRoleID();
		String temp = "";
		PageListBean plb = new PageListBean();

		if (ulf.getIntPage() == 0)
			ulf.setIntPage(1);
		int intPage = ulf.getIntPage();
		ArrayList list = new ArrayList();
		sql = "select * from role where role_status=1 and role_system=1";

		plb.setPerPage(15);
		plb.setNextPage(ulf.getIntPage() - 1);
		plb.setQuerySQL(sql.toString());

		int rowcount = plb.getRowCount();
		for (int i = 1; i < rowcount + 1; i++)
		{
			RoleForm uf = new RoleForm();
			temp = plb.getColValue(i, "role_id");
			if (userRoleID.equals("") && i == 1)
				userRoleID = temp;
			uf.setRoleID(temp);
			uf.setRoleName(plb.getColValue(i, "role_name"));
			uf.setRoleKey(plb.getColValue(i, "role_key"));
			uf.setRoleDescription(plb.getColValue(i, "role_description"));
			list.add(uf);
		}
		ulf.setPerPageNum(plb.getPerPage());

		ulf.setTotalRowCount(plb.getTotalRowCount());
		ulf.setList(list);
		ulf.setLastAction(ulf.getThisAction());

		request.setAttribute("ulf", ulf);

	}

	public void __doUserList(RoleListForm ulf, HttpServletRequest request)
	{
		String sql = "";
		String userRoleID = ulf.getUserRoleID();
		SelectDataBean ssdb = new SelectDataBean();
		sql = "select * from role where role_id=" + userRoleID;
		ssdb.setQuerySQL(sql);
		ssdb.executeQuery();
		if (ssdb.getRowCount() > 0)
		{
			ulf.setRoleName(ssdb.getColValue(1, "role_name") + "("
			    + ssdb.getColValue(1, "role_description") + ")");
		}


		sql = "select right_key from user_role_right";
		sql = sql + " where role_id=" + userRoleID;
		ssdb.setQuerySQL(sql);
		ssdb.executeQuery();
		int srowcount = ssdb.getRowCount();
		String[] selectedRightItems;
		if (srowcount >= 1)
			selectedRightItems = new String[srowcount];
		else
			selectedRightItems = new String[0];
		for (int j = 1; j < srowcount + 1; j++)
		{
			selectedRightItems[j - 1] = ssdb.getColValue(j, "right_key");
		}
		ulf.setSelectedRightItems(selectedRightItems);
		ulf.setThisAction("");
		String temp = XMLUtil.buildRightXml();
		ulf.setXml(temp);

		request.setAttribute("ulf", ulf);
		ssdb.close();
	}

	/*
	 * public void doUserList(RoleListForm ulf, HttpServletRequest request) {
	 * String sql = ""; String userRoleID = ""; String sysRoleID = ""; String temp =
	 * ""; int rowcount = 0; int srowcount = 0; SelectDataBean sdb = new
	 * SelectDataBean(); SelectDataBean ssdb = new SelectDataBean(); ArrayList
	 * list = new ArrayList(); ArrayList slist = new ArrayList(); sql = "select
	 * from role where role_status=1 and role_system=1"; //
	 * System.out.println("sql=" + sql); sdb.setQuerySQL(sql); sdb.executeQuery();
	 * rowcount = sdb.getRowCount(); ; for (int i = 1; i < rowcount + 1; i++) {
	 * RoleForm uf = new RoleForm(); temp = sdb.getColValue(i, "role_id"); //
	 * System.out.println("temp i=" + i + ", " + temp); if (i == 1) userRoleID =
	 * temp; uf.setRoleID(temp); uf.setRoleName(sdb.getColValue(i, "role_name"));
	 * uf.setRoleKey(sdb.getColValue(i, "role_key"));
	 * uf.setRoleDescription(sdb.getColValue(i, "role_description"));
	 * list.add(uf); } request.setAttribute("list1", list);
	 * 
	 * if (ulf.getUserRoleID().equals("")) { ulf.setUserRoleID(userRoleID); }
	 * 
	 * list = new ArrayList(); sql = "select from role where role_status=1 and
	 * role_system=0"; // System.out.println("sql=" + sql); sdb.setQuerySQL(sql);
	 * sdb.executeQuery(); rowcount = sdb.getRowCount(); ; for (int i = 1; i <
	 * rowcount + 1; i++) { RoleForm uf = new RoleForm(); temp =
	 * sdb.getColValue(i, "role_id"); if (i == 1) sysRoleID = temp;
	 * uf.setRoleID(temp); uf.setRoleName(sdb.getColValue(i, "role_name"));
	 * uf.setRoleDescription(sdb.getColValue(i, "role_description"));
	 * uf.setRoleSystem((sdb.getColValue(i, "role_system").equals("0") ? "true" :
	 * "false")); list.add(uf); }
	 * 
	 * if (ulf.getSysRoleID().equals("")) { ulf.setSysRoleID(sysRoleID); } sql =
	 * "select * from role_right where right_status=1 and role_id=" +
	 * ulf.getSysRoleID(); // System.out.println("sql=" + sql);
	 * ssdb.setQuerySQL(sql); ssdb.executeQuery(); srowcount = ssdb.getRowCount();
	 * 
	 * for (int j = 1; j < srowcount + 1; j++) { RoleRightForm rrf = new
	 * RoleRightForm();
	 * 
	 * rrf.setRightKey(ssdb.getColValue(j, "right_key"));
	 * rrf.setRightName(ssdb.getColValue(j, "right_name"));
	 * rrf.setRightCode(ssdb.getColValue(j, "right_code"));
	 * rrf.setRightDescription(ssdb.getColValue(j, "right_description"));
	 * ulf.setRoleName(ssdb.getColValue(j, "role_name")); if
	 * (!rrf.getRightCode().equals("sa01")) slist.add(rrf); } ulf.setList(list);
	 * 
	 * sql = "select right_key from user_role_right"; sql = sql + " where
	 * role_id=" + ulf.getUserRoleID(); sql = sql + " and right_key in (select
	 * right_key from role_right where role_id=" + ulf.getSysRoleID() + ")"; //
	 * System.out.println("sql=" + sql); ssdb.setQuerySQL(sql);
	 * ssdb.executeQuery(); srowcount = ssdb.getRowCount(); String[]
	 * selectedRightItems; if (srowcount >= 1) selectedRightItems = new
	 * String[srowcount]; else selectedRightItems = new String[0]; for (int j = 1;
	 * j < srowcount + 1; j++) { selectedRightItems[j - 1] = ssdb.getColValue(j,
	 * "right_key"); // System.out.println("selectedRightItems["
	 * +(j-1)+"]="+selectedRightItems[j-1]); }
	 * ulf.setSelectedRightItems(selectedRightItems); ulf.setThisAction("");
	 * request.setAttribute("ulf", ulf); request.setAttribute("slist", slist);
	 * ssdb.close(); sdb.close(); }
	 */
	public void doList(RoleListForm ulf, HttpServletRequest request)
	{
		String sql = "";
		String userRoleID = ulf.getUserRoleID();
		String temp = "";
		int rowcount = 0;
		int srowcount = 0;
		SelectDataBean sdb = new SelectDataBean();
		SelectDataBean ssdb = new SelectDataBean();
		List list = new ArrayList();
		List slist = new ArrayList();
		sql = "select * from role where role_status=1 and role_system=1";
		// System.out.println("sql=" + sql);
		sdb.setQuerySQL(sql);
		sdb.executeQuery();
		rowcount = sdb.getRowCount();
		;
		for (int i = 1; i < rowcount + 1; i++)
		{
			RoleForm uf = new RoleForm();
			temp = sdb.getColValue(i, "role_id");
			if (userRoleID.equals("") && i == 2)
				userRoleID = temp;
			uf.setRoleID(temp);
			uf.setRoleName(sdb.getColValue(i, "role_name"));
			uf.setRoleDescription(sdb.getColValue(i, "role_description"));
			list.add(uf);
		}
		request.setAttribute("list1", list);
		sql = "select * from role where role_status=1 and role_system=0";
		// System.out.println("sql=" + sql);
		sdb.setQuerySQL(sql);
		sdb.executeQuery();
		rowcount = sdb.getRowCount();
		;
		for (int i = 1; i < rowcount + 1; i++)
		{
			RoleForm uf = new RoleForm();
			temp = sdb.getColValue(i, "role_id");
			if (userRoleID.equals("") && i == 2)
				userRoleID = temp;
			uf.setRoleID(temp);
			uf.setRoleName(sdb.getColValue(i, "role_name"));
			uf.setRoleDescription(sdb.getColValue(i, "role_description"));
			slist.add(uf);
		}
		request.setAttribute("list0", slist);
		request.setAttribute("ulf", ulf);
		ssdb.close();
		sdb.close();
	}

	public void doEditUser4role(RoleListForm ulf, HttpServletRequest request,
	    UserRightInfo uri)
	{
		String sql = "";

		sql = "select role_id,role_name from role where role_system=1 and role_status=1 order by role_name";
		ListMenu rolelist = new ListMenu(sql, false);
		request.setAttribute("rolelist", rolelist);
		if (Constant.toInt(ulf.getUserRoleID()) < 1) {
			ulf.setUserRoleID(rolelist.getValue()[0]);
		}
		String sql1 = "";
		String sql2 = "";
		sql1 = "select u.user_id,user_no||'--'||user_name  as user_name from sys_user u where user_status=1 "
		    + " and not exists (select * from user_role r where r.role_id="
		    + ulf.getUserRoleID()
		    + " and u.user_id=r.user_id) order by  user_no,user_name";

		sql2 = "select u.user_id,user_no||'--'||user_name  as user_name from user_role r join sys_user u on u.user_id=r.user_id where r.role_id="
		    + ulf.getUserRoleID() + " order by  user_no,user_name";

		ListMenu userlist1 = new ListMenu(sql1, false);
		ListMenu userlist2 = new ListMenu(sql2, false);
		request.setAttribute("userlist1", userlist1);
		request.setAttribute("userlist2", userlist2);
		request.setAttribute("ulf", ulf);
	}

	public void doUpdateUser4role(RoleListForm ulf, HttpServletRequest request,
	    UserRightInfo uri)
	{
		String sql = "";
		DBExecuteBatch dbt = new DBExecuteBatch();

		sql = "delete from user_role where role_id=" + ulf.getUserRoleID();
		dbt.append(sql);
		if (ulf.getCount() > 0)
		{
			for (int i = 0; i < ulf.getRightUserID().length; i++)
			{
				sql = "insert into user_role(user_role_id,user_id,role_id) values(seq_userrole.nextval,"
				    + ulf.getRightUserID()[i] + "," + ulf.getUserRoleID() + ")";
				dbt.append(sql);
			}
		}
		dbt.executeBatch();
		Inform inf = new Inform();
		if (dbt.isSuccess())
		{
			inf.setMessage("您已经成功给用户授权!");
			inf.setForwardPage("/right/rolelist.do?thisAction=edituser4role");
		}
		else
		{
			inf.setMessage("操作失败!" + dbt.getMessage());
			inf.setBack(true);
		}
		request.setAttribute("inf", inf);
	}

	public void doEditRole4User(RoleListForm ulf, HttpServletRequest request,
	    UserRightInfo uri)
	{
		String sql = "";

		sql = "select user_id,user_no||'--'||user_name  as user_name from sys_user where user_status=1 order by user_no||'--'||user_name";
		ListMenu userlist = new ListMenu(sql, false);
		request.setAttribute("userlist", userlist);

		String sql1 = "";
		String sql2 = "";

		if (Constant.toInt(ulf.getUserID()) < 1)
		{
			ulf.setUserID(userlist.getValue()[0]);
		}

		sql1 = "select role_id,role_name from role where role_system=1 and role_status=1 and not exists (select * from user_role where user_role.role_id=role.role_id  and user_id="
		    + ulf.getUserID() + ") order by role_name";

		sql2 = "select r.role_id,role_name from user_role ur join role r on ur.role_id=r.role_id where user_id="
		    + ulf.getUserID() + " order by role_name";

		ListMenu rolelist1 = new ListMenu(sql1, false);
		ListMenu rolelist2 = new ListMenu(sql2, false);
		request.setAttribute("rolelist1", rolelist1);
		request.setAttribute("rolelist2", rolelist2);
		request.setAttribute("ulf", ulf);
	}

	public void doUpdateRole4User(RoleListForm ulf, HttpServletRequest request,
	    UserRightInfo uri)
	{
		String sql = "";
		DBExecuteBatch dbt = new DBExecuteBatch();

		sql = "delete from user_role where user_id=" + ulf.getUserID();
		dbt.append(sql);
		if (ulf.getCount() > 0)
		{
			for (int i = 0; i < ulf.getRightRoleID().length; i++)
			{
				sql = "insert into user_role(user_role_id,user_id,role_id) values(seq_userrole.nextval,"
				    + ulf.getUserID() + "," + ulf.getRightRoleID()[i] + ")";
				dbt.append(sql);
			}
		}
		dbt.executeBatch();
		Inform inf = new Inform();
		if (dbt.isSuccess())
		{
			inf.setMessage("您已经成功给用户授权!");
			inf.setForwardPage("/right/rolelist.do?thisAction=editrole4user");
		}
		else
		{
			inf.setMessage("操作失败!" + dbt.getMessage());
			inf.setBack(true);
		}
		request.setAttribute("inf", inf);
	}

	public void doRight(RoleListForm ulf, HttpServletRequest request,
	    UserRightInfo uri)
	{
		String sql = "";
		String userKey = ulf.getUserKey().trim();
		String roleID = ulf.getUserRoleID();

		ArrayList list = new ArrayList();

		sql = "select user_id,user_code,user_name,user_no,u.role_id,role_name,role_key from sys_user u left join role r ";
		sql = sql + " on u.role_id=r.role_id ";
		sql = sql + " where (user_name like '%" + userKey + "%'";
		sql = sql + " or user_no like '%" + userKey + "%')";
		sql = sql + " and user_status=1";

		if (roleID.equals("-1"))
		{
			// sql = sql + " and (u.role_id is null or u.role_id=-1) ";
			ulf.setUserRoleID("");
		}
		else if (!roleID.equals(""))
		{
			sql = sql + " and u.role_id=" + roleID;
			ulf.setUserRoleID(roleID);
		}

		sql = sql + " order by user_no,user_name";

		int intPage = 1;
		int perPageNum = 15;
		PageListBean plb = new PageListBean();
		intPage = ulf.getIntPage();

		plb.setPerPage(perPageNum);
		plb.setNextPage(intPage - 1);
		// �

		plb.setQuerySQL(sql.toString()); // ����
		int rowcount = plb.getRowCount();
		int pagecount = plb.getPageCount();

		SysUser uf = new SysUser();
		for (int i = 1; i < rowcount + 1; i++)
		{
			uf.setUserId(Constant.toLong(plb.getColValue(i, "user_id")));
			uf.setUserNo(plb.getColValue(i, "user_no"));
			uf.setUserName(plb.getColValue(i, "user_name"));
			list.add(uf.clone());
		}
		ulf.setList(list);
		ulf.setPerPageNum(plb.getPerPage());
		ulf.setTotalRowCount(plb.getTotalRowCount());
		request.setAttribute("ulf", ulf);

		sql = "select role_id,role_name from role where role_system=1 and role_status=1 order by role_name";
		ListMenu listusers = new ListMenu(sql);
		request.setAttribute("rolelist", listusers);

	}

	public void doEditRole(RoleListForm ulf, HttpServletRequest request, int s)
	{
		String sql = "";
		sql = "select * from role where role_id=";
		if (s == 0)
			sql = sql + ulf.getSysRoleID();
		else if (s == 1)
			sql = sql + ulf.getUserRoleID();
		SelectDataBean sdb = new SelectDataBean();
		sdb.setQuerySQL(sql);
		sdb.executeQuery();
		if (sdb.getRowCount() == 1)
		{
			RoleForm rf = new RoleForm();
			rf.setRoleID(sdb.getColValue(1, "role_id"));
			rf.setRoleName(sdb.getColValue(1, "role_name"));
			rf.setRoleKey(sdb.getColValue(1, "role_key"));
			rf.setRoleDescription(sdb.getColValue(1, "role_description"));
			if (s == 0)
				rf.setThisAction("upsysrole");
			else
				rf.setThisAction("upuserrole");
			request.setAttribute("rf", rf);
		}
		sdb.close();
	}

	public void doDeleteRight(RoleListForm ulf, HttpServletRequest request)
	{
		String sql = "";
		String[] items = ulf.getSelectedRightItems();

		try
		{
			DBExecuteBatch dbt = new DBExecuteBatch();
			for (int i = 0; i < items.length; i++)
			{
				sql = "delete from role_right where right_key=" + items[i];
				dbt.append(sql);
			}
			dbt.executeBatch();
		}
		catch (Exception ex)
		{
			System.out.println("ɾ�����ʧ�ܣ�" + ex);
		}
	}

	public void doEditSysRight(RoleListForm ulf, HttpServletRequest request, int s)
	{
		String sql = "";
		sql = "select * from role_right where right_key="
		    + ulf.getSelectedRightItems()[0];
		SelectDataBean sdb = new SelectDataBean();
		sdb.setQuerySQL(sql);
		sdb.executeQuery();
		if (sdb.getRowCount() == 1)
		{
			RoleRightForm rrf = new RoleRightForm();
			rrf.setRightKey(sdb.getColValue(1, "right_key"));
			rrf.setRightName(sdb.getColValue(1, "right_name"));
			rrf.setRightCode(sdb.getColValue(1, "right_code"));
			rrf.setRightAction(sdb.getColValue(1, "right_action"));
			rrf.setRightEvent(sdb.getColValue(1, "right_event"));
			rrf.setRightDescription(sdb.getColValue(1, "right_description"));
			rrf.setThisAction("upsysright");
			request.setAttribute("rrf", rrf);
		}
		sdb.close();
	}

	public void doEditUserRight(RoleListForm ulf, HttpServletRequest request,
	    UserRightInfo uri)
	{
		// System.out.println("==**********====" + ulf.getSelectedRightItem());
		String sql = "";
		String temp = "";
		String userRoleID = ulf.getUserRoleID();
		// System.out.println("userRoleID=" + userRoleID);
		String[] selectedRightItems = ulf.getSelectedRightItems();
		String sysRoleID = ulf.getSysRoleID();
		// System.out.println("sysRoleID=" + sysRoleID);
		SelectDataBean sdb = new SelectDataBean();
		/*
		 * sql = "select right_key from role_right where role_id=" + sysRoleID;
		 * System.out.println("sql role_right=" + sql); sdb.setQuerySQL(sql);
		 * sdb.executeQuery(); String[] sall = new String[sdb.getRowCount()]; for
		 * (int i = 1; i < sdb.getRowCount() + 1; i++) sall[i - 1] =
		 * sdb.getColValue(i, "right_key");
		 * 
		 * sql = "select right_key from user_role_right where role_id=" +
		 * userRoleID; sdb.setQuerySQL(sql); sdb.executeQuery(); String[] s1 = new
		 * String[sdb.getRowCount()]; for (int i = 0; i < sdb.getRowCount(); i++)
		 * s1[i - 1] = sdb.getColValue(i, "right_key");
		 */
		// sql = "delete from user_role_right where role_id=" + userRoleID
		// + " and right_key in "
		// + ArrayUtil.getStrOfArray(this.getMyRightKey(sysRoleID, request));
		sql = "delete from user_role_right where role_id=" + userRoleID;
		// + ArrayUtil.getStrOfArray(selectedRightItems);
		DBExecuteBatch dbt = new DBExecuteBatch();
		// System.out.println("sql=" + sql);
		// System.out
		// .println("selectedRightItems.length=" + selectedRightItems.length);

		dbt.append(sql);

		if (ulf.getSelectedRightItem() > 0)
		{
			for (int i = 0; i < selectedRightItems.length; i++)
			{
				if (selectedRightItems[0].equals(""))
					break;
				sql = "select right_code from role_right where right_key="
				    + selectedRightItems[i];
				sdb.setQuerySQL(sql);
				sdb.executeQuery();
				temp = sdb.getColValue(1, 1);

				if (sql.equals(""))// || !uri.hasRight(sql))//
				{
					continue;
				}
				sql = "insert into user_role_right(user_right_id,role_id,right_key,right_value) values(seq_userroleright.nextval,"
				    + userRoleID + ",";
				sql = sql + selectedRightItems[i] + ",1)";
				// System.out.println("sql=" + sql);
				dbt.append(sql);
			}
		}
		dbt.executeBatch();

		System.out.println("sql=" + dbt.getMessage());
	}

	public String doUpUserRight(RoleListForm ulf, HttpServletRequest request,
	    UserRightInfo uri)
	{
		String sql = "";
		String userRoleID = ulf.getUserRoleID();

		int[] selectedUserItems = ulf.getSelectedItems();

		String userIDs = ulf.getUserIDs();

		sql = "update sys_user set role_id=-1 where role_id=" + userRoleID
		    + " and user_id in " + userIDs;
		// System.out.println("sql=" + sql);
		DBExecuteBatch dbt = new DBExecuteBatch();
		dbt.append(sql);

		for (int i = 0; i < selectedUserItems.length; i++)
		{
			if (selectedUserItems[0] == -1)
				break;
			sql = "update sys_user set role_id=" + userRoleID;
			sql = sql + " where user_id=" + selectedUserItems[i];
			// System.out.println("sql=" + sql);
			dbt.append(sql);
		}
		// dbt.executeBatch();

		return "";
	}

	private void doEditSelectedUserRight(RoleListForm ulf,
	    HttpServletRequest request)
	{
		String temp = "";
		String userIDs = "";
		String sql = "select user_id,user_name,user_no from sys_user where user_type=0 and user_id in "
		    + transStr(ulf.getSelectedItems());
		SelectDataBean sdb = new SelectDataBean();
		sdb.setQuerySQL(sql);
		sdb.executeQuery();
		int rowcount = sdb.getRowCount();
		for (int i = 1; i < rowcount + 1; i++)
		{
			userIDs = userIDs + sdb.getColValue(i, "user_id") + ",";
			temp = temp + sdb.getColValue(i, "user_no") + "--"
			    + sdb.getColValue(i, "user_name") + ",";
		}
		userIDs = StringUtil.trim(userIDs);
		temp = StringUtil.trim(temp);
		ulf.setUserIDs(userIDs);
		ulf.setTemp(temp);
		ulf.setThisAction("upselecteduserright");
		request.setAttribute("ulf", ulf);
		sql = "select role_id,role_name from role where role_system=1 and role_status=1 order by role_name";
		ListMenu listusers = new ListMenu(sql);
		request.setAttribute("rolelist", listusers);
	}

	private String doUpSelectedUserRight(RoleListForm ulf,
	    HttpServletRequest request, UserRightInfo uri)
	{
		String sql = "";
		String userRoleID = ulf.getUserRoleID();
		/*
		 * if (!ArrayUtil.isInclude(getRightKeyOfRole(uri.getRoleId().toString()),
		 * getRightKeyOfRole(userRoleID))) { Inform inf = new Inform();
		 * inf.setMessage("���Ȩ�޲�����������Ȩ������Ȩ��!"); inf.setBack(true);
		 * request.setAttribute("inf", inf); return "inform"; }
		 */
		sql = "update sys_user set role_id=" + ulf.getUserRoleID()
		    + " where user_id in (" + ulf.getUserIDs() + ")";
		DBExecuteBean dbe = new DBExecuteBean();
		// System.out.println("sql=" + sql+",userKey="+ulf.getUserKey());
		ulf.setThisAction("right");
		dbe.executeUpdateSQL(sql);

		return "";
	}

	public void doDelRole(RoleListForm ulf, int t, HttpServletRequest request)
	{
		String sql = "";

		String userRole = "";
		Inform inf = new Inform();
		if (t == 0)
			userRole = ulf.getSysRoleID();
		else
			userRole = ulf.getUserRoleID();
		try
		{
			sql = "select role_id from user_role where role_id=" + userRole;
			SelectDataBean sdb = new SelectDataBean();
			sdb.setQuerySQL(sql);
			sdb.executeQuery();
			if (sdb.getRowCount() > 0)
			{
				inf.setMessage("该用户角色已经被使用,不能删除!");
				inf.setForwardPage("/right/rolelist.do?thisAction=user");
			}
			else
			{
				sql = " delete role where role_id=" + userRole;
				sdb.executeUpdateSQL(sql);
				inf.setMessage("您已经成功删除该用户角色!");
				inf.setForwardPage("/right/rolelist.do?thisAction=user");
			}
			sdb.close();
		}
		catch (Exception ex)
		{
			inf.setMessage("系统出错:" + ex.getMessage());
			inf.setBack(true);
		}
		request.setAttribute("inf", inf);
	}

	private String[] getMyRightKey(String sysRoleID, HttpServletRequest request)
	{
		UserRightInfo uri = (UserRightInfo) request.getSession()
		    .getAttribute("URI");
		String[] myKey = new String[0];
		String temp = "";
		if (uri == null)
			return myKey;
		else
		{
			String sql = "select right_key,right_code from role_right where right_status=1 and  role_id="
			    + sysRoleID;
			SelectDataBean sdb = new SelectDataBean();
			sdb.setQuerySQL(sql);
			sdb.executeQuery();
			for (int i = 1; i < sdb.getRowCount() + 1; i++)
			{
				// if (uri.hasRight(sdb.getColValue(i, "right_code")))
				temp = temp + sdb.getColValue(i, "right_key") + ",";
			}
			myKey = temp.split(",");
			sdb.close();
			return myKey;
		}

	}

	private String[] getRightKeyOfRole(String roleID)
	{
		String temp = "";
		String[] right = new String[0];
		String sql = "select right_key from role_right where right_status=1 and  role_id="
		    + roleID;
		SelectDataBean sdb = new SelectDataBean();
		sdb.setQuerySQL(sql);
		sdb.executeQuery();
		int rowcount = sdb.getRowCount();
		if (rowcount > 0)
		{
			right = new String[rowcount];
			for (int i = 1; i < rowcount + 1; i++)
			{
				temp = sdb.getColValue(i, "right_key");
				right[i - 1] = temp;
			}
		}
		sdb.close();
		return right;
	}

	private String transStr(int[] str)
	{
		if (str.length == 0)
			return "";
		String temp = "(";
		for (int i = 0; i < str.length; i++)
		{
			if (str.length - 1 == i)
				temp = temp + str[i];
			else
				temp = temp + str[i] + ",";
		}
		return temp + ")";
	}

	public static void main(String[] args)
	{
		RoleListAction rla = new RoleListAction();
		int[] str = { 1, 2, 3 };
		System.out.println("trans=" + rla.transStr(str));
	}

}