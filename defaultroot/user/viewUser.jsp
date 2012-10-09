<%@ page contentType="text/html;charset=UTF-8" language="java"%>
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


</script>

<style>
.divstyle {
	padding: 5px;
	font-family: "MS Serif", "New York", serif;
	background: #e5e5e5;
}
</style>

</head>
<body>
<div id="mainContainer"><!-- 滑动 
			<div class="closeSiseBar">
				<span class="btn"></span>
			</div>
			  -->
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
			
<c:import url="../_jsp/mainTitle.jsp?title1=用户管理&title2=查看用户详细信息" charEncoding="UTF-8" />
<hr>
			<table width="100%" cellpadding="0" cellspacing="0" border="0"
				class="dataList">
				<tr>
					<td class="lef">名字</td>
					<td style="text-align: left" ><c:out value="${user.userName}"/></td>
				</tr>
				<tr>
					<td class="lef">登录帐号</td>
					<td style="text-align: left" ><c:out value="${user.userNo}"/></td>
				</tr>
				<tr>
					<td class="lef">状态</td>
					<td style="text-align: left" ><c:out value="${user.userStatus}"/></td>
				</tr>
				<tr>
					<td class="lef">序列号</td>
					<td style="text-align: left" ><c:out value="${user.serialNumber}"/></td>
				</tr>
				<tr>
					<td class="lef">Email</td>
					<td style="text-align: left" ><c:out value="${user.userEmail}"/></td>
				</tr>
				<tr>
					<td class="lef">类型</td>
					<td style="text-align: left" ><c:out value="${user.userType}"/></td>
				</tr>

			</table>
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
			
	</table></div>
</div>
</body>
</html>
