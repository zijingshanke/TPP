package com.fordays.fdpay.right.action;

import javax.servlet.http.*;

import org.apache.struts.action.*;
import com.fordays.fdpay.right.RoleRightForm;
import com.fordays.fdpay.right.UserRightInfo;
import com.neza.database.*;



public class RightAction extends Action
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

    if (!uri.hasRight("se01"))
      return (mapping.findForward("invalid"));
    if (action.equals("insysright"))
    {
      doUpdate(rrf, request);
      forwardPage = "indexsys";
    }
    else if (action.equals("update"))
    {
      doUpdate(rrf, request);
    }
    forwardPage = "indexsys";
    return (mapping.findForward(forwardPage));
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
    dbe.executeUpdateSQL(sql);
  }

  public void doInsert(RoleRightForm rrf, HttpServletRequest request, int s)
  {
    String sql = "";
    DBExecuteBean dbe = new DBExecuteBean();
    sql = "insert into role_right(right_key,right_name,right_code,right_action,right_event,right_description,role_id,right_status)";
    sql += " values(seq_roleright.nextval,'" + rrf.getRightName()+"','"+ rrf.getRightCode() + "','"+rrf.getRightAction() + "','"+rrf.getRightEvent()+ "'," ;
    sql += rrf.getRightDescription() + "'," + rrf.getSysRoleID() + ",1)";
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