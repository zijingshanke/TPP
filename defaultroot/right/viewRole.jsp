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
 
-->
</script>
</head>

<body class="body_m">
<c:import url="/_jsp/mainTitle.jsp?title=权限管理-->用户角色" charEncoding="UTF-8"/>


<html:form name="ulf" action="/right/rolelist.do"
	type="com.sininet.zssat._right.RoleListForm" scope="request">
	<html:hidden property="thisAction" value="" />
	<table width="100%" cellspacing="1" class="table_li">
		<tr align="center" class="table_title">
			<td>
			<div align="center">No.</div>
			</td>
			<td>
			<div align="center"></div>
			</td>
			<td>
			<div align="center">用户角色</div>
			</td>
			<td>
			<div align="center">描 述</div>
			</td>
		</tr>
		<c:forEach var="info" items="${ulf.list}" varStatus="status">
			<tr>
				<td class="table_content" width="20">
				<div align="center"><c:out value="${status.count}" /></div>
				</td>
				<td class="table_content" width="20"><html:multibox
					property="selectedItems" styleClass="cb" value="${info.roleID}"
					disabled="${info.roleSystem}" /></td>
				<td class="table_content"><a
					href="role.do?thisAction=view&roleID=<c:out value='${info.roleID}' />"><c:out
					value="${info.roleName}" /></a></td>
				<td class="table_content"><c:out value="${info.roleDescription}" /></td>
			</tr>
		</c:forEach>
	</table>

	<table width="100%" style="margin-top: 5px;">
		<tr>
			<td><a href="javascript:addRole()"><html:img
				page="/theme/images/new.gif" border="0" /></a> <a
				href="javascript:editRole()"><html:img
				page="/theme/images/edit.gif" border="0" /></a> <a
				href="javascript:delRole()"><html:img
				page="/theme/images/delete.gif" border="0" /></a></td>
		</tr>
	</table>

</html:form>

</body>
</html>