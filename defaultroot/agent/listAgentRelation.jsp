<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt" %>
<html>
	<head>
		<title>main</title>
		<link href="../_css/reset.css" rel="stylesheet" type="text/css" />
		<link href="../_css/global.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" language="javascript"
			src="../_js/jquery-1.3.1.min.js"></script>
		<script src="../_js/common.js" type="text/javascript"></script>
		<script type="text/javascript">
       
        
	function searchAgent()
	{
	   document.forms[0].thisAction.value="list";
	   document.forms[0].submit();
	}
	      function checkAll(e, itemName)
		{
		   var aa = document.getElementsByName(itemName);
		   for (var i=0; i<aa.length; i++)
		    aa[i].checked = e.checked;
		}
		
	

        </script>
	</head>
	<body>
		<div id="mainContainer">
			<div id="container">
				<html:form action="/agent/agentlist.do">
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
<c:import url="../_jsp/mainTitle.jsp?title1=商户管理&title2=商户列表" charEncoding="UTF-8" />

								<div class="searchBar">
									<p>
										搜索栏
									</p>
									<hr />
                                        
									<table cellpadding="0" cellspacing="0" border="0"
										class="searchPanel">
										<tr>
											<td>
												真实姓名：
												<html:text property="agentName" style="width:150px;" styleClass="colorblue2 p_5" />
											</td>
											<td>
												账号：
												<html:text property="agentEmail" style="width:150px;" styleClass="colorblue2 p_5" />
											</td>
											
											<td>
												<input type="submit" name="button" id="button" value="查询"
													class="submit greenBtn" />
											</td>
										</tr>
									</table>

								</div>

								<table width="100%" cellpadding="0" cellspacing="0" border="0"
									class="dataList">
									<tr>
										<th>
											<div>
												序号
											</div>
										</th>
										<th>
											<div
												style="height: 100%; width: 100%; vertical-align: center; padding-top: 4px;">
												<input type="checkbox"
													onclick="checkAll(this, 'selectedItems')" name="sele" />
												全选
											</div>
										</th>
										<th>
											<div>
												登陆名
											</div>
										</th>
										<th>
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
												地址
											</div>
										</th>
										<th>
											<div>
												办公电话
											</div>
										</th>
										<th>
											<div>
												移动电话
											</div>
										</th>
										
										<th>
											<div>
												操作
											</div>
										</th>

									</tr>
									<c:forEach var="info" items="${alf.list}" varStatus="status">
										<tr>
											<td>
												<c:out
													value="${status.count+(alf.intPage-1)*alf.perPageNum}" />
											</td>
											<td>
												<html:multibox property="selectedItems" style="height:5px;"
													value="${info.id}">
												</html:multibox>
											</td>
											<td>
												<c:out value="${info.loginName}" />
											</td>
											<td>
												<html:link
													page="/agent/agentlist.do?thisAction=view&agentId=${info.id}">
													<c:out value="${info.name}" />
												</html:link>
											</td>
											<td>
												<c:out value="${info.email}" />
											</td>
											<td>
												<c:out value="${info.address}" />
											</td>
											<td>
												<c:out value="${info.telephone}" />
											</td>
											<td>
												<c:out value="${info.mobile}" />
											</td>
											
											<td  style="text-align: center;">
												<a href="../agent/agentRelationshiplist.do?thisAction=listParent&id=<c:out value="${info.id}" />">查看上级</a>&nbsp;&nbsp;/&nbsp;&nbsp;<a href="../agent/agentRelationshiplist.do?thisAction=listChild&id=<c:out value="${info.id}"/>">查看下级</a>
											</td>

										</tr>
									</c:forEach>

								</table>

								<div class="splitLine"></div>
								<div class="">

									<table width="100%" style="margin-top: 5px;">
										<tr><c:check code="sb02,sb03">
											<td>
											&nbsp;
													
											</td>
                                           </c:check>
											<td align="right">
												<div>
													共有记录&nbsp;
													<c:out value="${alf.totalRowCount}"></c:out>
													&nbsp;条&nbsp;&nbsp;&nbsp;&nbsp; [
													<a href="JavaScript:turnToPage(document.forms[0],0)">首页</a>
													|
													<a href="JavaScript:turnToPage(document.forms[0],1)">上一页</a>
													|
													<a href="JavaScript:turnToPage(document.forms[0],2)">下一页</a>
													|
													<a href="JavaScript:turnToPage(document.forms[0],3)">
														末页</a>] 页数:
													<c:out value="${alf.intPage}" />
													/
													<c:out value="${alf.pageCount}" />
													]
												</div>
											</td>
										</tr>
									</table>
									<div class="clear"></div>
								</div>
							</td>
							<td width="10" class="tbrr"></td>
						</tr>
						<tr>
							<td width="10" class="tblb"></td>
							<td class="tbbb"></td>
							<td width="10" class="tbrb"></td>
						</tr>

					</table>
				</html:form>
			</div>
		</div>
	</body>
</html>
