package com.fordays.fdpay.user.biz;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import com.fordays.fdpay.agent.Agent;
import com.fordays.fdpay.right.UserRightInfo;
import com.fordays.fdpay.system.SysLog;
import com.fordays.fdpay.system.dao.SysLogDAO;
import com.fordays.fdpay.user.SysUser;
import com.fordays.fdpay.user.UserListForm;
import com.fordays.fdpay.user.dao.UserDAO;
import com.neza.encrypt.MD5;
import com.neza.exception.AppException;

public class UserBizImp implements UserBiz {
	private UserDAO userDAO;
	private SysLogDAO sysLogDAO;
	private TransactionTemplate transactionTemplate;

	// 获取当前登录用户
	public SysUser getCurrentUser(HttpServletRequest request)
			throws AppException {
		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute(
				"URI");
		SysUser user = uri.getUser();
		return user;
	}

	public SysUser getUserById(long id) throws AppException {
		return userDAO.getUserById(id);
	}

	public List getUsers() throws AppException {
		return userDAO.list();
	}

	public List getUsers(UserListForm ulf) throws AppException {
		return userDAO.list(ulf);
	}

	public SysUser login(String userNo, String userPassword)
			throws AppException {
		return userDAO.login(userNo, userPassword);
	}
	public int checkUser(SysUser user, String password) throws AppException
	{
		int loginStatus = 0;
		SysUser tempUser = userDAO.getUserByNo(user);
		if (tempUser == null)
			loginStatus = 1; 
		else if (!tempUser.getUserNo().equalsIgnoreCase(user.getUserNo()))
		{
			loginStatus = 1; 
		}
		else
		{
			if (!tempUser.getUserPassword().equals(MD5.encrypt(password)))
			{
				loginStatus = 2; 
			}
		}
		return loginStatus;
	}

	public void saveUser(SysUser user) throws AppException {
		userDAO.save(user);
	}

	public long updateUserPassword(SysUser user) throws AppException {
		return 0;
	}

	public long _updateUserInfo(SysUser user) throws AppException {
		SysLog syslog = new SysLog();
		syslog.setLogContent("test transaction...");
		syslog.setLogDate(new Timestamp(System.currentTimeMillis()));
		syslog.setSysUser(user);
		sysLogDAO.save(syslog);

		user.setUserName("xxxxxxx");
		userDAO.update(user);

		return user.getUserId();
	}

	public long updateUserInfo(SysUser user) throws AppException {
		userDAO.save(user);
		return user.getUserId();
	}

	public long deleteUser(long id) throws AppException {
		try {
			if (sysLogDAO.getSysLogByUserId(id)) {
				return 0;
			} else {
				userDAO.deleteById(id);
				return 1;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	public boolean hasSameNo(String userNo) throws AppException {
		return false;
	}

	public void setSysLogDAO(SysLogDAO sysLogDAO) throws AppException {
		this.sysLogDAO = sysLogDAO;
	}

	public SysUser getUserByNo(SysUser user) throws AppException {
		return userDAO.getUserByNo(user);
	}

	public SysUser getUserByName(String userName) throws AppException {
		return userDAO.getUserByName(userName);
	}

	public void setTransactionManager(
			HibernateTransactionManager transactionManager) {
		this.transactionTemplate = new TransactionTemplate(transactionManager);
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
}
