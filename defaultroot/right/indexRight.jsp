<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<html>
<head>
<title>main</title>
<link href="../_css/reset.css" rel="stylesheet" type="text/css" />
<link href="../_css/global.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" language="javascript"
	src="../_js/jquery-1.3.1.min.js"></script>
<script src="../_js/common.js" type="text/javascript"></script>


<script>
<!--

 function editUserRight()
 {
   if (sumCheckedBox(document.ulf.selectedItems)<1)
     alert("您还没有选择用户!");
   else 
   {
     document.ulf.thisAction.value="editselecteduserright";
     document.ulf.submit();
   }
 }

  function changeRole()
  {
	 document.ulf.thisAction.value="right";
     document.ulf.submit();
  }
    
 function editUser4Role()
 {
   document.ulf.thisAction.value="edituser4role";
   document.ulf.submit();
 }
 
 function editRole4User()
 {
     document.ulf.thisAction.value="editrole4user";
     document.ulf.submit();

 }
 
 
//用户权限
//-->
</script>
</head>

<body class="body_m">
<c:import url="/_jsp/mainTitle.jsp?title=权限管理-->用户授权" charEncoding="UTF-8"/>


<html:form  action="/right/rolelist.do"
	>
	<html:hidden name="ulf" property="thisAction" value="right" />
	<html:hidden name="ulf" property="userIDs" />
	<html:hidden name="ulf" property="intPage" />
	<html:hidden name="ulf" property="pageCount" />

	<table width="100%" height="45" class="table_sc">
		<tr>
			<td>
			<div align="center">税务机关</div>
			</td>
			<td>
			<div align="left"><html:select property="subsatId" name="ulf">
				<html:options collection="subsatlist" property="value"
					labelProperty="label" />
			</html:select></div>
			</td>
			<td>
			<div align="center">用户角色</div>
			</td>
			<td>
			<div align="left"><html:select property="userRoleID" name="ulf">
				<html:options collection="rolelist" property="value"
					labelProperty="label" />
			</html:select></div>
			</td>
			<td>登录帐号/用户姓名</td>
			<td><html:text property="userKey" name="ulf" styleClass="TextInput" /></td>
			<td><input type="button" class="button1"
				onclick="document.ulf.submit();" value="搜 索"></td>
		</tr>
	</table>

	<table width="100%" cellspacing="1" class="table_li">
		<tr class="table_li1">
			<td >
			<div align="center">No.</div>
			</td>
			<td>
			<div align="center"></div>
			</td>
			<td>
			<div align="center">登录帐号</div>
			</td>
			<td>
			<div align="center">用户名称</div>
			</td>
			<td>
			<div align="center">所属角色</div>
			</td>
		</tr>
		<c:forEach var="info" items="${ulf.list}" varStatus="status">
			<tr class="table_li2">
				<td class="cGray" width="20">
				<div align="center"><c:out value="${status.count+(ulf.intPage-1)*ulf.perPageNum}" /></div>
				</td>
				<td class="cGray" width="20"><html:multibox
					property="selectedItems" styleClass="cb" value="${info.userId}" /></td>
				<td class="cGray"><c:out value="${info.userNo}" /></td>
				<td class="cGray"  width="10%"><c:out value="${info.userName}" /></td>
				<td class="cGray"><c:out value="${info.roleName}" /></td>
			</tr>
		</c:forEach>
	</table>

	<table width="100%" style="margin-top: 5px;">
		<tr>
			<td><input type="button" class="button1" onclick="editUserRight();"
				value="修 改">
				
				<input type="button" class="button3" onclick="editUser4Role();"
				value="给用户分配角色"> 
				<input type="button" class="button3" onclick="editRole4User();"
				value="授角色给用户">
				 <input type="button" class="button1"
				onclick="document.ulf.reset();" value="重 置"> </td>
			<td align="right">
			<table class="table_id">
				<tr>
					<td><a href="JavaScript:turnToPage2(document.ulf,0)">首页</a> <a
						href="JavaScript:turnToPage2(document.ulf,1)">前页</a> <a
						href="JavaScript:turnToPage2(document.ulf,2)">后页</a> <a
						href="JavaScript:turnToPage2(document.ulf,3)">尾页</a> <c:out
						value="${ulf.intPage}" />/<c:out value="${ulf.pageCount}" /></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>

</html:form>

</body>
</html>