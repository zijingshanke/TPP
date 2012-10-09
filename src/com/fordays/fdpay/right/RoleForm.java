package com.fordays.fdpay.right;


import org.apache.struts.action.ActionForm;
import com.neza.database.SelectDataBean;


public class RoleForm  extends ActionForm {

   private String roleID="";
   private String roleName="";
   private String roleKey="";
   private String roleDescription="";
   private String roleSystem="";
   private String thisAction="";  
 
	
	/**
	 * @return Returns the thisAction.
	 */
	public String getThisAction()
	{
		return thisAction;
	}

	/**
	 * @param thisAction The thisAction to set.
	 */
	public void setThisAction(String thisAction)
	{
		this.thisAction=thisAction;
	}


	public String getRoleID()
	{
		if(roleID.equals(""))
			roleID="-1";
		return roleID;
	}

	public void setRoleID(String roleID)
	{
		this.roleID = roleID;
	}

	/**
	 * @return Returns the roleName.
	 */
	public String getRoleName()
	{
		return roleName;
	}

	/**
	 * @param roleName The roleName to set.
	 */
	public void setRoleName(String roleName)
	{
		this.roleName=roleName;
	}

	public String getRoleKey()
  {
    return roleKey;
  }
  

  public void setRoleKey(String roleKey)
  {
    this.roleKey = roleKey;
  }
  



	public String getRoleDescription()
	{
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription)
	{
		this.roleDescription = roleDescription;
	}

	/**
	 * @return Returns the roleSystem.
	 */
	public String getRoleSystem()
	{
		return roleSystem;
	}

	/**
	 * @param roleSystem The roleSystem to set.
	 */
	public void setRoleSystem(String roleSystem)
	{
		this.roleSystem=roleSystem;
	}

	public String getRoleID(String roleKey)
	{
	  if (roleKey.trim().equals(""))
	    return "-1";
	  else
	  {
	      String sql = "select role_id from st_role where role_key='"+ roleKey+"' and role_status=1";
	      System.out.println("sql=" + sql);
	      SelectDataBean sdb = new SelectDataBean();
	      sdb.setQuerySQL(sql);
	      sdb.executeQuery();
        String roleID=sdb.getColValue(1,1);
	      if (sdb.getRowCount()>0 || !roleID.equals(""))
	        return roleID;
	      else
	        return "-1";
	  }	
	}

}
