package com.fordays.fdpay.security.biz;

import java.security.cert.X509Certificate;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import com.fordays.fdpay.agent.Agent;
import com.fordays.fdpay.agent.CertInfo;
import com.fordays.fdpay.agent.dao.AgentDAO;

import com.fordays.fdpay.security.Certification;
import com.fordays.fdpay.security.CertificationForm;
import com.fordays.fdpay.system.TaskTimestamp;
import com.fordays.fdpay.system.dao.TaskTimestampDAO;
import com.fordays.fdpay.user.SysUser;
import com.neza.exception.AppException;

public class CertificationBizImpl implements CertificationBiz
{

	private AgentDAO agentDAO;
	private TaskTimestampDAO tasktimestampDAO;
	private TransactionTemplate transactionTemplate;

	public void setTransactionManager(
	    HibernateTransactionManager transactionManager)
	{
		this.transactionTemplate = new TransactionTemplate(transactionManager);
	}

	public boolean validateAgent(HttpServletRequest request,
	    CertificationForm certificationForm) throws AppException
	{
		try
		{
			String email = certificationForm.getEmail();
			Agent agent = agentDAO.getAgentByEmail(email);

			String certAttribute = "javax.servlet.request.X509Certificate";
			X509Certificate[] certificates = (X509Certificate[]) request
			    .getAttribute(certAttribute);
			System.out.println("---------validateAgent---email------=" + email);

			String temp = certificates[0].getSubjectDN().toString();
			System.out.println("---------validateAgent---------getSerialNumber="
			    + temp);
			System.out.println("---------validateAgent---------agent.getCertInfo().getSerialNo()="
			    + agent.getCertInfo().getSerialNo());
			System.out.println("---------validateAgent---------certificates[0].getSerialNumber().toString(16)="
			    + certificates[0].getSerialNumber().toString(16));
			
			if (certificates != null)
			{
				
				if (temp.indexOf(email) >= 0
				    && agent.getCertInfo().getSerialNo().equalsIgnoreCase(
				        certificates[0].getSerialNumber().toString(16)))					
				{
					CertInfo certInfo=agent.getCertInfo();
					Certification.getX509Certificate(certInfo).getPublicKey().equals(certificates[0].getPublicKey());
					TaskTimestamp tt=new TaskTimestamp();
					tt.setAgent(agent);
					tt.setTaskDate(new Timestamp(System.currentTimeMillis()));
					tt.setCount(new Long(1));
					tt.setTaskType(TaskTimestamp.type_9);
					tt.setStatus(new Long(1));
					tt.setTaskHour(new Long(1));
					tasktimestampDAO.save(tt);
					return true;
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

	public boolean validateUser(HttpServletRequest request, SysUser user)
	    throws AppException
	{
		try
		{

			String certAttribute = "javax.servlet.request.X509Certificate";
			X509Certificate[] certificates = (X509Certificate[]) request
			    .getAttribute(certAttribute);

			if (certificates != null && certificates.length > 0)
			{
				String temp = certificates[0].getSubjectDN().toString();
				System.out.println("---------1--------cert-getSubjectDN="
				    + certificates[0].getSubjectDN());

				System.out.println("---------1------cert---getSerialNumber="
				    + certificates[0].getSerialNumber().toString(16));
				System.out.println("---------1---------user.getSerialNumber="
				    + user.getSerialNumber());

				System.out.println("---------1---------valid="
				    +( temp.indexOf(user.getUserEmail()) > 0
				    && user.getSerialNumber().equalsIgnoreCase(
				        certificates[0].getSerialNumber().toString(16))));
				if (temp.indexOf(user.getUserEmail()) > 0
				    && user.getSerialNumber().equalsIgnoreCase(
				        certificates[0].getSerialNumber().toString(16)))

					return true;
				else
					
					System.out.println("-非法身份登录-----------");
			}
			else
			{
				System.out.println("-身份验证不登录-----------");
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

	public void setTasktimestampDAO(TaskTimestampDAO tasktimestampDAO)
  {
  	this.tasktimestampDAO = tasktimestampDAO;
  }
 

}
