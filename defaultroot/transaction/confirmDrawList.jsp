<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<%
int i = 1;
%>
<html>
	<head>
		<title>确认提现</title>
		<link href="../_css/reset.css" rel="stylesheet" type="text/css" /> 
		<link href="../_css/global.css" rel="stylesheet" type="text/css" />
		<script src="../_js/common.js" type="text/javascript"></script>
		<script type="text/javascript">
		function confirm(){
			document.forms[0].action="../transaction/drawlist.do?thisAction=confirmDraw";
			document.forms[0].submit();
		}
		</script>
	</head>
	<!-- Jsp页面:/transaction/confirmDrawList -->
	<body>
		<div id="mainContainer">
			<div id="container">
				<html:form action="/transaction/drawlist.do">
					<table width="100%" cellpadding="0" cellspacing="0" border="0"
						class="dataList" style="word-break: break-all">
						<tr>
							<th>
								<div>
									序号
								</div>
							</th>
							<th>
								<div>
									姓名
								</div>
							</th>
							<th>
								<div>
									账户
								</div>
							</th>
							<th>
								<div>
									金额
								</div>
							</th>
						</tr>

						<c:forEach items="${templist}" var="info">
							<tr>
								<td>
									<%=i%>
									<html:hidden property="selectedItems" value="${info.draw.id}" />
									<html:hidden property="bank" value="${info.draw.bank}" />
								</td>
								<td>
									<c:out value="${info.agent.name}"></c:out>
								</td>
								<td>
									<c:out value="${info.account.cardNo}"></c:out>
								</td>
								<td>
									<c:out value="${info.draw.amount}"></c:out>
								</td>
							</tr>
							<%
							i++;
							%>
						</c:forEach>
						<tr>
							<td colspan="3">
								总计:
							</td>
							<td>
								<c:out value="${totalAmount}"></c:out>
							</td>
						</tr>
					</table>
					<input type="button" class="button3" value="确认支付"
						onclick="confirm()" />
					<input type="button" class="button3" value="放弃支付"
						onclick="window.history.go(-2)" />
					<div>
						&nbsp;
					</div>
				</html:form>
			</div>
		</div>
	</body>
</html>