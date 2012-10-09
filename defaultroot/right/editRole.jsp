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


   if (document.forms[0].roleName.value=="")
   {
     alert("角色名称不能为空!");
     return;
   }
   document.forms[0].submit();	 
  }
	 
--> 
</script>
</head>

<body class="body_m">
<c:import url="/_jsp/mainTitle.jsp?title=权限管理-->添加用户角色" charEncoding="UTF-8"/>

<html:form 
	action="/right/role.do">
	<html:hidden name="rf" property="thisAction" />
	<html:hidden name="rf" property="roleID" />
	<html:hidden name="rf" property="roleSystem" />

	<table width="100%" cellspacing="1" class="table_li">
		<tr>
			<td class="table_ltitle">用户角色名称</td>
			<td class="table_content"><html:text name="rf" property="roleName"
				styleClass="TextInput" size="40" /></td>
		</tr>
		<c:if test="${rf.thisAction=='upsysrole' or rf.thisAction=='insysrole'}">
		<tr>
			<td class="table_ltitle">用户角色代码</td>
			<td class="table_content"><html:text name="rf" property="roleKey"
				styleClass="TextInput" size="40" /></td>
		</tr>
		</c:if>
		<tr>
			<td class="table_ltitle">用户角色描述</td>
			<td class="table_content"><html:text name="rf" property="roleDescription"
				styleClass="TextInput" size="40" /></td>
		</tr>
	</table>

	<table width="100%" style="margin-top: 5px;">
		<tr>
			<td><input type="button" class="button1" onclick="submitForm();"
				value="保 存"> <input type="button" class="button1"
				onclick="document.rf.reset();" value="重 置"></td>
		</tr>
	</table>

</html:form>

</body>
</html>