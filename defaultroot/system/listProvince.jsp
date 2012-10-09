<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<html>
	<head>
		<title>银行列表</title>
		<link href="../_css/reset.css" rel="stylesheet" type="text/css" />
		<link href="../_css/global.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" language="javascript"
			src="../_js/jquery-1.3.1.min.js"></script>
		<script src="../_js/common.js" type="text/javascript"></script>

		<script>

function add()
{
    document.forms[0].thisAction.value="add";
    document.forms[0].submit();
}

function edit()
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

function del()
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




</script>
	</head>
	<body>
		<div id="mainContainer">
			<!-- 滑动 
			<div class="closeSiseBar">
				<span class="btn"></span>
			</div>
			  -->
			<div id="container">
				<html:form action="/system/provincelist.do">
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
<c:import url="../_jsp/mainTitle.jsp?title1=银行管理&title2=银行列表" charEncoding="UTF-8" />
	<div class="searchBar">
									<p>
										搜索栏
									</p>
									<hr />

									<table cellpadding="0" cellspacing="0" border="0"
										class="searchPanel">
										<tr>
											<td>
												省(中文)
											<html:text property="province.cname"></html:text>				
											</td>
											<td>
												省(英文)	
											<html:text property="province.ename"></html:text>		
											</td>			
											<td>
												<c:check code="sg02">
												<input name="label" type="button" class="button1"
													value="新 增" onclick="add();">
											</c:check>
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
										
										<th>
											<div>
												省(中文)
												
											</div>
										</th>

										<th>
											<div>
												省(英文)
											</div>
										</th>
										


									</tr>
									<c:forEach var="info" items="${plf.list}" varStatus="status">
										<tr>

											<td>
												<html:multibox property="selectedItems" value="${info.id}"></html:multibox>
											</td>
											<td>
												<c:out value="${info.cname}" />
											</td>
													<td>
												<c:out value="${info.ename}" />
											</td>
												
										</tr>
									</c:forEach>

								</table>

								<table width="100%" style="margin-top: 5px;">
									<tr>
										<td>
											
											<c:check code="sg03">
												<input name="label" type="button" class="button1"
													value="修 改" onclick="edit();">
											</c:check>
											<c:check code="sg03">
												<input name="label" type="button" class="button1"
													value="删 除" onclick="del();">
											</c:check>

										</td>
										<td align="right">
											<div>
												共有记录&nbsp;
												<c:out value="${plf.totalRowCount}"></c:out>
												&nbsp;条&nbsp;&nbsp;&nbsp;&nbsp; [
												<a href="JavaScript:turnToPage(document.forms[0],0)">首页</a>
												|
												<a href="JavaScript:turnToPage(document.forms[0],1)">上一页</a>
												|
												<a href="JavaScript:turnToPage(document.forms[0],2)">下一页</a>
												|
												<a href="JavaScript:turnToPage(document.forms[0],3)"> 末页</a>]
												页数:
												<c:out value="${plf.intPage}" />
												/
												<c:out value="${plf.pageCount}" />
												]
											</div>
										</td>
									</tr>
								</table>
								<div class="clear"></div>

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
