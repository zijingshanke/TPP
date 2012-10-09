<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>

<html>
	<head>
		<title>main</title>
		<link href="../_css/reset.css" rel="stylesheet" type="text/css" />
		<link href="../_css/global.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" language="javascript"
			src="../_js/jquery-1.3.1.min.js"></script>
		<script src="../_js/common.js" type="text/javascript"></script>
<script src="../_js/calendar/WdatePicker.js" type="text/javascript"></script>
		<script>
function smb()
{
	var begin=document.getElementById("beginDate").value;
	var end=document.getElementById("endDate").value;
	if(begin!="" && end!="" && begin > end){
		alert("终止时间不能比起始时间小！");
		return false;
	}
			document.forms[0].submit();
}
</script>

	</head>
	<body style="text-align: center;">
		<div id="mainContainer">
			<div id="container">
				<html:form action="/cooperate/actionLoglist.do">
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
<c:import url="../_jsp/mainTitle.jsp?title1=操作管理&title2=操作记录列表" charEncoding="UTF-8" />
								<div class="searchBar">
									<p>
										搜索栏
									</p>
									<hr />

									<table cellpadding="0" cellspacing="0" border="0"
										class="searchPanel">
										<tr>
											<td>订单号：<html:text property="orderNo" styleClass="colorblue2 p_5" style="width:150px;"/> </td> 
											<td>日志内容：<html:text property="content" styleClass="colorblue2 p_5" style="width:150px;"/> </td>
											<td>
												时间：
												<html:text styleId="beginDate" property="beginDate" styleClass="colorblue2 p_5" style="width:150px;"
													onfocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})" readonly="true"/>
												--
												<html:text styleId="endDate" property="endDate" styleClass="colorblue2 p_5" style="width:150px;"
													onfocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})" readonly="true"/>
											</td>
											<td>
												<input type="button" name="button" id="button" value="查询"
													class="submit greenBtn" onclick="smb();" />
											</td>
										</tr>
									</table>
								</div>
								<table width="100%" cellpadding="0" cellspacing="0" border="0" class="dataList" style="word-break :break-all">
									<tr>
										<th width="2%"><div>&nbsp;</div></th>
										<th width="12%"><div>订单号</div></th>
										<th width="10%"><div>操作时间</div></th>
										<th width="5%"><div>状态</div></th>
										<th width="71%"><div>内容</div></th>

									</tr>
									<c:forEach var="info" items="${clf.list}" varStatus="status">
										<tr>
											<td>
												<c:out value="${status.count+(clf.intPage-1)*clf.perPageNum}" />
											</td>
											<td>
												<c:out value="${info.orderDetails.orderNo}" />
											</td>
											<td>
												<fmt:formatDate pattern="yyyy年MM月dd日 HH:mm:ss" value="${info.logDate}"/>
											</td>
											<td>
												<c:out value="${info.logTypeShow}" />
											</td>
											<td style="text-align: left;">
												<c:out value="${info.content}" />
											</td>
										</tr>
									</c:forEach>
								</table>

								<table width="100%" style="margin-top: 5px;">
									<tr>

										<td align="right">
											<div>
												共有记录&nbsp;
												<c:out value="${clf.totalRowCount}"></c:out>
												&nbsp;条&nbsp;&nbsp;&nbsp;&nbsp; [
												<a href="JavaScript:turnToPage(document.forms[0],0)">首页</a> |
												<a href="JavaScript:turnToPage(document.forms[0],1)">上一页</a> |
												<a href="JavaScript:turnToPage(document.forms[0],2)">下一页</a> |
												<a href="JavaScript:turnToPage(document.forms[0],3)"> 末页</a>] 页数:
												<c:out value="${clf.intPage}" />
												/
												<c:out value="${clf.pageCount}" />
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
