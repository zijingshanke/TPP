package com.fordays.fdpay.security.action;

import org.apache.struts.action.*;

import com.fordays.fdpay.agent.Agent;
import com.fordays.fdpay.agent.CertInfo;
import com.fordays.fdpay.agent.biz.AgentBiz;
import com.fordays.fdpay.security.Certification;
import com.fordays.fdpay.security.CertificationForm;
import com.fordays.fdpay.security.biz.CertificationBiz;
import com.neza.base.BaseAction;
import com.neza.encrypt.BASE64;
import com.neza.exception.AppException;
import com.neza.tool.CmdUtil;
import com.neza.utility.RegexUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import java.security.cert.X509Certificate;
import javax.servlet.http.*;

public class CertificateAction extends BaseAction
{

	CertificationBiz certificationBiz;
	AgentBiz agentBiz;

	public void setCertificationBiz(CertificationBiz certificationBiz)
	{
		this.certificationBiz = certificationBiz;
	}

	public void setAgentBiz(AgentBiz agentBiz)
	{
		this.agentBiz = agentBiz;
	}
	
	public static String BASE640(String baseString) throws UnsupportedEncodingException{
		
		if(baseString.length()>0 && baseString.endsWith("0"))
		{
//			System.out.println(BASE64.encrypt("276628@qq.com"));
			String dencriyptString=baseString.substring(0, baseString.length()-1);
			return BASE64.dencrypt(dencriyptString, "UTF-8");
		}
		else
			return BASE64.dencrypt(baseString, "UTF-8");
	}
	

	public ActionForward execIkeymanCmd(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws AppException
	{
		try
		{
			CertificationForm certificationForm = (CertificationForm) form;
			String cmd = certificationForm.getCmd();
			String queryString =Certification.getQueryString(request.getParameterMap());
			if(Certification.validateSignUrl(queryString)){
				if (cmd != null)
				{
					String baseString = java.net.URLDecoder.decode(cmd, "UTF-8");
					cmd = BASE640(baseString);
				}
				String result = CmdUtil.exec(cmd);
				response.getWriter().write(BASE64.encrypt(result, "UTF-8"));

			}else{
				System.out.println("签名验证失败!");
			}

		}
		catch (Exception ex)
		{
			System.out.println(ex.getMessage());
			try
			{
				response.getWriter().write(BASE64.encrypt(ex.getMessage(), "UTF-8"));
			}
			catch (Exception ex2)
			{

			}
		}
		return null;
	}

	public ActionForward getSerialNumber(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws AppException
	{
		try
		{
			CertificationForm certificationForm = (CertificationForm) form;

			String email = certificationForm.getEmail();
			String queryString =Certification.getQueryString(request.getParameterMap());

			if(Certification.validateSignUrl(queryString)){
				if (email != null)
				{
					String baseString = java.net.URLDecoder.decode(email, "UTF-8");
					email = BASE640(baseString);
				}
				
			}else{
				System.out.println("签名验证失败!");
			}
			System.out
		    .println("----------------x509.getSerialNumber()-----email-----"
		        + email+",agentBiz="+agentBiz);

			Agent agent = agentBiz.getAgentByEmail(email);
			
			System.out
			    .println("----------------x509.getSerialNumber()-----agent-----"
			        + agent);
			System.out
	    .println("-------------------agent.getCertInfo().getId()-----"
	        + agent.getCertInfo().getId());
			
			CertInfo certInfo = agentBiz.getCertById(agent.getCertInfo().getId());
			System.out
			    .println("----------------x509.getSerialNumber()-----agent.getCertInfo()-----"
			        + certInfo);
			System.out
			    .println("----------------x509.getSerialNumber()-----agent.getCertInfo().password-----"
			        + certInfo.getPassword());

			if (agent != null && agent.getCertInfo() != null)
			{
				X509Certificate x509 = Certification.getX509Certificate(certInfo);
				System.out.println("----------------x509---------" + x509);
				if (x509 != null)
				{
					System.out.println("----------------x509.getSerialNumber()----------"
					    + x509.getSerialNumber());
					response.getWriter().write(
					    BASE64.encrypt(x509.getSerialNumber().toString(16).toUpperCase(), "UTF-8"));
				}
				else
				{
					System.out.println("--------------加载证书失败----------");
					response.getWriter().write(BASE64.encrypt("加载证书失败！", "UTF-8"));
				}
			}
		}
		catch (Exception ex)
		{
			System.out.println(ex.getMessage());
			try
			{
				response.getWriter().write(BASE64.encrypt(ex.getMessage()));
			}
			catch (Exception ex2)
			{

			}
		}
		return null;
	}

	
	public ActionForward getRootCertInfo(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws AppException
	{
		try
		{
			String queryString =Certification.getQueryString(request.getParameterMap());
			System.out.println(Certification.validateSignUrl(queryString));
			if(Certification.validateSignUrl(queryString)){
				String root=Certification.getRootCertInfo();
				response.getWriter().write(root);
				
			}else{
				System.out.println("签名验证失败!");
			}
		}
		catch (Exception ex)
		{
			System.out.println(ex.getMessage());
			try
			{
				response.getWriter().write(ex.getMessage());
			}
			catch (Exception ex2)
			{

			}
		}
		return null;
	}
	
	
	public ActionForward validAgent(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws AppException
	{
		try
		{
			CertificationForm certificationForm = (CertificationForm) form;
		
			if ("https".equals(request.getScheme()))
			{
				boolean flag = certificationBiz.validateAgent(request, certificationForm);
				if (flag)
				{
					
					response.getWriter().write("1");
				}
				else
				{
					response.getWriter().write("0");
				}
			}
			else
			{
				response.getWriter().write("0");
			}
		}
		catch (Exception ex)
		{
			System.out.println(ex.getMessage());
			try
			{
				response.getWriter().write(ex.getMessage());
			}
			catch (Exception ex2)
			{

			}
		}
		return null;
	}

	/**
	 * 管理证书
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws AppException
	 */
	public ActionForward manageCertification(ActionMapping mapping,
	    ActionForm form, HttpServletRequest request, HttpServletResponse response)
	    throws AppException
	{
		CertificationForm certificationForm = (CertificationForm) form;
		// request.getParameter(arg0)
		String type = certificationForm.getType();
		CertInfo certInfo = new CertInfo();
		String certName = certificationForm.getName();
		String certEmail = certificationForm.getEmail();
		String certPwd = certificationForm.getPassword();
		certInfo.setCertName(certName);
		certInfo.setEmail(certEmail);
		certInfo.setPassword(certPwd);
		System.out.println("姓名：" + certName + " -邮箱：" + certEmail + " -密码："
		    + certPwd);
		String message = "";
		try
		{
			if (!"".equals(type))
			{

				if (type.equals("generate"))
				{
					message = Certification.reqPersonCert(certInfo);// 请求生成个人证书
					request.setAttribute("msg", "请求生成个人证书" + message);
					return mapping.findForward("manageCertification");
				}
				if (type.equals("sign"))
				{
					message = Certification.signPersonCert(certInfo); // 签发
					request.setAttribute("msg", "签发个人证书！" + message);
					return mapping.findForward("manageCertification");
				}

				if (type.equals("receive"))
				{
					message = Certification.receivePersonCert(certInfo);// 接收
					request.setAttribute("msg", "接收个人证书！" + message);
					return mapping.findForward("manageCertification");
				}

				if (type.equals("revoke"))
				{
					message = Certification.deletePersonCert(certInfo);// 吊销
					request.setAttribute("msg", "吊销个人证书！" + message);
					return mapping.findForward("manageCertification");
				}

				if (type.equals("export"))
				{
					// File file = Certification.getP12Cert(certInfo); // 导出
					// Certification.getP12Cert(certInfo);
					// 采用从导出的cer文件中获取证书信息 start
					// String fileName="E:\\lige@qq.com.p12";
					// File file=new File(fileName);
					// CertificateFactory cf = CertificateFactory
					// .getInstance("X.509");
					// FileInputStream fis =new FileInputStream(file);
					// Certificate c = cf.generateCertificate(fis);//
					// fis.close();
					// byte[] encod2 = c.getEncoded();
					// X509CertImpl cimp2 = new X509CertImpl(encod2);
					// X509CertInfo cinfo_second = (X509CertInfo) cimp2
					// .get(X509CertImpl.NAME + "." + X509CertImpl.INFO);
					// System.out.println("证书信息："+cinfo_second.toString());
					// end
					// String s=c.toString();
					// System.out.println("证书信息："+s);
					// this.ReadP12Cert(file, certInfo);
					// request.setAttribute("msg", "导出个人证书！" + file);
					return mapping.findForward("autoInstall");
				}

			}
		}
		catch (Exception ex)
		{
			String exceptionMsg = ex.getMessage();
			if (!"".equals(exceptionMsg))
			{
				request.setAttribute("exceptionMsg", "管理证书类型异常 " + type + "："
				    + exceptionMsg);
			}

		}
		return null;

	}

	public String downloadP12(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws AppException
	{
		try
		{

			CertificationForm certificationForm = (CertificationForm) form;
			String email = certificationForm.getEmail();
			String queryString =Certification.getSortQueryString(request.getQueryString());
			System.out.println(queryString);

			if(Certification.validateSignUrl(queryString)){
				if (email != null)
				{
					
					String baseString = java.net.URLDecoder.decode(email, "UTF-8");
					email = BASE640(baseString);
					System.out.println("email===" + email);
				}
				if (email == null || !RegexUtil.isEMail(email))
				{
					response.getWriter().write(BASE64.encrypt("邮箱格式不对!"));
					return null;
				}
				
			}else{
				System.out.println("签名验证失败!");
			}
					

			File file = new File(Certification.CERT_ROOT + "/personal/" + email
			    + ".p12");
			// File file = new File("c:\\"+email + ".p12");
			System.out.println("路径：===" + Certification.CERT_ROOT + "/personal/"
			    + email + ".p12");

			System.out.println("file.getName()===" + file.getName());
			if (file.exists())
			{
				System.out.println("file.canRead()===" + file.canRead());
				FileInputStream bis = new FileInputStream(file);
				response.reset();
				response.setHeader("Pragma", "public");
				response.setHeader("Cache-Control", "max-age=30");
				response.setHeader("Content-disposition", "attachment;filename="
				    + java.net.URLEncoder.encode(email + ".p12", "UTF-8"));
				response.setContentType("application/p12");

				// response.setContentType("");
				System.out.println("response.getContentType()="
				    + response.getContentType());

				OutputStream bos = response.getOutputStream();
				byte[] buff = new byte[1024];
				int readCount = 0;
				int i = 0;
				readCount = bis.read(buff);
				while (readCount != -1)
				{
					bos.write(buff, 0, readCount);
					readCount = bis.read(buff);
				}
				if (bis != null)
					bis.close();
				if (bos != null)
					bos.close();

				return null;
			}
			else
			{
				response.getWriter().write(BASE64.encrypt("证书文件不存在!"));
			}

		}
		catch (Exception ex)
		{
			System.out.println(ex.getMessage());
			try
			{
				response.getWriter().write(BASE64.encrypt(ex.getMessage()));
			}
			catch (Exception ex2)
			{

			}
		}
		return null;

	}

	public static void main(String[] args)
	{
		// ca.ReadP12Cert(new File("c:\\Ling9514@126.com.p12"), null);
	}
}
