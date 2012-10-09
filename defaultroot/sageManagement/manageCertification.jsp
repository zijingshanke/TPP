<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>My JSP 'manageCertification.jsp' starting page</title>
		<link href="../_css/reset.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" language="javascript"
			src="../_js/jquery-1.3.2.min.js"></script>
		<link href="../_css/global.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" language="javascript"
			src="../_js/jquery-1.3.1.min.js"></script>
		<script src="../_js/common.js" type="text/javascript"></script>
		<script type="text/javascript">

</script>
	</head>

	<body>
		<html:form action="/security/certificate.do">
			<html:hidden property="thisAction" value="manageCertification" />
			<input type="hidden" name="type" />

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
								<c:import url="../_jsp/mainTitle.jsp?title1=安全管理&title2=证书管理"
									charEncoding="UTF-8" />

								<hr />
								<!-- 开始 -->
								<div style="display: inline; float: left; margin-right: 20px;">
									<table>
										<tr>
											<td>
												请输入证书信息：
											</td>
										</tr>

										<tr>
											<td>
												姓名：
												<html:text property="name" value=""></html:text>
												邮箱：
												<html:text property="email" value=""></html:text>
												密码：
												<html:password property="password" value=""></html:password>
											</td>
										</tr>
										<tr>
											<td>
												<input type="button" class="button2" value="生成请求证书"
													onclick="generateCertification()" />
											</td>
										</tr>
										<tr>
											<td>
												<input type="button" class="button2" value="签发证书"
													onclick="signPersonCert()" />
											</td>
										</tr>
										<tr>
											<td>
												<input type="button" class="button2" value="接收个人证书"
													onclick="receivePersonCert()" />
											</td>
										</tr>
										<tr>
											<td>
												<input type="button" class="button2" value="导出证书"
													onclick="getP12Cert()" />
											</td>
										</tr>
										<tr>
											<td>
												<input type="button" class="button2" value="吊销证书"
													onclick="revokePersonCert()" />
											</td>
										</tr>

									</table>
								</div>
								<div style="display: inline; float: left; border: 3px;">
									<table>
										<tr>
											<td>
												<c:if test="${msg!=null}">
													<c:out value="${msg}"></c:out>
												</c:if>
												<c:if test="${exceptionMsg!=null}">
													<c:out value="${exceptionMsg}"></c:out>
												</c:if>
											</td>
										</tr>
									</table>
								</div>
								<!-- 结束 -->
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
		</html:form>
		<script type="text/javascript">

function generateCertification(){
	document.forms[0].type.value="generate";
	document.forms[0].submit();


}
function signPersonCert(){
		document.forms[0].type.value="sign";
	document.forms[0].submit();

}
function receivePersonCert(){
		document.forms[0].type.value="receive";
	document.forms[0].submit();


}
function getP12Cert(){
		document.forms[0].type.value="export";
	document.forms[0].submit();


}
function revokePersonCert(){
document.forms[0].type.value="revoke";
	document.forms[0].submit();


}
</script>
	</body>
</html>
