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
<script type="text/javascript">
function updateAgent(){
	var maxDayAmount=document.forms[0].maxDayAmount.value;
	var maxItermAmount=document.forms[0].maxItermAmount.value;
	var maxDrawAmount=document.forms[0].maxDrawAmount.value;
	var maxItemDrawAmount=document.forms[0].maxItemDrawAmount.value;
	if(maxDayAmount!="" && maxItermAmount!="" && maxDrawAmount!="" && maxItemDrawAmount!=""){
		if(!(/^([0-9]\d+|[0-9])(\.\d\d?)*$/.test(maxDayAmount))){
			alert("当日最大交易金额输入错误！");
			 return false;
		}
		if(!(/^([0-9]\d+|[0-9])(\.\d\d?)*$/.test(maxItermAmount))){
			alert("单笔最大交易金额输入错误！");
			 return false;
		}else if(parseFloat(maxItermAmount*100) > parseFloat(maxDayAmount*100)){
			alert("单笔最大交易金额不能大于当日最大交易金额！");
			 return false;
		}
		if(!(/^([0-9]\d+|[0-9])(\.\d\d?)*$/.test(maxDrawAmount))){
			alert("当日最大提现金额输入错误！");
			 return false;
		}
		if(!(/^([0-9]\d+|[0-9])(\.\d\d?)*$/.test(maxItemDrawAmount))){
			alert("单笔最大提现金额输入错误！");
			 return false;
		}else if(parseFloat(maxItemDrawAmount*100) > parseFloat(maxItemDrawAmount*100)){
			alert("单笔最大提现金额不能大于当日最大提现金额！");
			 return false;
		}
		document.forms[0].submit();
	}else{
		alert("带*号的必须填写");
		return false;
	}
		
	
}
</script>
</head>
<body>
<div id="mainContainer">
<div id="container"><html:form action="/agent/agent.do?thisAction=updateDefaultAmount">
	<html:hidden property="thisAction" name="agent" />
	<html:hidden property="id" name="agent" />
	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td width="10" height="10" class="tblt"></td>
			<td height="10" class="tbtt"></td>
			<td width="10" height="10" class="tbrt"></td>
		</tr>
		<tr>
			<td width="10" class="tbll"></td>
			<td valign="top" class="body">
<c:import url="../_jsp/mainTitle.jsp?title1=商户管理&title2=修改预设金额" charEncoding="UTF-8" />
			<hr>
			<table width="100%" cellpadding="0" cellspacing="0" border="0"
				class="dataList">
				<tr>
					<td class="lef">用户名</td>
					<td style="text-align: left" ><c:out value="${agent.name}"></c:out> </td>
				</tr>
				<tr>
					<td class="lef">登录帐号</td>
					<td style="text-align: left"><c:out value="${agent.loginName}"></c:out></td>
				</tr>
				<tr>
					<td class="lef">当日最大交易金额</td>
					<td style="text-align: left"> <font color="red">*</font> <html:text property="maxDayAmount" styleId="maxDayAmount"  style="width:200px;" styleClass="colorblue2 p_5" value="${agent.maxDayAmount}"/> </td>
				</tr>
				<tr>
					<td class="lef">单笔最大交易金额</td>
					<td style="text-align: left"> <font color="red">*</font> <html:text property="maxItermAmount"  styleId="maxItermAmount" style="width:200px;" styleClass="colorblue2 p_5" value="${agent.maxItermAmount}"/> </td>
				</tr>
				<tr>
					<td class="lef">当日最大提现金额</td>
					<td style="text-align: left"> <font color="red">*</font> <html:text property="maxDrawAmount" styleId="maxDrawAmount" style="width:200px;" styleClass="colorblue2 p_5" value="${agent.maxDrawAmount}"/> </td>
				</tr>
				<tr>
					<td class="lef">单笔最大提现金额</td>
					<td style="text-align: left"> <font color="red">*</font> <html:text property="maxItemDrawAmount" styleId="maxItemDrawAmount" style="width:200px;" styleClass="colorblue2 p_5" value="${agent.maxItemDrawAmount}"/> </td>
				</tr>
				
			</table>
			<table width="100%" style="margin-top: 5px;">
			<c:check code="sb03">
			<tr>
			<td>
			<input type="button" value="提 交" class="button1" onclick="updateAgent();"/>
			
			<input type="reset" value="重 置" class="button1"/>
			</td>
			</tr>
			</c:check>
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

</html:form></div>
</div>
</body>
</html>
