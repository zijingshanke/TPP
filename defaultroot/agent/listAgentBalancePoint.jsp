<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt" %>
<html>
	<head>
		<title>main</title>
		<link href="../_css/reset.css" rel="stylesheet" type="text/css" />
		<link href="../_css/global.css" rel="stylesheet" type="text/css" />
		<script src="../_js/common.js" type="text/javascript"></script>
		<script src="../_js/calendar/WdatePicker.js" type="text/javascript"></script>
		<script type="text/javascript">
        function addAgent()
		{
		    document.forms[0].thisAction.value="add";
		    document.forms[0].submit();
		}
        function editAgent()
		{
		  if(document.forms[0].selectedItems==null){
		   	alert("没有数据，无法操作！");
		   }
		  else if (sumCheckedBox(document.forms[0].selectedItems)<1)
		    alert("您还没有选择用户！");
		  else if (sumCheckedBox(document.forms[0].selectedItems)>1)
		    alert("您一次只能选择一个用户！");
		  else
		  {
		    document.forms[0].thisAction.value="edit";
		    document.forms[0].submit();
		  }
		}
        function delAgent()
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
        
	function searchAgent()
	{
	   document.forms[0].action="../agent/agentlist.do?thisAction=list";
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
  
  function download(){
  		
  		document.forms[0].thisAction.value="download";
  		document.forms[0].submit();
  		document.forms[0].thisAction.value="listAgent";
  		
  }
 
  function updateAgent(){
     if(confirm("您是否确定要更新该数据吗？")){
    document.forms[0].thisAction.value="updateAgentBalancePointByAgent";
    document.forms[0].submit();}
  }
  
    function  toAgentbal(){
    document.forms[0].thisAction.value="toUpdateAgentBalancePoint";
    document.forms[0].submit();
  }
        </script>
	</head>
	<body>
		<div id="mainContainer">
			<div id="container">
				<html:form action="/agent/agentBalancePointlist.do">
					<html:hidden property="thisAction"/>
					<html:hidden property="lastAction"/>
					<html:hidden property="intPage" />
					<html:hidden property="pageCount" />
					<table width="100%" cellpadding="0" cellspacing="0" border="0"  >
						<tr>
							<td width="10" height="10" class="tblt"></td>
							<td height="10" class="tbtt"></td>
							<td width="10" height="10" class="tbrt"></td>
						</tr>
						<tr>
							<td width="10" class="tbll"></td>
							<td valign="top" class="body">
					<c:import url="../_jsp/mainTitle.jsp?title1=商户管理&title2=商户余额盘点" charEncoding="UTF-8" />
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
												<html:text property="agentName" styleId="agentName" styleClass="colorblue2 p_5" style="width:150px;"/>
											</td>
											
											<td>
												账号：
												<html:text property="agentEmail" styleId="agentEmail" style="width:150px;" styleClass="colorblue2 p_5" />
											</td>
				                           <td>
											排序：
											<html:select property="sort" styleId="sort">
											<html:option value="0">商户名称</html:option>
											<html:option value="1">商户邮箱</html:option>
											<html:option value="2">总余额↑</html:option>
											<html:option value="3">总余额↓</html:option>
											<html:option value="4">可用余额↑</html:option>
											<html:option value="5">可用余额↓</html:option>
											<html:option value="6">冻结余额↑</html:option>
											<html:option value="7">冻结余额↓</html:option>
											<html:option value="8">信用金额↑</html:option>
											<html:option value="9">信用余额↓</html:option>
											</html:select>
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
										<th><div>序号</div></th>
										<th>
											<div style="height: 100%; width: 100%; vertical-align: center; padding-top: 4px;">
												<input type="checkbox" onclick="checkAll(this, 'selectedItems')" name="sele"/>
												全选
											</div>
										</th>
										<th><div>帐号</div></th>
										<th><div>姓名</div></th>
										<th><div>盘点时间</div></th>
										<th><div>可用余额（元）</div></th>
										<th><div>冻结余额（元）</div></th>
										<th><div>信用余额（元）</div></th>
										<th><div>总余额（元）</div></th>
										
										
									</tr>
									<c:forEach var="agentBalancePoint" items="${alf.list}" varStatus="status">
										<tr>
											<td>
												<c:out value="${status.count+(alf.intPage-1)*alf.perPageNum}" />
											</td>
											<td>
												<html:multibox property="selectedItems" style="height:5px;"
													value="${agentBalancePoint.id}" onclick="checkItem(this, 'sele')" >
												</html:multibox>
											</td>
											<td>
												<c:out value="${agentBalancePoint.email}" />
											</td>
											<td>
												<html:link
													page="/agent/agentlist.do?thisAction=view&agentId=${agentBalancePoint.agentId}">
													<c:out value="${agentBalancePoint.name}" />
												</html:link>
											</td>
											<td>
												<c:out value="${agentBalancePoint.balanceDate}" />
											</td>
											<td  style="text-align: right;">
												<fmt:formatNumber value="${agentBalancePoint.allowBalance}" pattern="0.00"/>&nbsp;
											</td>
											<td  style="text-align: right;">
												<fmt:formatNumber value="${agentBalancePoint.notAllowBalance}" pattern="0.00"/>&nbsp;
											</td>
											<td  style="text-align: right;">
												<fmt:formatNumber value="${agentBalancePoint.creditBalance}" pattern="0.00"/>&nbsp;
											</td>
											<td  style="text-align: right;">
												<fmt:formatNumber value="${agentBalancePoint.balanceAndCheckAmount}" pattern="0.00"/>&nbsp;
											</td>
											
		
										</tr>
									</c:forEach>
									
								</table>
								
								<div class="splitLine"></div>
								<div class="">

									<table width="100%" style="margin-top: 5px;">
										<tr><c:check code="sb02,sb03">
											<td>
											<input name="label" type="button" class="button2"
													value="更新商户" onclick="updateAgent();">
													
												
												<input name="label" type="button" class="button2"
													value="更新盘点" onclick="toAgentbal();">
												<!--  
												<input name="label" type="button" class="button1"
													value="删 除" onclick="delAgent();">
													
													<input name="label" type="button" class="button2"
													value="下载商户报表" onclick="download();" /> -->
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
