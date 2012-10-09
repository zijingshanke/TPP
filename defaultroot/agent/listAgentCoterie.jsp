<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html" %>  
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<html>
	<head>
		<title>商户圈商户</title>
		<link href="../_css/reset.css" rel="stylesheet" type="text/css" />
		<link href="../_css/global.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" language="javascript" src="../_js/jquery-1.3.1.min.js"></script>
        <script src="../_js/common.js" type="text/javascript"></script>
        <script type="text/javascript">
        function add()
		{
		    document.forms[0].thisAction.value="add";
		    document.forms[0].submit();
		}
        function addAll()
		{
		    document.forms[0].thisAction.value="addAll";
		    document.forms[0].submit();
		}
		
		   function update()
		{
		    document.forms[0].thisAction.value="update";
		    document.forms[0].submit();
		}
			
		
        function edit()
		{ if(document.forms[0].selectedItems==null){
		   	alert("没有数据，无法操作！");
		   }
		  else
		  if (sumCheckedBox(document.forms[0].selectedItems)<1)
		    alert("您还没有选择！");
		  else if (sumCheckedBox(document.forms[0].selectedItems)>1)
		    alert("您一次只能选择一个！");
		  else
		  {
		    document.forms[0].thisAction.value="update";
		    document.forms[0].submit();
		  }
		}
        function del()
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
		<div id="mainContainer" >
			<div id="container">
			<html:form action="/agent/agentcoterielist.do" method="post">
			<html:hidden property="coterieId" />
			<html:hidden property="thisAction" />
			<html:hidden property="lastAction" />
			<html:hidden property="intPage" />
			<html:hidden property="pageCount" />
				<table width="100%" cellpadding="0" cellspacing="0" border="0" >
					<tr>
						<td width="10" height="10" class="tblt"></td>
						<td height="10" class="tbtt"></td>
						<td width="10" height="10" class="tbrt"></td>
					</tr>
					<tr>
						<td width="10" class="tbll"></td>
						<td valign="top" class="body">
<c:import url="../_jsp/mainTitle.jsp?title1=商户圈管理&title2=圈内商户列表" charEncoding="UTF-8" />

							<div class="searchBar">
									<p>
										搜索栏
									</p>
									<hr />
                                        
									<table cellpadding="0" cellspacing="0" border="0"
										class="searchPanel">
										<tr>
											<td>商户账号：
											<html:text property="agentEmail" style="width:150px;" styleClass="colorblue2 p_5"></html:text>
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
										<div>序号</div>
									</th>
									<th>
											<div style="height: 100%; width: 100%; vertical-align: center; padding-top: 4px;">
												<input type="checkbox" onclick="checkAll(this, 'selectedItems')" name="sele" />全选</div>
									</th>
									<th>
										<div>商户圈名</div>
									</th>
									<th>
										<div>商户账号</div>
									</th>
									<th>
										<div>加入时间</div>
									</th>
																		
									<th>
										<div>开始时间</div>
									</th>
									<th>
										<div>结束时间</div>
									</th>
									<th>
										<div>还款天数</div>
									</th>
									<th>
										<div>最低信用金额(元)</div>
									</th>
									<th>
										<div>交易限制</div>
									</th>
									<th>
										<div>还款方式</div>
									</th>
									
									<th>
										<div>状态</div>
									</th>
									
								</tr>
								  <c:forEach var="info"  items="${alf.list}" varStatus="status"> 
								<tr>
									<td><c:out value="${status.count+(alf.intPage-1)*alf.perPageNum}" /></td>
									<td>
									<html:multibox property="selectedItems"
							value="${info.id}" onclick="checkItem(this, 'sele')">
									</html:multibox>
									</td>
									<td><c:out value="${info.coterie.name}"/></td>
									<td><html:link page="/agent/agentlist.do?thisAction=view&agentId=${info.agent.id}"><c:out value="${info.agent.email}"></c:out> </html:link> </td>
									<td><fmt:formatDate pattern="yyyy年MM月dd日 HH:mm:ss" value="${info.createDate}"/></td>
									
									
									
									<td><fmt:formatDate pattern="yyyy年MM月dd日 HH:mm:ss" value="${info.fromDate}"/></td>
									<td><fmt:formatDate pattern="yyyy年MM月dd日 HH:mm:ss" value="${info.expireDate}"/></td>
									<td><c:out value="${info.leaveDays}"></c:out> </td>
									<td><c:out value="${info.minAmount}"></c:out> </td>
									<td>
									 <c:if test="${info.transactionScope==0}"> 内买</c:if>
		                              <c:if test="${info.transactionScope==1}">外买</c:if>
									</td>
									<td>
									 <c:if test="${info.repaymentType==2}"> 多笔还</c:if>
		                             <c:if test="${info.repaymentType==1}">逐笔还</c:if>
									</td>
									<td><c:out value="${info.tempStatus}"></c:out> </td>
									
								</tr>
								</c:forEach>
								
							</table>
							
							<div class="splitLine"></div>
							<div class="">
								
								<table width="100%" style="margin-top: 5px;">
				<tr>
					<td>
					     <c:check code="sb09">
					     <input name="label" type="button" class="button1" value="新 增" onclick="add();"> 
						 </c:check>
						
						<c:check code="sb10">
					    <input name="label" type="button" class="button1" value="删 除" onclick="del();">
						</c:check>
						<input name="label" type="button" class="button1" value="修 改" onclick=" edit();">
						<input name="label" type="button" class="button1" value="批量添加" onclick="addAll();">
					</td>
						<td align="right">
					<div>共有记录&nbsp;<c:out value="${alf.totalRowCount}"></c:out>&nbsp;条&nbsp;&nbsp;&nbsp;&nbsp;
					[<a href="JavaScript:turnToPage(document.forms[0],0)">首页</a> | 
					<a  href="JavaScript:turnToPage(document.forms[0],1)">上一页</a> | 
					<a href="JavaScript:turnToPage(document.forms[0],2)">下一页</a> | 
					<a href="JavaScript:turnToPage(document.forms[0],3)"> 末页</a>] 页数:
					<c:out value="${alf.intPage}" />/<c:out value="${alf.pageCount}" />]</div>
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
						<td width="10" class="tbrb">&nbsp;</td>
					</tr>
					
				</table></html:form>
			</div>
		</div>
	</body>
</html>
					