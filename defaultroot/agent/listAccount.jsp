<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>银行卡信息</title>  
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
		<link href="../_css/reset.css" rel="stylesheet" type="text/css" />
		<link href="../_css/global.css" rel="stylesheet" type="text/css" />
				<script src="../_js/common.js" type="text/javascript"></script>
  </head>
  
<body>
		<div id="mainContainer">
			<div id="container">
				<html:form action="/agent/agent.do?thisAction=listAccount">
					<table width="100%" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<td width="10" height="10" class="tblt"></td>
							<td height="10" class="tbtt"></td>
							<td width="10" height="10" class="tbrt"></td>
						</tr>
						<tr>
							<td width="10" class="tbll"></td>
							<td valign="top" class="body">
<c:import url="../_jsp/mainTitle.jsp?title1=商户管理&title2=银行卡信息" charEncoding="UTF-8" />
								<div class="searchBar">
								</div>

								<table width="100%" cellpadding="0" cellspacing="0" border="0"
									class="dataList">
									<tr>
										<th><div>帐户名</div></th>
										<th><div>省</div></th>	
										<th><div>市</div></th>					
										<th><div>银行</div></th>
										<th><div>银行卡名称</div></th>
										<th><div>银行卡号码</div></th>
										<th><div>银行卡类型</div></th>
										<th><div>开户时间</div></th>
										<th><div>冻结时间</div></th>
										<th><div>绑定支付</div></th>
									</tr>
									<c:forEach var="info" items="${alf.list}" varStatus="status">
										<td><c:out value="${info.accountName}"></c:out></td>
										<td><c:out value="${info.city.province.cname}"></c:out></td>
										<td><c:out value="${info.city.cname}"></c:out></td>
										<td><c:out value="${info.accountAddress}"></c:out></td>
										<td><c:out value="${info.cardName}"></c:out></td>
										<td><c:out value="${info.cardNo}"></c:out></td>
										<td><c:out value="${info.cardType}"></c:out></td>
										<td><c:out value="${info.createDate}"></c:out></td>
										<td><c:out value="${info.frozenDate}"></c:out></td>
										<td><c:out value="${info.flgBindStatus}"></c:out></td>
										</tr>
									</c:forEach>

								</table>
								<input name="label" type="button" class="button1" value="返 回" onclick="window.history.back();">
								<div class="splitLine"></div>
								<div class="">

									<table width="100%" style="margin-top: 5px;">
										<tr>
											<td>
											</td>

											<td align="right">
												
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
