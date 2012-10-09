<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<html>

<head>
<TITLE></TITLE>
<link href="../_css/css.css" rel="stylesheet" type="text/css">
<script src="../_js/common.js" type="text/javascript"></script>
<script type="text/javascript" language="javascript"
src="../_js/table2.js"></script>
<script language="JavaScript">
<!--
  var roleID=0;

  function addRole()
  {
    document.ulf.thisAction.value="adduserrole";
    document.ulf.submit();
  }
  
  function editRole()
  {
	  document.ulf.thisAction.value="edituserrole";	 
      alert(document.ulf.thisAction.value);
	  document.ulf.submit();
  }
	
  function delRole()
  {

	  document.ulf.thisAction.value="deluserrole";
	      if(confirm("您真的要删除系统角色吗？"))
	  document.ulf.submit();
  }
  
  function selrme(selitme,id)
  {
	  selitme[id-1].checked=true;
	  document.ulf.action="rolelist.do?thisAction=user";
	  document.ulf.submit();
  }
  
  function saveRight()
  {
     if (confirm("你真的要修改这些权限吗？"))
	 {
	    document.ulf.selectedRightItem.value=sumCheckedBox(document.ulf.selectedRightItems)	
	   
		//alert(document.ulf.selectedRightItem.value);
	    document.ulf.thisAction.value="edituserright";
		//alert(document.ulf.selectedRightItem.value);
	    document.ulf.submit();
	 }
  }
  //用户角色
//--> 
</script>
</head>

<body class="body_m">
<c:import url="/_jsp/mainTitle.jsp?title=权限管理-->用户角色" charEncoding="UTF-8"/>

<html:form name="ulf" action="/right/rolelist.do"
	type="com.sininet.zssat._right.RoleListForm" scope="request">
	<html:hidden property="thisAction" value="" />
	<html:hidden property="selectedRightItem" />

	<table class="table_in">
		<tr>
			<td>
			<table class="list_table">
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
					<div align="center">角色代码</div>
					</td>
					<td>
					<div align="center">描 述</div>
					</td>
				</tr>
				<c:forEach var="info" items="${list1}" varStatus="status">
					<tr>
						<td class="table_content" width="20">
						<div align="center"><c:out value="${status.count}" /></div>
						</td>
						<td class="table_content" width="20"><html:radio
							property="userRoleID" value="${info.roleID}"
							onclick="selrme(document.ulf.userRoleID,${status.count})" /></td>
						<td class="table_content"><a
							href="javascript:selrme(document.ulf.userRoleID,<c:out value='${status.count}' />)"><c:out
							value="${info.roleName}" /></a></td>
						<td class="table_content"><c:out value="${info.roleKey}" /></td>
						<td class="table_content"><c:out
							value="${info.roleDescription}" /></td>
					</tr>
				</c:forEach>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%"  style="margin-top:5px;">
				<tr>
					<td><input type="button" class="button1" onclick="addRole()"
						value="新 增"> <input type="button" class="button1"
						onclick="editRole()" value="修 改"> <input type="button"
						class="button1" onclick="delRole()" value="删 除"></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table class="table_in">
				<tr class="table_ltitle">
					<td>
					<div align="left" id="title">功能项归类</div>
					</td>
					<td>
					<div align="left" >功能项</div>
					</td>
				</tr>
				<tr>
					<td valign="top" width="40%">
					<table class="list_table" >
						<tr align="center" class="table_title">
							<td>
							<div align="center">No.</div>
							</td>
							<td>
							<div align="center"></div>
							</td>
							<td>
							<div align="center">功能项归类</div>
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
								<td class="table_content" width="20"><html:radio
									property="sysRoleID" value="${info.roleID}"
									onclick="selrme(document.ulf.sysRoleID,${status.count})" /></td>
								<td class="table_content"><a
									href="javascript:selrme(document.ulf.sysRoleID,<c:out value='${status.count}' />)"><c:out
									value="${info.roleName}" /></a></td>
								<td class="table_content"><c:out
									value="${info.roleDescription}" /></td>
							</tr>
						</c:forEach>
					</table>
					</td>
					<td valign="top">
					<table class="table_in">
						<tr>
							<td>
							<table class="list_table">
								<tr align="center" class="table_title">
									<td>
									<div align="center">No.</div>
									</td>
									<td>
									<div align="center"></div>
									</td>
									<td>
									<div align="center">功能项名称</div>
									</td>
									<td>
									<div align="center">描述</div>
									</td>
								</tr>
								<c:forEach var="info" items="${slist}" varStatus="status">
								
									<tr>
										<td class="table_content" width="20">
										<div align="center"><c:out value="${status.count}" /></div>
										</td>
										<td class="table_content" width="20"><html:multibox
											property="selectedRightItems" name="ulf" styleClass="cb"
											value="${info.rightKey}" /></td>
										<td class="table_content"><c:out value="${info.rightName}" /></td>
										<td class="table_content"><c:out
											value="${info.rightDescription}" /></td>
									</tr>
									
								</c:forEach>
							</table>
							</td>
						</tr>
						<tr>
							<td align="left">
							<div  style="margin-top:5px;">
							 <input type="button" class="button1"
								onclick="saveRight();" value="保 存"> <input type="button"
								class="button1" onclick="document.ulf.reset();" value="重 置"></div></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</html:form>
</body>
</html>