<%@ page language="java" pageEncoding="utf-8"%>
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

		<script type="text/javascript">
		function addPatternShortMessage()
		{
		    document.forms[0].thisAction.value="add";
		    document.forms[0].submit();
		}

		function editPatternShortMessage()
		{
		 if(document.forms[0].selectedItems==null){
		   	alert("没有数据，无法操作！");
		   }
		  else
		  if (sumCheckedBox(document.forms[0].selectedItems)<1)
		    alert("您还没有选择邮件模板！");
		  else if (sumCheckedBox(document.forms[0].selectedItems)>1)
		    alert("您一次只能选择一个邮件模板！");
		  else
		  {
		    document.forms[0].thisAction.value="edit";
		    document.forms[0].submit();
		  }
		}

		function delPatternShortMessage()
		{
		 if(document.forms[0].selectedItems==null){
		   	alert("没有数据，无法操作！");
		   }
		  else
		  if (sumCheckedBox(document.forms[0].selectedItems)<1)
		    alert("您还没有选择邮件模板！");
		  else if(confirm("您真的要删除选择的这些邮件模板吗？"))
		  {
		    document.forms[0].thisAction.value="delete";
		    document.forms[0].submit();
		  }
		}

		function searchPatternShortMessage()
		{
		   document.forms[0].thisAction.value="list";
		   document.forms[0].submit();
		}
	</script>
	</head>
	<body>
		<div id="mainContainer">
			<div id="container">
				<html:form action="/system/patternShortMessagelist.do" >
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
								<div class="crumb">
<c:import url="../_jsp/mainTitle.jsp?title1=系统管理&title2=邮件模板列表" charEncoding="UTF-8" />
								</div>
								<div class="searchBar" style="height: 10%">

								</div>
								<table width="100%" cellpadding="0" cellspacing="0" border="0"
									class="dataList">
									<tr>
										<th>
											<div>
												序号
											</div>
										</th>
										<th width="35">
											<div>
												选择
											</div>
										</th>
										<th>
											<div>
												名称
											</div>
										</th>
										<th>
											<div>
												状态
											</div>
										</th>
										<th>
											<div>
												内容
											</div>
										</th>
										<th>
											<div>
												Code
											</div>
										</th>
										<th>
											<div>
												描述
											</div>
										</th>
									</tr>
									<c:forEach var="info" items="${psmlf.list}" varStatus="status">
										<tr>
											<td>
												<c:out
													value="${status.count+(psmlf.intPage-1)*psmlf.perPageNum}" />
											</td>
											<td>
												<html:multibox property="selectedItems" value="${info.id}"></html:multibox>
											</td>
											<td>
												<html:link
													page="/system/patternShortMessagelist.do?thisAction=view&id=${info.id}">
													<c:out value="${info.name}" />
												</html:link>
											</td>
											<td>
												<c:out value="${info.statusCaption}" />
											</td>
											<td>
												<c:out value="${info.content}" />
											</td>
											<td>
												<c:out value="${info.code}" />
											</td>
											<td>
												<c:out value="${info.description}" />
											</td>
										</tr>
									</c:forEach>
								</table>
								<table width="100%" style="margin-top: 5px;">
									<tr>
										<td>
										<c:check code="sg11">
											<input name="label" type="button" class="button1" value="新 增"
												onclick="addPatternShortMessage();">
												</c:check>
												<c:check code="sg12">
											<input name="label" type="button" class="button1" value="修 改"
												onclick="editPatternShortMessage();">
												</c:check>
												<c:check code="sg13">
											<input name="label" type="button" class="button1" value="删 除"
												onclick="delPatternShortMessage();">
												</c:check>
										</td>
										<td align="right">
											<div>
												共有记录&nbsp;
												<c:out value="${psmlf.totalRowCount}"></c:out>
												&nbsp;条&nbsp;&nbsp;&nbsp;&nbsp; [
												<a href="JavaScript:turnToPage(document.forms[0],0)">首页</a> |
												<a href="JavaScript:turnToPage(document.forms[0],1)">上一页</a> |
												<a href="JavaScript:turnToPage(document.forms[0],2)">下一页</a> |
												<a href="JavaScript:turnToPage(document.forms[0],3)"> 末页</a>]
												页数:
												<c:out value="${psmlf.intPage}" />
												/
												<c:out value="${psmlf.pageCount}" />
												]
											</div>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</html:form>
			</div>
		</div>
	</body>
</html>