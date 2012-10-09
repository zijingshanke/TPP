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
<script type="text/javascript">
function download(no){
	window.open("../transaction/expenseLoglist.do?thisAction=downloadExpenseDetailed&orderNo="+no);
}
</script>
	</head>
	<body>
		<div id="mainContainer">
			<div id="container">

				<table width="100%" cellpadding="0" cellspacing="0" border="0">
					<tr>
						<td width="10" height="10" class="tblt"></td>
						<td height="10" class="tbtt"></td>
						<td width="10" height="10" class="tbrt"></td>
					</tr>
					<tr>
						<td width="10" class="tbll"></td>
						<td valign="top" class="body" width="1220px;">
<c:import url="../_jsp/mainTitle.jsp?title1=交易管理&title2=预支详细" charEncoding="UTF-8" />
								<br/>
							<div>
								<table width="100%" cellpadding="0" cellspacing="0" border="0"
									class="dataList">
									<tr>
										<td> <div>交易号</div></td>
										<td><c:out value="${expense.no}" /></td>
										<td><div>申请方</div></td>
										<td>
											<html:link page="/agent/agentlist.do?thisAction=view&agentId=${expense.fromAgent.id}">
												<c:out value="${expense.fromAgent.name}" />
											</html:link>
										</td>
									</tr>
									<tr>
										<td> <div>说明</div></td>
										<td><c:out value="${expense.remark}" /></td>
										<td><div>报销方</div></td>
										<td>
											<html:link page="/agent/agentlist.do?thisAction=view&agentId=${expense.agent.id}">
												<c:out value="${expense.agent.name}" />
											</html:link>
										</td>
									</tr>
									
									<tr>
										<td><div>审核人</div></td>
										<td><c:out value="${expense.userName}" /></td>
										<td><div>审核时间</div></td>
										<td>
												<fmt:formatDate pattern="yyyy年MM月dd日 HH:mm:ss" value="${expense.checkDate}"/>
										</td>
									</tr>
									<tr>
										<td><div>批准人</div></td>
										<td><c:out value="${expense.user1Name}" /></td>
										<td><div>批准时间</div></td>
										<td>
												<fmt:formatDate pattern="yyyy年MM月dd日 HH:mm:ss" value="${expense.check1Date}"/>
										</td>
									</tr>
									<tr>
										<td><div>报销出纳</div></td>
										<td><c:out value="${expense.user2Name}" /></td>
										<td><div>转账时间</div></td>
										<td><fmt:formatDate pattern="yyyy年MM月dd日 HH:mm:ss" value="${expense.check2Date}"/></td>
									</tr>
									<tr>
										<td><div>预支金额(元)</div></td>
										<td style="text-align: right;">
											<fmt:formatNumber value="${expense.debit.amount}" pattern="0.00" />
											&nbsp;
										</td>
										<td><div>报销金额(元)</div></td>
										<td style="text-align: right;">
											<fmt:formatNumber value="${expense.amount}" pattern="0.00" />
											&nbsp;
										</td>
									</tr>
									<tr>
										<td><div>备注</div></td>
										<td><c:out value="${expense.noteCaption}" escapeXml="false" /></td>
										<td><div>实际操作金额(元)</div></td>
										<td style="text-align: right;">
											<fmt:formatNumber value="${finalAmount}" pattern="0.00" />
											&nbsp;
										</td>

									</tr>
								</table>



							<br />

								<table width="100%" cellpadding="0" cellspacing="0" border="0" class="dataList" style="word-break :break-all">
									<tr><th width="20%"><div>操作时间</div></th>
										<th width="10%"><div>操作人</div></th>
										<th width="60%"><div>备注</div></th>
										<th width="10%"><div>操作说明</div></th>
									</tr>
									<c:forEach var="info" items="${clf.list}" varStatus="status">
										<tr><td width="20%">
												<fmt:formatDate pattern="yyyy年MM月dd日 HH:mm:ss" value="${info.chargeDate}"/>
											</td>
											<td width="10%">
											<c:out value="${info.operater}" />
											</td>
											<td width="60%" style="text-align: left;"><c:out value="${info.content}" /></td>
											<td width="10%">
												<c:if test="${info.status==0}">
												申请
												</c:if>
												<c:if test="${info.status==1}">
												审核
												</c:if>
												<c:if test="${info.status==2}">
												批准
												</c:if>
												<c:if test="${info.status==3}">
												完成
												</c:if>
											</td>
									</c:forEach>
								</table><br/>
								<input name="label" type="button" class="button3" value="下载报销详细" onclick="download('<c:out value="${expense.no}" />');">
								<input name="label" type="button" class="button1" value="返 回" onclick="window.history.back();">
								<hr />
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

			</div>
		</div>
	</body>
</html>
