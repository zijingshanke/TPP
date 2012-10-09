<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt" %>
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
		var type="<c:out value='${type}'/>";
		var tempAmount=document.forms[0].tempAmount.value;
		var magerr=document.getElementById("magerr");
		magerr.innerHTML="";
		
		if(/^(([1-9]\d+|[1-9])|0)(\.\d\d?)*$/.test(tempAmount)){
				if(type==1){
					 if(confirm("确定冻结"+tempAmount+"元吗？")){  
							document.forms[0].action="../agent/agent.do?thisAction=freezeMoney";
							document.forms[0].submit();
						}
				}if(type==2){
					if(confirm("确定解冻"+tempAmount+"元吗？")){  
							document.forms[0].action="../agent/agent.do?thisAction=thawMoney";
							document.forms[0].submit();
						}
				}
		}else{
				magerr.innerHTML="金额输入错误！";
				return false;
		}
		
}
</script>
</head>
<body>
<div id="mainContainer">
<div id="container"><html:form action="/agent/agent.do">
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
			<c:if test="${type eq 1}">
	<c:import url="../_jsp/mainTitle.jsp?title1=商户管理&title2=冻结金额" charEncoding="UTF-8" />
	</c:if>
			<c:if test="${type eq 2}">
	<c:import url="../_jsp/mainTitle.jsp?title1=商户管理&title2=解冻金额" charEncoding="UTF-8" />
	</c:if>
			<hr>
			<table width="100%" cellpadding="0" cellspacing="0" border="0"
				class="dataList">
				<tr>
					<td class="lef">用户名</td>
					<td style="text-align: left" ><c:out value="${agent.name}"></c:out> </td>
				</tr>
				<tr>
					<td class="lef">登录帐号</td>
					<td style="text-align: left"><c:out value="${agent.email}"></c:out></td>
				</tr>
				<tr>
					<td class="lef">登陆名</td>
					<td style="text-align: left"><c:out value="${agent.loginName}"></c:out></td>
				</tr>
				<tr>
					<td class="lef">可用余额</td>
					<td style="text-align: left"><FONT color="red;"><fmt:formatNumber value="${agent.allowBalance}" pattern="0.00" /></FONT> 元</td>
				</tr>
				<tr>
					<td class="lef">冻结余额</td>
					<td style="text-align: left"><FONT color="red;"><fmt:formatNumber value="${agent.notallowBalance}" pattern="0.00" /></FONT> 元</td>
				</tr>
				<tr>
					<td class="lef">授信金额</td>
					<td style="text-align: left"><FONT color="red;"><fmt:formatNumber value="${agent.creditBalance}" pattern="0.00" /></FONT> 元</td>
				</tr>
				<tr>
					<td class="lef">输入金额</td>
					<td style="text-align: left"> 
					<html:text property="tempAmount" styleClass="colorblue2 p_5" onkeyup="value=value.replace(/[^\d,.]/g,'')"></html:text> 元<div id="magerr" style="color: red;"></div>
					</td>
				</tr>
						<tr>
					<td class="lef">备注</td>
					<td style="text-align: left"> 
					<html:textarea property="transactionMark" styleClass="colorblue2 p_5" style="width:405px; height:50px;"></html:textarea>
					</td>
				</tr>
			</table>
			<table width="100%" style="margin-top: 5px;">
			<c:check code="sb03">
			<tr>
			<td>
			<input type="button" value="返 回" class="button1" onclick="javascript:history.back(-1);"/>&nbsp;
			<c:if test="${type==1}">
				<input type="button" value="冻结" class="button1" onclick="updateAgent();"/>
			</c:if>
		   <c:if test="${type==2}">
				<input type="button" value="解冻" class="button1" onclick="updateAgent();"/>
			</c:if>
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
