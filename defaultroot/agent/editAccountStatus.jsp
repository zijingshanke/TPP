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
		document.forms[0].submit();
}
</script>
</head>
<body>
<div id="mainContainer">
<div id="container"><html:form action="/agent/agent.do?thisAction=updateAccountStatus">
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
					<td class="lef">账户状态</td>
					<td style="text-align: left"> 
					<html:radio property="accountStatus" name="agent" value="0">未开通</html:radio>
					<html:radio property="accountStatus" name="agent" value="1">开通</html:radio>
					<html:radio property="accountStatus" name="agent" value="2">冻结</html:radio>
					<html:radio property="accountStatus" name="agent" value="3">换了邮箱，待激活</html:radio>
					</td>
				</tr>
			</table>
			<table width="100%" style="margin-top: 5px;">
			<c:check code="sb03">
			<tr>
			<td>
			<input type="button" value="提 交" class="button1" onclick="updateAgent();"/>
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
