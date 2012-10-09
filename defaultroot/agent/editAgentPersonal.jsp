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


<script type="text/javascript" language="javascript">

function addAgent()
{
	if(document.forms[0].address.value==null || document.forms[0].address.value=="")
	{
		alert("地址不能为空！");
		return false;
	}else
	if(document.forms[0].mobile.value==null || document.forms[0].mobile.value=="")
	{
		alert("手机号码不能为空！");
		return false;
	}else
	if(document.forms[0].telephone.value==null || document.forms[0].telephone.value=="")
	{
		alert("电话号码不能为空！");
		return false;
	}else
	if(!(/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(document.forms[0].email.value))){
		alert("邮箱错误！");
		return false;
	}else
	if(!(/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(document.forms[0].tempEmail.value))){
		alert("邮箱错误！");
		return false;
	}else
	{
		document.forms[0].submit();
	}
    
}

</script>
</head>
<body>
<div id="mainContainer">
<div id="container"><html:form action="/agent/agent.do?thisAction=updatePersonal">
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
<c:import url="../_jsp/mainTitle.jsp?title1=商户管理&title2=修改个人信息" charEncoding="UTF-8" />
									<hr>
			<table width="100%" cellpadding="0" cellspacing="0" border="0"
				class="dataList">
				<tr>
					<td class="lef">用户名</td>
					<td style="text-align: left" ><c:out value="${agent.name}"></c:out> </td>
				</tr>
				<c:if test="${registerType==1}">
					<tr>
						<td class="lef">企业名称</td>
						<td style="text-align: left" ><c:out value="${agent.companyName}"></c:out> </td>
					</tr>
				</c:if>
				<tr>
					<td class="lef">登录帐号</td>
					<td style="text-align: left"><c:out value="${agent.loginName}"></c:out></td>
				</tr>
				
				
				<tr>
					<td class="lef">地址</td>
					<td style="text-align: left"><font color="red">*</font><html:text property="address" styleId="address" name="agent" styleClass="colorblue6 p_5" /> </td>
				</tr>
				<tr>
					<td class="lef">手机</td>
					<td style="text-align: left"><font color="red">*</font><html:text property="mobile" styleId="mobile" name="agent" styleClass="colorblue2 p_5" /> </td>
				</tr>
				<tr>
					<td class="lef">电话</td>
					<td style="text-align: left"><font color="red">*</font><html:text property="telephone" styleId="telephone" name="agent" styleClass="colorblue2 p_5" /> </td>
				</tr>
				<tr>
					<td class="lef">邮箱</td>
					<td style="text-align: left"><font color="red">*</font><html:text property="email" styleId="email" name="agent" styleClass="colorblue6 p_5" /> </td>
				</tr>
				<tr>
					<td class="lef">备用邮箱</td>
					<td style="text-align: left"><font color="red">*</font><html:text property="tempEmail" styleId="tempEmail" name="agent" styleClass="colorblue6 p_5" /> </td>
				</tr>
			</table>
			<table width="100%" style="margin-top: 5px;">
			<c:check code="sb02">
			<tr>
			<td>
			<input type="button" value="提 交" class="button1" onclick="addAgent();"/>
			
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
