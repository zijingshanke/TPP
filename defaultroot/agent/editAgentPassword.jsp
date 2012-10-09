<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>

<html>
<head>
<title>main</title>
<link href="../_css/reset.css" rel="stylesheet" type="text/css" />
<link href="../_css/global.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" language="javascript"
	src="../_js/jquery-1.3.1.min.js"></script>
<script src="../_js/common.js" type="text/javascript"></script>
<script type="text/javascript">
function updateAgent(){
	 if(document.forms[0].password.value!=null && document.forms[0].password.value!="")
	{
		if(document.forms[0].password.value.length<6 || document.forms[0].password.value.length>20)
		{
			alert("登陆密码的长度为6——20位的数字或字母！");
			document.forms[0].password.focus();
			return false;
		}
	}if(document.forms[0].tempPassword.value!=document.forms[0].password.value){
			alert("两次登录密码不相同！");
			document.forms[0].tempPassword.focus();
			return false;
		}
	
	 if(document.forms[0].payPassword.value!=null && document.forms[0].payPassword.value!="")
	{
		if(document.forms[0].payPassword.value.length<6 || document.forms[0].payPassword.value.length>20)
		{
			alert("交易密码的长度为6——20位的数字或字母！");
			document.forms[0].payPassword.focus();
			return false;
		}
	}if(document.forms[0].tempPayPassword.value!=document.forms[0].payPassword.value){
			alert("两次交易密码不相同！");
			document.forms[0].tempPayPassword.focus();
			return false;
		}
	if(document.forms[0].payPassword.value!=null && document.forms[0].payPassword.value!=""&&document.forms[0].password.value!=null && document.forms[0].password.value!="")
	{
		if(document.forms[0].payPassword.value==document.forms[0].password.value)
		{
			alert("登陆密码不能和交易密码相同！");
			document.forms[0].payPassword.focus();
			return false;
		}
	}
		document.forms[0].submit();
	
}
</script>
</head>
<body>
<div id="mainContainer">
<div id="container"><html:form action="/agent/agent.do?thisAction=updatePassword">
	<html:hidden property="thisAction" name="agent" />
	<html:hidden property="id" name="agent" />
	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td width="10" height="10" class="tblt"></td>
			<td height="10" class="tbtt"></td>
			<td width="10" height="10" class="tbrt"></td>
		</tr>
		<tr>
			<td width="10" class="tbll"></td>
			<td valign="top" class="body">
<c:import url="../_jsp/mainTitle.jsp?title1=商户管理&title2=修改密钥信息" charEncoding="UTF-8" />
			<hr>
			<table width="100%" cellpadding="0" cellspacing="0" border="0"
				class="dataList">
				<tr>
					<td class="lef">用户名</td>
					<td style="text-align: left" ><c:out value="${agent.name}"></c:out> </td>
				</tr>
				<tr>
					<td class="lef">登录帐号</td>
					<td style="text-align: left"><c:out value="${agent.email}"></c:out></td>
				</tr>
				<tr>
					<td class="lef">登陆名</td>
					<td style="text-align: left"><c:out value="${agent.loginName}"></c:out></td>
				</tr>
				<tr>
					<td class="lef">登陆密码</td>
					<td style="text-align: left"> <font color="red">*</font> <html:password property="password" styleId="password" name="agent" style="width:200px;" styleClass="colorblue2 p_5" value=""/> </td>
				</tr>
				<tr>
					<td class="lef">确认登陆密码</td>
					<td style="text-align: left"> <font color="red">*</font> <input name="tempPassword" type="password" id="tempPassword" style="width:200px;" class="colorblue2 p_5" vlaue=""/> </td>
				</tr>
				<tr>
					<td class="lef">交易密码</td>
					<td style="text-align: left"><font color="red">*</font> <html:password property="payPassword" styleId="payPassword" name="agent" style="width:200px;" styleClass="colorblue2 p_5" value=""/>
					 </td>
				</tr>
				<tr>
					<td class="lef">确认交易密码</td>
					<td style="text-align: left"><font color="red">*</font> <input name="tempPayPassword" type="password" id="tempPayPassword" style="width:200px;" class="colorblue2 p_5" vlaue=""/> 
					&nbsp;&nbsp;&nbsp;<font color="red">*如果没有填写新的密码，提交后，密码还是保持原有。</font>
					 </td>
				</tr>
			</table>
			<table width="100%" style="margin-top: 5px;">
			<c:check code="sb03">
			<tr>
			<td>
			<input type="button" value="提 交" class="button1" onclick="updateAgent();"/>
			
			<input type="reset" value="重 置" class="button1"/>
			</td>
			</tr>
			</c:check>
				</table>					
			<div class="clear"></div>
			</td>
							<td width="10" class="tbrr"></td>
						</tr>
						<tr>
							<td width="10" class="tblb"></td>
							<td class="tbbb"></td>
							<td width="10" class="tbrb"></td>
						</tr>
	</table>

</html:form></div>
</div>
</body>
</html>
