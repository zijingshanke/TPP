package com.fordays.fdpay.user.action;

import java.security.cert.X509Certificate;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.fordays.fdpay.right.UserRightInfo;
import com.fordays.fdpay.right.biz.RightBiz;
import com.fordays.fdpay.security.biz.CertificationBiz;
import com.fordays.fdpay.system.LoginLog;
import com.fordays.fdpay.system.biz.LoginLogBiz;
import com.fordays.fdpay.transaction.biz.DrawBiz;
import com.fordays.fdpay.user.SysUser;
import com.fordays.fdpay.user.biz.UserBiz;
import com.neza.base.BaseAction;
import com.neza.base.Inform;
import com.neza.encrypt.MD5;
import com.neza.exception.AppException;

public class UserAction extends BaseAction
{
	private UserBiz userBiz;
	private RightBiz rightBiz;
	private LoginLogBiz loginlogBiz;
	private CertificationBiz certificationBiz;
	private boolean needCertfication;

	public void setNeedCertfication(boolean needCertfication)
	{
		this.needCertfication = needCertfication;
	}

	public void setCertificationBiz(CertificationBiz certificationBiz)
	{
		this.certificationBiz = certificationBiz;
	}

	public void setLoginlogBiz(LoginLogBiz loginlogBiz)
	{
		this.loginlogBiz = loginlogBiz;
	}

	private DrawBiz drawBiz;

	public void setUserBiz(UserBiz userBiz)
	{
		this.userBiz = userBiz;
	}

	public SysUser getUserByURI(HttpServletRequest request)
	{
		UserRightInfo uri = (UserRightInfo) request.getSession()
		    .getAttribute("URI");
		if (uri != null && uri.getUser() != null)
			return uri.getUser();
		else
		{
			return null;
		}
	}

	public ActionForward update(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws AppException
	{

		String forwardPage = "";
		SysUser user = (SysUser) form;
		Inform inf = new Inform();
		try
		{
			SysUser tempUser = (SysUser) userBiz.getUserById(user.getUserId());
			tempUser.setUserName(user.getUserName());
			tempUser.setUserNo(user.getUserNo());
			// tempUser.setUserType(user.getUserType());
			tempUser.setSerialNumber(user.getSerialNumber());
			tempUser.setUserEmail(user.getUserEmail());
			tempUser.setUserStatus(user.getUserStatus());
			userBiz.updateUserInfo(tempUser);
			request.setAttribute("user", tempUser);

			inf.setMessage("您已经成功更新了用户资料！");
			inf.setForwardPage("/user/userlist.do");
			inf.setParamId("thisAction");
			inf.setParamValue("list");

		}
		catch (Exception ex)
		{
			inf.setMessage("更新用户资料出错！错误信息是：" + ex.getMessage());
			inf.setBack(true);
		}
		request.setAttribute("inf", inf);
		forwardPage = "inform";
		return (mapping.findForward(forwardPage));
	}

	public ActionForward editPassword(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws AppException
	{
		String forwardPage = "";
		SysUser u = (SysUser) form;
		SysUser user = (SysUser) userBiz.getUserById(u.getUserId());
		user.setThisAction("updatePassword");
		request.setAttribute("user", user);
		forwardPage = "edituserpassword";

		return (mapping.findForward(forwardPage));
	}

	public ActionForward editMyPassword(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws AppException
	{
		String forwardPage = "";
		SysUser u = (SysUser) form;
		SysUser user = userBiz.getUserById(u.getUserId());
		user.setThisAction("updatePassword");
		request.setAttribute("user", user);
		forwardPage = "editmypassword";

		return (mapping.findForward(forwardPage));
	}

	public ActionForward updatePassword(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws AppException
	{

		String forwardPage = "";
		SysUser user = (SysUser) form;
		Inform inf = new Inform();
		try
		{
			SysUser tempUser = userBiz.getUserById(user.getUserId());
			tempUser.setUserPassword(MD5.encrypt(user.getUserPassword1()));
			userBiz.updateUserInfo(tempUser);
			request.setAttribute("user", tempUser);

			inf.setMessage("您已经成功修改了用户的密码！");
			inf.setForwardPage("/user/userlist.do?thisAction=list");
			inf.setParamValue("list");

		}
		catch (Exception ex)
		{
			inf.setMessage("更新用户资料出错！错误信息是：" + ex.getMessage());
			inf.setBack(true);
		}

		request.setAttribute("inf", inf);
		forwardPage = "inform";
		return (mapping.findForward(forwardPage));
	}

	public ActionForward updateMyPassword(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws AppException
	{

		String forwardPage = "";
		SysUser user = (SysUser) form;
		Inform inf = new Inform();
		SysUser use = this.getUserByURI(request);
		if (use != null)
		{
			if (!MD5.encrypt(user.getUserPassword()).equals(use.getUserPassword()))
			{
				inf.setMessage("原密码输入错误！");
				inf.setBack(true);
			}
			else
			{
				try
				{
					SysUser tempUser = (SysUser) userBiz.getUserById(use.getUserId());
					tempUser.setUserNo(user.getUserNo());
					tempUser.setUserPassword(MD5.encrypt(user.getUserPassword1()));
					userBiz.updateUserInfo(tempUser);
					request.setAttribute("user", tempUser);

					inf.setMessage("您已经成功修改了用户的密码！返回登陆！");
					inf.setForwardPage("/user/user.do?thisAction=exit");
					inf.setParamValue("list");

				}
				catch (Exception ex)
				{
					inf.setMessage("更新用户资料出错！错误信息是：" + ex.getMessage());
					inf.setBack(true);
				}
			}
		}
		else
		{
			request.getSession().invalidate();
			return mapping.findForward("exit");
		}
		request.setAttribute("inf", inf);
		forwardPage = "inform";
		return (mapping.findForward(forwardPage));
	}

	public ActionForward insert(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws AppException
	{
		String forwardPage = "";
		SysUser user = (SysUser) form;
		Inform inf = new Inform();
		try
		{
			SysUser tempUser = new SysUser();
			tempUser.setUserName(user.getUserName());
			tempUser.setUserNo(user.getUserNo());
			tempUser.setUserStatus(user.getUserStatus());
			tempUser.setSerialNumber(user.getSerialNumber());
			tempUser.setUserEmail(user.getUserEmail());
			tempUser.setUserType(new Long(1));
			userBiz.saveUser(tempUser);

			request.setAttribute("user", tempUser);
			inf.setMessage("成功增加了用户！现在为该用户设置密码！");
			inf.setForwardPage("/user/user.do?thisAction=editPassword&userId="
			    + tempUser.getUserId());

		}
		catch (Exception ex)
		{
			inf.setMessage("增加用户出错！错误信息是：" + ex.getMessage());
			inf.setBack(true);
		}
		request.setAttribute("inf", inf);
		forwardPage = "inform";
		return (mapping.findForward(forwardPage));
	}

	// 登录!
	public ActionForward login(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws AppException
	{
		SysUser user = (SysUser) form;

		if (request.getSession().getAttribute("rand") != null
		    && user.getRand().toString().equals(
		        request.getSession().getAttribute("rand").toString()))

		{
			SysUser tempUser = userBiz
			    .login(user.getUserNo(), user.getUserPassword());

			if (tempUser != null && tempUser.getUserId() > 0)
			{

				if (tempUser.getUserStatus() == 2)
				{
					request.setAttribute("err", "statusError");
					return mapping.findForward("login");
				}
				else if (tempUser.getUserStatus() == 1)
				{
					System.out.println("---------------------------------验证身份前："
					    + certificationBiz);
					boolean flag = certificationBiz.validateUser(request, tempUser);
					System.out.println("---------------------------------验证身份结果：" + flag);
					if (!flag && needCertfication)
					{
						request.setAttribute("err", "validError");
						return mapping.findForward("login");
					}

					LoginLog loginlog = new LoginLog();
					loginlog.setIp(request.getRemoteAddr());
					loginlog.setLocate(new Long(2));
					loginlog.setStatus(new Long(1));
					loginlog.setLoginDate(new Timestamp(System.currentTimeMillis()));
					loginlog.setLoginName(tempUser.getUserNo());
					loginlogBiz.saveLoginLog(loginlog);
					UserRightInfo uri = new UserRightInfo();
					uri.setUser(tempUser);
					rightBiz.setRights(uri, tempUser.getUserId());
					request.getSession().setAttribute("URI", uri);
					return mapping.findForward("index");
				}
				return null;
			}
			else
			{
				LoginLog loginlog = new LoginLog();
				loginlog.setIp(request.getRemoteAddr());
				loginlog.setLocate(new Long(2));
				loginlog.setStatus(new Long(0));
				loginlog.setLoginDate(new Timestamp(System.currentTimeMillis()));
				loginlog.setLoginName(user.getUserName());
				loginlogBiz.saveLoginLog(loginlog);

				int i = userBiz.checkUser(user, user.getUserPassword());
				if (i == 1)
				{
					request.setAttribute("err", "nameError");
					return mapping.findForward("login");
				}
				else if (i == 2)
				{
					request.setAttribute("err", "passError");
					return mapping.findForward("login");
				}
				return null;
			}
		}
		else
		{
			request.setAttribute("err", "randError");
			return mapping.findForward("login");
		}

	}

	public ActionForward exit(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws AppException
	{
		request.removeAttribute("URI");
		request.getSession().invalidate();
		return mapping.findForward("exit");
	}

	public void validateUser(HttpServletRequest request, long userId)
	    throws AppException
	{
		try
		{
			System.out.println("-------------0------getScheme-" + request.getScheme());
			String certAttribute = "javax.servlet.request.X509Certificate";
			X509Certificate[] certificates = (X509Certificate[]) request
			    .getAttribute(certAttribute);
			System.out.println("------------0-------certificates-" + certificates);

			if ("https".equalsIgnoreCase(request.getScheme()))
			{
				System.out.println("-----------0--------2222222222222-");
				if (certificates != null)
				{
					String temp = certificates[0].toString();
					System.out.println("----------0----------" + temp);

				}
				else
				{
					System.out.println("------------2222222222-------certificates-"
					    + certificates);
				}
			}

		}
		catch (Exception ex)
		{
			System.out.println(ex.getMessage());
		}

	}

	public DrawBiz getDrawBiz()
	{
		return drawBiz;
	}

	public void setDrawBiz(DrawBiz drawBiz)
	{
		this.drawBiz = drawBiz;
	}

	public void setRightBiz(RightBiz rightBiz)
	{
		this.rightBiz = rightBiz;
	}

}