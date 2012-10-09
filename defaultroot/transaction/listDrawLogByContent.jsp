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
						<td valign="top" class="body">
<c:import url="../_jsp/mainTitle.jsp?title1=提现管理&title2=提现详细" charEncoding="UTF-8" />
							<div class="searchBar">
							</div>
								<table width="100%" cellpadding="0" cellspacing="0" border="0"
									class="dataList">
									<tr>
										<td width="25%">
											<div>
												交易号
											</div>
										</td>
										<td width="25%">
											<c:out value="${draw.orderNo}" />
										</td>
										<td width="25%">
											<div>
												商户名
											</div>
										</td>
										<td width="25%">
											<html:link
												page="/agent/agentlist.do?thisAction=view&agentId=${draw.agent.id}">
												<c:out value="${draw.agent.name}" />
											</html:link>
										</td>
									</tr>
									<tr>
										<td>
											<div>
												申请时间
											</div>
										</td>
										<td>
										<fmt:formatDate pattern="yyyy年MM月dd日 HH:mm:ss" value="${draw.requestDate}"/>
										</td>
										<td>
											<div>
												完成时间
											</div>
										</td>
										<td>
											<fmt:formatDate pattern="yyyy年MM月dd日 HH:mm:ss" value="${draw.drawDate}"/>
										</td>
									</tr>
									<tr>
										<td>
											<div>
												批准人
											</div>
										</td>
										<td>
											<c:out value="${draw.sysUser.userName}" />
										</td>
										<td>
											<div>
												批准时间
											</div>
										</td>
										<td>
											<fmt:formatDate pattern="yyyy年MM月dd日 HH:mm:ss" value="${draw.checkDate}"/>
										</td>
									</tr>
									<tr>
										<td>
											<div>
												核准人
											</div>
										</td>
										<td>
											<c:out value="${draw.sysUser1.userName}" />
										</td>
										<td>
											<div>
												核准时间
											</div>
										</td>
										<td>
											<fmt:formatDate pattern="yyyy年MM月dd日 HH:mm:ss" value="${draw.check1Date}"/>
										</td>
									</tr>
									<tr>
										<td>
											<div>
												备注
											</div>
										</td>
										<td>
											<c:out value="${draw.noteCaption}" escapeXml="false" />
										</td>
										<td>
											<div>
												提取金额(元)
											</div>
										</td>
										<td style="text-align: right;">
											<fmt:formatNumber value="${draw.amount}" pattern="0.00" />
											&nbsp;
										</td>

									</tr>
								</table>



							<hr />

								<table width="100%" cellpadding="0" cellspacing="0" border="0"
									class="dataList">
									<tr><th width="20%"><div>操作时间</div></th>
										<th width="10%"><div>操作人</div></th>
										<th width="60%"><div>备注</div></th>
										<th width="10%"><div>操作说明</div></th>
									</tr>
									<c:forEach var="info" items="${clf.list}" varStatus="status">
										<tr>
											<td>
												<fmt:formatDate pattern="yyyy年MM月dd日 HH:mm:ss" value="${info.chargeDate}"/>
											</td>
											<td>
												<c:if test="${info.status==0}">
													<c:out value="${info.sysUser.userName}" />
												</c:if>
												<c:if test="${info.status==1}">
													<c:out value="${draw.sysUser.userName}" />
												</c:if>
												<c:if test="${info.status==2}">
													<c:out value="${draw.sysUser1.userName}" />
												</c:if>
												<c:if test="${info.status==3}">
													<c:out value="${draw.sysUser3.userName}" />
												</c:if>
												&nbsp;
											</td>
											<td style="text-align: left;">
												<c:out value="${info.content}" />
											</td>
											<td>
												<c:if test="${info.status==0}">
												申请
												</c:if>
												<c:if test="${info.status==3}">
												成功
												</c:if>
												<c:if test="${info.status==1}">
												批准
												</c:if>
												<c:if test="${info.status==2}">
												核准
												</c:if>
											</td>
									</c:forEach>
								</table><br/>
								<input name="label" type="button" class="button1" value="返 回"
									onclick="window.history.back();">
								<hr />
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
