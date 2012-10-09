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
		
		<script>
			function addTransactionStatus()
			{
			    document.forms[0].submit();
			}
		</script>
	</head>
	<body>
		<div id="mainContainer">
			<div id="container">
				<html:form action="/system/transactionStatus.do">
					<html:hidden property="thisAction" name="transactionStatus" />
					<html:hidden property="id" name="transactionStatus" />
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
<c:import url="../_jsp/mainTitle.jsp?title1=短信管理&title2=编辑短信" charEncoding="UTF-8" />
								</div>
								<hr>
								<table width="100%" cellpadding="0" cellspacing="0" border="0"
									class="dataList">
									<tr>
										<td  class="lef">
											名称
										</td>
										<td style="text-align: left">
											<html:text property="name" name="transactionStatus" styleClass="colorblue2 p_5"/>
										</td>
									</tr>
									<tr>
										<td class="lef">
											状态
										</td>
										<td style="text-align: left">
										
											<html:radio property="status" value="1"
												name="transactionStatus">有效</html:radio>
											<html:radio property="status" value="0"
												name="transactionStatus">无效</html:radio>
										</td>
									</tr>
									<tr>
										<td class="lef">
											Code
										</td>
										<td style="text-align: left">
											<html:text property="code" name="transactionStatus" styleClass="colorblue2 p_5"/>
										</td>
									</tr>
								</table>
								<table width="100%" style="margin-top: 5px;">
									<tr>
										<td>
											<input name="label" type="button" class="button1" value="提 交"
												onclick="addTransactionStatus();">
											<input name="label" type="button" class="button1" value="重 置"
												onclick="document.transactionStatus.reset();">
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
