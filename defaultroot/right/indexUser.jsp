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
  var roleID=0;

  function addRole()
  {
    document.forms[0].thisAction.value="adduserrole";
    document.forms[0].submit();
  }
  
  function editRole()
  {
     if(isSelRadio(document.forms[0].userRoleID))
     {
	  document.forms[0].thisAction.value="edituserrole";	 
	  document.forms[0].submit();
	 }
	 else
	   alert("请先选择一个用户角色!");
	  
  }
	
  function delRole()
  {

	  document.forms[0].thisAction.value="deluserrole";
	      if(confirm("您真的要删除系统角色吗？"))
	  document.forms[0].submit();
  }
  
  function selrme(selitme,id)
  {
	  selitme[id-1].checked=true;
	  document.forms[0].action="rolelist.do?thisAction=user";
	  document.forms[0].submit();
  }
  
  function saveRight()
  {
     if (confirm("您真的要修改这些权限吗？"))
	 {
	    document.forms[0].selectedRightItem.value=sumCheckedBox(document.forms[0].selectedRightItems)	
	   
		//alert(document.forms[0].selectedRightItem.value);
	    document.forms[0].thisAction.value="edituserright";
		//alert(document.forms[0].selectedRightItem.value);
	    document.forms[0].submit();
	 }
  }
  
 function turnPage(objForm,id)
 {
  document.forms[0].action="rolelist.do?thisAction=user";
  var p=0;
  if (id==0)
    p=1;
  else if (id==1)	
    p=parseInt(objForm.intPage.value)-1;
  else if (id==2)	
     p=parseInt(objForm.intPage.value)+1;
  else if (id==3)	 
   p=objForm.pageCount.value;
  else
   p=id;
  objForm.intPage.value=p;  
   alert(objForm.thisAction.value);
  objForm.submit();
 }
  //用户角色
//--> 
</script>
</head>

<body class="body_m">
<c:import url="/_jsp/mainTitle.jsp?title=权限管理-->用户角色"
	charEncoding="UTF-8" />

<html:form action="/right/rolelist.do">
	<html:hidden name="ulf" property="thisAction" />
	<html:hidden name="ulf" property="lastAction" />
	<html:hidden name="ulf" property="intPage" />
	<html:hidden name="ulf" property="pageCount" />
	<html:hidden name="ulf" property="selectedRightItem" />
	<c:out value="${ulf.xml}" escapeXml="false" />
	</xml>
	<table class="table_in">
		<tr>
			<td valign="top">
			<table class="list_table" width="100%">
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
					<td>
					<div align="center">设置权限</div>
					</td>
				</tr>
				<c:forEach var="info" items="${ulf.list}" varStatus="status">
					<tr>
						<td class="table_content" width="20">
						<div align="center"><c:out
							value="${status.count+(ulf.intPage-1)*ulf.perPageNum}" /></div>
						</td>
						<td class="table_content" width="20"><html:radio
							property="userRoleID" value="${info.roleID}" /></td>
						<td class="table_content"><c:out value="${info.roleName}" /></td>
						<td class="table_content"><c:out
							value="${info.roleDescription}" /></td>
						<td class="table_content">
						<div align="center"><html:link
							page="/right/rolelist.do?thisAction=editroleright"
							paramId="userRoleID" paramProperty="roleID" paramName="info">
						设置权限</html:link></div>
						</td>
					</tr>
				</c:forEach>
			</table>
			</td>
			<td valign="top"></td>
		</tr>
		<tr>
			<td valign="top">
			<table width="100%" style="margin-top: 5px;">
				<tr>
					<td><input type="button" class="button1" onClick="addRole()"
						value="新 增"> <input type="button" class="button1"
						onclick="editRole()" value="修 改"> <input type="button"
						class="button1" onClick="delRole()" value="删 除"></td>


					<td align="right"><a
						href="JavaScript:turnToPage2(document.ulf,0)">首页</a> <a
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
<script language="JavaScript">
<!--

//-->
</script>
</body>
</html>