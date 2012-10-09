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
					<td class="lef">name</td>
					<td style="text-align: left" ><c:out value="${sysConfig.name}"/></td>
				</tr>
				<tr>
					<td class="lef">code</td>
					<td style="text-align: left" ><c:out value="${sysConfig.code}"/></td>
				</tr>
				<tr>
					<td class="lef">active</td>
					<td style="text-align:left"><c:out value="${sysConfig.active}"/></td>
				</tr>
				<tr>
					<td class="lef">value</td>
					<td style="text-align:left"><c:out value="${sysConfig.value}"/></td>
				</tr>
					<tr>
					<td class="lef">description</td>
					<td style="text-align:left"><c:out value="${sysConfig.description}"/></td>
				</tr>
			</table>
			<table width="100%" style="margin-top: 5px;">
				<tr>
					<td><input name="label"
						type="button" class="button1" value="返 回" onclick="window.history.back();">
					</td>

				</tr>
			</table>
			</td>
		</tr>
	</table>
</div>
</div>
</body>
</html>
