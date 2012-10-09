<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<html>
<head>
<TITLE></TITLE>
<title>main</title>
<link href="../_css/reset.css" rel="stylesheet" type="text/css" />
<link href="../_css/global.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" language="javascript"
	src="../_js/jquery-1.3.1.min.js"></script>
<script src="../_js/common.js" type="text/javascript"></script>


<script>
<!--
   function submitForm()
	 {
    if (document.forms[0].rightName.value=="")
	   {
	     alert("角色名称不能为空!");
	     return;
	   }
	   else
	     document.forms[0].submit();	 
	 }
	 
--> 
</script>
</head>

<body class="body_m">

<c:import url="/_jsp/mainTitle.jsp?title=权限管理-->添加系统角色权限"
	charEncoding="UTF-8" />


<html:form action="/right/roleright.do">
	<html:hidden name="rrf" property="thisAction" />
	<html:hidden name="rrf" property="rightKey" />
	<table width="100%" cellspacing="1" class="table_li">
		<tr>
			<td class="fbg">系统角色权限名称</td>
			<td class="fbg2"><html:text name="rrf" property="rightName"
				styleClass="TextInput" size="40" /></td>
		</tr>
		<tr>
			<td class="fbg">系统角色权限代码</td>
			<td class="fbg2"><html:text name="rrf" property="rightCode"
				styleClass="TextInput" size="40" /></td>
		</tr>
		<tr>
			<td class="fbg">系统角色权限事件</td>
			<td class="fbg2"><html:text name="rrf" property="rightAction"
				styleClass="TextInput" size="40" /></td>
		</tr>
		<tr>
			<td class="fbg">系统角色权限方法</td>
			<td class="fbg2"><html:text name="rrf" property="rightEvent"
				styleClass="TextInput" size="40" /></td>
		</tr>
		<tr>
			<td class="fbg">系统角色权限描述</td>
			<td class="fbg2"><html:text name="rrf" property="rightDescription"
				styleClass="TextInput" size="40" /></td>
		</tr>
	</table>

	<table width="100%" style="margin-top: 5px;">
		<tr>
			<td><input type="button" class="button1" onclick="submitForm();"
				value="保 存"> <input type="button" class="button1"
				onclick="document.forms[0].reset();" value="重 置"></td>
		</tr>
	</table>

</html:form>
</body>
</html>