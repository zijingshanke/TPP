package com.fordays.fdpay.right.biz;

import java.util.List;

import com.fordays.fdpay.right.RoleRight;
import com.fordays.fdpay.right.UserRightInfo;
import com.fordays.fdpay.right.dao.RightDAO;
import com.neza.database.SelectDataBean;
import com.neza.exception.AppException;

public class RightBizImpl implements RightBiz
{

	private RightDAO rightDAO;

	public void setRightDAO(RightDAO rightDAO)
	{
		this.rightDAO = rightDAO;
	}

	public void setRights(UserRightInfo uri, long userId) throws AppException
	{
		List list = rightDAO.listRoleRightsByUserId(userId);
		long b = System.currentTimeMillis();

		for (int i = 0; i < list.size(); i++)
		{
			RoleRight rr = (RoleRight) list.get(i);
	//		if (rr.getRightCode().equals("se01"))
		//		System.out.println("---------00-------------------------4.3==========");
			if (rr.getRightCode().equals("sa01"))
			{
				List list1 = rightDAO.listRoleRights();
				uri.clear();
				for (int j = 0; j < list1.size(); j++)
				{
					RoleRight rrx = (RoleRight) list1.get(i);
					uri.addRight(rrx.getRightCode(), rrx.getRightAction());
				}
				return;
			}
			uri.addRight(rr.getRightCode(), rr.getRightAction());
		}


	}

	
}
