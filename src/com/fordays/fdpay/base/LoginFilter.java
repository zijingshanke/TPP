package com.fordays.fdpay.base;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fordays.fdpay.right.UserRightInfo;
import com.fordays.fdpay.user.SysUser;
import com.neza.base.Constant;

public class LoginFilter extends HttpServlet implements Filter
{
	protected FilterConfig filterConfig;

	public void doFilter(ServletRequest sRequest, ServletResponse sResponse,
	    FilterChain filterChain) throws IOException, ServletException
		{
			HttpServletRequest request = (HttpServletRequest) sRequest;
			HttpServletResponse response = (HttpServletResponse) sResponse;
			UserRightInfo uri = request.getSession().getAttribute("URI") == null ? null
			    : (UserRightInfo) request.getSession().getAttribute("URI");
			SysUser user = null;
			if (user != null)
				user = uri.getUser();
			String method = "";
			if (request.getParameter("thisAction") != null)
				method = "?thisAction=" + request.getParameter("thisaction");
			String url = request.getRequestURI() + method;
			if (url.equals("/index.jsp") || url.equals("/login.jsp")
			    || url.equals("/agent/index.html"))
			{
				filterChain.doFilter(request, response);
				return;
			}
			else if (user == null || user.getUserId() < 1)
			{
				response.sendRedirect(Constant.SERVLET_PATH+"/login.jsp");
				return;
			}
			else
			{
				filterChain.doFilter(request, response);
				return;
			}
			// ServletRequest.getRequestDispatcher("");
			// request.getRequestDispatcher("index.jsp");
		}

	public void init(FilterConfig filterConfig) throws ServletException
		{
			this.filterConfig = filterConfig;
		}

	public void destory()
		{

		}
}
