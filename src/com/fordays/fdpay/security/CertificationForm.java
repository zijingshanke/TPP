package com.fordays.fdpay.security;

public class CertificationForm extends org.apache.struts.action.ActionForm    
{
	private static final long serialVersionUID = 1L;
	private String cmd="";
	private String name="";
	private String email="";
	private String password="";
	private String thisAction="";
	private String type="";
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName()
  {
  	return name;
  }
	public void setName(String name)
  {
  	this.name = name;
  }


	 public String getEmail()
  {
  	return email;
  }
	public void setEmail(String email)
  {
  	this.email = email;
  }
	public String getThisAction()
	 {
	     return thisAction;
	 }


	public String getCmd()
  {
  	return cmd;
  }
	public void setCmd(String cmd)
  {
  	this.cmd = cmd;
  }
	public void setThisAction(String thisAction)
	 {
	     this.thisAction=thisAction;
	 }
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
