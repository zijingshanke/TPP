package com.fordays.fdpay.right.dao;

import java.util.List;

import com.neza.base.BaseDAOSupport;
import com.neza.exception.AppException;

public class RightDAOImpl extends BaseDAOSupport implements RightDAO
{
	public RightDAOImpl()
	{
	}

	public List listRoleRights() throws AppException
	{
		String hql = "from RoleRight";
		return this.list(hql);
	}
	
	public List listRoleRightsByUserId(long userId) throws AppException
	{
		String hql ="from RoleRight where rightKey in (select rightKey from UserRoleRight where roleId in (select roleId from UserRole where userId ="+userId+"))";
		return this.list(hql);
	}
	
}
