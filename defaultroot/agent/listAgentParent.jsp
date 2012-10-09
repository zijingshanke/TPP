<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<html>
	<head>
		<title>上下级管理</title>
		<link href="../_css/reset.css" rel="stylesheet" type="text/css" />
		<link href="../_css/global.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" language="javascript"
			src="../_js/jquery-1.3.1.min.js"></script>
		<script src="../_js/common.js" type="text/javascript"></script>
		<script type="text/javascript">
        function addCoterie()
		{
		    document.forms[0].thisAction.value="add";
		    document.forms[0].submit();
		}
        function editCoterie()
		{
		 if(document.forms[0].selectedItems==null){
		   	alert("没有数据，无法操作！");
		   }
		  else
		  if (sumCheckedBox(document.forms[0].selectedItems)<1)
		    alert("您还没有选择！");
		  else if (sumCheckedBox(document.forms[0].selectedItems)>1)
		    alert("您一次只能选择一个！");
		  else
		  {
		    document.forms[0].thisAction.value="edit";
		    document.forms[0].submit();
		  }
		}
        function delCoterie()
		{
		 if(document.forms[0].selectedItems==null){
		   	alert("没有数据，无法操作！");
		   }
		  else
		  if (sumCheckedBox(document.forms[0].selectedItems)<1)
		    alert("您还没有选择！");
		  else if(confirm("您真的要删除选择的这些吗？"))
		  {
		    document.forms[0].thisAction.value="delete";
		    document.forms[0].submit();
		  }
		}
        
	function searchCoterie()
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
		
		function checkItem(e, allName)
		{
		   var all = document.getElementsByName(allName)[0];
		   if(!e.checked) all.checked = false;
		   else
		   {
		     var aa = document.getElementsByName(e.name);
		     for (var i=0; i<aa.length; i++)
		      if(!aa[i].checked) return;
		     all.checked = true;
		   }
		}

        </script>
	</head>
	<body>
		<div id="mainContainer">
			<div id="container">
				<html:form action="/agent/agentRelationshiplist.do">
					<html:hidden property="thisAction"/>
					<html:hidden property="lastAction"/>
					<html:hidden property="intPage"/>
					<html:hidden property="pageCount"/>
					<table width="100%" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<td width="10" height="10" class="tblt"></td>
							<td height="10" class="tbtt"></td>
							<td width="10" height="10" class="tbrt"></td>
						</tr>
						<tr>
							<td width="10" class="tbll"></td>
							<td valign="top" class="body">
<c:import url="../_jsp/mainTitle.jsp?title1=上下级管理&title2=我的上级列表" charEncoding="UTF-8" />

								<div class="searchBar">
									<p>
										搜索栏
									</p>
									<hr />

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
												我的上级
											</div>
										</th>
										<th>
											<div>
												上级帐号(email)
											</div>
										</th>
										<th>
											<div>
												创建时间
											</div>
										</th>
										<th>
											<div>
												有效期
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
												<html:multibox property="selectedItems" value="${info.id}"
													onclick="checkItem(this, 'sele')">
												</html:multibox>
											</td>
											<td>
												<c:out value="${info.parentAgent.name}"></c:out>
											</td>
											<td>
												<c:out value="${info.parentAgent.loginName}"></c:out>
											</td>
											<td>
												<c:out value="${info.createDate}"></c:out>
											</td>
											<td>
												<c:out value="${info.expireDate}"></c:out>
											</td>
											
										</tr>
									</c:forEach>

								</table>

								<div class="splitLine"></div>
								<div class="">

									<table width="100%" style="margin-top: 5px;">
										<tr>
											&nbsp;
											</td>

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
									<input type="button" value="返 回" class="button2" onclick="window.history.back();"/>
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
