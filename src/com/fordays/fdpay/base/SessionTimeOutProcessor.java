package com.fordays.fdpay.base;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.RequestProcessor;
import org.apache.struts.config.ModuleConfig;
import com.fordays.fdpay.right.UserRightInfo;
public class SessionTimeOutProcessor extends RequestProcessor
{

	public boolean processPreprocess(HttpServletRequest request,
	    HttpServletResponse response)
	{
	
		UserRightInfo uri;
		if (request.getSession().getAttribute("URI") != null)
			uri = (UserRightInfo) request.getSession().getAttribute("URI");
		else
			uri = null;
		String actionName = request.getQueryString();
		if(actionName==null)
			actionName="";
		String url = request.getServletPath() + "?" + actionName;
		String temp = "";
			if (uri == null || uri.getUser() == null)
			{
				try
				{
					for (int i = 0; i <Constant.url.size(); i++)
					{
						if(Constant.url.get(i).endsWith(".*"))
							temp = Constant.url.get(i).replace("*", "do");
						else
							temp = Constant.url.get(i).replace(".", ".do?thisAction=");
						
						if (url.indexOf(temp) >= 0)
							return true;
					}
					response.sendRedirect(request.getContextPath() + "/login.jsp");
					
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
				return false;
			}
			else
			{
			//	if(uri.hasRight(rightName, event))
				return true;
			}

	}

	@Override
  public void init(ActionServlet servlet, ModuleConfig moduleConfig)
      throws ServletException
  {
	  // TODO Auto-generated method stub
	  super.init(servlet, moduleConfig);
  }


	
}
