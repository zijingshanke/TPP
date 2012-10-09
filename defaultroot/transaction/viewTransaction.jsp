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
<c:import url="../_jsp/mainTitle.jsp?title1=交易管理&title2=详细信息" charEncoding="UTF-8" />
			
			<table width="100%" cellpadding="0" cellspacing="0" border="0"
				class="dataList">
				<tr>
					<td class="lef" width="10%">商品名称</td><td style="text-align: left" width="30%"><c:out value="${orderDetails.shopName}"/></td>
				    <td class="lef" width="10%">商品价格</td><td style="text-align: left" width="10%"><fmt:formatNumber value="${orderDetails.shopPrice}" pattern="0.00"/>&nbsp;元</td>
					<td class="lef" width="10%">商品数量</td><td style="text-align: left" width="10%"><c:out value="${orderDetails.shopTotal}"/></td>
					<td class="lef" width="10%">商品展示地址</td><td style="text-align: left" width="10%"><c:out value="${orderDetails.shopUrl}"/></td>
				</tr>
				<tr>
					<td class="lef">商品说明</td><td style="text-align: left" ><c:out value="${orderDetails.detailsContent}"/></td>
					<td class="lef">物流方式</td><td style="text-align: left" ><c:out value="${orderDetails.logisticsType}"/></td>
					<td class="lef">邮费承担方</td><td style="text-align: left" ><c:out value="${orderDetails.emailPriceUndertake}"/></td>
					<td class="lef">邮费</td><td style="text-align: left" ><c:out value="${orderDetails.emailPrice}"/></td>
				</tr>
				<tr>
					<td class="lef">购买类型</td><td style="text-align: left" ><c:out value="${orderDetails.buyType}"/></td>
					<td class="lef">付款原因</td><td style="text-align: left" ><c:out value="${orderDetails.paymentReason}"/></td>
					<td class="lef">付款金额</td><td style="text-align: left" ><fmt:formatNumber value="${orderDetails.paymentPrice}" pattern="0.00"/>&nbsp;元</td>
					<td class="lef">收货地址</td><td style="text-align: left" >&nbsp;<c:out value="${orderDetails.consigneeAddress}"/></td>
				</tr>
			
			</table>
			
			<hr/>
			<c:if test="${listSize>0}">
<c:import url="../_jsp/mainTitle.jsp?title1=交易管理&title2=日志信息" charEncoding="UTF-8" />
			<hr/>
			<table width="100%" cellpadding="0" cellspacing="0" border="0"
				class="dataList" style="word-break :break-all">
				<tr>
				<th width="10%"><div>时间</div></td>
				<th width="83%"><div>内容</div></td>
				<th width="7%"><div>状态</div></td>
				</tr>
				<c:forEach var="info" items="${list}">
				<tr>
				<td style="text-align: left;"><c:out value="${info.logDate}"/></td>
				<td style="text-align: left;"><c:out value="${info.content}"/></td>
				<td style="text-align: left;"><c:out value="${info.logTypeShow}"/></td>
				</tr>
				</c:forEach>
				</table>
				<hr/>
				</c:if>
			<table width="100%" style="margin-top: 5px;">
				<tr>
					<td><input name="label"
						type="button" class="button1" value="返 回" onclick="window.history.back();">
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
</div>
</div>
</body>
</html>
