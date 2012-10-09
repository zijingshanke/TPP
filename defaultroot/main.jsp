<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<c:if test="${URI==null}">
	<script language="JavaScript">
   	top.location="login.jsp" 
	</script>
</c:if>

<html>
<head>
<title>main</title>
<link href="../_css/reset.css" rel="stylesheet" type="text/css" />
<link href="../_css/global.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" language="javascript"
	src="../_js/jquery-1.3.1.min.js"></script>
<script src="../_js/common.js" type="text/javascript"></script>
<script type="text/javascript">
function sel(bank,statu)
{
	var url="../transaction/drawlist.do?thisAction=listDraw"
	window.location.href=url+"&status="+statu+"&bank="+bank; 
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
		<td valign="top" class="body">
		<table>
			<tr>
				<td>欢迎您： <font color="red"><c:out value="${user.userName}" /> </font></td>
			</tr>
			<c:check code="sc09">
				<tr>
					<td><a
						href="../transaction/chargelist.do?thisAction=listOtherCharge&status=0&bank=OTHER">您有
					<font color="red"><c:out value="${application}" /> </font>条线下充值需要批准！</a></td>
				</tr>
			</c:check>
			<c:check code="sc10">
				<tr>
					<td><a
						href="../transaction/chargelist.do?thisAction=listOtherCharge&status=3&bank=OTHER">您有
					<font color="red"><c:out value="${audit}" /> </font>条线下充值需要核准！</a></td>
				</tr>
			</c:check>
			<tr>
					<td><a href="../transaction/drawlist.do?thisAction=listDraw&status=0&bank=0&type=1">您有
					<font color="red"><c:out value="${total}" /> </font>个商户需要实名认证！</a></td>
				</tr>
		</table>
 	<table width="100%" cellpadding="0" cellspacing="0" border="0" class="dataList">
 			<tr>
				<th width="20%"> <div>状态</div> </th>
				<th width="25%"> <div>银行</div> </th>
				<th> <div>笔数</div> </th>
				<th> <div>金额（元）</div> </th>
			</tr>
			<!-- 目前需要批准的提现请求开始 -->
				<!-- ICBC -->
				<tr>
					<td rowspan="6" width="20%">
					<a href="../transaction/drawlist.do?thisAction=listDraw&status=0&bank=0">目前需要批准的提现请求</a></td>
					<td><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='ICBC'&& tradingStatistics.status==0}">
							<img src="../_img/bank/icon_icbc_s.gif" onclick="sel('ICBC',0);" />
						</c:if>
					</c:forEach></td>
					<td><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='ICBC'&& tradingStatistics.status==0}">
							<c:out value="${tradingStatistics.article}" />
						</c:if>
					</c:forEach></td>
					<td style="text-align: right;">
					<c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='ICBC'&& tradingStatistics.status==0}">
							<fmt:formatNumber value="${tradingStatistics.money}" pattern="0.00" />
						</c:if>
					</c:forEach>
					</td>
				</tr>
				<!-- CCB -->
				<tr>
					<td><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='CCB'&& tradingStatistics.status==0}">
							<img src="../_img/bank/icon_ccb_s.gif" onclick="sel('CCB',0);" />
						</c:if>
					</c:forEach></td>
					<td><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='CCB'&& tradingStatistics.status==0}">
							<c:out value="${tradingStatistics.article}" />
						</c:if>
					</c:forEach></td>
					<td style="text-align: right;"><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='CCB'&& tradingStatistics.status==0}">
							<fmt:formatNumber value="${tradingStatistics.money}" pattern="0.00" />
						</c:if>
					</c:forEach></td>
				</tr>
				<!-- BCM -->
				<tr>
					<td><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='BCM'&& tradingStatistics.status==0}">
							<img src="../_img/bank/bankLogo_10.gif" onclick="sel('BCM',0);" />
						</c:if>
					</c:forEach></td>
					<td><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='BCM'&& tradingStatistics.status==0}">
							<c:out value="${tradingStatistics.article}" />
						</c:if>
					</c:forEach></td>
					<td style="text-align: right;"><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='BCM'&& tradingStatistics.status==0}">
							<fmt:formatNumber value="${tradingStatistics.money}" pattern="0.00" />
						</c:if>
					</c:forEach></td>
				</tr>
				<!-- CMBC -->
				<tr>
					<td><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='CMBC'&& tradingStatistics.status==0}">
							<img src="../_img/bank/bank_04.gif" onclick="sel('CMBC',0);" />
						</c:if>
					</c:forEach></td>
					<td><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='CMBC'&& tradingStatistics.status==0}">
							<c:out value="${tradingStatistics.article}" />
						</c:if>
					</c:forEach></td>
					<td style="text-align: right;"><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='CMBC'&& tradingStatistics.status==0}">
							<fmt:formatNumber value="${tradingStatistics.money}" pattern="0.00" />
						</c:if>
					</c:forEach></td>
				</tr>
				<!-- ABC -->
				<tr>
					<td><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='ABC'&& tradingStatistics.status==0}">
							<img src="../_img/bank/bank_06.gif" onclick="sel('ABC',0);" />
						</c:if>
					</c:forEach></td>
					<td><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='ABC'&& tradingStatistics.status==0}">
							<c:out value="${tradingStatistics.article}" />
						</c:if>
					</c:forEach></td>
					<td style="text-align: right;"><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='ABC'&& tradingStatistics.status==0}">
							<fmt:formatNumber value="${tradingStatistics.money}" pattern="0.00" />
						</c:if>
					</c:forEach></td>
				</tr>
				<!-- CITIC -->
				<tr>
					<td><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='CITIC'&& tradingStatistics.status==0}">
							<img src="../_img/bank/bankLogo_11.gif" onclick="sel('CITIC',0);" />
						</c:if>
					</c:forEach></td>
					<td><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='CITIC'&& tradingStatistics.status==0}">
							<c:out value="${tradingStatistics.article}" />
						</c:if>
					</c:forEach></td>
					<td style="text-align: right;"><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='CITIC'&& tradingStatistics.status==0}">
							<fmt:formatNumber value="${tradingStatistics.money}" pattern="0.00" />
						</c:if>
					</c:forEach></td>
				</tr>
			<!-- 目前需要批准的提现请求结束 -->
			<!-- 目前需要核准的提现请求开始 -->
				<!-- ICBC -->
				<tr>
					<td rowspan="6" width="20%">
					<a href="../transaction/drawlist.do?thisAction=listDraw&status=1&bank=0">目前需要核准的提现请求</a></td>
					<td><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='ICBC'&& tradingStatistics.status==1}">
							<img src="../_img/bank/icon_icbc_s.gif" onclick="sel('ICBC',1);" />
						</c:if>
					</c:forEach></td>
					<td><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='ICBC'&& tradingStatistics.status==1}">
							<c:out value="${tradingStatistics.article}" />
						</c:if>
					</c:forEach></td>
					<td style="text-align: right;">
					<c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='ICBC'&& tradingStatistics.status==1}">
							<fmt:formatNumber value="${tradingStatistics.money}" pattern="0.00" />
						</c:if>
					</c:forEach>
					</td>
				</tr>
				<!-- CCB -->
				<tr>
					<td><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='CCB'&& tradingStatistics.status==1}">
							<img src="../_img/bank/icon_ccb_s.gif" onclick="sel('CCB',1);" />
						</c:if>
					</c:forEach></td>
					<td><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='CCB'&& tradingStatistics.status==1}">
							<c:out value="${tradingStatistics.article}" />
						</c:if>
					</c:forEach></td>
					<td style="text-align: right;"><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='CCB'&& tradingStatistics.status==1}">
							<fmt:formatNumber value="${tradingStatistics.money}" pattern="0.00" />
						</c:if>
					</c:forEach></td>
				</tr>
				<!-- BCM -->
				<tr>
					<td><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='BCM'&& tradingStatistics.status==1}">
							<img src="../_img/bank/bankLogo_10.gif" onclick="sel('BCM',1);" />
						</c:if>
					</c:forEach></td>
					<td><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='BCM'&& tradingStatistics.status==1}">
							<c:out value="${tradingStatistics.article}" />
						</c:if>
					</c:forEach></td>
					<td style="text-align: right;"><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='BCM'&& tradingStatistics.status==1}">
							<fmt:formatNumber value="${tradingStatistics.money}" pattern="0.00" />
						</c:if>
					</c:forEach></td>
				</tr>
				<!-- CMBC -->
				<tr>
					<td><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='CMBC'&& tradingStatistics.status==1}">
							<img src="../_img/bank/bank_04.gif" onclick="sel('CMBC',1);" />
						</c:if>
					</c:forEach></td>
					<td><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='CMBC'&& tradingStatistics.status==1}">
							<c:out value="${tradingStatistics.article}" />
						</c:if>
					</c:forEach></td>
					<td style="text-align: right;"><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='CMBC'&& tradingStatistics.status==1}">
							<fmt:formatNumber value="${tradingStatistics.money}" pattern="0.00" />
						</c:if>
					</c:forEach></td>
				</tr>
				<!-- ABC -->
				<tr>
					<td><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='ABC'&& tradingStatistics.status==1}">
							<img src="../_img/bank/bank_06.gif" onclick="sel('ABC',1);" />
						</c:if>
					</c:forEach></td>
					<td><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='ABC'&& tradingStatistics.status==1}">
							<c:out value="${tradingStatistics.article}" />
						</c:if>
					</c:forEach></td>
					<td style="text-align: right;"><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='ABC'&& tradingStatistics.status==1}">
							<fmt:formatNumber value="${tradingStatistics.money}" pattern="0.00" />
						</c:if>
					</c:forEach></td>
				</tr>
				<!-- CITIC -->
				<tr>
					<td><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='CITIC'&& tradingStatistics.status==1}">
							<img src="../_img/bank/bankLogo_11.gif" onclick="sel('CITIC',1);" />
						</c:if>
					</c:forEach></td>
					<td><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='CITIC'&& tradingStatistics.status==1}">
							<c:out value="${tradingStatistics.article}" />
						</c:if>
					</c:forEach></td>
					<td style="text-align: right;"><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='CITIC'&& tradingStatistics.status==1}">
							<fmt:formatNumber value="${tradingStatistics.money}" pattern="0.00" />
						</c:if>
					</c:forEach></td>
				</tr>
			<!-- 目前需要批准的提现请求结束 -->
			<!-- 已经完成核准的提现请求开始 -->
				<!-- ICBC -->
				<tr>
					<td rowspan="6" width="20%">
					<a href="../transaction/drawlist.do?thisAction=listDraw&status=2&bank=0">已经完成核准的提现请求</a></td>
					<td><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='ICBC'&& tradingStatistics.status==2}">
							<img src="../_img/bank/icon_icbc_s.gif" onclick="sel('ICBC',2);" />
						</c:if>
					</c:forEach></td>
					<td><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='ICBC'&& tradingStatistics.status==2}">
							<c:out value="${tradingStatistics.article}" />
						</c:if>
					</c:forEach></td>
					<td style="text-align: right;">
					<c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='ICBC'&& tradingStatistics.status==2}">
							<fmt:formatNumber value="${tradingStatistics.money}" pattern="0.00" />
						</c:if>
					</c:forEach>
					</td>
				</tr>
				<!-- CCB -->
				<tr>
					<td><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='CCB'&& tradingStatistics.status==2}">
							<img src="../_img/bank/icon_ccb_s.gif" onclick="sel('CCB',2);" />
						</c:if>
					</c:forEach></td>
					<td><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='CCB'&& tradingStatistics.status==2}">
							<c:out value="${tradingStatistics.article}" />
						</c:if>
					</c:forEach></td>
					<td style="text-align: right;"><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='CCB'&& tradingStatistics.status==2}">
							<fmt:formatNumber value="${tradingStatistics.money}" pattern="0.00" />
						</c:if>
					</c:forEach></td>
				</tr>
				<!-- BCM -->
				<tr>
					<td><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='BCM'&& tradingStatistics.status==2}">
							<img src="../_img/bank/bankLogo_10.gif" onclick="sel('BCM',2);" />
						</c:if>
					</c:forEach></td>
					<td><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='BCM'&& tradingStatistics.status==2}">
							<c:out value="${tradingStatistics.article}" />
						</c:if>
					</c:forEach></td>
					<td style="text-align: right;"><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='BCM'&& tradingStatistics.status==2}">
							<fmt:formatNumber value="${tradingStatistics.money}" pattern="0.00" />
						</c:if>
					</c:forEach></td>
				</tr>
				<!-- CMBC -->
				<tr>
					<td><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='CMBC'&& tradingStatistics.status==2}">
							<img src="../_img/bank/bank_04.gif" onclick="sel('CMBC',2);" />
						</c:if>
					</c:forEach></td>
					<td><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='CMBC'&& tradingStatistics.status==2}">
							<c:out value="${tradingStatistics.article}" />
						</c:if>
					</c:forEach></td>
					<td style="text-align: right;"><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='CMBC'&& tradingStatistics.status==2}">
							<fmt:formatNumber value="${tradingStatistics.money}" pattern="0.00" />
						</c:if>
					</c:forEach></td>
				</tr>
				<!-- ABC -->
				<tr>
					<td><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='ABC'&& tradingStatistics.status==2}">
							<img src="../_img/bank/bank_06.gif" onclick="sel('ABC',2);" />
						</c:if>
					</c:forEach></td>
					<td><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='ABC'&& tradingStatistics.status==2}">
							<c:out value="${tradingStatistics.article}" />
						</c:if>
					</c:forEach></td>
					<td style="text-align: right;"><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='ABC'&& tradingStatistics.status==2}">
							<fmt:formatNumber value="${tradingStatistics.money}" pattern="0.00" />
						</c:if>
					</c:forEach></td>
				</tr>
				<!-- CITIC -->
				<tr>
					<td><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='CITIC'&& tradingStatistics.status==2}">
							<img src="../_img/bank/bankLogo_11.gif" onclick="sel('CITIC',2);" />
						</c:if>
					</c:forEach></td>
					<td><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='CITIC'&& tradingStatistics.status==2}">
							<c:out value="${tradingStatistics.article}" />
						</c:if>
					</c:forEach></td>
					<td style="text-align: right;"><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='CITIC'&& tradingStatistics.status==2}">
							<fmt:formatNumber value="${tradingStatistics.money}" pattern="0.00" />
						</c:if>
					</c:forEach></td>
				</tr>
			<!-- 已经完成核准的提现请求结束 -->
			<!-- 已经完成转账的提现请求开始 -->
				<!-- ICBC -->
				<tr>
					<td rowspan="6" width="20%">
					<a href="../transaction/drawlist.do?thisAction=listDraw&status=3&bank=0">已经完成转账的提现请求</a></td>
					<td><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='ICBC'&& tradingStatistics.status==3}">
							<img src="../_img/bank/icon_icbc_s.gif" onclick="sel('ICBC',3);" />
						</c:if>
					</c:forEach></td>
					<td><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='ICBC'&& tradingStatistics.status==3}">
							<c:out value="${tradingStatistics.article}" />
						</c:if>
					</c:forEach></td>
					<td style="text-align: right;">
					<c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='ICBC'&& tradingStatistics.status==3}">
							<fmt:formatNumber value="${tradingStatistics.money}" pattern="0.00" />
						</c:if>
					</c:forEach>
					</td>
				</tr>
				<!-- CCB -->
				<tr>
					<td><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='CCB'&& tradingStatistics.status==3}">
							<img src="../_img/bank/icon_ccb_s.gif" onclick="sel('CCB',3);" />
						</c:if>
					</c:forEach></td>
					<td><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='CCB'&& tradingStatistics.status==3}">
							<c:out value="${tradingStatistics.article}" />
						</c:if>
					</c:forEach></td>
					<td style="text-align: right;"><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='CCB'&& tradingStatistics.status==3}">
							<fmt:formatNumber value="${tradingStatistics.money}" pattern="0.00" />
						</c:if>
					</c:forEach></td>
				</tr>
				<!-- BCM -->
				<tr>
					<td><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='BCM'&& tradingStatistics.status==3}">
							<img src="../_img/bank/bankLogo_10.gif" onclick="sel('BCM',3);" />
						</c:if>
					</c:forEach></td>
					<td><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='BCM'&& tradingStatistics.status==3}">
							<c:out value="${tradingStatistics.article}" />
						</c:if>
					</c:forEach></td>
					<td style="text-align: right;"><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='BCM'&& tradingStatistics.status==3}">
							<fmt:formatNumber value="${tradingStatistics.money}" pattern="0.00" />
						</c:if>
					</c:forEach></td>
				</tr>
				<!-- CMBC -->
				<tr>
					<td><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='CMBC'&& tradingStatistics.status==3}">
							<img src="../_img/bank/bank_04.gif" onclick="sel('CMBC',3);" />
						</c:if>
					</c:forEach></td>
					<td><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='CMBC'&& tradingStatistics.status==3}">
							<c:out value="${tradingStatistics.article}" />
						</c:if>
					</c:forEach></td>
					<td style="text-align: right;"><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='CMBC'&& tradingStatistics.status==3}">
							<fmt:formatNumber value="${tradingStatistics.money}" pattern="0.00" />
						</c:if>
					</c:forEach></td>
				</tr>
				<!-- ABC -->
				<tr>
					<td><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='ABC'&& tradingStatistics.status==3}">
							<img src="../_img/bank/bank_06.gif" onclick="sel('ABC',3);" />
						</c:if>
					</c:forEach></td>
					<td><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='ABC'&& tradingStatistics.status==3}">
							<c:out value="${tradingStatistics.article}" />
						</c:if>
					</c:forEach></td>
					<td style="text-align: right;"><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='ABC'&& tradingStatistics.status==3}">
							<fmt:formatNumber value="${tradingStatistics.money}" pattern="0.00" />
						</c:if>
					</c:forEach></td>
				</tr>
				<!-- CITIC -->
				<tr>
					<td><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='CITIC'&& tradingStatistics.status==3}">
							<img src="../_img/bank/bankLogo_11.gif" onclick="sel('CITIC',3);" />
						</c:if>
					</c:forEach></td>
					<td><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='CITIC'&& tradingStatistics.status==3}">
							<c:out value="${tradingStatistics.article}" />
						</c:if>
					</c:forEach></td>
					<td style="text-align: right;"><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='CITIC'&& tradingStatistics.status==3}">
							<fmt:formatNumber value="${tradingStatistics.money}" pattern="0.00" />
						</c:if>
					</c:forEach></td>
				</tr>
			<!-- 已经完成转账的提现请求结束 -->
			<!-- 已经被撤销的提现请求开始 -->
				<!-- ICBC -->
				<tr>
					<td rowspan="6" width="20%">
					<a href="../transaction/drawlist.do?thisAction=listDraw&status=4&bank=0">已经被撤销的提现请求</a></td>
					<td><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='ICBC'&& tradingStatistics.status==4}">
							<img src="../_img/bank/icon_icbc_s.gif" onclick="sel('ICBC',4);" />
						</c:if>
					</c:forEach></td>
					<td><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='ICBC'&& tradingStatistics.status==4}">
							<c:out value="${tradingStatistics.article}" />
						</c:if>
					</c:forEach></td>
					<td style="text-align: right;">
					<c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='ICBC'&& tradingStatistics.status==4}">
							<fmt:formatNumber value="${tradingStatistics.money}" pattern="0.00" />
						</c:if>
					</c:forEach>
					</td>
				</tr>
				<!-- CCB -->
				<tr>
					<td><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='CCB'&& tradingStatistics.status==4}">
							<img src="../_img/bank/icon_ccb_s.gif" onclick="sel('CCB',4);" />
						</c:if>
					</c:forEach></td>
					<td><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='CCB'&& tradingStatistics.status==4}">
							<c:out value="${tradingStatistics.article}" />
						</c:if>
					</c:forEach></td>
					<td style="text-align: right;"><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='CCB'&& tradingStatistics.status==4}">
							<fmt:formatNumber value="${tradingStatistics.money}" pattern="0.00" />
						</c:if>
					</c:forEach></td>
				</tr>
				<!-- BCM -->
				<tr>
					<td><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='BCM'&& tradingStatistics.status==4}">
							<img src="../_img/bank/bankLogo_10.gif" onclick="sel('BCM',4);" />
						</c:if>
					</c:forEach></td>
					<td><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='BCM'&& tradingStatistics.status==4}">
							<c:out value="${tradingStatistics.article}" />
						</c:if>
					</c:forEach></td>
					<td style="text-align: right;"><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='BCM'&& tradingStatistics.status==4}">
							<fmt:formatNumber value="${tradingStatistics.money}" pattern="0.00" />
						</c:if>
					</c:forEach></td>
				</tr>
				<!-- CMBC -->
				<tr>
					<td><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='CMBC'&& tradingStatistics.status==4}">
							<img src="../_img/bank/bank_04.gif" onclick="sel('CMBC',4);" />
						</c:if>
					</c:forEach></td>
					<td><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='CMBC'&& tradingStatistics.status==4}">
							<c:out value="${tradingStatistics.article}" />
						</c:if>
					</c:forEach></td>
					<td style="text-align: right;"><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='CMBC'&& tradingStatistics.status==4}">
							<fmt:formatNumber value="${tradingStatistics.money}" pattern="0.00" />
						</c:if>
					</c:forEach></td>
				</tr>
				<!-- ABC -->
				<tr>
					<td><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='ABC'&& tradingStatistics.status==4}">
							<img src="../_img/bank/bank_06.gif" onclick="sel('ABC',4);" />
						</c:if>
					</c:forEach></td>
					<td><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='ABC'&& tradingStatistics.status==4}">
							<c:out value="${tradingStatistics.article}" />
						</c:if>
					</c:forEach></td>
					<td style="text-align: right;"><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='ABC'&& tradingStatistics.status==4}">
							<fmt:formatNumber value="${tradingStatistics.money}" pattern="0.00" />
						</c:if>
					</c:forEach></td>
				</tr>
				<!-- CITIC -->
				<tr>
					<td><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='CITIC'&& tradingStatistics.status==4}">
							<img src="../_img/bank/bankLogo_11.gif" onclick="sel('CITIC',4);" />
						</c:if>
					</c:forEach></td>
					<td><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='CITIC'&& tradingStatistics.status==4}">
							<c:out value="${tradingStatistics.article}" />
						</c:if>
					</c:forEach></td>
					<td style="text-align: right;"><c:forEach var="tradingStatistics" items="${ts}">
						<c:if test="${tradingStatistics.bank=='CITIC'&& tradingStatistics.status==4}">
							<fmt:formatNumber value="${tradingStatistics.money}" pattern="0.00" />
						</c:if>
					</c:forEach></td>
				</tr>
			<!-- 已经被撤销的提现请求结束 -->
 	</table>
 
		</td>
	</tr>
	<tr>
		<td width="10" class="tbrr"></td>
	</tr>
	<tr>
		<td width="10" class="tblb"></td>
		<td class="tbbb"></td>
		<td width="10" class="tbrb"></td>
	</tr>
</table>
<div class="clear"></div>
</div>
</div>
</body>
</html>
