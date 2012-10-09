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
<script src="../_js/prototype.js" type="text/javascript"></script>
<script src="../_js/select.js" type="text/javascript"></script>
<script language="JavaScript">
<!--

 function editUserRight()
 {
  if(document.forms[0].selectedItems==null){
		   	alert("没有数据，无法操作！");
		   }
		  else
   if (sumCheckedBox(document.forms[0].selectedItems)<1)
     alert("您还没有选择用户!");
   else if (confirm("您真的要修改这些用户所属权限角色吗?"))
   {
     document.forms[0].thisAction.value="editselecteduserright";
     document.forms[0].submit();
   }
 }

  function submitForm()
  {
     document.forms[0].thisAction.value="edituser4role";
     document.forms[0].submit();
  }
   
  function saveForm()
  {
     document.forms[0].thisAction.value="updateuser4role";
     document.forms[0].count.value=$('rightUserID').options.length;
     js.select.selectAll($('rightUserID'));
     document.forms[0].submit();
  }
   
 function moveAll(obj1,obj2)
 {
   js.select.moveAll(obj1,obj2); 
   js.select.sort(obj2);
 }
 
 
 function moveLeft2Right(obj1,obj2)
 {
   js.select.moveSelected(obj1,obj2); 
   js.select.sort(obj2);
 }
 
 function moveRight2Left(obj2,obj1)
 {
   js.select.moveSelected(obj2,obj1);
   js.select.sort(obj2);
 }
//-->
</script>
</head>

<body class="body_m">
<c:import url="/_jsp/mainTitle.jsp?title=权限管理-->用户授权"
	charEncoding="UTF-8" />


<html:form action="/right/rolelist.do">
	<html:hidden property="thisAction" value="right" />
	<html:hidden property="userIDs" />
	<html:hidden property="intPage" />
	<html:hidden property="pageCount" />
	<html:hidden property="count" />
	<table width="100%" height="45" class="table_sc">
		<tr>
			<td>
			<div align="center">用户角色</div>
			</td>
			<td>
			<div align="left"><html:select property="userRoleID" name="ulf"
				onchange="submitForm();">
				<html:options collection="rolelist" property="value"
					labelProperty="label" />
			</html:select></div>
			</td>
		</tr>
	</table>

	<table width="100%" cellspacing="1" class="table_li">
		<tr class="table_li1">
			<td>
			<div align="center">用户列表</div>
			</td>
			<td>
			<div align="center"></div>
			</td>
			<td>
			<div align="center">选中的用户</div>
			</td>
		</tr>
		<tr class="table_li2">
			<td rowspan="4" class="cGray"><html:select property="leftUserID"
				styleId="leftUserID" size="10" multiple="true" style="width:180px">
				name="ulf">
				<html:options collection="userlist1" property="value"
					labelProperty="label" />
			</html:select></td>
			<td class="cGray"><input type="button" class="button1"
				onclick="moveAll($('leftUserID'),$('rightUserID'));" value="全部右移"></td>
			<td rowspan="4" class="cGray"><html:select
				property="rightUserID" styleId="rightUserID" size="10"
				multiple="true" style="width:180px">
				name="ulf">
				<html:options collection="userlist2" property="value"
					labelProperty="label" />
			</html:select></td>
		</tr>
		<tr class="table_li2">
			<td class="cGray"><input type="button" class="button1"
				onclick="moveLeft2Right($('leftUserID'),$('rightUserID'));"
				value="右  移"></td>
		</tr>
		<tr class="table_li2">
			<td class="cGray"><input type="button" class="button1"
				onclick="moveRight2Left($('rightUserID'),$('leftUserID'));"
				value="左  移"></td>
		</tr>
		<tr class="table_li2">
			<td class="cGray"><input type="button" class="button1"
				onclick="moveAll($('rightUserID'),$('leftUserID'));" value="全部左移"></td>
		</tr>
	</table>

	<table width="100%" style="margin-top: 5px;">
		<tr>
			<td><input type="button" class="button1" onclick="saveForm();"
				value="保 存"> <input type="button" class="button1"
				onClick="submitForm();" value="重 置"></td>
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