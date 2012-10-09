<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<html>
<head>
<title>main</title>
<link href="../_css/reset.css" rel="stylesheet" type="text/css" />
<link href="../_css/global.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" language="javascript"
	src="../_js/jquery-1.3.2.min.js"></script>
<script src="../_js/common.js" type="text/javascript"></script>

<script>

function addUser()
{
    document.forms[0].thisAction.value="add";
    document.forms[0].submit();
}

function editUser()
{
 if(document.forms[0].selectedItems==null){
		   	alert("没有数据，无法操作！");
		   }
		  else
  if (sumCheckedBox(document.forms[0].selectedItems)<1)
    alert("您还没有选择用户！");
  else if (sumCheckedBox(document.forms[0].selectedItems)>1)
    alert("您一次只能选择一个用户！");
  else
  {
    document.forms[0].thisAction.value="edit";
    document.forms[0].submit();
  }
}

function delUser()
{
 if(document.forms[0].selectedItems==null){
		   	alert("没有数据，无法操作！");
		   }
		  else
  if (sumCheckedBox(document.forms[0].selectedItems)<1)
    alert("您还没有选择用户！");
  else if(confirm("您真的要删除选择的这些用户吗？"))
  {
    document.forms[0].thisAction.value="delete";
    document.forms[0].submit();
  }
}



function searchUser()
{
   document.forms[0].thisAction.value="list";
   document.forms[0].submit();
}


</script>
</head>
<body>
<div id="mainContainer">
<div id="container"><html:form action="/user/userlist.do" >
	<html:hidden property="thisAction" />
	<html:hidden property="lastAction" />
	<html:hidden property="intPage" />
	<html:hidden property="pageCount" />
	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td width="10" height="10" class="tblt"></td>
			<td height="10" class="tbtt"></td>
			<td width="10" height="10" class="tbrt"></td>
		</tr>
		<tr>
			<td width="10" class="tbll"></td>
							<td valign="top" class="body">
<c:import url="../_jsp/mainTitle.jsp?title1=用户管理&title2=用户列表" charEncoding="UTF-8" />
								<div class="searchBar">
									<p>
										搜索栏
									</p>
									<hr />

									<table cellpadding="0" cellspacing="0" border="0"
										class="searchPanel">
										<tr>
											<td>
												姓名：
												<html:text property="userName"  styleClass="colorblue2 p_5" style="width:150px;"
													 />
											</td>
											<td>
												帐号：
												<html:text property="userNo" styleClass="colorblue2 p_5" style="width:150px;"
													/>
											</td>
											<td>
												序列号：
												<html:text property="serialNumber" styleClass="colorblue2 p_5" style="width:150px;"
													/>
											</td>
											<td>
												Email：
												<html:text property="userEmail" styleClass="colorblue2 p_5" style="width:150px;"
													/>
											</td>
											<td>
												状态：
												<html:select property="userStatus" >
													<html:option value="0">请选择</html:option>
													<html:option value="1">启用</html:option>
													<html:option value="2">停用</html:option>
												</html:select>
											</td>
											<td>
												<input type="submit" name="button" id="button" value="提交"
													class="submit greenBtn" />
											</td>
										</tr>
									</table>
								</div>
								<table width="100%" cellpadding="0" cellspacing="0" border="0"
									class="dataList">
									<tr>
										<th width="35">
											<div>
												&nbsp;
											</div>
										</th>
										<th width="230">
											<div>
												名字
											</div>
										</th>
										<th>
											<div>
												帐号
											</div>
										</th>
										<th>
											<div>
												序列号
											</div>
										</th>
										<th>
											<div>
												Email
											</div>
										</th>

										<th>
											<div>
												状态
											</div>
										</th>
										

									</tr>
									<c:forEach var="info" items="${ulf.list}" varStatus="status">
										<tr>

											<td>
												<html:multibox property="selectedItems" value="${info.userId}"></html:multibox>
											</td>
											<td>
												<html:link
													page="/user/userlist.do?thisAction=view&userId=${info.userId}">
													<c:out value="${info.userName}" />
												</html:link>
											</td>
											<td>
												<c:out value="${info.userNo}" />
											</td>
											<td>
												<c:out value="${info.serialNumber}" />
											</td>
											<td>
												<c:out value="${info.userEmail}" />
											</td>
											<td>
												<c:out value="${info.userStatusCaption}" />
											</td>
											
										</tr>
									</c:forEach>

								</table>

								<table width="100%" style="margin-top: 5px;">
									<tr>
										<td>
										<c:check code="sa05">
											<input name="label" type="button" class="button1" value="新 增"
												onclick="addUser();">
												</c:check>
										<c:check code="sa02,sa03">
											<input name="label" type="button" class="button1" value="修 改"
												onclick="editUser();">
												</c:check>
										<c:check code="sa04">
											<input name="label" type="button" class="button1" value="删 除"
												onclick="delUser();">
                                                </c:check>
										</td>
										<td align="right">
											<div>
												共有记录&nbsp;
												<c:out value="${ulf.totalRowCount}"></c:out>
												&nbsp;条&nbsp;&nbsp;&nbsp;&nbsp; [
												<a href="JavaScript:turnToPage(document.forms[0],0)">首页</a> |
												<a href="JavaScript:turnToPage(document.forms[0],1)">上一页</a> |
												<a href="JavaScript:turnToPage(document.forms[0],2)">下一页</a> |
												<a href="JavaScript:turnToPage(document.forms[0],3)"> 末页</a>] 页数:
												<c:out value="${ulf.intPage}" />
												/
												<c:out value="${ulf.pageCount}" />
												]
											</div>
										</td>
									</tr>
								</table>	<div class="clear"></div>
							
						</td>
						<td width="10" class="tbrr"></td>
					</tr>
					<tr>
						<td width="10" class="tblb"></td>
						<td class="tbbb"></td>
						<td width="10" class="tbrb"></td>
					</tr>
			
	</table>
</html:form></div>
</div>
</body>
</html>
