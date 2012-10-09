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

 function saveUserRight()
 {
   var o=document.ulf.userRoleID;
   if (o.selectedIndex==0)
   {
     alert("您还没有选择要把这些用户设置成什么权限角色!");
     o.focus();
     return;
   }
   
   if (confirm("您真的要把这些用户所属权限角色改成\""+ o.options[o.selectedIndex].text+"\"吗?"))
   {
     document.ulf.thisAction.value="upselecteduserright";
     document.ulf.submit();
   }
 }

//-->
</script>
</head>

<body class="body_m">

<c:import url="/_jsp/mainTitle.jsp?title=权限管理-->用户权限" charEncoding="UTF-8"/>

<html:form name="ulf" action="/right/rolelist.do"
	type="com.sininet.zssat._right.RoleListForm" scope="request">
	<html:hidden property="thisAction" />
	<html:hidden property="userIDs" />

	<table width="100%" cellspacing="1" class="table_li">
	<tr class="table_li1">
			<td>
			<div align="center">用户</div>
			</td>
			<td>
			<div align="center">要设置成下面选择的权限</div>
			</td>
		</tr>


		<tr>
	<tr class="table_li2">
			<td class="cGray"><c:forTokens items="${ulf.temp}"
				delims="," var="theValue">
				<c:out value="${theValue}" />
				<br>
			</c:forTokens></td>
			<td class="cGray">
			<div align="center"><html:select property="userRoleID"
				name="ulf" onchange="changeRole()">
				<html:options collection="rolelist" property="value"
					labelProperty="label" />
			</html:select></div>
			</td>
		</tr>
	</table>

	<table width="100%" style="margin-top: 5px;">
		<tr>
			<td><input type="button" class="button1"
				onclick="saveUserRight();" value="保 存"> <input type="button"
				class="button1" onclick="document.ulf.reset();" value="重 置">
			<input type="button" class="button1"
				onclick="document.ulf.thisAction.value='right';window.history.back()"
				value="返 回"></td>
		</tr>
	</table>

</html:form>

<script language="JavaScript">
<!--
  var varUserRoleID='<c:out value="${ulf.userRoleID}" />'
  selectCurrent(document.ulf.userRoleID,varUserRoleID);  

-->
</script>
</body>
</html>