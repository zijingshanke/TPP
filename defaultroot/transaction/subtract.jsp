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
<script type="text/javascript">
function sel()
{
	var email=document.getElementById("email").value;
	var amount=document.getElementById("amount").value;
	var note=document.getElementById("note").value;
	var re=/^([1-9]|[0.])[0-9]{0,}(([.]*\d{1,2})|[0-9]{0,})$/;
	var regm = /^([a-zA-Z0-9]+[-|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	if(!email.match(regm))
	{
	alert("账户为空、出现空格或者Email格式不正确！");
		return false;
	}
	else if(!re.test(amount) || amount<0 || amount>1000000){
		alert("金额必须为数字，最多为两位小数并且不能大于1000000！");
		return false;
	}else if(note=="")
	{
	alert("备注不能为空");
	return false;
	}
	 if(confirm("确认进行扣款吗？")){ 
		document.forms[0].submit();
		}
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
<c:import url="../_jsp/mainTitle.jsp?title1=交易管理&title2=系统扣款" charEncoding="UTF-8" />
							<hr />
							<html:form action="/transaction/draw.do?thisAction=addSubtract">
							<table cellpadding="0" cellspacing="0" border="0" align="center">
							<tr><td style="text-align: right">扣款账户：</td><td> <html:text property="agentLoginName" styleClass="colorblue2 p_5" styleId="email" value=""/> </td></tr>
							<tr><td style="text-align: right">扣款金额：</td><td> <html:text property="amount" styleClass="colorblue2 p_5" styleId="amount" value=""/> <font color="red;"> 元</font> </td></tr>
							<tr><td style="text-align: right">备注：</td><td> <html:text property="note" styleClass="colorblue2 p_5" styleId="note" value=""/> </td></tr>
							<tr><td colspan="2" align="center"> <input type="button"  class="submit greenBtn" value="确认扣款" onclick="sel();"/> </td></tr>
							</table>
							</html:form>
							<hr />
							
							
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
	</body>
</html>
