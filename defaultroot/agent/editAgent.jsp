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


<script>

function addAgent()
{
    document.forms[0].submit();
}

</script>
</head>
<body>

<div id="mainContainer">
<div id="container"><html:form action="/agent/agent.do">
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
<c:import url="../_jsp/mainTitle.jsp?title1=商户管理&title2=修改信息" charEncoding="UTF-8" />
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
				<c:check code="sb02">
				<tr>
					<td class="lef">商户一般信息</td>
					<td style="text-align: left">
					<html:link page="/agent/agentlist.do?thisAction=editPersonal&agentId=${agent.id}">修改个人信息</html:link> </td>
				</tr>
				<tr>
					<td class="lef">商户密码信息</td>
					<td style="text-align: left">
					<html:link page="/agent/agentlist.do?thisAction=editPassword&agentId=${agent.id}">修改商户密码</html:link> </td>
				</tr>
				<tr>
					<td class="lef">预设资金限额</td>
					<td style="text-align: left">
					<html:link page="/agent/agentlist.do?thisAction=editDefaultAmount&agentId=${agent.id}">修改预设资金</html:link> </td>
				</tr>
				<tr>
					<td class="lef">商户账户状态</td>
					<td style="text-align: left">
					<c:if test="${agent.accountStatus!=null}">
					目前状态：
					<FONT color="red;">
					<c:out value="${agent.accountStatusTo}"></c:out>
					</FONT>
					</c:if>
					<html:link page="/agent/agentlist.do?thisAction=editAccountStatus&agentId=${agent.id}">修改账户状态</html:link> </td>
				</tr>
				<tr>
					<td class="lef">资金处理</td>
					<td style="text-align: left">
					<html:link page="/agent/agent.do?thisAction=fundsTreatment&type=1&agentId=${agent.id}">冻结资金</html:link> 
					<html:link page="/agent/agent.do?thisAction=fundsTreatment&type=2&agentId=${agent.id}">解冻资金</html:link> 
					</td>
				</tr>
				</c:check>
				<c:check code="sb03">
				<!-- <tr>
					<td class="lef">密钥信息</td>
					<td style="text-align: left">
					
					<html:link page="/agent/agentlist.do?thisAction=editKey&agentId=${agent.id}">修改密钥信息</html:link></td>
				    
				</tr> -->
				</c:check>
			</table>
			<input type="button" class="button2" value="返 回" onclick="javascript:history.go(-1);" />
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