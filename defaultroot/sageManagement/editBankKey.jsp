<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'listBank.jsp' starting page</title>
    <link href="../_css/reset.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" language="javascript"
			src="../_js/jquery-1.3.2.min.js"></script>
		<link href="../_css/global.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" language="javascript"
			src="../_js/jquery-1.3.1.min.js"></script>
		<script src="../_js/common.js" type="text/javascript"></script>
		
</head>
<body>
<html:form action="/system/bankKey.do?thisAction=update">
<html:hidden property="bankName" value="${bankKey.bankName}"></html:hidden>
<html:hidden property="keyType" value="${bankKey.keyType}"></html:hidden>
<div id="mainContainer">
<div id="container">
<table width="100%" cellpadding="0" cellspacing="0" border="0">
	<tr>
	<td width="10" height="10" class="tblt"></td>
	<td height="10" class="tbtt"></td>
	<td width="10" height="10" class="tbrt"></td>
	</tr>
	<tr>
	<td width="10" class="tbll"></td>
	<td valign="top" class="body">
<c:import url="../_jsp/mainTitle.jsp?title1=安全管理&title2=修改银行密钥" charEncoding="UTF-8" />
    <hr/>
	<!-- 开始 -->
	
	<table align="center">
	<tr>
	<td>银行名称：<font color="red"><c:out value="${bkName}"/></font> <br/>修改：<FONT color="red"><c:out value="${bkKey}"/></FONT></td>
	</tr>
	<tr>
	<td>
	<html:textarea property="key" cols="45" rows="10" style="border: 1 solid #888888;" value="${key}"/>
	</td>
	</tr>
	<c:check code="sf02,sf03">
	<tr>
	<td align="center">
	
	<input type="submit" value="更 改" class="btn1" />
	
	&nbsp;<input type="button" value="返 回" class="btn1" onclick="window.location.href='../sageManagement/listBank.jsp'" />
	</td>
	</tr>
	</c:check>
	</table>
  <!-- 结束 -->				
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
</div>
</div>
</html:form>
</body>
</html>
