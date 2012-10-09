<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>


<c:if test="${URI==null}">
	<script language="JavaScript">
   	top.location="../login.jsp" 
	</script>
</c:if>
<%
	String title1 = request.getParameter("title1");
	if (title1 != null)
	{
		try
		{
			out.println("-----------------1="+new String(title1.getBytes("ISO-8859-1")));
			out.println("-----------------2="+new String(title1.getBytes("ISO-8859-1"),"UTF-8"));
			out.println("---- -------------3="+java.net.URLDecoder.decode(title1, "UTF-8"));
			out.println("---- -------------4="+java.net.URLDecoder.decode(title1, "ISO-8859-1"));		
			out.println("---- -------------4="+java.net.URLDecoder.decode(new String(title1.getBytes("ISO-8859-1")), "UTF-8"));	
		}
		catch (Exception ex)
		{
			out.print(ex.getMessage());
		}
	}
	else
		title1 = "";
	String title2 = request.getParameter("title2");
	if (title2 != null)
		title2 = new String(title2.getBytes("UTF-8"));
	else
		title2 = "";
%>

<style>
.divstyle {
	padding: 5px;
	font-family: "MS Serif", "New York", serif;
	background: #e5e5e5;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<div class="divstyle"><%=title1%> &gt;&gt;<font color="red"><%=title2%></font>
</div>


