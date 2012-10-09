package com.fordays.fdpay.watch.action;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.fordays.fdpay.watch.biz.WatchBiz;
import com.neza.base.BaseAction;
import com.neza.base.Inform;
import com.neza.encrypt.MD5;
import com.neza.exception.AppException;

public class WatchAction extends BaseAction {
	private WatchBiz watchBiz;

	public void setWatchBiz(WatchBiz watchBiz) {
		this.watchBiz = watchBiz;
	}

	public ActionForward getExceptionalAgents(ActionMapping mapping,
			ActionForm from, HttpServletRequest request,
			HttpServletResponse response) {
		PrintWriter out=null;
		try {
		  out=response.getWriter();
			String data_xml=watchBiz.getExceptionalAgents();
			out.print(data_xml);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.print("none");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.print("none");
		}
		return null;
	}
	
	public ActionForward getRepeatCharge(ActionMapping mapping,
			ActionForm from, HttpServletRequest request,
			HttpServletResponse response) {
		PrintWriter out=null;
		try {
		  out=response.getWriter();
			String data_xml=watchBiz.getRepeatCharges();
			out.print(data_xml);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.print("none");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.print("none");
		}
		return null;
	}
}