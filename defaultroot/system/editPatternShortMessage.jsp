<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<html>
	<head>
		<title>editPatternShortMessage</title>
		<link href="../_css/reset.css" rel="stylesheet" type="text/css" />
		<link href="../_css/global.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" language="javascript"
			src="../_js/jquery-1.3.1.min.js"></script>
		<script src="../_js/common.js" type="text/javascript"></script>

		<script>
			function addPatternShortMessage()
			{
			    document.forms[0].submit();
			}
		</script>
	</head>
	<body>
		<div id="mainContainer">
			<div id="container">
				<html:form action="/system/patternShortMessage.do">
					<html:hidden property="thisAction" name="patternShortMessage" />
					<html:hidden property="id" name="patternShortMessage" />
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
									<c:import url="../_jsp/mainTitle.jsp?title1=短信管理&title2=短信列表"
										charEncoding="UTF-8" />
								</div>
								<hr>
								<table width="100%" cellpadding="0" cellspacing="0" border="0"
									class="dataList">
									<tr>
										<td class="lef">
											名称
										</td>
										<td style="text-align: left">
											<html:text property="name" name="patternShortMessage"
												styleClass="colorblue2 p_5" />
										</td>
									</tr>
									<tr>
										<td class="lef">
											状态
										</td>
										<td style="text-align: left">
											<!--<html:text property="status" name="patternShortMessage" />-->
											<html:radio property="status" value="1"
												name="patternShortMessage">有效</html:radio>
											<html:radio property="status" value="0"
												name="patternShortMessage">无效</html:radio>
										</td>
									</tr>
									<tr>
										<td class="lef">
											Code
										</td>
										<td style="text-align: left">
											<html:text property="code" name="patternShortMessage"
												styleClass="colorblue2 p_5" />
										</td>
									</tr>
									<tr>
										<td class="lef">
											内容
										</td>
										<td style="text-align: left">
											<html:text property="content" name="patternShortMessage"
												styleClass="colorblue2 p_5" style="width:100%;heigh:100%;" />
										</td>
									</tr>
									<tr>
										<td class="lef">
											描述
										</td>
										<td style="text-align: left">
											<html:textarea property="description"
												name="patternShortMessage" styleClass="colorblue4 p_5"
												style="width:100%;heigh:100%;" />
										</td>
									</tr>
								</table>
								<table width="100%" style="margin-top: 5px;">
									<c:check code="sg11,sg12">
										<tr>
											<td>
												<input name="label" type="button" class="button1" value="保存"
													�� �    onclick="addPatternShortMessage();">

												<input name="label" type="button" class="button1" value="重置"
													� onclick="document.patternShortMessage.reset();">
											</td>
										</tr>
									</c:check>
								</table>
							</td>
						</tr>
					</table>
				</html:form>
			</div>
		</div>
	</body>
</html>
