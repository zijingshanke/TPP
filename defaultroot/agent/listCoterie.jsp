<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<html>
	<head>
		<title>商户圈</title>
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
				<html:form action="/agent/coterielist.do" >
					<html:hidden property="thisAction" name="clf" />
					<html:hidden property="lastAction" name="clf" />
					<html:hidden property="intPage" name="clf" />
					<html:hidden property="pageCount" name="clf" />
					<table width="100%" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<td width="10" height="10" class="tblt"></td>
							<td height="10" class="tbtt"></td>
							<td width="10" height="10" class="tbrt"></td>
						</tr>
						<tr>
							<td width="10" class="tbll"></td>
							<td valign="top" class="body">
<c:import url="../_jsp/mainTitle.jsp?title1=商户管理&title2=商户圈列表" charEncoding="UTF-8" />

								<div class="searchBar">
									<p>
										搜索栏
									</p>
									<hr />

									<table cellpadding="0" cellspacing="0" border="0"
										class="searchPanel">
										<tr>
											<td>
												圈名：
												<html:text property="coterieName" style="width:150px;"
													styleClass="colorblue2 p_5" />
											</td>
											<td>
												中间账户：
												<html:text property="agentEmail" style="width:150px;" styleClass="colorblue2 p_5"/>
											</td>
											<td>
												临时账户：
												<html:text property="tempAgentEmail" style="width:150px;" styleClass="colorblue2 p_5"/>
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
												商户圈名
											</div>
										</th>
										<th>
											<div>
												中间帐户
											</div>
										</th>
										<th>
											<div>
												临时帐户
											</div>
										</th>
										<th>
											<div>
												支付费率
											</div>
										</th>
										<th>
											<div>
												合作伙伴
											</div>
										</th>
										<th>
											<div>
												签名密钥
											</div>
										</th>
										<th>
											<div>
												签名方式
											</div>
										</th>
											<th>
											<div>
												商务圈类型
											</div>
										</th>
										<th>
											<div>
												操作
											</div>
										</th>
									</tr>
									<c:forEach var="info" items="${clf.list}" varStatus="status">
										<tr>
											<td>
												<c:out
													value="${status.count+(clf.intPage-1)*clf.perPageNum}" />
											</td>
											<td>
												<html:multibox property="selectedItems" value="${info.id}"
													onclick="checkItem(this, 'sele')">
												</html:multibox>
											</td>
											<td>
												<c:out value="${info.name}"></c:out>
											</td>
											<td>
												<html:link
													page="/agent/agentlist.do?thisAction=view&agentId=${info.agent.id}">
													<c:out value="${info.agent.email}"></c:out>
												</html:link>
											</td>
											<td>
											<html:link page="/agent/agentlist.do?thisAction=view&agentId=${info.tempAgent.id}">
												<c:out value="${info.tempAgent.email}"></c:out>
												</html:link>
											</td>
											<td>
												<c:out value="${info.rate}"></c:out>
											</td>
											<td>
												<c:out value="${info.partner}"></c:out>
											</td>
											<td>
												<c:out value="${info.signKey}"></c:out>
											</td>
												<td>
												<c:out value="${info.signType}"></c:out>
											</td>
												<td>
												<c:out value="${info.tempAllow}"></c:out>
											</td>
										
											<td class="operationArea">
												<c:check code="sb08">
												<html:link
													page="/agent/agentcoterielist.do?thisAction=list&coterieId=${info.id}">查看圈内商户</html:link>
											    </c:check>
											</td>
										</tr>
									</c:forEach>

								</table>

								<div class="splitLine"></div>
								<div class="">

									<table width="100%" style="margin-top: 5px;">
										<tr>
											<td><c:check code="sb05">
												<input name="label" type="button" class="button1"
													value="新 增" onclick="addCoterie();">
													</c:check>
												<c:check code="sb06">
												<input name="label" type="button" class="button1"
													value="修 改" onclick="editCoterie();">
													</c:check>
												<c:check code="sb07">
												<input name="label" type="button" class="button1"
													value="删 除" onclick="delCoterie();">
													</c:check>
											</td>

											<td align="right">
												<div>
													共有记录&nbsp;
													<c:out value="${clf.totalRowCount}"></c:out>
													&nbsp;条&nbsp;&nbsp;&nbsp;&nbsp; [
													<a href="JavaScript:turnToPage(document.forms[0],0)">首页</a>
													|
													<a href="JavaScript:turnToPage(document.forms[0],1)">上一页</a>
													|
													<a href="JavaScript:turnToPage(document.forms[0],2)">下一页</a>
													|
													<a href="JavaScript:turnToPage(document.forms[0],3)">
														末页</a>] 页数:
													<c:out value="${clf.intPage}" />
													/
													<c:out value="${clf.pageCount}" />
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
