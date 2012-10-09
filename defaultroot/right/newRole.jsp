<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<html>

<head>
<TITLE></TITLE>
<link href="../_css/css.css" rel="stylesheet" type="text/css">
<script type="text/javascript" language="javascript"
src="../_js/table2.js"></script>
<script src="../_js/common.js" type="text/javascript"></script>
<script language="JavaScript">
<!--
   function submitForm()
	 {
	   if (document.uf.roleName=="")
	   {
	     alert("角色名称不能为空!");
	     return;
	   }
	   document.uf.submit();	 
	 }
	 
--> 
</script>
</head>

<body class="body_m">
<c:import url="/_jsp/mainTitle.jsp?title=权限管理-->添加用户角色" charEncoding="UTF-8"/>

<html:form name="uf" type="com.sininet.zssat._right.RoleForm"
	action="/right/role.do" scope="request">
	<html:hidden property="thisAction" />
	<html:hidden property="roleSystem" />

	<table width="100%" cellspacing="1" class="table_li">
		<tr>
			<td class="table_ltitle">用户角色名称</td>
			<td class="table_content"><html:text property="roleName"
				styleClass="TextInput" size="40" /></td>
		</tr>
		<tr>
			<td class="table_ltitle">用户角色描述</td>
			<td class="table_content"><html:text property="roleDescription"
				styleClass="TextInput" size="40" /></td>
		</tr>
	</table>

	<table width="100%" style="margin-top: 5px;">
		<tr>
			<td><a href="JavaScript:submitForm();"><html:img
				page="/theme/images/save.gif" border="0" /></a> <a
				href="JavaScript:document.uf.reset();"><html:img
				page="/theme/images/reset.gif" border="0" /></a></td>
		</tr>
	</table>

</html:form>
<c:import url="/theme/jsp/mainBottom.jsp" />
</body>
</html>