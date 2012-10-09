package com.fordays.fdpay.security.biz;

import java.security.cert.X509Certificate;

import javax.servlet.http.HttpServletRequest;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import com.fordays.fdpay.agent.Agent;
import com.fordays.fdpay.agent.dao.AgentDAO;
import com.fordays.fdpay.user.dao.UserDAO;

import com.fordays.fdpay.security.CertificationForm;
import com.fordays.fdpay.user.SysUser;
import com.neza.exception.AppException;

public class _CertificationBizImpl {

  private AgentDAO agentDAO;
  private UserDAO userDAO;
  private TransactionTemplate transactionTemplate;

	public void setTransactionManager(
			HibernateTransactionManager transactionManager) {
		this.transactionTemplate = new TransactionTemplate(transactionManager);
	}

	public boolean validateAgent(HttpServletRequest request,CertificationForm certificationForm) throws AppException
	{
		try
		{
			Agent agent=agentDAO.getAgentByEmail(certificationForm.getEmail());
			String certAttribute = "javax.servlet.request.X509Certificate";
			X509Certificate[] certificates = (X509Certificate[]) request
			    .getAttribute(certAttribute);
			
	
			if ("https".equals(request.getScheme()))
			{
				if (certificates != null)
				{
					String temp=certificates[0].toString();
					System.out.println("--------------------"+temp);
					if(temp.indexOf(agent.getEmail())>0 && temp.indexOf(agent.getCertInfo().getSerialNo())>0)
						return true;
				}
				else
				{
					return false;
				}
			}
			else
			{
				return false;
			}
		}
		catch (Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		return false;
	}

	public boolean validateUser(HttpServletRequest request,long userId) throws AppException
	{
		try
		{
			System.out.println("-------------------getScheme-"+request.getScheme());

			SysUser user=userDAO.getUserById(userId);
					
			System.out.println("-------------------getUserEmail-"+user.getUserEmail());
			
			String certAttribute = "javax.servlet.request.X509Certificate";
			X509Certificate[] certificates = (X509Certificate[]) request
			    .getAttribute(certAttribute);
			System.out.println("-------------------certificates-"+certificates);
	
			if ("https".equalsIgnoreCase(request.getScheme()))
			{
				System.out.println("-------------------2222222222222-");
				if (certificates != null)
				{					
					String temp=certificates[0].toString();
					System.out.println("--------------------"+temp);
					System.out.println("------------------getSerialNumber="+certificates[0].getSerialNumber());
					System.out.println("------------------getIssuerDN="+certificates[0].getIssuerDN());
					System.out.println("-------------------temp.indexOf(user.getUserEmail())>0="+(temp.indexOf(user.getUserEmail())>0));
					System.out.println("-------------------temp.indexOf(user.getSerialNumber())>0="+( user.getSerialNumber().trim().equalsIgnoreCase(certificates[0].getSerialNumber().toString(16))));
					if(temp.indexOf(user.getUserEmail())>0 && user.getSerialNumber().trim().equalsIgnoreCase(certificates[0].getSerialNumber().toString(16)));
						return true;
				}
				else
				{
					System.out.println("------------3333-------certificates-"+certificates);
					return false;
				}
			}
			else
			{
				return false;
			}
		}
		catch (Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		return false;
	}

	public void setAgentDAO(AgentDAO agentDAO)
  {
  	this.agentDAO = agentDAO;
  }

	public void setUserDAO(UserDAO userDAO)
  {
  	this.userDAO = userDAO;
  }

}
