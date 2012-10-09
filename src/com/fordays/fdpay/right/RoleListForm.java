package com.fordays.fdpay.right;


import javax.servlet.ServletRequest;

import org.apache.struts.action.ActionMapping;

import com.neza.base.ListActionForm;
import com.neza.utility.StringUtil;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * 
 * </p>
 * 
 * @author LIGE
 * @version 1.0
 */

public class RoleListForm extends ListActionForm
{
	private String userRoleID = "";
	private String sysRoleID = "";
	private String userID = "";
	private String[] leftUserID = new String[0];
	private String[] rightUserID = new String[0];
	private String[] leftRoleID = new String[0];
	private String[] rightRoleID = new String[0];
	private String userCode = "";
	private String userKey = "";
	private String subsatId = "";
	private String temp = "";
	private String userIDs = "";
	private String roleName = "";
	private String[] selectedRoleItems = new String[0];
	private String[] selectedRightItems = new String[0];
	private String selectedRightItemsStr="";
	private int selectedRightItem = -1;
	private String xml ="";
	private int count =0;

	public String getRoleName()
	{
		return roleName;
	}

	/**
	 * @param roleName
	 *          The roleName to set.
	 */
	public void setRoleName(String roleName)
	{
		this.roleName = roleName;
	}

	/**
	 * @return Returns the selectedRightItems.
	 */
	public String[] getSelectedRightItems()
	{
		return selectedRightItems;
	}

	/**
	 * @param selectedRightItems
	 *          The selectedRightItems to set.
	 */
	public void setSelectedRightItems(String[] selectedRightItems)
	{
		this.selectedRightItems = selectedRightItems;
		this.selectedRightItemsStr =StringUtil.convert(selectedRightItems);
	}

	/**
	 * @return Returns the selectedRoleItems.
	 */
	public String[] getSelectedRoleItems()
	{
		return selectedRoleItems;
	}

	/**
	 * @param selectedRoleItems
	 *          The selectedRoleItems to set.
	 */
	public void setSelectedRoleItems(String[] selectedRoleItems)
	{
		this.selectedRoleItems = selectedRoleItems;

	}

	/**
	 * @return Returns the userID.
	 */
	public String getUserID()
	{
		if (userID.equals(""))
			userID = "-1";
		return userID;
	}

	/**
	 * @param userID
	 *          The userID to set.
	 */
	public void setUserID(String userID)
	{
		this.userID = userID;
	}

	public int getSelectedRightItem()
	{
		return selectedRightItem;
	}

	public String getSysRoleID()
	{
		if (sysRoleID.equals(""))
			sysRoleID = "-1";
		return sysRoleID;
	}

	public void setSysRoleID(String sysRoleID)
	{
		this.sysRoleID = sysRoleID;
	}

	public String getUserRoleID()
	{
		if (userRoleID.equals(""))
			userRoleID = "-1";

		return userRoleID;
	}

	public void setUserRoleID(String userRoleID)
	{
		this.userRoleID = userRoleID;
	}

	public void setSelectedRightItem(int selectedRightItem)
	{
		this.selectedRightItem = selectedRightItem;
	}

	public String getUserKey()
	{
		return userKey;
	}

	public void setUserKey(String userKey)
	{
		this.userKey = userKey;
	}

	public String getUserIDs()
	{
		return userIDs;
	}

	public void setUserIDs(String userIDs)
	{
		this.userIDs = userIDs;
	}

	public String getTemp()
	{
		return temp;
	}

	public void setTemp(String temp)
	{
		this.temp = temp;
	}

	public String getUserCode()
	{
		return userCode;
	}

	public void setUserCode(String userCode)
	{
		this.userCode = userCode;
	}

	public String getXml()
	{
		return xml;
	}

	public void setXml(String xml)
	{
		this.xml = xml;
	}

	public String getSelectedRightItemsStr()
	{
		return selectedRightItemsStr;
	}

	public void setSelectedRightItemsStr(String selectedRightItemsStr)
	{
		this.selectedRightItemsStr = selectedRightItemsStr;
	}

	public String getSubsatId()
	{
		return subsatId;
	}

	public void setSubsatId(String subsatId)
	{
		this.subsatId = subsatId;
	}

	public String[] getLeftUserID()
	{
		return leftUserID;
	}

	public void setLeftUserID(String[] leftUserID)
	{
		this.leftUserID = leftUserID;
	}

	public String[] getRightUserID()
	{
		return rightUserID;
	}

	public void setRightUserID(String[] rightUserID)
	{
		this.rightUserID = rightUserID;
	}

	public String[] getLeftRoleID()
	{
		return leftRoleID;
	}

	public void setLeftRoleID(String[] leftRoleID)
	{
		this.leftRoleID = leftRoleID;
	}

	public String[] getRightRoleID()
	{
		return rightRoleID;
	}

	public void setRightRoleID(String[] rightRoleID)
	{
		this.rightRoleID = rightRoleID;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void reset(ActionMapping mapping, ServletRequest request)
	{
		// TODO Auto-generated method stub
		super.reset(mapping, request);
		userRoleID = "";
		sysRoleID = "";
		userID = "";
		userCode = "";
		userKey = "";
		temp = "";
		userIDs = "";
		roleName = "";
		selectedRoleItems = new String[0];
		selectedRightItems = new String[0];
		selectedRightItemsStr="";
		selectedRightItem = -1;
		subsatId="";
		xml ="";
	}

}
