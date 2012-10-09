<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%
	try
	{
		out.println("now test certification...");
		String certAttribute = "javax.servlet.request.X509Certificate";
		java.security.cert.X509Certificate[] certificates = (java.security.cert.X509Certificate[]) request
		    .getAttribute(certAttribute);

		if (certificates != null)
		{
			for (int i = 0; i < certificates.length; i++)
			{
				out.println("getIssuerDN="
				    + certificates[i].getIssuerDN());
				out.println("getSerialNumber="
				    + certificates[i].getSerialNumber());
				out.println("getExtensionValue="
				    + certificates[i].getExtensionValue("E"));
			}
		}
		else
		{
			out.println("请插入你的证书。");
			/*if ("https".equals(request.getScheme()))
			{
				out.println("请插入你的证书。");
				
			}
			else
			{
				out.println("This was not an HTTPS request, "
				    + "so no client certificate is available");
			}*/

		}
	}
	catch (Exception ex)
	{
		out.println(ex.getMessage());
	}
%>


