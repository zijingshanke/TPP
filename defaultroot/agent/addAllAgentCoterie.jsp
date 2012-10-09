<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>

<html>
<head>
<title>main</title>
<link href="../_css/reset.css" rel="stylesheet" type="text/css" />
<link href="../_css/global.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" language="javascript"
	src="../_js/jquery-1.3.1.min.js"></script>
<script src="../_js/common.js" type="text/javascript"></script>
<script>

function add()
{ 
   document.forms[0].submit();
}

function reset()
{
	document.forms[0].reset();
}
</script>
</head>
<body>
<div id="mainContainer">
<div id="container"><html:form action="/agent/agentcoterie.do">
	<html:hidden property="thisAction" name="agentCoterie" />
	<html:hidden property="id" name="agentCoterie" />
	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td width="10" height="10" class="tblt"></td>
			<td height="10" class="tbtt"></td>
			<td width="10" height="10" class="tbrt"></td>
		</tr>
		<tr>
			<td width="10" class="tbll"></td>
			<td valign="top" class="body">
<c:import url="../_jsp/mainTitle.jsp?title1=商户圈用户管理&title2=批量添加圈内商户列表" charEncoding="UTF-8" />
		
									<hr>
			<table width="100%" cellpadding="0" cellspacing="0" border="0"
				class="dataList">
				<tr>
					<td class="lef">商户圈名</td>
					<td style="text-align: left" ><c:out value="${coterie.name}"/>
					<input type="hidden" name="coterieId" value="<c:out value="${coterie.id}"></c:out>"/>
					</td>
				</tr>
				<tr>
					<td class="lef">商户Email</td>
					<td style="text-align: left">
					<html:textarea property="agentEmail" cols="60" rows="6" name="agentCoterie"></html:textarea><font>*请输入需要添加的商户账号，用 （；分号 ，逗号） 隔开。</font></td>
				</tr>
				
				
			</table>
			<table width="100%" style="margin-top: 5px;">
				<c:check code="sb09"><tr>
					<td>
					
					<input name="label" type="button" class="button1"
						value="提 交" onclick="add();">
						
						 <input name="label"
						type="reset" class="button1" value="重 置" onclick="reset();">
						
					</td>
					</tr></c:check>
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
					
				</table></html:form>
			</div>
		</div>
	</body>
</html>